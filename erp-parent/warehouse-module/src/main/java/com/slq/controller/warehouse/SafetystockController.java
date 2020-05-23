<<<<<<< HEAD
package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.Product;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.User;
import com.slq.service.production.IProductService;
import com.slq.service.warehouse.ISafetystockServive;
import com.slq.service.IGatherService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/safetystock")
public class SafetystockController {
	
	@Autowired
	ISafetystockServive safetystockService;
	
	@Autowired
	IProductService service;
	
	@Autowired IGatherService gatherservice;
	
	@RequestMapping("/getAllsafety")
	public ResponseData getAllsafety() {
		List<Safetystock> safeList = safetystockService.getAllsafety();
		System.out.println("安全库存配置信息："+JSON.toJSONString(safeList));
		return DefaultResponseData.successResponseData(safeList);
	}
	
	@RequestMapping("/search")
	public ResponseData search(){		
		List<Safetystock> saPageInfo= safetystockService.selectByReviewList();
		System.out.println("审核库存配置信息："+JSON.toJSONString(saPageInfo));
		return DefaultResponseData.successResponseData(saPageInfo);
	}
	
	@RequestMapping("/getAllsafetyById/{productId}")
	public ResponseData getAllsafetyById(@PathVariable("productId")String productId) {
		System.out.println("配置单编号"+productId);
		Safetystock safetystock=safetystockService.getAllsafetyById(productId);
		return DefaultResponseData.successResponseData(safetystock);
	}
	
	@RequestMapping("/addSafety")
	public ResponseData addSafety(@RequestBody Safetystock safetystocks) {				
		safetystockService.updatestockId(safetystocks);		
		int i=safetystockService.addSafety(safetystocks);
	    
		System.out.println("安全库存配置信息："+JSON.toJSONString(i));
		if(i>0) {			 			 			
			return DefaultResponseData.successResponseData(i);
		}else {
				return DefaultResponseData.ADD_ERROR;
		}
	}
	
	@RequestMapping("/updateChecktag")
	public ResponseData updateid(@RequestBody Safetystock safetystocks) {
		int i=safetystockService.updatestockId(safetystocks);
		System.out.println("修改的审核状态："+JSON.toJSONString(safetystocks.getId()));
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	
	@RequestMapping("/Checktag")
	public ResponseData updateChecktag(@RequestBody Safetystock safetystock) {
		int i=safetystockService.updateChecktag(safetystock);
		System.out.println("修改的审核状态："+JSON.toJSONString(safetystock.getId()));
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	/*
	 * 修改传3个值:是否通过值、当前配置单的单号、审核人的名字
	 */
	@RequestMapping("/updateSefeStatus")
	public ResponseData updateSefeStatus(@RequestBody Safetystock safetystock) {			
		int i = safetystockService.updateSefeStatus(safetystock);
		System.out.println(safetystock.getId());
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	@RequestMapping("/selectSafetyStocks")
	public ResponseData select() {
		List<Safetystock>  list=safetystockService.selectSafetystocks();
				return DefaultResponseData.successResponseData(list);
		
	}
	@RequestMapping("/selectByName/{productName}")
	public ResponseData selectName(@PathVariable("productName")String productName) {
		List<Safetystock> selectNameList=safetystockService.selectByName(productName);
		System.out.println(selectNameList);
		return DefaultResponseData.successResponseData(selectNameList);
		
	}
	
	@RequestMapping("/selectByTime/{registertime}")
	public ResponseData selecttime(@PathVariable("registertime")String registertime) {
		List<Safetystock> selectregistertimeList=safetystockService.selectByName(registertime);
		System.out.println(selectregistertimeList);
		return DefaultResponseData.successResponseData(selectregistertimeList);
		
	}
	
	@RequestMapping("/selectStockChange")
	public ResponseData select2() {
		List<Safetystock> list=safetystockService.selectStockChange();
		return DefaultResponseData.successResponseData(list);
		
	}
	@RequestMapping("/updateStockChange")
	public ResponseData update(@RequestBody Safetystock safetystock) {
		int i=safetystockService.updateStockChange(safetystock);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
		
	}
}
=======
package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.Product;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.User;
import com.slq.service.production.IProductService;
import com.slq.service.warehouse.ISafetystockServive;
import com.slq.service.IGatherService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/safetystock")
public class SafetystockController {
	
	@Autowired
	ISafetystockServive safetystockService;
	
	@Autowired
	IProductService service;
	
	@Autowired IGatherService gatherservice;
	
	@RequestMapping("/getAllsafety")
	public ResponseData getAllsafety() {
		List<Safetystock> safeList = safetystockService.getAllsafety();
		System.out.println("安全库存配置信息："+JSON.toJSONString(safeList));
		return DefaultResponseData.successResponseData(safeList);
	}
	
	@RequestMapping("/search")
	public ResponseData search(){		
		List<Safetystock> saPageInfo= safetystockService.selectByReviewList();
		System.out.println("审核库存配置信息："+JSON.toJSONString(saPageInfo));
		return DefaultResponseData.successResponseData(saPageInfo);
	}
	
	@RequestMapping("/getAllsafetyById/{productId}")
	public ResponseData getAllsafetyById(@PathVariable("productId")String productId) {
		System.out.println("配置单编号"+productId);
		Safetystock safetystock=safetystockService.getAllsafetyById(productId);
		return DefaultResponseData.successResponseData(safetystock);
	}
	
	@RequestMapping("/addSafety")
	public ResponseData addSafety(@RequestBody Safetystock safetystocks) {				
		safetystockService.updatestockId(safetystocks);		
		int i=safetystockService.addSafety(safetystocks);
	    
		System.out.println("安全库存配置信息："+JSON.toJSONString(i));
		if(i>0) {			 			 			
			return DefaultResponseData.successResponseData(i);
		}else {
				return DefaultResponseData.ADD_ERROR;
		}
	}
	
	@RequestMapping("/updateChecktag")
	public ResponseData updateid(@RequestBody Safetystock safetystocks) {
		int i=safetystockService.updatestockId(safetystocks);
		System.out.println("修改的审核状态："+JSON.toJSONString(safetystocks.getId()));
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	
	@RequestMapping("/Checktag")
	public ResponseData updateChecktag(@RequestBody Safetystock safetystock) {
		int i=safetystockService.updateChecktag(safetystock);
		System.out.println("修改的审核状态："+JSON.toJSONString(safetystock.getId()));
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	/*
	 * 修改传3个值:是否通过值、当前配置单的单号、审核人的名字
	 */
	@RequestMapping("/updateSefeStatus")
	public ResponseData updateSefeStatus(@RequestBody Safetystock safetystock) {			
		int i = safetystockService.updateSefeStatus(safetystock);
		System.out.println(safetystock.getId());
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	@RequestMapping("/selectSafetyStocks")
	public ResponseData select() {
		List<Safetystock>  list=safetystockService.selectSafetystocks();
				return DefaultResponseData.successResponseData(list);
		
	}
	@RequestMapping("/selectByName/{productName}")
	public ResponseData selectName(@PathVariable("productName")String productName) {
		List<Safetystock> selectNameList=safetystockService.selectByName(productName);
		System.out.println(selectNameList);
		return DefaultResponseData.successResponseData(selectNameList);
		
	}
	
	@RequestMapping("/selectByTime/{registertime}")
	public ResponseData selecttime(@PathVariable("registertime")String registertime) {
		List<Safetystock> selectregistertimeList=safetystockService.selectByName(registertime);
		System.out.println(selectregistertimeList);
		return DefaultResponseData.successResponseData(selectregistertimeList);
		
	}
	
	@RequestMapping("/selectStockChange")
	public ResponseData select2() {
		List<Safetystock> list=safetystockService.selectStockChange();
		return DefaultResponseData.successResponseData(list);
		
	}
	@RequestMapping("/updateStockChange")
	public ResponseData update(@RequestBody Safetystock safetystock) {
		int i=safetystockService.updateStockChange(safetystock);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
		
	}
}
>>>>>>> warehouse
