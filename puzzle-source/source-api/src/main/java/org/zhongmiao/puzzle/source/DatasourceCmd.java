package org.zhongmiao.puzzle.source;

import lombok.Data;
import org.zhongmiao.puzzle.conn.ConnConfig;
import org.zhongmiao.puzzle.source.enums.SourceType;

/**
 * 数据源命令对象
 */
@Data
public class DatasourceCmd {

    private DatasourceCmd() {

    }

    /**
     * 创建数据源
     */
    @Data
    public static class CreateDatasource {

        /**
         * 数据源名称
         */
        private String     name;

        /**
         * 数据源描述
         */
        private String     description;

        /**
         * 数据源类型
         */
        private SourceType type;

        /**
         * 连接配置
         */
        private ConnConfig connConfig;

    }

    /**
     * 修改数据源
     */
    @Data
    public static class UpdateDatasource extends CreateDatasource {

        /**
         * 数据源 ID
         */
        private Long id;

    }

    /**
     * 删除数据源
     */
    @Data
    public static class DeleteDatasource {

        /**
         * 数据源 ID
         */
        private Long id;

    }

}
