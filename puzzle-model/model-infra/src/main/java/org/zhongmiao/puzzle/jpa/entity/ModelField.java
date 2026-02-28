package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.enums.AggFunction;
import org.zhongmiao.puzzle.enums.FieldRole;
import org.zhongmiao.puzzle.enums.FilterOperator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

/**
 * 模型字段实体
 * 定义模型中的字段配置，包括字段角色、聚合函数、过滤条件等
 */
@Data
@Entity
@Table(name = "model_field")
public class ModelField extends JpaEntity {

    /** 所属模型 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    /** 源表ID */
    @Column(nullable = false)
    private Long sourceTableId;

    /** 源表字段名称 */
    @Column(length = 256, nullable = false)
    private String sourceColumnName;

    /** 字段别名（在查询结果中的显示名称） */
    @Column(length = 256)
    private String fieldAlias;

    /** 字段角色：DIMENSION/MEASURE/ATTRIBUTE/TIME */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private FieldRole fieldRole;

    /** 聚合函数：SUM/COUNT/AVG/MAX/MIN/COUNT_DISTINCT */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private AggFunction aggFunction;

    /** 过滤操作符：=、!=、>、<、IN、LIKE、BETWEEN */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private FilterOperator filterOperator;

    /** 过滤值列表（JSON 数组格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<String> filterValues;

    /** 是否为计算字段 */
    private Boolean isComputed;

    /** 计算表达式（用于计算字段） */
    @Column(columnDefinition = "text")
    private String computeExpr;

    /** 字段在模型中的序号位置 */
    @Column(nullable = false)
    private Integer ordinalPosition;

}
