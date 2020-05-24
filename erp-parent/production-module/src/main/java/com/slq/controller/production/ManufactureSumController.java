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
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ManufactureSum;
import com.slq.service.production.IManufactureService;
import com.slq.util.DefaultResponseData;
<<<<<<< HEAD
<<<<<<< HEAD
import com.slq.util.ManufactureChart;
=======
>>>>>>> warehouse
=======
import com.slq.util.ManufactureChart;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.util.ResponseData;
import com.slq.util.TimeLine;

/***
 * 制定产品控制类
 * @author 孙陆泉
 *
 */
@RestController
@RequestMapping("/api/manufacture")
public class ManufactureSumController {

	@Autowired
	private IManufactureService manufactureService;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	//获取图表数据
	@GetMapping("/getProductProductionFinishChartResult")
	public ResponseData getProductProductionFinishChartResult() {
		List<ManufactureChart> manufactureCharts=manufactureService.getProductProductionFinishChartResult();
		return DefaultResponseData.successResponseData(manufactureCharts);
	}
	//获取可以登记物料报损报溢的派工单
		@GetMapping("/getMeetProfitAndLossManufactures")
		public ResponseData getMeetProfitAndLossManufactures() {
			List<ManufactureSum> manufactures = manufactureService.getMeetProfitAndLossManufactures();
			return DefaultResponseData.successResponseData(manufactures);
		}
<<<<<<< HEAD
=======
	
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	@GetMapping("/getProductionProgressByManufactureId/{id}")
	public ResponseData getProductionProgressByManufactureId(@PathVariable("id")Integer id) {
		List<TimeLine> timeLines=manufactureService.getProductionProgressByManufactureId(id);
		return DefaultResponseData.successResponseData(timeLines);
	}
	//获取满足审核状态的派工单
	@GetMapping("/meetChecker")
	public ResponseData getMeetCheckerInternalProductions() {
		List<ManufactureSum> manufactures = manufactureService.getMeetCheckerInternalProductions();
		return DefaultResponseData.successResponseData(manufactures);
	}
	//获取满足登记产品的派工单
	//在产品派工单登记的时候使用
	@GetMapping("/meetInternalProductions")
	public ResponseData getMeetInternalProductions() {
		List<ManufactureSum> manufactures = manufactureService.getMeetInternalProductions();
		return DefaultResponseData.successResponseData(manufactures);
	}
	@PostMapping("/update")
	public ResponseData update(@RequestBody ManufactureSum manufacture) {
		int i =manufactureService.update(manufacture);
		return DefaultResponseData.successResponseData(i);
	}
	//在修改的时候获取
	@GetMapping("/byId/{id}")
	public ResponseData getManufactureById(@PathVariable("id") Integer id) {
		ManufactureSum manufacture=manufactureService.getManufactureById(id);
		return DefaultResponseData.successResponseData(manufacture);
	}
	@PostMapping("/search")
	public ResponseData search(@RequestBody Params params) {
		PageInfo<ManufactureSum> manufacturePage=manufactureService.searchManufactureSum(params);
		return DefaultResponseData.successResponseData(manufacturePage);
	}
	//获取需要生产派工单的格式
	@PostMapping("/getInsert")
	public ResponseData getInsert(@RequestBody JSONObject params) {
		System.out.println(JSON.toJSONString(params));
		String product_id=params.getString("product_id");
		Integer [] productionPlanDetailIds=params.getObject("productionPlanDetailIds",Integer[].class);
		ManufactureSum manfacture = manufactureService.getInsert(product_id,productionPlanDetailIds);
		return DefaultResponseData.successResponseData(manfacture);
	}
	
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody ManufactureSum manufactureSum) {
		System.out.println(JSON.toJSONString(manufactureSum));
		System.out.println("---------------------");
		System.out.println(JSON.toJSONString(manufactureSum.getProcessDesign()));
		int i = this.manufactureService.insert(manufactureSum);
		return DefaultResponseData.successResponseData(i);
	}
	
	@GetMapping("/checker")
	public ResponseData getCheckerManufacture() {
		List<ManufactureSum> manufactures = manufactureService.getCheckerManufacture();
		return DefaultResponseData.successResponseData(manufactures);
	}
	@PostMapping("pass")
	public ResponseData pass(@RequestBody ManufactureSum manufactureSum) {
		int i = this.manufactureService.pass(manufactureSum);
		return DefaultResponseData.successResponseData(i);
	}
	@PostMapping("/reject")
	public ResponseData reject(@RequestBody ManufactureSum manufactureSum) {
		int i = this.manufactureService.reject(manufactureSum);
		return DefaultResponseData.successResponseData(i);
	}
}
