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
 * 模型字段接口
 * <p>
 * 管理模型的字段配置
 */
@RestController
@RequiredArgsConstructor
public class ModelFieldController {

    private final ModelService modelService;

    /**
     * 批量添加模型字段
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/add-fields")
    public ServerResponse<Void> addFields(@RequestBody ModelCmd.AddFields cmd) {
        modelService.addFields(cmd);
        return ServerResponse.ok();
    }

    /**
     * 批量删除模型字段
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/remove-fields")
    public ServerResponse<Void> removeFields(@RequestBody ModelCmd.RemoveFields cmd) {
        modelService.removeFields(cmd);
        return ServerResponse.ok();
    }
}
