package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongmiao.puzzle.role.RoleCmd;
import org.zhongmiao.puzzle.role.RoleDto;
import org.zhongmiao.puzzle.role.RoleQuery;

import java.util.List;

/**
 * 角色接口
 * <p>
 * 管理角色信息
 */
@RestController
@RequiredArgsConstructor
public class RoleController {

    /**
     * 创建角色
     */
    @RequirePermission("role:create")
    @PostMapping("/api/role/create-role")
    public ServerResponse<Void> createRole(@RequestBody @Validated RoleCmd.CreateRole cmd) {

        return ServerResponse.ok();
    }

    /**
     * 修改角色
     */
    @RequirePermission("role:update")
    @PutMapping("/api/role/update-role")
    public ServerResponse<Void> updateRole(@RequestBody RoleCmd.UpdateRole cmd) {

        return ServerResponse.ok();
    }

    /**
     * 批量删除角色
     */
    @RequirePermission("role:delete")
    @DeleteMapping("/api/role/delete-roles")
    public ServerResponse<Void> deleteRole(@RequestBody List<String> ids) {

        return ServerResponse.ok();
    }

    /**
     * 角色列表
     */
    @RequirePermission("role:list")
    @PostMapping("/api/role/list-role")
    public ServerResponse<List<RoleDto.Role>> listRole(@RequestBody @Validated QueryRequest<RoleQuery.QueryRoleList> qry) {

        return ServerResponse.ok();
    }

    /**
     * 角色详情
     */
    @RequirePermission("role:list")
    @GetMapping("/api/role/{id}")
    public ServerResponse<RoleDto.RoleDetail> getRole(@PathVariable String id) {

        return ServerResponse.ok();
    }

    /**
     * 角色绑定权限
     */
    @RequirePermission("role:update")
    @PutMapping("/api/role/update-role-permission")
    public ServerResponse<Void> bindPermission(@RequestBody RoleCmd.UpdateRolePermission cmd) {

        return ServerResponse.ok();
    }

}
