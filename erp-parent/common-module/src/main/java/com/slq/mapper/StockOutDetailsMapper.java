package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.sPayDetails;;


@Mapper
public interface StockOutDetailsMapper {

	int addStockOutDetails(String id,List<sPayDetails> spayDetails);
	List<sPayDetails> getSpayDetailsByParentId(String parent_id);
	sPayDetails getSpayDetailsByParentIdAndProductIdOne(@Param("parentId")String payId,@Param("productId")String productId);
	int getNotRegistrationCount(String parentId);
}
