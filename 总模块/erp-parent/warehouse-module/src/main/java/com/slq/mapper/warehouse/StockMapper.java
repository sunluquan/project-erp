package com.slq.mapper.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slq.pojo.warehouse.Stock;

@Mapper
public interface StockMapper {

	List<Stock> getStocks();

	Stock getStockById(String stockId);

	int addStock(Stock stock);

}
