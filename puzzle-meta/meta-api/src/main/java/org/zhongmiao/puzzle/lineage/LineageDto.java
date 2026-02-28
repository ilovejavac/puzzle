package org.zhongmiao.puzzle.lineage;

import lombok.Data;
import org.zhongmiao.puzzle.enums.LineageEntityType;
import org.zhongmiao.puzzle.enums.LineageTransformType;

import java.time.LocalDateTime;

/**
 * 血缘关系 DTO
 */
@Data
public class LineageDto {

    private Long id;
    private Long tenantId;
    private LineageEntityType entityType;
    private Long entityId;
    private String entityName;
    private LineageTransformType transformType;
    private String fromEntities; // JSON 数组
    private String toEntity;
    private LocalDateTime createdAt;

}
