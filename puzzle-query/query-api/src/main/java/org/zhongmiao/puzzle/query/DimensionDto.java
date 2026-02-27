package org.zhongmiao.puzzle.query;

import lombok.Data;

/**
 * Dimension DTO
 */
@Data
public class DimensionDto {

    private Long   id;

    private String name;

    private String description;

    private String column;

    private String type;

    private Long   modelId;

}
