package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.security.service.annotation.RequireRole;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.tenant.TenantCmd;
import org.zhongmiao.puzzle.tenant.TenantDto;
import org.zhongmiao.puzzle.tenant.TenantQuery;
import org.zhongmiao.puzzle.tenant.TenantService;

import org.springframework.data.domain.Page;
import java.util.List;

/**
 * 租户接口
 * <p>
 * 管理租户信息
 */
@RestController
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    /**
     * 创建租户
     * 需要 admin 角色
     */
    @RequireRole("admin")
    @PostMapping("/api/tenant/create-tenant")
    public ServerResponse<Void> createTenant(@RequestBody TenantCmd.CreateTenant cmd) {
        tenantService.createTenant(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改租户
     * 需要 admin 角色
     */
    @RequireRole("admin")
    @PutMapping("/api/tenant/update-tenant")
    public ServerResponse<Void> updateTenant(@RequestBody TenantCmd.UpdateTenant cmd) {
        tenantService.updateTenant(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询租户列表（分页）
     * 需要权限[tenant:list]
     */
    @RequirePermission("tenant:list")
    @PostMapping("/api/tenant/list-tenant")
    public ServerResponse<List<TenantDto.TenantList>> listTenant(@RequestBody QueryRequest<TenantQuery.QueryTenant> qry) {
        return ServerResponse.success(tenantService.listTenant(qry));
    }

}
