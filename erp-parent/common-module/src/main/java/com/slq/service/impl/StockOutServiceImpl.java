package com.slq.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.mapper.StockOutDetailsMapper;
import com.slq.mapper.StockOutMapper;
import com.slq.pojo.warehouse.sPay;
import com.slq.pojo.warehouse.sPayDetails;
import com.slq.service.IStockOutService;

@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public class StockOutServiceImpl implements IStockOutService {

	@Autowired
	StockOutMapper stockOutMapper;	//出库表的mapper
	
	@Autowired
	StockOutDetailsMapper stockOutDetailsMapper;//出库详细表的mapper
	
	/*
	 * 添加出库表
	 */
	@Transactional
	@Override
	public int addStockOut(sPay spay) {
		stockOutMapper.addStockOut(spay);	//调用出库表的方法
		String id=spay.getPayId();			//获取当前单子的出库编号
		if(id!=null&&!id.equals("")) {
			int i=addStockOutDetails(id, spay.getSpayDetails());
			if(i>0) {
				return i;
			}
		}
		throw new RuntimeException();
	}
	
	/*
	 * 添加出库详细表
	 */
	private int addStockOutDetails(String id, List<sPayDetails> spayDetails) {
		return stockOutDetailsMapper.addStockOutDetails(id, spayDetails);
	}

	/*
	 *查询 所有未审核的出库单
	 */
	@Override
	public List<sPay> selectByTag() {
		return stockOutMapper.selectByTag();
	}

	/*
	 * 根据出库单编号查询出库单对象
	 */
	@Override
	public sPay selectByPayId(String id) {
		return stockOutMapper.selectByPayId(id);
	}

	@Override
	public int upStatus(sPay spay) {
		return stockOutMapper.upStatus(spay);
	}

	@Override
	public List<sPay> selectAll() {
		return stockOutMapper.selectAll();
	}

	@Override
	public int upNotStatus(sPay spay) {
		return stockOutMapper.upNotStatus(spay);
	}

	@Override
	public PageInfo<sPay> searchSpay(Params params) {
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
					.doSelectPageInfo(()->stockOutMapper.searchSpay(params));
	}

	@Override
	public List<sPay> selectTags() {
		return stockOutMapper.selectTags();
	}

	@Override
	public sPay selectDetails(sPayDetails spay) {
		return stockOutMapper.selectDetails(spay);
	}

	@Override
	public int selectCount() {
		return stockOutMapper.selectCount();
	}

	@Override
	public sPay selectDetails(String payId, String productId) {
		sPay sPay=stockOutMapper.selectDetails(payId);
		List<sPayDetails> sPayDetailsList=new ArrayList<sPayDetails>();
		sPayDetailsList.add(stockOutDetailsMapper.getSpayDetailsByParentIdAndProductIdOne(sPay.getPayId(), productId));
		sPay.setSpayDetails(sPayDetailsList);
		return sPay;
	}
	
	@Transactional
	@Override
	public int upSum(sPayDetails detail) {
		int i = stockOutMapper.upSum(detail);
//		i=stockOutDetailsMapper.getNotRegistrationCount(detail.getParentId());
//		if(i==0) {
//			String alreadyRegistration="已调度";
//			i = stockOutMapper.updateStoreTag(alreadyRegistration);
//		}
		return i;
	}

	@Override
	public List<sPay> selectRegists() {
		return stockOutMapper.selectRegists();
	}

	@Override
	public int selectRegistCount() {
		return stockOutMapper.selectRegistCount();
	}

	@Override
	public int aksure(sPay spay) {
		return stockOutMapper.aksure(spay);
	}

	@Override
	public int checkInOne(sPayDetails sPayDetails) {
		return 0;
	}


}
