package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.zhongmiao.puzzle.engine.model.ModelSnapshot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 模型版本实体
 * 用于管理模型的历史版本，记录模型配置的快照和变更说明
 */
@Data
@Entity
@Table(name = "model_version")
public class ModelVersion extends JpaEntity {

    /** 所属模型ID */
    @Column(nullable = false)
    private Long modelId;

    /** 版本号 */
    @Column(nullable = false)
    private Integer version;

    /** 版本快照（JSON 格式，存储完整的模型配置） */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "text", nullable = false)
    private ModelSnapshot snapshot;

    /** 变更说明 */
    @Column(columnDefinition = "text")
    private String changeNote;

    /** 创建人ID */
    private Long createdBy;

}
