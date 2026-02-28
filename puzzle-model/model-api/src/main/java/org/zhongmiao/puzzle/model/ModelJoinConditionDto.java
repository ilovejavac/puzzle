package org.zhongmiao.puzzle.model;

import lombok.Data;

/**
 * 模型关联条件 DTO
 */
@Data
public class ModelJoinConditionDto {

    private Long id;
    private Long modelJoinId;
    private Long leftFieldId;
    private Long rightFieldId;
    private String operator; // =, !=, >, <, >=, <=

}
