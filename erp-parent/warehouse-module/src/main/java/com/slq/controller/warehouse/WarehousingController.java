package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.IGatherService;
import com.slq.service.warehouse.IWarehousingService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/gather")
public class WarehousingController {
   @Autowired IWarehousingService service;
   
   @RequestMapping("/addgather")
   public ResponseData insert(@RequestBody Gather gather) {
	   System.out.println(JSON.toJSONString(gather));
	   int result=service.addgather(gather);
	   System.out.println(result);
	   if(result>0) {
			return DefaultResponseData.successResponseData(result);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}
	   
   }
     
   @RequestMapping("/selectreview")
   public ResponseData select() {
	   List<Gather> list =service.gatherReview();	   
	  return DefaultResponseData.successResponseData(list);	   
   }
   
  /* 入库调度*/
   @RequestMapping("/gatherScheduling")
   public ResponseData selectScheduling() {
	   List<Gather> list1=service.gatherScheduling();
	return DefaultResponseData.successResponseData(list1);
	   
   }
   @RequestMapping("/getSchedulingid/{gatherId}")
   public ResponseData selectid(@PathVariable("gatherId") String gatherId) {
	   List<Gather> gathers=service.getSchedulingid(gatherId);
	   
	return DefaultResponseData.successResponseData(gathers);
	   
   }
   
   @RequestMapping("/getGatherSafetystock/{productId}")
   public ResponseData getDataByid(@PathVariable("productId") String productId) {
	    Gather gather=service.getGatherDetailsByid(productId);
	    System.out.println(gather);
	   return DefaultResponseData.successResponseData(gather);
	   
   }
   
   @GetMapping("/getByidGather/{productId}")
   public ResponseData getDataByid2(@PathVariable("productId") String productId) {
	   List<Gather> gather=service.getByidGather(productId);
	   System.out.println(gather);
	   return DefaultResponseData.successResponseData(gather);
	   
   }
   
   @GetMapping("/StorehousesGather/{productId}")
   public ResponseData selectStorehousesGather(@PathVariable("productId") String productId) {
	   List<Storehouse> storehouse=service.selectStorehousesGather(productId);
	   System.out.println(storehouse);
	   return DefaultResponseData.successResponseData(storehouse);
	   
   }
   
   @RequestMapping("/updgathernum")
   public ResponseData getnum(@RequestBody Gather gather) {
	   int i=service.updgathernum(gather);
	   System.out.println(i);
	   if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}	   
   }
   @RequestMapping("/updgatherDetailsNum")
   public ResponseData updDtails(@RequestBody Gather scheduling) {
	   int i=service.updgatherDetailsNum(scheduling);
	   if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}	  
	   
   }
   @PostMapping("/updqwenum")
   public ResponseData updDtails1(@RequestBody Gather scheduling) {
	   int i=service.updqwenum(scheduling);
	   if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}	  
	   
   }
   /* 入库登记*/
   @RequestMapping("/gatherRegistrationList")
   public ResponseData selectRegData() {
	   List<Gather> list=service.gatherRegistrationList();
	return DefaultResponseData.successResponseData(list);
	   
   }
   
   @RequestMapping("/gatherRegistrationByid/{gatherId}")
   public ResponseData gatherid(@PathVariable("gatherId") String gatherId) {
	  List<Gather> list=service.gatherRegistrationByid(gatherId);
	return DefaultResponseData.successResponseData(list);
	   
   }
   //根据编号获取入库单  和入库详细单
   @GetMapping("/byIdAll/{gatherId}")
   public ResponseData getGatherByIdAll(@PathVariable("gatherId") String gatherId) {
	   Gather gather = service.getGatherByIdAll(gatherId);
	   System.out.println(JSON.toJSONString(gather));
	   return DefaultResponseData.successResponseData(gather);
   }
   
   @PostMapping("/register")
   public ResponseData register(@RequestBody Gather registration) {
	   int i=service.register(registration);	   
	   return DefaultResponseData.successResponseData(i);			  
   }
   
   @PostMapping("/updReview")
   public ResponseData updreview(@RequestBody Gather registration) {
	   int i=service.updcheck(registration);
	   
			return DefaultResponseData.successResponseData(i);
		
   }
   
   @RequestMapping("/selectRegistration")
   public ResponseData selectRegistration() {
	   List<Gather> i=service.selectRegistration();
	   
	return DefaultResponseData.successResponseData(i);
		
   }
   
   @PostMapping("/updgathersum")
   public ResponseData updgathersum(@RequestBody Gather scheduling) {
	   int i=service.updgathersum(scheduling);	   
	   return DefaultResponseData.successResponseData(i);			  
   }
}


