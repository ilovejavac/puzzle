package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.engine.EngineService;
import org.zhongmiao.puzzle.engine.engine.EngineCmd;
import org.zhongmiao.puzzle.engine.engine.EngineQuery;
import org.zhongmiao.puzzle.engine.engine.ExecutionPlanDto;

/**
 * 执行计划接口
 * <p>
 * 管理执行计划的查询与重新生成
 */
@RestController
@RequiredArgsConstructor
public class ExecutionPlanController {

    private final EngineService engineService;

    /**
     * 获取执行计划详情
     * 需要权限[engine:view]
     */
    @RequirePermission("engine:view")
    @PostMapping("/api/engine/get-execution-plan")
    public ServerResponse<ExecutionPlanDto.PlanDetail> getExecutionPlan(
            @RequestBody EngineQuery.GetExecutionPlan qry) {
        return ServerResponse.success(engineService.getExecutionPlan(qry));
    }

    /**
     * 重新生成执行计划
     * 需要权限[engine:operate]
     */
    @RequirePermission("engine:operate")
    @PostMapping("/api/engine/regenerate-plan")
    public ServerResponse<Void> regeneratePlan(@RequestBody EngineCmd.RegeneratePlan cmd) {
        engineService.regeneratePlan(cmd);
        return ServerResponse.ok();
    }
}
