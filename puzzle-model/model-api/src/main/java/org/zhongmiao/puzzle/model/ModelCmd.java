package org.zhongmiao.puzzle.model;

import lombok.Data;

import java.util.List;

/**
 * Model Command DTO
 */
@Data
public class ModelCmd {

    private String name;
    private String description;
    private Long datasourceId;
    private String sourceTable;
    private List<String> selectedColumns;
    private List<MetricDefinition> metrics;
    private List<DimensionDefinition> dimensions;

    @Data
    public static class MetricDefinition {
        private String name;
        private String aggregation;
        private String column;
    }

    @Data
    public static class DimensionDefinition {
        private String name;
        private String column;
        private String type;
    }
}
