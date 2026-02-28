package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetricStatus implements CodeEnums {
    DRAFT(1, "草稿"),
    PUBLISHED(2, "已发布"),
    DEPRECATED(3, "已弃用");

    private final Integer code;
    private final String message;
}
