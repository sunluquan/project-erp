package com.slq.service.common;

import java.util.List;

import com.slq.pojo.common.Unit;


public interface IUnitService {

	Unit getUnitValueByKey(String table_column);

	List<Unit> getUnitKeyAll();

	
}
