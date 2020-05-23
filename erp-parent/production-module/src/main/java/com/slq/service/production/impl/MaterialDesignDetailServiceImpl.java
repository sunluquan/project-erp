package com.slq.service.production.impl;

import java.util.Arrays;
import java.util.Collection;
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
import com.slq.mapper.production.MaterialDesignDetailMapper;
import com.slq.pojo.production.MaterialDesignDetail;
import com.slq.pojo.production.ProcessMaterialDetail;
import com.slq.service.production.IMaterialDesignDetailService;
/***
 * 物料组成详细
 * @author 孙陆泉
 *
 */
@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class MaterialDesignDetailServiceImpl extends ServiceImpl<MaterialDesignDetailMapper, MaterialDesignDetail> implements IMaterialDesignDetailService{

	@Override
	public int insert(Integer parentId,List<MaterialDesignDetail> details) {
//		LambdaQueryWrapper<MaterialDesignDetail> queryWrapper=Wrappers.lambdaQuery();
//		queryWrapper.
		return this.baseMapper.insert(parentId,details);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<MaterialDesignDetail> getMaterialDesignDetailById(Integer parentId) {
		return this.baseMapper.getMaterialDesignDetailById(parentId);
	}
	@Transactional
	@Override
	public int deleteByParentId(Integer id) {
		LambdaUpdateWrapper<MaterialDesignDetail> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(MaterialDesignDetail::getParent_id, id);
		return this.baseMapper.delete(updateWrapper);
	}
	//根据产品编号  获取这个产品的物料详细信息
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<MaterialDesignDetail> getMaterialDesignDetailByProductId(String productId) {
		
		return this.baseMapper.getMaterialDesignDetailByProductId(productId);
	}
	//修改可用数量
//	@CacheEvict(allEntries = true)
//	@Transactional
//	@Override
//	public int updateResidualAmount(List<ProcessMaterialDetail> processMaterials) {
//		
//		return this.baseMapper.batchUpdateResidualAmount(processMaterials);
//	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int updateResidualAmount(List<MaterialDesignDetail> materials) {
		// TODO Auto-generated method stub
		return  this.baseMapper.batchUpdateResidualAmount(materials);
	}
	
	@Override
	public Float totalMaterialCostPriceSum(String product_id) {
		
		return this.baseMapper.totalMaterialCostPriceSum(product_id);
	}

}
