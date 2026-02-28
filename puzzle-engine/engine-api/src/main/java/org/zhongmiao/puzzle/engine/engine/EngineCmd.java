package org.zhongmiao.puzzle.engine.engine;

import lombok.Data;

/**
 * 引擎命令对象
 */
@Data
public class EngineCmd {

    private EngineCmd() {

    }

    @Data
    public static class RestartModel {
        private Long modelId;
    }

    @Data
    public static class StopModel {
        private Long modelId;
    }

    @Data
    public static class RegeneratePlan {
        private Long modelId;
    }

}
