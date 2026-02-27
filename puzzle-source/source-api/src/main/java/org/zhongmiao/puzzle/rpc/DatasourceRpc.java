package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.source.DatasourceCmd;
import org.zhongmiao.puzzle.source.DatasourceDto;
import org.zhongmiao.puzzle.source.DatasourceQuery;

import java.util.List;
import java.util.Map;

/**
 * Datasource RPC Service
 */
public interface DatasourceRpc {

    /**
     * Create datasource
     */
    Long createDatasource(DatasourceCmd cmd);

    /**
     * Update datasource
     */
    void updateDatasource(Long id, DatasourceCmd cmd);

    /**
     * Delete datasource
     */
    void deleteDatasource(Long id);

    /**
     * Get datasource by id
     */
    DatasourceDto getDatasource(Long id);

    /**
     * Query datasources
     */
    List<DatasourceDto> queryDatasources(DatasourceQuery query);

    /**
     * Test connection
     */
    Boolean testConnection(Long id);

    /**
     * Get connection config
     */
    Map<String, Object> getConnConfig(Long datasourceId);

}
