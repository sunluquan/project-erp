package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;

@Mapper
public interface GatherMapper extends BaseMapper<Gather>{

	int addgather(Gather gather);
    int addgatherdetailss(@Param("parentId") Integer parentId,@Param("productId") String productId,@Param("productName") String productName,@Param("amount") double amount,@Param("amountUnit") String amountUnit,@Param("costPrice") double costPrice,@Param("subtotal") double subtotal,@Param("paidAmount") Integer paidAmount,@Param("sid") Integer sid,@Param("payTag") String payTag);
    int updatestocknum(@Param("id") Integer id,@Param("amount") Integer amount);
	int addgatherdetails(@Param("id")Integer id,@Param("gatherDetails") List<GatherDetails> gatherDetails);
	List<Gather> gatherReview(); 
	List<Gather> gatherScheduling();
	List<Gather> getSchedulingid(@Param("gatherId")String gatherId);
	Gather getByidGather(@Param("id") Integer id);
	int updGatherDetailsSid(GatherDetails gatherDetails);	
	int updgahternum(Gather gather);
	int updgatherDetailsNum(Gather gather);
	int updgatherStoreTag(Gather gather);
	
	List<Gather> gatherRegistrationList();
	List<Gather> gatherRegistrationByid(@Param("gatherId")String gatherId);
	int updgatherPaidAmount();

	Gather getGatherByIdAll(String gatherId);
	
	List<GatherDetails> getGatherDetailsByParentId(String gatherId);
	int batchUpdate(@Param("list")List<GatherDetails> gatherDetails);
}
