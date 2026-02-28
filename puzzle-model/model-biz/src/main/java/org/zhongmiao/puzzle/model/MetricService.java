package org.zhongmiao.puzzle.model;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;
import org.zhongmiao.puzzle.metric.MetricCmd;
import org.zhongmiao.puzzle.metric.MetricDto;
import org.zhongmiao.puzzle.metric.MetricQuery;

/**
 * 指标服务接口
 */
public interface MetricService {

    /**
     * 创建指标
     */
    void createMetric(MetricCmd.CreateMetric cmd);

    /**
     * 修改指标
     */
    void updateMetric(MetricCmd.UpdateMetric cmd);

    /**
     * 删除指标
     */
    void deleteMetric(MetricCmd.DeleteMetric cmd);

    /**
     * 查询指标列表
     */
    Page<MetricDto.MetricList> listMetrics(QueryRequest<MetricQuery.QueryMetric> qry);

}
