package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzlePermission;

public interface PermissionDao extends BaseRepository<PuzzlePermission> {

    @Data
    class QueryPermission extends DslQuery<PuzzlePermission> {

    }

}
