package org.zhongmiao.puzzle.model;

import lombok.Data;

/**
 * Model Query DTO
 */
@Data
public class ModelQuery {

    private Long        tenantId;

    private String      name;

    private ModelStatus status;

}
