package org.zhongmiao.puzzle.query.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueryType implements CodeEnums {
    METRIC(1, "指标查询"),
    MODEL_DETAIL(2, "模型明细查询");

    private final Integer code;
    private final String message;
}
