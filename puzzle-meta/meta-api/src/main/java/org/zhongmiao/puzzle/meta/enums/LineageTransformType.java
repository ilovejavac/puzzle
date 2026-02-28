package org.zhongmiao.puzzle.meta.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LineageTransformType implements CodeEnums {
    DIRECT(1, "直接映射"),
    AGGREGATION(2, "聚合"),
    CALCULATION(3, "计算"),
    JOIN(4, "关联");

    private final Integer code;
    private final String message;
}
