package com.slq.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.Storehouse;

public interface IWarehouseService {

	List<Storehouse> selectAll();
	List<Storehouse> selectByid(Integer useType);

	int updateWarehouseStatus(Integer sid,Integer oldstoreStatus);
	List<Storehouse> selectByName(String storeName);
	Storehouse getHouseById(Integer sid);

	int addHouse(Storehouse house);

	int updateHouse(Storehouse house);

	int delHouse(Integer sid);

}
