package org.zhongmiao.puzzle.rpc;

import lombok.Data;

/**
 * 告警 RPC 服务
 * <p>
 * 供所有模块调用，触发告警
 */
public interface AlertRpc {

    /**
     * 触发告警
     *
     * @param alert 告警信息
     */
    void fire(Alert alert);

    /**
     * 解除告警
     *
     * @param alertId 告警 ID
     */
    void resolve(Long alertId);

    /**
     * 告警信息
     */
    @Data
    class Alert {
        /** 租户 ID */
        private Long tenantId;
        /** 告警类型 */
        private String alertType;
        /** 告警级别：INFO, WARNING, ERROR, CRITICAL */
        private String severity;
        /** 告警标题 */
        private String title;
        /** 告警内容 */
        private String content;
        /** 关联资源类型 */
        private String resourceType;
        /** 关联资源 ID */
        private Long resourceId;
        /** 关联资源名称 */
        private String resourceName;
        /** 扩展数据（JSON 格式） */
        private String extData;
    }

}
