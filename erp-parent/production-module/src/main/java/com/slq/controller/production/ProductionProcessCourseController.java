package com.slq.controller.production;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/***
 * 生产工序过程
 * @author 孙陆泉
 *
 */

import com.alibaba.fastjson.JSON;
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessCourse;
import com.slq.service.production.IProductionProcessCourseService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;
<<<<<<< HEAD
=======

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
@RestController
@RequestMapping("/api/productionProcessCourse")
public class ProductionProcessCourseController {

	@Autowired
	private IProductionProcessCourseService productionProcessCourseService;

	@PostMapping("/register")
	public ResponseData register(@RequestBody ManufactureSum manufacture) {
		System.out.println(JSON.toJSONString(manufacture));
		int i = productionProcessCourseService.register(manufacture);
		return DefaultResponseData.successResponseData(i);
	}
<<<<<<< HEAD
=======

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
//	@GetMapping("/byProcessIdAndmanufactureId")
//	public ResponseData getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,String process_id) {
//		ProductionProcessCourse productionProcessCourse = productionProcessCourseService.getProductionProcessCourseByProcessIdAndmanufactureId(manufacture_id,process_id);
//		return DefaultResponseData.successResponseData(productionProcessCourse);
//	}
	@GetMapping("/byProcessIdAndmanufactureId")
<<<<<<< HEAD
	public ResponseData getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,Integer production_process_id,String process_id) {
		ProductionProcessCourse productionProcessCourse = productionProcessCourseService.getProductionProcessCourseByProcessIdAndmanufactureId(manufacture_id,production_process_id,process_id);
		System.out.println(JSON.toJSONString(productionProcessCourse));
		return DefaultResponseData.successResponseData(productionProcessCourse);
	}
	//产品登记得审核
	@PostMapping("/checker")
	public ResponseData checker(@RequestBody ManufactureSum manufacture) {
		System.out.println(JSON.toJSONString(manufacture));
		//返回true 说明没有需要审核的登记和交接了
		boolean fals = productionProcessCourseService.checker(manufacture);
		return DefaultResponseData.successResponseData(fals);
	}
	
=======
	public ResponseData getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			Integer production_process_id, String process_id) {
		ProductionProcessCourse productionProcessCourse = productionProcessCourseService
				.getProductionProcessCourseByProcessIdAndmanufactureId(manufacture_id, production_process_id,
						process_id);
		System.out.println(JSON.toJSONString(productionProcessCourse));
		return DefaultResponseData.successResponseData(productionProcessCourse);
	}

	// 产品登记得审核
	@PostMapping("/checker")
	public ResponseData checker(@RequestBody ManufactureSum manufacture) {
		System.out.println(JSON.toJSONString(manufacture));
		// 返回true 说明没有需要审核的登记和交接了
		boolean fals = productionProcessCourseService.checker(manufacture);
		return DefaultResponseData.successResponseData(fals);
	}

	// 根据生产主表编号和 工序编号获取
	// 这个工序的生产工序过程记录集合
	@GetMapping("/byManufactureIdAndProcessId")
	public ResponseData productionProcessCoursebyManufactureIdAndProcessId(Integer manufacture_id, String process_id) {
		List<ProductionProcessCourse> productionProcessCourses = productionProcessCourseService
				.productionProcessCoursebyManufactureIdAndProcessId(manufacture_id, process_id);
		return DefaultResponseData.successResponseData(productionProcessCourses);
	}
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
