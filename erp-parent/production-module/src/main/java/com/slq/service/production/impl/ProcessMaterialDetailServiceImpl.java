package com.slq.service.production.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.common.constant.ProductConstant;
import com.slq.mapper.production.ProcessMaterialDetailMapper;
import com.slq.pojo.production.ProcessDetail;
import com.slq.pojo.production.ProcessMaterialDetail;
import com.slq.service.production.IProcessMaterialDetailService;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProcessMaterialDetailServiceImpl extends ServiceImpl<ProcessMaterialDetailMapper, ProcessMaterialDetail> implements IProcessMaterialDetailService{

	
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessMaterialDetail> getProcessMaterialDesignByParentId(Integer parentId) {
		
		return this.baseMapper.getProcessMaterialDesignByParentId(parentId);
	}
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int insert(Integer id, List<ProcessMaterialDetail> processMaterialDetails) {
		
		return this.baseMapper.batchInsert(id,processMaterialDetails);
	}
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int delete(Integer id) {
		LambdaUpdateWrapper<ProcessMaterialDetail> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(ProcessMaterialDetail::getParent_id, id);
		return this.baseMapper.delete(updateWrapper);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessMaterialDetail> getProcessMaterialDesignByParentId1(Integer parent_id) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProcessMaterialDesignByParentId1(parent_id);
	}

}
