package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AggFunction implements CodeEnums {
    SUM(1, "求和"),
    COUNT(2, "计数"),
    COUNT_DISTINCT(3, "去重计数"),
    AVG(4, "平均值"),
    MAX(5, "最大值"),
    MIN(6, "最小值");

    private final Integer code;
    private final String message;
}
