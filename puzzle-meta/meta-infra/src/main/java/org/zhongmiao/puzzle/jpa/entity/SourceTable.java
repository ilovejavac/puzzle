package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.zhongmiao.puzzle.TableType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 源表实体
 * 用于管理外部数据源的表元数据，记录表结构、统计信息和同步状态
 */
@Data
@Entity
@Table(name = "meta_source_table")
public class SourceTable extends JpaEntity {

    /** 所属数据源ID */
    @Column(nullable = false)
    private Long datasourceId;

    /** 数据库名称/Schema名称 */
    @Column(length = 128)
    private String schemaName;

    /** 表名称 */
    @Column(length = 256, nullable = false)
    private String tableName;

    /** 表类型：TABLE/VIEW/MATERIALIZED_VIEW */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private TableType tableType;

    /** 行数统计 */
    private Long rowCount;

    /** 表大小（字节） */
    private Long sizeBytes;

    /** 最后同步时间 */
    private LocalDateTime syncedAt;

    /** 源表字段列表 */
    @OneToMany(mappedBy = "sourceTable", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordinalPosition ASC")
    private List<SourceColumn> columns;

}
