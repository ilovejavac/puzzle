package org.zhongmiao.puzzle.query.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Route condition for query routing rules
 * Defines field, operator, value, and logical operator
 */
@Data
public class RouteCondition implements Serializable {

    /** Field name to evaluate */
    private String field;

    /** Comparison operator (e.g., "=", "!=", ">", "<", "IN", "CONTAINS") */
    private String operator;

    /** Value to compare against */
    private Object value;

    /** Logical operator (AND/OR) for combining conditions */
    private String logicOperator;

}
