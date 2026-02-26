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

    }

    @Data
    public static class UpdateTenant extends CreateTenant {

        private String id;

    }

}
