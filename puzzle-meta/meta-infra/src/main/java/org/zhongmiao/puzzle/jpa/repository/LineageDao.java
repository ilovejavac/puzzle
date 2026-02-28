package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.Lineage;
import org.zhongmiao.puzzle.meta.enums.LineageEntityType;
import org.zhongmiao.puzzle.meta.enums.LineageTransformType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LineageDao extends BaseRepository<Lineage> {

    @Data
    class QueryLineage extends DslQuery<Lineage> {

        private LineageEntityType sourceType;
        private Long sourceId;
        private Collection<Long> sourceIdIn;
        private LineageEntityType targetType;
        private Long targetId;
        private Collection<Long> targetIdIn;
        private Long modelId;
        private Collection<Long> modelIdIn;
        private LineageTransformType transformType;

    }

    default Optional<Lineage> findByBizId(String bizId) {
        return load(new QueryLineage().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryLineage().setBizId(bizId));
    }

    default List<Lineage> findBySourceTypeAndSourceId(LineageEntityType sourceType, Long sourceId) {
        return loads(new QueryLineage().setSourceType(sourceType).setSourceId(sourceId));
    }

    default List<Lineage> findByTargetTypeAndTargetId(LineageEntityType targetType, Long targetId) {
        return loads(new QueryLineage().setTargetType(targetType).setTargetId(targetId));
    }

    default List<Lineage> findByModelId(Long modelId) {
        return loads(new QueryLineage().setModelId(modelId));
    }

}
