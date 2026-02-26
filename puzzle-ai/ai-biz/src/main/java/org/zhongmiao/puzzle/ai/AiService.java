package org.zhongmiao.puzzle.ai;

import org.zhongmiao.puzzle.ai.AiQueryRequest;
import org.zhongmiao.puzzle.ai.AiQueryResponse;

/**
 * AI Service
 */
public interface AiService {

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
