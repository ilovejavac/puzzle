package org.zhongmiao.puzzle.model;

import com.dev.lib.web.model.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型错误码
 */
@Getter
@AllArgsConstructor
public enum ModelError implements CodeEnums {

    MODEL_NOT_EXISTS(42001, "模型不存在"),
    MODEL_ALREADY_DEPLOYED(42002, "模型已部署"),
    MODEL_HAS_DEPENDENCIES(42003, "模型存在依赖关系"),
    FIELD_NOT_FOUND(42004, "字段不存在"),
    INVALID_JOIN_CONFIG(42005, "无效的关联配置"),
    METRIC_NOT_EXISTS(42006, "指标不存在"),
    DIMENSION_NOT_EXISTS(42007, "维度不存在");

    private final Integer code;
    private final String message;

}
