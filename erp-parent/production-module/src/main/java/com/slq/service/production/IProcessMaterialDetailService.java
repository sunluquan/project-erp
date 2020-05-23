package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.ProcessDetail;
import com.slq.pojo.production.ProcessMaterialDetail;

public interface IProcessMaterialDetailService {

	List<ProcessMaterialDetail> getProcessMaterialDesignByParentId(Integer parentId);

	int insert(Integer id,List<ProcessMaterialDetail> processMaterialDetails);

	int delete(Integer id);

	List<ProcessMaterialDetail> getProcessMaterialDesignByParentId1(Integer id);

}
