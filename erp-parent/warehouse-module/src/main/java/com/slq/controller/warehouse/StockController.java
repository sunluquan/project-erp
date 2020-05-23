package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.warehouse.Stock;
import com.slq.service.warehouse.IStockService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
		
	@Autowired
	IStockService stockService;
	
	@RequestMapping("/getStockslist")
	public ResponseData getStocks() {
		List<Stock> stockList=stockService.getStocks();
		System.out.println(stockList);
		return DefaultResponseData.successResponseData(stockList);
	}
	
	@RequestMapping("/getStockById")
	public ResponseData getStockById(String stockId) {
		System.out.println("库存编号"+stockId);
		Stock stock=stockService.getStockById(stockId);
		return DefaultResponseData.successResponseData(stock);
	}
<<<<<<< HEAD
	
	@RequestMapping("/addStock")
	public ResponseData addStock(Stock stock) {
		int i = stockService.addStock(stock);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}
	}
=======
>>>>>>> warehouse
}
