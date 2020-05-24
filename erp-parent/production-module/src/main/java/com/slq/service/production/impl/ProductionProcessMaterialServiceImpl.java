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
import com.slq.mapper.production.ProductionProcessMaterialMapper;
import com.slq.pojo.production.ProductProfitLossDetail;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.service.production.IProductionProcessMaterialService;
@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductionProcessMaterialServiceImpl extends ServiceImpl<ProductionProcessMaterialMapper, ProductionProcessMaterial> implements IProductionProcessMaterialService{

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int batchInsert(List<ProductionProcess> productionProcessList) {
		
		return this.baseMapper.batchInsert(productionProcessList);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionProcessMaterial> getProductionProcessMaterialByParentId(Integer production_process_id) {
		LambdaQueryWrapper<ProductionProcessMaterial> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ProductionProcessMaterial::getParent_id, production_process_id);
		return this.baseMapper.selectList(queryWrapper);
	}
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int batchUpdateRealAmount(Integer manufacture_id,
			List<ProductionProcessMaterial> productionProcessMaterials) {
		return this.baseMapper.batchUpdateRealAmount(manufacture_id,productionProcessMaterials);
	}
	
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionProcessMaterial> getProductionProcessMaterialAndUseAmount(Integer process_id) {
		// TODO Auto-generated method stub
		return null;
		//return this.baseMapper.getProductionProcessMaterialAndUseAmount(process_id);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionProcessMaterial> getProductionProcessMaterialAndUseAmount(Integer production_process_id,
			Integer production_process_course_id) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProductionProcessMaterialAndUseAmount(production_process_id,production_process_course_id);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int batchUpdateRealAmount1(Integer production_process_id, List<ProductionProcessMaterial> productionProcessMaterials) {
		
		return this.baseMapper.batchUpdateRealAmount1(production_process_id, productionProcessMaterials);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	//根据生产工序id修改该生产工序物料表下的 renew_amount数量
	public int batchUpdateRenewAmount(Integer production_process_id,
			List<ProductProfitLossDetail> productProfitLossDetails) {
		
		return this.baseMapper.batchUpdateRenewAmount(production_process_id,productProfitLossDetails);
	}
	//根据生产主表的id查询它的所有工序再根据所有工序查询所有的物料
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionProcessMaterial> getProductionProcessMaterialByManufactureId(Integer id) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProductionProcessMaterialByManufactureId(id);
	}

}
