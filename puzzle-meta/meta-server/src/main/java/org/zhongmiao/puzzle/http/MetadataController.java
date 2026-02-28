package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.lineage.LineageGraphDto;
import org.zhongmiao.puzzle.meta.*;

import java.util.List;

/**
 * 元数据接口
 * <p>
 * 管理元数据、浏览源表、查看血缘关系
 */
@RestController
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataService metadataService;

    /**
     * 同步 Schema
     * 需要权限[meta:sync]
     */
    @RequirePermission("meta:sync")
    @PostMapping("/api/meta/sync-schema")
    public ServerResponse<Void> syncSchema(@RequestBody MetadataCmd.SyncSchema cmd) {
        metadataService.syncSchema(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询源表列表（分页）
     * 需要权限[meta:view]
     */
    @RequirePermission("meta:view")
    @PostMapping("/api/meta/list-source-tables")
    public ServerResponse<List<SourceTableDto>> listSourceTables(
            @RequestBody QueryRequest<MetadataQuery.QuerySourceTables> qry) {
        return ServerResponse.success(metadataService.listSourceTables(qry));
    }

    /**
     * 获取源表详情
     * 需要权限[meta:view]
     */
    @RequirePermission("meta:view")
    @PostMapping("/api/meta/get-source-table")
    public ServerResponse<SourceTableDto> getSourceTable(@RequestBody MetadataQuery.GetSourceTable qry) {
        return ServerResponse.success(metadataService.getSourceTable(qry));
    }

    /**
     * 获取表的字段列表
     * 需要权限[meta:view]
     */
    @RequirePermission("meta:view")
    @PostMapping("/api/meta/list-columns")
    public ServerResponse<List<SourceColumnDto>> listColumns(@RequestBody MetadataQuery.ListColumns qry) {
        return ServerResponse.success(metadataService.listColumns(qry));
    }

    /**
     * 获取血缘关系图
     * 需要权限[meta:lineage]
     */
    @RequirePermission("meta:lineage")
    @PostMapping("/api/meta/get-lineage-graph")
    public ServerResponse<LineageGraphDto> getLineageGraph(@RequestBody MetadataQuery.QueryLineage qry) {
        return ServerResponse.success(metadataService.getLineageGraph(qry));
    }

    /**
     * 搜索元数据
     * 需要权限[meta:search]
     */
    @RequirePermission("meta:search")
    @PostMapping("/api/meta/search-metadata")
    public ServerResponse<List<Object>> searchMetadata(@RequestBody MetadataQuery.SearchMetadata qry) {
        return ServerResponse.success(metadataService.searchMetadata(qry));
    }

}
