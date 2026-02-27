package org.zhongmiao.puzzle.source;

import lombok.Data;
import org.zhongmiao.puzzle.SourceType;

/**
 * Datasource DTO
 */
@Data
public class DatasourceDto {

    private Long       id;

    private String     name;

    private String     description;

    private SourceType type;

    private String     host;

    private Integer    port;

    private String     database;

    private String     username;

    private Boolean    connected;

}
