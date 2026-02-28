package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.source.DatasourceCmd;
import org.zhongmiao.puzzle.source.DatasourceDto;
import org.zhongmiao.puzzle.source.DatasourceQuery;
import org.zhongmiao.puzzle.source.DatasourceService;

import java.util.List;

/**
 * 数据源接口
 * <p>
 * 管理数据源配置，测试连接，同步 Schema
 */
@RestController
@RequiredArgsConstructor
public class DatasourceController {

    private final DatasourceService datasourceService;

    /**
     * 创建数据源
     * 需要权限[source:create]
     */
    @RequirePermission("source:create")
    @PostMapping("/api/source/create-datasource")
    public ServerResponse<Void> createDatasource(@RequestBody DatasourceCmd.CreateDatasource cmd) {

        datasourceService.createDatasource(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改数据源
     * 需要权限[source:update]
     */
    @RequirePermission("source:update")
    @PostMapping("/api/source/update-datasource")
    public ServerResponse<Void> updateDatasource(@RequestBody DatasourceCmd.UpdateDatasource cmd) {

        datasourceService.updateDatasource(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除数据源
     * 需要权限[source:delete]
     */
    @RequirePermission("source:delete")
    @PostMapping("/api/source/delete-datasource")
    public ServerResponse<Void> deleteDatasource(@RequestBody DatasourceCmd.DeleteDatasource cmd) {

        datasourceService.deleteDatasource(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询数据源列表（分页）
     * 需要权限[source:list]
     */
    @RequirePermission("source:list")
    @PostMapping("/api/source/list-datasources")
    public ServerResponse<List<DatasourceDto.DatasourceList>> listDatasources(
            @RequestBody QueryRequest<DatasourceQuery.QueryDatasource> qry) {

        return ServerResponse.success(datasourceService.listDatasources(qry));
    }

    /**
     * 获取数据源详情
     * 需要权限[source:view]
     */
    @RequirePermission("source:view")
    @PostMapping("/api/source/get-datasource")
    public ServerResponse<DatasourceDto.DatasourceDetail> getDatasource(@RequestBody DatasourceQuery.GetDatasource qry) {

        return ServerResponse.success(datasourceService.getDatasource(qry));
    }

    /**
     * 测试连接
     * 需要权限[source:test]
     */
    @RequirePermission("source:test")
    @PostMapping("/api/source/test-connection")
    public ServerResponse<Boolean> testConnection(@RequestBody DatasourceQuery.TestConnection qry) {

        return ServerResponse.success(datasourceService.testConnection(qry));
    }

    /**
     * 同步 Schema
     * 需要权限[source:sync]
     */
    @RequirePermission("source:sync")
    @PostMapping("/api/source/sync-schema")
    public ServerResponse<Void> syncSchema(@RequestBody DatasourceQuery.SyncSchema qry) {

        datasourceService.syncSchema(qry);
        return ServerResponse.ok();
    }

    /**
     * 获取数据源下的表列表
     * 需要权限[source:view]
     */
    @RequirePermission("source:view")
    @PostMapping("/api/source/list-tables")
    public ServerResponse<List<DatasourceDto.TableInfo>> listTables(
            @RequestBody DatasourceQuery.ListTables qry) {

        return ServerResponse.success(datasourceService.listTables(qry));
    }

}
