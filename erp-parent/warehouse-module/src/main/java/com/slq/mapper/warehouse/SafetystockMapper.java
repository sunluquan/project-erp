package com.slq.mapper.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.Safetystock;

@Mapper
public interface SafetystockMapper {

	List<Safetystock> getAllsafety();

	int addSafety(Safetystock safetystocks);	
	int updatestockId(Safetystock stocks);
    
	Safetystock getAllsafetyById(String safetystockId);
    
	List<Safetystock> selectByReviewList();
	
	int updateChecktag(Safetystock safetystock);
	
	int updateSefeStatus(Safetystock safetystock);
     
    List<Safetystock> selectSafetystocks();
    List<Safetystock> selectByName(@Param("product_name") String product_name);
    List<Safetystock> selectBytime(@Param("registertime")String registertime);
    List<Safetystock> selectStockChange();
    int updateStockChange(Safetystock safetystock);
}
