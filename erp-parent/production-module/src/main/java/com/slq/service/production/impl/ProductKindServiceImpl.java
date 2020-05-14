package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.production.ProductKindMapper;
import com.slq.pojo.production.ProductKind;
import com.slq.service.production.IProductKindService;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductKindServiceImpl extends ServiceImpl<ProductKindMapper, ProductKind> implements IProductKindService{

	//这里需要使用redis缓存
	@Override
	public List<ProductKind> getKinds() {
		List<ProductKind> productKinds = this.baseMapper.getProductKindAll();
		return productKinds;
	}

}
