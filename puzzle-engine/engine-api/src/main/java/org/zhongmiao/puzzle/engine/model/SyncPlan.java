package org.zhongmiao.puzzle.engine.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Data synchronization plan
 * Defines sync type, steps, and configuration
 */
@Data
public class SyncPlan implements Serializable {

    /** Sync type (e.g., "FULL", "INCREMENTAL") */
    private String syncType;

    /** List of sync steps */
    private List<SyncStep> steps;

    /** Additional sync configuration */
    private Map<String, Object> config;

    /**
     * Single step in a sync plan
     */
    @Data
    public static class SyncStep implements Serializable {

        /** Step name */
        private String name;

        /** Step type (e.g., "EXTRACT", "LOAD", "TRANSFORM") */
        private String type;

        /** Step configuration */
        private Map<String, Object> config;

    }

}
