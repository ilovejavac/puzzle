package org.zhongmiao.puzzle.model;

import com.dev.lib.exceptions.BizException;

/**
 * 模型异常
 */
public class ModelException extends BizException {

    public ModelException(ModelError error) {
        super(error.getCode(), error.getMessage());
    }

}
