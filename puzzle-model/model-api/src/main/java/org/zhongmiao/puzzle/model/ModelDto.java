package org.zhongmiao.puzzle.model;

import lombok.Data;
import org.zhongmiao.puzzle.enums.AssignedEngine;
import org.zhongmiao.puzzle.enums.AssignedStorage;
import org.zhongmiao.puzzle.enums.ModelStatus;
import org.zhongmiao.puzzle.enums.ModelType;
import org.zhongmiao.puzzle.metric.MetricDto;
import org.zhongmiao.puzzle.dimension.DimensionDto;

import java.util.List;

/**
 * 模型 DTO
 */
@Data
public class ModelDto {

    private ModelDto() {

    }

    /**
     * 模型列表项
     */
    @Data
    public static class ModelList {
        private Long id;
        private String modelName;
        private ModelType modelType;
        private ModelStatus status;
        private AssignedEngine assignedEngine;
        private AssignedStorage assignedStorage;
    }

    /**
     * 模型详情
     */
    @Data
    public static class ModelDetail extends ModelList {
        private String description;
        private Long primarySourceTableId;
        private String assignedStorageTable;
        private Integer version;
    }

    /**
     * 模型完整定义（包含关联和字段）
     */
    @Data
    public static class ModelFull extends ModelDetail {
        private List<ModelFieldDto> fields;
        private List<ModelJoinDto> joins;
        private List<MetricDto.MetricDetail> metrics;
        private List<DimensionDto.DimensionDetail> dimensions;
        private OutputTableDto outputTable;
    }

}
