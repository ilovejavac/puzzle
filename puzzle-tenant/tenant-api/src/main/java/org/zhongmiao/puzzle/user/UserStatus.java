package org.zhongmiao.puzzle.user;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus implements CodeEnums {
    ACTIVE(1, "正常"),
    DISABLED(2, "禁用"),
    LOCKED(3, "锁定"),
    EXPIRED(4, "过期"),
    DEPRECATED(5, "弃用");

    private final Integer code;

    private final String  message;
}
