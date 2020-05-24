package com.slq.service.stock;

import java.util.List;

import com.slq.pojo.stock.*;;

public interface StockRecedeService {
	public List<StockRecede> selectAllStock();
	public void insertStockRecede(StockRecede stockRecede);
	public void insertStockRD(RecedeDetails recedeDetails);
	public void updateStockOD(StockRecede stockRecede);
	public void updateStockRD(RecedeDetails recedeDetails);
	public void deleteStockOD(int recId);
	public void deleteStockRD(int detSequ);
	public StockRecede selectOne(int recId);
	public List<StockRecede> selectAllCheck();
	public List<StockRecede> selectAllNoCheck();
}
