package com.slq.service;


import java.util.List;

import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;

public interface IGatherService {
	int addgather(Gather gather);
    int addgatherdetailss(Integer parentId,String productId,String productName,double amount,String amountUnit,double costPrice,double subtotal,Integer paidAmount,Integer sid,String payTag);
    void addgatherdetails(Integer id, List<GatherDetails> gatherDetails);
    int updatestocknum(Integer id,Integer amount);
    List<Gather> gatherReview();
    List<Gather> gatherScheduling();
    List<Gather> getSchedulingid(String gatherId);
    Gather getByidGather(Integer id);
    int updGatherDetailsSid(GatherDetails gatherDetails);
	int updgatherDetailsNum(Gather gather);
	List<Gather> gatherRegistrationList();
	int updgathernum(Gather gather);
	List<Gather> gatherRegistrationByid(String gatherId);
	//根据编号获取入库单  和入库详细单
	Gather getGatherByIdAll(String gatherId);
	int register(Gather scheduling);
	//生产完成 后制定的成品入库
	int finishedProductStorage(ManufactureSum manufacture);
}
