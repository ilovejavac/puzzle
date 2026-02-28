package org.zhongmiao.puzzle.engine.engine;

import lombok.Data;

/**
 * 引擎查询对象
 */
@Data
public class EngineQuery {

    private EngineQuery() {

    }

    @Data
    public static class GetExecutionPlan {
        private Long modelId;
    }

}
