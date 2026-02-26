package org.zhongmiao.puzzle.meta;

import org.zhongmiao.puzzle.meta.SourceColumnDto;
import org.zhongmiao.puzzle.meta.SourceTableDto;

import java.util.List;

/**
 * Metadata Service
 */
public interface MetadataService {

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
}
