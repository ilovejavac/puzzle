package org.zhongmiao.puzzle.adapt;

import com.dev.lib.security.util.UserDetails;
import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.TenantError;
import org.zhongmiao.puzzle.TenantException;
import org.zhongmiao.puzzle.jpa.entity.PuzzlePermission;
import org.zhongmiao.puzzle.jpa.entity.PuzzleRole;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.jpa.entity.PuzzleTenant;
import org.zhongmiao.puzzle.jpa.repository.UserDao;
import org.zhongmiao.puzzle.jpa.repository.TenantDao;
import org.zhongmiao.puzzle.user.UserCmd;
import org.zhongmiao.puzzle.user.UserDto;
import org.zhongmiao.puzzle.user.UserQuery;
import org.zhongmiao.puzzle.user.UserService;
import org.zhongmiao.puzzle.user.UserStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * User Repository Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRepo implements UserService {

    private final UserDao dao;
    private final TenantDao tenantDao;

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

    @Override
    @Transactional
    public String createUser(UserCmd.CreateUser cmd) {
        PuzzleUser entity = new PuzzleUser();
        entity.setUsername(cmd.getUsername());
        entity.setEmail(cmd.getEmail());
        entity.setPhone(cmd.getPhone());
        entity.setStatus(UserStatus.ACTIVE);

        // If tenantBizId is provided, query the tenant and set its ID
        // This overrides the automatic tenant ID from JPA plugin
        if (cmd.getTenantBizId() != null && !cmd.getTenantBizId().isEmpty()) {
            PuzzleTenant tenant = tenantDao.load(
                    new TenantDao.QueryTenant().setBizId(cmd.getTenantBizId())
            ).orElseThrow(() -> new TenantException(TenantError.TENANT_NOT_EXISTS));

            // Set the database ID of the tenant
            entity.setTenantId(tenant.getId());
            log.info("Creating user {} for specified tenant: {} (ID: {})",
                    cmd.getUsername(), cmd.getTenantBizId(), tenant.getId());
        } else {
            log.info("Creating user {} for current tenant from token", cmd.getUsername());
        }

        dao.save(entity);
        return entity.getBizId();
    }

    @Override
    @Transactional
    public void updateUser(UserCmd.UpdateUser cmd) {
        PuzzleUser entity = dao.load(
                new UserDao.QueryUser().setBizId(cmd.getId())
        ).orElseThrow(() -> new TenantException(TenantError.USER_NOT_EXISTS));

        // JPA auto-update: direct modification in transaction
        entity.setUsername(cmd.getUsername());
        entity.setEmail(cmd.getEmail());
        entity.setPhone(cmd.getPhone());
    }

    @Override
    @Transactional
    public void deleteUsers(List<String> bizIds) {
        dao.delete(new UserDao.QueryUser().setBizIdIn(bizIds));
    }

    @Override
    @Transactional
    public Page<UserDto.UserList> listUser(QueryRequest<UserQuery.QueryUser> qry) {
        UserDao.QueryUser queryUser = new UserDao.QueryUser();
        queryUser.external(qry);
        return dao.page(queryUser).map(this::mapToUserListDto);
    }

    @Override
    @Transactional
    public UserDto.UserList getUser(String bizId) {
        PuzzleUser entity = dao.load(
                new UserDao.QueryUser().setBizId(bizId)
        ).orElseThrow(() -> new TenantException(TenantError.USER_NOT_EXISTS));

        return mapToUserListDto(entity);
    }

    static UserDetails mapUser2Detail(PuzzleUser it) {
        UserDetails ud;
        List<PuzzleRole> roles = Optional.ofNullable(it.getRoles()).orElse(Collections.emptyList());

        List<String> rolCode = roles.stream().map(PuzzleRole::getRoleCode).distinct().toList();
        List<String> permissionCode = roles.stream()
                .map(PuzzleRole::getPermissions)
                .flatMap(Collection::stream)
                .map(PuzzlePermission::getPermissionCode)
                .distinct()
                .toList();

        ud = UserDetails.builder()
                .id(it.getId())
                .email(it.getEmail())
                .phone(it.getPhone())
                .username(it.getUsername())
                .userType("user")
                .tenant(it.getTenantId())
                .roles(rolCode)
                .permissions(permissionCode)
                .validated(true)
                .build();
        return ud;
    }

    private UserDto.UserList mapToUserListDto(PuzzleUser entity) {
        UserDto.UserList dto = new UserDto.UserList();
        dto.setBizId(entity.getBizId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        return dto;
    }

}
