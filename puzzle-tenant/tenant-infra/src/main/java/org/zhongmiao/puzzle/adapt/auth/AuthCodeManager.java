package org.zhongmiao.puzzle.adapt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zhongmiao.puzzle.auth.LoginType;


@Component
@RequiredArgsConstructor
public class AuthCodeManager {

    public void send(LoginType loginType, String receiver) {

    }

    public boolean verify(LoginType loginType, String receiver, String code) {
        return true;
    }
}
