package org.zhongmiao.puzzle.engine;

import com.dev.lib.exceptions.BizException;

/**
 * 引擎异常
 */
public class EngineException extends BizException {

    public EngineException(EngineError error) {
        super(error.getCode(), error.getMessage());
    }

}
