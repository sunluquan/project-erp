package com.slq.service;


import java.util.List;

import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;

public interface IGatherService {
	int addgather(Gather gather);
    int addgatherdetailss(Integer parentId,String productId,String productName,double amount,String amountUnit,double costPrice,double subtotal,Integer paidAmount,Integer sid,String payTag);
    void addgatherdetails(Integer id, List<GatherDetails> gatherDetails);
    int updatestocknum(Integer id,Integer amount);
    List<Gather> gatherReview();
    List<Gather> gatherScheduling();
    List<Gather> getSchedulingid(String stockid);
    Gather getByidGather(Integer id);
}
