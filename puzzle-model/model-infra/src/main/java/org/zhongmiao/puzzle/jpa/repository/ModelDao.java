package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.Model;
import org.zhongmiao.puzzle.enums.ModelStatus;
import org.zhongmiao.puzzle.enums.ModelType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ModelDao extends BaseRepository<Model> {

    @Data
    class QueryModel extends DslQuery<Model> {

        private String modelName;
        private String modelNameLike;
        private ModelType modelType;
        private Collection<ModelType> modelTypeIn;
        private ModelStatus status;
        private Collection<ModelStatus> statusIn;
        private Collection<Long> idIn;

    }

    default Optional<Model> findByBizId(String bizId) {
        return load(new QueryModel().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryModel().setBizId(bizId));
    }

    default Optional<Model> findByModelName(String modelName) {
        return load(new QueryModel().setModelName(modelName));
    }

    default List<Model> findByModelType(ModelType modelType) {
        return loads(new QueryModel().setModelType(modelType));
    }

    default List<Model> findByStatus(ModelStatus status) {
        return loads(new QueryModel().setStatus(status));
    }

    default List<Model> findByModelTypeAndStatus(ModelType modelType, ModelStatus status) {
        return loads(new QueryModel().setModelType(modelType).setStatus(status));
    }

}
