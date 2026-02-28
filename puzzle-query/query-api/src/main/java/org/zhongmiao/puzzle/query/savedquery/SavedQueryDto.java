package org.zhongmiao.puzzle.query.savedquery;

import lombok.Data;

import java.util.List;

/**
 * 保存查询 DTO
 */
@Data
public class SavedQueryDto {

    private SavedQueryDto() {

    }

    @Data
    public static class SavedQueryList {
        private Long id;
        private String queryName;
        private org.zhongmiao.puzzle.query.enums.QueryType queryType;
    }

    @Data
    public static class SavedQueryDetail extends SavedQueryList {
        private List<Long> metricIds;
        private List<Long> dimensionIds;
        private org.zhongmiao.puzzle.query.model.FilterCondition filters;
        private org.zhongmiao.puzzle.query.model.TimeRange timeRange;
        private Long modelId;
        private org.zhongmiao.puzzle.query.enums.TargetEngine targetEngine;
        private Integer cacheTtlSeconds;
    }

}
