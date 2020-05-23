package com.slq.controller.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.Product;
import com.slq.service.production.IProductService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;
/***
 * 物料  分支  那个太多了
 * @author 孙陆泉
 *
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController {

	@Autowired
	private IProductService productService;
	
	//分页查询  是物料的且审核通过了的
	@PostMapping("/search")
	public ResponseData searchMaterial(@RequestBody Params params) {
		PageInfo<Product> productPage =  productService.searchMaterial(params);
		return DefaultResponseData.successResponseData(productPage);
	}
}
