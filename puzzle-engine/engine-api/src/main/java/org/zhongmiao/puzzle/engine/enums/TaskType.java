package org.zhongmiao.puzzle.engine.enums;

import com.dev.lib.web.model.CodeEnums;

public enum TaskType implements CodeEnums {
    SYNC(1, "同步任务"),
    COMPUTE(2, "计算任务"),
    ARCHIVE(3, "归档任务");

    private final Integer code;
    private final String message;

    TaskType(Integer code, String message) {
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
