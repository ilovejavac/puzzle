package org.zhongmiao.puzzle.role;

import com.dev.lib.web.model.QueryRequest;

import org.springframework.data.domain.Page;
import java.util.List;

/**
 * Role Service
 */
public interface RoleService {

    /**
     * Create role
     */
    String createRole(RoleCmd.CreateRole cmd);

    /**
     * Update role
     */
    void updateRole(RoleCmd.UpdateRole cmd);

    /**
     * Delete roles by bizIds
     */
    void deleteRoles(List<String> bizIds);

    /**
     * Query role list with pagination
     */
    Page<RoleDto.Role> listRole(QueryRequest<RoleQuery.QueryRoleList> qry);

    /**
     * Get role detail by bizId
     */
    RoleDto.RoleDetail getRole(String bizId);

    /**
     * Update role permissions
     */
    void updateRolePermission(RoleCmd.UpdateRolePermission cmd);

}
