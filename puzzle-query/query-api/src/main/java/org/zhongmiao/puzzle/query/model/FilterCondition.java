package org.zhongmiao.puzzle.query.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Filter condition for queries
 * Defines field, operator, and value for filtering
 */
@Data
public class FilterCondition implements Serializable {

    /** Field name to filter on */
    private String field;

    /** Operator (e.g., "=", "!=", ">", "<", "IN", "LIKE", "BETWEEN") */
    private String operator;

    /** Filter value (can be single value or array) */
    private Object value;

}
