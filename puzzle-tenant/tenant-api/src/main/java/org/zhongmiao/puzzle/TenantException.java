package org.zhongmiao.puzzle;

import com.dev.lib.exceptions.BizException;

public class TenantException extends BizException {

    public TenantException(TenantError error) {

        super(error.getCode(), error.getMessage());
    }

}
