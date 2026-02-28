package org.zhongmiao.puzzle.engine.engine;

import lombok.Data;
import org.zhongmiao.puzzle.engine.model.ComputePlan;
import org.zhongmiao.puzzle.engine.model.SyncPlan;

/**
 * 执行计划 DTO
 */
@Data
public class ExecutionPlanDto {

    private ExecutionPlanDto() {

    }

    @Data
    public static class PlanDetail {
        private Long id;
        private Long modelId;
        private Integer modelVersion;
        private SyncPlan syncPlan;
        private ComputePlan computePlan;
        private org.zhongmiao.puzzle.engine.enums.ExecutionPlanStatus status;
    }

}
