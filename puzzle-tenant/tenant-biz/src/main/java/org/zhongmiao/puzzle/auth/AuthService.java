package org.zhongmiao.puzzle.auth;

import com.dev.lib.security.util.UserDetails;

public interface AuthService {

    void sendCode(AuthCmd.CodeSend cmd);

    UserDetails login(AuthCmd.LoginPhone cmd);

    UserDetails login(AuthCmd.LoginMail cmd);

    UserDetails login(AuthCmd.LoginPass cmd);

}
