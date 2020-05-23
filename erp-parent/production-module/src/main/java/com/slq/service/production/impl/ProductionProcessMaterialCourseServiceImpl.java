package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.mapper.production.ProductionProcessMaterialCourseMapper;
import com.slq.pojo.production.ProductionProcessCourse;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.pojo.production.ProductionProcessMaterialCourse;
import com.slq.service.production.IProductionProcessMaterialCourseService;
@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductionProcessMaterialCourseServiceImpl extends ServiceImpl<ProductionProcessMaterialCourseMapper, ProductionProcessMaterialCourse> implements IProductionProcessMaterialCourseService{

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int batchInsert(Integer manufacture_id, List<ProductionProcessMaterial> productionProcessMaterials) {
		
		return this.baseMapper.batchInsert(manufacture_id,productionProcessMaterials);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionProcessMaterialCourse> byParentId(Integer id) {
		LambdaQueryWrapper<ProductionProcessMaterialCourse> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ProductionProcessMaterialCourse::getParent_id, id);
		return this.baseMapper.selectList(queryWrapper);
	}
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int batchUpdate(List<ProductionProcessMaterial> productionProcessMaterials) {
		// TODO Auto-generated method stub
		return this.baseMapper.batchUpdate(productionProcessMaterials);
	}
}
