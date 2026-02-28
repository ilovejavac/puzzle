package org.zhongmiao.puzzle.query.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueryStatus implements CodeEnums {
    SUCCESS(1, "成功"),
    FAILED(2, "失败"),
    TIMEOUT(3, "超时"),
    CANCELLED(4, "已取消");

    private final Integer code;
    private final String message;
}
