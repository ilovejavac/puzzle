package org.zhongmiao.puzzle.role;

import com.dev.lib.entity.dsl.Condition;
import com.dev.lib.entity.dsl.QueryType;
import lombok.Data;

public class RoleQuery {

    private RoleQuery() {

    }

    @Data
    public static class QueryRoleList {

        private String roleCode;

        @Condition(type = QueryType.LIKE)
        private String roleName;

    }

}
