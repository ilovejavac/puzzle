package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.TenantError;
import org.zhongmiao.puzzle.TenantException;
import org.zhongmiao.puzzle.jpa.entity.PuzzleTenant;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.jpa.entity.PuzzleRole;
import org.zhongmiao.puzzle.jpa.repository.TenantDao;
import org.zhongmiao.puzzle.jpa.repository.UserDao;
import org.zhongmiao.puzzle.jpa.repository.RoleDao;
import org.zhongmiao.puzzle.jpa.repository.PermissionDao;
import org.zhongmiao.puzzle.tenant.TenantCmd;
import org.zhongmiao.puzzle.tenant.TenantDto;
import org.zhongmiao.puzzle.tenant.TenantQuery;
import org.zhongmiao.puzzle.tenant.TenantService;
import org.zhongmiao.puzzle.user.UserStatus;
import org.zhongmiao.puzzle.role.RoleType;

import java.util.List;

/**
 * Tenant Repository Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TenantRepo implements TenantService {

    private final TenantDao dao;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PermissionDao permissionDao;

    @Override
    @Transactional
    public String createTenant(TenantCmd.CreateTenant cmd) {
        // 1. Create tenant
        PuzzleTenant entity = new PuzzleTenant();
        entity.setTenantName(cmd.getTenantName());
        entity.setExpiredAt(cmd.getExpiredAt());

        dao.save(entity);
        String tenantBizId = entity.getBizId();
        Long tenantId = entity.getId();  // Get database ID for setting tenantId

        log.info("Created tenant: {} with bizId: {}", cmd.getTenantName(), tenantBizId);

        // 2. Create admin user if provided
        if (cmd.getAdminUser() != null) {
            createTenantAdmin(tenantId, tenantBizId, cmd);
        }

        return tenantBizId;
    }

    /**
     * Create admin user for the new tenant
     */
    private void createTenantAdmin(Long tenantId, String tenantBizId, TenantCmd.CreateTenant cmd) {
        // 1. Create user
        PuzzleUser user = new PuzzleUser();
        user.setUsername(cmd.getAdminUser().getUsername());
        user.setEmail(cmd.getAdminUser().getEmail());
        user.setPhone(cmd.getAdminUser().getPhone());
        user.setStatus(UserStatus.ACTIVE);
        // Explicitly set tenant ID to the newly created tenant
        user.setTenantId(tenantId);

        userDao.save(user);
        log.info("Created admin user {} for tenant: {}", cmd.getAdminUser().getUsername(), cmd.getTenantName());

        // 2. Find or create tenant admin role
        List<PuzzleRole> adminRoles = roleDao.loads(
                new RoleDao.QueryRole()
                        .setTenantId(tenantBizId)
                        .setRoleCode("TENANT_ADMIN")
        );

        PuzzleRole adminRole;
        if (adminRoles.isEmpty()) {
            // Create tenant-specific admin role
            adminRole = new PuzzleRole();
            adminRole.setTenantId(tenantId);
            adminRole.setRoleCode("TENANT_ADMIN");
            adminRole.setRoleName(cmd.getTenantName() + "管理员");
            adminRole.setDescription("租户管理员，拥有本租户所有权限");
            adminRole.setType(RoleType.SYSTEM);
            roleDao.save(adminRole);
            log.info("Created TENANT_ADMIN role for tenant: {}", cmd.getTenantName());
        } else {
            adminRole = adminRoles.get(0);
        }

        // 3. Assign admin role to user
        user.setRoles(List.of(adminRole));
        userDao.save(user);
        log.info("Assigned TENANT_ADMIN role to user {} for tenant: {}", cmd.getAdminUser().getUsername(), cmd.getTenantName());
    }

    @Override
    @Transactional
    public void updateTenant(TenantCmd.UpdateTenant cmd) {
        PuzzleTenant entity = dao.load(
                new TenantDao.QueryTenant().setBizId(cmd.getId())
        ).orElseThrow(() -> new TenantException(TenantError.TENANT_NOT_EXISTS));

        // JPA auto-update: direct modification in transaction
        entity.setTenantName(cmd.getTenantName());
        entity.setExpiredAt(cmd.getExpiredAt());

        log.info("Updated tenant: {}", cmd.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenantDto.TenantList> listTenant(QueryRequest<TenantQuery.QueryTenant> qry) {
        TenantDao.QueryTenant query = new TenantDao.QueryTenant();
        query.external(qry);
        return dao.page(query).map(this::mapToDto);
    }

    private TenantDto.TenantList mapToDto(PuzzleTenant entity) {
        TenantDto.TenantList dto = new TenantDto.TenantList();
        dto.setBizId(entity.getBizId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

}
