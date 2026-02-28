package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.engine.EngineService;
import org.zhongmiao.puzzle.engine.engine.EngineCmd;
import org.zhongmiao.puzzle.engine.EngineTaskService;
import org.zhongmiao.puzzle.engine.task.EngineTaskDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskQuery;

import java.util.List;

/**
 * 模型任务接口
 * <p>
 * 管理模型级别的任务操作：获取模型任务、重启、停止
 */
@RestController
@RequiredArgsConstructor
public class EngineModelController {

    private final EngineService engineService;
    private final EngineTaskService engineTaskService;

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
}
