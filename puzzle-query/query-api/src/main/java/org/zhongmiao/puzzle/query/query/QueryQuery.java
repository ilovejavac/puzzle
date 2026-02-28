package org.zhongmiao.puzzle.query.query;

import lombok.Data;

/**
 * 查询查询对象
 */
@Data
public class QueryQuery {

    private QueryQuery() {

    }

    @Data
    public static class GetMetricResult {
        private String queryId;
    }

    @Data
    public static class GetDetailResult {
        private String queryId;
    }

}
