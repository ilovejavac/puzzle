package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.dimension.DimensionCmd;
import org.zhongmiao.puzzle.dimension.DimensionDto;
import org.zhongmiao.puzzle.dimension.DimensionQuery;
import org.zhongmiao.puzzle.model.DimensionService;

import java.util.List;

/**
 * 维度接口
 * <p>
 * 管理维度的创建、修改、删除和查询
 */
@RestController
@RequiredArgsConstructor
public class DimensionController {

    private final DimensionService dimensionService;

    /**
     * 创建维度
     * 需要权限[model:create]
     */
    @RequirePermission("model:create")
    @PostMapping("/api/model/create-dimension")
    public ServerResponse<Void> createDimension(@RequestBody DimensionCmd.CreateDimension cmd) {
        dimensionService.createDimension(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改维度
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/update-dimension")
    public ServerResponse<Void> updateDimension(@RequestBody DimensionCmd.UpdateDimension cmd) {
        dimensionService.updateDimension(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除维度
     * 需要权限[model:delete]
     */
    @RequirePermission("model:delete")
    @PostMapping("/api/model/delete-dimension")
    public ServerResponse<Void> deleteDimension(@RequestBody DimensionCmd.DeleteDimension cmd) {
        dimensionService.deleteDimension(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询维度列表
     * 需要权限[model:view]
     */
    @RequirePermission("model:view")
    @PostMapping("/api/model/list-dimensions")
    public ServerResponse<List<DimensionDto.DimensionList>> listDimensions(
            @RequestBody QueryRequest<DimensionQuery.QueryDimension> qry) {
        return ServerResponse.success(dimensionService.listDimensions(qry));
    }
}
