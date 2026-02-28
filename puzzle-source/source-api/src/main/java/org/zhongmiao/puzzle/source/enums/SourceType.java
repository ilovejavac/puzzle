package org.zhongmiao.puzzle.source.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceType {
    MYSQL("MYSQL", "MySQL"),
    POSTGRESQL("POSTGRESQL", "PostgreSQL"),
    ORACLE("ORACLE", "Oracle"),
    SQLSERVER("SQLSERVER", "SQL Server"),
    CLICKHOUSE("CLICKHOUSE", "ClickHouse"),
    DORIS("DORIS", "Doris"),
    ICEBERG("ICEBERG", "Iceberg"),
    KAFKA("KAFKA", "Kafka"),
    ELASTICSEARCH("ELASTICSEARCH", "Elasticsearch"),
    REDIS("REDIS", "Redis"),
    MONGODB("MONGODB", "MongoDB"),
    HIVE("HIVE", "Hive"),
    MAXCOMPUTE("MAXCOMPUTE", "MaxCompute"),
    DATASPHINX("DATASPHINX", "DataSphinx");

    @JsonValue
    private final String code;
    private final String message;
}
