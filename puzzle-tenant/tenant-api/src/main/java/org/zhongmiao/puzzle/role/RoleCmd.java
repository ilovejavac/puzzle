package org.zhongmiao.puzzle.role;

import lombok.Data;

import java.util.List;

public class RoleCmd {

    private RoleCmd() {

    }

    @Data
    public static class CreateRole {

        private String roleCode;

        private String roleName;

        private String description;

    }

    @Data
    public static class UpdateRole extends CreateRole {

        private String id;

    }

    @Data
    public static class UpdateRolePermission {

        private String roleId;

        private List<String> permissions;

    }

}
