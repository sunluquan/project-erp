package com.slq.controller.production;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
>>>>>>> warehouse
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
<<<<<<< HEAD
<<<<<<< HEAD
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
=======
>>>>>>> warehouse
=======
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.pojo.User;
import com.slq.pojo.production.ProductKind;
import com.slq.service.production.IProductKindService;
import com.slq.util.ActiveUser;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;

@RestController
@RequestMapping("/api/productKind")
public class ProductKindController {

	@Autowired
	private IProductKindService productKindService;
	@GetMapping("/getKinds")
	public ResponseData getKinds() {
		
		System.out.println(JSON.toJSONString(SecurityUtils.getSubject().getPrincipal()));
		System.out.println(SecurityUtils.getSubject().getSession().getId());
		//User user = ((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		List<ProductKind> productKinds = productKindService.getKinds();
		return DefaultResponseData.successResponseData(productKinds);
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	@PostMapping("/search")
	public ResponseData searchProductKind(@RequestBody Params params) {
		PageInfo<ProductKind> productKindPage = productKindService.searchProductKind(params);
		return DefaultResponseData.successResponseData(productKindPage);
	}
	@PostMapping("/insert")
	public ResponseData insert(@RequestBody ProductKind productKind) {
		int id = productKindService.insert(productKind);
		return DefaultResponseData.successResponseData(id);
	}
<<<<<<< HEAD
=======
	
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
