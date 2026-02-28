package org.zhongmiao.puzzle.model.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Storage configuration for models
 * Defines format, properties, and replication settings
 */
@Data
public class StorageConfig implements Serializable {

    /** Storage format (e.g., "parquet", "orc") */
    private String format;

    /** Storage properties */
    private Map<String, String> properties;

    /** Number of replications */
    private Integer replications;

}
