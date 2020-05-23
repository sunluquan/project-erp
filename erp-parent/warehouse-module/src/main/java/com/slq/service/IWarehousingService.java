package com.slq.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;

public interface IGatherService {
	int addgather(Gather gather);
    int addgatherdetailss(Integer parentId,String productId,String productName,double amount,String amountUnit,double costPrice,double subtotal,Integer paidAmount,Integer sid,String payTag);
    void addgatherdetails(Integer id, List<GatherDetails> gatherDetails);
    int updatestocknum(Integer id,Integer amount);
    List<Gather> gatherReview();
    List<Gather> gatherScheduling();
    List<Gather> getSchedulingid(String gatherId);
    List<Gather> getByidGather(Integer id);
    int updGatherDetailsSid(GatherDetails gatherDetails);
	int updgatherDetailsNum(Gather gather);
	List<Gather> gatherRegistrationList();
	int updgathernum(Gather gather);
	List<Gather> gatherRegistrationByid(String gatherId);
	//根据编号获取入库单  和入库详细单
	Gather getGatherByIdAll(String gatherId);
	int register(Gather scheduling);
	int updcheck(Gather gather);
	Gather getGatherDetailsByid(String productId);
	int updqwenum(Gather gahter);
	int updqwe(List<Safetystock> safetystock);
}
