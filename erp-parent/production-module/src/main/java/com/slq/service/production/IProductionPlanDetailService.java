package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.ProductionPlanDetail;

public interface IProductionPlanDetailService {

	int insert(Integer id,List<ProductionPlanDetail> productionPlanDetails);

	List<ProductionPlanDetail> getProductionPlanDetailByParentId(Integer parent_id);

	int deleteByParentId(Integer id);

	int batchUpdateManufacture(Integer id, List<ProductionPlanDetail> productionPlanDetails, Integer manufacture_tag);
	// 获取可以制定派工单的生产计划单以及详细
	List<ProductionPlanDetail> getProductionPlanDetailMeetDespatching();

}
