package com.slq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.mapper.DeptMapper;
import com.slq.pojo.warehouse.Dept;
import com.slq.service.IDeptService;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptMapper deptMapper;
	

	@Override
	public Dept getDeptById(int deptId) {
		return deptMapper.getDeptById(deptId);
	}

	@Override
	public int addDept(Dept dept) {
		return deptMapper.addDept(dept);
	}

	@Override
	public int updateDept(Dept dept) {
		return deptMapper.updateDept(dept);
	}

	@Override
	public int deleteDept(Integer dId) {
		return deptMapper.deleteDept(dId);
	}

	@Override
	public List<Dept> getAllDept() {
		// TODO Auto-generated method stub
		return deptMapper.getAllDept();
	}

}
