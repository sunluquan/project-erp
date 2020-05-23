package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProductionPlan;
import com.slq.pojo.production.ProductionPlanDetail;
import com.slq.service.production.IProductionPlanDetailService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productionPlanDetail")
public class ProductionPlanDetailController {

	@Autowired
	private IProductionPlanDetailService productionPlanDetailService;

	// 获取可以制定派工单的生产计划单以及详细
	@GetMapping("/meetDespatching")
	public ResponseData getProductionPlanDetailMeetDespatching() {
		List<ProductionPlanDetail> productionPlanDetails = productionPlanDetailService.getProductionPlanDetailMeetDespatching();
		return DefaultResponseData.successResponseData(productionPlanDetails);
	}

	@GetMapping("/byParentId/{parent_id}")
	public ResponseData getProductionPlanDetailByParentId(@PathVariable("parent_id") Integer parent_id) {
		List<ProductionPlanDetail> productionPlanDetails = productionPlanDetailService
				.getProductionPlanDetailByParentId(parent_id);
		return DefaultResponseData.successResponseData(productionPlanDetails);
	}
}
