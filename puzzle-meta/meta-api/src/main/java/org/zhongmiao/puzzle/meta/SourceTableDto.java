package org.zhongmiao.puzzle.meta;

import lombok.Data;

import java.util.List;

/**
 * 源表 DTO
 */
@Data
public class SourceTableDto {

    private Long id;
    private Long datasourceId;
    private String schemaName;
    private String tableName;
    private String tableComment;
    private List<SourceColumnDto> columns;

}
