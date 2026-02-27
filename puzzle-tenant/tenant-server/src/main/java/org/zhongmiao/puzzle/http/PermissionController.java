package org.zhongmiao.puzzle.http;

import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.permission.PermissionDto;
import org.zhongmiao.puzzle.permission.PermissionService;

import java.util.List;

/**
 * 权限接口
 * <p>
 * 获取系统配置的权限
 */
@RestController
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * 获取可授权资源列表
     */
    @GetMapping("/api/permission/resources")
    public ServerResponse<List<PermissionDto>> queryPermissionResources() {
        return ServerResponse.success(permissionService.queryPermissionResources());
    }

}
