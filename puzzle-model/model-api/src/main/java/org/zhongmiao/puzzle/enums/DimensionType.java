package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DimensionType implements CodeEnums {
    CATEGORICAL(1, "分类维度"),
    TIME(2, "时间维度"),
    GEO(3, "地理维度");

    private final Integer code;
    private final String message;
}
