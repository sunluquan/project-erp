package com.slq.controller.production;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.MaterialDesignDetail;
import com.slq.pojo.production.ProductMaterialDesign;
import com.slq.service.production.IMaterialDesignService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

/***
 * 产品物料组成设计明细
 * @author 孙陆泉
 *
 */
@RestController
@RequestMapping("/api/materialDesign")
public class MaterialDesignController {
	@Autowired
	private IMaterialDesignService materialDesignService;
	
	@GetMapping("/useState/{product_id}")//查看该产品对应的物料使用情况
	//查看该产品是否还有物料未使用完  返回的是未使用完的物料数量
	public ResponseData checkMaterialUseState(@PathVariable("product_id")String product_id) {
		int i = materialDesignService.checkMaterialUseState(product_id);
		return DefaultResponseData.successResponseData(i);
	}
//	@PostMapping("/insert")
//	public ResponseData insert(@RequestBody ProductMaterialDesign productMaterialDesign) {
//		System.out.println(JSON.toJSONString(productMaterialDesign));
//		int i = materialDesignService.insert(productMaterialDesign);
//		return null;
//	}
	@PostMapping("/search")
	public ResponseData search(@RequestBody Params params) {
		PageInfo<ProductMaterialDesign> ProductMaterialDesignPage=materialDesignService.search(params);
		return DefaultResponseData.successResponseData(ProductMaterialDesignPage);
	}
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody JSONObject data) {
		ProductMaterialDesign productMaterialDesign = data.getObject("productMaterialDesign", ProductMaterialDesign.class);
		productMaterialDesign.setMaterials(data.getObject("materials",new ArrayList<MaterialDesignDetail>().getClass()));// 
		System.out.println(JSON.toJSONString(productMaterialDesign));
		try {
			int i = materialDesignService.insert(productMaterialDesign);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	//获取所有需要审核的产品物料设计单
	@GetMapping("/checker")
	public ResponseData checkerMaterialDesign() {
		List<ProductMaterialDesign> productMaterialDesigns=materialDesignService.checkerMaterialDesign();
		return DefaultResponseData.successResponseData(productMaterialDesigns);
	}
	//做为确认审核时候查询的单据  有详细物料组成
	@GetMapping("/byId/{id}")
	public ResponseData getMateralDesignById(@PathVariable("id") Integer id) {
		ProductMaterialDesign productMaterialDesign=materialDesignService.getMateralDesignById(id);
		return DefaultResponseData.successResponseData(productMaterialDesign);
	}
	//通过
	@PostMapping("/pass")
	public ResponseData materialDesignPass(@RequestBody ProductMaterialDesign productMaterialDesign) {
		try {
			int i=materialDesignService.materialDesignPass(productMaterialDesign);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	//驳回
	@PostMapping("/reject")
	public ResponseData materialDesignReject(@RequestBody ProductMaterialDesign productMaterialDesign) {
		try {
			int i=materialDesignService.materialDesignReject(productMaterialDesign);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	@PostMapping("/update")
	public ResponseData update(@RequestBody ProductMaterialDesign design) {
		try {
			int i=materialDesignService.update(design);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
}
