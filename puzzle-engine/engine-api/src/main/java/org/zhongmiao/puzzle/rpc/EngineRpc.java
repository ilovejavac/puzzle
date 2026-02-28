package org.zhongmiao.puzzle.rpc;

/**
 * 计算引擎 RPC 服务
 * <p>
 * 被调用方: model
 */
public interface EngineRpc {

    /**
     * 部署模型到计算引擎
     * <p>
     * 将模型部署到计算引擎执行（被 model 模块调用）
     *
     * @param modelId 模型 ID
     */
    void deployModel(Long modelId);

    /**
     * 停止模型执行
     * <p>
     * 停止正在运行的模型任务（被 model 模块调用）
     *
     * @param modelId 模型 ID
     */
    void stopModel(Long modelId);

    /**
     * 重启模型执行
     * <p>
     * 重新启动已停止的模型任务（被 model 模块调用）
     *
     * @param modelId 模型 ID
     */
    void restartModel(Long modelId);

}
