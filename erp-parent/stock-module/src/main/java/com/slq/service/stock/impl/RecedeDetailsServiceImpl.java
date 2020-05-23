package com.slq.service.stock.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slq.mapper.singleMethod.common.RecedeDetailsMapper;
import com.slq.pojo.stock.*;
import com.slq.service.stock.RecedeDetailsService;


@Service
public class RecedeDetailsServiceImpl implements RecedeDetailsService{
	@Autowired
	RecedeDetailsMapper rdm;

	@Override
	public StockRecede selectOne(int recId) {
		return rdm.selectOne(recId);
	}
}
