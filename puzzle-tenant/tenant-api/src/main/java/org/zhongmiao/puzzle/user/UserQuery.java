package org.zhongmiao.puzzle.user;

import lombok.Data;

public class UserQuery {

    private UserQuery() {

    }

    @Data
    public static class QueryUser {

        private String username;

        private String email;

        private String phone;

    }

}
