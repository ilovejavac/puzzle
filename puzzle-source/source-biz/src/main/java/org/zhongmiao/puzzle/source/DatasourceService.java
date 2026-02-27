package org.zhongmiao.puzzle.source;

import java.util.List;

/**
 * Datasource Service
 */
public interface DatasourceService {

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

}
