package org.zhongmiao.puzzle.query;

import com.dev.lib.exceptions.BizException;

/**
 * 查询异常
 */
public class QueryException extends BizException {

    public QueryException(QueryError error) {
        super(error.getCode(), error.getMessage());
    }

}
