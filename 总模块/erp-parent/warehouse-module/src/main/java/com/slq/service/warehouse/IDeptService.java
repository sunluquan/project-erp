package com.slq.service.warehouse;


import java.util.List;

import com.slq.pojo.warehouse.Dept;

public interface IDeptService {
	
	List<Dept> getAllDept();
	
	Dept getDeptById(int deptId);

	int addDept(Dept dept);

	int updateDept(Dept dept);

	int deleteDept(Integer dId);
	
	
}
