package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.Datasource;
import org.zhongmiao.puzzle.source.enums.SourceStatus;
import org.zhongmiao.puzzle.source.enums.SourceType;

import java.util.List;
import java.util.Optional;

public interface DatasourceDao extends BaseRepository<Datasource> {

    @Data
    class QueryDatasource extends DslQuery<Datasource> {

        private String sourceName;
        private String sourceNameLike;
        private SourceType sourceType;
        private SourceStatus status;

    }

    default Optional<Datasource> findByBizId(String bizId) {
        return load(new QueryDatasource().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryDatasource().setBizId(bizId));
    }

    default Optional<Datasource> findBySourceName(String sourceName) {
        return load(new QueryDatasource().setSourceName(sourceName));
    }

}
