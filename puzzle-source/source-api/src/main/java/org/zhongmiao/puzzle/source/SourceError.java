package org.zhongmiao.puzzle.source;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据源错误码
 */
@Getter
@AllArgsConstructor
public enum SourceError implements CodeEnums {

    DATASOURCE_NOT_EXISTS(40001, "数据源不存在"),
    DATASOURCE_ALREADY_EXISTS(40002, "数据源已存在"),
    DATASOURCE_IN_USE(40003, "数据源正在使用中"),
    CONNECTION_TEST_FAILED(40004, "连接测试失败"),
    SCHEMA_SYNC_FAILED(40005, "Schema同步失败");

    private final Integer code;
    private final String message;

}
