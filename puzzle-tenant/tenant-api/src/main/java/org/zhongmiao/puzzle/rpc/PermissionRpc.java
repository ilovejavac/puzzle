package org.zhongmiao.puzzle.rpc;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限 RPC 服务
 * <p>
 * 供所有模块调用，进行权限校验和数据脱敏配置获取
 */
public interface PermissionRpc {

    /**
     * 校验用户是否拥有指定权限
     *
     * @param userId          用户 ID
     * @param permissionCodes 权限代码列表
     * @return 权限校验结果
     */
    PermissionCheckResult checkPermissions(Long userId, Set<String> permissionCodes);

    /**
     * 校验用户是否拥有指定角色
     *
     * @param userId    用户 ID
     * @param roleCodes 角色代码列表
     * @return 是否拥有角色
     */
    Boolean hasRoles(Long userId, Set<String> roleCodes);

    /**
     * 获取用户的数据脱敏配置
     * <p>
     * 用于查询模块在返回结果时对敏感字段进行脱敏处理
     *
     * @param userId   用户 ID
     * @param tenantId 租户 ID
     * @return 数据脱敏配置
     */
    DataMaskConfig getDataMask(Long userId, Long tenantId);

    /**
     * 校验用户是否有权访问指定资源
     *
     * @param userId       用户 ID
     * @param resourceType 资源类型
     * @param resourceId   资源 ID
     * @return 是否有权访问
     */
    Boolean hasResourceAccess(Long userId, String resourceType, Long resourceId);

    /**
     * 权限校验结果
     */
    @Data
    class PermissionCheckResult {
        /** 是否有权限 */
        private Boolean hasPermission;
        /** 缺失的权限列表 */
        private Set<String> missingPermissions;
    }

    /**
     * 数据脱敏配置
     */
    @Data
    class DataMaskConfig {
        /** 是否启用行级过滤 */
        private Boolean rowFilterEnabled;
        /** 行过滤条件表达式 */
        private String rowFilterExpression;
        /** 列脱敏规则：key=列名, value=脱敏类型 */
        private Map<String, String> columnMaskRules;
    }

}
