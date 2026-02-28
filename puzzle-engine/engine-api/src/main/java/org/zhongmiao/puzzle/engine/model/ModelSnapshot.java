package org.zhongmiao.puzzle.engine.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Model snapshot for versioning
 * Stores complete model configuration at a point in time
 */
@Data
public class ModelSnapshot implements Serializable {

    /** Model ID */
    private Long modelId;

    /** Model name */
    private String modelName;

    /** Model fields configuration */
    private List<FieldSnapshot> fields;

    /** Model joins configuration */
    private List<JoinSnapshot> joins;

    /** Primary source table ID */
    private Long primarySourceTableId;

    /** Model type */
    private String modelType;

    /** Assigned storage */
    private String assignedStorage;

    /** Assigned engine */
    private String assignedEngine;

    /**
     * Field snapshot within a model
     */
    @Data
    public static class FieldSnapshot implements Serializable {

        private Long sourceTableId;
        private String sourceColumnName;
        private String fieldAlias;
        private String fieldRole;
        private String aggFunction;
        private String filterOperator;
        private Object filterValues;
        private Boolean isComputed;
        private String computeExpr;
        private Integer ordinalPosition;

    }

    /**
     * Join snapshot within a model
     */
    @Data
    public static class JoinSnapshot implements Serializable {

        private Long joinTableId;
        private String joinType;
        private List<ConditionSnapshot> conditions;
        private Integer ordinal;

    }

    /**
     * Join condition snapshot
     */
    @Data
    public static class ConditionSnapshot implements Serializable {

        private Long leftFieldId;
        private Long rightFieldId;
        private String operator;

    }

}
