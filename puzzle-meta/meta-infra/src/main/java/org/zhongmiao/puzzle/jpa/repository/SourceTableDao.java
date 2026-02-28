package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.SourceTable;
import org.zhongmiao.puzzle.TableType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SourceTableDao extends BaseRepository<SourceTable> {

    @Data
    class QuerySourceTable extends DslQuery<SourceTable> {

        private Long datasourceId;
        private Collection<Long> datasourceIdIn;
        private String schemaName;
        private String schemaNameLike;
        private String tableName;
        private String tableNameLike;
        private TableType tableType;
        private Collection<Long> idIn;

    }

    default Optional<SourceTable> findByBizId(String bizId) {
        return load(new QuerySourceTable().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QuerySourceTable().setBizId(bizId));
    }

    default List<SourceTable> findByDatasourceId(Long datasourceId) {
        return loads(new QuerySourceTable().setDatasourceId(datasourceId));
    }

    default List<SourceTable> findBySchemaNameAndTableName(String schemaName, String tableName) {
        return loads(new QuerySourceTable().setSchemaName(schemaName).setTableName(tableName));
    }

    default Optional<SourceTable> findByDatasourceIdAndSchemaNameAndTableName(Long datasourceId, String schemaName, String tableName) {
        List<SourceTable> results = loads(new QuerySourceTable()
                .setDatasourceId(datasourceId)
                .setSchemaName(schemaName)
                .setTableName(tableName));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
