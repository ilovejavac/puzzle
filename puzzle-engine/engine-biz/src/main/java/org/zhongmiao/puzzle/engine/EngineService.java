package org.zhongmiao.puzzle.engine;

import org.zhongmiao.puzzle.engine.engine.EngineCmd;
import org.zhongmiao.puzzle.engine.engine.EngineQuery;
import org.zhongmiao.puzzle.engine.engine.ExecutionPlanDto;

import java.util.Map;

/**
 * 引擎服务接口
 */
public interface EngineService {

    /**
     * 重启模型
     */
    void restartModel(EngineCmd.RestartModel cmd);

    /**
     * 停止模型
     */
    void stopModel(EngineCmd.StopModel cmd);

    /**
     * 获取执行计划详情
     */
    ExecutionPlanDto.PlanDetail getExecutionPlan(EngineQuery.GetExecutionPlan qry);

    /**
     * 重新生成执行计划
     */
    void regeneratePlan(EngineCmd.RegeneratePlan cmd);

    /**
     * 获取引擎总览统计
     */
    Map<String, Object> getEngineOverview();

}
