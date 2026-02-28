package org.zhongmiao.puzzle.dimension;

import lombok.Data;
import org.zhongmiao.puzzle.enums.DimensionType;

/**
 * 维度 DTO
 */
@Data
public class DimensionDto {

    private DimensionDto() {

    }

    /**
     * 维度列表项
     */
    @Data
    public static class DimensionList {
        private Long id;
        private String dimensionName;
        private String dimensionCode;
        private DimensionType dimensionType;
    }

    /**
     * 维度详情
     */
    @Data
    public static class DimensionDetail extends DimensionList {
        private Long modelId;
        private Long sourceFieldId;
        private String description;
    }

    /**
     * 维度完整定义（包含模型和字段信息）
     */
    @Data
    public static class DimensionFull extends DimensionDetail {
        private String modelName;
        private String sourceTableName;
        private String sourceFieldName;
        private String sourceFieldType;
    }

}
