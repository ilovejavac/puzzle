package org.zhongmiao.puzzle.metric;

import lombok.Data;
import org.zhongmiao.puzzle.enums.AggFunction;
import org.zhongmiao.puzzle.enums.MetricStatus;
import org.zhongmiao.puzzle.enums.MetricType;

/**
 * 指标 DTO
 */
@Data
public class MetricDto {

    private MetricDto() {

    }

    /**
     * 指标列表项
     */
    @Data
    public static class MetricList {
        private Long id;
        private String metricName;
        private String metricCode;
        private MetricType metricType;
        private MetricStatus status;
    }

    /**
     * 指标详情
     */
    @Data
    public static class MetricDetail extends MetricList {
        private Long modelId;
        private Long sourceFieldId;
        private AggFunction aggFunction;
        private String filterExpression;
        private String description;
    }

    /**
     * 指标完整定义（包含模型和字段信息）
     */
    @Data
    public static class MetricFull extends MetricDetail {
        private String modelName;
        private String sourceTableName;
        private String sourceFieldName;
        private String sourceFieldType;
    }

}
