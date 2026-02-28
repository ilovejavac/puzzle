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
import org.zhongmiao.puzzle.engine.enums.ExecutionPlanStatus;
import org.zhongmiao.puzzle.engine.model.ComputePlan;
import org.zhongmiao.puzzle.engine.model.SyncPlan;

import java.time.LocalDateTime;

/**
 * 执行计划实体
 * 用于管理模型的执行计划，包括数据同步计划和计算计划
 */
@Data
@Table(name = "engine_execution_plan")
@Entity
public class ExecutionPlan extends TenantEntity {

    /** 关联的模型ID */
    @Column(nullable = false)
    private Long modelId;

    /** 模型版本号 */
    @Column(nullable = false)
    private Integer modelVersion;

    /** 数据同步计划（JSON 格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private SyncPlan syncPlan;

    /** 计算计划（JSON 格式） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private ComputePlan computePlan;

    /** 执行计划状态 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private ExecutionPlanStatus status;

    /** 计划生成时间 */
    private LocalDateTime generatedAt;

    /** 计划激活时间 */
    private LocalDateTime activatedAt;

}