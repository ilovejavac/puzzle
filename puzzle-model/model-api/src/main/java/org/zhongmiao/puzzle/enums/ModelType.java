package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelType implements CodeEnums {
    REALTIME(1, "实时模型"),
    OFFLINE(2, "离线模型");

    private final Integer code;
    private final String message;
}
