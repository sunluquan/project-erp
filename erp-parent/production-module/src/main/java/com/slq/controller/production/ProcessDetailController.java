package com.slq.controller.production;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.pojo.production.ProcessDetail;
import com.slq.service.production.IProcessDetailService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

/***
 * 工序设计详细
 * @author 孙陆泉
 *
 */
@RequestMapping("/api/processDetail")
@RestController
public class ProcessDetailController {
	@Autowired
	private IProcessDetailService processDetailService;
	//新增派工单中使用
	@GetMapping("/byProductId/{product_id}")
	public ResponseData getProcessDetailByProductId(@PathVariable("product_id") String product_id) {
		List<ProcessDetail> processDetails = processDetailService.getProcessDetailByProductId(product_id);
		return DefaultResponseData.successResponseData(processDetails);
	}
	@GetMapping("/byParentId/{parentId}")
	public ResponseData getProcessDetailByParentId(@PathVariable("parentId") Integer parentId) {
		List<ProcessDetail> processDetails = processDetailService.getProcessDetailByParentId(parentId);
		return DefaultResponseData.successResponseData(processDetails);
	}
	//更新工序的物料信息
	@PostMapping("/updateMaterial")
	public ResponseData updateProcessDetailMaterial(@RequestBody ProcessDetail processDetail) {
		try {
			System.out.println(JSON.toJSONString(processDetail));
			int i = processDetailService.updateProcessDetailMaterial(processDetail);
			return DefaultResponseData.successResponseData(i);
		} catch (Exception e) {
			return DefaultResponseData.errorResponseData(e.getMessage());
		}
	}
}
