package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user_role")
public class PuzzleRole extends TenantEntity {

    @Column(length = 64)
    private String roleCode;

    @Column(length = 128)
    private String roleName;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "user_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"})
    )
    private List<PuzzlePermission> permissions;

}
