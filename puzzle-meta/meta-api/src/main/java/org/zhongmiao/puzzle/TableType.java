package org.zhongmiao.puzzle;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableType implements CodeEnums {
    table(1, ""),
    view(2, "");

    private final Integer code;

    private final String  message;
}
