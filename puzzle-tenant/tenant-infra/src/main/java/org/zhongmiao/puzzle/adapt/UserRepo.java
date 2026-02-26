package org.zhongmiao.puzzle.adapt;

import com.dev.lib.security.util.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.jpa.entity.PuzzlePermission;
import org.zhongmiao.puzzle.jpa.entity.PuzzleRole;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.jpa.repository.UserDao;
import org.zhongmiao.puzzle.user.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepo implements UserService {

    private final UserDao dao;

    @Override
    @Transactional
    public UserDetails loadUser(Long id) {

        UserDetails ud = null;
        Optional<PuzzleUser> loadUser = dao.findById(id);
        if (loadUser.isPresent()) {
            PuzzleUser it = loadUser.get();

            ud = mapUser2Detail(it);
        }
        return ud;
    }

    static UserDetails mapUser2Detail(PuzzleUser it) {

        UserDetails ud;
        List<PuzzleRole> roles = Optional.ofNullable(it.getRoles()).orElse(Collections.emptyList());

        List<String> rolCode = roles.stream().map(PuzzleRole::getRoleCode).distinct().toList();
        List<String> permissionCode = roles.stream().map(PuzzleRole::getPermissions).flatMap(Collection::stream).map(
                PuzzlePermission::getPermissionCode).distinct().toList();

        ud = UserDetails.builder()
                .id(it.getId())
                .email(it.getEmail())
                .phone(it.getPhone())
                .username(it.getUsername())
                .userType("user")
                .tenant(it.getTenantId())
                .roles(
                        rolCode
                )
                .permissions(
                        permissionCode
                )
                .validated(true)

                .build();
        return ud;
    }

}
