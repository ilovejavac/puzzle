package org.zhongmiao.puzzle.lineage;

import lombok.Data;

/**
 * 血缘图边 DTO
 */
@Data
public class LineageEdgeDto {

    private String from;
    private String to;
    private String transformType; // DIRECT, AGGREGATION, FILTER, JOIN, DERIVATION

}
