package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;

@Mapper
public interface GatherMapper extends BaseMapper<Gather>{    
	int addgather(Gather gather);
    int updatestocknum(@Param("id") Integer id,@Param("amount") Integer amount);
	int addgatherdetails(@Param("id")Integer id,@Param("gatherDetails") List<GatherDetails> gatherDetails);
	List<Gather> gatherReview(); 
	List<Gather> gatherScheduling();
	List<Gather> getSchedulingid(@Param("gatherId")String gatherId);
	List<Gather> getByidGather(@Param("id") Integer id);
	int updGatherDetailsSid(GatherDetails gatherDetails);	
	int updgahternum(Gather gather);
	int updgatherDetailsNum(Gather gather);
	int updgatherStoreTag(Gather gather);
	
	//入库登记
	List<Gather> gatherRegistrationList();
	List<Gather> gatherRegistrationByid(@Param("gatherId")String gatherId);
	int updgatherPaidAmount();
	Gather getGatherByIdAll(String gatherId);
	
	List<GatherDetails> getGatherDetailsByParentId(String gatherId);
	int batchUpdate(@Param("list")List<GatherDetails> gatherDetails);
	int updcheck(Gather gather);
	
	Gather getGatherDetailsByid(String productId);
	List<Safetystock> getListGatherDetailsSafetystocks(String productId);
	int updqwenum(Gather gahter);
	int updqwe(@Param("list")List<Safetystock> safetystock);
}
