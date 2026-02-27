package org.zhongmiao.puzzle.jpa.repository;

import com.dev.lib.entity.dsl.DslQuery;
import com.dev.lib.jpa.entity.BaseRepository;
import lombok.Data;
import org.zhongmiao.puzzle.jpa.entity.PuzzlePermission;

import java.util.Collection;
import java.util.List;

public interface PermissionDao extends BaseRepository<PuzzlePermission> {

    /**
     * Find permissions by service name
     */
    default List<PuzzlePermission> findByService(String service) {

        return loads(new QueryPermission().setService(service));
    }

    /**
     * Find permission by service and code
     */
    default List<PuzzlePermission> findByServiceAndPermissionCode(String service, String permissionCode) {

        return loads(new QueryPermission().setService(service).setPermissionCode(permissionCode));
    }

    /**
     * Find permission by service, path and method (stable identifier for an endpoint)
     */
    default List<PuzzlePermission> findByServiceAndPathAndMethod(String service, String path, String method) {

        return loads(new QueryPermission().setService(service).setPath(path).setMethod(method));
    }

    @Data
    class QueryPermission extends DslQuery<PuzzlePermission> {

        private String service;

        private String permissionCode;

        private String path;

        private String method;

        private Collection<String> permissionCodeIn;

    }

}
