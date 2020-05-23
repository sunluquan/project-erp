package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.production.ProcessDetail;

@Mapper
public interface ProcessDetailMapper extends BaseMapper<ProcessDetail> {

	int batchInsert(@Param("parent_id") Integer id, @Param("processDetails") List<ProcessDetail> processDetails);

	List<ProcessDetail> getProcessDetailByParentId(@Param("parentId")Integer parentId);

	// 不仅获取工序集合 还获取工序的物料集合
	List<ProcessDetail> getProcessDetailByParentIdJoinMaterialDetail(Integer parentId);

	Float processTotalManhoirCostPriceSum(Integer id);

	// 根据产品编号查询 该产品的工序详细
	// 在新增派工单的时候使用到
	List<ProcessDetail> getProcessDetailByProductId(String product_id);
}
