package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.common.constant.ProductConstant;
import com.slq.mapper.production.ProductProcessMapper;
import com.slq.pojo.production.ProductProcess;
import com.slq.service.production.IProductProcessService;
/***
 * 
 * @author 孙陆泉
 * 工序表service
 */
@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductProcessServiceImpl extends ServiceImpl<ProductProcessMapper, ProductProcess> implements IProductProcessService{

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductProcess> selectUsableProcess() {
		LambdaQueryWrapper<ProductProcess> queryWrapper=Wrappers.lambdaQuery();
		//可用状态
		queryWrapper.eq(ProductProcess::getState, ProductConstant.UP_STATE);
		return this.baseMapper.selectList(queryWrapper);
	}

}
