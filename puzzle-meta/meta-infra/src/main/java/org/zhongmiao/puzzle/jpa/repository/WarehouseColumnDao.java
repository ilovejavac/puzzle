package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.WarehouseColumn;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface WarehouseColumnDao extends BaseRepository<WarehouseColumn> {

    @Data
    class QueryWarehouseColumn extends DslQuery<WarehouseColumn> {

        private Long warehouseTableId;
        private Collection<Long> warehouseTableIdIn;
        private String columnName;
        private String columnNameLike;
        private Collection<Long> idIn;

    }

    default Optional<WarehouseColumn> findByBizId(String bizId) {
        return load(new QueryWarehouseColumn().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryWarehouseColumn().setBizId(bizId));
    }

    default List<WarehouseColumn> findByWarehouseTableId(Long warehouseTableId) {
        return loads(new QueryWarehouseColumn().setWarehouseTableId(warehouseTableId));
    }

    default Optional<WarehouseColumn> findByWarehouseTableIdAndColumnName(Long warehouseTableId, String columnName) {
        List<WarehouseColumn> results = loads(new QueryWarehouseColumn()
                .setWarehouseTableId(warehouseTableId)
                .setColumnName(columnName));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
