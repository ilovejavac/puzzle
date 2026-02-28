package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.enums.DimensionType;
import org.zhongmiao.puzzle.jpa.entity.Dimension;
import org.zhongmiao.puzzle.jpa.repository.DimensionDao;
import org.zhongmiao.puzzle.dimension.DimensionCmd;
import org.zhongmiao.puzzle.dimension.DimensionDto;
import org.zhongmiao.puzzle.dimension.DimensionQuery;
import org.zhongmiao.puzzle.model.DimensionService;
import org.zhongmiao.puzzle.model.ModelError;
import org.zhongmiao.puzzle.model.ModelException;

/**
 * Dimension Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DimensionRepo implements DimensionService {

    private final DimensionDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDimension(DimensionCmd.CreateDimension cmd) {
        Dimension entity = new Dimension();
        entity.setModelFieldId(cmd.getModelId());
        entity.setDimName(cmd.getDimensionName());
        entity.setDimCode(cmd.getDimensionCode());
        entity.setDimType(DimensionType.CATEGORICAL);

        dao.save(entity);
        log.info("Created dimension: {}", cmd.getDimensionName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDimension(DimensionCmd.UpdateDimension cmd) {
        Dimension entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new ModelException(ModelError.DIMENSION_NOT_EXISTS));

        entity.setDimName(cmd.getDimensionName());
        entity.setDimCode(cmd.getDimensionCode());
        entity.setModelFieldId(cmd.getSourceFieldId());
        log.info("Updated dimension: {}", cmd.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDimension(DimensionCmd.DeleteDimension cmd) {
        Dimension entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new ModelException(ModelError.DIMENSION_NOT_EXISTS));
        dao.delete(entity);
        log.info("Deleted dimension: {}", cmd.getId());
    }

    @Override
    public Page<DimensionDto.DimensionList> listDimensions(QueryRequest<DimensionQuery.QueryDimension> qry) {
        DimensionDao.QueryDimension query = new DimensionDao.QueryDimension();
        query.external(qry);
        return dao.page(query).map(this::mapToListDto);
    }

    private DimensionDto.DimensionList mapToListDto(Dimension entity) {
        DimensionDto.DimensionList dto = new DimensionDto.DimensionList();
        dto.setId(entity.getId());
        dto.setDimensionName(entity.getDimName());
        dto.setDimensionCode(entity.getDimCode());
        dto.setDimensionType(entity.getDimType());
        return dto;
    }

}
