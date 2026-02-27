package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzleRole;

import java.util.Collection;
import java.util.List;

public interface RoleDao extends BaseRepository<PuzzleRole> {

    /**
     * Find all roles that have the specified permission
     *
     * @param permissionId the permission ID
     * @return list of roles bound to this permission
     */
    List<PuzzleRole> findByPermissionsId(Long permissionId);

    /**
     * Count roles that have the specified permission
     *
     * @param permissionId the permission ID to check
     * @return count of roles using this permission
     */
    long countByPermissionsId(Long permissionId);

    @Data
    class QueryRole extends DslQuery<PuzzleRole> {

        private String roleCode;

        private String roleName;

        private Collection<String> bizIdIn;

        private String tenantId;

    }

}
