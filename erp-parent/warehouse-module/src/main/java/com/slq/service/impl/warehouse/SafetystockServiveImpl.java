package com.slq.service.impl.warehouse;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.mapper.GatherMapper;
import com.slq.mapper.warehouse.SafetystockMapper;
import com.slq.pojo.production.Product;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;
<<<<<<< HEAD
=======

import com.slq.mapper.warehouse.SafetystockMapper;
import com.slq.mapper.warehouse.StockMapper;
import com.slq.mapper.warehouse.WarehousingMapper;
import com.slq.pojo.warehouse.GatherDetails;
import com.slq.pojo.warehouse.Safetystock;
import com.slq.pojo.warehouse.Stock;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.service.warehouse.ISafetystockServive;
import com.slq.pojo.User;
import com.slq.util.ActiveUser;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public class SafetystockServiveImpl implements ISafetystockServive {

	@Autowired
	SafetystockMapper safetystockMapper;
<<<<<<< HEAD
<<<<<<< HEAD
	@Autowired GatherMapper mapper;
	
=======
	@Autowired WarehousingMapper mapper;
	@Autowired StockMapper stockmapper;
>>>>>>> warehouse
=======
	@Autowired GatherMapper mapper;
	
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	
	@Override
	public List<Safetystock> getAllsafety() {
		return safetystockMapper.getAllsafety();
	}

	@Override
	public int addSafety(Safetystock safetystocks) {
		return safetystockMapper.addSafety(safetystocks);
	}

	@Override
	public Safetystock getAllsafetyById(String productId) {
		
		return safetystockMapper.getAllsafetyById(productId);
	}

	@Override
	public int updateSefeStatus(Safetystock safetystock) {		
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
<<<<<<< HEAD
<<<<<<< HEAD
=======
		Stock stock=new Stock();
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		safetystock.setChecker(user.getUname());	
		GatherDetails gatherDetails=new GatherDetails();
		gatherDetails.setSid(safetystock.getSid());
		gatherDetails.setProductId(safetystock.getProductId());
		mapper.updGatherDetailsSid(gatherDetails);
<<<<<<< HEAD
<<<<<<< HEAD
=======
		stock.setProductId(safetystock.getProductId());
		stock.setSid(safetystock.getSid());
		stock.setRegistMan(user.getUname());
		stockmapper.addStock(stock);
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
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
		System.out.println(safetystockMapper.selectByReviewList()+"qwwee");
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
