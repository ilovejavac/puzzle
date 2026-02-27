package org.zhongmiao.puzzle.tenant;

import com.dev.lib.web.model.QueryRequest;

import org.springframework.data.domain.Page;
import java.util.List;

/**
 * Tenant Service
 */
public interface TenantService {

    /**
     * Create tenant
     */
    String createTenant(TenantCmd.CreateTenant cmd);

    /**
     * Update tenant
     */
    void updateTenant(TenantCmd.UpdateTenant cmd);

    /**
     * Query tenant list with pagination
     */
    Page<TenantDto.TenantList> listTenant(QueryRequest<TenantQuery.QueryTenant> qry);

}
