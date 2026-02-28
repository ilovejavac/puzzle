package org.zhongmiao.puzzle.query.savedquery;

import lombok.Data;

import java.util.List;

/**
 * 保存查询命令对象
 */
@Data
public class SavedQueryCmd {

    private SavedQueryCmd() {

    }

    @Data
    public static class SaveQuery {
        private String queryName;
        private org.zhongmiao.puzzle.query.enums.QueryType queryType;
        private List<Long> metricIds;
        private List<Long> dimensionIds;
    }

    @Data
    public static class DeleteSavedQuery {
        private Long id;
    }

}
