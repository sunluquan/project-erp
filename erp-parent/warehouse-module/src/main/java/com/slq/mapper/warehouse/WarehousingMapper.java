package com.slq.mapper.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.warehouse.Storehouse;

@Mapper
public interface WarehousingMapper extends BaseMapper<Gather>{    
	int addgather(Gather gather);
    int updatestocknum(@Param("id") Integer id,@Param("amount") Integer amount);
	int addgatherdetails(@Param("id")Integer id,@Param("gatherDetails") List<GatherDetails> gatherDetails);
	List<Gather> gatherReview(); 
	List<Gather> gatherScheduling();
	List<Gather> getSchedulingid(@Param("gatherId")String gatherId);
	List<Gather> getByidGather(@Param("productId") String productId);
	int updGatherDetailsSid(GatherDetails gatherDetails);	
	int updgahternum(Gather gather);
	int updgatherDetailsNum(@Param("list")List<GatherDetails> gatherDetails);
	int updgatherStoreTag(Gather gather);
	List<Storehouse> selectStorehousesGather(@Param("productId")String productId);
	//入库登记
	List<Gather> gatherRegistrationList();
	List<Gather> gatherRegistrationByid(@Param("gatherId")String gatherId);
	int updgatherPaidAmount();
	Gather getGatherByIdAll(String gatherId);
	
	List<GatherDetails> getGatherDetailsByParentId(String gatherId);
	int batchUpdate(@Param("list")List<GatherDetails> gatherDetails);
	int updcheck(Gather gather);
	int updpayTag(GatherDetails gatherDetails);
	
	Gather getGatherDetailsByid(String productId);
	List<Safetystock> getListGatherDetailsSafetystocks(String productId);
	int updqwenum(Gather gahter);
	int updqwe(@Param("list")List<Safetystock> safetystock);
	
	List<Gather> selectRegistration();
}
