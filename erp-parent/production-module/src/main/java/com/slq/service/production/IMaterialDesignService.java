package com.slq.service.production;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProductMaterialDesign;

public interface IMaterialDesignService {

	int insert(ProductMaterialDesign productMaterialDesign);

	List<ProductMaterialDesign> checkerMaterialDesign();

	ProductMaterialDesign getMateralDesignById(Integer id);

	int materialDesignReject(ProductMaterialDesign productMaterialDesign);

	int materialDesignPass(ProductMaterialDesign productMaterialDesign);

	PageInfo<ProductMaterialDesign> search(Params params);

	int update(ProductMaterialDesign design);

	int checkMaterialUseState(String product_id);

	

	

}
