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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.common.constant.ProductConstant;
import com.slq.mapper.production.ProcessDesignMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ProcessDesign;
import com.slq.pojo.production.Product;
import com.slq.service.production.IMaterialDesignDetailService;
import com.slq.service.production.IMaterialDesignService;
import com.slq.service.production.IProcessDesignService;
import com.slq.service.production.IProcessDetailService;
import com.slq.service.production.IProductService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProcessDesignServiceImpl extends ServiceImpl<ProcessDesignMapper, ProcessDesign>
		implements IProcessDesignService {

	@Autowired
	private IProcessDetailService processDetailService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductService produtService;
	@Autowired
	private IMaterialDesignService materialDesignService;
	@Autowired
	private IMaterialDesignDetailService materialDesignDetailService;
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int insert(ProcessDesign processDesign) {
		String message = "工序新增失败!";
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		processDesign.setRegister(user.getUid());
		// 设置为审核中
		processDesign.setCheck_tag(ProductConstant.PROCESS_DESIGN_UNREVIEWED);
		//
		processDesign.setProcess_id(IDGenerateUtil.getID(ProductConstant.PROCESS_DESIGN));
		this.baseMapper.insert(processDesign);
		Integer id = processDesign.getId();
		if (id != null) {
			int i = processDetailService.batchInsert(id, processDesign.getProcessDetails());
			if (i > 0) {
				// 修改产品表的 process_tag为0 已经设计再审核 或者 已经驳回
				i = produtService.updateProcess_tag(processDesign.getProduct_id(),
						ProductConstant.MAKING_PROCESS_CHECKER);
				if (i > 0) {
					return id;
				}
			}
			message = "工序详细新增失败!";
		}
		throw new RuntimeException(message);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessDesign> getProcessDesignChecker() {
		// 获取这个 check_tag审核状态为审核中的
		Integer check_tag = ProductConstant.PROCESS_DESIGN_UNREVIEWED;
		// 第二个参数是 工序物料设计状态
		return this.baseMapper.getProcessList(check_tag, null);
	}

	// 获取工序设计主表和 详细表的数据
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProcessDesign getProcessDesignById(Integer id) {

		return this.baseMapper.getProcessDesignById(id);
	}

	// 工序设计单的审核通过
	@CacheEvict(allEntries = true)
	@Override
	public int processDesignPass(ProcessDesign processDesign) {
		LambdaUpdateWrapper<ProcessDesign> updateWrapper = Wrappers.lambdaUpdate();
		String message = "工序审核状态设置失败!";
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		Integer id = processDesign.getId();
		String product_id = processDesign.getProduct_id();
		String checker = user.getUid();
		// 设置工序为通过
		Integer check_tag = ProductConstant.PROCESS_DESIGN_PASS;
		Date current_time = new Date();
		processDesign = new ProcessDesign(id, checker, current_time, check_tag);
		int i = this.baseMapper.updateById(processDesign);
		if (i > 0) {
			// 设置产品表的process_tag状态为 0 表示设计审核中或者驳回状态
			Integer process_tag = ProductConstant.MAKING_PROCESS_CHECKER;
			i = productService.updateProcess_tag(product_id, process_tag);
			if (i > 0) {
				return i;
			}
			message = "产品工序状态修改失败!";
		}
		throw new RuntimeException(message);
	}

	// 驳回
	@CacheEvict(allEntries = true)
	@Override
	public int processDesignReject(ProcessDesign processDesign) {
		String message = "工序审核状态设置失败!";
		LambdaUpdateWrapper<ProcessDesign> updateWrapper = Wrappers.lambdaUpdate();
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		Integer id = processDesign.getId();
		String product_id = processDesign.getProduct_id();
		String checker = user.getUid();
		// 设置工序为没有通过
		Integer check_tag = ProductConstant.MATERIAL_DESIGN_REJECT;
		Date current_time = new Date();
		String rejectMessage = processDesign.getCheck_suggestion();
		processDesign = new ProcessDesign(id, checker, current_time, rejectMessage, check_tag);
		int i = this.baseMapper.updateById(processDesign);
		if (i > 0) {
			return i;
			// 虽然他是驳回状态 但是也需要设置产品表下的process_tag字段的状态
			// 因为如果不设置的话
			// 如果查询产品第一次来设计工序的时候还没有设计单
			// 这个时候 需要联合processDesign表查询不属于 这个表中的产品
			// 这样才能查到 这个processDesign表中没有产品id的记录
			// 因为只有查出产品编号没有这个processDesign表
			// 才能知道它是不是第一次来设计
			// 然而现在的话 只需要根据 产品表的的process_tag字段
			// 为-1 默认为-1 的时候就能知道 他在这个process Design中没有
			// 然后再它设计了之后 不管是他再审核中还是驳回状态 都将
			// 产品表中的字段prodcess_tag该写0 这样就知道他在这个表中了
			//
			// 这里想到了 我新增这个产品工序设计单的时候就已经
			// 修改了产品表下的prodcess_tag=0了 所以这里如果驳回的话不需要做修改了
			// materialDesign表也是如此
//			Integer process_tag=ProductConstant.MAKING_PROCESS_CHECKER;
//			productService.updateProcess_tag(product_id, process_tag);
		}
		throw new RuntimeException(message);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ProcessDesign> processDesignsearch(Params params) {
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
				.doSelectPageInfo(() -> this.baseMapper.processDesignsearch(params));
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int processDesignUpdate(ProcessDesign processDesign) {
		String message = "修改失败!";
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		ProcessDesign updateProcessDesign = new ProcessDesign();
		Integer id = processDesign.getId();
		updateProcessDesign.setId(id);
		updateProcessDesign.setDesigner(processDesign.getDesigner());
		// 修改后重新设置状态为需要审核
		updateProcessDesign.setCheck_tag(ProductConstant.PROCESS_DESIGN_UNREVIEWED);
		updateProcessDesign.setChanger(user.getUid());
		updateProcessDesign.setChange_time(new Date());
		updateProcessDesign.setMessage(processDesign.getMessage());
		updateProcessDesign.setCheck_suggestion(null);
		int i = this.baseMapper.updateById(updateProcessDesign);
		if (i > 0) {
			i = processDetailService.deleteByParentId(id);
			if (i > 0) {
				i = processDetailService.batchInsert(id, processDesign.getProcessDetails());
				if (i > 0) {
					return i;
				}
			}
		}
		throw new RuntimeException(message);
	}

	// 获取可以制定工序物料设计的 工序单
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessDesign> accordMakingProcessMaterial() {
		// 工序设计单为通过状态
		Integer check_tag = ProductConstant.PROCESS_DESIGN_PASS;
		// 工序物料设计为 从未设计过
		Integer process_material_tag = ProductConstant.NOT_PROCESS_MATERIAL_DESIGN;
		return this.baseMapper.getProcessList(check_tag, process_material_tag);
	}

	// 获取工序详细单 和工序详细物料纤细单
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProcessDesign getProcessDesignJoinMaterialDetail(Integer id) {

		return this.getBaseMapper().getProcessDesignJoinMaterialDetail(id);
	}

	// 更新工序物料设计状态
	@CacheEvict(allEntries = true)
	@Override
	public int updateProcessMaterialDesignState(ProcessDesign processDesign) {
		String product_id = processDesign.getProduct_id();
		Integer id = processDesign.getId();
		int i = materialDesignService.checkMaterialUseState(product_id);
		String message = "";
		if (i > 0) {// 说明还有物料没有使用完
			message = "你还有物料未使用!";
		} else {
			//获取人工成本
			Float totalManhoirCostPriceSum = processDetailService.processTotalManhoirCostPriceSum(processDesign.getId());
			//获取物料成本
			Float totalMaterialCostPriceSum = materialDesignDetailService.totalMaterialCostPriceSum(product_id);
			//并将他们设置到工序单中去
			LambdaUpdateWrapper<ProcessDesign> updateWrapper = Wrappers.lambdaUpdate();
			// 设置状态为审核中
			Integer material_tag = ProductConstant.UNREVIEWED_PROCESS_MATERIAL_DESIGN;
			updateWrapper.set(ProcessDesign::getManhour_cost_price_sum, totalManhoirCostPriceSum);
			updateWrapper.set(ProcessDesign::getMaterial_cost_price_sum, totalMaterialCostPriceSum);
			updateWrapper.set(ProcessDesign::getProcess_material_tag, material_tag);
			updateWrapper.eq(ProcessDesign::getId, id);
			i = this.baseMapper.update(null, updateWrapper);
			if (i > 0) {
				return i;
			}
			message = "工序物料设置状态失败!";
		}
		throw new RuntimeException(message);

	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProcessDesign> getCheckerProcessMaterial() {
		// 工序设计单为通过状态
		Integer check_tag = ProductConstant.PROCESS_DESIGN_PASS;
		// 工序物料设计为已经设计 但是还在审核中的状态
		Integer process_material_tag = ProductConstant.UNREVIEWED_PROCESS_MATERIAL_DESIGN;
		return this.baseMapper.getProcessList(check_tag, process_material_tag);
	}
	//工序物料审核通过
	//我还需要计算商品的实际价格
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int processMaterialPass(ProcessDesign processDesign) {
		
		String product_id = processDesign.getProduct_id();
		//设置工序总表下的 process_material_tag为1工序已经设计状态
		Integer process_material_tag = ProductConstant.ACCOMPLISH_PROCESS_MATERIAL_DESIGN;
		//设置产品表下的工序设计状态 为  已经设计了 
		Integer process_tag = ProductConstant.ACCOMPLISH_MANING_PROCESS;
		LambdaUpdateWrapper<ProcessDesign> updateWrapper=Wrappers.lambdaUpdate();
		//获取工时总成本和物料总成本
		float total_real=processDesign.getManhour_cost_price_sum()+processDesign.getMaterial_cost_price_sum();
		//就是产品的实际成本价
		float real_cost_price=total_real;
		updateWrapper.set(ProcessDesign::getProcess_material_tag,process_material_tag);
		updateWrapper.eq(ProcessDesign::getId, processDesign.getId());
		String message="工序物料状态设置失败!";
		int i = this.baseMapper.update(null, updateWrapper);
		if(i>0) {
			//修改产品表下的工序设计状态为已经设计 并且设置实际成本价
			i = productService.updateProcessTagAndRealCostPrice(product_id, process_tag,real_cost_price);
			//i = productService.updateProcess_tag(product_id, process_tag);
			if(i>0) {
				return i;
			}
			message="产品工序状态设置失败!";
		}
		throw new RuntimeException(message);
	}
	//工序物料审核未通过
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int processMaterialReject(ProcessDesign processDesign) {
		String product_id = processDesign.getProcess_id();
		//设置工序总表下的 process_material_tag为2工序物料状态为驳回
		Integer process_material_tag = ProductConstant.REJECT_PROCESS_MATERIAL_DESIGN;
		//设置产品表下的工序设计状态 为  0 审核中或者已经驳回 
		Integer process_tag = ProductConstant.MAKING_PROCESS_CHECKER;
		LambdaUpdateWrapper<ProcessDesign> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.set(ProcessDesign::getProcess_material_tag,process_material_tag);
		updateWrapper.set(ProcessDesign::getCheck_suggestion,processDesign.getCheck_suggestion());
		updateWrapper.eq(ProcessDesign::getId, processDesign.getId());
		String message="工序物料状态设置失败!";
		int i = this.baseMapper.update(null, updateWrapper);
		if(i>=0) { 
			//这个设置只是为了保险起见  因为一般情况下  如果工序物料是审核状态
			//的话或者是驳回状态  产品表下的工序状态因该是0
			i = productService.updateProcess_tag(product_id, process_tag);
			if(i>=0) {
				return i;
			}
			System.out.println("---------------------------------------------------------------"+i);
			message="产品工序状态设置失败!";
		}
		throw new RuntimeException(message);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ProcessDesign> processMaterialSearch(Params params) {
		return PageHelper.startPage(params.getPageNum(),params.getPageSize())
					.doSelectPageInfo(()->
							this.baseMapper.processMaterialSearch(params)
					);
	}
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int alterProcessMaterialDesign(ProcessDesign processDesign) {
		String product_id = processDesign.getProduct_id();
		int i = materialDesignService.checkMaterialUseState(product_id);
		String message="";
		if(i>0) {//如果i>0说明还有未使用的物料
			message="还有未使用的物料!";
			throw new RuntimeException(message);
		}
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		Integer id=processDesign.getId();
		//设置工序单的物料设计状态为  审核中
		Integer process_material_tag=ProductConstant.UNREVIEWED_PROCESS_MATERIAL_DESIGN;
		String changer=user.getUid();
		Date currentTime=new Date();
		//获取人工成本
		Float totalManhoirCostPriceSum = processDetailService.processTotalManhoirCostPriceSum(processDesign.getId());
		//获取物料成本
		Float totalMaterialCostPriceSum = materialDesignDetailService.totalMaterialCostPriceSum(product_id);
		//并将他们设置到工序单中去
		LambdaUpdateWrapper<ProcessDesign> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.set(ProcessDesign::getProcess_material_tag,process_material_tag);
		updateWrapper.set(ProcessDesign::getManhour_cost_price_sum, totalManhoirCostPriceSum);
		updateWrapper.set(ProcessDesign::getMaterial_cost_price_sum, totalMaterialCostPriceSum);
		updateWrapper.set(ProcessDesign::getChanger,changer);
		updateWrapper.set(ProcessDesign::getChange_time,currentTime);
		updateWrapper.eq(ProcessDesign::getId,id);
		i = this.baseMapper.update(null, updateWrapper);
		message="工序物料状态设置失败!";
		if(i>0) {
			//修改的时候需要用到  因为修改的时候
			//设计总表下的字段process_material=1为审核通过
			//这样 产品表下的 process_tag=1 也会为已经设计
			//所以可能需要修改
			//设置为产品表下的process_tag状态为审核中或者被驳回
			
			Integer process_tag=ProductConstant.MAKING_PROCESS_CHECKER;
			productService.updateProcess_tag(product_id, process_tag);
			if(i>=0) {
				return i;
			}
			message="产品工序设计状态失败!";
		}
		throw new RuntimeException(message);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProcessDesign getProcessAndMaterialPrice(String product_id) {
		LambdaQueryWrapper<ProcessDesign> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ProcessDesign::getProduct_id, product_id);
		queryWrapper.select(ProcessDesign::getManhour_cost_price_sum,ProcessDesign::getMaterial_cost_price_sum);
		return this.baseMapper.selectOne(queryWrapper);
	}
	//在新增派工单的时候使用 
		//获取这个产品的工序以及工序详细单
		//因为在新增生产工序表的时候需要用到 工序总表的编号
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProcessDesign getProcessDesignByProductId(String product_id) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProcessDesignByProductId(product_id);
	}

}
