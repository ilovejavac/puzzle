# Tenant Module - 数据库初始化说明

## 文件说明

### data.sql
- **用途**: 初始化数据脚本
- **执行时机**: 应用启动时自动执行（`spring.sql.init.mode: always`）
- **包含内容**:
  - 默认租户
  - 初始用户（admin, testuser）
  - 初始角色（SUPER_ADMIN, NORMAL_USER）
  - 系统权限（18个基础权限）
  - 用户-角色关联
  - 角色-权限关联

## 初始化数据详情

### 1. 租户数据
| ID | 租户名称 | 到期时间 |
|----|---------|---------|
| 1 | 系统默认租户 | 2099-12-31 |

### 2. 用户数据
| 用户名 | 密码 | 邮箱 | 角色 | 状态 |
|-------|------|------|------|------|
| admin | admin123 | admin@puzzle.com | SUPER_ADMIN | ACTIVE |
| testuser | admin123 | test@puzzle.com | NORMAL_USER | ACTIVE |

**⚠️ 重要提示**: 生产环境部署前必须修改默认密码！

### 3. 角色数据
| 角色编码 | 角色名称 | 描述 | 类型 |
|---------|---------|------|------|
| SUPER_ADMIN | 超级管理员 | 系统超级管理员，拥有所有权限 | SYSTEM |
| NORMAL_USER | 普通用户 | 系统普通用户，基础权限 | CUSTOM |

### 4. 权限分类
- **租户管理** (4个): tenant:query, tenant:create, tenant:update, tenant:delete
- **用户管理** (5个): user:query, user:create, user:update, user:delete, user:reset-password
- **角色管理** (5个): role:query, role:create, role:update, role:delete, role:assign-permission
- **权限管理** (1个): permission:query
- **认证权限** (3个): auth:login, auth:logout, auth:refresh

## 使用方式

### 1. 开发环境
直接启动应用，Spring Boot 会自动执行 data.sql：
```bash
mvn spring-boot:run -pl puzzle-tenant/tenant-server
```

### 2. 生产环境
建议步骤：
1. 修改 `data.sql` 中的默认密码
2. 使用加密后的 BCrypt 密码替换
3. 根据实际需求调整初始角色和权限
4. 部署后立即登录并修改 admin 密码

## 密码加密

初始化脚本使用 BCrypt 加密算法（`$2a$10$...`）。

生成加密密码的方式：
```java
String password = "admin123";
String encrypted = BCrypt.hashpw(password, BCrypt.gensalt());
```

或使用在线工具：https://bcrypt-generator.com/

## 数据库表结构

JPA 会自动创建以下表（`ddl-auto: update`）：
- `user_tenant` - 租户表
- `user_tbl` - 用户表
- `user_role` - 角色表
- `user_permission` - 权限表
- `user_role_rel` - 用户-角色关联表
- `user_role_permission` - 角色-权限关联表

## 自定义初始化

### 添加新租户
```sql
INSERT INTO user_tenant (id, tenant_name, expired_at, created_at, updated_at)
VALUES (2, '新租户名称', '2025-12-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

### 添加新用户
```sql
INSERT INTO user_tbl (biz_id, tenant_id, username, password, email, status, created_at, updated_at)
VALUES (
    'user-biz-id',
    'default-tenant',
    'newuser',
    '$2a$10$...' -- BCrypt加密后的密码
    'user@example.com',
    'ACTIVE',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
```

### 添加新角色
```sql
INSERT INTO user_role (biz_id, tenant_id, role_code, role_name, description, type, created_at, updated_at)
VALUES (
    'role-biz-id',
    'default-tenant',
    'ROLE_CODE',
    '角色名称',
    '角色描述',
    'CUSTOM',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
```

## 故障排查

### 1. data.sql 没有执行
检查 `application.yaml` 配置：
```yaml
spring:
  sql:
    init:
      mode: always  # 确保设置为 always
```

### 2. 插入失败：主键冲突
确保 `ON CONFLICT (id) DO NOTHING` 子句存在，避免重复插入。

### 3. 密码登录失败
- 确认密码使用 BCrypt 加密
- 检查 `user_tbl.status` 是否为 `ACTIVE`
- 查看日志确认认证流程

## 安全建议

1. **立即修改默认密码**
2. **启用强密码策略**
3. **定期审计用户权限**
4. **启用多因素认证（MFA）**
5. **记录所有审计日志**
