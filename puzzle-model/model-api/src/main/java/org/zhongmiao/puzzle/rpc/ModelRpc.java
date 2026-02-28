package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.model.ModelDto;
import org.zhongmiao.puzzle.enums.ModelStatus;

/**
 * 模型 RPC 服务
 * <p>
 * 被调用方: engine, query
 */
public interface ModelRpc {

    /**
     * 获取模型完整定义
     * <p>
     * 包含字段、关联等信息（被 engine 模块调用）
     *
     * @param modelId 模型 ID
     * @return 模型完整定义
     */
    ModelDto.ModelFull getModelFull(Long modelId);

    /**
     * 更新模型状态
     * <p>
     * 更新模型的部署/运行状态（被 engine 模块调用）
     *
     * @param modelId 模型 ID
     * @param status  新状态
     */
    void updateModelStatus(Long modelId, ModelStatus status);

    /**
     * 保存模型产出表信息
     * <p>
     * 保存模型生成的数仓表信息（被 engine 模块调用）
     *
     * @param modelId     模型 ID
     * @param outputTable 产出表名
     */
    void saveOutput(Long modelId, String outputTable);

    /**
     * 获取模型产出表名
     * <p>
     * 获取模型生成的数仓表名（被 query 模块调用）
     *
     * @param modelId 模型 ID
     * @return 产出表名
     */
    String getModelOutputTable(Long modelId);

}
