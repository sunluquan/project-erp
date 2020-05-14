package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.pojo.warehouse.Gather;
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
   @RequestMapping("/gatherScheduling")
   public ResponseData selectScheduling() {
	   List<Gather> list1=service.gatherScheduling();
	return DefaultResponseData.successResponseData(list1);
	   
   }
   @RequestMapping("/getSchedulingid/{stockid}")
   public ResponseData selectid(@PathVariable("stockid") String stockid) {
	   List<Gather> gathers=service.getSchedulingid(stockid);
	   
	return DefaultResponseData.successResponseData(gathers);
	   
   }
   @RequestMapping("/getByidGather/{id}")
   public ResponseData getDataByid(@PathVariable("id") Integer id) {
	   Gather gather=service.getByidGather(id);
	   System.out.println(gather);
	return DefaultResponseData.successResponseData(gather);
	   
   }
}
