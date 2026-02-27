package org.zhongmiao.puzzle.query;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Query Result DTO
 */
@Data
public class QueryDto {

    private Boolean                   success;

    private String                    errorMessage;

    private List<Map<String, Object>> data;

    private Long                      total;

    private Long                      executionTime;

}
