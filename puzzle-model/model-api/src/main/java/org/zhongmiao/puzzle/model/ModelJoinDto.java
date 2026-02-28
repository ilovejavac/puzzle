package org.zhongmiao.puzzle.model;

import lombok.Data;
import org.zhongmiao.puzzle.enums.JoinType;

import java.util.List;

/**
 * 模型关联 DTO
 */
@Data
public class ModelJoinDto {

    private Long id;
    private Long modelId;
    private Long joinTableId;
    private JoinType joinType; // INNER, LEFT, RIGHT, FULL
    private Integer ordinal;
    private List<ModelJoinConditionDto> conditions;

}
