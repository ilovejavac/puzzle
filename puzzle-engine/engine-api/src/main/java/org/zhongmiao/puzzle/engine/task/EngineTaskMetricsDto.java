package org.zhongmiao.puzzle.engine.task;

import lombok.Data;

/**
 * 引擎任务指标 DTO
 */
@Data
public class EngineTaskMetricsDto {

    private Long taskId;
    private Long recordsProcessed;
    private Long bytesProcessed;
    private Double throughput;
    private Long duration;

}
