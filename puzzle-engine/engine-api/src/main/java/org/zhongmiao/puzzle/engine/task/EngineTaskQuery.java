package org.zhongmiao.puzzle.engine.task;

import lombok.Data;

/**
 * 引擎任务查询对象
 */
@Data
public class EngineTaskQuery {

    private EngineTaskQuery() {

    }

    @Data
    public static class QueryTask {
        private Long modelId;
    }

    @Data
    public static class GetTask {
        private Long taskId;
    }

    @Data
    public static class GetTaskLogs {
        private Long taskId;
    }

    @Data
    public static class ListModelTasks {
        private Long modelId;
    }

    @Data
    public static class GetTaskMetrics {
        private Long taskId;
    }

}
