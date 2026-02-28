package org.zhongmiao.puzzle.query;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryCmd;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryDto;
import org.zhongmiao.puzzle.query.savedquery.SavedQueryQuery;

/**
 * 保存查询服务接口
 */
public interface SavedQueryService {

    /**
     * 保存查询
     */
    void saveQuery(SavedQueryCmd.SaveQuery cmd);

    /**
     * 删除保存的查询
     */
    void deleteSavedQuery(SavedQueryCmd.DeleteSavedQuery cmd);

    /**
     * 查询保存的查询列表（分页）
     */
    Page<SavedQueryDto.SavedQueryList> listSavedQueries(QueryRequest<SavedQueryQuery.QuerySavedQuery> qry);

    /**
     * 获取保存的查询详情
     */
    SavedQueryDto.SavedQueryDetail getSavedQuery(SavedQueryQuery.GetSavedQuery qry);

}
