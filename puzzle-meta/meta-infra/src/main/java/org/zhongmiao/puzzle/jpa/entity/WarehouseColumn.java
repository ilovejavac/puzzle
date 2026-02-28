package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 数据仓库表字段实体
 * 用于管理数仓表的字段元数据，记录字段类型、分区键、业务名称等信息
 */
@Data
@Entity
@Table(name = "meta_warehouse_column")
public class WarehouseColumn extends JpaEntity {

    /**
     * 所属仓库表
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private WarehouseTable warehouseTable;

    /**
     * 字段名称
     */
    @Column(length = 256, nullable = false)
    private String columnName;

    /**
     * 字段数据类型
     */
    @Column(length = 128, nullable = false)
    private String columnType;

    /**
     * 字段在表中的序号位置
     */
    @Column(nullable = false)
    private Integer ordinalPosition;

    /**
     * 是否为分区键
     */
    private Boolean isPartitionKey;

    /**
     * 字段描述
     */
    private String description;

    /**
     * 业务名称
     */
    @Column(length = 256)
    private String bizName;

    /**
     * 数据标准编码
     */
    @Column(length = 128)
    private String dataStandard;

}
