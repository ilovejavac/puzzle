package org.zhongmiao.puzzle.engine.enums;

import com.dev.lib.web.model.CodeEnums;

public enum ExecutionPlanStatus implements CodeEnums {
    PENDING(1, "待审批"),
    APPROVED(2, "已审批"),
    ACTIVE(3, "生效中"),
    FAILED(4, "失败");

    private final Integer code;
    private final String message;

    ExecutionPlanStatus(Integer code, String message) {
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
