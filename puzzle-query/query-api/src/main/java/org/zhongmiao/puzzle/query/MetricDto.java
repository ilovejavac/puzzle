package org.zhongmiao.puzzle.query;

import lombok.Data;

/**
 * Metric DTO
 */
@Data
public class MetricDto {

    private Long   id;

    private String name;

    private String description;

    private String aggregation;

    private String column;

    private Long   modelId;

}
