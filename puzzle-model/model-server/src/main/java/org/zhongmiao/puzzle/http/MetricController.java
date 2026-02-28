package org.zhongmiao.puzzle.http;

import com.dev.lib.security.service.annotation.RequirePermission;
import com.dev.lib.web.model.QueryRequest;
import com.dev.lib.web.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhongmiao.puzzle.metric.MetricCmd;
import org.zhongmiao.puzzle.metric.MetricDto;
import org.zhongmiao.puzzle.metric.MetricQuery;
import org.zhongmiao.puzzle.model.MetricService;

import java.util.List;

/**
 * 指标接口
 * <p>
 * 管理指标的创建、修改、删除和查询
 */
@RestController
@RequiredArgsConstructor
public class MetricController {

    private final MetricService metricService;

    /**
     * 创建指标
     * 需要权限[model:create]
     */
    @RequirePermission("model:create")
    @PostMapping("/api/model/create-metric")
    public ServerResponse<Void> createMetric(@RequestBody MetricCmd.CreateMetric cmd) {
        metricService.createMetric(cmd);
        return ServerResponse.ok();
    }

    /**
     * 修改指标
     * 需要权限[model:update]
     */
    @RequirePermission("model:update")
    @PostMapping("/api/model/update-metric")
    public ServerResponse<Void> updateMetric(@RequestBody MetricCmd.UpdateMetric cmd) {
        metricService.updateMetric(cmd);
        return ServerResponse.ok();
    }

    /**
     * 删除指标
     * 需要权限[model:delete]
     */
    @RequirePermission("model:delete")
    @PostMapping("/api/model/delete-metric")
    public ServerResponse<Void> deleteMetric(@RequestBody MetricCmd.DeleteMetric cmd) {
        metricService.deleteMetric(cmd);
        return ServerResponse.ok();
    }

    /**
     * 查询指标列表
     * 需要权限[model:view]
     */
    @RequirePermission("model:view")
    @PostMapping("/api/model/list-metrics")
    public ServerResponse<List<MetricDto.MetricList>> listMetrics(
            @RequestBody QueryRequest<MetricQuery.QueryMetric> qry) {
        return ServerResponse.success(metricService.listMetrics(qry));
    }
}
