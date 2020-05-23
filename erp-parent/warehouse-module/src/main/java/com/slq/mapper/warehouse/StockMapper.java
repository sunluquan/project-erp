package com.slq.mapper.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD

=======
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.GatherDetails;
>>>>>>> warehouse
import com.slq.pojo.warehouse.Stock;

@Mapper
public interface StockMapper {

	List<Stock> getStocks();

	Stock getStockById(String stockId);
<<<<<<< HEAD

	int addStock(Stock stock);
=======
    
	int addStock(Stock stock);
	
	int updateStock(@Param("list") List<GatherDetails> gatherDetailsList);
>>>>>>> warehouse

}
