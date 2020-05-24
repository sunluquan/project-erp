package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.pojo.production.MaterialDesignDetail;
import com.slq.service.production.IMaterialDesignDetailService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/materialDesignDetail")
public class MaterialDesignDetailController {

	@Autowired
	private IMaterialDesignDetailService materialDesignDetailService;
	
	@GetMapping("/byParentId/{parentId}")
	public ResponseData getMaterialDesignDetailById(@PathVariable("parentId") Integer parentId) {
		System.out.println("parentId:"+parentId);
		List<MaterialDesignDetail> materialDesignDetails = materialDesignDetailService.getMaterialDesignDetailById(parentId);
		
		System.out.println(JSON.toJSONString(materialDesignDetails));
		return DefaultResponseData.successResponseData(materialDesignDetails);
	}
	//根据产品编号  获取这个产品的物料详细信息
	@GetMapping("/byProductId/{productId}")
	public ResponseData getMaterialDesignDetailByProductId(@PathVariable("productId")String productId) {
		List<MaterialDesignDetail> materialDesignDetails = materialDesignDetailService.getMaterialDesignDetailByProductId(productId);
		return DefaultResponseData.successResponseData(materialDesignDetails);
	}
}
