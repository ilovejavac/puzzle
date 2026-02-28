package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.enums.MetricStatus;
import org.zhongmiao.puzzle.enums.MetricType;
import org.zhongmiao.puzzle.query.model.FilterCondition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.List;

/**
 * 指标实体
 * 定义业务指标的度量方式、计算规则和统计口径
 */
@Data
@Entity
@Table(name = "model_metric")
public class Metric extends TenantEntity {

    /** 所属模型ID */
    @Column(nullable = false)
    private Long modelId;

    /** 指标编码 */
    @Column(length = 128, nullable = false)
    private String metricCode;

    /** 指标名称 */
    @Column(length = 256, nullable = false)
    private String metricName;

    /** 指标类型：原子指标/衍生指标/复合指标 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private MetricType metricType;

    /** 关联的字段ID（原子指标使用） */
    private Long fieldId;

    /** 指标表达式（衍生指标和复合指标使用） */
    @Column(columnDefinition = "text")
    private String expression;

    /** 依赖的其他指标ID列表（JSON 数组格式，复合指标使用） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<Long> dependentMetricIds;

    /** 额外的过滤条件（JSON 格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<FilterCondition> extraFilters;

    /** 指标单位 */
    @Column(length = 32)
    private String unit;

    /** 精度小数位数 */
    private Integer precisionScale;

    /** 指标描述 */
    private String description;

    /** 指标状态：ACTIVE/DRAFT/DEPRECATED */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private MetricStatus status;

    /** 创建人ID */
    private Long createdBy;

}
