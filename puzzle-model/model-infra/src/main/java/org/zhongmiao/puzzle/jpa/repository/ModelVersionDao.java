package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.ModelVersion;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ModelVersionDao extends BaseRepository<ModelVersion> {

    @Data
    class QueryModelVersion extends DslQuery<ModelVersion> {

        private Long modelId;
        private Collection<Long> modelIdIn;
        private Integer version;
        private Collection<Integer> versionIn;
        private Collection<Long> idIn;

    }

    default Optional<ModelVersion> findByBizId(String bizId) {
        return load(new QueryModelVersion().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryModelVersion().setBizId(bizId));
    }

    default List<ModelVersion> findByModelId(Long modelId) {
        return loads(new QueryModelVersion().setModelId(modelId));
    }

    default Optional<ModelVersion> findByModelIdAndVersion(Long modelId, Integer version) {
        List<ModelVersion> results = loads(new QueryModelVersion()
                .setModelId(modelId)
                .setVersion(version));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
