package org.zhongmiao.puzzle.query.query;

import lombok.Data;

/**
 * 查询日志 DTO
 */
@Data
public class QueryLogDto {

    private QueryLogDto() {

    }

    @Data
    public static class QueryLogList {
        private Long id;
        private Long userId;
        private Long modelId;
        private org.zhongmiao.puzzle.query.enums.TargetEngine queryType;
        private org.zhongmiao.puzzle.query.enums.QueryStatus status;
        private Long executionTime;
        private Long rowsReturned;
    }

}
