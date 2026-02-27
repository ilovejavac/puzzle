package org.zhongmiao.puzzle.source;

import lombok.Data;
import org.zhongmiao.puzzle.SourceType;

/**
 * Datasource Query DTO
 */
@Data
public class DatasourceQuery {

    private Long       tenantId;

    private String     name;

    private SourceType type;

}
