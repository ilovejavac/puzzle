package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.QueryRouteRule;
import org.zhongmiao.puzzle.query.enums.TargetEngine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QueryRouteRuleDao extends BaseRepository<QueryRouteRule> {

    @Data
    class QueryQueryRouteRule extends DslQuery<QueryRouteRule> {

        private String ruleName;
        private String ruleNameLike;
        private TargetEngine targetEngine;
        private Collection<TargetEngine> targetEngineIn;
        private Boolean enabled;
        private Collection<Long> idIn;

    }

    default Optional<QueryRouteRule> findByBizId(String bizId) {
        return load(new QueryQueryRouteRule().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryQueryRouteRule().setBizId(bizId));
    }

    default Optional<QueryRouteRule> findByRuleName(String ruleName) {
        return load(new QueryQueryRouteRule().setRuleName(ruleName));
    }

    default List<QueryRouteRule> findByEnabled(Boolean enabled) {
        return loads(new QueryQueryRouteRule().setEnabled(enabled));
    }

    default List<QueryRouteRule> findByTargetEngine(TargetEngine targetEngine) {
        return loads(new QueryQueryRouteRule().setTargetEngine(targetEngine));
    }

}
