package org.zhongmiao.puzzle.source;

import lombok.Data;
import org.zhongmiao.puzzle.SourceType;

import java.util.Map;

/**
 * Datasource Command DTO
 */
@Data
public class DatasourceCmd {

    private String              name;

    private String              description;

    private SourceType          type;

    private String              host;

    private Integer             port;

    private String              database;

    private String              username;

    private String              password;

    private Map<String, Object> properties;

}
