package org.zhongmiao.puzzle;

import org.zhongmiao.puzzle.query.DimensionDto;
import org.zhongmiao.puzzle.query.MetricDto;
import org.zhongmiao.puzzle.query.MetricQueryRequest;
import org.zhongmiao.puzzle.query.QueryDto;

import java.util.List;

public interface QueryRpc {

    /**
     * 指标查询
     * <p>
     * req: {
     * metricIds: [1, 2],
     * dimensionIds: [3, 4],
     * filters: [{dimId: 3, op: "IN", values: ["上海"]}],
     * timeRange: {start, end, granularity: "DAY"},
     * limit: 1000
     * }
     */
    QueryDto queryByMetric(MetricQueryRequest request);

    /**
     * 生成 SQL 预览
     */
    String previewSQL(MetricQueryRequest request);

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
