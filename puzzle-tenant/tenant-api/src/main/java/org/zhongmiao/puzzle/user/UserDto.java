package org.zhongmiao.puzzle.user;

import com.dev.lib.web.BaseVO;
import lombok.Data;

public class UserDto {

    private UserDto() {

    }

    @Data
    public static class UserList extends BaseVO {

        private String username;

        private String email;

        private String phone;

    }

}
