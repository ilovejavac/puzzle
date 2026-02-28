package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.engine.EngineService;
import org.zhongmiao.puzzle.engine.EngineTaskService;
import org.zhongmiao.puzzle.engine.task.EngineTaskMetricsDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskQuery;

import java.util.Map;

/**
 * 引擎监控接口
 * <p>
 * 提供任务指标统计与引擎总览数据
 */
@RestController
@RequiredArgsConstructor
public class EngineMonitorController {

    private final EngineService engineService;
    private final EngineTaskService engineTaskService;

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
