package com.slq.service.production;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProcessDesign;

public interface IProcessDesignService {

	public int insert(ProcessDesign processDesign);

	public List<ProcessDesign> getProcessDesignChecker();

	public ProcessDesign getProcessDesignById(Integer id);

	public int processDesignReject(ProcessDesign processDesign);

	public int processDesignPass(ProcessDesign processDesign);

	public PageInfo<ProcessDesign> processDesignsearch(Params params);

	public int processDesignUpdate(ProcessDesign processDesign);
	//获取可以制定工序物料设计的  工序单
	public List<ProcessDesign> accordMakingProcessMaterial();
	//获取工序详细单 和工序详细物料纤细单
	public ProcessDesign getProcessDesignJoinMaterialDetail(Integer id);

	public int updateProcessMaterialDesignState(ProcessDesign processDesign);

	public List<ProcessDesign> getCheckerProcessMaterial();

	public int processMaterialPass(ProcessDesign processDesign);

	public int processMaterialReject(ProcessDesign processDesign);

	public PageInfo<ProcessDesign> processMaterialSearch(Params params);

	public int alterProcessMaterialDesign(ProcessDesign processDesign);
	//获取工序和物料的价格
	public ProcessDesign getProcessAndMaterialPrice(String product_id);

	public ProcessDesign getProcessDesignByProductId(String product_id);
}
