package com.slq.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.warehouse.Storehouse;

@Mapper
public interface WarehouseMapper {

	List<Storehouse> selectAll();
	List<Storehouse> selectByid(@Param("id") Integer id);
    int updateWarehouseStatus(@Param("sid")Integer sid, @Param("newstoreStatus")Integer newstoreStatus);
    List<Storehouse> selectByName(@Param("storeName") String storeName);
	Storehouse getHouseById(Integer sid);

	int addHouse(Storehouse house);

	int updateHouse(Storehouse house);

	int delHouse(Integer sid);

}
