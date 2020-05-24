package com.slq.controller.common1;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.Product;
import com.slq.service.production.IProductService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	public ProductController() {
		super();
		System.out.println("------------------------------------------------------------------------------------");
	}
	@Autowired
	private IProductService productService;
	//获取符合制定派工单的产品
	//按产品划分
	@GetMapping("/meetDispatching")
	public ResponseData getMeetDispatchingProduction() {
		List<Product> products=productService.getMeetDispatchingProduction();
		return DefaultResponseData.successResponseData(products);
	}
	// 获取符合制定工序设计的产品 Accord符合 making制定 
	//process 工序 
	@GetMapping("/accordMakingProcess")
	public ResponseData getAccordMakingProcessProducts() {
		List<Product> products = productService.getAccordMakingProcessProducts();
		return DefaultResponseData.successResponseData(products);
	}
	//获取所有审核中的产品设计
	@GetMapping("/checker")
	public ResponseData checker_search() {
		List<Product> checkerProducts = productService.checker_search();
		return DefaultResponseData.successResponseData(checkerProducts);
	}
	//这个是在新增生产计划的时候选择产品的时候用到
	//他需要能够生产  也就是  物料设计 工序设计  工序物料设计 等都要为已经设计
	@PostMapping("/searchCanProductionProduct")
	public ResponseData searchCanProductionProduct(@RequestBody Params params) {
		PageInfo<Product> productPage = productService.searchCanProductionProduct(params);
		return DefaultResponseData.successResponseData(productPage);
	}
	@PostMapping("/search")
	public ResponseData search(@RequestBody JSONObject data) {
		System.out.println(JSON.toJSONString(data));
		System.out.println(SecurityUtils.getSubject().getSession().getId());
		Params params = data.getObject("params", Params.class);
		String[] checkKinds = data.getObject("checkKinds", String[].class);
		params.setReplenishParams("checkKinds", checkKinds);
		// @RequestBody Params params
		System.out.println(JSON.toJSONString("params:" + params));
		PageInfo<Product> productPage = productService.productSearch(params);
		return DefaultResponseData.successResponseData(productPage);
	}
	//产品驳回
	@PostMapping("/reject")
	public ResponseData rejectProductDesign(@RequestBody Product product) {
		try {
			int i = productService.rejectProductDesign(product);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	//旧的 产品驳回
	@GetMapping("/reject/{id}")
	public ResponseData rejectProductDesign(String cause,@PathVariable Integer id) {
		try {
			int i = productService.rejectProductDesign(id,cause);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	//设置审核的通过状态
	@PostMapping("/updateCheckTag")
	public ResponseData updateProductAndPass(@RequestBody JSONObject data) {
		System.out.println(JSON.toJSONString(data));
		try {
			Product product = JSON.parseObject(JSON.toJSONString(data),Product.class);
			System.out.println(JSON.toJSONString(product));
			int i = productService.updateCheckTag(product);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	@PostMapping("/update")
	public ResponseData updateProduct(@RequestBody Product product) {
		try {
			int i = productService.update(product);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
		
	}
	//审核时 获取的产品  和修改不同的是  字段不同
	@GetMapping("/checkerProduct/{id}")
	public ResponseData getCheckerProduct(@PathVariable("id")Integer id) {
		Product product = productService.getCheckerProduct(id);
		return DefaultResponseData.successResponseData(product);
	}
	//修改时候
	@GetMapping("/getById/{id}")
	public ResponseData getById(@PathVariable("id")Integer id) {
		Product product = productService.getProductById(id);
		return DefaultResponseData.successResponseData(product);
	}
//	@PostMapping("/insert")
//	public ResponseData insert(@RequestBody JSONObject data) {
//		System.out.println(JSON.toJSONString(data));
//		Product product=JSON.parseObject(JSON.toJSONString(data),Product.class);
//		System.out.println(JSON.toJSONString(product));
//		return null;
////		System.out.println("product:"+JSON.toJSONString(product));
////		try {
////			int i =productService.insertProduct(product);
////			return DefaultResponseData.successResponseData(i);
////		} catch (Exception e) {
////			return DefaultResponseData.errorResponseData(e.getMessage());
////		}
//	}
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody JSONObject data) {
		Product product = JSON.parseObject(JSON.toJSONString(data), Product.class);
		System.out.println("product:" + JSON.toJSONString(product));
		System.out.println(SecurityUtils.getSubject().getSession().getId());
		try {
			int i = productService.insertProduct(product);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			e.printStackTrace();
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	//查询所有产品  物料为设计但是通过审核了的
	@GetMapping("/wantMaterialDesign")
	public ResponseData getProductWantMaterialDesign() {
		List<Product> products=productService.getProductWantMaterialDesign();
		return DefaultResponseData.successResponseData(products);
	}
	//库存模块使用的方法
	//获取没有配置库存的
	@GetMapping("/getNotConfigureInventoryProducts")
	public ResponseData getNotConfigureInventoryProducts() {
		List<Product> products=productService.getNotConfigureInventoryProducts();
		return DefaultResponseData.successResponseData(products);
	}
}
