package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.ModelJoinCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ModelJoinConditionDao extends BaseRepository<ModelJoinCondition> {

    @Data
    class QueryModelJoinCondition extends DslQuery<ModelJoinCondition> {

        private Long modelJoinId;
        private Collection<Long> modelJoinIdIn;
        private Collection<Long> leftTableIdIn;
        private Collection<Long> rightTableIdIn;
        private String operator;
        private Collection<Long> idIn;

    }

    default Optional<ModelJoinCondition> findByBizId(String bizId) {
        return load(new QueryModelJoinCondition().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryModelJoinCondition().setBizId(bizId));
    }

    default List<ModelJoinCondition> findByModelJoinId(Long modelJoinId) {
        return loads(new QueryModelJoinCondition().setModelJoinId(modelJoinId));
    }

}
