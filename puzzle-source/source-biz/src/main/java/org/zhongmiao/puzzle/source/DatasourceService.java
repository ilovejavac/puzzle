package org.zhongmiao.puzzle.source;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 数据源服务接口
 */
public interface DatasourceService {

    /**
     * 创建数据源
     */
    void createDatasource(DatasourceCmd.CreateDatasource cmd);

    /**
     * 修改数据源
     */
    void updateDatasource(DatasourceCmd.UpdateDatasource cmd);

    /**
     * 删除数据源
     */
    void deleteDatasource(DatasourceCmd.DeleteDatasource cmd);

    /**
     * 查询数据源列表（分页）
     */
    Page<DatasourceDto.DatasourceList> listDatasources(QueryRequest<DatasourceQuery.QueryDatasource> qry);

    /**
     * 获取数据源详情
     */
    DatasourceDto.DatasourceDetail getDatasource(DatasourceQuery.GetDatasource qry);

    /**
     * 测试连接
     */
    Boolean testConnection(DatasourceQuery.TestConnection qry);

    /**
     * 同步 Schema
     */
    void syncSchema(DatasourceQuery.SyncSchema qry);

    /**
     * 获取数据源下的表列表
     */
    List<DatasourceDto.TableInfo> listTables(DatasourceQuery.ListTables qry);

}
