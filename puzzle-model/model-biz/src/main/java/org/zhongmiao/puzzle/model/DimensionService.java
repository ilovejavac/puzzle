package org.zhongmiao.puzzle.model;

import com.dev.lib.web.model.QueryRequest;
import org.springframework.data.domain.Page;
import org.zhongmiao.puzzle.dimension.DimensionCmd;
import org.zhongmiao.puzzle.dimension.DimensionDto;
import org.zhongmiao.puzzle.dimension.DimensionQuery;

/**
 * 维度服务接口
 */
public interface DimensionService {

    /**
     * 创建维度
     */
    void createDimension(DimensionCmd.CreateDimension cmd);

    /**
     * 修改维度
     */
    void updateDimension(DimensionCmd.UpdateDimension cmd);

    /**
     * 删除维度
     */
    void deleteDimension(DimensionCmd.DeleteDimension cmd);

    /**
     * 查询维度列表
     */
    Page<DimensionDto.DimensionList> listDimensions(QueryRequest<DimensionQuery.QueryDimension> qry);

}
