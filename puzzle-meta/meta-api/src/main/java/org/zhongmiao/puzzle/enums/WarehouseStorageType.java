package org.zhongmiao.puzzle.meta.enums;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarehouseStorageType implements CodeEnums {
    CLICKHOUSE(1, "ClickHouse"),
    ICEBERG(2, "Iceberg"),
    DORIS(3, "Doris"),
    ES(4, "Elasticsearch"),
    HUDI(5, "Hudi")
    ;

    private final Integer code;
    private final String message;
}
