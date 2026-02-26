package org.zhongmiao.puzzle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceType {
    MYSQL,
    POSTGRESQL,
    ORACLE,
    SQLSERVER,
    KAFKA,
    S3;
}