package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.pojo.production.ProductionProcessMaterialCourse;

public interface IProductionProcessMaterialCourseService {

	int batchInsert(Integer manufacture_id, List<ProductionProcessMaterial> productionProcessMaterials);

	List<ProductionProcessMaterialCourse> byParentId(Integer id);


	int batchUpdate(List<ProductionProcessMaterial> productionProcessMaterials);

}
