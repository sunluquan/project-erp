package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.warehouse.Dept;

@Mapper
public interface DeptMapper {

	List<Dept> getAllDept();

	Dept getDeptById(int deptId);

	int addDept(Dept dept);

	int updateDept(Dept dept);

	int deleteDept(Integer dId);

}
