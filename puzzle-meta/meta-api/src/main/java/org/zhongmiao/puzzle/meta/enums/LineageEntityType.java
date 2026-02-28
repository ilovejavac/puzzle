package org.zhongmiao.puzzle.meta.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LineageEntityType implements CodeEnums {
    SOURCE_TABLE(1, "源表"),
    MODEL(2, "模型"),
    METRIC(3, "指标");

    private final Integer code;
    private final String message;
}
