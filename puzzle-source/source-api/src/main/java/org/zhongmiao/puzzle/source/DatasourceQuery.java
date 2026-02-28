package org.zhongmiao.puzzle.source;

import lombok.Data;
import org.zhongmiao.puzzle.source.enums.SourceType;

/**
 * 数据源查询对象
 */
@Data
public class DatasourceQuery {

    private DatasourceQuery() {

    }

    /**
     * 查询数据源列表
     */
    @Data
    public static class QueryDatasource {
        private String name;
        private SourceType type;
    }

    /**
     * 获取数据源详情
     */
    @Data
    public static class GetDatasource {
        private Long id;
    }

    /**
     * 测试连接
     */
    @Data
    public static class TestConnection {
        private Long id;
    }

    /**
     * 同步 Schema
     */
    @Data
    public static class SyncSchema {
        private Long datasourceId;
    }

    /**
     * 获取表列表
     */
    @Data
    public static class ListTables {
        private Long datasourceId;
        private String schemaName;
    }

}
