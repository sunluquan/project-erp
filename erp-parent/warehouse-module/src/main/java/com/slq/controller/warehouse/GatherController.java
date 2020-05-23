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
import com.slq.service.IGatherService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/gather")
public class GatherController {
   @Autowired IGatherService service;
   
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
   @RequestMapping("/getByidGather/{id}")
   public ResponseData getDataByid(@PathVariable("id") Integer id) {
	   Gather gather=service.getByidGather(id);
	   System.out.println(gather);
	   return DefaultResponseData.successResponseData(gather);
	   
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
	   return DefaultResponseData.successResponseData(gather);
   }
   
   @PostMapping("/register")
   public ResponseData register(@RequestBody Gather registration) {
	   int i=service.register(registration);
	   if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}	  
   }
}


