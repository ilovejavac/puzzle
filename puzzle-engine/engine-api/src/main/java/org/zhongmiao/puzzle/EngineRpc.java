package org.zhongmiao.puzzle;

import org.zhongmiao.puzzle.deploy.DeployDto;
import org.zhongmiao.puzzle.engine.EngineDto;

import java.util.List;

public interface EngineRpc {

    /**
     * 部署模型
     */
    DeployDto.Result deployModel(String model);

    void stopModel(String model);

    void restartModel(String model);

    /**
     * 任务状态查询
     */
    EngineDto.Task getTask(String task);

    List<EngineDto.Task> listModelTasks(String model);

    /**
     * 注册产出表
     */
    void registerOutputTable();

}
