package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.common.Params;
import com.slq.pojo.production.ProductionPlan;
@Mapper
public interface ProductionPlanMapper extends BaseMapper<ProductionPlan>{

	List<ProductionPlan> searchProductionPlan(Params params);

	ProductionPlan getProductionPlanAndDetailById(Integer id);

}
