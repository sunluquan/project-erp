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
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessCourse;
import com.slq.service.production.IProductionProcessService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

/***
 * 生产工序
 * 
 * @author 孙陆泉
 *
 */
@RestController
@RequestMapping("/api/productionProcess")
public class ProductionProcessController {

	@Autowired
	private IProductionProcessService productionProcessService;

	// 通过 根据产品编号 通过processDes
	// 查询这个工序总表下的工序详细 :processDetail
	// 然后转换为生产工序
	/// api/productionProcess/byParentId/${manufacture_id}
	@GetMapping("/byParentId/{manufacture_id}")
	public ResponseData getProcessDetailTransitionProductionProcess(
			@PathVariable("manufacture_id") Integer manufacture_id) {
		List<ProductionProcess> productionProcessList = productionProcessService
				.getProcessDetailTransitionProductionProcess(manufacture_id);
		return DefaultResponseData.successResponseData(productionProcessList);
	}

	@PostMapping("/register")
	public ResponseData register(@RequestBody ManufactureSum manufacture) {
		int i = productionProcessService.register(manufacture);
		return DefaultResponseData.successResponseData(i);
	}

//	@PostMapping("/insert")
//	public ResponseData insert() {
//		
//		return 
//	}
	// 复核生产登记的时候
	// 联合查询生产工序 和生产过程工序
	// 在联合查询生产工序物料和生产过程工序物料
	@GetMapping("/byProcessIdAndmanufactureId")
	public ResponseData getProductionProcessByProcessIdAndmanufactureId(Integer manufacture_id, String process_id) {
		ProductionProcess productionProcess = productionProcessService
				.getProductionProcessCourseByProcessIdAndmanufactureId(manufacture_id, process_id);
		return DefaultResponseData.successResponseData(productionProcess);
	}

	// 工序完成得交接
	@PostMapping("/handover")
	public ResponseData handover(@RequestBody ProductionProcess productionProcess) {
		System.out.println(JSON.toJSONString(productionProcess));
		int i = productionProcessService.handover(productionProcess);
		return DefaultResponseData.successResponseData(i);
	}
	//审核交接
	@PostMapping("/checker_handover")
	public ResponseData checker_handover(@RequestBody ProductionProcess productionProcess) {
		System.out.println(JSON.toJSONString(productionProcess));
		boolean fals = productionProcessService.checker_handover(productionProcess);
		return DefaultResponseData.successResponseData(fals);
	}
}
