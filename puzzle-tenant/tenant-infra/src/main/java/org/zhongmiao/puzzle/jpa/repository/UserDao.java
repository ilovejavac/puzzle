package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.user.UserStatus;

public interface UserDao extends BaseRepository<PuzzleUser> {

    @Data
    class QueryUser extends DslQuery<PuzzleUser> {

        private String username;

        private String email;

        private String phone;

        private UserStatus status;
    }

}
