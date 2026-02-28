package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.enums.AssignedEngine;
import org.zhongmiao.puzzle.enums.AssignedStorage;
import org.zhongmiao.puzzle.enums.ModelStatus;
import org.zhongmiao.puzzle.enums.ModelType;
import org.zhongmiao.puzzle.model.model.StorageConfig;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据模型实体
 * 定义数据模型的核心配置，包括模型类型、主表、关联关系、字段和存储引擎
 */
@Data
@Entity
@Table(name = "model_tbl")
public class Model extends TenantEntity {

    /** 模型名称 */
    @Column(length = 128, nullable = false)
    private String modelName;

    /** 模型类型：FACT/DIMENSION/AGGREGATE/MART */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private ModelType modelType;

    /** 模型描述 */
    private String description;

    /** 主源表ID */
    @Column(nullable = false)
    private Long primarySourceTableId;

    /** 分配的计算引擎：SPARK/FLINK/PRESTO */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private AssignedEngine assignedEngine;

    /** 分配的存储类型：ICEBERG/HUDI/PAIMON */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private AssignedStorage assignedStorage;

    /** 分配的存储表名称 */
    @Column(length = 256)
    private String assignedStorageTable;

    /** 分配的存储配置（JSON 格式，存储分区、生命周期等） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private StorageConfig assignedStorageConfig;

    /** 模型状态：DRAFT/PENDING/ACTIVE/FAILED */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private ModelStatus status;

    /** 部署时间 */
    private LocalDateTime deployedAt;

    /** 模型版本号 */
    @Column(nullable = false)
    private Integer version;

    /** 模型关联列表 */
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordinal ASC")
    private List<ModelJoin> joins;

    /** 模型字段列表 */
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordinalPosition ASC")
    private List<ModelField> fields;

}
