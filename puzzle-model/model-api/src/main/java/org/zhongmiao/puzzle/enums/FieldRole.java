package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldRole implements CodeEnums {
    DIMENSION(1, "维度"),
    MEASURE(2, "度量"),
    FILTER(3, "过滤条件"),
    PK(4, "主键");

    private final Integer code;
    private final String message;
}
