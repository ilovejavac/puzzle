package org.zhongmiao.puzzle.auth;

import lombok.Data;

public class AuthCmd {

    private AuthCmd() {

    }

    @Data
    public static class CodeSend {
        private String receiver;

        private LoginType type;
    }

    @Data
    public static class LoginPhone {

        private String phone;

        private String code;

    }

    @Data
    public static class LoginMail {

        private String mail;

        private String code;

    }

    @Data
    public static class LoginPass {

        private String username;

        private String password;

    }

}
