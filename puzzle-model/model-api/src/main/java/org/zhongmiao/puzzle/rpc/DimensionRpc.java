package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.dimension.DimensionDto;

/**
 * 维度 RPC 服务
 * <p>
 * 被调用方: query
 */
public interface DimensionRpc {

    /**
     * 获取维度定义
     * <p>
     * 包含模型和字段信息（被 query 模块调用）
     *
     * @param dimensionId 维度 ID
     * @return 维度完整定义
     */
    DimensionDto.DimensionFull getDimension(Long dimensionId);

}
