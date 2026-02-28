package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.jpa.entity.ExecutionPlan;
import org.zhongmiao.puzzle.jpa.repository.ExecutionPlanDao;
import org.zhongmiao.puzzle.engine.engine.*;

import java.util.List;
import java.util.Map;

/**
 * Engine Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EngineRepo implements EngineService {

    private final ExecutionPlanDao executionPlanDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restartModel(EngineCmd.RestartModel cmd) {
        // TODO: Implement model restart logic
        log.info("Restarting model: {}", cmd.getModelId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopModel(EngineCmd.StopModel cmd) {
        // TODO: Implement model stop logic
        log.info("Stopping model: {}", cmd.getModelId());
    }

    @Override
    public ExecutionPlanDto.PlanDetail getExecutionPlan(EngineQuery.GetExecutionPlan qry) {
        List<ExecutionPlan> plans = executionPlanDao.findByModelId(qry.getModelId());
        if (plans.isEmpty()) {
            return new ExecutionPlanDto.PlanDetail();
        }
        ExecutionPlan entity = plans.get(0);
        ExecutionPlanDto.PlanDetail dto = new ExecutionPlanDto.PlanDetail();
        dto.setId(entity.getId());
        dto.setModelId(entity.getModelId());
        dto.setSyncPlan(entity.getSyncPlan());
        dto.setComputePlan(entity.getComputePlan());
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void regeneratePlan(EngineCmd.RegeneratePlan cmd) {
        // TODO: Implement plan regeneration
        log.info("Regenerating plan for model: {}", cmd.getModelId());
    }

    @Override
    public Map<String, Object> getEngineOverview() {
        // TODO: Implement engine overview statistics
        return Map.of();
    }

}
