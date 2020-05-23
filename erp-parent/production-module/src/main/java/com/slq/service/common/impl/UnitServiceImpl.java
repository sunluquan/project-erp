package com.slq.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.common.UnitMapper;
import com.slq.pojo.common.Unit;
import com.slq.service.common.IUnitService;


@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper,Unit> implements IUnitService{

	@Override
	public Unit getUnitValueByKey(String table_column) {

		return this.baseMapper.getUnitByTableColumn(table_column);
	}

	@Override
	public List<Unit> getUnitKeyAll() {
		QueryWrapper<Unit> query=new QueryWrapper<Unit>();
		List<Unit> units=this.getBaseMapper().selectList(query);
		System.out.println(JSON.toJSONString(units));
		return units;
	}

}
