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
import com.slq.pojo.production.ProductProfitLoss;
import com.slq.service.production.IProductProfitLossService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productProfitLoss")
public class ProductProfitLossController {

	@Autowired
	private IProductProfitLossService productProfitLossService;

	@PostMapping("/search")
	public ResponseData searchProductProfitLoss(@RequestBody Params params) {
		PageInfo<ProductProfitLoss> productProfitLossPage = productProfitLossService.searchProductProfitLoss(params);
		return DefaultResponseData.successResponseData(productProfitLossPage);
	}
	//根据编号查询 报损报溢单和报损报溢详细单集合
	@GetMapping("/byIdAll/{id}")
	public ResponseData getProductProfitLossByIdAll(@PathVariable("id")Integer id) {
		ProductProfitLoss productProfitLoss = productProfitLossService.getProductProfitLossByIdAll(id);
		return DefaultResponseData.successResponseData(productProfitLoss);
	}
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody ProductProfitLoss productProfitLoss) {
		System.out.println(JSON.toJSONString(productProfitLoss));
		int id = productProfitLossService.insert(productProfitLoss);
		return DefaultResponseData.successResponseData(id);
	}
	//获取状态在审核中的报损报溢单集合
	@GetMapping("/check")
	public ResponseData getCheckProductProfitLossList() {
		List<ProductProfitLoss> productProfitLossList = productProfitLossService.getCheckProductProfitLossList();
		return DefaultResponseData.successResponseData(productProfitLossList);
	}
	 
	@PostMapping("/pass")
	public ResponseData pass(@RequestBody ProductProfitLoss productProfitLoss) {
		System.out.println("pass:"+JSON.toJSONString(productProfitLoss));
		int i = productProfitLossService.pass(productProfitLoss);
		return DefaultResponseData.successResponseData(i);
	}
	@PostMapping("/reject")
	public ResponseData reject(@RequestBody ProductProfitLoss productProfitLoss) {
		System.out.println("reject:"+JSON.toJSONString(productProfitLoss));
		int i = productProfitLossService.reject(productProfitLoss);
		return DefaultResponseData.successResponseData(i);
	}
	
	@PostMapping("/update")
	public ResponseData update(@RequestBody ProductProfitLoss productProfitLoss) {
		System.out.println(JSON.toJSONString(productProfitLoss));
		int i = productProfitLossService.update(productProfitLoss);
		return DefaultResponseData.successResponseData(i);
	}
}
