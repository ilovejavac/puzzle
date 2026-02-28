package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssignedEngine implements CodeEnums {
    FLINK(1, "Flink"),
    SEATUNNEL(2, "SeaTunnel");

    private final Integer code;
    private final String message;
}
