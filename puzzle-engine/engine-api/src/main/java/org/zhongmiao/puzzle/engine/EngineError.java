package org.zhongmiao.puzzle.engine;

import com.dev.lib.web.model.CodeEnums;

public enum EngineError implements CodeEnums {

    ENGINE_NOT_EXISTS(43001, "计算引擎不存在"),
    ENGINE_TASK_NOT_EXISTS(43002, "引擎任务不存在"),
    ENGINE_ALREADY_RUNNING(43003, "引擎已在运行中"),
    ENGINE_OFFLINE(43004, "引擎已离线"),
    TASK_SUBMIT_FAILED(43005, "任务提交失败"),
    TASK_EXECUTION_FAILED(43006, "任务执行失败");

    private final Integer code;
    private final String message;

    EngineError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
