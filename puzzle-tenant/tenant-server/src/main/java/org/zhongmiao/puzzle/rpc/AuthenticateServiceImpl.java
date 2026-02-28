package org.zhongmiao.puzzle.rpc;

import com.dev.lib.security.model.EndpointPermission;
import com.dev.lib.security.service.AuthenticateService;
import com.dev.lib.security.util.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.zhongmiao.puzzle.adapt.PermissionRepo;
import org.zhongmiao.puzzle.user.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@DubboService
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserService userService;

    private final PermissionRepo permissionRepo;

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
        log.info("Registering {} permissions", permissions.size());

        // Group permissions by service and register each service's permissions
        permissions.stream()
                .collect(Collectors.groupingBy(EndpointPermission::getService))
                .forEach(permissionRepo::registerPermissions);
    }

}
