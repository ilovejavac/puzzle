package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JoinType implements CodeEnums {
    LEFT(1, "左连接"),
    INNER(2, "内连接"),
    RIGHT(3, "右连接"),
    FULL(4, "全连接");

    private final Integer code;
    private final String message;
}
