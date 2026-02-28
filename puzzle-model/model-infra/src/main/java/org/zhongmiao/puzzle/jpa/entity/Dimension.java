package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.enums.DimensionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.List;

/**
 * 维度实体
 * 定义数据查询和分析的维度信息，支持维度类型和枚举值配置
 */
@Data
@Entity
@Table(name = "model_dimension")
public class Dimension extends TenantEntity {

    /** 维度编码 */
    @Column(length = 128, nullable = false)
    private String dimCode;

    /** 维度名称 */
    @Column(length = 256, nullable = false)
    private String dimName;

    /** 维度类型：CATEGORY/TIME/GEOGRAPHY/ATTRIBUTE */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private DimensionType dimType;

    /** 关联的模型字段ID */
    private Long modelFieldId;

    /** 维度枚举值（JSON 数组格式，用于类别型维度） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text")
    private List<String> valueEnum;

    /** 维度描述 */
    private String description;

}
