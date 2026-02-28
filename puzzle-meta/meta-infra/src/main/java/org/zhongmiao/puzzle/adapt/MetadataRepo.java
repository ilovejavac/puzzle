package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.jpa.entity.SourceTable;
import org.zhongmiao.puzzle.jpa.repository.SourceTableDao;
import org.zhongmiao.puzzle.jpa.repository.SourceColumnDao;
import org.zhongmiao.puzzle.lineage.LineageGraphDto;
import org.zhongmiao.puzzle.meta.*;
import org.zhongmiao.puzzle.MetaError;
import org.zhongmiao.puzzle.MetaException;

import java.util.List;

/**
 * Metadata Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MetadataRepo implements MetadataService {

    private final SourceTableDao sourceTableDao;
    private final SourceColumnDao sourceColumnDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncSchema(MetadataCmd.SyncSchema cmd) {
        // TODO: Implement schema synchronization
        log.info("Syncing schema for datasource: {}", cmd.getDatasourceId());
    }

    @Override
    public Page<SourceTableDto> listSourceTables(QueryRequest<MetadataQuery.QuerySourceTables> qry) {
        SourceTableDao.QuerySourceTable query = new SourceTableDao.QuerySourceTable();
        query.external(qry);
        return sourceTableDao.page(query).map(this::mapToDto);
    }

    @Override
    public SourceTableDto getSourceTable(MetadataQuery.GetSourceTable qry) {
        SourceTable entity = sourceTableDao.findById(qry.getTableId())
                .orElseThrow(() -> new MetaException(MetaError.SOURCE_TABLE_NOT_EXISTS));
        return mapToDto(entity);
    }

    @Override
    public List<SourceColumnDto> listColumns(MetadataQuery.ListColumns qry) {
        SourceColumnDao.QuerySourceColumn query = new SourceColumnDao.QuerySourceColumn();
        query.setSourceTableId(qry.getTableId());
        return sourceColumnDao.loads(query).stream()
                .map(this::mapColumnToDto)
                .toList();
    }

    @Override
    public LineageGraphDto getLineageGraph(MetadataQuery.QueryLineage qry) {
        // TODO: Implement lineage graph generation
        return new LineageGraphDto();
    }

    @Override
    public List<Object> searchMetadata(MetadataQuery.SearchMetadata qry) {
        // TODO: Implement metadata search
        return List.of();
    }

    private SourceTableDto mapToDto(SourceTable entity) {
        SourceTableDto dto = new SourceTableDto();
        dto.setId(entity.getId());
        dto.setDatasourceId(entity.getDatasourceId());
        dto.setSchemaName(entity.getSchemaName());
        dto.setTableName(entity.getTableName());
        return dto;
    }

    private SourceColumnDto mapColumnToDto(org.zhongmiao.puzzle.jpa.entity.SourceColumn entity) {
        SourceColumnDto dto = new SourceColumnDto();
        dto.setId(entity.getId());
        dto.setTableId(entity.getSourceTable().getId());
        dto.setColumnName(entity.getColumnName());
        dto.setColumnType(entity.getColumnType());
        dto.setColumnComment(entity.getDescription());
        dto.setNullable(entity.getIsNullable());
        dto.setPrimaryKey(entity.getIsPrimaryKey());
        return dto;
    }

}
