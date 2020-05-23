package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.ProductProfitLossDetail;

public interface IProductProfitLossDetailService {

	Integer batchInsert(Integer id, List<ProductProfitLossDetail> productProfitLossDetails);

	List<ProductProfitLossDetail> getProductProfitLossByParentId(Integer id);

	int batchUpdate(List<ProductProfitLossDetail> productProfitLossDetails);

	int batchDelete(Integer id);

}
