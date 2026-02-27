package org.zhongmiao.puzzle.permission;

import java.util.List;

/**
 * Permission Service
 */
public interface PermissionService {

    /**
     * Get all permissions grouped by service
     *
     * @return list of permissions
     */
    List<PermissionDto> queryPermissionResources();

}
