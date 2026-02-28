package org.zhongmiao.puzzle.metric;

import lombok.Data;

/**
 * 指标命令对象
 */
@Data
public class MetricCmd {

    private MetricCmd() {

    }

    @Data
    public static class CreateMetric {
        private Long modelId;
        private String metricName;
        private String metricCode;
        private Long sourceFieldId;
        private String aggFunction;
    }

    @Data
    public static class UpdateMetric extends CreateMetric {
        private Long id;
    }

    @Data
    public static class DeleteMetric {
        private Long id;
    }

}
