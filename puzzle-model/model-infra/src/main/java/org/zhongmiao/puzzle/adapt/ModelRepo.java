package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.enums.ModelStatus;
import org.zhongmiao.puzzle.jpa.entity.Model;
import org.zhongmiao.puzzle.jpa.repository.ModelDao;
import org.zhongmiao.puzzle.jpa.repository.ModelFieldDao;
import org.zhongmiao.puzzle.jpa.repository.ModelJoinDao;
import org.zhongmiao.puzzle.model.*;
import org.zhongmiao.puzzle.model.ModelError;
import org.zhongmiao.puzzle.model.ModelException;

/**
 * Model Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ModelRepo implements ModelService {

    private final ModelDao dao;
    private final ModelFieldDao modelFieldDao;
    private final ModelJoinDao modelJoinDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createModel(ModelCmd.CreateModel cmd) {
        Model entity = new Model();
        entity.setModelName(cmd.getModelName());
        entity.setDescription(cmd.getDescription());
        entity.setPrimarySourceTableId(cmd.getPrimarySourceTableId());
        entity.setStatus(ModelStatus.DRAFT);
        entity.setVersion(1);

        dao.save(entity);
        log.info("Created model: {}", cmd.getModelName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateModel(ModelCmd.UpdateModel cmd) {
        Model entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new ModelException(ModelError.MODEL_NOT_EXISTS));

        entity.setModelName(cmd.getModelName());
        entity.setDescription(cmd.getDescription());
        log.info("Updated model: {}", cmd.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteModel(ModelCmd.DeleteModel cmd) {
        Model entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new ModelException(ModelError.MODEL_NOT_EXISTS));
        dao.delete(entity);
        log.info("Deleted model: {}", cmd.getId());
    }

    @Override
    public Page<ModelDto.ModelList> listModels(QueryRequest<ModelQuery.QueryModel> qry) {
        ModelDao.QueryModel query = new ModelDao.QueryModel();
        query.external(qry);
        return dao.page(query).map(this::mapToListDto);
    }

    @Override
    public ModelDto.ModelDetail getModel(ModelQuery.GetModel qry) {
        Model entity = dao.findById(qry.getId())
                .orElseThrow(() -> new ModelException(ModelError.MODEL_NOT_EXISTS));
        return mapToDetailDto(entity);
    }

    @Override
    public ModelDto.ModelFull getModelFull(ModelQuery.GetModel qry) {
        Model entity = dao.findById(qry.getId())
                .orElseThrow(() -> new ModelException(ModelError.MODEL_NOT_EXISTS));
        // TODO: Load joins and fields
        return mapToFullDto(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deployModel(ModelCmd.DeployModel cmd) {
        Model entity = dao.findById(cmd.getModelId())
                .orElseThrow(() -> new ModelException(ModelError.MODEL_NOT_EXISTS));
        entity.setStatus(ModelStatus.DEPLOYING);
        log.info("Deploying model: {}", cmd.getModelId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopModel(ModelCmd.StopModel cmd) {
        Model entity = dao.findById(cmd.getModelId())
                .orElseThrow(() -> new ModelException(ModelError.MODEL_NOT_EXISTS));
        entity.setStatus(ModelStatus.STOPPED);
        log.info("Stopped model: {}", cmd.getModelId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFields(ModelCmd.AddFields cmd) {
        // TODO: Implement batch field creation
        log.info("Adding {} fields to model: {}", cmd.getFields().size(), cmd.getModelId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeFields(ModelCmd.RemoveFields cmd) {
        // TODO: Implement batch field deletion
        log.info("Removing {} fields from model: {}", cmd.getFieldIds().size(), cmd.getModelId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addJoin(ModelCmd.AddJoin cmd) {
        // TODO: Implement join creation
        log.info("Adding join to model: {}", cmd.getModelId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeJoin(ModelCmd.RemoveJoin cmd) {
        // TODO: Implement join deletion
        log.info("Removing join {} from model: {}", cmd.getJoinId(), cmd.getModelId());
    }

    private ModelDto.ModelList mapToListDto(Model entity) {
        ModelDto.ModelList dto = new ModelDto.ModelList();
        dto.setId(entity.getId());
        dto.setModelName(entity.getModelName());
        dto.setModelType(entity.getModelType());
        dto.setStatus(entity.getStatus());
        dto.setAssignedEngine(entity.getAssignedEngine());
        dto.setAssignedStorage(entity.getAssignedStorage());
        return dto;
    }

    private ModelDto.ModelDetail mapToDetailDto(Model entity) {
        ModelDto.ModelDetail dto = new ModelDto.ModelDetail();
        dto.setId(entity.getId());
        dto.setModelName(entity.getModelName());
        dto.setModelType(entity.getModelType());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setPrimarySourceTableId(entity.getPrimarySourceTableId());
        dto.setAssignedStorageTable(entity.getAssignedStorageTable());
        dto.setVersion(entity.getVersion());
        return dto;
    }

    private ModelDto.ModelFull mapToFullDto(Model entity) {
        ModelDto.ModelFull dto = new ModelDto.ModelFull();
        dto.setId(entity.getId());
        dto.setModelName(entity.getModelName());
        dto.setModelType(entity.getModelType());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setPrimarySourceTableId(entity.getPrimarySourceTableId());
        dto.setAssignedStorageTable(entity.getAssignedStorageTable());
        dto.setVersion(entity.getVersion());
        // TODO: Add fields and joins
        return dto;
    }

}
