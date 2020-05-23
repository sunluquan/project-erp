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

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.common.constant.ProductionProfitLossConstant;
import com.slq.controller.production.ProductProfitLossController;
import com.slq.mapper.production.ProductProfitLossMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ProductProfitLoss;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.service.production.IProductProfitLossDetailService;
import com.slq.service.production.IProductProfitLossService;
import com.slq.service.production.IProductionProcessMaterialService;
import com.slq.util.ActiveUser;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductProfitLossServiceImpl extends ServiceImpl<ProductProfitLossMapper, ProductProfitLoss> implements IProductProfitLossService{

	@Autowired
	private IProductProfitLossDetailService productProfitLossDetailService;
	@Autowired
	private IProductionProcessMaterialService productionProcessMaterialService;
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int insert(ProductProfitLoss productProfitLoss) {
		productProfitLoss.setCheck_tag(ProductionProfitLossConstant.UNREVIEWED);
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		productProfitLoss.setRegister(user.getUid());
		productProfitLoss.setRegister_time(new Date());
		this.baseMapper.insert(productProfitLoss);
		Integer id=productProfitLoss.getId();
		//这里有一个想法
		//就是当它进行物料申领亏损登记的时候  设置
		//当前生产工序的状态为宁外一个状态 审核中
		if(id!=null&&id>0) {
			int i = productProfitLossDetailService.batchInsert(id,productProfitLoss.getProductProfitLossDetails());
			return id;
		}
		throw new RuntimeException();
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductProfitLoss> getCheckProductProfitLossList() {
		LambdaQueryWrapper<ProductProfitLoss> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ProductProfitLoss::getCheck_tag, ProductionProfitLossConstant.UNREVIEWED);
		queryWrapper.orderByDesc(ProductProfitLoss::getManufacture_id);
		List<ProductProfitLoss> productProfitLossList=this.baseMapper.selectList(queryWrapper);
		return productProfitLossList;
	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int pass(ProductProfitLoss productProfitLoss) {
		//设置状态为审核通过
		Integer check_tag=ProductionProfitLossConstant.PASS;
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		LambdaUpdateWrapper<ProductProfitLoss> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.set(ProductProfitLoss::getCheck_tag,check_tag );
		updateWrapper.set(ProductProfitLoss::getChecker,user.getUid());
		updateWrapper.set(ProductProfitLoss::getCheck_time,new Date());
		updateWrapper.set(ProductProfitLoss::getRemark, productProfitLoss.getRemark());
		updateWrapper.eq(ProductProfitLoss::getId, productProfitLoss.getId());
		int i=this.baseMapper.update(null, updateWrapper);
		if(i>0) {
			i = productProfitLossDetailService.batchUpdate(productProfitLoss.getProductProfitLossDetails());
			//根据生产工序id修改该生产工序物料表下的 renew_amount数量
			i = productionProcessMaterialService.batchUpdateRenewAmount(productProfitLoss.getProduction_process_id(),productProfitLoss.getProductProfitLossDetails());
			return i;
		}
		throw new RuntimeException();
	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override 
	public int reject(ProductProfitLoss productProfitLoss) {
		//设置状态为驳回状态
		Integer check_tag=ProductionProfitLossConstant.REJECT;
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		LambdaUpdateWrapper<ProductProfitLoss> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.set(ProductProfitLoss::getCheck_tag,check_tag );
		updateWrapper.set(ProductProfitLoss::getCheck_suggestion,productProfitLoss.getCheck_suggestion() );
		updateWrapper.set(ProductProfitLoss::getChecker,user.getUid());
		updateWrapper.set(ProductProfitLoss::getCheck_time,new Date());
		updateWrapper.set(ProductProfitLoss::getRemark, productProfitLoss.getRemark());
		updateWrapper.eq(ProductProfitLoss::getId, productProfitLoss.getId());
		int i=this.baseMapper.update(null, updateWrapper);
		//如果在登记的时候设置了  
		//它对应的派工单的对应生产工序的状态的时候 
		//这里就需要改回来 改回为可以登记的状态
		return i;
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ProductProfitLoss> searchProductProfitLoss(Params params) {
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
					.doSelectPageInfo(()->this.baseMapper.searchProductProfitLoss(params)); 
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProductProfitLoss getProductProfitLossByIdAll(Integer id) {
		LambdaQueryWrapper<ProductProfitLoss> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ProductProfitLoss::getId, id);
		ProductProfitLoss productProfitLoss=this.baseMapper.selectOne(queryWrapper);
		productProfitLoss.setProductProfitLossDetails(productProfitLossDetailService.getProductProfitLossByParentId(id));
		return productProfitLoss;
	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int update(ProductProfitLoss productProfitLoss) {
		Integer check_tag=ProductionProfitLossConstant.UNREVIEWED;
		LambdaUpdateWrapper<ProductProfitLoss> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.set(ProductProfitLoss::getCheck_tag,check_tag );
		updateWrapper.set(ProductProfitLoss::getRemark, productProfitLoss.getRemark());
		updateWrapper.eq(ProductProfitLoss::getId,productProfitLoss.getId());
		int i=this.baseMapper.update(null, updateWrapper);
		if(i>0) {
			i = productProfitLossDetailService.batchDelete(productProfitLoss.getId());
			i = productProfitLossDetailService.batchInsert(productProfitLoss.getId(), productProfitLoss.getProductProfitLossDetails());
			return i;
		}
		throw new RuntimeException();
	}
	@Override
	public Integer insertInternalProfitLoss(Integer id, List<ProductionProcessMaterial> productionProcessMaterials,
			Integer numerical_difference) {
		// TODO Auto-generated method stub
		return null;
	}

}
