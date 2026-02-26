package org.zhongmiao.puzzle.permission;

import lombok.Data;

@Data
public class PermissionDto {

    /**
     * 权限 code
     */
    private String code;

    /**
     * 所属系统
     */
    private String sys;

}
