package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.ExecutionPlan;
import org.zhongmiao.puzzle.engine.enums.ExecutionPlanStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExecutionPlanDao extends BaseRepository<ExecutionPlan> {

    @Data
    class QueryExecutionPlan extends DslQuery<ExecutionPlan> {

        private Long modelId;
        private Collection<Long> modelIdIn;
        private Integer modelVersion;
        private Collection<Integer> modelVersionIn;
        private ExecutionPlanStatus status;
        private Collection<ExecutionPlanStatus> statusIn;
        private Collection<Long> idIn;

    }

    default Optional<ExecutionPlan> findByBizId(String bizId) {
        return load(new QueryExecutionPlan().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryExecutionPlan().setBizId(bizId));
    }

    default List<ExecutionPlan> findByModelId(Long modelId) {
        return loads(new QueryExecutionPlan().setModelId(modelId));
    }

    default Optional<ExecutionPlan> findByModelIdAndModelVersion(Long modelId, Integer modelVersion) {
        List<ExecutionPlan> results = loads(new QueryExecutionPlan()
                .setModelId(modelId)
                .setModelVersion(modelVersion));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    default List<ExecutionPlan> findByStatus(ExecutionPlanStatus status) {
        return loads(new QueryExecutionPlan().setStatus(status));
    }

}
