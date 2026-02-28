package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.Dimension;
import org.zhongmiao.puzzle.enums.DimensionType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DimensionDao extends BaseRepository<Dimension> {

    @Data
    class QueryDimension extends DslQuery<Dimension> {

        private String dimCode;
        private String dimCodeLike;
        private String dimName;
        private String dimNameLike;
        private DimensionType dimType;
        private Collection<DimensionType> dimTypeIn;
        private Long modelFieldId;
        private Collection<Long> modelFieldIdIn;
        private Collection<Long> idIn;

    }

    default Optional<Dimension> findByBizId(String bizId) {
        return load(new QueryDimension().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryDimension().setBizId(bizId));
    }

    default Optional<Dimension> findByDimCode(String dimCode) {
        return load(new QueryDimension().setDimCode(dimCode));
    }

    default List<Dimension> findByModelFieldId(Long modelFieldId) {
        return loads(new QueryDimension().setModelFieldId(modelFieldId));
    }

    default List<Dimension> findByDimType(DimensionType dimType) {
        return loads(new QueryDimension().setDimType(dimType));
    }

}
