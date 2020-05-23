package com.slq.service.impl.warehouse;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.constant1.WarehouseConstant;
import com.slq.constant1.WarehouseStock;
import com.slq.mapper.GatherMapper;
import com.slq.mapper.warehouse.StockMapper;
import com.slq.mapper.warehouse.WarehousingMapper;
import com.slq.pojo.User;
import com.slq.pojo.warehouse.Gather;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.warehouse.Stock;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.IGatherService;
import com.slq.service.warehouse.IWarehousingService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;

import io.lettuce.core.dynamic.annotation.CommandNaming.LetterCase;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public  class WarehousingServiceImpl extends ServiceImpl<WarehousingMapper, Gather> implements IWarehousingService {

	@Autowired WarehousingMapper mapper;
	@Autowired StockMapper stockmapper;
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
	public List<Gather> getByidGather(String productId) {
		// TODO Auto-generated method stub
		return mapper.getByidGather(productId);
	}



	@Override
	public int updgathernum(Gather gather) {
		// TODO Auto-generated method stub
		return mapper.updgahternum(gather);
	}



	@Override
	public int updgatherDetailsNum(Gather gather) {		
		List<Safetystock> safetystock =gather.getSafetystock();		
		int total=0;
		double total2=0;
		for (Safetystock safetystock2 : safetystock) {
			total+=safetystock2.getQwe();
			total2+=(safetystock2.getRealCostPrice()*total);
			gather.setQwe(safetystock2.getQwe());
		}
		gather.setAmount(total);
		//gather.setSubtotal(total2);
		Gather gatherss=new Gather();
		gatherss.setGatherId(gather.getGatherId());
		gatherss.setAmountSum(total);
		gatherss.setCostPriceSum(total2);
		mapper.updgahternum(gatherss);
		return 0;
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
		List<Safetystock> safetystocks=scheduling.getSafetystock();
		float total=0f;
		float total2=0f;		
		for (GatherDetails gatherDetails2 : gatherDetails) {
			total+=gatherDetails2.getPaidAmount();
			total2+=(float) (gatherDetails2.getRealCostPrice()*gatherDetails2.getPaidAmount());
		}
		System.out.println(total);		
//		scheduling.setAmountSum(total);	
//		scheduling.setCostPriceSum((int)total2);
		updateWrapper.set(Gather::getRegister, user.getUname());
		updateWrapper.set(Gather::getRegisterTime,  new Date()) ;
		updateWrapper.set(Gather::getCheckTag, 0);
		updateWrapper.set(Gather::getGatheredAmountSum, total);
		updateWrapper.set(Gather::getCostPriceSum, total2);
		updateWrapper.eq(Gather::getId, scheduling.getId());
		//updateWrapper.eq(Gather::getProductId,scheduling.getProductId());
		//updateWrapper.eq(Gather::getPaidAmount, total);
		int i=this.baseMapper.update(null, updateWrapper);
		if(i>0) {
			i = this.baseMapper.batchUpdate(gatherDetails);
//			#{paidAmount}
			return i;
		}
		return 0;
	}



	@Override
	public int updcheck(Gather gather) {
		// TODO Auto-generated method stub
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		List<Stock> stocks=gather.getStocks();
		gather.setChecker(user.getUname());
		
		List<GatherDetails> gatherDetailsList=gather.getGatherDetails();
		GatherDetails gatherDetails=new GatherDetails();
		gatherDetails.setProductId(gather.getProductId());
		mapper.updpayTag(gatherDetails);
//		stocks.setStockId(IDGenerateUtil.getID(WarehouseStock.GATHER_PREFIX));		
		//List<Integer> id= gatherDetailsList.getId();
//		stocks.setAmount(gatherDetails.getPaidAmount());
//		stocks.setRegistMan(user.getUname());
//		stocks.setCheckMan(user.getUname());
		stockmapper.updateStock(gatherDetailsList);
		return mapper.updcheck(gather);
	}



	@Override
	public Gather getGatherDetailsByid(String productId) {
		// TODO Auto-generated method stub
		return mapper.getGatherDetailsByid(productId);
	}



	@Override
	public int updqwenum(Gather gather) {
		return 0;
//		// TODO Auto-generated method stub
//		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
//		List<GatherDetails> gatherDetails =gather.getGatherDetails();
//		int total=0;
//		double total2=0;
//		for (GatherDetails gatherDetails2 : gatherDetails) {
//			total+=gatherDetails2.getAmount();
//			total2+=gatherDetails2.getRealCostPrice()*gatherDetails2.getAmount();
//			
//		}
//		System.out.println(total);
//		//gather.setProductId(gather.getProductId());
//		//gather.setAmount(gather.getAmount());
//		//gather.setSid(gather.getSid());
//		gather.setGatherId(gather.getGatherId());
//		gather.setAmountSum(total);
//		gather.setCostPriceSum(total2);
//		gather.setAttemper(user.getUname());
//        mapper.updgahternum(gather);       
//        return mapper.updgatherDetailsNum(gather);
	}



	@Override
	public int updqwe(List<Safetystock> safetystock) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Storehouse> selectStorehousesGather(String productId) {
		// TODO Auto-generated method stub
		return mapper.selectStorehousesGather(productId);
	}



	@Override
	public int updpayTag(GatherDetails gatherDetails) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Gather> selectRegistration() {
		// TODO Auto-generated method stub
		return mapper.selectRegistration();
	}



	@Override
	public int updgathersum(Gather scheduling) {
		LambdaUpdateWrapper<Gather> updateWrapper=Wrappers.lambdaUpdate();
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		
		
//		scheduling.setRegister(user.getUid());
//		scheduling.setRegisterTime(new Date());
//		scheduling.setCheckTag(1);
		List<GatherDetails> gatherDetails =scheduling.getGatherDetails();
		Gather gathers=new Gather();
		int total=0;
		double total2=0;
		for (GatherDetails gatherDetails2 : gatherDetails) {
			total+=gatherDetails2.getAmount();
			total2+=gatherDetails2.getRealCostPrice()*gatherDetails2.getAmount();
			
		}
		System.out.println(total);		
//		scheduling.setAmountSum(total);	
//		scheduling.setCostPriceSum((int)total2);
		//gather.setProductId(gather.getProductId());
				//gather.setAmount(gather.getAmount());
				//gather.setSid(gather.getSid());
		gathers.setGatherId(scheduling.getGatherId());
		gathers.setAttemper(user.getUname()) ;
		gathers.setAmountSum(total);
		gathers.setCostPriceSum(total2);		
		//updateWrapper.eq(Gather::getProductId,scheduling.getProductId());
		//updateWrapper.eq(Gather::getPaidAmount, total);
		int i=mapper.updgahternum(gathers);
		if(i>0) {
			i = this.baseMapper.updgatherDetailsNum(gatherDetails);
//			#{paidAmount}
			return i;
		}
		return 0;
	}
	
}
