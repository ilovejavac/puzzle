package org.zhongmiao.puzzle.dimension;

import lombok.Data;

/**
 * 维度查询对象
 */
@Data
public class DimensionQuery {

    private DimensionQuery() {

    }

    @Data
    public static class QueryDimension {
        private Long modelId;
    }

}
