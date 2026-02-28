package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetricType implements CodeEnums {
    ATOMIC(1, "原子指标"),
    DERIVED(2, "衍生指标"),
    COMPOSITE(3, "复合指标");

    private final Integer code;
    private final String message;
}
