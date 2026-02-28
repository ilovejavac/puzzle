package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import org.zhongmiao.puzzle.engine.enums.EngineTaskStatus;
import org.zhongmiao.puzzle.engine.enums.EngineType;
import org.zhongmiao.puzzle.engine.enums.TaskType;

import java.time.LocalDateTime;

/**
 * 引擎任务实体
 * 用于管理数据同步和计算任务的执行，支持多种执行引擎
 */
@Data
@Table(name = "engine_task")
@Entity
public class EngineTask extends TenantEntity {

    /** 所属执行计划ID */
    @Column(nullable = false)
    private Long executionPlanId;

    /** 关联的模型ID */
    @Column(nullable = false)
    private Long modelId;

    /** 任务类型：数据同步/计算/归档 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private TaskType taskType;

    /** 引擎类型：SeaTunnel/Flink/DolphinScheduler */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private EngineType engineType;

    /** 引擎作业ID（SeaTunnel/Flink job ID） */
    @Column(length = 256)
    private String engineJobId;

    /** DolphinScheduler 流程ID */
    private Long dsProcessId;

    /** DolphinScheduler 任务ID */
    private Long dsTaskId;

    /** 引擎配置（生成的 HOCON/Flink SQL） */
    @Column(columnDefinition = "text")
    private String engineConfig;

    /** 任务状态 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private EngineTaskStatus status;

    /** 任务开始时间 */
    private LocalDateTime startedAt;

    /** 任务结束时间 */
    private LocalDateTime finishedAt;

    /** 错误信息 */
    @Column(columnDefinition = "text")
    private String errorMsg;

}