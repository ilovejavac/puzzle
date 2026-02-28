package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.ModelJoin;
import org.zhongmiao.puzzle.enums.JoinType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ModelJoinDao extends BaseRepository<ModelJoin> {

    @Data
    class QueryModelJoin extends DslQuery<ModelJoin> {

        private Long modelId;
        private Collection<Long> modelIdIn;
        private Long sourceTableId;
        private Collection<Long> sourceTableIdIn;
        private JoinType joinType;
        private Collection<JoinType> joinTypeIn;
        private Collection<Long> idIn;

    }

    default Optional<ModelJoin> findByBizId(String bizId) {
        return load(new QueryModelJoin().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryModelJoin().setBizId(bizId));
    }

    default List<ModelJoin> findByModelId(Long modelId) {
        return loads(new QueryModelJoin().setModelId(modelId));
    }

    default List<ModelJoin> findByModelIdAndSourceTableId(Long modelId, Long sourceTableId) {
        return loads(new QueryModelJoin()
                .setModelId(modelId)
                .setSourceTableId(sourceTableId));
    }

    default List<ModelJoin> findByModelIdAndJoinType(Long modelId, JoinType joinType) {
        return loads(new QueryModelJoin()
                .setModelId(modelId)
                .setJoinType(joinType));
    }

}
