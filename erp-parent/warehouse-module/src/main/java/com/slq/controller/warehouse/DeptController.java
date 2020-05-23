package com.slq.controller.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.exception.NotToBeDeletedException;
import com.slq.pojo.warehouse.Dept;
import com.slq.service.IDeptService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/dept")
public class DeptController {

	@Autowired
	private IDeptService deptService;
	
	@RequestMapping("/getAllDept")
	public ResponseData getAllDept() {
		
		List<Dept> deptList = deptService.getAllDept();
		System.out.println("部门列表信息："+JSON.toJSONString(deptList));
		return DefaultResponseData.successResponseData(deptList);
	}
	
	@RequestMapping("/getDeptById/{deptId}")
	public ResponseData getDeptById(@PathVariable("deptId")Integer deptId) {
		System.out.println("部门编号:"+deptId);
		Dept dept = deptService.getDeptById(deptId);
		return DefaultResponseData.successResponseData(dept);
	}
	
	@RequestMapping("/addDept")
	public ResponseData addDept(@RequestBody Dept dept) {
		System.out.println(dept);
		int i = deptService.addDept(dept);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.ADD_ERROR;
		}
	}
	
	@RequestMapping("/updateDept")
	public ResponseData updatePermission(@RequestBody Dept dept) {
		System.out.println(dept);
		int i = deptService.updateDept(dept);
		if(i>0) {
			return DefaultResponseData.successResponseData(i);
		}else {
			return DefaultResponseData.UPDATE_ERROR;
		}
	}
}
