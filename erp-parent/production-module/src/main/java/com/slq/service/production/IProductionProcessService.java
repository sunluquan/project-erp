package com.slq.service.production;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.slq.common.constant.ProductionConstant;
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProcessDesign;
import com.slq.pojo.production.ProductionProcess;

public interface IProductionProcessService {

	List<ProductionProcess> getProcessDetailTransitionProductionProcess(Integer manufacture_id);

	int insert(Integer id, List<ProductionProcess> productionProcessList);

	int register(ManufactureSum manufacture);

	int updateProductionProcess_process_finish_tag(Integer manufacture_id, Integer process_finish_tag,
			Float labour_hour_amount);

	int update(ProductionProcess productionProcess);

	ProductionProcess getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			String process_id);

	int update_process_finish_tag(ProductionProcess productionProcess);

	Integer getCheckFinishCount(Integer id);
	
	Integer handover(ProductionProcess productionProcess);

	boolean checker_handover(ProductionProcess productionProcess);

	Integer getNotProcess_transfer_tag_finish_count(Integer parent_id);

}
