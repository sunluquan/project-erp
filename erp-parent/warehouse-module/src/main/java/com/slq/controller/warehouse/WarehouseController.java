<<<<<<< HEAD
package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.slq.common.Params;
import com.slq.pojo.production.Product;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.IWarehouseService;
import com.slq.service.production.IProductService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
		
	
	public WarehouseController() {
		super();
	System.out.println("-------------------------------------------------------");
	}

	@Autowired
	private IWarehouseService wareHouseService;
	
	@Autowired IProductService productService;
	
	@RequestMapping("/getAllHouse")
	public ResponseData getAllHouse() {
		List<Storehouse> houseList = wareHouseService.selectAll();
		System.out.println("仓库信息："+JSON.toJSONString(houseList));
		return DefaultResponseData.successResponseData(houseList);
	}
	
	@RequestMapping("/getByid/{id}")
    public ResponseData byid(@PathVariable("id") Integer id) {
//		Product product=productService.selectByid(productId);
//		System.out.println(product);
//		int usetype=product.getUse_type();
		List<Storehouse> strList=wareHouseService.selectByid(id);
		System.out.println(strList);
		return DefaultResponseData.successResponseData(strList);
		
	}
    
	@RequestMapping("/updateStatus/{sid}/{newstoreStatus}")
	public ResponseData updatePermission(@PathVariable("sid")Integer sid,@PathVariable("newstoreStatus")Integer newstoreStatus) {
		int i = wareHouseService.updateWarehouseStatus(sid, newstoreStatus);
		return DefaultResponseData.successResponseData(i);
	}
	@RequestMapping("/selectByName/{storeName}")
	public ResponseData SelectByIdName(@PathVariable("storeName") String storeName) {
		List<Storehouse> byList=wareHouseService.selectByName(storeName);
		return DefaultResponseData.successResponseData(byList);
		
	}
	
	@RequestMapping("/getHouseById/{sid}")
	public ResponseData getHouseById(@PathVariable("sid") Integer sid) {
		System.out.println("仓库编号:"+sid);
		Storehouse house = wareHouseService.getHouseById(sid);
		return DefaultResponseData.successResponseData(house);
	}
	
	@RequestMapping("/addHouse")
	public ResponseData addHouse(@RequestBody Storehouse house) {
		System.out.println(house);
		int i = wareHouseService.addHouse(house);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}
	}
	
	@RequestMapping("/updateHouse")
	public ResponseData updateHouse(@RequestBody Storehouse house) {
		System.out.println(house);
		int i = wareHouseService.updateHouse(house);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	
	@RequestMapping("/delHouse/{sid}")
	public ResponseData delHouse(@PathVariable("sid")Integer sid) {
		int i;
		i = wareHouseService.delHouse(sid);
		return i>0?DefaultResponseData.successResponseData(DefaultResponseData.DELETE_SUCCESS,i):DefaultResponseData.DELETE_ERROR;
	}
}
=======
package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.IWarehouseService;
import com.slq.service.production.IProductService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
		
	
	public WarehouseController() {
		super();
	System.out.println("-------------------------------------------------------");
	}

	@Autowired
	private IWarehouseService wareHouseService;
	
	@Autowired IProductService productService;
	
	@RequestMapping("/getAllHouse")
	public ResponseData getAllHouse() {
		List<Storehouse> houseList = wareHouseService.selectAll();
		System.out.println("仓库信息："+JSON.toJSONString(houseList));
		return DefaultResponseData.successResponseData(houseList);
	}
	
	@RequestMapping("/getByid/{id}")
    public ResponseData byid(@PathVariable("id") Integer id) {
//		Product product=productService.selectByid(productId);
//		System.out.println(product);
//		int usetype=product.getUse_type();
		List<Storehouse> strList=wareHouseService.selectByid(id);
		System.out.println(strList);
		return DefaultResponseData.successResponseData(strList);
		
	}
    
	@RequestMapping("/updateStatus/{sid}/{newstoreStatus}")
	public ResponseData updatePermission(@PathVariable("sid")Integer sid,@PathVariable("newstoreStatus")Integer newstoreStatus) {
		int i = wareHouseService.updateWarehouseStatus(sid, newstoreStatus);
		return DefaultResponseData.successResponseData(i);
	}
	@RequestMapping("/selectByName/{storeName}")
	public ResponseData SelectByIdName(@PathVariable("storeName") String storeName) {
		List<Storehouse> byList=wareHouseService.selectByName(storeName);
		return DefaultResponseData.successResponseData(byList);
		
	}
	
	@RequestMapping("/getHouseById/{sid}")
	public ResponseData getHouseById(@PathVariable("sid") Integer sid) {
		System.out.println("仓库编号:"+sid);
		Storehouse house = wareHouseService.getHouseById(sid);
		return DefaultResponseData.successResponseData(house);
	}
	
	@RequestMapping("/addHouse")
	public ResponseData addHouse(@RequestBody Storehouse house) {
		System.out.println(house);
		int i = wareHouseService.addHouse(house);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}
	}
	
	@RequestMapping("/updateHouse")
	public ResponseData updateHouse(@RequestBody Storehouse house) {
		System.out.println(house);
		int i = wareHouseService.updateHouse(house);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
	
	@RequestMapping("/delHouse/{sid}")
	public ResponseData delHouse(@PathVariable("sid")Integer sid) {
		int i;
		i = wareHouseService.delHouse(sid);
		return i>0?DefaultResponseData.successResponseData(DefaultResponseData.DELETE_SUCCESS,i):DefaultResponseData.DELETE_ERROR;
	}
}
>>>>>>> warehouse
