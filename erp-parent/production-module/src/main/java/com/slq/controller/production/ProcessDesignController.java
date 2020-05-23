package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProcessDesign;
import com.slq.service.production.IProcessDesignService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/processDesign")
public class ProcessDesignController {
	
	@Autowired
	private IProcessDesignService processDesignService;
	
	//在新增派工单的时候使用 
	//获取这个产品的工序以及工序详细单
	//因为在新增生产工序表的时候需要用到 工序总表的编号
	//还没有使用的
	@GetMapping("/byProductId/{product_id}")
	public ResponseData getProcessDesignByProductId(@PathVariable("product_id")String product_id) {
		ProcessDesign processDesign=processDesignService.getProcessDesignByProductId(product_id);
		System.out.println(JSON.toJSONString(processDesign));
		return DefaultResponseData.successResponseData(processDesign);
	}
	//这个是工序物料设计的分页查询
	//因为工序的分页查询会查出check_tag=0 1 2的
	//但是工序物料设计  只会查询check_tag=1 and process_material_tag=0 1 2
	@PostMapping("/searchMaterial")
	public ResponseData processMaterialSearch(@RequestBody Params params) {
		System.out.println(JSON.toJSONString(params));
		PageInfo<ProcessDesign> porcessDesignPage=processDesignService.processMaterialSearch(params);
		return DefaultResponseData.successResponseData(porcessDesignPage);
	}
	//获取可以审核的工序物料设计单
	@GetMapping("/checkerProcessMaterial")
	public ResponseData getCheckerProcessMaterial() {
		List<ProcessDesign> processDesigns=processDesignService.getCheckerProcessMaterial();
		return DefaultResponseData.successResponseData(processDesigns);
	}
	//当工序物料设计被驳回的时候使用的  修改  工序  物料  设计 的方法
	@PostMapping("/alterProcessMaterialDesign")
	public ResponseData alterProcessMaterialDesign(@RequestBody ProcessDesign processDesign) {
		int i = processDesignService.alterProcessMaterialDesign(processDesign);
		return DefaultResponseData.successResponseData(i);
	}
	//这个是在making_process_material  点击提交的时候使用
	//更新工序物料设计的状态
	@PostMapping("/updateMaterialState")
	public ResponseData updateProcessMaterialDesignState(@RequestBody ProcessDesign processDesign) {
		//try {
		System.out.println(JSON.toJSONString(processDesign));
			int i = processDesignService.updateProcessMaterialDesignState(processDesign);
			return DefaultResponseData.successResponseData(i);
//		} catch (Exception e) {
//			return DefaultResponseData.errorResponseData(e.getMessage());
//		}
		
	}
	//获取工序单  根据id 还获取工序单的工序详细  再获取工序详细的工序物料详细
	@GetMapping("/byIdAll/{id}")
	public ResponseData getProcessDesignJoinMaterialDetail(@PathVariable("id")Integer id) {
		ProcessDesign processDesign=processDesignService.getProcessDesignJoinMaterialDetail(id);
		return DefaultResponseData.successResponseData(processDesign);
	}
	//上面那个不知道是否还有别的模块调用了  所以在写了一个
	//上面那个只是查询check_tag=1的  
	//我需要获取 审核中的工序物料  所以还有加上 process_material_tag=0
//	@GetMapping("/byIdAll1/{id}")
//	public ResponseData getProcessDesignJoinMaterialDetail1(@PathVariable("id")Integer id) {
//		ProcessDesign processDesign=processDesignService.getProcessDesignJoinMaterialDetail1(id);
//		return DefaultResponseData.successResponseData(processDesign);
//	}
	//获取可以制定工序物料设计的  工序单
	@GetMapping("/accordMakingProcessMaterial")
	public ResponseData accordMakingProcessMaterial() {
		List<ProcessDesign> processDesigns=processDesignService.accordMakingProcessMaterial();
		return DefaultResponseData.successResponseData(processDesigns);
	}
	@PostMapping("/search")
	public ResponseData processDesignsearch(@RequestBody Params params) {
		PageInfo<ProcessDesign> processDesignPage= processDesignService.processDesignsearch(params);
		return DefaultResponseData.successResponseData(processDesignPage);
	}
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody ProcessDesign processDesign) {
		try {
			int id= processDesignService.insert(processDesign);
			return DefaultResponseData.successResponseData(id);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	@GetMapping("/checker")
	public ResponseData getProcessDesignChecker() {
		List<ProcessDesign> processDesigns=processDesignService.getProcessDesignChecker();
		return DefaultResponseData.successResponseData(processDesigns);
	}
	//再点击审核的时候使用到了
	//点击设置物料的时候耶使用到了
	@GetMapping("/byId/{id}")
	public ResponseData getProcessDesignById(@PathVariable("id")Integer id) {
		ProcessDesign processDesign=processDesignService.getProcessDesignById(id);
		return DefaultResponseData.successResponseData(processDesign);
	}
	//工序物料设计审核通过
	@PostMapping("/passMaterial")
	public ResponseData processMaterialPass(@RequestBody ProcessDesign processDesign) {
		int i = processDesignService.processMaterialPass(processDesign);
		return DefaultResponseData.successResponseData(i);
	}
	//工序物料设计审核驳回
	@PostMapping("/rejectMaterial")
	public ResponseData processMaterialReject(@RequestBody ProcessDesign processDesign) {
		System.out.println(processDesign);
		int i = processDesignService.processMaterialReject(processDesign);
		return DefaultResponseData.successResponseData(i);
	}
	//审核工序通过
	@PostMapping("/pass")
	public ResponseData processDesignPass(@RequestBody ProcessDesign processDesign) {
		try {
			int i= processDesignService.processDesignPass(processDesign);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	//审核工序不通过
	@PostMapping("/reject")
	public ResponseData processDesignReject(@RequestBody ProcessDesign processDesign) {
		try {
			int i= processDesignService.processDesignReject(processDesign);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	
	@PostMapping("/update")
	public ResponseData processDesignUpdate(@RequestBody ProcessDesign processDesign) {
		try {
			int i= processDesignService.processDesignUpdate(processDesign);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
	
}
