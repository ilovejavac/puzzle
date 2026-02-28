package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelStatus implements CodeEnums {
    DRAFT(1, "草稿"),
    DEPLOYING(2, "部署中"),
    RUNNING(3, "运行中"),
    STOPPED(4, "已停止"),
    FAILED(5, "失败");

    private final Integer code;
    private final String message;
}
