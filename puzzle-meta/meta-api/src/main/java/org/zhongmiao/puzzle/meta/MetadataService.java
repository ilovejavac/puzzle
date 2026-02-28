package org.zhongmiao.puzzle.meta;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 元数据服务接口
 */
public interface MetadataService {

    /**
     * 同步 Schema
     */
    void syncSchema(MetadataCmd.SyncSchema cmd);

    /**
     * 查询源表列表（分页）
     */
    Page<SourceTableDto> listSourceTables(QueryRequest<MetadataQuery.QuerySourceTables> qry);

    /**
     * 获取源表详情
     */
    SourceTableDto getSourceTable(MetadataQuery.GetSourceTable qry);

    /**
     * 获取表的字段列表
     */
    List<SourceColumnDto> listColumns(MetadataQuery.ListColumns qry);

    /**
     * 获取血缘关系图
     */
    LineageGraphDto getLineageGraph(MetadataQuery.QueryLineage qry);

    /**
     * 搜索元数据
     */
    List<Object> searchMetadata(MetadataQuery.SearchMetadata qry);

}
