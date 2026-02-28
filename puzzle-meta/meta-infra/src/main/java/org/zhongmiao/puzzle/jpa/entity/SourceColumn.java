package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 源表字段实体
 * 用于管理外部数据源的表字段元数据，记录字段类型、位置和约束信息
 */
@Data
@Entity
@Table(name = "meta_source_column")
public class SourceColumn extends TenantEntity {

    /** 所属源表 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private SourceTable sourceTable;

    /** 字段名称 */
    @Column(length = 256, nullable = false)
    private String columnName;

    /** 字段数据类型 */
    @Column(length = 128, nullable = false)
    private String columnType;

    /** 字段在表中的序号位置 */
    @Column(nullable = false)
    private Integer ordinalPosition;

    /** 是否允许为空 */
    private Boolean isNullable;

    /** 是否为主键 */
    private Boolean isPrimaryKey;

    /** 字段描述 */
    private String description;

}