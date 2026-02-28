package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.ModelField;
import org.zhongmiao.puzzle.enums.AggFunction;
import org.zhongmiao.puzzle.enums.FieldRole;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ModelFieldDao extends BaseRepository<ModelField> {

    @Data
    class QueryModelField extends DslQuery<ModelField> {

        private Long modelId;
        private Collection<Long> modelIdIn;
        private Long sourceTableId;
        private Collection<Long> sourceTableIdIn;
        private FieldRole fieldRole;
        private Collection<FieldRole> fieldRoleIn;
        private AggFunction aggFunction;
        private Collection<AggFunction> aggFunctionIn;
        private String fieldAlias;
        private String fieldAliasLike;
        private Collection<Long> idIn;

    }

    default Optional<ModelField> findByBizId(String bizId) {
        return load(new QueryModelField().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryModelField().setBizId(bizId));
    }

    default List<ModelField> findByModelId(Long modelId) {
        return loads(new QueryModelField().setModelId(modelId));
    }

    default List<ModelField> findByModelIdAndFieldRole(Long modelId, FieldRole fieldRole) {
        return loads(new QueryModelField()
                .setModelId(modelId)
                .setFieldRole(fieldRole));
    }

    default List<ModelField> findBySourceTableId(Long sourceTableId) {
        return loads(new QueryModelField().setSourceTableId(sourceTableId));
    }

    default Optional<ModelField> findByModelIdAndFieldAlias(Long modelId, String fieldAlias) {
        List<ModelField> results = loads(new QueryModelField()
                .setModelId(modelId)
                .setFieldAlias(fieldAlias));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
