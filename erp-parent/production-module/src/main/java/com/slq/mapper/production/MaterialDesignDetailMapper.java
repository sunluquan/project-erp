package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.production.MaterialDesignDetail;
import com.slq.pojo.production.ProcessMaterialDetail;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
public interface MaterialDesignDetailMapper extends BaseMapper<MaterialDesignDetail>{

	int insert(@Param("parentId")Integer parentId,@Param("details")List<MaterialDesignDetail> details);

	List<MaterialDesignDetail> getMaterialDesignDetailById(Integer parentId);
	//根据产品编号  获取这个产品的物料详细信息
	List<MaterialDesignDetail> getMaterialDesignDetailByProductId(String productId);

	//int batchUpdateResidualAmount(List<ProcessMaterialDetail> processMaterials);

	int batchUpdateResidualAmount(List<MaterialDesignDetail> materials);

	Float totalMaterialCostPriceSum(String product_id);

}
