package org.zhongmiao.puzzle.model;

import org.zhongmiao.puzzle.model.ModelCmd;
import org.zhongmiao.puzzle.model.ModelDto;
import org.zhongmiao.puzzle.model.ModelQuery;
import org.zhongmiao.puzzle.model.ModelStatus;

import java.util.List;

/**
 * Model Service
 */
public interface ModelService {

    /**
     * Create model
     */
    Long createModel(ModelCmd cmd);

    /**
     * Update model
     */
    void updateModel(Long id, ModelCmd cmd);

    /**
     * Delete model
     */
    void deleteModel(Long id);

    /**
     * Get model by id
     */
    ModelDto getModel(Long id);

    /**
     * Query models
     */
    List<ModelDto> queryModels(ModelQuery query);

    /**
     * Deploy model
     */
    void deployModel(Long modelId);

    /**
     * Stop model
     */
    void stopModel(Long modelId);

    /**
     * Update model status
     */
    void updateModelStatus(Long modelId, ModelStatus status);
}
