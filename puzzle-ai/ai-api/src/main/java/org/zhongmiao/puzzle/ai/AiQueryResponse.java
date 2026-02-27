package org.zhongmiao.puzzle.ai;

import lombok.Data;

/**
 * AI Query Response DTO
 */
@Data
public class AiQueryResponse {

    private String  answer;

    private String  sql;

    private Boolean success;

    private String  errorMessage;

}
