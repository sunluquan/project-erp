package com.slq.controller.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.pojo.production.ProductProcess;
import com.slq.service.production.IProductProcessService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

/***
 * 工序相关控制层
 * @author 孙陆泉
 *
 */
@RequestMapping("/api/process")
@RestController
public class ProductProcessController {

	@Autowired
	private IProductProcessService productProcessService;
	//查询所有可用的工序数据
	@GetMapping("/selectUsable")
	public ResponseData selectUsableProcess() {
		List<ProductProcess> productProcessList=productProcessService.selectUsableProcess();
		return DefaultResponseData.successResponseData(productProcessList);
	}
}
