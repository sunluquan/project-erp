package com.slq.service.production.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slq.common.constant.ProductConstant;
import com.slq.mapper.production.ProcessDetailMapper;
import com.slq.pojo.production.ProcessDetail;
import com.slq.pojo.production.ProcessMaterialDetail;
import com.slq.service.production.IMaterialDesignDetailService;
import com.slq.service.production.IProcessDetailService;
import com.slq.service.production.IProcessMaterialDetailService;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProcessDetailServiceImpl extends ServiceImpl<ProcessDetailMapper, ProcessDetail>
		implements IProcessDetailService {

	@Autowired
	private IProcessMaterialDetailService processMaterialDetailService;
	@Autowired
	private IMaterialDesignDetailService materialDesignDetailService;

	@Transactional
	@Override
	public int batchInsert(Integer id, List<ProcessDetail> processDetails) {
		return this.baseMapper.batchInsert(id, processDetails);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessDetail> getProcessDetailByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProcessDetailByParentId(parentId);
	}

	// 根据产品工序设计总表的编号 删除 下面的所有详细表
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int deleteByParentId(Integer id) {
		// TODO Auto-generated method stub
		LambdaUpdateWrapper<ProcessDetail> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.eq(ProcessDetail::getParent_id, id);
		return this.baseMapper.delete(updateWrapper);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int updateProcessDetailMaterial(ProcessDetail processDetail) {
		Integer material_tag = processDetail.getMaterial_tag();
		Integer id = processDetail.getId();
		List<ProcessMaterialDetail> processMaterials = processDetail.getProcessMaterials();
		// 已经设计的状态
		Integer accomplish = ProductConstant.ACCOMPLISH_PROCESS_MATERIAL_TAG;
		Integer not_accomplish = ProductConstant.NOT_PROCESS_MATERIAL_TAG;
		LambdaUpdateWrapper<ProcessDetail> updateWrapper = Wrappers.lambdaUpdate();
		int i = 1;
		String message = "工序物料设置失败!";
		// 说明他设计过了 就需要先删除他 然后再新增
		// 如果没有设计就是直接新增就行了
		if (material_tag == accomplish) {
			i = processMaterialDetailService.delete(id);
			//如果它不仅是已经设计的状态  
			//而且 他又没有选择设计物料了  说明它可能点击错了物料
			if (processMaterials == null || processMaterials.isEmpty()) {
				i = materialDesignDetailService.updateResidualAmount(processDetail.getMaterials());
				message="物料数量设置出错!";
				if(i>=0) {
					Integer not_process_material_tag=ProductConstant.NOT_PROCESS_MATERIAL_TAG;
					updateWrapper.set(ProcessDetail::getMaterial_tag, not_process_material_tag);
					updateWrapper.eq(ProcessDetail::getId, id);
					i = this.baseMapper.update(null, updateWrapper);
					if(i>0) {
						return not_process_material_tag;
					}
					message="工序物料状态设置失败!";
				}
				throw new RuntimeException(message);
			}
		}
		// 我还需要修改 物料详细表的可用数量
		if (i > 0) {
			i = materialDesignDetailService.updateResidualAmount(processDetail.getMaterials());
			if (i >= 0) {
				i = processMaterialDetailService.insert(id, processMaterials);
				if (i > 0) {
					updateWrapper.set(ProcessDetail::getMaterial_tag, accomplish);
					updateWrapper.eq(ProcessDetail::getId, id);
					i = this.baseMapper.update(null, updateWrapper);
					if (i >= 0) {
						return accomplish;
					}
				}
			}
		}
		throw new RuntimeException(message);
	}

	@Override
	public Float processTotalManhoirCostPriceSum(Integer id) {

		return this.baseMapper.processTotalManhoirCostPriceSum(id);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessDetail> getProcessDetailByProductId(String product_id) {
		
		return this.baseMapper.getProcessDetailByProductId(product_id);
	}

}
