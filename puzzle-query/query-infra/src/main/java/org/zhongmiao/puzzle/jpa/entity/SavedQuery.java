package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.query.enums.QueryType;
import org.zhongmiao.puzzle.query.enums.TargetEngine;
import org.zhongmiao.puzzle.query.model.FilterCondition;
import org.zhongmiao.puzzle.query.model.TimeRange;

import java.io.Serializable;
import java.util.List;

/**
 * 保存的查询实体
 * 用于保存用户常用的查询配置，支持快速执行和分享
 */
@Data
@Entity
@Table(name = "query_saved_query")
public class SavedQuery extends TenantEntity implements Serializable {

    /** 查询名称 */
    @Column(length = 256, nullable = false)
    private String queryName;

    /** 查询类型：METRIC_QUERY/ADHOC_QUERY/CROSSTAB */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private QueryType queryType;

    /** 指标ID列表（JSON 数组格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<Long> metricIds;

    /** 维度ID列表（JSON 数组格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<Long> dimensionIds;

    /** 过滤条件（JSON 格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<FilterCondition> filters;

    /** 时间范围（JSON 格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private TimeRange timeRange;

    /** 关联的模型ID */
    private Long modelId;

    /** 目标执行引擎 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private TargetEngine targetEngine;

    /** 缓存有效期（秒） */
    private Integer cacheTtlSeconds;

    /** 创建人ID */
    private Long createdBy;

}