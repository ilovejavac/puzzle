package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.WarehouseTable;
import org.zhongmiao.puzzle.enums.WarehouseStorageType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface WarehouseTableDao extends BaseRepository<WarehouseTable> {

    @Data
    class QueryWarehouseTable extends DslQuery<WarehouseTable> {

        private String databaseName;
        private String databaseNameLike;
        private String tableName;
        private String tableNameLike;
        private WarehouseStorageType storageType;
        private Collection<Long> idIn;

    }

    default Optional<WarehouseTable> findByBizId(String bizId) {
        return load(new QueryWarehouseTable().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryWarehouseTable().setBizId(bizId));
    }

    default List<WarehouseTable> findByDatabaseNameAndTableName(String databaseName, String tableName) {
        return loads(new QueryWarehouseTable()
                .setDatabaseName(databaseName)
                .setTableName(tableName));
    }

    default Optional<WarehouseTable> findByDatabaseNameAndTableNameAndStorageType(
            String databaseName, String tableName, WarehouseStorageType storageType) {
        List<WarehouseTable> results = loads(new QueryWarehouseTable()
                .setDatabaseName(databaseName)
                .setTableName(tableName)
                .setStorageType(storageType));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    default List<WarehouseTable> findByStorageType(WarehouseStorageType storageType) {
        return loads(new QueryWarehouseTable().setStorageType(storageType));
    }

}
