package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.EngineTaskMetrics;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EngineTaskMetricsDao extends BaseRepository<EngineTaskMetrics> {

    @Data
    class QueryEngineTaskMetrics extends DslQuery<EngineTaskMetrics> {

        private Collection<Long> idIn;

    }

    default Optional<EngineTaskMetrics> findByBizId(String bizId) {
        return load(new QueryEngineTaskMetrics().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryEngineTaskMetrics().setBizId(bizId));
    }

}
