package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
=======
>>>>>>> warehouse
=======
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<<<<<<< HEAD
<<<<<<< HEAD
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
=======
>>>>>>> warehouse
=======
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
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

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ProductKind> searchProductKind(Params params) {
		return PageHelper.startPage(params.getPageNum(),params.getPageSize())
					.doSelectPageInfo(()->this.baseMapper.searchProductKind(params));
	}
	@CacheEvict(allEntries = true)
	@Override
	public int insert(ProductKind productKind) {
		int i = this.baseMapper.insert(productKind);
		return i;
	}

<<<<<<< HEAD
=======
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
