package org.zhongmiao.puzzle.engine.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Computation plan for model execution
 * Defines compute steps, output format, and configuration
 */
@Data
public class ComputePlan implements Serializable {

    /** List of compute steps */
    private List<ComputeStep> steps;

    /** Output format (e.g., "PARQUET", "ORC", "JSON") */
    private String outputFormat;

    /** Additional compute configuration */
    private Map<String, Object> config;

    /**
     * Single step in a compute plan
     */
    @Data
    public static class ComputeStep implements Serializable {

        /** Step name */
        private String name;

        /** Step type (e.g., "AGGREGATE", "JOIN", "FILTER") */
        private String type;

        /** Step configuration */
        private Map<String, Object> config;

    }

}
