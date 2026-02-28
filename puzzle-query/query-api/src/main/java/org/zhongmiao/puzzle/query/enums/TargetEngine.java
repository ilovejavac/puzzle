package org.zhongmiao.puzzle.query.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetEngine implements CodeEnums {
    CLICKHOUSE(1, "ClickHouse"),
    TRINO(2, "Trino"),
    ES(3, "Elasticsearch"),
    AUTO(4, "自动选择");

    private final Integer code;
    private final String message;
}
