package org.zhongmiao.puzzle.dimension;

import lombok.Data;

/**
 * 维度命令对象
 */
@Data
public class DimensionCmd {

    private DimensionCmd() {

    }

    @Data
    public static class CreateDimension {
        private Long modelId;
        private String dimensionName;
        private String dimensionCode;
        private Long sourceFieldId;
    }

    @Data
    public static class UpdateDimension extends CreateDimension {
        private Long id;
    }

    @Data
    public static class DeleteDimension {
        private Long id;
    }

}
