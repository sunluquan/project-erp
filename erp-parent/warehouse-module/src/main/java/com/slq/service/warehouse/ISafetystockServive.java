package com.slq.service.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.warehouse.Safetystock;

public interface ISafetystockServive {

	List<Safetystock> getAllsafety();

	int addSafety(Safetystock safetystocks);
	int updatestockId(Safetystock stocks);
	List<Safetystock> selectByReviewList();
	Safetystock getAllsafetyById(String productId);
	
	int updateChecktag(Safetystock safetystock);

	int updateSefeStatus(Safetystock safetystock);
	List<Safetystock> selectSafetystocks();
	List<Safetystock> selectByName(String product_name);
	List<Safetystock> selectBytime(String registertime);
	List<Safetystock> selectStockChange();
	int updateStockChange(Safetystock safetystock);
}
