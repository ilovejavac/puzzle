package org.zhongmiao.puzzle.meta;

import lombok.Data;

import java.util.List;

/**
 * 血缘图节点 DTO
 */
@Data
public class LineageNodeDto {

    private String id;
    private String name;
    private String type; // SOURCE_TABLE, MODEL, METRIC, WAREHOUSE_TABLE
    private String database;
    private String table;

}
