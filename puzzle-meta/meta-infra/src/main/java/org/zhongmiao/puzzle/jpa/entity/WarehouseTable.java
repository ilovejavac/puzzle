package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.meta.PartitionSpec;
import org.zhongmiao.puzzle.enums.WarehouseStorageType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据仓库表实体
 * 用于管理数仓中的表元数据，支持多种存储类型（Iceberg/Hudi/Paimon）和分区策略
 */
@Data
@Entity
@Table(name = "meta_warehouse_table")
public class WarehouseTable extends TenantEntity {

    /** 数据库名称 */
    @Column(length = 128, nullable = false)
    private String databaseName;

    /** 表名称 */
    @Column(length = 256, nullable = false)
    private String tableName;

    /** 存储类型：ICEBERG/HUDI/PAIMON/HIVE */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private WarehouseStorageType storageType;

    /** 存储位置（如 S3 路径、HDFS 路径） */
    @Column(columnDefinition = "text")
    private String storageLocation;

    /** 分区规范（JSON 格式，存储分区字段和分区值） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private PartitionSpec partitionSpec;

    /** 表描述 */
    private String description;

    /** 行数统计 */
    private Long rowCount;

    /** 表大小（字节） */
    private Long sizeBytes;

    /** 统计信息最后更新时间 */
    private LocalDateTime statsUpdatedAt;

    /** 仓库表字段列表 */
    @OneToMany(mappedBy = "warehouseTable", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordinalPosition ASC")
    private List<WarehouseColumn> columns;

}
