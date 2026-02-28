package org.zhongmiao.puzzle.meta;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Partition specification for warehouse tables
 * Defines partition keys, format, and options
 */
@Data
public class PartitionSpec implements Serializable {

    /** List of partition key columns */
    private List<String> partitionKeys;

    /** Partition format (e.g., "hive", "identity") */
    private String format;

    /** Additional partition options */
    private Map<String, Object> options;

}
