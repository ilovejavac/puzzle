package org.zhongmiao.puzzle.query;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询错误码
 */
@Getter
@AllArgsConstructor
public enum QueryError implements CodeEnums {

    QUERY_NOT_EXISTS(44001, "查询不存在"),
    SAVED_QUERY_NOT_EXISTS(44002, "保存的查询不存在"),
    QUERY_EXECUTION_FAILED(44003, "查询执行失败"),
    QUERY_TIMEOUT(44004, "查询超时"),
    INVALID_SQL(44005, "无效的SQL"),
    UNSUPPORTED_OPERATION(44006, "不支持的操作");

    private final Integer code;
    private final String message;

}
