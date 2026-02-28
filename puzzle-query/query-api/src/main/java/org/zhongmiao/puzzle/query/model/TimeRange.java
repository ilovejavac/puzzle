package org.zhongmiao.puzzle.query.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Time range for queries
 * Defines time field and range for query execution
 */
@Data
public class TimeRange implements Serializable {

    /** Field name for time filtering */
    private String field;

    /** Time unit (e.g., "DAY", "HOUR", "MINUTE") */
    private String unit;

    /** Time value (e.g., last N days/hours) */
    private Integer value;

    /** Range start (ISO format or expression) */
    private String start;

    /** Range end (ISO format or expression) */
    private String end;

}
