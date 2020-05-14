package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.MaterialDesignDetail;
import com.slq.pojo.production.ProcessMaterialDetail;

public interface IMaterialDesignDetailService {

	int insert(Integer parentId ,List<MaterialDesignDetail> details);

	List<MaterialDesignDetail> getMaterialDesignDetailById(Integer parentId);

	int deleteByParentId(Integer id);
	//根据产品编号  获取这个产品的物料详细信息
	List<MaterialDesignDetail> getMaterialDesignDetailByProductId(String productId);

	//int updateResidualAmount(List<ProcessMaterialDetail> processMaterials);

	int updateResidualAmount(List<MaterialDesignDetail> materials);
	
	Float totalMaterialCostPriceSum(String product_id);
}
