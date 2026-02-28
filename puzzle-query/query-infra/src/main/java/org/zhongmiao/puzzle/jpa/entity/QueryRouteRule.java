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
import org.zhongmiao.puzzle.query.enums.TargetEngine;
import org.zhongmiao.puzzle.query.model.RouteCondition;

import java.io.Serializable;

/**
 * 查询路由规则实体
 * 定义查询路由规则，根据条件将查询请求路由到不同的执行引擎
 */
@Data
@Entity
@Table(name = "query_route_rule")
public class QueryRouteRule extends TenantEntity implements Serializable {

    /** 规则名称 */
    @Column(length = 128, nullable = false)
    private String ruleName;

    /** 规则优先级（数字越小优先级越高） */
    @Column(nullable = false)
    private Integer priority;

    /** 路由条件（JSON 格式，定义匹配规则） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private RouteCondition condition;

    /** 目标执行引擎：PRESTO/SPARK/CLICKHOUSE */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private TargetEngine targetEngine;

    /** 是否启用 */
    private Boolean enabled;

}