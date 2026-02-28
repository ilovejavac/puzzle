package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.engine.EngineTaskService;
import org.zhongmiao.puzzle.engine.task.EngineTaskDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskQuery;

import java.util.List;

/**
 * 任务接口
 * <p>
 * 管理任务的查询、详情、日志查看
 */
@RestController
@RequiredArgsConstructor
public class EngineTaskController {

    private final EngineTaskService engineTaskService;

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
}
