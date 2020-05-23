package com.slq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.slq.common.constant.WarehouseConstant;
import com.slq.mapper.GatherMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.IGatherDetailService;
import com.slq.service.IGatherService;
import com.slq.service.IWarehouseService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public  class GatherServiceImpl extends ServiceImpl<GatherMapper, Gather> implements IGatherService {

	@Autowired GatherMapper mapper;
	
	@Autowired
	private IGatherDetailService gatherDetailService;
//	@Autowired
//	private IWarehouseService warehouseService;
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
	public List<Gather> getSchedulingid(String gatherId) {
		// TODO Auto-generated method stub
		return mapper.getSchedulingid(gatherId);
	}



	@Override
	public Gather getByidGather(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getByidGather(id);
	}



	@Override
	public int updgathernum(Gather gather) {
		// TODO Auto-generated method stub
		return mapper.updgahternum(gather);
	}



	@Override
	public int updgatherDetailsNum(Gather gather) {
		Gather gathers = new Gather();
		// TODO Auto-generated method stub		
		return mapper.updgatherDetailsNum(gather);
	}



	@Override
	public int updGatherDetailsSid(GatherDetails gatherDetails) {
		// TODO Auto-generated method stub
		return mapper.updGatherDetailsSid(gatherDetails);
	}



	@Override
	public List<Gather> gatherRegistrationList() {
		// TODO Auto-generated method stub
		return mapper.gatherRegistrationList();
	}



	@Override
	public List<Gather> gatherRegistrationByid(String gatherId) {
		// TODO Auto-generated method stub
		return mapper.gatherRegistrationByid(gatherId);
	}


	//根据编号获取入库单  和入库详细单
	@Override
	public Gather getGatherByIdAll(String gatherId) {
		// TODO Auto-generated method stub
		return mapper.getGatherByIdAll(gatherId);
	}


	
	@Override
	public int register(Gather scheduling) {
		LambdaUpdateWrapper<Gather> updateWrapper=Wrappers.lambdaUpdate();
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		
		
//		scheduling.setRegister(user.getUid());
//		scheduling.setRegisterTime(new Date());
//		scheduling.setCheckTag(1);
		List<GatherDetails> gatherDetails =scheduling.getGatherDetails();
		float total=0f;
		float total2=0f;
		for (GatherDetails gatherDetails2 : gatherDetails) {
			total+=gatherDetails2.getPaidAmount();
			total2+=(float) (gatherDetails2.getRealCostPrice()*gatherDetails2.getPaidAmount());
			System.out.println(gatherDetails2.getRealCostPrice());
		}
		System.out.println(total+"gveg"+total2);
		
//		scheduling.setAmountSum((int)total);
//		scheduling.setCostPriceSum((int)total2);
		
		updateWrapper.set(Gather::getRegister, user.getUid());
		updateWrapper.set(Gather::getRegisterTime,  new Date()) ;
		updateWrapper.set(Gather::getCheckTag, 0);
		updateWrapper.set(Gather::getAmountSum, total);
		updateWrapper.set(Gather::getCostPriceSum, total2);
		updateWrapper.eq(Gather::getId, scheduling.getId());
		int i=this.baseMapper.update(null, updateWrapper);
		if(i>0) {
			i = this.baseMapper.batchUpdate(gatherDetails);
//			#{paidAmount}
			return i;
		}
		return 0;
	}


	//生产完成 后制定的成品入库
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int finishedProductStorage(ManufactureSum manufacture) {
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser(); 
		String gatherId=IDGenerateUtil.getID(WarehouseConstant.GATHER_PREFIX);//入库单编号
		 String stockid=manufacture.getManufacture_id();//外来入库编号
		 String storer=user.getUid();				//入库人
		 String reasonexact=WarehouseConstant.FINISHEDPRODUCTSTORAGE;//入库详细理由
		 Integer amountSum=manufacture.getTested_amount();			//总数量
		 //总金额等于 总物料价格+总工时价格
		 double costPriceSum=manufacture.getReal_material_cost_price_sum()+manufacture.getReal_labour_cost_price_sum();//总金额
		 Integer gatheredAmountSum=0;	//确认入库总数量
		 String remark=null;				//备注
		 String register=user.getUid();
		 Date registerTime=new Date();		//登记时间
		 Gather gather=new Gather(gatherId, stockid, storer, reasonexact, amountSum, costPriceSum, gatheredAmountSum, remark, register, registerTime);				
		this.baseMapper.insert(gather);
		Integer id=gather.getId();
		if(id>0) {
			List<GatherDetails> gatherDetailsList=new ArrayList<GatherDetails>();
			
			 String productId=manufacture.getProduct_id();		//产品编号
			 String productName=manufacture.getProduct_name();		//产品名称
			 Integer mustAmount=manufacture.getTested_amount();			//入库数量
			 String productUnit=null;		//单位
			
			 double subtotal=(manufacture.getReal_material_cost_price_sum()+manufacture.getReal_labour_cost_price_sum());		//小计
			 double realCostPrice=subtotal/mustAmount;
			 Integer payTag=WarehouseConstant.NO_PAY_TAG;
			 GatherDetails gatherDetails=new GatherDetails(productId, productName, realCostPrice, subtotal, mustAmount, payTag);
			 gatherDetailsList.add(gatherDetails);
			 gatherDetailService.batchInsertFinishedGatherDetail(gatherId,gatherDetailsList);
			 return id;
		}
		throw new RuntimeException();
	}
}
