package org.zhongmiao.puzzle.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OutputStorageType implements CodeEnums {
    CLICKHOUSE(1, "ClickHouse"),
    ICEBERG(2, "Iceberg"),
    DORIS(3, "Doris"),
    ES(4, "Elasticsearch");

    private final Integer code;
    private final String message;
}
