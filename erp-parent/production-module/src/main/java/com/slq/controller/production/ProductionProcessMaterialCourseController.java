package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProductionProcessMaterialCourse;
import com.slq.service.production.IProductionProcessMaterialCourseService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productionProcessMaterialCourse")
public class ProductionProcessMaterialCourseController {

	@Autowired
	private IProductionProcessMaterialCourseService productionProcessMaterialCourseService;
	
	@GetMapping("/getParentId/{parent_id}")
	public ResponseData getProductionProcessMaterialCourseByParentId(@PathVariable("parent_id")Integer parent_id) {
		List<ProductionProcessMaterialCourse> productionProcessMaterialCourses=productionProcessMaterialCourseService.byParentId(parent_id);
		return DefaultResponseData.successResponseData(productionProcessMaterialCourses);
	}
}
