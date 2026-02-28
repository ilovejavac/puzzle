package org.zhongmiao.puzzle.query.query;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 查询 DTO
 */
@Data
public class QueryDto {

    private QueryDto() {

    }

    @Data
    public static class MetricQueryResult {
        private List<Map<String, Object>> data;
        private Long total;
    }

    @Data
    public static class DetailQueryResult {
        private List<Map<String, Object>> data;
        private Long total;
    }

    @Data
    public static class QueryResult {
        private Boolean success;
        private String errorMessage;
        private List<Map<String, Object>> data;
        private Long total;
        private Long executionTime;
    }

}
