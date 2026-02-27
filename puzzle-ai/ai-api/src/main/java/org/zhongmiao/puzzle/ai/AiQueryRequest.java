package org.zhongmiao.puzzle.ai;

import lombok.Data;

/**
 * AI Query Request DTO
 */
@Data
public class AiQueryRequest {

    private String  prompt;

    private String  context;

    private Integer maxTokens;

}
