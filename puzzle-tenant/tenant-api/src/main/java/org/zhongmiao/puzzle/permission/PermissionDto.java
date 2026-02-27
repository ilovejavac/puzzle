package org.zhongmiao.puzzle.permission;

import com.dev.lib.web.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDto extends BaseVO {

    /**
     * 所属系统/服务
     */
    private String service;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求方法
     */
    private String method;

}
