package org.zhongmiao.puzzle.query.query;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 查询命令对象
 */
@Data
public class QueryCmd {

    private QueryCmd() {

    }

    @Data
    public static class ExecuteMetricQuery {
        private List<Long> metricIds;
        private List<Long> dimensionIds;
        private List<FilterCondition> filters;
        private TimeRange timeRange;
        private Integer limit;
    }

    @Data
    public static class ExecuteDetailQuery {
        private Long modelId;
        private List<String> columns;
        private List<FilterCondition> filters;
        private Integer limit;
        private Integer offset;
    }

    @Data
    public static class ExecuteSql {
        private String sql;
        private Integer limit;
    }

    @Data
    public static class FilterCondition {
        private String field;
        private String operator;
        private Object value;
    }

    @Data
    public static class TimeRange {
        private String start;
        private String end;
        private String granularity;
    }

}
