package org.zhongmiao.puzzle.model;

import lombok.Data;
import org.zhongmiao.puzzle.enums.AggFunction;
import org.zhongmiao.puzzle.enums.FieldRole;
import org.zhongmiao.puzzle.enums.FilterOperator;

/**
 * 模型字段 DTO
 */
@Data
public class ModelFieldDto {

    private Long id;
    private Long modelId;
    private Long sourceColumnId;
    private String fieldName;
    private String fieldType;
    private FieldRole fieldRole; // DIMENSION, MEASURE, ATTRIBUTE
    private AggFunction aggregator; // SUM, COUNT, AVG, MAX, MIN
    private String filterExpression;
    private FilterOperator filterOperator;
    private String filterValue;
    private Integer ordinalPosition;

}
