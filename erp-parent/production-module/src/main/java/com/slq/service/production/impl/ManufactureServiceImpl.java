package com.slq.service.production.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.slq.common.constant.ProductionConstant;
import com.slq.mapper.production.ManufactureMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProcessDesign;
import com.slq.pojo.production.ProcessDetail;
import com.slq.pojo.production.ProcessMaterialDetail;
import com.slq.pojo.production.ProductionPlanDetail;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.service.production.IManufactureService;
import com.slq.service.production.IProcessDesignService;
import com.slq.service.production.IProcessMaterialDetailService;
import com.slq.service.production.IProductionPlanDetailService;
import com.slq.service.production.IProductionProcessMaterialService;
import com.slq.service.production.IProductionProcessService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;
<<<<<<< HEAD
import com.slq.util.ManufactureChart;
=======
>>>>>>> warehouse
import com.slq.util.TimeLine;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
<<<<<<< HEAD
public class ManufactureServiceImpl extends ServiceImpl<ManufactureMapper, ManufactureSum>
		implements IManufactureService {
=======
public class ManufactureServiceImpl extends ServiceImpl<ManufactureMapper, ManufactureSum> implements IManufactureService{
>>>>>>> warehouse
	@Autowired
	private IProcessDesignService processDesignService;
	@Autowired
	private IProductionPlanDetailService productionPlanDetailService;
	@Autowired
<<<<<<< HEAD
	private IProductionProcessService productionProcessService;
=======
	private IProductionProcessService productionProcessService ;
>>>>>>> warehouse
	@Autowired
	private IProcessMaterialDetailService processMaterialDetailService;
	@Autowired
	private IProductionProcessMaterialService productionProcessMaterialService;
<<<<<<< HEAD

=======
>>>>>>> warehouse
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ManufactureSum getInsert(String product_id, Integer[] productionPlanDetailIds) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return this.baseMapper.getInsert(product_id, productionPlanDetailIds);
	}

=======
		return this.baseMapper.getInsert(product_id,productionPlanDetailIds);
	}
>>>>>>> warehouse
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int insert(ManufactureSum manufactureSum) {
<<<<<<< HEAD
		String product_id = manufactureSum.getProduct_id();
		// 不需要获取工时成本和物料成本了 因为我修改之前时获取的工序详细表
		// 修改后获取的时工序总表 所以修改后包含了工时成本和物料成本
		// ProcessDesign
		// processDesign=processDesignService.getProcessAndMaterialPrice(product_id);
		ProcessDesign processDesign = manufactureSum.getProcessDesign();
		// 设置派工单的状态为审核中
		Integer check_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_CHECK_TAG;
		// 设置派工单的生产过程 为未生产
		Integer manufacture_procedure_tag = ProductionConstant.NOT_MANUFACTURE_PROCEDURE_TAG;
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		// 获取编号
		String manufacture_id = IDGenerateUtil.getID(ProductionConstant.MANUFACTURE_PREFIX);
		// 总设计生产量
		Integer totalAmount = manufactureSum.getAmount();
		LambdaUpdateWrapper<ManufactureSum> updateWrapper = Wrappers.lambdaUpdate();
=======
		String product_id=manufactureSum.getProduct_id();
		//不需要获取工时成本和物料成本了 因为我修改之前时获取的工序详细表
		//修改后获取的时工序总表  所以修改后包含了工时成本和物料成本
		//ProcessDesign processDesign=processDesignService.getProcessAndMaterialPrice(product_id);
		ProcessDesign processDesign=manufactureSum.getProcessDesign();
		//设置派工单的状态为审核中
		Integer check_tag=ProductionConstant.UNREVIEWED_MANUFACTURE_CHECK_TAG;
		//设置派工单的生产过程 为未生产
		Integer manufacture_procedure_tag=ProductionConstant.NOT_MANUFACTURE_PROCEDURE_TAG;
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		//获取编号
		String manufacture_id = IDGenerateUtil.getID(ProductionConstant.MANUFACTURE_PREFIX);
		//总设计生产量
		Integer totalAmount=manufactureSum.getAmount();
		LambdaUpdateWrapper<ManufactureSum> updateWrapper=Wrappers.lambdaUpdate();
>>>>>>> warehouse
//		updateWrapper.set(ManufactureSum::getManufacture_id, manufacture_id);
//		updateWrapper.set(ManufactureSum::getCheck_tag, check_tag);
//		updateWrapper.set(ManufactureSum::getManufacture_procedure_tag, manufacture_procedure_tag);
//		updateWrapper.set(ManufactureSum::getAmount, manufactureSum.getAmount());
//		updateWrapper.set(ManufactureSum::getRegister, user.getUid());
//		updateWrapper.set(ManufactureSum::getRegister_time, new Date());
//		updateWrapper.set(ManufactureSum::getDesigner, manufactureSum.getDesigner());
//		updateWrapper.set(ManufactureSum::getRemark, manufactureSum.getRemark());
//		updateWrapper.set(ManufactureSum::getMaterial_cost_price_sum, processDesign.getMaterial_cost_price_sum());
//		updateWrapper.set(ManufactureSum::getLabour_cost_price_sum, processDesign.getManhour_cost_price_sum());
		manufactureSum.setManufacture_id(manufacture_id);
		manufactureSum.setCheck_tag(check_tag);
		manufactureSum.setManufacture_procedure_tag(manufacture_procedure_tag);
		manufactureSum.setRegister(user.getUid());
		manufactureSum.setRegister_time(new Date());
<<<<<<< HEAD
		manufactureSum.setMaterial_cost_price_sum(processDesign.getMaterial_cost_price_sum() * totalAmount);
		manufactureSum.setLabour_cost_price_sum(processDesign.getManhour_cost_price_sum() * totalAmount);
		int i = this.baseMapper.insert(manufactureSum);
		Integer id = manufactureSum.getId();
		String message = "新增派工单失败!";
		if (id != null && id > 0) {
			// 修改生产计划详细单的 派工(制定)单状态为已制定
			List<ProductionPlanDetail> productionPlanDetails = manufactureSum.getProductionPlanDetails();
			Integer manufacture_tag = ProductionConstant.HAVE_MANUFACTURE_TAG;
			i = productionPlanDetailService.batchUpdateManufacture(manufactureSum.getId(),
					manufactureSum.getProductionPlanDetails(), manufacture_tag);

			List<ProcessDetail> processDetails = processDesign.getProcessDetails();
			// 生产工序表
			List<ProductionProcess> productionProcessList = new ArrayList<ProductionProcess>();
			// 工序完成标志 未开始0
			Integer process_finish_tag = ProductionConstant.NOT_START_PROCESS_FINISH_TAG;
			// 工序交接标志 未交接0
			Integer process_transfer_tag = ProductionConstant.NOT_TRANSFER_PROCESS_TRANSFER_TAG;
			// 设计工时成本
			Float subtotal = 0f;
			// 设计工序物料成本
			Float material_subtotal = 0f;
			// 生产工序物料
			List<ProductionProcessMaterial> productionProcessMaterials;
			// 工序物料
			List<ProcessMaterialDetail> processMaterialDetails = null;
			ProductionProcessMaterial productionProcessMaterial;
			for (ProcessDetail processDetail : processDetails) {
				productionProcessMaterials = new ArrayList<ProductionProcessMaterial>();
				// 获取工序对应的物料详细信息 通过工序id
				processMaterialDetails = processMaterialDetailService
						.getProcessMaterialDesignByParentId1(processDetail.getId());
				for (ProcessMaterialDetail processMaterialDetail : processMaterialDetails) {
					// 通过这个工序物料新增生产工序物料
					productionProcessMaterial = new ProductionProcessMaterial(processMaterialDetail.getProduct_id(),
							processMaterialDetail.getProduct_name(), processMaterialDetail.getCost_price(),
							processMaterialDetail.getUseAmount() * totalAmount,
							processMaterialDetail.getCost_price() * processMaterialDetail.getUseAmount() * totalAmount);
					//设置单个产品这一到工序所需要的物料数量 很重要
					productionProcessMaterial.setSingle_material_design_amount(processMaterialDetail.getUseAmount());
					//设置从库存领取的物料数量为 设计数量
					//因为还没有和姚的整合  后续需要删除这个
					productionProcessMaterial.setRenew_amount(processMaterialDetail.getUseAmount() * totalAmount);
					productionProcessMaterials.add(productionProcessMaterial);
					// 这一个生产工序物料设计的物料所用价格:物料价格*物料数量
					// processMaterialDetail.getCost_price()*processMaterialDetail.getUseAmount()
					// 获取一个工序的物料总设计价格
					material_subtotal += processMaterialDetail.getUseAmount() * processMaterialDetail.getCost_price();
				}
				material_subtotal = material_subtotal * totalAmount;
				// 获取设计工时成本
				subtotal = processDetail.getCost_price() * processDetail.getLabour_hour_amount() * totalAmount;
				// totalAmount工序投产的数量 也就是需要生产的产品数量
				ProductionProcess productionProcess = new ProductionProcess(processDetail.getDetails_number(),
						processDetail.getProcess_id(), processDetail.getProcess().getProcessName(),
						processDetail.getLabour_hour_amount() * totalAmount, subtotal, material_subtotal, totalAmount,
						processDetail.getCost_price(), process_finish_tag, process_transfer_tag);
				// 工时单位
				productionProcess.setTime_unit(processDetail.getTime_unit());
				// 将生产工序物料设计详细 添加到对应的生产工序设计中去
				// 这样对后面的新增好写很多
				productionProcess.setProductionProcessMaterials(productionProcessMaterials);
				productionProcessList.add(productionProcess);
				material_subtotal = 0f;
			}
			System.out.println("productionProcessList:" + JSON.toJSONString(productionProcessList));
			// 新增 productionProcess生产工序
			// id为派工单(制造单)编号
			i = productionProcessService.insert(id, productionProcessList);
			message = "新增生产工序单失败!";
			if (i > 0) {
=======
		manufactureSum.setMaterial_cost_price_sum( processDesign.getMaterial_cost_price_sum()*totalAmount);
		manufactureSum.setLabour_cost_price_sum(processDesign.getManhour_cost_price_sum()*totalAmount);
		int i = this.baseMapper.insert(manufactureSum);
		Integer id=manufactureSum.getId();
		String message="新增派工单失败!";
		if(id!=null&&id>0) {
			//修改生产计划详细单的 派工(制定)单状态为已制定
			List<ProductionPlanDetail> productionPlanDetails=manufactureSum.getProductionPlanDetails();
			Integer manufacture_tag = ProductionConstant.HAVE_MANUFACTURE_TAG;
			i = productionPlanDetailService.batchUpdateManufacture(manufactureSum.getId(),manufactureSum.getProductionPlanDetails(),manufacture_tag);
			
			List<ProcessDetail> processDetails=processDesign.getProcessDetails();
			//生产工序表
			List<ProductionProcess> productionProcessList=new ArrayList<ProductionProcess>();
			//工序完成标志 未开始0
			Integer process_finish_tag=ProductionConstant.NOT_START_PROCESS_FINISH_TAG;
			//工序交接标志 未交接0
			Integer process_transfer_tag=ProductionConstant.NOT_TRANSFER_PROCESS_TRANSFER_TAG;
			//设计工时成本
			Float subtotal=0f;
			//设计工序物料成本
			Float material_subtotal=0f;
			//生产工序物料
			List<ProductionProcessMaterial> productionProcessMaterials;
			//工序物料
			List<ProcessMaterialDetail> processMaterialDetails=null;
			ProductionProcessMaterial productionProcessMaterial;
			for (ProcessDetail processDetail : processDetails	) {
				productionProcessMaterials=new ArrayList<ProductionProcessMaterial>();
				//获取工序对应的物料详细信息 通过工序id
				processMaterialDetails=processMaterialDetailService.getProcessMaterialDesignByParentId1(processDetail.getId());
				for (ProcessMaterialDetail processMaterialDetail : processMaterialDetails) {
					//通过这个工序物料新增生产工序物料
					productionProcessMaterial=new ProductionProcessMaterial
							(processMaterialDetail.getProduct_id(), processMaterialDetail.getProduct_name(),
									processMaterialDetail.getCost_price(), processMaterialDetail.getUseAmount()*totalAmount,
									processMaterialDetail.getCost_price()*processMaterialDetail.getUseAmount()*totalAmount);
					
					productionProcessMaterials.add(productionProcessMaterial);
							//这一个生产工序物料设计的物料所用价格:物料价格*物料数量
							//processMaterialDetail.getCost_price()*processMaterialDetail.getUseAmount()
					//获取一个工序的物料总设计价格
					material_subtotal+=processMaterialDetail.getUseAmount()*processMaterialDetail.getCost_price();
				}  
				material_subtotal=material_subtotal*totalAmount;
				//获取设计工时成本
				subtotal=processDetail.getCost_price()*processDetail.getLabour_hour_amount()*totalAmount;
				//totalAmount工序投产的数量  也就是需要生产的产品数量
				ProductionProcess productionProcess=new ProductionProcess
						(processDetail.getDetails_number(), processDetail.getProcess_id(),
								processDetail.getProcess().getProcessName(), 
								processDetail.getLabour_hour_amount()*totalAmount, 
								subtotal, material_subtotal,totalAmount, processDetail.getCost_price(), process_finish_tag, process_transfer_tag);
				//工时单位
				productionProcess.setTime_unit(processDetail.getTime_unit());
				//将生产工序物料设计详细  添加到对应的生产工序设计中去
				//这样对后面的新增好写很多
				productionProcess.setProductionProcessMaterials(productionProcessMaterials);
				productionProcessList.add(productionProcess);
				material_subtotal=0f;
			}
			System.out.println("productionProcessList:"+JSON.toJSONString(productionProcessList));
			//新增  productionProcess生产工序
			//id为派工单(制造单)编号 
			i=productionProcessService.insert(id, productionProcessList);
			message="新增生产工序单失败!";
			if(i>0) {
>>>>>>> warehouse
				System.out.println("生产工序 productionProcess 的id集合:");
				for (ProductionProcess productionProcess : productionProcessList) {
					System.out.println(productionProcess.getId());
				}
<<<<<<< HEAD
				// 生产工序表下有生产工序详细表
				// 新增生产工序详细
				i = productionProcessMaterialService.batchInsert(productionProcessList);
				message = "新增生产工序物料详细失败!";
				if (i > 0) {
					return id;
				}
			}
			message = "生产计划详细 状态设置失败!";
		}
		throw new RuntimeException(message);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureSum> getCheckerManufacture() {
		LambdaQueryWrapper<ManufactureSum> queryWrapper = Wrappers.lambdaQuery();
		// 设置派工单的状态为审核中
		Integer check_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_CHECK_TAG;
		queryWrapper.eq(ManufactureSum::getCheck_tag, check_tag);
		// return this.baseMapper.getCheckerManufacture();
		return this.baseMapper.selectList(queryWrapper);
	}

	// 审核通过的时候 需要加入出库单申请单
	// 因为还没有整合 所有就没有搞了先
=======
				//生产工序表下有生产工序详细表
				//新增生产工序详细
				i = productionProcessMaterialService.batchInsert(productionProcessList);
				message="新增生产工序物料详细失败!";
				if(i>0) {
					return id;
				}
			}
			message="生产计划详细 状态设置失败!";
		}
		throw new RuntimeException(message);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureSum> getCheckerManufacture() {
		LambdaQueryWrapper<ManufactureSum> queryWrapper=Wrappers.lambdaQuery();
		//设置派工单的状态为审核中
		Integer check_tag=ProductionConstant.UNREVIEWED_MANUFACTURE_CHECK_TAG;
		queryWrapper.eq(ManufactureSum::getCheck_tag,check_tag);
		//return this.baseMapper.getCheckerManufacture();
		return this.baseMapper.selectList(queryWrapper);
	}
	//审核通过的时候  需要加入出库单申请单
	//因为还没有整合  所有就没有搞了先
>>>>>>> warehouse
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int pass(ManufactureSum manufactureSum) {
<<<<<<< HEAD
		Integer check_tag = ProductionConstant.PASS_MANUFACTURE_CHECK_TAG;
		Integer id=manufactureSum.getId();
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		LambdaUpdateWrapper<ManufactureSum> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.eq(ManufactureSum::getId, id);
		updateWrapper.set(ManufactureSum::getCheck_tag, check_tag);
		updateWrapper.set(ManufactureSum::getChecker, user.getUid());
		updateWrapper.set(ManufactureSum::getCheck_time, new Date());
		int i = this.baseMapper.update(null, updateWrapper);
		if(i>0) {//还需要发送一条领取物料语句
			//根据生产主表的id查询它的所有工序再根据所有工序查询所有的物料
			List<ProductionProcessMaterial> productionProcessMaterials = productionProcessMaterialService.getProductionProcessMaterialByManufactureId(id);
			//进行领取物料
		}
		return i;
	}

=======
		Integer check_tag=ProductionConstant.PASS_MANUFACTURE_CHECK_TAG;
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		LambdaUpdateWrapper<ManufactureSum> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(ManufactureSum::getId, manufactureSum.getId());
		updateWrapper.set(ManufactureSum::getCheck_tag, check_tag);
		updateWrapper.set(ManufactureSum::getChecker, user.getUid());
		updateWrapper.set(ManufactureSum::getCheck_time, new Date());
		return this.baseMapper.update(null, updateWrapper);
	}
>>>>>>> warehouse
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int reject(ManufactureSum manufactureSum) {
<<<<<<< HEAD
		Integer check_tag = ProductionConstant.REJECT_MANUFACTURE_CHECK_TAG;
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		LambdaUpdateWrapper<ManufactureSum> updateWrapper = Wrappers.lambdaUpdate();
=======
		Integer check_tag=ProductionConstant.REJECT_MANUFACTURE_CHECK_TAG;
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		LambdaUpdateWrapper<ManufactureSum> updateWrapper=Wrappers.lambdaUpdate();
>>>>>>> warehouse
		updateWrapper.eq(ManufactureSum::getId, manufactureSum.getId());
		updateWrapper.set(ManufactureSum::getCheck_tag, check_tag);
		updateWrapper.set(ManufactureSum::getChecker, user.getUid());
		updateWrapper.set(ManufactureSum::getCheck_time, new Date());
		updateWrapper.set(ManufactureSum::getCheck_suggestion, manufactureSum.getCheck_suggestion());
		return this.baseMapper.update(null, updateWrapper);
	}
<<<<<<< HEAD

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ManufactureSum> searchManufactureSum(Params params) {

		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
				.doSelectPageInfo(() -> this.baseMapper.searchManufactureSum(params));

	}

	// 在修改的时候获取
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ManufactureSum getManufactureById(Integer id) {

		return this.baseMapper.getManufactureById(id);
	}

	@CacheEvict(allEntries = true)
	@Override
	public int update(ManufactureSum manufacture) {
		LambdaUpdateWrapper<ManufactureSum> updateWrapper = Wrappers.lambdaUpdate();
		// 设置为:等待审核
		Integer check_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_CHECK_TAG;
=======
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ManufactureSum> searchManufactureSum(Params params) {
		
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
					.doSelectPageInfo(()->
						this.baseMapper.searchManufactureSum(params)
							);
		
	}
	//在修改的时候获取
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ManufactureSum getManufactureById(Integer id) {
		
		return this.baseMapper.getManufactureById(id);
	}
	@CacheEvict(allEntries = true)
	@Override
	public int update(ManufactureSum manufacture) {
		LambdaUpdateWrapper<ManufactureSum> updateWrapper=Wrappers.lambdaUpdate();
		//设置为:等待审核
		Integer check_tag=ProductionConstant.UNREVIEWED_MANUFACTURE_CHECK_TAG;
>>>>>>> warehouse
		updateWrapper.set(ManufactureSum::getCheck_tag, check_tag);
		updateWrapper.set(ManufactureSum::getDesigner, manufacture.getDesigner());
		updateWrapper.set(ManufactureSum::getRemark, manufacture.getRemark());
		updateWrapper.eq(ManufactureSum::getId, manufacture.getId());
		return this.baseMapper.update(null, updateWrapper);
	}
<<<<<<< HEAD

	// 查询生产主表的生产工序状态 为生产等待状态-1
	// 生产中 0 生产审核中 2
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureSum> getMeetInternalProductions() {
		// 产品派工单 审核状态为通过 1
		Integer check_tag = ProductionConstant.PASS_MANUFACTURE_CHECK_TAG;
		// 产品生产过程标志为 -1 未登记 和 0 生产中 两个状态就可以进行登记
		List<Integer> manufacture_procedure_tags = new ArrayList<Integer>();
		// 还从未登记过的
		Integer not_manufacture_procedure_tag = ProductionConstant.NOT_MANUFACTURE_PROCEDURE_TAG;
		// 审核中的状态 也可以是还没有登记完成
		Integer unreviewed_manufacture_procedure_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_PROCEDURE_TAG;
		// 生产中
		Integer execute_manufacture_procedure_tag = ProductionConstant.EXECUTE_MANUFACTURE_PROCEDURE_TAG;
		manufacture_procedure_tags.add(not_manufacture_procedure_tag);
		manufacture_procedure_tags.add(execute_manufacture_procedure_tag);
		manufacture_procedure_tags.add(unreviewed_manufacture_procedure_tag);
		LambdaQueryWrapper<ManufactureSum> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(ManufactureSum::getCheck_tag, check_tag);
		queryWrapper.in(ManufactureSum::getManufacture_procedure_tag, manufacture_procedure_tags);
		queryWrapper.orderByDesc(ManufactureSum::getId);
		return this.baseMapper.selectList(queryWrapper);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureSum> getMeetCheckerInternalProductions() {
		LambdaQueryWrapper<ManufactureSum> queryWrapper = Wrappers.lambdaQuery();
		// 产品派工单 审核状态为通过 1
		Integer check_tag = ProductionConstant.PASS_MANUFACTURE_CHECK_TAG;
		// 审核中的状态 也可以是还没有登记完成
		Integer unreviewed_manufacture_procedure_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_PROCEDURE_TAG;
		queryWrapper.eq(ManufactureSum::getCheck_tag, check_tag);
		queryWrapper.eq(ManufactureSum::getManufacture_procedure_tag, unreviewed_manufacture_procedure_tag);
		queryWrapper.orderByDesc(ManufactureSum::getId);
		return this.baseMapper.selectList(queryWrapper);
	}

=======
	//查询生产主表的生产工序状态 为生产等待状态-1
	//生产中 0 生产审核中 2
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureSum> getMeetInternalProductions() {
		//产品派工单  审核状态为通过 1
		Integer check_tag=ProductionConstant.PASS_MANUFACTURE_CHECK_TAG;
		//产品生产过程标志为 -1 未登记 和 0 生产中 两个状态就可以进行登记
		List<Integer> manufacture_procedure_tags=new ArrayList<Integer>();
		//还从未登记过的
		Integer not_manufacture_procedure_tag=ProductionConstant.NOT_MANUFACTURE_PROCEDURE_TAG;
		//审核中的状态  也可以是还没有登记完成
		Integer unreviewed_manufacture_procedure_tag=ProductionConstant.UNREVIEWED_MANUFACTURE_PROCEDURE_TAG;
		//生产中
		Integer execute_manufacture_procedure_tag =ProductionConstant.EXECUTE_MANUFACTURE_PROCEDURE_TAG;
		manufacture_procedure_tags.add(not_manufacture_procedure_tag);
		manufacture_procedure_tags.add(execute_manufacture_procedure_tag);
		manufacture_procedure_tags.add(unreviewed_manufacture_procedure_tag);
		LambdaQueryWrapper<ManufactureSum> queryWrapper=Wrappers.lambdaQuery();
		queryWrapper.eq(ManufactureSum::getCheck_tag,check_tag);
		queryWrapper.in(ManufactureSum::getManufacture_procedure_tag,manufacture_procedure_tags);
		queryWrapper.orderByDesc(ManufactureSum::getId);
		return this.baseMapper.selectList(queryWrapper);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureSum> getMeetCheckerInternalProductions() {
		LambdaQueryWrapper<ManufactureSum> queryWrapper=Wrappers.lambdaQuery();
		//产品派工单  审核状态为通过 1
		Integer check_tag=ProductionConstant.PASS_MANUFACTURE_CHECK_TAG;
		//审核中的状态  也可以是还没有登记完成
		Integer unreviewed_manufacture_procedure_tag=ProductionConstant.UNREVIEWED_MANUFACTURE_PROCEDURE_TAG;
		queryWrapper.eq(ManufactureSum::getCheck_tag,check_tag);
		queryWrapper.eq(ManufactureSum::getManufacture_procedure_tag,unreviewed_manufacture_procedure_tag);
		queryWrapper.orderByDesc(ManufactureSum::getId);
		return this.baseMapper.selectList(queryWrapper);
	}
>>>>>>> warehouse
	//
	@CacheEvict
	@Transactional
	@Override
<<<<<<< HEAD
	public int setManufacture_procedure_tag(Integer id, Integer manufacture_procedure_tag) {
		LambdaUpdateWrapper<ManufactureSum> updateWrapper = Wrappers.lambdaUpdate();
=======
	public int setManufacture_procedure_tag(Integer id,Integer manufacture_procedure_tag) {
		LambdaUpdateWrapper<ManufactureSum> updateWrapper=Wrappers.lambdaUpdate();
>>>>>>> warehouse
		updateWrapper.set(ManufactureSum::getManufacture_procedure_tag, manufacture_procedure_tag);
		updateWrapper.eq(ManufactureSum::getId, id);
		return this.baseMapper.update(null, updateWrapper);
	}
<<<<<<< HEAD

	// 获取通过生产总表的id获取该总表下的
	// 工序集合的实际总物料 和 实际工时总价格
	@Override
	public ManufactureSum getManufactureReal(Integer id) {

		return this.baseMapper.getManufactureReal(id);
	}

	// 修改当前生产主表的实际总物料价格
	// 实际总工时数 实际总合格数量
	// 和生产状态->已完工
=======
	//获取通过生产总表的id获取该总表下的
	//工序集合的实际总物料 和 实际工时总价格
	@Override
	public ManufactureSum getManufactureReal(Integer id) {
		
		return this.baseMapper.getManufactureReal(id);
	}
	//修改当前生产主表的实际总物料价格
	//实际总工时数 实际总合格数量
	//和生产状态->已完工
>>>>>>> warehouse
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int updateReal(ManufactureSum manufacture) {
<<<<<<< HEAD
		LambdaUpdateWrapper<ManufactureSum> updateWrapper = Wrappers.lambdaUpdate();
		// 生产状态->已完工
		Integer manufacture_procedure_tag = ProductionConstant.FINISH_MANUFACTURE_PROCEDURE_TAG;
		updateWrapper.set(ManufactureSum::getReal_labour_cost_price_sum, manufacture.getReal_labour_cost_price_sum());
		updateWrapper.set(ManufactureSum::getReal_material_cost_price_sum,
				manufacture.getReal_material_cost_price_sum());
=======
		LambdaUpdateWrapper<ManufactureSum> updateWrapper=Wrappers.lambdaUpdate();
		//生产状态->已完工
		Integer manufacture_procedure_tag=ProductionConstant.FINISH_MANUFACTURE_PROCEDURE_TAG;
		updateWrapper.set(ManufactureSum::getReal_labour_cost_price_sum, manufacture.getReal_labour_cost_price_sum());
		updateWrapper.set(ManufactureSum::getReal_material_cost_price_sum, manufacture.getReal_material_cost_price_sum());
>>>>>>> warehouse
		updateWrapper.set(ManufactureSum::getTested_amount, manufacture.getTested_amount());
		updateWrapper.set(ManufactureSum::getManufacture_procedure_tag, manufacture_procedure_tag);
		updateWrapper.eq(ManufactureSum::getId, manufacture.getId());
		return this.baseMapper.update(null, updateWrapper);
	}
<<<<<<< HEAD

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<TimeLine> getProductionProgressByManufactureId(Integer id) {

		return this.baseMapper.getProductionProgressByManufactureId(id);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ManufactureChart> getProductProductionFinishChartResult() {

		return this.baseMapper.getProductProductionFinishChartResult();
	}

	// 获取可以登记物料报损报溢的派工单
	@Override
	public List<ManufactureSum> getMeetProfitAndLossManufactures() {
		LambdaQueryWrapper<ManufactureSum> queryWrapper = Wrappers.lambdaQuery();
		// 生产过程工序为等待或者生产中
		List<Integer> manufacture_procedure_tags = new ArrayList<Integer>();
		manufacture_procedure_tags.add(ProductionConstant.NOT_MANUFACTURE_PROCEDURE_TAG);

		manufacture_procedure_tags.add(ProductionConstant.EXECUTE_MANUFACTURE_PROCEDURE_TAG);
		queryWrapper.in(ManufactureSum::getManufacture_procedure_tag, manufacture_procedure_tags);
		return this.baseMapper.selectList(queryWrapper);
	}

=======
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<TimeLine> getProductionProgressByManufactureId(Integer id) {
		
		return this.baseMapper.getProductionProgressByManufactureId(id);
	}

	
>>>>>>> warehouse
}
