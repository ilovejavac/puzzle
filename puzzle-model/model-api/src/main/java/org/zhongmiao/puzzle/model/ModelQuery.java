package org.zhongmiao.puzzle.model;

import lombok.Data;

/**
 * 模型查询对象
 */
@Data
public class ModelQuery {

    private ModelQuery() {

    }

    @Data
    public static class QueryModel {
        private String modelName;
    }

    @Data
    public static class GetModel {
        private Long id;
    }

}
