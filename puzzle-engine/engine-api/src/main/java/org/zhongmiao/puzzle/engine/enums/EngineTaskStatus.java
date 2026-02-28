package org.zhongmiao.puzzle.engine.enums;

import com.dev.lib.web.model.CodeEnums;

public enum EngineTaskStatus implements CodeEnums {
    CREATED(1, "已创建"),
    SUBMITTING(2, "提交中"),
    RUNNING(3, "运行中"),
    SUSPENDED(4, "已暂停"),
    SUCCESS(5, "成功"),
    FAILED(6, "失败"),
    CANCELLED(7, "已取消");

    private final Integer code;
    private final String message;

    EngineTaskStatus(Integer code, String message) {
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
