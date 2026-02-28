package org.zhongmiao.puzzle.meta;

import com.dev.lib.exceptions.BizException;

/**
 * 元数据异常
 */
public class MetaException extends BizException {

    public MetaException(MetaError error) {
        super(error.getCode(), error.getMessage());
    }

}
