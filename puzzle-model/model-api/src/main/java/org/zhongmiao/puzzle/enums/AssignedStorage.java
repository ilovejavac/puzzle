package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssignedStorage implements CodeEnums {
    CLICKHOUSE(1, "ClickHouse"),
    ICEBERG(2, "Iceberg"),
    DORIS(3, "Doris");

    private final Integer code;
    private final String message;
}
