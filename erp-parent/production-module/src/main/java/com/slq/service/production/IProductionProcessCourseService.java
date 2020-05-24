package com.slq.service.production;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
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

<<<<<<< HEAD
=======
	List<ProductionProcessCourse> productionProcessCoursebyManufactureIdAndProcessId(Integer manufacture_id,
			String process_id);

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
