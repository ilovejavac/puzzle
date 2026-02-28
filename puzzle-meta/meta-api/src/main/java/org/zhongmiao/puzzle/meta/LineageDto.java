package org.zhongmiao.puzzle.meta;

import lombok.Data;
import org.zhongmiao.puzzle.meta.enums.LineageEntityType;
import org.zhongmiao.puzzle.meta.enums.LineageTransformType;

import java.time.LocalDateTime;
import java.util.List;

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
