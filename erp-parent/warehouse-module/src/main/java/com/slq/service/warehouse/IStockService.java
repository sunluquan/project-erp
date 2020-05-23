package com.slq.service.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Stock;

public interface IStockService {

	List<Stock> getStocks();

	Stock getStockById(String stockId);
	int addStock(Stock stock);

	int updateStock(List<GatherDetails> gatherDetailsList);

}
