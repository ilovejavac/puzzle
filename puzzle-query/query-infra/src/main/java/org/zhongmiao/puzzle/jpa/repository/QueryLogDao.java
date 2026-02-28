package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.QueryLog;
import org.zhongmiao.puzzle.query.enums.QueryStatus;
import org.zhongmiao.puzzle.query.enums.TargetEngine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QueryLogDao extends BaseRepository<QueryLog> {

    @Data
    class QueryQueryLog extends DslQuery<QueryLog> {

        private Long userId;
        private Collection<Long> userIdIn;
        private Long modelId;
        private Collection<Long> modelIdIn;
        private TargetEngine targetEngine;
        private Collection<TargetEngine> targetEngineIn;
        private QueryStatus status;
        private Collection<QueryStatus> statusIn;
        private Collection<Long> idIn;

    }

    default Optional<QueryLog> findByBizId(String bizId) {
        return load(new QueryQueryLog().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryQueryLog().setBizId(bizId));
    }

    default List<QueryLog> findByUserId(Long userId) {
        return loads(new QueryQueryLog().setUserId(userId));
    }

    default List<QueryLog> findByModelId(Long modelId) {
        return loads(new QueryQueryLog().setModelId(modelId));
    }

    default List<QueryLog> findByStatus(QueryStatus status) {
        return loads(new QueryQueryLog().setStatus(status));
    }

    default List<QueryLog> findByTargetEngine(TargetEngine targetEngine) {
        return loads(new QueryQueryLog().setTargetEngine(targetEngine));
    }

    default List<QueryLog> findByUserIdAndModelId(Long userId, Long modelId) {
        return loads(new QueryQueryLog()
                .setUserId(userId)
                .setModelId(modelId));
    }

    default List<QueryLog> findByModelIdAndStatus(Long modelId, QueryStatus status) {
        return loads(new QueryQueryLog()
                .setModelId(modelId)
                .setStatus(status));
    }

}
