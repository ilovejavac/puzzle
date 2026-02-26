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

    }

    @Data
    public static class UpdateUser extends CreateUser {

        private String id;

    }

}
