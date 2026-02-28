package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.SourceColumn;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SourceColumnDao extends BaseRepository<SourceColumn> {

    @Data
    class QuerySourceColumn extends DslQuery<SourceColumn> {

        private Long sourceTableId;
        private Collection<Long> sourceTableIdIn;
        private String columnName;
        private String columnNameLike;
        private Collection<Long> idIn;

    }

    default Optional<SourceColumn> findByBizId(String bizId) {
        return load(new QuerySourceColumn().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QuerySourceColumn().setBizId(bizId));
    }

    default List<SourceColumn> findBySourceTableId(Long sourceTableId) {
        return loads(new QuerySourceColumn().setSourceTableId(sourceTableId));
    }

    default Optional<SourceColumn> findBySourceTableIdAndColumnName(Long sourceTableId, String columnName) {
        List<SourceColumn> results = loads(new QuerySourceColumn()
                .setSourceTableId(sourceTableId)
                .setColumnName(columnName));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
