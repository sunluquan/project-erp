package com.slq.service.production.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.production.ProductUseMapper;
import com.slq.pojo.production.ProductUse;
import com.slq.service.production.IProductUseService;
@Service
public class ProductUseServiceImpl extends ServiceImpl<ProductUseMapper, ProductUse> implements IProductUseService{

	@Override
	public List<ProductUse> getProductUseAll() {
		LambdaQueryWrapper<ProductUse> queryWrapper=Wrappers.lambdaQuery();
		
		return this.baseMapper.selectList(queryWrapper);
	}

}
