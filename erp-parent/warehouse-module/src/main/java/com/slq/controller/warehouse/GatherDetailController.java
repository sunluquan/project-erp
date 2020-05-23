package com.slq.controller.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slq.service.IGatherDetailService;

@RestController
@RequestMapping("/api/gatherDetail")
public class GatherDetailController {

	@Autowired
	private IGatherDetailService gatherDetailService;
	
	
}
