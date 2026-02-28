package org.zhongmiao.puzzle.lineage;

import lombok.Data;

import java.util.List;

/**
 * 血缘图 DTO
 */
@Data
public class LineageGraphDto {

    private List<LineageNodeDto> nodes;
    private List<LineageEdgeDto> edges;

}
