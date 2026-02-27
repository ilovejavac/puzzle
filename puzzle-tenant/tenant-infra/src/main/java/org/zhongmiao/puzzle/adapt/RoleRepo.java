package org.zhongmiao.puzzle.adapt;

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
import org.zhongmiao.puzzle.jpa.repository.PermissionDao;
import org.zhongmiao.puzzle.jpa.repository.RoleDao;
import org.zhongmiao.puzzle.permission.PermissionDto;
import org.zhongmiao.puzzle.role.RoleCmd;
import org.zhongmiao.puzzle.role.RoleDto;
import org.zhongmiao.puzzle.role.RoleQuery;
import org.zhongmiao.puzzle.role.RoleService;
import org.zhongmiao.puzzle.role.RoleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Role Repository Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RoleRepo implements RoleService {

    private final RoleDao roleDao;
    private final PermissionDao permissionDao;

    @Override
    @Transactional
    public String createRole(RoleCmd.CreateRole cmd) {
        PuzzleRole entity = new PuzzleRole();
        entity.setRoleCode(cmd.getRoleCode());
        entity.setRoleName(cmd.getRoleName());
        entity.setDescription(cmd.getDescription());
        entity.setType(RoleType.USER);

        roleDao.save(entity);
        log.info("Created role: {} with bizId: {}", cmd.getRoleCode(), entity.getBizId());
        return entity.getBizId();
    }

    @Override
    @Transactional
    public void updateRole(RoleCmd.UpdateRole cmd) {
        PuzzleRole entity = roleDao.load(
                new RoleDao.QueryRole().setBizId(cmd.getId())
        ).orElseThrow(() -> new TenantException(TenantError.ROLE_NOT_EXISTS));

        // JPA auto-update: direct modification in transaction
        entity.setRoleCode(cmd.getRoleCode());
        entity.setRoleName(cmd.getRoleName());
        entity.setDescription(cmd.getDescription());

        log.info("Updated role: {}", cmd.getId());
    }

    @Override
    @Transactional
    public void deleteRoles(List<String> bizIds) {
        roleDao.delete(new RoleDao.QueryRole().setBizIdIn(bizIds));
        log.info("Deleted {} roles", bizIds.size());
    }

    @Override
    @Transactional
    public Page<RoleDto.Role> listRole(QueryRequest<RoleQuery.QueryRoleList> qry) {
        RoleDao.QueryRole queryRole = new RoleDao.QueryRole();
        queryRole.external(qry);
        return roleDao.page(queryRole).map(this::mapToRoleDto);
    }

    @Override
    @Transactional
    public RoleDto.RoleDetail getRole(String bizId) {
        PuzzleRole entity = roleDao.load(
                new RoleDao.QueryRole().setBizId(bizId)
        ).orElseThrow(() -> new TenantException(TenantError.ROLE_NOT_EXISTS));

        RoleDto.RoleDetail dto = new RoleDto.RoleDetail();
        dto.setBizId(entity.getBizId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setRoleCode(entity.getRoleCode());
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());

        List<PermissionDto> permissions = Optional.ofNullable(entity.getPermissions())
                .orElse(new ArrayList<>())
                .stream()
                .map(this::mapToPermissionDto)
                .toList();
        dto.setPermissions(permissions);

        return dto;
    }

    @Override
    @Transactional
    public void updateRolePermission(RoleCmd.UpdateRolePermission cmd) {
        PuzzleRole entity = roleDao.load(
                new RoleDao.QueryRole().setBizId(cmd.getRoleId())
        ).orElseThrow(() -> new TenantException(TenantError.ROLE_NOT_EXISTS));

        // Load all permissions by codes in one query
        List<String> permissionCodes = cmd.getPermissions();
        List<PuzzlePermission> allPermissions = permissionDao.loads(
                new PermissionDao.QueryPermission().setPermissionCodeIn(permissionCodes)
        );

        // Convert to map for easy lookup
        Map<String, PuzzlePermission> permissionMap = allPermissions.stream()
                .collect(Collectors.toMap(PuzzlePermission::getPermissionCode, p -> p));

        // Build permissions list maintaining order from cmd
        List<PuzzlePermission> permissions = permissionCodes.stream()
                .map(permissionMap::get)
                .filter(java.util.Objects::nonNull)
                .toList();

        // JPA auto-update: direct modification in transaction
        entity.setPermissions(permissions);

        log.info("Updated permissions for role: {}", cmd.getRoleId());
    }

    private RoleDto.Role mapToRoleDto(PuzzleRole entity) {
        RoleDto.Role dto = new RoleDto.Role();
        dto.setBizId(entity.getBizId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setRoleCode(entity.getRoleCode());
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private PermissionDto mapToPermissionDto(PuzzlePermission entity) {
        PermissionDto dto = new PermissionDto();
        dto.setService(entity.getService());
        dto.setPermissionCode(entity.getPermissionCode());
        dto.setDescription(entity.getDescription());
        dto.setPath(entity.getPath());
        dto.setMethod(entity.getMethod());
        return dto;
    }

}
