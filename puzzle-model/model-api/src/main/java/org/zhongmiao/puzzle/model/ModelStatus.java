package org.zhongmiao.puzzle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelStatus {
    DRAFT,      // 草稿
    DEPLOYING,  // 部署中
    RUNNING,    // 运行中
    STOPPED,    // 已停止
    FAILED;     // 失败
}