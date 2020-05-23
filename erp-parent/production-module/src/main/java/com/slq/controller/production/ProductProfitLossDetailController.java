package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProductProfitLossDetail;
import com.slq.service.production.IProductProfitLossDetailService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;
/***
 * 报损报溢详细控制器
 * @author 孙陆泉
 *
 */
@RestController
@RequestMapping("/api/productProfitLossDetail")
public class ProductProfitLossDetailController {

	@Autowired
	private IProductProfitLossDetailService productProfitLossDetailService;
	
	@GetMapping("/byParentId/{parent_id}")
	public ResponseData getProductProfitLossDetailsByParentId(@PathVariable("parent_id")Integer parent_id) {
		List<ProductProfitLossDetail> productProfitLossDetails = productProfitLossDetailService.getProductProfitLossByParentId(parent_id);
		return DefaultResponseData.successResponseData(productProfitLossDetails);
	}
}

