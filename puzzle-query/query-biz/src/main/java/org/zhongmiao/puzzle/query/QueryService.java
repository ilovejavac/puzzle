package org.zhongmiao.puzzle.query;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;
import org.zhongmiao.puzzle.query.query.*;

import java.util.Map;

/**
 * 查询服务接口
 */
public interface QueryService {

    /**
     * 执行指标查询
     */
    Map<String, Object> executeMetricQuery(QueryCmd.ExecuteMetricQuery cmd);

    /**
     * 获取指标查询结果（分页）
     */
    QueryDto.MetricQueryResult getMetricResult(QueryQuery.GetMetricResult qry);

    /**
     * 执行明细查询
     */
    Map<String, Object> executeDetailQuery(QueryCmd.ExecuteDetailQuery cmd);

    /**
     * 获取明细查询结果（分页）
     */
    QueryDto.DetailQueryResult getDetailResult(QueryQuery.GetDetailResult qry);

    /**
     * 执行原生 SQL 查询
     */
    Map<String, Object> executeSql(QueryCmd.ExecuteSql cmd);

    /**
     * 查询执行历史（分页）
     */
    Page<QueryLogDto.QueryLogList> listQueryHistory(QueryRequest<QueryLogQuery.QueryHistory> qry);

    /**
     * 获取查询统计
     */
    Map<String, Object> getQueryStats(QueryLogQuery.GetStats qry);

}
