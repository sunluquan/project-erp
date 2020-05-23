package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.ProcessDetail;

public interface IProcessDetailService {

	int batchInsert(Integer id, List<ProcessDetail> processDetails);

	List<ProcessDetail> getProcessDetailByParentId(Integer parentId);

	int deleteByParentId(Integer id);

	int updateProcessDetailMaterial(ProcessDetail processDetail);
	//获取总工时价格
	Float processTotalManhoirCostPriceSum(Integer id);

	List<ProcessDetail> getProcessDetailByProductId(String product_id);
	
}
