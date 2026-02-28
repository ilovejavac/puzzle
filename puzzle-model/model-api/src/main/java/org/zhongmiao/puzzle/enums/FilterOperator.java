package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilterOperator implements CodeEnums {
    EQ(1, "等于"),
    NEQ(2, "不等于"),
    GT(3, "大于"),
    LT(4, "小于"),
    IN(5, "在列表中"),
    BETWEEN(6, "在范围中"),
    LIKE(7, "模糊匹配");

    private final Integer code;
    private final String message;
}
