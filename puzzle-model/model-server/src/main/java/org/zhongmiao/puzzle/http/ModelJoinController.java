package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.model.ModelCmd;
import org.zhongmiao.puzzle.model.ModelService;

/**
 * 模型关联接口
 * <p>
 * 管理模型之间的表关联关系（JOIN）
 */
@RestController
@RequiredArgsConstructor
public class ModelJoinController {

    private final ModelService modelService;

    /**
     * 添加模型关联
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/add-join")
    public ServerResponse<Void> addJoin(@RequestBody ModelCmd.AddJoin cmd) {
        modelService.addJoin(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除模型关联
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/remove-join")
    public ServerResponse<Void> removeJoin(@RequestBody ModelCmd.RemoveJoin cmd) {
        modelService.removeJoin(cmd);
        return ServerResponse.ok();
    }
}
