package com.slq.mapper.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Stock;

@Mapper
public interface StockMapper {

	List<Stock> getStocks();

	Stock getStockById(String stockId);
    
	int addStock(Stock stock);
	
	int updateStock(@Param("list") List<GatherDetails> gatherDetailsList);

}
