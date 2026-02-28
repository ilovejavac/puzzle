package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.engine.engine.EngineCmd;
import org.zhongmiao.puzzle.engine.engine.EngineDto;
import org.zhongmiao.puzzle.engine.engine.EngineQuery;
import org.zhongmiao.puzzle.engine.engine.EngineService;
import org.zhongmiao.puzzle.engine.engine.ExecutionPlanDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskMetricsDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskQuery;
import org.zhongmiao.puzzle.engine.task.EngineTaskService;

import java.util.List;
import java.util.Map;

/**
 * 引擎接口
 * <p>
 * 管理执行引擎、任务监控、运维操作
 */
@RestController
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;
    private final EngineTaskService engineTaskService;

    // ==================== 任务管理 ====================

    /**
     * 查询任务列表（分页）
     * 需要权限[engine:list]
     */
    @RequirePermission("engine:list")
    @PostMapping("/api/engine/list-tasks")
    public ServerResponse<List<EngineTaskDto.TaskList>> listTasks(
            @RequestBody QueryRequest<EngineTaskQuery.QueryTask> qry) {
        return ServerResponse.success(engineTaskService.listTasks(qry));
    }

    /**
     * 获取任务详情
     * 需要权限[engine:view]
     */
    @RequirePermission("engine:view")
    @PostMapping("/api/engine/get-task")
    public ServerResponse<EngineTaskDto.TaskDetail> getTask(@RequestBody EngineTaskQuery.GetTask qry) {
        return ServerResponse.success(engineTaskService.getTask(qry));
    }

    /**
     * 获取任务的执行日志
     * 需要权限[engine:view]
     */
    @RequirePermission("engine:view")
    @PostMapping("/api/engine/get-task-logs")
    public ServerResponse<List<String>> getTaskLogs(@RequestBody EngineTaskQuery.GetTaskLogs qry) {
        return ServerResponse.success(engineTaskService.getTaskLogs(qry));
    }

    // ==================== 模型任务管理 ====================

    /**
     * 获取模型的所有任务
     * 需要权限[engine:view]
     */
    @RequirePermission("engine:view")
    @PostMapping("/api/engine/list-model-tasks")
    public ServerResponse<List<EngineTaskDto.TaskDetail>> listModelTasks(
            @RequestBody EngineTaskQuery.ListModelTasks qry) {
        return ServerResponse.success(engineTaskService.listModelTasks(qry));
    }

    /**
     * 重启模型任务
     * 需要权限[engine:operate]
     */
    @RequirePermission("engine:operate")
    @PostMapping("/api/engine/restart-model")
    public ServerResponse<Void> restartModel(@RequestBody EngineCmd.RestartModel cmd) {
        engineService.restartModel(cmd);
        return ServerResponse.ok();
    }

    /**
     * 停止模型任务
     * 需要权限[engine:operate]
     */
    @RequirePermission("engine:operate")
    @PostMapping("/api/engine/stop-model")
    public ServerResponse<Void> stopModel(@RequestBody EngineCmd.StopModel cmd) {
        engineService.stopModel(cmd);
        return ServerResponse.ok();
    }

    // ==================== 执行计划 ====================

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

    // ==================== 监控统计 ====================

    /**
     * 获取任务指标统计
     * 需要权限[engine:view]
     */
    @RequirePermission("engine:view")
    @PostMapping("/api/engine/get-task-metrics")
    public ServerResponse<EngineTaskMetricsDto> getTaskMetrics(@RequestBody EngineTaskQuery.GetTaskMetrics qry) {
        return ServerResponse.success(engineTaskService.getTaskMetrics(qry));
    }

    /**
     * 获取引擎总览统计
     * 需要权限[engine:view]
     */
    @RequirePermission("engine:view")
    @PostMapping("/api/engine/get-engine-overview")
    public ServerResponse<Map<String, Object>> getEngineOverview() {
        return ServerResponse.success(engineService.getEngineOverview());
    }

}

