package org.zhongmiao.puzzle.meta;

import lombok.Data;

/**
 * Source Table DTO
 */
@Data
public class SourceTableDto {

    private Long id;
    private Long datasourceId;
    private String schemaName;
    private String tableName;
    private String tableComment;
    private java.util.List<SourceColumnDto> columns;
}
