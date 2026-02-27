-- =====================================================
-- Puzzle DW - Tenant Module Initial Data
-- 自动创建初始租户、用户、角色
-- =====================================================

-- =====================================================
-- 1. 创建初始租户
-- =====================================================
INSERT INTO user_tenant (id, biz_id, tenant_name, expired_at, deleted, created_at, updated_at)
VALUES (
    1,
    'sys-tenant',
    '系统默认租户',
    '2099-12-31 23:59:59',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
)
ON CONFLICT (id) DO NOTHING;

-- =====================================================
-- 2. 创建初始用户
-- 密码说明：使用 BCrypt 加密
-- admin: admin123 (生产环境必须修改)
-- =====================================================
INSERT INTO user_tbl (id, biz_id, tenant_id, username, password, email, phone, status, deleted, created_at, updated_at, creator_id, modifier_id)
VALUES
    (
        1,
        'admin-usr',
        1,
        'admin',
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', -- BCrypt(admin123)
        'admin@puzzle.com',
        '13800138000',
        'ACTIVE',
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        0,
        0
    ),
    (
        2,
        'test-usr',
        1,
        'testuser',
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', -- BCrypt(admin123)
        'test@puzzle.com',
        '13800138001',
        'ACTIVE',
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        0,
        0
    )
ON CONFLICT (id) DO NOTHING;

-- =====================================================
-- 3. 创建初始角色
-- =====================================================
INSERT INTO user_role (id, biz_id, tenant_id, role_code, role_name, description, type, deleted, created_at, updated_at, creator_id, modifier_id)
VALUES
    (
        1,
        'admin-role',
        1,
        'SUPER_ADMIN',
        '超级管理员',
        '系统超级管理员，拥有所有权限',
        1,
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        0,
        0
    ),
    (
        2,
        'user-role',
        1,
        'NORMAL_USER',
        '普通用户',
        '系统普通用户，基础权限',
        0,
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        0,
        0
    )
ON CONFLICT (id) DO NOTHING;

-- =====================================================
-- 4. 用户-角色关联
-- =====================================================
INSERT INTO user_role_rel (user_id, role_id)
VALUES
    (1, 1), -- admin -> SUPER_ADMIN
    (2, 2)  -- testuser -> NORMAL_USER
ON CONFLICT (user_id, role_id) DO NOTHING;

-- =====================================================
-- 初始化完成
-- =====================================================
-- 默认登录账号：
-- 管理员: admin / admin123
-- 测试用户: testuser / admin123
-- 注意：权限由应用启动时自动注册，不在此初始化
-- =====================================================
