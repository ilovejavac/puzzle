package org.zhongmiao.puzzle.metric;

import lombok.Data;

/**
 * 指标查询对象
 */
@Data
public class MetricQuery {

    private MetricQuery() {

    }

    @Data
    public static class QueryMetric {
        private Long modelId;
    }

}
