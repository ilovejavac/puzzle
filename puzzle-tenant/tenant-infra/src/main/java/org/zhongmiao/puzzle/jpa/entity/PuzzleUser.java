package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.entity.encrypt.Encrypt;
import com.dev.lib.jpa.TenantEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.zhongmiao.puzzle.user.UserStatus;

import java.util.List;

@Data
@Entity
@Table(name = "user_tbl")
public class PuzzleUser extends TenantEntity {

    @Column(length = 64)
    private String username;

    @Encrypt
    private String password;

    @Column(length = 128)
    private String email;

    @Column(length = 32)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12)")
    private UserStatus status;

    @ManyToMany
    @JoinTable(
            name = "user_role_rel",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private List<PuzzleRole> roles;

}
