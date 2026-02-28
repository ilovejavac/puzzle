package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 模型关联条件实体
 * 定义表关联的具体条件（ON 子句），支持复杂的关联逻辑
 */
@Data
@Entity
@Table(name = "model_join_condition")
public class ModelJoinCondition extends JpaEntity {

    /** 所属模型关联 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_join_id", nullable = false)
    private ModelJoin modelJoin;

    /** 左表ID */
    @Column(nullable = false)
    private Long leftTableId;

    /** 左表字段名称 */
    @Column(length = 256, nullable = false)
    private String leftColumn;

    /** 操作符：=、!=、>、<、>=、<=、LIKE、BETWEEN、IN */
    @Column(length = 16, nullable = false)
    private String operator;

    /** 右表ID */
    @Column(nullable = false)
    private Long rightTableId;

    /** 右表字段名称或常量值 */
    @Column(length = 256, nullable = false)
    private String rightColumn;

    /** 条件序号（用于支持多条件 AND/OR 组合） */
    @Column(nullable = false)
    private Integer ordinal;

}
