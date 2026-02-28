package org.zhongmiao.puzzle.model;

import lombok.Data;

/**
 * 模型命令对象
 */
@Data
public class ModelCmd {

    private ModelCmd() {

    }

    @Data
    public static class CreateModel {
        private String modelName;
        private String description;
        private Long primarySourceTableId;
    }

    @Data
    public static class UpdateModel extends CreateModel {
        private Long id;
    }

    @Data
    public static class DeleteModel {
        private Long id;
    }

    @Data
    public static class DeployModel {
        private Long modelId;
    }

    @Data
    public static class StopModel {
        private Long modelId;
    }

    @Data
    public static class AddFields {
        private Long modelId;
        private java.util.List<ModelFieldDto> fields;
    }

    @Data
    public static class RemoveFields {
        private Long modelId;
        private java.util.List<Long> fieldIds;
    }

    @Data
    public static class AddJoin {
        private Long modelId;
        private ModelJoinDto join;
    }

    @Data
    public static class RemoveJoin {
        private Long modelId;
        private Long joinId;
    }

}
