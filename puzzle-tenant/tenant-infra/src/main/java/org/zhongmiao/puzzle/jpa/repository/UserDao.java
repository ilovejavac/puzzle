package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;

public interface UserDao extends BaseRepository<PuzzleUser> {

    @Data
    class QueryUser extends DslQuery<PuzzleUser> {

    }

}
