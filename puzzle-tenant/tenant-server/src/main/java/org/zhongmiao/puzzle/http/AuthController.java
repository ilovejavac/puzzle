package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.TokenService;
import com.dev.lib.security.util.SecurityContextHolder;
import com.dev.lib.security.util.UserDetails;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.auth.AuthCmd;
import org.zhongmiao.puzzle.auth.AuthService;

/**
 * 认证接口
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    private final AuthService authService;

    /**
     * 发送验证码
     */
    @PostMapping("/api/auth/code-send")
    public ServerResponse<Void> sendCode(@RequestBody @Validated AuthCmd.CodeSend cmd) {

        authService.sendCode(cmd);

        return ServerResponse.ok();
    }

    /**
     * 账号密码登录
     *
     * @return token
     */
    @PostMapping("/api/auth/pass-login")
    public ServerResponse<String> loginPass(@RequestBody @Validated AuthCmd.LoginPass cmd) {

        UserDetails ud = authService.login(cmd);

        return ServerResponse.success(
                tokenService.generateToken(ud)
        );
    }

    /**
     * 邮件登录
     */
    @PostMapping("/api/auth/mail-login")
    public ServerResponse<String> loginMail(@RequestBody @Validated AuthCmd.LoginMail cmd) {

        UserDetails ud = authService.login(cmd);

        return ServerResponse.success(
                tokenService.generateToken(ud)
        );
    }

    /**
     * 手机号登录
     */
    @PostMapping("/api/auth/phone-login")
    public ServerResponse<String> loginPhone(@RequestBody @Validated AuthCmd.LoginPhone cmd) {

        UserDetails ud = authService.login(cmd);

        return ServerResponse.success(
                tokenService.generateToken(ud)
        );
    }

    /**
     * 当前用户信息
     */
    @GetMapping("/api/current")
    public ServerResponse<UserDetails> current() {

        UserDetails current = SecurityContextHolder.get();

        return ServerResponse.success(current);
    }

}
