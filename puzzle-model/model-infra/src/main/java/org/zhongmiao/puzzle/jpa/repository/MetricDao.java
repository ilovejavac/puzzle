package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.Metric;
import org.zhongmiao.puzzle.enums.MetricStatus;
import org.zhongmiao.puzzle.enums.MetricType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MetricDao extends BaseRepository<Metric> {

    @Data
    class QueryMetric extends DslQuery<Metric> {

        private Long modelId;
        private Collection<Long> modelIdIn;
        private String metricCode;
        private String metricCodeLike;
        private MetricType metricType;
        private Collection<MetricType> metricTypeIn;
        private MetricStatus status;
        private Collection<MetricStatus> statusIn;
        private Collection<Long> idIn;

    }

    default Optional<Metric> findByBizId(String bizId) {
        return load(new QueryMetric().setBizId(bizId));
    }

    default boolean existsByBizId(String bizId) {
        return exists(new QueryMetric().setBizId(bizId));
    }

    default Optional<Metric> findByMetricCode(String metricCode) {
        return load(new QueryMetric().setMetricCode(metricCode));
    }

    default List<Metric> findByModelId(Long modelId) {
        return loads(new QueryMetric().setModelId(modelId));
    }

    default List<Metric> findByModelIdAndStatus(Long modelId, MetricStatus status) {
        return loads(new QueryMetric()
                .setModelId(modelId)
                .setStatus(status));
    }

    default List<Metric> findByMetricType(MetricType metricType) {
        return loads(new QueryMetric().setMetricType(metricType));
    }

    default List<Metric> findByStatus(MetricStatus status) {
        return loads(new QueryMetric().setStatus(status));
    }

}
