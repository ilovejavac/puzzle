package org.zhongmiao.puzzle.meta;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 元数据错误码
 */
@Getter
@AllArgsConstructor
public enum MetaError implements CodeEnums {

    SOURCE_TABLE_NOT_EXISTS(41001, "源表不存在"),
    LINEAGE_NOT_FOUND(41002, "血缘关系未找到"),
    SCHEMA_SYNC_FAILED(41003, "Schema同步失败");

    private final Integer code;
    private final String message;

}
