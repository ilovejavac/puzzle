package org.zhongmiao.puzzle.rpc;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 租户 RPC 服务
 * <p>
 * 供各模块调用，获取租户配置信息
 */
public interface TenantRpc {

    /**
     * 获取租户配置信息
     *
     * @param tenantId 租户 ID
     * @return 租户配置
     */
    TenantConfig getTenantConfig(Long tenantId);

    /**
     * 获取租户的引擎配置
     *
     * @param tenantId 租户 ID
     * @return 引擎配置
     */
    EngineConfig getEngineConfig(Long tenantId);

    /**
     * 获取租户的存储配置
     *
     * @param tenantId 租户 ID
     * @return 存储配置
     */
    StorageConfig getStorageConfig(Long tenantId);

    /**
     * 校验租户是否有效
     *
     * @param tenantId 租户 ID
     * @return 是否有效
     */
    Boolean isTenantActive(Long tenantId);

    /**
     * 租户配置信息
     */
    @Data
    class TenantConfig {
        /** 租户 ID */
        private Long tenantId;
        /** 租户名称 */
        private String tenantName;
        /** 租户状态 */
        private String status;
        /** 到期时间 */
        private LocalDateTime expiredAt;
        /** 最大模型数量 */
        private Integer maxModels;
        /** 最大数据源数量 */
        private Integer maxDatasources;
        /** 扩展配置 */
        private Map<String, Object> properties;
    }

    /**
     * 引擎配置
     */
    @Data
    class EngineConfig {
        /** Flink 配置 */
        private Map<String, Object> flinkConfig;
        /** SeaTunnel 配置 */
        private Map<String, Object> seatunnelConfig;
        /** DolphinScheduler 配置 */
        private Map<String, Object> dsConfig;
    }

    /**
     * 存储配置
     */
    @Data
    class StorageConfig {
        /** ClickHouse 配置 */
        private Map<String, Object> clickhouseConfig;
        /** Iceberg 配置 */
        private Map<String, Object> icebergConfig;
        /** Kafka 配置 */
        private Map<String, Object> kafkaConfig;
    }

}
