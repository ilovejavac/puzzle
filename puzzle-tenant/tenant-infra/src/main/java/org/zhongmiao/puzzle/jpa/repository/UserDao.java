package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.user.UserStatus;

import java.util.Collection;
import java.util.Collections;

public interface UserDao extends BaseRepository<PuzzleUser> {

    @Data
    class QueryUser extends DslQuery<PuzzleUser> {

        private String username;

        private String email;

        private String phone;

        private UserStatus status;

        private Collection<String> bizIdIn;

    }

}
