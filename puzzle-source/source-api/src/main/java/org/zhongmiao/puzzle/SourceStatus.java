package org.zhongmiao.puzzle;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceStatus implements CodeEnums {
    NORMAL(0, "正常"),
    FAILURE(1, "连接失败"),
    DISABLED(2, "停用");

    private final Integer code;

    private final String message;
}
