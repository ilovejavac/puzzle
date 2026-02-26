package org.zhongmiao.puzzle.user;

import com.dev.lib.security.util.UserDetails;

public interface UserService {

    UserDetails loadUser(Long id);

}
