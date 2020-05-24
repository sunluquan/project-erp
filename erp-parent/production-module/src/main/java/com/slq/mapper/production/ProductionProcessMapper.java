package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProcessDesign;
import com.slq.pojo.production.ProductionProcess;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ProductionProcessMapper extends BaseMapper<ProductionProcess>{


	List<ProductionProcess> getProcessDetailTransitionProductionProcess(Integer manufacture_id);
	/***
	 * 
	 * @param manufacture_id
	 * @param productionProcessList :新增的生产工序集合 
	 * 因为需要新增后获取这个生产工序集合的自增长id
	 * 看网上说好像是多个参数时必须使用"list"
	 * 不然注入会有问题
	 * @return
	 */
	int batchInsert(@Param("manufacture_id")Integer manufacture_id,
			@Param("list")List<ProductionProcess> productionProcessList);
	//复核生产登记的时候  
		//联合查询生产工序 和生产过程工序
		//在联合查询生产工序物料和生产过程工序物料
	ProductionProcess getProductionProcessCourseByProcessIdAndmanufactureId
	(@Param("manufacture_id")Integer manufacture_id, @Param("process_id")String process_id);
	
	int updateReal(ProductionProcess productionProcess);

}
