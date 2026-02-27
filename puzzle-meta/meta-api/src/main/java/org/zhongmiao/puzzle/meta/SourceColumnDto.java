package org.zhongmiao.puzzle.meta;

import lombok.Data;

/**
 * Source Column DTO
 */
@Data
public class SourceColumnDto {

    private Long    id;

    private Long    tableId;

    private String  columnName;

    private String  columnType;

    private String  columnComment;

    private Boolean nullable;

    private Boolean primaryKey;

}
