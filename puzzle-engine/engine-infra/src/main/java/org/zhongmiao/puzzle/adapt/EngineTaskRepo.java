package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.zhongmiao.puzzle.jpa.entity.EngineTask;
import org.zhongmiao.puzzle.jpa.repository.EngineTaskDao;
import org.zhongmiao.puzzle.engine.task.*;

import java.util.List;

/**
 * Engine Task Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EngineTaskRepo implements EngineTaskService {

    private final EngineTaskDao dao;

    @Override
    public Page<EngineTaskDto.TaskList> listTasks(QueryRequest<EngineTaskQuery.QueryTask> qry) {
        EngineTaskDao.QueryEngineTask query = new EngineTaskDao.QueryEngineTask();
        query.external(qry);
        return dao.page(query).map(this::mapToListDto);
    }

    @Override
    public EngineTaskDto.TaskDetail getTask(EngineTaskQuery.GetTask qry) {
        EngineTask entity = dao.findById(qry.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + qry.getTaskId()));
        return mapToDetailDto(entity);
    }

    @Override
    public List<String> getTaskLogs(EngineTaskQuery.GetTaskLogs qry) {
        // TODO: Implement task log retrieval
        return List.of();
    }

    @Override
    public List<EngineTaskDto.TaskDetail> listModelTasks(EngineTaskQuery.ListModelTasks qry) {
        EngineTaskDao.QueryEngineTask query = new EngineTaskDao.QueryEngineTask();
        query.setModelId(qry.getModelId());
        return dao.loads(query).stream()
                .map(this::mapToDetailDto)
                .toList();
    }

    @Override
    public EngineTaskMetricsDto getTaskMetrics(EngineTaskQuery.GetTaskMetrics qry) {
        EngineTask entity = dao.findById(qry.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + qry.getTaskId()));
        EngineTaskMetricsDto dto = new EngineTaskMetricsDto();
        dto.setTaskId(entity.getId());
        // TODO: Map actual metrics from entity
        return dto;
    }

    private EngineTaskDto.TaskList mapToListDto(EngineTask entity) {
        EngineTaskDto.TaskList dto = new EngineTaskDto.TaskList();
        dto.setId(entity.getId());
        dto.setModelId(entity.getModelId());
        dto.setTaskType(entity.getTaskType());
        dto.setEngineType(entity.getEngineType());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    private EngineTaskDto.TaskDetail mapToDetailDto(EngineTask entity) {
        EngineTaskDto.TaskDetail dto = new EngineTaskDto.TaskDetail();
        dto.setId(entity.getId());
        dto.setModelId(entity.getModelId());
        dto.setTaskType(entity.getTaskType());
        dto.setEngineType(entity.getEngineType());
        dto.setStatus(entity.getStatus());
        dto.setEngineJobId(entity.getEngineJobId());
        dto.setEngineConfig(entity.getEngineConfig());
        dto.setStartedAt(entity.getStartedAt());
        dto.setFinishedAt(entity.getFinishedAt());
        dto.setErrorMsg(entity.getErrorMsg());
        return dto;
    }

}
