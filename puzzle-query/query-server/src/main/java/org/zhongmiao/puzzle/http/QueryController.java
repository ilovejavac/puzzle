package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.query.query.QueryCmd;
import org.zhongmiao.puzzle.query.query.QueryDto;
import org.zhongmiao.puzzle.query.query.QueryLogDto;
import org.zhongmiao.puzzle.query.query.QueryLogQuery;
import org.zhongmiao.puzzle.query.query.QueryQuery;
import org.zhongmiao.puzzle.query.QueryService;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryCmd;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryDto;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryQuery;
import org.zhongmiao.puzzle.query.SavedQueryService;

import java.util.List;
import java.util.Map;

/**
 * 查询接口
 * <p>
 * 处理指标查询、明细查询、保存查询
 */
@RestController
@RequiredArgsConstructor
public class QueryController {

    private final QueryService queryService;
    private final SavedQueryService savedQueryService;

    // ==================== 指标查询 ====================

    /**
     * 执行指标查询
     * 需要权限[query:execute]
     */
    @RequirePermission("query:execute")
    @PostMapping("/api/query/execute-metric-query")
    public ServerResponse<Map<String, Object>> executeMetricQuery(@RequestBody QueryCmd.ExecuteMetricQuery cmd) {
        return ServerResponse.success(queryService.executeMetricQuery(cmd));
    }

    /**
     * 获取指标查询结果（分页）
     * 需要权限[query:execute]
     */
    @RequirePermission("query:execute")
    @PostMapping("/api/query/get-metric-result")
    public ServerResponse<QueryDto.MetricQueryResult> getMetricResult(
            @RequestBody QueryQuery.GetMetricResult qry) {
        return ServerResponse.success(queryService.getMetricResult(qry));
    }

    // ==================== 明细查询 ====================

    /**
     * 执行明细查询
     * 需要权限[query:execute]
     */
    @RequirePermission("query:execute")
    @PostMapping("/api/query/execute-detail-query")
    public ServerResponse<Map<String, Object>> executeDetailQuery(@RequestBody QueryCmd.ExecuteDetailQuery cmd) {
        return ServerResponse.success(queryService.executeDetailQuery(cmd));
    }

    /**
     * 获取明细查询结果（分页）
     * 需要权限[query:execute]
     */
    @RequirePermission("query:execute")
    @PostMapping("/api/query/get-detail-result")
    public ServerResponse<QueryDto.DetailQueryResult> getDetailResult(
            @RequestBody QueryQuery.GetDetailResult qry) {
        return ServerResponse.success(queryService.getDetailResult(qry));
    }

    // ==================== SQL 查询 ====================

    /**
     * 执行原生 SQL 查询
     * 需要权限[query:sql]
     */
    @RequirePermission("query:sql")
    @PostMapping("/api/query/execute-sql")
    public ServerResponse<Map<String, Object>> executeSql(@RequestBody QueryCmd.ExecuteSql cmd) {
        return ServerResponse.success(queryService.executeSql(cmd));
    }

    // ==================== 保存查询 ====================

    /**
     * 保存查询
     * 需要权限[query:save]
     */
    @RequirePermission("query:save")
    @PostMapping("/api/query/save-query")
    public ServerResponse<Void> saveQuery(@RequestBody SavedQueryCmd.SaveQuery cmd) {
        savedQueryService.saveQuery(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除保存的查询
     * 需要权限[query:delete]
     */
    @RequirePermission("query:delete")
    @PostMapping("/api/query/delete-saved-query")
    public ServerResponse<Void> deleteSavedQuery(@RequestBody SavedQueryCmd.DeleteSavedQuery cmd) {
        savedQueryService.deleteSavedQuery(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询保存的查询列表（分页）
     * 需要权限[query:list]
     */
    @RequirePermission("query:list")
    @PostMapping("/api/query/list-saved-queries")
    public ServerResponse<List<SavedQueryDto.SavedQueryList>> listSavedQueries(
            @RequestBody QueryRequest<SavedQueryQuery.QuerySavedQuery> qry) {
        return ServerResponse.success(savedQueryService.listSavedQueries(qry));
    }

    /**
     * 获取保存的查询详情
     * 需要权限[query:view]
     */
    @RequirePermission("query:view")
    @PostMapping("/api/query/get-saved-query")
    public ServerResponse<SavedQueryDto.SavedQueryDetail> getSavedQuery(
            @RequestBody SavedQueryQuery.GetSavedQuery qry) {
        return ServerResponse.success(savedQueryService.getSavedQuery(qry));
    }

    // ==================== 查询历史 ====================

    /**
     * 查询执行历史（分页）
     * 需要权限[query:history]
     */
    @RequirePermission("query:history")
    @PostMapping("/api/query/list-query-history")
    public ServerResponse<List<QueryLogDto.QueryLogList>> listQueryHistory(
            @RequestBody QueryRequest<QueryLogQuery.QueryHistory> qry) {
        return ServerResponse.success(queryService.listQueryHistory(qry));
    }

    /**
     * 获取查询统计
     * 需要权限[query:stats]
     */
    @RequirePermission("query:stats")
    @PostMapping("/api/query/get-query-stats")
    public ServerResponse<Map<String, Object>> getQueryStats(@RequestBody QueryLogQuery.GetStats qry) {
        return ServerResponse.success(queryService.getQueryStats(qry));
    }

}

