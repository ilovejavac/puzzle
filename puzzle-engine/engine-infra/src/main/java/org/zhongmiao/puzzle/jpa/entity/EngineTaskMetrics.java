package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 引擎任务指标实体
 * 用于记录引擎任务执行过程中的性能指标和统计信息
 */
@Data
@Table(name = "engine_task_metrics")
public class EngineTaskMetrics extends JpaEntity {

    /** 输入记录数 */
    private Long recordsIn;

    /** 输出记录数 */
    private Long recordsOut;

    /** 输入字节数 */
    private Long bytesIn;

    /** 输出字节数 */
    private Long bytesOut;

    /** Kafka 消费延迟（毫秒） */
    private Long lag;

    /** 检查点计数 */
    private Integer checkpointCount;

    /** 最后检查点时间 */
    private LocalDateTime lastCheckpointAt;

    /** 指标采集时间 */
    @Column(nullable = false)
    private LocalDateTime collectedAt;

}