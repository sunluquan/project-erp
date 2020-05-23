package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProductUse;
import com.slq.service.production.IProductUseService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productUse")
public class ProductUseController {

	@Autowired
	private IProductUseService productUseService;
	
	@GetMapping("/All")
	public ResponseData getProductUseAll() {
		List<ProductUse> productUses=productUseService.getProductUseAll();
		return DefaultResponseData.successResponseData(productUses);
	}
}
