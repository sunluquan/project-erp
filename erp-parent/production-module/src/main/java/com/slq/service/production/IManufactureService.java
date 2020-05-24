package com.slq.service.production;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ManufactureSum;
<<<<<<< HEAD
<<<<<<< HEAD
import com.slq.util.ManufactureChart;
=======
>>>>>>> warehouse
=======
import com.slq.util.ManufactureChart;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.util.TimeLine;

public interface IManufactureService {

	ManufactureSum getInsert(String product_id, Integer[] productionPlanDetailIds);

	int insert(ManufactureSum manufactureSum);

	List<ManufactureSum> getCheckerManufacture();

	int pass(ManufactureSum manufactureSum);

	int reject(ManufactureSum manufactureSum);

	PageInfo<ManufactureSum> searchManufactureSum(Params params);

	ManufactureSum getManufactureById(Integer id);

	int update(ManufactureSum manufacture);

	List<ManufactureSum> getMeetInternalProductions();

	List<ManufactureSum> getMeetCheckerInternalProductions();

	int setManufacture_procedure_tag(Integer id,Integer manufacture_procedure_tag);

	ManufactureSum getManufactureReal(Integer id);

	int updateReal(ManufactureSum manufacture);

	List<TimeLine> getProductionProgressByManufactureId(Integer id);

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	List<ManufactureChart> getProductProductionFinishChartResult();

	List<ManufactureSum> getMeetProfitAndLossManufactures();

<<<<<<< HEAD
=======
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
