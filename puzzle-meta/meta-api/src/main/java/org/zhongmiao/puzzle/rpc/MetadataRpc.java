package org.zhongmiao.puzzle.rpc;

import org.zhongmiao.puzzle.meta.SourceColumnDto;
import org.zhongmiao.puzzle.meta.SourceTableDto;

import java.util.List;

/**
 * 元数据 RPC 服务
 * <p>
 * 被调用方: source, model, engine
 */
public interface MetadataRpc {

    /**
     * 同步数据源 Schema
     * <p>
     * 从数据源同步表结构信息（被 source 模块调用）
     *
     * @param datasourceId 数据源 ID
     */
    void syncSchema(Long datasourceId);

    /**
     * 获取源表信息
     * <p>
     * 根据 ID 获取源表详情（被 model 模块调用）
     *
     * @param tableId 表 ID
     * @return 源表信息
     */
    SourceTableDto getSourceTable(Long tableId);

    /**
     * 获取表的字段列表
     * <p>
     * 获取指定表的所有字段（被 model 模块调用）
     *
     * @param tableId 表 ID
     * @return 字段列表
     */
    List<SourceColumnDto> listColumns(Long tableId);

    /**
     * 保存血缘关系
     * <p>
     * 保存模型到源表和数仓表之间的血缘关系（被 engine 模块调用）
     *
     * @param modelId    模型 ID
     * @param fromTables 源表 ID 列表（逗号分隔）
     * @param toTable    目标数仓表名
     */
    void saveLineage(Long modelId, String fromTables, String toTable);

    /**
     * 注册数仓表
     * <p>
     * 将模型生成的数仓表注册到元数据（被 engine 模块调用）
     *
     * @param modelId   模型 ID
     * @param tableName 数仓表名
     */
    void registerWarehouseTable(Long modelId, String tableName);

}
