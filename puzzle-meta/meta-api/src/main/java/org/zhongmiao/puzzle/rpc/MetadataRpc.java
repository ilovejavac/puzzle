package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.meta.SourceColumnDto;
import org.zhongmiao.puzzle.meta.SourceTableDto;

import java.util.List;

/**
 * Metadata RPC Service
 */
public interface MetadataRpc {

    /**
     * Sync schema from datasource
     */
    void syncSchema(Long datasourceId);

    /**
     * Get source tables by datasource
     */
    List<SourceTableDto> getSourceTables(Long datasourceId);

    /**
     * Get source table by id
     */
    SourceTableDto getSourceTable(Long tableId);

    /**
     * List columns of table
     */
    List<SourceColumnDto> listColumns(Long tableId);

    /**
     * Save lineage
     */
    void saveLineage(Long modelId, String fromTables, String toTable);

    /**
     * Register warehouse table
     */
    void registerWarehouseTable(Long modelId, String tableName);

}
