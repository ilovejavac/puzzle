package org.zhongmiao.puzzle.meta;

import lombok.Data;

/**
 * 元数据命令对象
 */
@Data
public class MetadataCmd {

    private MetadataCmd() {

    }

    /**
     * 同步 Schema
     */
    @Data
    public static class SyncSchema {
        private Long datasourceId;
    }

}
