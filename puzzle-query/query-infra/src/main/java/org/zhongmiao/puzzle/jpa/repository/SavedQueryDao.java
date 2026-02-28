package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.SavedQuery;
import org.zhongmiao.puzzle.query.enums.QueryType;
import org.zhongmiao.puzzle.query.enums.TargetEngine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SavedQueryDao extends BaseRepository<SavedQuery> {

    @Data
    class QuerySavedQuery extends DslQuery<SavedQuery> {

        private String queryName;
        private String queryNameLike;
        private QueryType queryType;
        private Collection<QueryType> queryTypeIn;
        private Long modelId;
        private Collection<Long> modelIdIn;
        private TargetEngine targetEngine;
        private Collection<TargetEngine> targetEngineIn;
        private Collection<Long> idIn;

    }

    default Optional<SavedQuery> findByBizId(String bizId) {
        return load(new QuerySavedQuery().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QuerySavedQuery().setBizId(bizId));
    }

    default Optional<SavedQuery> findByQueryName(String queryName) {
        return load(new QuerySavedQuery().setQueryName(queryName));
    }

    default List<SavedQuery> findByModelId(Long modelId) {
        return loads(new QuerySavedQuery().setModelId(modelId));
    }

    default List<SavedQuery> findByQueryType(QueryType queryType) {
        return loads(new QuerySavedQuery().setQueryType(queryType));
    }

    default List<SavedQuery> findByTargetEngine(TargetEngine targetEngine) {
        return loads(new QuerySavedQuery().setTargetEngine(targetEngine));
    }

    default List<SavedQuery> findByModelIdAndQueryType(Long modelId, QueryType queryType) {
        return loads(new QuerySavedQuery()
                .setModelId(modelId)
                .setQueryType(queryType));
    }

}
