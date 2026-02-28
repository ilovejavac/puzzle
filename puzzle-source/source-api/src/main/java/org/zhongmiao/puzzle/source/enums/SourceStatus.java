package org.zhongmiao.puzzle.source.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceStatus {
    ACTIVE("ACTIVE", "正常"),
    DISABLED("DISABLED", "禁用"),
    TESTING("TESTING", "测试中"),
    ERROR("ERROR", "错误"),
    LOCKED("LOCKED", "锁定"),
    EXPIRED("EXPIRED", "过期");

    @JsonValue
    private final String code;
    private final String message;
}
