package com.slq.service.production.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.common.constant.ProductionConstant;
import com.slq.mapper.production.ProductionPlanMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ProductionPlan;
import com.slq.pojo.production.ProductionPlanDetail;
import com.slq.service.production.IProductionPlanDetailService;
import com.slq.service.production.IProductionPlanService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;

@Service
@CacheConfig(cacheNames = "productionModule")
@Transactional
public class ProductionPlanServiceImpl extends ServiceImpl<ProductionPlanMapper, ProductionPlan>
		implements IProductionPlanService {
	@Autowired
	private IProductionPlanDetailService productionPlanDetailService;

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int insert(ProductionPlan productionPlan) {
		Integer check_tag = ProductionConstant.UNREVIEWED;
		productionPlan.setCheck_tag(check_tag);
		String message = "生产产品总单设置失败!";
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		// 获取生产计划编号
		productionPlan.setProduction_id(IDGenerateUtil.getID(ProductionConstant.PRODUCTION_PREFIX));
		productionPlan.setRegister(user.getUid());
		productionPlan.setRegister_time(new Date());
		int i = this.baseMapper.insert(productionPlan);
		Integer id = productionPlan.getId();
		if (id != null && id > 0) {
			List<ProductionPlanDetail> productionPlanDetails = productionPlan.getProductionPlanDetails();
			i = productionPlanDetailService.insert(id, productionPlanDetails);
			if (i > 0) {
				return id;
			}
			message = "生产产品详细单添加失败!";
		}
		throw new RuntimeException(message);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ProductionPlan> searchProductionPlan(Params params) {
//		LambdaQueryWrapper<ProductionPlan> queryWrapper=Wrappers.lambdaQuery();
//		queryWrapper.between(ProductionPlan::getRegister_time, params.getDateTimeRange()[0], params.getDateTimeRange()[1]);
//		queryWrapper.notLike(ProductionPlan::getProduction_id, params.getKeyWord());
//		if(params.getOrderingRule()!=null&&params.getOrderingRule().getProp()!=null&&!params.getOrderingRule().getProp().equals("")) {
//			queryWrapper.orderby
//		}else {
//			queryWrapper.orderByDesc(ProductionPlan::getProduction_id);
//		}
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
				.doSelectPageInfo(() -> this.baseMapper.searchProductionPlan(params));
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionPlan> getCheckerProductionPlans() {
		// 获取审核状态为未审核的
		Integer check_tag = ProductionConstant.UNREVIEWED;
		LambdaQueryWrapper<ProductionPlan> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(ProductionPlan::getCheck_tag, check_tag);
		queryWrapper.orderByDesc(ProductionPlan::getProduction_id);
		return this.baseMapper.selectList(queryWrapper);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProductionPlan getProductionPlanAndDetailById(Integer id) {

		return this.baseMapper.getProductionPlanAndDetailById(id);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int pass(ProductionPlan productionPlan) {
		Integer id = productionPlan.getId();
		// 审核通过
		Integer check_tag = ProductionConstant.PASS;
		LambdaUpdateWrapper<ProductionPlan> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionPlan::getCheck_tag, check_tag);
		updateWrapper.eq(ProductionPlan::getId, id);
		return this.baseMapper.update(null, updateWrapper);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int reject(ProductionPlan productionPlan) {
		Integer id = productionPlan.getId();
		// 审核未通过
		Integer check_tag = ProductionConstant.REJECT;
		String check_suggestion = productionPlan.getCheck_suggestion();
		LambdaUpdateWrapper<ProductionPlan> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionPlan::getCheck_tag, check_tag);
		updateWrapper.set(ProductionPlan::getCheck_suggestion, check_suggestion);
		updateWrapper.eq(ProductionPlan::getId, id);
		return this.baseMapper.update(null, updateWrapper);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int update(ProductionPlan productionPlan) {
		Integer id=productionPlan.getId();
		// 获取审核状态为未审核的
		Integer check_tag = ProductionConstant.UNREVIEWED;
		LambdaUpdateWrapper<ProductionPlan> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionPlan::getCheck_tag, check_tag);
		updateWrapper.set(ProductionPlan::getRemark, productionPlan.getRemark());
		updateWrapper.set(ProductionPlan::getDesigner, productionPlan.getDesigner());
		updateWrapper.set(ProductionPlan::getProduction_reason, productionPlan.getProduction_reason());
		updateWrapper.eq(ProductionPlan::getId, id);
		int i = this.baseMapper.update(null, updateWrapper);
		String message="生产计划单修改失败!";
		if(i>0) {
			i = productionPlanDetailService.deleteByParentId(id);
			if(i>0) {
				i = productionPlanDetailService.insert(id, productionPlan.getProductionPlanDetails());
				if(i>0) {
					return i;
				}
				message="生产计划详细单设置失败!";
			}
		}
		throw new RuntimeException(message);
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	////获取满足制定派工单的生产计划
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionPlan> getMeetDespatchingProductionPlans() {
		
		return this.baseMapper.getMeetDespatchingProductionPlans();
	}
<<<<<<< HEAD
=======
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	

}
