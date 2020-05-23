package com.slq.service.warehouse;

import java.util.List;

<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.GatherDetails;
>>>>>>> warehouse
import com.slq.pojo.warehouse.Stock;

public interface IStockService {

	List<Stock> getStocks();

	Stock getStockById(String stockId);
<<<<<<< HEAD

	int addStock(Stock stock);

=======
	int addStock(Stock stock);

	int updateStock(List<GatherDetails> gatherDetailsList);

>>>>>>> warehouse
}
