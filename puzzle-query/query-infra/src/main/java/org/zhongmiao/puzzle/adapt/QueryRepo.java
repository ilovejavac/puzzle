package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.zhongmiao.puzzle.jpa.entity.QueryLog;
import org.zhongmiao.puzzle.jpa.repository.QueryLogDao;
import org.zhongmiao.puzzle.query.QueryService;
import org.zhongmiao.puzzle.query.query.*;

import java.util.Map;

/**
 * Query Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class QueryRepo implements QueryService {

    private final QueryLogDao queryLogDao;

    @Override
    public Map<String, Object> executeMetricQuery(QueryCmd.ExecuteMetricQuery cmd) {
        // TODO: Implement metric query execution
        log.info("Executing metric query with metrics: {}", cmd.getMetricIds());
        return Map.of();
    }

    @Override
    public QueryDto.MetricQueryResult getMetricResult(QueryQuery.GetMetricResult qry) {
        // TODO: Implement metric result retrieval
        return new QueryDto.MetricQueryResult();
    }

    @Override
    public Map<String, Object> executeDetailQuery(QueryCmd.ExecuteDetailQuery cmd) {
        // TODO: Implement detail query execution
        log.info("Executing detail query for model: {}", cmd.getModelId());
        return Map.of();
    }

    @Override
    public QueryDto.DetailQueryResult getDetailResult(QueryQuery.GetDetailResult qry) {
        // TODO: Implement detail result retrieval
        return new QueryDto.DetailQueryResult();
    }

    @Override
    public Map<String, Object> executeSql(QueryCmd.ExecuteSql cmd) {
        // TODO: Implement SQL execution
        log.info("Executing SQL: {}", cmd.getSql());
        return Map.of();
    }

    @Override
    public Page<QueryLogDto.QueryLogList> listQueryHistory(QueryRequest<QueryLogQuery.QueryHistory> qry) {
        QueryLogDao.QueryQueryLog query = new QueryLogDao.QueryQueryLog();
        query.external(qry);
        return queryLogDao.page(query).map(this::mapToListDto);
    }

    @Override
    public Map<String, Object> getQueryStats(QueryLogQuery.GetStats qry) {
        // TODO: Implement query statistics
        return Map.of();
    }

    private QueryLogDto.QueryLogList mapToListDto(QueryLog entity) {
        QueryLogDto.QueryLogList dto = new QueryLogDto.QueryLogList();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setModelId(entity.getModelId());
        dto.setQueryType(entity.getTargetEngine());
        dto.setStatus(entity.getStatus());
        dto.setExecutionTime(entity.getElapsedMs());
        dto.setRowsReturned(entity.getRowsReturned());
        return dto;
    }

}
