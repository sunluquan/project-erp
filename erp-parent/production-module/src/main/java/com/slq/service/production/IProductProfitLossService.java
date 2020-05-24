package com.slq.service.production;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProductProfitLoss;
import com.slq.pojo.production.ProductionProcessMaterial;

public interface IProductProfitLossService {

	int insert(ProductProfitLoss productProfitLoss);

	List<ProductProfitLoss> getCheckProductProfitLossList();

	int pass(ProductProfitLoss productProfitLoss);

	int reject(ProductProfitLoss productProfitLoss);

	PageInfo<ProductProfitLoss> searchProductProfitLoss(Params params);

	ProductProfitLoss getProductProfitLossByIdAll(Integer id);

	int update(ProductProfitLoss productProfitLoss);

	Integer insertInternalProfitLoss(Integer id, List<ProductionProcessMaterial> productionProcessMaterials,
			Integer numerical_difference);

}
