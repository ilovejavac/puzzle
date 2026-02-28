package org.zhongmiao.puzzle.query.savedquery;

import lombok.Data;

/**
 * 保存查询查询对象
 */
@Data
public class SavedQueryQuery {

    private SavedQueryQuery() {

    }

    @Data
    public static class QuerySavedQuery {
        private String queryName;
    }

    @Data
    public static class GetSavedQuery {
        private Long id;
    }

}
