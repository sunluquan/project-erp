package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProductionPlan;
import com.slq.service.production.IProductionPlanService;
import com.slq.util.DefaultResponseData;
import com.slq.util.DefaultResponseData1;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productionPlan")
public class ProductionPlanController {

	@Autowired
	private IProductionPlanService productionPlanService;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	//获取满足制定派工单的生产计划
	@GetMapping("/meetDespatching")
	public ResponseData getMeetDespatchingProductionPlan() {
		List<ProductionPlan> productionPlans=productionPlanService.getMeetDespatchingProductionPlans();
		return DefaultResponseData.successResponseData(productionPlans);
	}
<<<<<<< HEAD
=======
	
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody ProductionPlan productionPlan) {
		System.out.println(JSON.toJSONString(productionPlan));
		int i=productionPlanService.insert(productionPlan);
		return DefaultResponseData.successResponseData(i);
	}
	@PostMapping("/update")
	public ResponseData update(@RequestBody ProductionPlan productionPlan) {
		System.out.println(JSON.toJSONString(productionPlan));
		int i=productionPlanService.update(productionPlan);
		return DefaultResponseData.successResponseData(i);
	}
	@PostMapping("/search")
	public ResponseData search(@RequestBody Params params) {
		PageInfo<ProductionPlan> productionPlanPage=productionPlanService.searchProductionPlan(params);
		return DefaultResponseData.successResponseData(productionPlanPage);
	}
	//获取所有可以审核的生产计划单
	@GetMapping("/checker")
	public ResponseData checker() {
		List<ProductionPlan> productionPlans=productionPlanService.getCheckerProductionPlans();
		return DefaultResponseData.successResponseData(productionPlans);
	}
	//查询生产计划总单和详细单
	@GetMapping("/byIdAll/{id}")
	public ResponseData getProductionPlanAndDetailById(@PathVariable("id")Integer id) {
		ProductionPlan productionPlan=productionPlanService.getProductionPlanAndDetailById(id);
		return DefaultResponseData.successResponseData(productionPlan);
	}
	@PostMapping("/pass")
	public ResponseData pass(@RequestBody ProductionPlan productionPlan) {
		int i = productionPlanService.pass(productionPlan);
		return DefaultResponseData.successResponseData(i);
	}
	
	@PostMapping("/reject")
	public ResponseData reject(@RequestBody ProductionPlan productionPlan) {
		int i = productionPlanService.reject(productionPlan);
		return DefaultResponseData.successResponseData(i);
	}
}
