package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.production.ProductProfitLossDetailMapper;
import com.slq.pojo.production.ProductProfitLossDetail;
import com.slq.service.production.IProductProfitLossDetailService;
@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductProfitLossDetailServiceImpl extends ServiceImpl<ProductProfitLossDetailMapper, ProductProfitLossDetail> implements IProductProfitLossDetailService{

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public Integer batchInsert(Integer id, List<ProductProfitLossDetail> productProfitLossDetails) {
		
		return this.baseMapper.batchInsert(id,productProfitLossDetails);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductProfitLossDetail> getProductProfitLossByParentId(Integer id) {
		LambdaQueryWrapper<ProductProfitLossDetail> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ProductProfitLossDetail::getParent_id, id);
		return this.baseMapper.selectList(queryWrapper);
	}
	
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int batchUpdate(List<ProductProfitLossDetail> productProfitLossDetails) {
		
		return this.baseMapper.batchUpdate(productProfitLossDetails);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int batchDelete(Integer id) {
		LambdaUpdateWrapper<ProductProfitLossDetail> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(ProductProfitLossDetail::getParent_id, id);
		return this.baseMapper.delete(updateWrapper);
	}

}
