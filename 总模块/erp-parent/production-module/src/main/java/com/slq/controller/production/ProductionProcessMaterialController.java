package com.slq.controller.production;
/***
 * 生产工序物料
 * @author 孙陆泉
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.service.production.IProductionProcessMaterialService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productionProcessMaterial")
public class ProductionProcessMaterialController {

	@Autowired
	private IProductionProcessMaterialService productionProcessMaterialService;
	//通过production_process生产工序id获取
	//对应的生产工序物料集合
	@GetMapping("/byParentId/{production_process_id}")
	public ResponseData byParentId(@PathVariable("production_process_id")Integer production_process_id) {
		List<ProductionProcessMaterial> productionProcessMaterials=productionProcessMaterialService.getProductionProcessMaterialByParentId(production_process_id);
		return DefaultResponseData.successResponseData(productionProcessMaterials);
	}
}
