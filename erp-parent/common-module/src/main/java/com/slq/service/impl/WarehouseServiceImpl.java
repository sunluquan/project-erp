package com.slq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slq.mapper.WarehouseMapper;
import com.slq.pojo.production.Product;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.IWarehouseService;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public class WarehouseServiceImpl implements IWarehouseService{

	@Autowired
	private WarehouseMapper wareHouseMapper;
	
	@Override
	public List<Storehouse> selectAll() {		
		return  wareHouseMapper.selectAll();
	}

	@Override
	public Storehouse getHouseById(Integer sid) {
		return wareHouseMapper.getHouseById(sid);
	}

	@Override
	public int addHouse(Storehouse house) {
		return wareHouseMapper.addHouse(house);
	}

	@Override
	public int updateHouse(Storehouse house) {
		return wareHouseMapper.updateHouse(house);
	}

	@Override
	public int delHouse(Integer sid) {
		return wareHouseMapper.delHouse(sid);
	}

	@Override
	public int updateWarehouseStatus(Integer sid, Integer newstoreStatus) {
		// TODO Auto-generated method stub
		int i=wareHouseMapper.updateWarehouseStatus(sid, newstoreStatus);
		if(i>0) {
			return newstoreStatus;
		}else {
			throw new RuntimeException();
		}
	
	}

	@Override
	public List<Storehouse> selectByName(String storeName) {
		// TODO Auto-generated method stub
		return wareHouseMapper.selectByName(storeName);
	}

	@Override
	public List<Storehouse> selectByid(Integer id) {
		// TODO Auto-generated method stub
		
		return  wareHouseMapper.selectByid(id);
	}
	
}
