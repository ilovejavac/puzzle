package org.zhongmiao.puzzle;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TenantError implements CodeEnums {

    AUTH_PASSWORD_VERIFY_FAILED(100099, "账号密码错误"),
    AUTH_CODE_VERIFY_FAILED(100100, "验证码错误"),
    USER_NOT_EXISTS(100101, "用户不存在"),
    ROLE_NOT_EXISTS(100102, "角色不存在"),
    TENANT_NOT_EXISTS(100103, "租户不存在"),
    PERMISSION_NOT_EXISTS(100104, "权限不存在");

    private final Integer code;

    private final String  message;
}
