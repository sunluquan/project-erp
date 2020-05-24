package com.slq.service.production;

import java.util.List;

import org.apache.ibatis.annotations.Param;

<<<<<<< HEAD
<<<<<<< HEAD
import com.slq.pojo.production.ProductProfitLossDetail;
=======
>>>>>>> warehouse
=======
import com.slq.pojo.production.ProductProfitLossDetail;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessMaterial;

public interface IProductionProcessMaterialService {

	int batchInsert(List<ProductionProcess> productionProcessList);

	List<ProductionProcessMaterial> getProductionProcessMaterialByParentId(Integer production_process_id);

	int batchUpdateRealAmount(Integer manufacture_id, List<ProductionProcessMaterial> productionProcessMaterials);
	//唯一不同就是这两个放在在xml文件中修改得使用数量得获取
	//当前工序使用得物料数量不同
	int batchUpdateRealAmount1(Integer id, List<ProductionProcessMaterial> productionProcessMaterials);
	
	List<ProductionProcessMaterial> getProductionProcessMaterialAndUseAmount(Integer process_id);

	List<ProductionProcessMaterial> getProductionProcessMaterialAndUseAmount(
			Integer production_process_id,
			Integer production_process_course_id);

<<<<<<< HEAD
<<<<<<< HEAD
	int batchUpdateRenewAmount(Integer production_process_id, List<ProductProfitLossDetail> productProfitLossDetails);
	//获取生产领取物料的集合
	List<ProductionProcessMaterial> getProductionProcessMaterialByManufactureId(Integer id);
=======
	

>>>>>>> warehouse
=======
	int batchUpdateRenewAmount(Integer production_process_id, List<ProductProfitLossDetail> productProfitLossDetails);
	//获取生产领取物料的集合
	List<ProductionProcessMaterial> getProductionProcessMaterialByManufactureId(Integer id);
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
