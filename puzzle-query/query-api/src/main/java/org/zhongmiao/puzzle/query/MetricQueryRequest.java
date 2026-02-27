package org.zhongmiao.puzzle.query;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Metric Query Request
 */
@Data
public class MetricQueryRequest {

    private List<Long>          metricIds;

    private List<Long>          dimensionIds;

    private Map<String, Object> filters;

    private String              startTime;

    private String              endTime;

    private Integer             limit;

    private Integer             offset;

}
