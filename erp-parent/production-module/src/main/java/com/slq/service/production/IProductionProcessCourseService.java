package com.slq.service.production;

import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessCourse;

public interface IProductionProcessCourseService {

	int register(ManufactureSum manufacture);

	ProductionProcessCourse getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			String process_id);

	ProductionProcessCourse getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			Integer production_process_id, String process_id);

	boolean checker(ManufactureSum manufacture);
//	ProductionProcessCourse getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
//			Integer process_id);

}
