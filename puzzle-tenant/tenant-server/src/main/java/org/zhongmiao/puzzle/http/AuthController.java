package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.TokenService;
import com.dev.lib.security.util.SecurityContextHolder;
import com.dev.lib.security.util.UserDetails;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证接口
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    /**
     * 登录
     *
     * @return token
     */
    @PostMapping("/api/auth/login")
    public ServerResponse<String> login() {

        return ServerResponse.success(
                tokenService.generateToken(UserDetails.System)
        );
    }

    /**
     * 当前用户信息
     */
    @GetMapping("/api/current")
    public ServerResponse<UserDetails> current() {

        return ServerResponse.success(
                SecurityContextHolder.get()
        );
    }

}
