package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.jpa.entity.SavedQuery;
import org.zhongmiao.puzzle.jpa.repository.SavedQueryDao;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryCmd;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryDto;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryQuery;
import org.zhongmiao.puzzle.query.SavedQueryService;
import org.zhongmiao.puzzle.query.QueryError;
import org.zhongmiao.puzzle.query.QueryException;

/**
 * Saved Query Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SavedQueryRepo implements SavedQueryService {

    private final SavedQueryDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuery(SavedQueryCmd.SaveQuery cmd) {
        SavedQuery entity = new SavedQuery();
        entity.setQueryName(cmd.getQueryName());
        entity.setQueryType(cmd.getQueryType());
        entity.setMetricIds(cmd.getMetricIds());
        entity.setDimensionIds(cmd.getDimensionIds());

        dao.save(entity);
        log.info("Saved query: {}", cmd.getQueryName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSavedQuery(SavedQueryCmd.DeleteSavedQuery cmd) {
        SavedQuery entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new QueryException(QueryError.SAVED_QUERY_NOT_EXISTS));
        dao.delete(entity);
        log.info("Deleted saved query: {}", cmd.getId());
    }

    @Override
    public Page<SavedQueryDto.SavedQueryList> listSavedQueries(QueryRequest<SavedQueryQuery.QuerySavedQuery> qry) {
        SavedQueryDao.QuerySavedQuery query = new SavedQueryDao.QuerySavedQuery();
        query.external(qry);
        return dao.page(query).map(this::mapToListDto);
    }

    @Override
    public SavedQueryDto.SavedQueryDetail getSavedQuery(SavedQueryQuery.GetSavedQuery qry) {
        SavedQuery entity = dao.findById(qry.getId())
                .orElseThrow(() -> new QueryException(QueryError.SAVED_QUERY_NOT_EXISTS));
        return mapToDetailDto(entity);
    }

    private SavedQueryDto.SavedQueryList mapToListDto(SavedQuery entity) {
        SavedQueryDto.SavedQueryList dto = new SavedQueryDto.SavedQueryList();
        dto.setId(entity.getId());
        dto.setQueryName(entity.getQueryName());
        dto.setQueryType(entity.getQueryType());
        return dto;
    }

    private SavedQueryDto.SavedQueryDetail mapToDetailDto(SavedQuery entity) {
        SavedQueryDto.SavedQueryDetail dto = new SavedQueryDto.SavedQueryDetail();
        dto.setId(entity.getId());
        dto.setQueryName(entity.getQueryName());
        dto.setQueryType(entity.getQueryType());
        dto.setMetricIds(entity.getMetricIds());
        dto.setDimensionIds(entity.getDimensionIds());
        return dto;
    }

}
