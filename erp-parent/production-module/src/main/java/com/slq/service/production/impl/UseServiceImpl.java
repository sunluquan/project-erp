package com.slq.service.production.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.production.UseMapper;
import com.slq.pojo.production.Use;
import com.slq.service.production.IUseService;

@CacheConfig(cacheNames = "productionModule")
@Service
public class UseServiceImpl extends ServiceImpl<UseMapper, Use> implements IUseService{

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public Use getUse(Integer use_type) {
		//LambdaQueryWrapper<Use> queryWrapper=Wrappers.lambdaQuery();
		return this.baseMapper.selectById(use_type);
	}

}
