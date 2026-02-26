package org.zhongmiao.puzzle.query;

import org.zhongmiao.puzzle.query.DimensionDto;
import org.zhongmiao.puzzle.query.MetricDto;
import org.zhongmiao.puzzle.query.MetricQueryRequest;
import org.zhongmiao.puzzle.query.QueryDto;

import java.util.List;

/**
 * Query Service
 */
public interface QueryService {

    /**
     * Execute metric query
     */
    QueryDto executeQuery(MetricQueryRequest request);

    /**
     * Get all metrics
     */
    List<MetricDto> getAllMetrics();

    /**
     * Get metric by id
     */
    MetricDto getMetric(Long metricId);

    /**
     * Get all dimensions
     */
    List<DimensionDto> getAllDimensions();

    /**
     * Get dimension by id
     */
    DimensionDto getDimension(Long dimensionId);

    /**
     * Get metrics by model
     */
    List<MetricDto> getMetricsByModel(Long modelId);

    /**
     * Get dimensions by model
     */
    List<DimensionDto> getDimensionsByModel(Long modelId);
}
