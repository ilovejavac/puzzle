package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_tenant")
public class PuzzleTenant extends JpaEntity {

    private String tenantName;

    /**
     * 到期时间
     */
    private LocalDateTime expiredAt;

}
