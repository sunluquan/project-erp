package com.slq.service.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slq.mapper.common.StockRecedeMapper;
import com.slq.pojo.stock.*;
import com.slq.service.stock.StockRecedeService;

@Service
public class StockRecedeServiceImpl implements StockRecedeService{
	@Autowired
	StockRecedeMapper srm;
	
	@Override
	public List<StockRecede> selectAllStock() {
		return srm.selectAllStock();
	}

	@Override
	public void insertStockRecede(StockRecede stockRecede) {
		srm.insertStockRecede(stockRecede);
	}

	@Override
	public void insertStockRD(RecedeDetails recedeDetails) {
		srm.insertStockRD(recedeDetails);
	}

	@Override
	public void updateStockOD(StockRecede stockRecede) {
		srm.updateStockOD(stockRecede);
	}

	@Override
	public void updateStockRD(RecedeDetails recedeDetails) {
		srm.updateStockRD(recedeDetails);
	}

	@Override
	public void deleteStockOD(int recId) {
		srm.deleteStockOD(recId);
	}

	@Override
	public void deleteStockRD(int detSequ) {
		srm.deleteStockRD(detSequ);
	}

	@Override
	public StockRecede selectOne(int recId) {
		return srm.selectOne(recId);
	}

	@Override
	public List<StockRecede> selectAllCheck() {
		return srm.selectAllCheck();
	}

	@Override
	public List<StockRecede> selectAllNoCheck() {
		return srm.selectAllNoCheck();
	}
}
