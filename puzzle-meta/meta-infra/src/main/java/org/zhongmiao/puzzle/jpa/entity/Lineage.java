package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import org.zhongmiao.puzzle.meta.enums.LineageEntityType;
import org.zhongmiao.puzzle.meta.enums.LineageTransformType;

/**
 * 血缘关系实体
 * 用于追踪数据从源头到目标的转换过程，记录数据流转路径和转换规则
 */
@Data
@Entity
@Table(name = "meta_lineage")
public class Lineage extends JpaEntity {

    /** 源实体类型：SOURCE_TABLE/MODEL/METRIC */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private LineageEntityType sourceType;

    /** 源实体ID */
    @Column(nullable = false)
    private Long   sourceId;

    /** 源字段名称（可选，用于字段级血缘） */
    @Column(length = 256)
    private String sourceColumn;

    /** 目标实体类型：SOURCE_TABLE/MODEL/METRIC */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private LineageEntityType targetType;

    /** 目标实体ID */
    @Column(nullable = false)
    private Long   targetId;

    /** 目标字段名称（可选，用于字段级血缘） */
    @Column(length = 256)
    private String targetColumn;

    /** 转换类型：DIRECT/AGGREGATE/JOIN/FILTER/CALCULATE */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private LineageTransformType transformType;

    /** 所属模型ID */
    private Long   modelId;

}
