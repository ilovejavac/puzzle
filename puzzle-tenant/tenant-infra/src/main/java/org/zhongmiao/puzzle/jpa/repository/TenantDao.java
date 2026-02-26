package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzleTenant;

public interface TenantDao extends BaseRepository<PuzzleTenant> {

    @Data
    class QueryTenant extends DslQuery<PuzzleTenant> {

    }
}
