package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.pojo.production.Product;
import com.slq.service.IProductService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

//@RestController
//@RequestMapping("/api/product")
public class ProductController1 {
  @Autowired IProductService service;
  
  @RequestMapping("/selectProList")
  public ResponseData selectAll(){
	  List<Product> list=service.selectProList();
	  System.out.println("产品信息："+JSON.toJSONString(list));
	return DefaultResponseData.successResponseData(list);
	  
  }
  
  @RequestMapping("/selectByid/{productId}")
  public ResponseData selectid(@PathVariable("productId")String productId) {
	  Product product=service.selectByid(productId);
	  System.out.println("编号产品信息："+JSON.toJSONString(product));
	return DefaultResponseData.successResponseData(product);
	  
  }
}
