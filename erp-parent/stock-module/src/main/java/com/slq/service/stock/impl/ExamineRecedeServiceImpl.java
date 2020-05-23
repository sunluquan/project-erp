package com.slq.service.stock.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slq.mapper.common.ExamineRecede;
import com.slq.pojo.stock.*;
import com.slq.service.stock.ExamineService;


/**
 * 退货审核Service实现类
 * @author lqy
 */
@Service
public class ExamineRecedeServiceImpl implements ExamineService{
	@Autowired
	ExamineRecede er;

	@Override
	public void examineRecede(int recId) {
			er.examineRecede(recId);
	}

	@Override
	public void rejectRecede(StockRecede sr) {
			er.rejectRecede(sr);
	}
}
