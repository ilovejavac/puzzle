package org.zhongmiao.puzzle.meta;

import lombok.Data;

/**
 * 元数据查询对象
 */
@Data
public class MetadataQuery {

    private MetadataQuery() {

    }

    /**
     * 查询源表列表
     */
    @Data
    public static class QuerySourceTables {
        private Long datasourceId;
        private String schemaName;
        private String tableName;
    }

    /**
     * 获取源表详情
     */
    @Data
    public static class GetSourceTable {
        private Long tableId;
    }

    /**
     * 获取表的字段列表
     */
    @Data
    public static class ListColumns {
        private Long tableId;
    }

    /**
     * 查询血缘关系
     */
    @Data
    public static class QueryLineage {
        private Long modelId;
        private String entityType; // SOURCE_TABLE, MODEL, METRIC
    }

    /**
     * 搜索元数据
     */
    @Data
    public static class SearchMetadata {
        private String keyword;
        private String type; // table, column, model, metric
    }

}
