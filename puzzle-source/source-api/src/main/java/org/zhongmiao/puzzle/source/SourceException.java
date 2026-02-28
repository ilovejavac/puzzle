package org.zhongmiao.puzzle.source;

import com.dev.lib.exceptions.BizException;

/**
 * 数据源异常
 */
public class SourceException extends BizException {

    public SourceException(SourceError error) {
        super(error.getCode(), error.getMessage());
    }

}
