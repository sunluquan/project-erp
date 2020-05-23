package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.production.ProductionPlanDetail;
@Mapper
public interface ProductionPlanDetailMapper extends BaseMapper<ProductionPlanDetail>{

	int batchInsert(@Param("id")Integer id,@Param("productionPlanDetails") List<ProductionPlanDetail> productionPlanDetails);

	List<ProductionPlanDetail> getProductionPlanDetailByParentId(Integer parent_id);
	//根据产品编号获取  所有的可以制定派工单的产品
	List<ProductionPlanDetail> getProductionPlanDetailAndProductionPlanByProductId(String product_id);

	int batchUpdateManufacture(@Param("id")Integer id,
			@Param("productionPlanDetails")List<ProductionPlanDetail> productionPlanDetails,
			@Param("manufacture_tag")Integer manufacture_tag);

	List<ProductionPlanDetail> getProductionPlanDetailMeetDespatching();
}
