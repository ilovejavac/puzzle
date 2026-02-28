package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.OutputTable;
import org.zhongmiao.puzzle.enums.OutputStorageType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OutputTableDao extends BaseRepository<OutputTable> {

    @Data
    class QueryOutputTable extends DslQuery<OutputTable> {

        private Long modelId;
        private Collection<Long> modelIdIn;
        private OutputStorageType storageType;
        private Collection<OutputStorageType> storageTypeIn;
        private Boolean isPrimary;
        private Collection<Long> idIn;

    }

    default Optional<OutputTable> findByBizId(String bizId) {
        return load(new QueryOutputTable().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryOutputTable().setBizId(bizId));
    }

    default List<OutputTable> findByModelId(Long modelId) {
        return loads(new QueryOutputTable().setModelId(modelId));
    }

    default Optional<OutputTable> findPrimaryByModelId(Long modelId) {
        List<OutputTable> results = loads(new QueryOutputTable()
                .setModelId(modelId)
                .setIsPrimary(true));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    default List<OutputTable> findByStorageType(OutputStorageType storageType) {
        return loads(new QueryOutputTable().setStorageType(storageType));
    }

    default List<OutputTable> findByModelIdAndStorageType(Long modelId, OutputStorageType storageType) {
        return loads(new QueryOutputTable()
                .setModelId(modelId)
                .setStorageType(storageType));
    }

}
