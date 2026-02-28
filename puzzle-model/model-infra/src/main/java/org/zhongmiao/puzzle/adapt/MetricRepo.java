package org.zhongmiao.puzzle.adapt;

import com.dev.lib.web.model.QueryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhongmiao.puzzle.enums.MetricStatus;
import org.zhongmiao.puzzle.jpa.entity.Metric;
import org.zhongmiao.puzzle.jpa.repository.MetricDao;
import org.zhongmiao.puzzle.metric.MetricCmd;
import org.zhongmiao.puzzle.metric.MetricDto;
import org.zhongmiao.puzzle.metric.MetricQuery;
import org.zhongmiao.puzzle.model.MetricService;
import org.zhongmiao.puzzle.model.ModelError;
import org.zhongmiao.puzzle.model.ModelException;

/**
 * Metric Service Implementation
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MetricRepo implements MetricService {

    private final MetricDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMetric(MetricCmd.CreateMetric cmd) {
        Metric entity = new Metric();
        entity.setModelId(cmd.getModelId());
        entity.setMetricName(cmd.getMetricName());
        entity.setMetricCode(cmd.getMetricCode());
        entity.setFieldId(cmd.getSourceFieldId());
        entity.setExpression(cmd.getAggFunction());
        entity.setStatus(MetricStatus.PUBLISHED);

        dao.save(entity);
        log.info("Created metric: {}", cmd.getMetricName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMetric(MetricCmd.UpdateMetric cmd) {
        Metric entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new ModelException(ModelError.METRIC_NOT_EXISTS));

        entity.setMetricName(cmd.getMetricName());
        entity.setMetricCode(cmd.getMetricCode());
        entity.setFieldId(cmd.getSourceFieldId());
        entity.setExpression(cmd.getAggFunction());
        log.info("Updated metric: {}", cmd.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMetric(MetricCmd.DeleteMetric cmd) {
        Metric entity = dao.findById(cmd.getId())
                .orElseThrow(() -> new ModelException(ModelError.METRIC_NOT_EXISTS));
        dao.delete(entity);
        log.info("Deleted metric: {}", cmd.getId());
    }

    @Override
    public Page<MetricDto.MetricList> listMetrics(QueryRequest<MetricQuery.QueryMetric> qry) {
        MetricDao.QueryMetric query = new MetricDao.QueryMetric();
        query.external(qry);
        return dao.page(query).map(this::mapToListDto);
    }

    private MetricDto.MetricList mapToListDto(Metric entity) {
        MetricDto.MetricList dto = new MetricDto.MetricList();
        dto.setId(entity.getId());
        dto.setMetricName(entity.getMetricName());
        dto.setMetricCode(entity.getMetricCode());
        dto.setMetricType(entity.getMetricType());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}
