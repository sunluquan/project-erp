package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.common.Params;
import com.slq.pojo.production.ProductMaterialDesign;

@Mapper
public interface MaterialDesignMapper extends BaseMapper<ProductMaterialDesign>{

	List<ProductMaterialDesign> checkerMaterialDesign();

	ProductMaterialDesign getMateralDesignById(Integer id);

	List<ProductMaterialDesign> search(Params params);

	int checkMaterialUseState(String product_id);


}
