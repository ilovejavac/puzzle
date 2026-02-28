package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import lombok.Data;
import org.zhongmiao.puzzle.enums.JoinType;
import jakarta.persistence.*;

import java.util.List;

/**
 * 模型关联实体
 * 定义模型中表与表之间的关联关系（JOIN），支持内连接、左连接等多种连接方式
 */
@Data
@Entity
@Table(name = "model_join")
public class ModelJoin extends JpaEntity {

    /** 所属模型 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    /** 源表ID（被关联的表） */
    @Column(nullable = false)
    private Long sourceTableId;

    /** 连接类型：INNER/LEFT/RIGHT/FULL/CROSS */
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)", nullable = false)
    private JoinType joinType;

    /** 关联序号（用于控制关联顺序） */
    @Column(nullable = false)
    private Integer ordinal;

    /** 关联条件列表 */
    @OneToMany(mappedBy = "modelJoin", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordinal ASC")
    private List<ModelJoinCondition> conditions;

}
