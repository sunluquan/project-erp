package com.slq.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.mapper.SafetystockMapper;
import com.slq.pojo.production.Product;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.User;
import com.slq.service.ISafetystockServive;
import com.slq.util.ActiveUser;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public class SafetystockServiveImpl implements ISafetystockServive {

	@Autowired
	SafetystockMapper safetystockMapper;
	
	
	
	@Override
	public List<Safetystock> getAllsafety() {
		return safetystockMapper.getAllsafety();
	}

	@Override
	public int addSafety(Safetystock safetystocks) {
		return safetystockMapper.addSafety(safetystocks);
	}

	@Override
	public Safetystock getAllsafetyById(Integer id) {
		
		return safetystockMapper.getAllsafetyById(id);
	}

	@Override
	public int updateSefeStatus(Safetystock safetystock) {
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		safetystock.setChecker(user.getUid());
		return safetystockMapper.updateSefeStatus(safetystock);
	}

	@Override
	public int updatestockId(Safetystock stocks) {
		// TODO Auto-generated method stub
		return safetystockMapper.updatestockId(stocks);
	}

	@Override
	public List<Safetystock> selectByReviewList() {
		// TODO Auto-generated method stub
		return safetystockMapper.selectByReviewList();
	}

	@Override
	public int updateChecktag(Safetystock safetystock) {
		// TODO Auto-generated method stub
		return safetystockMapper.updateChecktag(safetystock);
	}

	@Override
	public List<Safetystock> selectSafetystocks() {
		// TODO Auto-generated method stub
		return safetystockMapper.selectSafetystocks();
	}

	@Override
	public List<Safetystock> selectStockChange() {
		// TODO Auto-generated method stub
		return safetystockMapper.selectStockChange();
	}

	@Override
	public int updateStockChange(Safetystock safetystock) {
		// TODO Auto-generated method stub
		return safetystockMapper.updateStockChange(safetystock);
	}

	@Override
	public List<Safetystock> selectByName(String product_name) {
		// TODO Auto-generated method stub
		return safetystockMapper.selectByName(product_name);
	}

	@Override
	public List<Safetystock> selectBytime(String registertime) {
		// TODO Auto-generated method stub
		return safetystockMapper.selectBytime(registertime);
	}


}
