package org.zhongmiao.puzzle.conn;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConnConfig implements Serializable {

    private String host;

    private String port;

    private String db;

    private String schema;

    private String user;

    private String password;

}
