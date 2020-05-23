package com.slq.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/***
 * 所有级联数据的获取控制类
 * @author 孙陆泉
 *
 */

import com.slq.common.CascadeChoice;
import com.slq.common.Template;
import com.slq.service.ICascadeChoiceService;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;
@RestController
@RequestMapping("/api/cascaded")
public class CascadeChoiceController {

	@Autowired
	private ICascadeChoiceService cascadeChoiceService;
	
	/***
	 * 获取permission数据格式的级联选择器中的数据
	 * @return
	 */
	@RequestMapping("/permission")
	public ResponseData getCascadePermission() {
		List<CascadeChoice> cascadechoices = cascadeChoiceService.getCascadePermission();
		return DefaultResponseData.successResponseData(cascadechoices);
	}
<<<<<<< HEAD
	@RequestMapping("/productKind")
	public ResponseData getCascadeProductKind() {
		List<CascadeChoice> cascadechoices = cascadeChoiceService.getCascadeProductKind();
		return DefaultResponseData.successResponseData(cascadechoices);
	}
=======
>>>>>>> warehouse
	/***
	 * 获取所有的在templates下的文件名
	 * @return
	 */
	@GetMapping("/templates")
	public ResponseData getCascadeTemplates() {
		List<Template> templates = cascadeChoiceService.getCascadeTemplates();
		return DefaultResponseData.successResponseData(templates);
	}
<<<<<<< HEAD
	
=======
>>>>>>> warehouse
}
