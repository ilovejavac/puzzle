package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zhongmiao.puzzle.user.UserCmd;
import org.zhongmiao.puzzle.user.UserDto;
import org.zhongmiao.puzzle.user.UserQuery;
import org.zhongmiao.puzzle.user.UserService;

import java.util.List;

/**
 * 用户接口
 * <p>
 * 管理用户信息
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 创建用户
     */
    @RequirePermission("user:create")
    @PostMapping("/api/user/create-user")
    public ServerResponse<Void> createUser(@RequestBody UserCmd.CreateUser cmd) {

        userService.createUser(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改用户
     */
    @RequirePermission("user:update")
    @PutMapping("/api/user/update-user")
    public ServerResponse<Void> updateUser(@RequestBody UserCmd.UpdateUser cmd) {

        userService.updateUser(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除用户
     */
    @RequirePermission("user:delete")
    @DeleteMapping("/api/user/delete-user")
    public ServerResponse<Void> deleteUser(@RequestBody List<String> ids) {

        userService.deleteUsers(ids);
        return ServerResponse.ok();
    }

    /**
     * 用户列表（分页）
     */
    @RequirePermission("user:list")
    @PostMapping("/api/user/list-user")
    public ServerResponse<List<UserDto.UserList>> listUser(@RequestBody QueryRequest<UserQuery.QueryUser> qry) {

        return ServerResponse.success(userService.listUser(qry));
    }

    /**
     * 用户详情
     */
    @RequirePermission("user:list")
    @GetMapping("/api/user/{id}")
    public ServerResponse<UserDto.UserList> getUser(@PathVariable String id) {

        return ServerResponse.success(userService.getUser(id));
    }

}
