package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.enums.OutputStorageType;
import org.zhongmiao.puzzle.model.model.TableSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

/**
 * 模型输出表实体
 * 定义模型计算结果的输出存储配置，支持多种存储类型
 */
@Data
@Entity
@Table(name = "model_output_table")
public class OutputTable extends TenantEntity {

    /** 所属模型ID */
    @Column(nullable = false)
    private Long modelId;

    /** 存储类型：ICEBERG/HUDI/PAIMON/HIVE/KAFKA */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private OutputStorageType storageType;

    /** 存储位置（如 S3 路径、Kafka Topic） */
    @Column(length = 512, nullable = false)
    private String storageLocation;

    /** 表结构定义（JSON 格式，存储字段信息） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text", nullable = false)
    private TableSchema tableSchema;

    /** 是否为主输出表 */
    private Boolean isPrimary;

    /** 数据保留天数（TTL） */
    private Integer ttlDays;

}
