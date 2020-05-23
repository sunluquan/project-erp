package com.slq.service.production.impl;

import java.util.ArrayList;
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
import com.slq.common.constant.ProductionConstant;
import com.slq.mapper.production.ProductionProcessCourseMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessCourse;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.service.production.IManufactureService;
import com.slq.service.production.IProductionProcessCourseService;
import com.slq.service.production.IProductionProcessMaterialCourseService;
import com.slq.service.production.IProductionProcessMaterialService;
import com.slq.service.production.IProductionProcessService;
import com.slq.util.ActiveUser;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductionProcessCourseServiceImpl extends
		ServiceImpl<ProductionProcessCourseMapper, ProductionProcessCourse> implements IProductionProcessCourseService {

	@Autowired
	private IProductionProcessService productionProcessService;

	@Autowired
	private IProductionProcessMaterialCourseService productionProcessMaterialCourseService;
	@Autowired
	private IProductionProcessMaterialService productionProcessMaterialService;
	@Autowired
	private IManufactureService manufactureService;
	@Autowired
	private IProductionProcessCourseService productionProcessCourseService;

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int register(ManufactureSum manufacture) {
		// 生产工序过程表
		ProductionProcessCourse productionProcessCourse = manufacture.getProductionProcessCourse();
		// 生产工序表
		ProductionProcess productionProcess = manufacture.getProductionProcess1();
		//生产工序物料过程集合
		List<ProductionProcessMaterial> productionProcessMaterials = productionProcess
				.getProductionProcessMaterials();
		Integer manufacture_id = manufacture.getId();
		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		// 未完成的审核状态
		Integer process_finish_tag = productionProcess.getProcess_finish_tag();//
		//只有当这道工序的没有物料的时候才会根据前端传过来的状态
		//如何审核为完成时的审核的话
		if (productionProcessMaterials!=null&&!productionProcessMaterials.isEmpty()&&process_finish_tag != null && process_finish_tag == ProductionConstant.FINISH_CHECK_PROCESS_FINISH_TAG) {
				
		} else {
			// 将它设置为未完成的审核状态
			process_finish_tag = ProductionConstant.UNFINISHED_CHECK_PROCESS_FINISH_TAG;
		}
		// 修改当前 登记的生产工序 的工序完成状态 还需要增加它的已用工时为 本身已用的工时数+当前使用的工时数
//		Float real_labour_hour_amount=productionProcess.getReal_labour_hour_amount();
//		if(real_labour_hour_amount==null)real_labour_hour_amount=0f;
//		real_labour_hour_amount=productionProcessCourse.getLabour_hour_amount()+real_labour_hour_amount;
		// int
		// i=productionProcessService.updateProductionProcess_process_finish_tag(manufacture_id,process_finish_tag,real_labour_hour_amount);
		// 设置生产工序完成的标志
		productionProcess.setProcess_finish_tag(process_finish_tag);
		int i = productionProcessService.update_process_finish_tag(productionProcess);
		Integer manufacture_procedure_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_PROCEDURE_TAG;
		// 设置生产总表的状态为审核状态
		i = manufactureService.setManufacture_procedure_tag(manufacture.getId(), manufacture_procedure_tag);
		// 工时成本小计 当前登记工序的使用的工时数*单位工时数价格
		Float man_hour_total = productionProcessCourse.getLabour_hour_amount() * productionProcess.getCost_price();
		ProductionProcessCourse productionProcessCourse1 = new ProductionProcessCourse(manufacture_id,
				productionProcess.getProcess_id(), productionProcess.getProcess_name(),
				productionProcess.getTime_unit(), productionProcess.getCost_price(), man_hour_total);
		// 负责人
		productionProcessCourse1.setResponsible_person(productionProcessCourse.getResponsible_person());
		// 备注
		productionProcessCourse1.setProcedure_describe(productionProcessCourse.getProcedure_describe());
		productionProcessCourse1.setRegister(user.getUid());
		productionProcessCourse1.setRegister_time(new Date());
		// 设置本次使用的工时数
		productionProcessCourse1
				.setLabour_hour_amount(manufacture.getProductionProcessCourse().getLabour_hour_amount());
		// 设置新增生产工序过程的状态为 审核中
		productionProcessCourse1.setCheck_tag(ProductionConstant.UNREVIEWED_PRODUCTION_PROCESS_COURSE_CHECK_TAG);
		// 新增生产工序过程表
		i = this.baseMapper.insert(productionProcessCourse1);
		Integer productionProcessCourse_id = productionProcessCourse1.getId();
		System.out.println("productionProcessCourse_id:" + productionProcessCourse_id);
		if (productionProcessCourse_id != null && productionProcessCourse_id > 0) {
			
			System.out.println("productionProcessMaterials!=null&&!productionProcessMaterials.isEmpty():"
					+ productionProcessMaterials != null && !productionProcessMaterials.isEmpty());
			// 因为有些工序不需要使用使用物料啥的
			if (productionProcessMaterials != null && !productionProcessMaterials.isEmpty()) {
				// 修改当前生产工序物料的已经使用物料数量为 已使用数量+当前工序使用数量
				// 需要在审核通过的时候在修改它
				// i =
				// productionProcessMaterialService.batchUpdateRealAmount(productionProcess.getId(),productionProcessMaterials);
				// 新增生产工序物料过程表 根据生产工序物料集合 映射到生产工序物料过程表中
				i = productionProcessMaterialCourseService.batchInsert(productionProcessCourse_id,
						productionProcessMaterials);
				if (i > 0) {
					return productionProcessCourse_id;
				}
				throw new RuntimeException();
			}
			return productionProcessCourse_id;
		}
		throw new RuntimeException();
	}

	// 不需要
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProductionProcessCourse getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			String process_id) {
//		LambdaQueryWrapper<ProductionProcessCourse> queryWrapper=Wrappers.lambdaQuery();
//		queryWrapper.eq(ProductionProcessCourse::getParent_id, manufacture_id);
//		queryWrapper.eq(ProductionProcessCourse::getProcess_id, process_id);
//		ProductionProcessCourse productionProcessCourse=this.baseMapper.selectOne(queryWrapper);
//		Integer production_process_course_id=productionProcessCourse.getId();
//		List<ProductionProcessMaterial> productionProcessMaterials = 
//				productionProcessMaterialService.getProductionProcessMaterialAndUseAmount(process_id,production_process_course_id);
//		productionProcessCourse.setProductionProcessMaterialCourses(productionProcessMaterialCourseService.byParentId(id));
//		return productionProcessCourse;
		return null;
	}

//	@Cacheable(keyGenerator = "keyGenerator")
//	@Override
//	public ProductionProcessCourse getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
//			String process_id) {
//		LambdaQueryWrapper<ProductionProcessCourse> queryWrapper=Wrappers.lambdaQuery();
//		queryWrapper.eq(ProductionProcessCourse::getParent_id, manufacture_id);
//		queryWrapper.eq(ProductionProcessCourse::getProcess_id, process_id);
//		ProductionProcessCourse productionProcessCourse=this.baseMapper.selectOne(queryWrapper);
//		Integer production_process_course_id=productionProcessCourse.getId();
//		List<ProductionProcessMaterial> productionProcessMaterials = productionProcessMaterialService.getProductionProcessMaterialAndUseAmount(process_id,production_process_course_id);
//		//productionProcessCourse.setProductionProcessMaterialCourses(productionProcessMaterialCourseService.byParentId(id));
//		return productionProcessCourse;
//	}
	@Override
	public ProductionProcessCourse getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			Integer production_process_id, String process_id) {
		LambdaQueryWrapper<ProductionProcessCourse> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(ProductionProcessCourse::getParent_id, manufacture_id);
		queryWrapper.eq(ProductionProcessCourse::getProcess_id, process_id);
		// 查询审核状态为没有通过得 因为一个工序只可能有一个审核中得状态
		queryWrapper.eq(ProductionProcessCourse::getCheck_tag,
				ProductionConstant.UNREVIEWED_PRODUCTION_PROCESS_COURSE_CHECK_TAG);
		ProductionProcessCourse productionProcessCourse = this.baseMapper.selectOne(queryWrapper);
		Integer production_process_course_id = productionProcessCourse.getId();
		List<ProductionProcessMaterial> productionProcessMaterials = productionProcessMaterialService
				.getProductionProcessMaterialAndUseAmount(production_process_id, production_process_course_id);
		productionProcessCourse.setProductionProcessMaterials(productionProcessMaterials);
		return productionProcessCourse;
	}

	@CacheEvict(allEntries = true)
	@Override
	public boolean checker(ManufactureSum manufacture) {
		ProductionProcess productionProcess = manufacture.getProductionProcess1();
		ProductionProcessCourse productionProcessCourse = manufacture.getProductionProcessCourse();
//		System.out.println(JSON.toJSONString(productionProcessCourse));
//		List<ProductionProcessMaterial> productionProcessMaterials=productionProcessCourse.getProductionProcessMaterials();
//		System.out.println(JSON.toJSONString(productionProcessMaterials));
//		if(true) {
//			return true;
//		}
		List<ProductionProcessMaterial> productionProcessMaterials = productionProcessCourse
				.getProductionProcessMaterials();

		User user = ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		// 生产工序过程的审核状态为通过
		Integer check_tag = ProductionConstant.PASS_PRODUCTION_PROCESS_COURSE_CHECK_TAG;
		LambdaUpdateWrapper<ProductionProcessCourse> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionProcessCourse::getLabour_hour_amount,
				productionProcessCourse.getLabour_hour_amount());
		updateWrapper.set(ProductionProcessCourse::getProcedure_describe,
				productionProcessCourse.getProcedure_describe());
		updateWrapper.set(ProductionProcessCourse::getResponsible_person,
				productionProcessCourse.getResponsible_person());
		updateWrapper.set(ProductionProcessCourse::getSubtotal,
				productionProcessCourse.getLabour_hour_amount() * productionProcessCourse.getCost_price());
		updateWrapper.set(ProductionProcessCourse::getChecker, user.getUid());
		updateWrapper.set(ProductionProcessCourse::getCheck_time, new Date());
		updateWrapper.set(ProductionProcessCourse::getCheck_tag, check_tag);
		updateWrapper.eq(ProductionProcessCourse::getId, productionProcessCourse.getId());
		int i = this.baseMapper.update(null, updateWrapper);
		
		Integer process_finish_tag = null;
		// 总的工时数为已有工时数+新增的工时数
		// Float
		// real_labour_hour_amount=productionProcess.getReal_labour_hour_amount()+productionProcess.getLabour_hour_amount();
		// 修改生产工序的 实际物料和实际工时价格
		// 当前登记审核通过的工时价格
		// Float
		// real_subtotal=real_labour_hour_amount*productionProcess.getCost_price();
		// 因为可能一道工序分多次生产的话 这个物料成本之前在数据库中就会有
		// 所以先获取之前的物料成本 在加上当前这个登记的物料成本
		// Float real_module_subtotal=productionProcess.getReal_module_subtotal();
		//上面注释掉的原由是 我直接再修改语句中
		//使用+=的方式  可以减少错误
		//当前登记的工序的实际物料价格  为什么没有加上之前的物料价格
		//因为之前的物料价格会再修改的时候使用 
		//之前的实际物料价格+=当前的物料价格 
		//实际工时价格也是一样
		Float real_module_subtotal = 0f;
		if (productionProcessMaterials != null && !productionProcessMaterials.isEmpty()) {
			//如果当前工序的所有从库存领取的物料都使用完了之后
			//才会将当前工序状态修改为已完成的状态
			boolean isFinish=true;
			for (ProductionProcessMaterial productionProcessMaterial : productionProcessMaterials) {
				//实际物料成本 =当前登记的物料使用数量*物料的价格
				real_module_subtotal += 
						productionProcessMaterial.getProductionProcessMaterialCourse().getUse_amount()
						*productionProcessMaterial.getCost_price();
				//当前物料使用的总数量 ：已使用的数量+当前审核时候使用的数量
				//这里可能会出现数据不同步问题
				//原因：productionProcessMaterial.getRenew_amount()的值可能和数据库的值不同
				//而在下面的修改Renew_amount方法中 使用的时数据库的数量renew_amount=renew_amount+use_amount
				//如果出现 下次就使用在到数据库查询就行了
				int amount_sum= productionProcessMaterial.getRenew_amount()+productionProcessMaterial.getProductionProcessMaterialCourse().getUse_amount();
				//如果只要有一次amount_sum-productionProcessMaterial.getReal_amount()!=0
				//成立  那么就说明 这个产品的这道工序至少还有一个物料没有使用  那它的完成状态就不能为已完成
				if(isFinish) {
					//总的使用数量如果==从库存领取物料的数量相同的话 说明这个工序的这个物料使用完了
					if(amount_sum-productionProcessMaterial.getReal_amount()!=0) {
						isFinish=false;
					}
				}
			}
			//如果还是为true说明该工序所有物料都使用完了
			if(isFinish) {
				//那就设置当前工序为已完工状态
				process_finish_tag = ProductionConstant.FINISH_PROCESS_FINISH_TAG;
			}else {
				// 又设置为当前工序为可以登记的状态
				process_finish_tag = ProductionConstant.NOT_START_PROCESS_FINISH_TAG;
			}
		}else {
			// 如果当前工序状态为已经完成的状态的审核中
			//并且还没有设置物料
			if (productionProcess.getProcess_finish_tag() == ProductionConstant.FINISH_CHECK_PROCESS_FINISH_TAG) {
				// 设置当前工序为完成状态
				process_finish_tag = ProductionConstant.FINISH_PROCESS_FINISH_TAG;
			}else {
				// 又设置为当前工序为可以登记的状态
				process_finish_tag = ProductionConstant.NOT_START_PROCESS_FINISH_TAG;
			}
		}
		//当前登记的工序的实际工时价格
		Float real_subtotal=productionProcessCourse.getLabour_hour_amount()*productionProcess.getCost_price();
		// 设置已使用工时数为 已使用工时数+当前工序使用得工时数
		productionProcess.setReal_labour_hour_amount(productionProcessCourse.getLabour_hour_amount());
		productionProcess.setReal_subtotal(real_subtotal);
		productionProcess.setReal_module_subtotal(real_module_subtotal);
		productionProcess.setProcess_finish_tag(process_finish_tag);
		// 修改生产工序的状态 实际物料金额 实际工时金额
		i = productionProcessService.update(productionProcess);
		if (productionProcessMaterials != null && !productionProcessMaterials.isEmpty()) {
			// 修改生产工序物料过程
			i = productionProcessMaterialCourseService.batchUpdate(productionProcessMaterials);
			// 修改生产工序物料
			i = productionProcessMaterialService.batchUpdateRealAmount1(productionProcess.getId(),
					productionProcessMaterials);
		}
//		List<Integer> process_finish_tags=new ArrayList<Integer>();
//		//当前生产工序为登记过但是没有完工的审核中
//		process_finish_tags.add(ProductionConstant.UNFINISHED_CHECK_PROCESS_FINISH_TAG);
//		//当前生产工序为登记过并且完成的审核中
//		process_finish_tags.add(ProductionConstant.FINISH_CHECK_PROCESS_FINISH_TAG);
//		//当前生产工序状态为交接审核中的
//		Integer process_transfer_tag = ProductionConstant.UNFINISHED_RANSFER_PROCESS_TRANSFER_TAG;
		// Integer
		// check_finish=productionProcessService.getCheckFinishCount(manufacture.getId(),process_finish_tags,process_transfer_tag);
		// 上面得一些状态直接写到了这个方法中去了
		Integer check_finish = productionProcessService.getCheckFinishCount(manufacture.getId());
		if (check_finish > 0) {// 如果大于零 说明还有未审核的工序
			return false;
		} else {
			// 就是没有 就说明没有未审核的工序了 那么就修改生产工序主表manufacture的状态为生产中
			Integer manufacture_procedure_tag = ProductionConstant.EXECUTE_MANUFACTURE_PROCEDURE_TAG;
			i = manufactureService.setManufacture_procedure_tag(manufacture.getId(), manufacture_procedure_tag);
			return true;
		}
	}

}
