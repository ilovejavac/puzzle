package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.model.ModelCmd;
import org.zhongmiao.puzzle.model.ModelDto;
import org.zhongmiao.puzzle.model.ModelQuery;
import org.zhongmiao.puzzle.model.ModelService;

import java.util.List;

/**
 * 模型接口
 * <p>
 * 模型生命周期管理：创建、修改、删除、查询、部署、停止
 */
@RestController
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    /**
     * 创建模型
     * 需要权限[model:create]
     */
    @RequirePermission("model:create")
    @PostMapping("/api/model/create-model")
    public ServerResponse<Void> createModel(@RequestBody ModelCmd.CreateModel cmd) {
        modelService.createModel(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改模型
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/update-model")
    public ServerResponse<Void> updateModel(@RequestBody ModelCmd.UpdateModel cmd) {
        modelService.updateModel(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除模型
     * 需要权限[model:delete]
     */
    @RequirePermission("model:delete")
    @PostMapping("/api/model/delete-model")
    public ServerResponse<Void> deleteModel(@RequestBody ModelCmd.DeleteModel cmd) {
        modelService.deleteModel(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询模型列表（分页）
     * 需要权限[model:list]
     */
    @RequirePermission("model:list")
    @PostMapping("/api/model/list-models")
    public ServerResponse<List<ModelDto.ModelList>> listModels(
            @RequestBody QueryRequest<ModelQuery.QueryModel> qry) {
        return ServerResponse.success(modelService.listModels(qry));
    }

    /**
     * 获取模型详情
     * 需要权限[model:view]
     */
    @RequirePermission("model:view")
    @PostMapping("/api/model/get-model")
    public ServerResponse<ModelDto.ModelDetail> getModel(@RequestBody ModelQuery.GetModel qry) {
        return ServerResponse.success(modelService.getModel(qry));
    }

    /**
     * 获取模型完整定义
     * 需要权限[model:view]
     */
    @RequirePermission("model:view")
    @PostMapping("/api/model/get-model-full")
    public ServerResponse<ModelDto.ModelFull> getModelFull(@RequestBody ModelQuery.GetModel qry) {
        return ServerResponse.success(modelService.getModelFull(qry));
    }

    /**
     * 部署模型
     * 需要权限[model:deploy]
     */
    @RequirePermission("model:deploy")
    @PostMapping("/api/model/deploy-model")
    public ServerResponse<Void> deployModel(@RequestBody ModelCmd.DeployModel cmd) {
        modelService.deployModel(cmd);
        return ServerResponse.ok();
    }

    /**
     * 停止模型
     * 需要权限[model:stop]
     */
    @RequirePermission("model:stop")
    @PostMapping("/api/model/stop-model")
    public ServerResponse<Void> stopModel(@RequestBody ModelCmd.StopModel cmd) {
        modelService.stopModel(cmd);
        return ServerResponse.ok();
    }
}
