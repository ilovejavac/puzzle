package org.zhongmiao.puzzle.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EngineTaskStatus {
    CREATED,     // 已创建
    SUBMITTING,  // 提交中
    RUNNING,     // 运行中
    SUSPENDED,   // 已暂停
    SUCCESS,     // 成功
    FAILED,      // 失败
    CANCELLED;   // 已取消
}