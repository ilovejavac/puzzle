package org.zhongmiao.puzzle.role;

import com.dev.lib.web.BaseVO;
import lombok.Data;
import org.zhongmiao.puzzle.permission.PermissionDto;

import java.util.List;

public class RoleDto {

    private RoleDto() {

    }

    @Data
    public static class Role extends BaseVO {

        private String roleCode;

        private String roleName;

        private String description;

    }

    @Data
    public static class RoleDetail extends Role {

        private List<PermissionDto> permissions;

    }

}
