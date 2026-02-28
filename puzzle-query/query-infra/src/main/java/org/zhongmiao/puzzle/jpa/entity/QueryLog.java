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
import org.zhongmiao.puzzle.query.enums.QueryStatus;
import org.zhongmiao.puzzle.query.enums.TargetEngine;
import org.zhongmiao.puzzle.query.model.FilterCondition;
import org.zhongmiao.puzzle.query.model.TimeRange;

import java.io.Serializable;
import java.util.List;

/**
 * 查询日志实体
 * 用于记录所有查询的执行日志，包括查询参数、生成的SQL和执行结果
 */
@Data
@Entity
@Table(name = "query_log")
public class QueryLog extends TenantEntity implements Serializable {

    /** 用户ID */
    private Long userId;

    /** 关联的模型ID */
    private Long modelId;

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

    /** 生成的SQL语句 */
    @Column(columnDefinition = "text", nullable = false)
    private String generatedSql;

    /** 目标执行引擎 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private TargetEngine targetEngine;

    /** 查询状态：SUCCESS/FAILED/TIMEOUT */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private QueryStatus status;

    /** 返回的行数 */
    private Long rowsReturned;

    /** 执行耗时（毫秒） */
    private Long elapsedMs;

    /** 错误信息 */
    @Column(columnDefinition = "text")
    private String errorMsg;

}