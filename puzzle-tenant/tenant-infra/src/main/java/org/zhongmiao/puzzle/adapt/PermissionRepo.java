package org.zhongmiao.puzzle.adapt;

import com.dev.lib.security.model.EndpointPermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.jpa.entity.PuzzlePermission;
import org.zhongmiao.puzzle.jpa.entity.PuzzlePermissionToPermissionDtoMapper;
import org.zhongmiao.puzzle.jpa.entity.PuzzleRole;
import org.zhongmiao.puzzle.jpa.repository.PermissionDao;
import org.zhongmiao.puzzle.jpa.repository.RoleDao;
import org.zhongmiao.puzzle.permission.PermissionDto;
import org.zhongmiao.puzzle.permission.PermissionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Permission Repository Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionRepo implements PermissionService {

    private final PermissionDao dao;

    private final RoleDao       roleDao;

    private final PuzzlePermissionToPermissionDtoMapper mapper;

    @Override
    @Transactional
    public List<PermissionDto> queryPermissionResources() {

        List<PuzzlePermission> permissions = dao.loads(new PermissionDao.QueryPermission());

        return permissions.stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Register or update permissions for a service with intelligent synchronization
     * <p>
     * Strategy:
     * - Use (service, path, method) as stable identifier for an endpoint
     * - permissionCode can be updated without losing role bindings
     * - Delete only when endpoint no longer exists (path + method)
     *
     * @param service     Service name
     * @param permissions List of endpoint permissions from the service
     */
    @Transactional
    public void registerPermissions(String service, List<EndpointPermission> permissions) {

        log.info("Registering {} endpoints for service: {}", permissions.size(), service);

        // Flatten permissions from all endpoints
        List<PermissionEntry> permissionEntries = permissions.stream()
                .flatMap(ep -> Arrays.stream(ep.getPermissions())
                        .map(code -> new PermissionEntry(code, ep.getPath(), ep.getMethod())))
                .distinct()
                .toList();

        log.info("Extracted {} unique permissions for service: {}", permissionEntries.size(), service);

        // Build lookup key: path + method
        Set<String> newEndpointKeys = permissionEntries.stream()
                .map(entry -> entry.path() + ":" + entry.method())
                .collect(Collectors.toSet());

        // Find existing permissions for this service
        List<PuzzlePermission> existingPermissions = dao.findByService(service);

        // Track processed permissions to identify deletions
        Set<PuzzlePermission> processedPermissions = new java.util.HashSet<>();

        // Save or update permissions using (path, method) as stable identifier
        List<PuzzlePermission> toSave = new ArrayList<>();
        for (PermissionEntry entry : permissionEntries) {
            // Find existing permission by (service, path, method)
            List<PuzzlePermission> existing = dao.findByServiceAndPathAndMethod(
                    service, entry.path(), entry.method()
            );

            if (existing.isEmpty()) {
                // New permission - create new entity
                PuzzlePermission entity = new PuzzlePermission();
                entity.setService(service);
                entity.setPermissionCode(entry.code());
                entity.setDescription(buildDescription(entry.path(), entry.method()));
                entity.setPath(entry.path());
                entity.setMethod(entry.method());
                toSave.add(entity);
                log.debug(
                        "Creating new permission: service={}, path={}, method={}, code={}",
                        service, entry.path(), entry.method(), entry.code()
                );
            } else {
                // Existing permission - update code and description if changed
                PuzzlePermission entity = existing.get(0);
                processedPermissions.add(entity);

                boolean updated = false;
                if (!entry.code().equals(entity.getPermissionCode())) {
                    log.info(
                            "Updating permission code: {} -> {} (path={}, method={})",
                            entity.getPermissionCode(), entry.code(), entry.path(), entry.method()
                    );
                    entity.setPermissionCode(entry.code());
                    updated = true;
                }

                String newDescription = buildDescription(entry.path(), entry.method());
                if (!newDescription.equals(entity.getDescription())) {
                    entity.setDescription(newDescription);
                    updated = true;
                }

                if (updated) {
                    toSave.add(entity);
                }
            }
        }

        if (!toSave.isEmpty()) {
            dao.saveAll(toSave);
            log.info("Saved/Updated {} permissions for service: {}", toSave.size(), service);
        }

        // Identify permissions to delete (endpoints that no longer exist)
        List<PuzzlePermission> toDelete = existingPermissions.stream()
                .filter(p -> !processedPermissions.contains(p))
                .toList();

        if (!toDelete.isEmpty()) {
            // Check permissions and prepare for safe deletion
            List<PuzzlePermission> canDelete   = new ArrayList<>();
            List<PuzzlePermission> hasBindings = new ArrayList<>();

            for (PuzzlePermission permission : toDelete) {
                List<PuzzleRole> boundRoles = roleDao.findByPermissionsId(permission.getId());
                if (!boundRoles.isEmpty()) {
                    hasBindings.add(permission);
                } else {
                    canDelete.add(permission);
                }
            }

            // Delete permissions without role bindings
            if (!canDelete.isEmpty()) {
                dao.deleteAll(canDelete);
                log.info("Deleted {} obsolete permissions for service: {}", canDelete.size(), service);
            }

            // Log warnings for permissions with role bindings
            if (!hasBindings.isEmpty()) {
                log.warn(
                        "Skipped deletion of {} permissions due to role bindings for service: {}",
                        hasBindings.size(), service
                );
                for (PuzzlePermission permission : hasBindings) {
                    log.warn(
                            "  - path={}, method={}, code={}",
                            permission.getPath(), permission.getMethod(), permission.getPermissionCode()
                    );
                }
            }
        }
    }

    /**
     * Build description from path and method
     */
    private String buildDescription(String path, String method) {

        return path + " [" + method + "]";
    }

    private PermissionDto mapToDto(PuzzlePermission entity) {

        return mapper.convert(entity);
    }

    /**
     * Permission entry record
     */
    private record PermissionEntry(String code, String path, String method) {
    }

}
