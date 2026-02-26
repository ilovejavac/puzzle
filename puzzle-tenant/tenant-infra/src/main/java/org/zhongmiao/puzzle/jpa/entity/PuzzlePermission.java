package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_permission")
public class PuzzlePermission extends JpaEntity {

    private String permissionCode;

}
