package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.ai.AiQueryRequest;
import org.zhongmiao.puzzle.ai.AiQueryResponse;

/**
 * AI RPC Service
 */
public interface AiRpc {

    /**
     * Execute AI query
     */
    AiQueryResponse executeQuery(AiQueryRequest request);

    /**
     * Generate SQL from natural language
     */
    String generateSql(String prompt, Long tenantId);

    /**
     * Suggest metrics based on data
     */
    String suggestMetrics(Long datasourceId, String tableName);
}
