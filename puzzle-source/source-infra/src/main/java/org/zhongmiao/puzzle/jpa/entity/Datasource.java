package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.conn.ConnConfig;
import org.zhongmiao.puzzle.source.enums.SourceStatus;
import org.zhongmiao.puzzle.source.enums.SourceType;

import java.time.LocalDateTime;

/**
 * 数据源实体
 * 用于管理外部数据源的连接配置和状态信息
 */
@Data
@Entity
@Table(name = "source_tbl")
public class Datasource extends TenantEntity {

    /** 数据源名称 */
    @Column(length = 128, nullable = false)
    private String sourceName;

    /** 数据源类型：MYSQL/POSTGRESQL/KAFKA/HIVE等 */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private SourceType sourceType;

    /** 连接配置（JSON 格式，包含主机、端口、用户名等） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private ConnConfig connConfig;

    /** 数据源状态：ACTIVE/INACTIVE/ERROR */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private SourceStatus status;

    /** 最后测试时间 */
    private LocalDateTime lastTestAt;

    /** 最后测试结果 */
    @Column(columnDefinition = "text")
    private String lastTestResult;

    /** 数据源描述 */
    private String description;

}