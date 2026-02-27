package org.zhongmiao.puzzle.user;

import lombok.Data;

public class UserCmd {

    private UserCmd() {

    }

    @Data
    public static class CreateUser {

        private String username;

        private String email;

        private String phone;

        /**
         * 租户业务 ID（可选）
         * 如果不指定，使用当前登录用户的租户
         * 超级管理员可以指定任意租户 bizId 来创建该租户的用户
         * 系统会自动查询租户的数据库 ID 并设置
         */
        private String tenantBizId;

    }

    @Data
    public static class UpdateUser extends CreateUser {

        private String id;

    }

}
