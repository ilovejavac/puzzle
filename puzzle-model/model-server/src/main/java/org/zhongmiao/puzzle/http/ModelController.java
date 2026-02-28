package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.model.*;
import org.zhongmiao.puzzle.model.MetricService;
import org.zhongmiao.puzzle.metric.MetricCmd;
import org.zhongmiao.puzzle.metric.MetricDto;
import org.zhongmiao.puzzle.metric.MetricQuery;
import org.zhongmiao.puzzle.model.DimensionService;
import org.zhongmiao.puzzle.dimension.DimensionCmd;
import org.zhongmiao.puzzle.dimension.DimensionDto;
import org.zhongmiao.puzzle.dimension.DimensionQuery;

import java.util.List;

/**
 * 模型接口
 * <p>
 * 管理数据模型，包括模型定义、字段配置、指标定义、维度定义、部署操作
 */
@RestController
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final MetricService metricService;
    private final DimensionService dimensionService;

    // ==================== 模型管理 ====================

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

    // ==================== 字段管理 ====================

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

    // ==================== 关联管理 ====================

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

    // ==================== 指标管理 ====================

    /**
     * 创建指标
     * 需要权限[model:create]
     */
    @RequirePermission("model:create")
    @PostMapping("/api/model/create-metric")
    public ServerResponse<Void> createMetric(@RequestBody MetricCmd.CreateMetric cmd) {
        metricService.createMetric(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改指标
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/update-metric")
    public ServerResponse<Void> updateMetric(@RequestBody MetricCmd.UpdateMetric cmd) {
        metricService.updateMetric(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除指标
     * 需要权限[model:delete]
     */
    @RequirePermission("model:delete")
    @PostMapping("/api/model/delete-metric")
    public ServerResponse<Void> deleteMetric(@RequestBody MetricCmd.DeleteMetric cmd) {
        metricService.deleteMetric(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询指标列表
     * 需要权限[model:view]
     */
    @RequirePermission("model:view")
    @PostMapping("/api/model/list-metrics")
    public ServerResponse<List<MetricDto.MetricList>> listMetrics(
            @RequestBody QueryRequest<MetricQuery.QueryMetric> qry) {
        return ServerResponse.success(metricService.listMetrics(qry));
    }

    // ==================== 维度管理 ====================

    /**
     * 创建维度
     * 需要权限[model:create]
     */
    @RequirePermission("model:create")
    @PostMapping("/api/model/create-dimension")
    public ServerResponse<Void> createDimension(@RequestBody DimensionCmd.CreateDimension cmd) {
        dimensionService.createDimension(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改维度
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/update-dimension")
    public ServerResponse<Void> updateDimension(@RequestBody DimensionCmd.UpdateDimension cmd) {
        dimensionService.updateDimension(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除维度
     * 需要权限[model:delete]
     */
    @RequirePermission("model:delete")
    @PostMapping("/api/model/delete-dimension")
    public ServerResponse<Void> deleteDimension(@RequestBody DimensionCmd.DeleteDimension cmd) {
        dimensionService.deleteDimension(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询维度列表
     * 需要权限[model:view]
     */
    @RequirePermission("model:view")
    @PostMapping("/api/model/list-dimensions")
    public ServerResponse<List<DimensionDto.DimensionList>> listDimensions(
            @RequestBody QueryRequest<DimensionQuery.QueryDimension> qry) {
        return ServerResponse.success(dimensionService.listDimensions(qry));
    }

}
