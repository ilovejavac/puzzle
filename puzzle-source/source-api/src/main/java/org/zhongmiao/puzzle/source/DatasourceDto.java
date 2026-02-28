package org.zhongmiao.puzzle.source;

import lombok.Data;
import org.zhongmiao.puzzle.source.enums.SourceStatus;
import org.zhongmiao.puzzle.source.enums.SourceType;

/**
 * 数据源 DTO
 */
@Data
public class DatasourceDto {

    private DatasourceDto() {

    }

    /**
     * 数据源列表项
     */
    @Data
    public static class DatasourceList {
        private Long id;
        private String name;
        private String description;
        private SourceType type;
        private SourceStatus status;
    }

    /**
     * 数据源详情
     */
    @Data
    public static class DatasourceDetail extends DatasourceList {
        private String host;
        private Integer port;
        private String database;
        private String username;
    }

    /**
     * 表信息
     */
    @Data
    public static class TableInfo {
        private Long id;
        private String schemaName;
        private String tableName;
        private String tableComment;
        private Long columnCount;
    }

}
