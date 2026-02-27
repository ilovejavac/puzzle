package org.zhongmiao.puzzle.tenant;

import lombok.Data;

import java.time.LocalDateTime;

public class TenantCmd {

    private TenantCmd() {

    }

    @Data
    public static class CreateTenant {

        private String tenantName;

        /**
         * 到期时间
         */
        private LocalDateTime expiredAt;

        /**
         * 租户管理员信息（可选）
         * 如果提供，创建租户时会同时创建该租户的管理员用户
         */
        private AdminUser adminUser;

        @Data
        public static class AdminUser {
            private String username;
            private String email;
            private String phone;
        }

    }

    @Data
    public static class UpdateTenant extends CreateTenant {

        private String id;

    }

}
