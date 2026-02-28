package org.zhongmiao.puzzle.model;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 模型服务接口
 */
public interface ModelService {

    /**
     * 创建模型
     */
    void createModel(ModelCmd.CreateModel cmd);

    /**
     * 修改模型
     */
    void updateModel(ModelCmd.UpdateModel cmd);

    /**
     * 删除模型
     */
    void deleteModel(ModelCmd.DeleteModel cmd);

    /**
     * 查询模型列表（分页）
     */
    Page<ModelDto.ModelList> listModels(QueryRequest<ModelQuery.QueryModel> qry);

    /**
     * 获取模型详情
     */
    ModelDto.ModelDetail getModel(ModelQuery.GetModel qry);

    /**
     * 获取模型完整定义
     */
    ModelDto.ModelFull getModelFull(ModelQuery.GetModel qry);

    /**
     * 部署模型
     */
    void deployModel(ModelCmd.DeployModel cmd);

    /**
     * 停止模型
     */
    void stopModel(ModelCmd.StopModel cmd);

    /**
     * 批量添加模型字段
     */
    void addFields(ModelCmd.AddFields cmd);

    /**
     * 批量删除模型字段
     */
    void removeFields(ModelCmd.RemoveFields cmd);

    /**
     * 添加模型关联
     */
    void addJoin(ModelCmd.AddJoin cmd);

    /**
     * 删除模型关联
     */
    void removeJoin(ModelCmd.RemoveJoin cmd);

}
