package org.zhongmiao.puzzle.engine;

import org.zhongmiao.puzzle.deploy.DeployDto;

/**
 * Engine Service
 */
public interface EngineService {

    /**
     * Deploy model
     */
    void deployModel(Long modelId, DeployDto deployDto);

    /**
     * Stop model
     */
    void stopModel(Long modelId);

    /**
     * Restart model
     */
    void restartModel(Long modelId);

    /**
     * Get engine task status
     */
    EngineDto getTaskStatus(Long modelId);
}
