```flowchart LR
    subgraph DataSources["📊 数据源层"]
        direction TB
        MySQL[(MySQL)]
        PostgreSQL[(PostgreSQL)]
        Oracle[(Oracle)]
        SQLServer[(SQL Server)]
        SaaS["SaaS API / 日志 / CSV / 其他数据源"]
    end

    subgraph DataIngestion["🔄 数据采集层"]
        direction TB
        SchemaRegistry["Schema Registry"]
        CDC["Debezium / Flink CDC<br/>支持：MySQL, PostgreSQL, Oracle,<br/>SQL Server, DB2, MariaDB, MongoDB"]
        Kafka[("Kafka / Pulsar")]
    end

    subgraph Metadata["📚 元数据与语义层"]
        direction TB
        OpenMetadata["元数据管理<br/>OpenMetadata / Amundsen"]
        ColumnMapping["列/宽表关联 & 权限配置"]
        Metrics["指标层 / Cube / dbt metrics"]
        Dashboard["前端 Dashboard / 探索或分析"]
    end

    subgraph Compute["⚡ 计算与查询层"]
        direction TB
        APIGateway["查询代理 / API 网关"]
        Flink["Apache Flink<br/>实时计算层"]
        ClickHouse[(ClickHouse)]
        Druid[(Apache Druid)]
        ES[(Elasticsearch)]
        Trino["Trino / Presto<br/>查询引擎"]
    end

    subgraph DataLake["💾 数据湖存储层"]
        direction TB
        ObjectStorage[("对象存储<br/>S3 / OSS / GCS")]
        TableFormat["Iceberg / Delta / Hudi"]
    end

    %% 数据流向
    MySQL --> CDC
    PostgreSQL --> CDC
    Oracle --> CDC
    SQLServer --> CDC
    SaaS --> CDC
    
    SchemaRegistry -.-> CDC
    CDC -->|"初始快照 / CDC"| Kafka
    Kafka -->|"批量落地"| ObjectStorage
    
    ObjectStorage --> TableFormat
    TableFormat --> Trino
    
    Kafka --> Flink
    Flink -->|"维表 Join"| ClickHouse
    Flink -->|"维表 Join"| Druid
    Flink -->|"全文索引"| ES
    
    OpenMetadata --> ColumnMapping
    ColumnMapping --> Metrics
    Metrics --> Dashboard
    
    Dashboard -->|"列选择 / 聚合 / 过滤 / Join 配置"| APIGateway
    APIGateway --> ClickHouse
    APIGateway --> Druid
    APIGateway --> ES
    APIGateway --> Trino
    
    ClickHouse --> APIGateway
    Druid --> APIGateway
    ES --> APIGateway
    Trino --> APIGateway

    %% 样式
    classDef database fill:#e1f5ff,stroke:#0066cc,stroke-width:2px
    classDef process fill:#fff4e1,stroke:#ff9900,stroke-width:2px
    classDef storage fill:#e8f5e9,stroke:#009900,stroke-width:2px
    classDef compute fill:#fce4ec,stroke:#cc0066,stroke-width:2px
    classDef highlight fill:#ffebee,stroke:#ff0000,stroke-width:3px
    
    class MySQL,PostgreSQL,Oracle,SQLServer,Kafka,ClickHouse,Druid,ES,ObjectStorage database
    class CDC,SchemaRegistry,OpenMetadata,ColumnMapping,Metrics,Flink,TableFormat,APIGateway,Trino process
    class Flink,ClickHouse,Druid highlight
```

## 数据仓库平台（Puzzle DW）技术文档

### 一、项目概述

Puzzle DW 是一个多租户 SaaS 数据仓库平台，用户通过可视化拖拽方式完成数据建模，系统自动完成数据同步、计算、存储和查询。用户无需编写
SQL 和代码，无需感知底层引擎细节。

核心理念
用户只关心业务建模：选源表 → 拖字段 → 配指标 → 点部署
系统自动处理一切：引擎选择、存储分配、数据同步、计算调度、查询路由
多租户隔离：数据、资源、权限完全隔离

### 二、系统架构

```text
┌─────────────────────────────────────────────────────────────┐
│                        前端 (Vue3)                           │
│  数据源管理 │ 可视化建模 │ 指标查询 │ 元数据浏览 │ 系统管理     │
└──────────────────────────┬──────────────────────────────────┘
                           │ REST API
                           ▼
┌──────────────────────────────────────────────────────────────┐
│                      API Gateway                             │
│                  (认证 / 租户识别 / 路由)                      │
└──────────────────────────┬──────────────────────────────────┘
                           │
          ┌────────────────┼────────────────┐
          ▼                ▼                ▼
  ┌──────────────┐ ┌─────────────┐ ┌──────────────┐
  │  dw-tenant   │ │  dw-source  │ │   dw-meta    │
  │  租户/用户/   │ │  数据源管理  │ │  元数据/血缘  │
  │  权限/认证    │ │             │ │              │
  └──────────────┘ └─────────────┘ └──────────────┘
          │                │                │
          ▼                ▼                ▼
  ┌──────────────┐ ┌─────────────┐ ┌──────────────┐
  │  dw-model    │ │  dw-engine  │ │  dw-query    │
  │  模型/指标/   │ │  引擎调度/   │ │  查询路由/   │
  │  维度中心     │ │  任务管理    │ │  SQL生成     │
  └──────────────┘ └─────────────┘ └──────────────┘
                           │
                   ┌───────┼───────┐
                   ▼       ▼       ▼
             ┌─────────┐ ┌────┐ ┌──────────────┐
             │SeaTunnel│ │Flink│ │DolphinSched. │
             └─────────┘ └────┘ └──────────────┘
                   │       │
          ┌────────┼───────┼────────┐
          ▼        ▼       ▼        ▼
      ┌──────┐ ┌───────┐ ┌─────┐ ┌────┐
      │Kafka │ │Iceberg│ │ CK  │ │ ES │
      └──────┘ └───────┘ └─────┘ └────┘
```

### 三、微服务模块

| 服务                | 职责                                                    |
|-------------------|-------------------------------------------------------|
| **puzzle-tenant** | 租户/用户/角色/权限                                           |
| **puzzle-source** | 数据源管理、连通性测试                                           |
| **puzzle-meta**   | Schema 采集、血缘追踪                                        |
| **puzzle-model**  | **核心服务**：模型定义、拖拽建模、指标定义、自动生成执行计划                      |
| **puzzle-engine** | 执行引擎调度：根据模型类型自动提交 SeaTunnel/Flink/DolphinScheduler 任务 |
| **puzzle-query**  | 查询路由、SQL 生成、结果返回                                      |
| **puzzle-system** | 审计日志、告警、系统配置                                          |

```text
┌──────────┬──────────────────────────────────────────────┐
│ 调用方    │ 被调用方.接口                                  │
├──────────┼──────────────────────────────────────────────┤
│ ALL      │ tenant.PermissionRpc (权限校验)                │
│ ALL      │ system.AuditRpc (审计日志)                     │
│ source   │ meta.MetadataRpc.syncSchema                   │
│ meta     │ source.DatasourceRpc.getConnConfig             │
│ model    │ meta.MetadataRpc.getSourceTable/listColumns    │
│ model    │ engine.EngineRpc.deployModel/stop/restart      │
│ engine   │ model.ModelRpc.getModelFull                    │
│ engine   │ source.DatasourceRpc.getConnConfig             │
│ engine   │ meta.MetadataRpc.saveLineage/registerTable     │
│ engine   │ model.ModelRpc.updateModelStatus/saveOutput    │
│ engine   │ tenant.TenantRpc.getTenantConfig               │
│ engine   │ system.AlertRpc.fire                           │
│ query    │ model.MetricRpc/DimensionRpc/ModelRpc          │
│ query    │ tenant.PermissionRpc.getDataMask               │
└──────────┴──────────────────────────────────────────────┘
```

### 四、核心流程

```text
用户点击「部署」
       │
       ▼
  dw-model: 保存版本快照
       │
       ▼
  dw-model → dw-engine: EngineRpc.deployModel(modelId, version)
       │
       ▼
  dw-engine: 获取完整模型定义
       │ ModelRpc.getModelFull()
       │ DatasourceRpc.getConnConfig()
       ▼
  dw-engine: 生成执行计划
       │
       ├── 同步计划 (SeaTunnel HOCON)
       │     源表 → Kafka Topic / Iceberg
       │
       ├── 计算计划 (Flink SQL)
       │     JOIN + 聚合 + 过滤 → ClickHouse
       │
       └── 归档计划 (DolphinScheduler)
             ClickHouse → Iceberg 冷归档
       │
       ▼
  dw-engine: 提交任务到各引擎
       │
       ├── SeaTunnel REST API → 启动同步
       ├── Flink REST API → 提交 SQL Job
       └── DS REST API → 创建调度流程
       │
       ▼
  dw-engine → dw-model: ModelRpc.updateModelStatus(RUNNING)
       │
       ▼
  dw-engine → dw-meta: MetadataRpc.saveLineage()
                        MetadataRpc.registerWarehouseTable()
```

```text
用户选择指标+维度+过滤条件，点击「查询」
       │
       ▼
  dw-query: 接收查询请求
       │
       ├── MetricRpc.getMetricFull() → 获取指标定义
       ├── DimensionRpc.getDimension() → 获取维度定义
       ├── ModelRpc.getModelOutputTable() → 获取产出表
       └── PermissionRpc.getDataMask() → 获取行过滤+列脱敏
       │
       ▼
  dw-query: SQL 生成
       │ SELECT dim_a, dim_b, SUM(amount) AS metric_1
       │ FROM ck_table
       │ WHERE region = 'east'    ← 行过滤注入
       │   AND date BETWEEN ...
       │ GROUP BY dim_a, dim_b
       │ ORDER BY metric_1 DESC
       │ LIMIT 1000
       │
       ▼
  dw-query: 查询路由
       │ 匹配路由规则 → 选择 ClickHouse / Trino / ES
       │
       ▼
  dw-query: 执行查询
       │
       ▼
  dw-query: 列脱敏处理
       │ phone → 138****1234
       │
       ▼
  dw-query: 返回结果 + 记录查询日志
```

```text
用户填写连接信息，点击「测试连接」
       │
       ▼
  dw-source: JDBC 连通性测试
       │ 成功 → 返回测试结果
       │
       ▼
用户点击「保存」
       │
       ▼
  dw-source: 保存数据源
       │ 密码 AES 加密存储
       │
       ▼
用户点击「同步 Schema」
       │
       ▼
  dw-source → dw-meta: MetadataRpc.syncSchema(datasourceId)
       │
       ▼
  dw-meta → dw-source: DatasourceRpc.getConnConfig()
       │
       ▼
  dw-meta: JDBC 采集 information_schema
       │ 写入 p_source_table + p_source_column
       │
       ▼
  前端可在模型设计器中看到源表和字段
```

### 五、数据流架构

```text
源数据库 (MySQL/PG)
       │
       │ SeaTunnel CDC
       ▼
    Kafka Topic
  (tenant_123.orders)
       │
       │ Flink SQL
       │ JOIN + AGG + FILTER
       ▼
   ClickHouse
  (dw_123.model_456)
       │
       │ DolphinScheduler 定时归档
       ▼
    Iceberg
  (s3://dw/tenant_123/model_456/)
```

```text
源数据库 (MySQL/PG/Oracle)
       │
       │ SeaTunnel 批量抽取
       │ (DolphinScheduler 调度)
       ▼
    Iceberg ODS 层
       │
       │ Flink Batch / Trino
       │ JOIN + AGG + FILTER
       ▼
   ClickHouse / Iceberg DWS 层
```