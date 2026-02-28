package org.zhongmiao.puzzle.engine.task;

import lombok.Data;
import org.zhongmiao.puzzle.engine.enums.EngineTaskStatus;
import org.zhongmiao.puzzle.engine.enums.EngineType;
import org.zhongmiao.puzzle.engine.enums.TaskType;

import java.time.LocalDateTime;

/**
 * 引擎任务 DTO
 */
@Data
public class EngineTaskDto {

    private EngineTaskDto() {

    }

    @Data
    public static class TaskList {
        private Long id;
        private Long modelId;
        private TaskType taskType;
        private EngineType engineType;
        private EngineTaskStatus status;
    }

    @Data
    public static class TaskDetail extends TaskList {
        private String engineJobId;
        private String engineConfig;
        private LocalDateTime startedAt;
        private LocalDateTime finishedAt;
        private String errorMsg;
    }

}
