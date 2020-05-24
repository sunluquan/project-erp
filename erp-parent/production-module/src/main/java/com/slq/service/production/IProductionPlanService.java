package com.slq.service.production;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProductionPlan;

public interface IProductionPlanService {

	int insert(ProductionPlan productionPlan);

	PageInfo<ProductionPlan> searchProductionPlan(Params params);

	List<ProductionPlan> getCheckerProductionPlans();

	ProductionPlan getProductionPlanAndDetailById(Integer id);

	int pass(ProductionPlan productionPlan);

	int reject(ProductionPlan productionPlan);

	int update(ProductionPlan productionPlan);

<<<<<<< HEAD
<<<<<<< HEAD
	List<ProductionPlan> getMeetDespatchingProductionPlans();

=======
>>>>>>> warehouse
=======
	List<ProductionPlan> getMeetDespatchingProductionPlans();

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6

}
