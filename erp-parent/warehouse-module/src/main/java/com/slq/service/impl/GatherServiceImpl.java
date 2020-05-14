package com.slq.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slq.constant1.WarehouseConstant;
import com.slq.mapper.GatherMapper;
import com.slq.pojo.User;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.service.IGatherService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public  class GatherServiceImpl implements IGatherService {

	@Autowired GatherMapper mapper;
	
	@Override
	public int addgather(Gather gather) {
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		gather.setRegister(user.getUid());
		gather.setGatherId(IDGenerateUtil.getID(WarehouseConstant.GATHER_PREFIX));	
		mapper.addgather(gather);
		Integer id=gather.getId();
		if(id!=null&&id>0) {
			int i= mapper.addgatherdetails(id,gather.getGatherDetails());
			if(i>0) {
				return id;
			}
		}
		
		throw new RuntimeException();
				
	}

	

	@Override
	public int updatestocknum(Integer id, Integer amount) {
		// TODO Auto-generated method stub
		return mapper.updatestocknum(id, amount);
	}



	@Override
	public int addgatherdetailss(Integer parentId, String productId, String productName, double amount,
			String amountUnit, double costPrice, double subtotal, Integer paidAmount, Integer sid, String payTag) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void addgatherdetails(Integer id, List<GatherDetails> gatherDetails) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Gather> gatherReview() {
		// TODO Auto-generated method stub
		return mapper.gatherReview();
	}



	@Override
	public List<Gather> gatherScheduling() {
		// TODO Auto-generated method stub
		return mapper.gatherScheduling();
	}



	@Override
	public List<Gather> getSchedulingid(String stockid) {
		// TODO Auto-generated method stub
		return mapper.getSchedulingid(stockid);
	}



	@Override
	public Gather getByidGather(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getByidGather(id);
	}





}
