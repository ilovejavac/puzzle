package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.metric.MetricDto;

/**
 * 指标 RPC 服务
 * <p>
 * 被调用方: query
 */
public interface MetricRpc {

    /**
     * 获取指标完整定义
     * <p>
     * 包含模型和字段信息（被 query 模块调用）
     *
     * @param metricId 指标 ID
     * @return 指标完整定义
     */
    MetricDto.MetricFull getMetricFull(Long metricId);

}
