package org.zhongmiao.puzzle.engine.enums;

import com.dev.lib.web.model.CodeEnums;

public enum EngineType implements CodeEnums {
    SEATUNNEL(1, "SeaTunnel"),
    FLINK(2, "Flink"),
    DOLPHIN(3, "DolphinScheduler");

    private final Integer code;
    private final String message;

    EngineType(Integer code, String message) {
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
