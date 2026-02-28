package org.zhongmiao.puzzle.rpc;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 审计日志 RPC 服务
 * <p>
 * 供所有模块调用，记录审计日志
 */
public interface AuditRpc {

    /**
     * 记录审计日志
     *
     * @param auditLog 审计日志
     */
    void log(AuditLog auditLog);

    /**
     * 批量记录审计日志
     *
     * @param auditLogs 审计日志列表
     */
    void batchLog(List<AuditLog> auditLogs);

    /**
     * 审计日志
     */
    @Data
    class AuditLog {
        /** 租户 ID */
        private Long tenantId;
        /** 用户 ID */
        private Long userId;
        /** 用户名 */
        private String username;
        /** 操作模块 */
        private String module;
        /** 操作类型 */
        private String operationType;
        /** 操作描述 */
        private String description;
        /** 请求路径 */
        private String requestPath;
        /** 请求方法 */
        private String requestMethod;
        /** 请求参数 */
        private String requestParams;
        /** 响应状态 */
        private Integer responseStatus;
        /** 响应时间（毫秒） */
        private Long responseTime;
        /** 客户端 IP */
        private String clientIp;
        /** 用户代理 */
        private String userAgent;
        /** 操作时间 */
        private LocalDateTime operatedAt;
    }

}
