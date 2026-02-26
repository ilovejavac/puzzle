package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzleRole;

public interface RoleDao extends BaseRepository<PuzzleRole> {

    @Data
    class QueryRole extends DslQuery<PuzzleRole> {

    }
}
