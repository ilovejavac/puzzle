package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.query.query.MetricQueryRequest;
import org.zhongmiao.puzzle.query.query.QueryDto;

/**
 * 查询 RPC 服务
 * <p>
 * 供其他模块调用，执行查询操作
 */
public interface QueryRpc {

    /**
     * 指标查询
     * <p>
     * req: {
     * metricIds: [1, 2],
     * dimensionIds: [3, 4],
     * filters: [{dimId: 3, op: "IN", values: ["上海"]}],
     * timeRange: {start, end, granularity: "DAY"},
     * limit: 1000
     * }
     */
    QueryDto queryByMetric(MetricQueryRequest request);

    /**
     * 明细查询
     */
    QueryDto queryDetail(QueryDto request);

}
