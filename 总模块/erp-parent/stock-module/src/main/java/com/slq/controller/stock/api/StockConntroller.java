package com.slq.controller.stock.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.slq.pojo.production.Product;
import com.slq.pojo.production.ProductKind;
import com.slq.pojo.production.ProductUse;
import com.slq.pojo.stock.*;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.stock.IStockService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/stock")
public class StockConntroller {

	@Autowired
	private IStockService stockService;

	@PostMapping("/product_add")
	public ResponseData checkuid(String searchValue) {

		List<Product> userPageInfo = stockService.product_add(searchValue);
		//System.out.println(userPageInfo.get(0).getProduct_use().getUseName() + "dsajklfGJHGDS");
		return DefaultResponseData.successResponseData(userPageInfo);
	}

//		@RequestMapping("/qgd")
//		public String qgd() {
//			System.out.println(IDGenerateUtil.getID(Constant.PRODUCT_DESIGN)+"张加好");
//		String id=IDGenerateUtil.getID(Constant.PRODUCT_DESIGN);
//		return id;
//		}
	@PostMapping("/select_pkind")
	public ResponseData pkind() {
		List<ProductKind> use = stockService.pkind();
		System.out.println(use.get(0).getKindName() + "gfdsgr");
		// System.out.println(userPageInfo.get(0).getProductUse().getUserName()+"fdsajjksdgfgkjfgj");
		return DefaultResponseData.successResponseData(use);
	}

	// 采购类型
	@PostMapping("/select_use")
	public ResponseData fd() {System.out.println("dfsafhszdjkfh");
		List<ProductUse> fd = stockService.use();
		return DefaultResponseData.successResponseData(fd);
	}

	@PostMapping("/select_provideInfo")
	public ResponseData provideInfo() {
		List<provideInfo> fd = stockService.provideInfo();
		return DefaultResponseData.successResponseData(fd);
	}

//		@PostMapping("/insert_stock")
//		public int insertUser() {
//			System.out.println("在纠结纠结");
//			return 1;
//		}
	// 增加请购主表
	@PostMapping(value = "/insert_stock")
//		@RequiresRoles("business")
	public int save(@RequestBody List<stockapply> goodsPictureList) {
		System.out.println("dafdas" + goodsPictureList.get(0).getProduct().get(0).getProduct_id());
		int add = stockService.insert_stock(goodsPictureList);
		return add;
	}

	// 增加采购主表
	@PostMapping(value = "/insert_stockorder")
//		@RequiresRoles("business")
	public int stockorder(@RequestBody List<stockorder> goodsPictureList) {
		System.out.println(goodsPictureList.get(0).getStockapplydetails().get(0).getMobile()
				+ goodsPictureList.get(0).getStodept() + "fdgfddgdsg");
		int add = stockService.insert_stockorder(goodsPictureList);
		return add;
	}
	// 增加采购入库主表
		@PostMapping(value = "/insert_stockstoragetable")
//			@RequiresRoles("business")
		public int stockstoragetable(@RequestBody List<stockstoragetable> goodsPictureList) {
			System.out.println("qwertyyui");
			System.out.println(goodsPictureList.get(0).getStockorderdetails().get(0).getSodmaterielname() + "fdgfddgdsg");
			int add = stockService.insert_stockstoragetable(goodsPictureList);
			return add;
		}
	
	// 查询请购主表
	@PostMapping("/select_stock")
	public ResponseData select_stock() {
		List<stockapply> userPageInfo = stockService.select_stock();
		System.out.println(userPageInfo.get(0).getApptype());
		return DefaultResponseData.successResponseData(userPageInfo);
	}

	@PostMapping("/select_stock_id")
	public ResponseData select_stock_id() {
		List<stockapply> userPageInfo = stockService.select_stock_id();
		return DefaultResponseData.successResponseData(userPageInfo);
	}

	@PostMapping("/select_stockapplydetails")
	public ResponseData select_stockapplydetails(@RequestBody int id) {
		System.out.println(id + "name");
		List<stockapplydetails> userPageInfo = stockService.select_stockapplydetails(id);
		System.out.println(userPageInfo.get(0).getApdmateridlname());
		return DefaultResponseData.successResponseData(userPageInfo);
	}

	@PostMapping("/update_stock")
	public int update_stock(@RequestBody int id) {
		System.out.println(id + "name");
		int userPageInfo = stockService.update_stock(id);
		return userPageInfo;
	}

	@PostMapping("/update_s")
	public int update_s(@RequestBody int id) {
		System.out.println(id + "name");
		int userPageInfo = stockService.update_s(id);
		return userPageInfo;
	}

	// 查询订单表
	@PostMapping("/select_stockorder")
	public ResponseData stockorder() {
		List<stockorder> userPageInfo = stockService.select_stockorder();
		for(stockorder so:userPageInfo) {
			System.out.println(so.getStockorderDetails());
		}
		return DefaultResponseData.successResponseData(userPageInfo);
	}
	// 采购详细表

	@PostMapping("/select_stockorderdetails")
	public ResponseData select_stockorderdetails(@RequestBody int id) {
		List<stockapplydetails> userPageInfo = stockService.select_stockorderdetails(id);
		return DefaultResponseData.successResponseData(userPageInfo);
	}
	//查询订单入库

	@PostMapping("/s_stockstoragetable")
	public ResponseData select_stockstoragetable() {
		
		List<stockstoragetable> userPageInfo = stockService.s_stockstoragetable();
		return DefaultResponseData.successResponseData(userPageInfo);
	}
	//查询订单入库详情
	@PostMapping("/select_stockstoragedetails")
	public ResponseData select_stockstoragedetails(@RequestBody int id) {
		List<stockstoragedetails> userPageInfo = stockService.select_stockstoragedetails(id);
		return DefaultResponseData.successResponseData(userPageInfo);
	}
	
	// 修改订单表状态
	@PostMapping("/update_stockorder")
	public int update_stockorder(@RequestBody int id) {
		int userPageInfo = stockService.update_stockorder(id);
		return userPageInfo;
	}
	// 修改入库订单表状态
		@PostMapping("/update_stockstoragetable")
		public int update_stockstoragetable(@RequestBody int cid) {
			int userPageInfo = stockService.update_stockstoragetable(cid);
			return userPageInfo;
		}
	@PostMapping("/update_order")
	public int update_order(@RequestBody int id) {
		int userPageInfo = stockService.update_order(id);
		return userPageInfo;
	}
	// 仓库查询

	@PostMapping("/select_storehouse")
	public ResponseData select_storehouse() {
		List<Storehouse> userPageInfo = stockService.select_storehouse();
		return DefaultResponseData.successResponseData(userPageInfo);
	}
	@PostMapping("/qwe_stockorder")
	public ResponseData qwe_stockorder() {
		List<stockorder> userPageInfo = stockService.qwe_stockorder();
		return DefaultResponseData.successResponseData(userPageInfo);
	}
	//添加退货单
	@RequestMapping("/insertOrderAndDetails")
	public ResponseData insertOrderAndDetails(@RequestBody stockorder stockorder) {
		try {
				stockService.insertStockOrder(stockorder, stockorder.getStockorderDetails());
		}catch(Exception ex) {
			ex.printStackTrace();
			return DefaultResponseData.ADD_ERROR;
		}
			return DefaultResponseData.successResponseData(200);
	}
	
}