package com.slq.mapper.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
<<<<<<< HEAD

=======
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.GatherDetails;
>>>>>>> warehouse
=======

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.pojo.warehouse.Stock;

@Mapper
public interface StockMapper {

	List<Stock> getStocks();

	Stock getStockById(String stockId);
<<<<<<< HEAD
<<<<<<< HEAD

	int addStock(Stock stock);
=======
    
	int addStock(Stock stock);
	
	int updateStock(@Param("list") List<GatherDetails> gatherDetailsList);
>>>>>>> warehouse
=======

	int addStock(Stock stock);
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6

}
