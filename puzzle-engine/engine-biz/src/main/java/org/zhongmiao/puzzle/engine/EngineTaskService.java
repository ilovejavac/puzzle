package org.zhongmiao.puzzle.engine;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;
import org.zhongmiao.puzzle.engine.task.EngineTaskDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskMetricsDto;
import org.zhongmiao.puzzle.engine.task.EngineTaskQuery;

import java.util.List;

/**
 * 引擎任务服务接口
 */
public interface EngineTaskService {

    /**
     * 查询任务列表（分页）
     */
    Page<EngineTaskDto.TaskList> listTasks(QueryRequest<EngineTaskQuery.QueryTask> qry);

    /**
     * 获取任务详情
     */
    EngineTaskDto.TaskDetail getTask(EngineTaskQuery.GetTask qry);

    /**
     * 获取任务的执行日志
     */
    List<String> getTaskLogs(EngineTaskQuery.GetTaskLogs qry);

    /**
     * 获取模型的所有任务
     */
    List<EngineTaskDto.TaskDetail> listModelTasks(EngineTaskQuery.ListModelTasks qry);

    /**
     * 获取任务指标统计
     */
    EngineTaskMetricsDto getTaskMetrics(EngineTaskQuery.GetTaskMetrics qry);

}
