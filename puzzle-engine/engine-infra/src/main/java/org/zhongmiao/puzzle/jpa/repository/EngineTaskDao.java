package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.EngineTask;
import org.zhongmiao.puzzle.engine.enums.EngineTaskStatus;
import org.zhongmiao.puzzle.engine.enums.EngineType;
import org.zhongmiao.puzzle.engine.enums.TaskType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EngineTaskDao extends BaseRepository<EngineTask> {

    @Data
    class QueryEngineTask extends DslQuery<EngineTask> {

        private Long executionPlanId;
        private Collection<Long> executionPlanIdIn;
        private Long modelId;
        private Collection<Long> modelIdIn;
        private TaskType taskType;
        private Collection<TaskType> taskTypeIn;
        private EngineType engineType;
        private Collection<EngineType> engineTypeIn;
        private EngineTaskStatus status;
        private Collection<EngineTaskStatus> statusIn;
        private String engineJobId;
        private Collection<Long> idIn;

    }

    default Optional<EngineTask> findByBizId(String bizId) {
        return load(new QueryEngineTask().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryEngineTask().setBizId(bizId));
    }

    default Optional<EngineTask> findByEngineJobId(String engineJobId) {
        return load(new QueryEngineTask().setEngineJobId(engineJobId));
    }

    default List<EngineTask> findByExecutionPlanId(Long executionPlanId) {
        return loads(new QueryEngineTask().setExecutionPlanId(executionPlanId));
    }

    default List<EngineTask> findByModelId(Long modelId) {
        return loads(new QueryEngineTask().setModelId(modelId));
    }

    default List<EngineTask> findByStatus(EngineTaskStatus status) {
        return loads(new QueryEngineTask().setStatus(status));
    }

    default List<EngineTask> findByExecutionPlanIdAndStatus(Long executionPlanId, EngineTaskStatus status) {
        return loads(new QueryEngineTask()
                .setExecutionPlanId(executionPlanId)
                .setStatus(status));
    }

    default List<EngineTask> findByModelIdAndTaskType(Long modelId, TaskType taskType) {
        return loads(new QueryEngineTask()
                .setModelId(modelId)
                .setTaskType(taskType));
    }

    default List<EngineTask> findByEngineTypeAndStatus(EngineType engineType, EngineTaskStatus status) {
        return loads(new QueryEngineTask()
                .setEngineType(engineType)
                .setStatus(status));
    }

}
