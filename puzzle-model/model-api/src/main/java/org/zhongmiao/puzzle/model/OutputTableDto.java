package org.zhongmiao.puzzle.model;

import lombok.Data;
import org.zhongmiao.puzzle.enums.OutputStorageType;

/**
 * 输出表 DTO
 */
@Data
public class OutputTableDto {

    private Long id;
    private Long modelId;
    private String tableName;
    private OutputStorageType storageType; // CLICKHOUSE, ICEBERG, KAFKA
    private String storagePath;
    private String tableComment;
    private Integer partitionCount;

}
