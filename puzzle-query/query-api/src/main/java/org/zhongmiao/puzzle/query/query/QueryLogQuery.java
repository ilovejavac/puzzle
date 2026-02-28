package org.zhongmiao.puzzle.query.query;

import lombok.Data;

/**
 * 查询日志查询对象
 */
@Data
public class QueryLogQuery {

    private QueryLogQuery() {

    }

    @Data
    public static class QueryHistory {
        private String queryType;
    }

    @Data
    public static class GetStats {
        private String timeRange;
    }

}
