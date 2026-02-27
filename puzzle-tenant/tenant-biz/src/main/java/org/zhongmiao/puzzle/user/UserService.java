package org.zhongmiao.puzzle.user;

import com.dev.lib.security.util.UserDetails;
import com.dev.lib.web.model.QueryRequest;

import org.springframework.data.domain.Page;
import java.util.List;

public interface UserService {

    UserDetails loadUser(Long id);

    /**
     * Create user
     */
    String createUser(UserCmd.CreateUser cmd);

    /**
     * Update user
     */
    void updateUser(UserCmd.UpdateUser cmd);

    /**
     * Delete users by bizIds
     */
    void deleteUsers(List<String> bizIds);

    /**
     * Query user list with pagination
     */
    Page<UserDto.UserList> listUser(QueryRequest<UserQuery.QueryUser> qry);

    /**
     * Get user detail by bizId
     */
    UserDto.UserList getUser(String bizId);

}
