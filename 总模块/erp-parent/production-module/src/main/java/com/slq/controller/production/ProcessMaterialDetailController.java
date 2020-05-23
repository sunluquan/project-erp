package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProcessDetail;
import com.slq.pojo.production.ProcessMaterialDetail;
import com.slq.service.production.IProcessMaterialDetailService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;
@RestController
@RequestMapping("/api/processMaterialDetail")
public class ProcessMaterialDetailController {

	@Autowired
	private IProcessMaterialDetailService processMaterialDetailService;
	
	//根据工序编号查询
	@GetMapping("/byParentId/{parentId}")
	public ResponseData getProcessMaterialDesignByParentId(@PathVariable("parentId")Integer parentId) {
		List<ProcessMaterialDetail> processMaterialDetails=processMaterialDetailService.getProcessMaterialDesignByParentId(parentId);
		return DefaultResponseData.successResponseData(processMaterialDetails);
	}
//	@PostMapping("/insert")
//	public ResponseData insert(@RequestBody ProcessDetail processDetail) {
//		processMaterialDetailService.insert(processDetail);
//		return null;
//	}
}
