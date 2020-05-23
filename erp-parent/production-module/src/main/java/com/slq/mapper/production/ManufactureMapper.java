package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ManufactureSum;
<<<<<<< HEAD
import com.slq.util.ManufactureChart;
=======
>>>>>>> warehouse
import com.slq.util.TimeLine;

@Mapper
public interface ManufactureMapper extends BaseMapper<ManufactureSum>{

	
	ManufactureSum getInsert(
			@Param("product_id")String product_id, 
			@Param("productionPlanDetailIds")Integer[] productionPlanDetailIds);

	List<ManufactureSum> searchManufactureSum(Params params);

	ManufactureSum getManufactureById(Integer id);

	//获取通过生产总表的id获取该总表下的
	//工序集合的实际总物料 和 实际工时总价格
	ManufactureSum getManufactureReal(Integer id);

	List<TimeLine> getProductionProgressByManufactureId(Integer id);

<<<<<<< HEAD
	List<ManufactureChart> getProductProductionFinishChartResult();

=======
>>>>>>> warehouse
	
}
