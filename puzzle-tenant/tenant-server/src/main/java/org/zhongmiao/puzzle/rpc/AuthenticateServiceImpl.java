package org.zhongmiao.puzzle.rpc;

import com.dev.lib.security.model.EndpointPermission;
import com.dev.lib.security.service.AuthenticateService;
import com.dev.lib.security.util.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zhongmiao.puzzle.user.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserService userService;

    @Override
    public UserDetails loadUserById(Long id) {

        return userService.loadUser(id);
    }

    @Override
    public Collection<UserDetails> batchLoadUserByIds(Set<Long> ids) {

        return List.of();
    }

    @Override
    public void registerPermissions(List<EndpointPermission> permissions) {

    }

}
