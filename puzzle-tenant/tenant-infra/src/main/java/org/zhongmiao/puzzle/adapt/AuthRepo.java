package org.zhongmiao.puzzle.adapt;

import com.dev.lib.security.util.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zhongmiao.puzzle.TenantError;
import org.zhongmiao.puzzle.TenantException;
import org.zhongmiao.puzzle.adapt.auth.AuthCodeManager;
import org.zhongmiao.puzzle.auth.AuthCmd;
import org.zhongmiao.puzzle.auth.AuthService;
import org.zhongmiao.puzzle.auth.LoginType;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.jpa.repository.UserDao;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthRepo implements AuthService {

    private final UserDao userDao;

    private final AuthCodeManager codeManager;

    @Override
    public void sendCode(AuthCmd.CodeSend cmd) {
        codeManager.send(cmd.getType(), cmd.getReceiver());
    }

    @Override
    public UserDetails login(AuthCmd.LoginPhone cmd) {
        if (!codeManager.verify(LoginType.PHONE, cmd.getPhone(), cmd.getCode())) {
            throw new TenantException(TenantError.AUTH_CODE_VERIFY_FAILED);
        }

        PuzzleUser pu = userDao.load(new UserDao.QueryUser().setPhone(cmd.getPhone())).orElseThrow(
                () -> new TenantException(TenantError.USER_NOT_EXISTS)
        );

        return UserRepo.mapUser2Detail(pu);
    }

    @Override
    public UserDetails login(AuthCmd.LoginMail cmd) {
        if (!codeManager.verify(LoginType.MAIL, cmd.getMail(), cmd.getCode())) {
            throw new TenantException(TenantError.AUTH_CODE_VERIFY_FAILED);
        }

        PuzzleUser pu = userDao.load(new UserDao.QueryUser().setEmail(cmd.getMail())).orElseThrow(
                () -> new TenantException(TenantError.USER_NOT_EXISTS)
        );

        return UserRepo.mapUser2Detail(pu);
    }

    @Override
    public UserDetails login(AuthCmd.LoginPass cmd) {
        PuzzleUser pu = userDao.load(new UserDao.QueryUser().setUsername(cmd.getUsername())).orElseThrow(
                () -> new TenantException(TenantError.USER_NOT_EXISTS)
        );

        if (!Objects.equals(cmd.getPassword(), pu.getPassword())) {
            throw new TenantException(TenantError.AUTH_PASSWORD_VERIFY_FAILED);
        }

        return UserRepo.mapUser2Detail(pu);
    }
}
