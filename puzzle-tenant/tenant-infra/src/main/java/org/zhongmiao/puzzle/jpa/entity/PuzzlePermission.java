package org.zhongmiao.puzzle.jpa.entity;

import com.dev.lib.jpa.entity.JpaEntity;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.zhongmiao.puzzle.permission.PermissionDto;

@Data
@Entity
@Table(name = "user_permission")
@AutoMapper(target = PermissionDto.class, reverseConvertGenerate = false)
public class PuzzlePermission extends JpaEntity {

    @Column(length = 64)
    private String service;

    @Column(length = 128)
    private String permissionCode;

    @Column(length = 256)
    private String description;

    @Column(length = 512)
    private String path;

    @Column(length = 16)
    private String method;

}
