package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.production.ProductionPlanDetailMapper;
import com.slq.pojo.production.ProductionPlanDetail;
import com.slq.service.production.IProductionPlanDetailService;

@Service
@CacheConfig(cacheNames = "productionModule")
@Transactional
public class ProductionPlanDetailServiceImpl extends ServiceImpl<ProductionPlanDetailMapper, ProductionPlanDetail> implements IProductionPlanDetailService{

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int insert(Integer id,List<ProductionPlanDetail> productionPlanDetails) {
		
		return this.baseMapper.batchInsert(id,productionPlanDetails);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionPlanDetail> getProductionPlanDetailByParentId(Integer parent_id) {
		
		return this.baseMapper.getProductionPlanDetailByParentId(parent_id);
	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int deleteByParentId(Integer id) {
		LambdaUpdateWrapper<ProductionPlanDetail> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(ProductionPlanDetail::getParent_id, id);
		return this.baseMapper.delete(updateWrapper);
	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int batchUpdateManufacture(Integer id, List<ProductionPlanDetail> productionPlanDetails,
			Integer manufacture_tag) {
		
		return this.baseMapper.batchUpdateManufacture(id,productionPlanDetails,manufacture_tag);
	}
	// 获取可以制定派工单的生产计划单以及详细
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionPlanDetail> getProductionPlanDetailMeetDespatching() {
		
		return this.baseMapper.getProductionPlanDetailMeetDespatching();
	}

}
