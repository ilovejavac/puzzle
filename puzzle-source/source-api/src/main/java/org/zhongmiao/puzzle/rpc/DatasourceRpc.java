package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.conn.ConnConfig;

/**
 * 数据源 RPC 服务
 * <p>
 * 被调用方: meta, engine
 */
public interface DatasourceRpc {

    /**
     * 获取数据源连接配置
     * <p>
     * 用于元数据同步和引擎执行
     *
     * @param datasourceId 数据源 ID
     * @return 连接配置
     */
    ConnConfig getConnConfig(Long datasourceId);

}
