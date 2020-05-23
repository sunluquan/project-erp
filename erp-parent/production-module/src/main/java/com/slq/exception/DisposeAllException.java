package com.slq.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.slq.util.DefaultResponseData;
/***
 * 捕获所有的异常
 * @author 孙陆泉
 *
 */
@Component
public class DisposeAllException implements HandlerExceptionResolver{

	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		ModelAndView model=new ModelAndView();
		//model.addObject(DefaultResponseData.errorResponseData(ex.getMessage()));
		System.out.println("进入捕获所有的异常类中");
		ex.printStackTrace();
		FastJsonJsonView jsonView=new FastJsonJsonView();
		jsonView.addStaticAttribute("responseData",DefaultResponseData.errorResponseData(ex.getMessage()));
		model.setView(jsonView);
		return model;
	}

}
