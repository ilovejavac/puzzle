package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.jpa.entity.Datasource;
import org.zhongmiao.puzzle.jpa.repository.DatasourceDao;
import org.zhongmiao.puzzle.source.*;

import java.util.List;

/**
 * Datasource Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DatasourceRepo implements DatasourceService {

    private final DatasourceDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDatasource(DatasourceCmd.CreateDatasource cmd) {
        Datasource entity = new Datasource();
        entity.setSourceName(cmd.getName());
        entity.setDescription(cmd.getDescription());
        entity.setSourceType(cmd.getType());
        entity.setConnConfig(cmd.getConnConfig());
        entity.setStatus(org.zhongmiao.puzzle.source.enums.SourceStatus.ACTIVE);

        dao.save(entity);
        log.info("Created datasource: {}", cmd.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDatasource(DatasourceCmd.UpdateDatasource cmd) {
        Datasource entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new SourceException(SourceError.DATASOURCE_NOT_EXISTS));

        entity.setSourceName(cmd.getName());
        entity.setDescription(cmd.getDescription());
        entity.setConnConfig(cmd.getConnConfig());
        // JPA dirty checking will automatically save
        log.info("Updated datasource: {}", cmd.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDatasource(DatasourceCmd.DeleteDatasource cmd) {
        Datasource entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new SourceException(SourceError.DATASOURCE_NOT_EXISTS));
        dao.delete(entity);
        log.info("Deleted datasource: {}", cmd.getId());
    }

    @Override
    public Page<DatasourceDto.DatasourceList> listDatasources(QueryRequest<DatasourceQuery.QueryDatasource> qry) {
        DatasourceDao.QueryDatasource query = new DatasourceDao.QueryDatasource();
        query.external(qry);
        return dao.page(query).map(this::mapToListDto);
    }

    @Override
    public DatasourceDto.DatasourceDetail getDatasource(DatasourceQuery.GetDatasource qry) {
        Datasource entity = dao.findById(qry.getId())
                .orElseThrow(() -> new SourceException(SourceError.DATASOURCE_NOT_EXISTS));
        return mapToDetailDto(entity);
    }

    @Override
    public Boolean testConnection(DatasourceQuery.TestConnection qry) {
        // TODO: Implement actual connection test
        return true;
    }

    @Override
    public void syncSchema(DatasourceQuery.SyncSchema qry) {
        // TODO: Implement schema synchronization
        log.info("Syncing schema for datasource: {}", qry.getDatasourceId());
    }

    @Override
    public List<DatasourceDto.TableInfo> listTables(DatasourceQuery.ListTables qry) {
        // TODO: Implement table listing
        return List.of();
    }

    private DatasourceDto.DatasourceList mapToListDto(Datasource entity) {
        DatasourceDto.DatasourceList dto = new DatasourceDto.DatasourceList();
        dto.setId(entity.getId());
        dto.setName(entity.getSourceName());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getSourceType());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    private DatasourceDto.DatasourceDetail mapToDetailDto(Datasource entity) {
        DatasourceDto.DatasourceDetail dto = new DatasourceDto.DatasourceDetail();
        dto.setId(entity.getId());
        dto.setName(entity.getSourceName());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getSourceType());
        dto.setStatus(entity.getStatus());
        // Add connection details if needed
        return dto;
    }

}
