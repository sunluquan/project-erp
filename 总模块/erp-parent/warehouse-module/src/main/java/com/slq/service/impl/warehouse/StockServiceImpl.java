package com.slq.service.impl.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slq.mapper.warehouse.StockMapper;
import com.slq.pojo.warehouse.Stock;
import com.slq.service.warehouse.IStockService;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	StockMapper stockMapper;
	
	@Override
	public List<Stock> getStocks() {
		return stockMapper.getStocks();
	}

	@Override
	public Stock getStockById(String stockId) {
		return stockMapper.getStockById(stockId);
	}

	@Override
	public int addStock(Stock stock) {
		return stockMapper.addStock(stock);
	}

}
