package org.zhongmiao.puzzle.model.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Table schema definition for output tables
 * Defines columns, table comment, and options
 */
@Data
public class TableSchema implements Serializable {

    /** List of column definitions */
    private List<ColumnDefinition> columns;

    /** Table comment */
    private String tableComment;

    /** Table options */
    private Map<String, String> options;

    /**
     * Column definition within a table schema
     */
    @Data
    public static class ColumnDefinition implements Serializable {

        /** Column name */
        private String name;

        /** Column data type */
        private String type;

        /** Column comment */
        private String comment;

        /** Whether column is nullable */
        private Boolean nullable;

    }

}
