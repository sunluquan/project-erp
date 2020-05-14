package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;

@Mapper
public interface GatherMapper {
    int addgather(Gather gather);
    int addgatherdetailss(@Param("parentId") Integer parentId,@Param("productId") String productId,@Param("productName") String productName,@Param("amount") double amount,@Param("amountUnit") String amountUnit,@Param("costPrice") double costPrice,@Param("subtotal") double subtotal,@Param("paidAmount") Integer paidAmount,@Param("sid") Integer sid,@Param("payTag") String payTag);
    int updatestocknum(@Param("id") Integer id,@Param("amount") Integer amount);
	int addgatherdetails(@Param("id")Integer id,@Param("gatherDetails") List<GatherDetails> gatherDetails);
	List<Gather> gatherReview(); 
	List<Gather> gatherScheduling();
	List<Gather> getSchedulingid(@Param("stockid")String stockid);
	Gather getByidGather(@Param("id") Integer id);
}
