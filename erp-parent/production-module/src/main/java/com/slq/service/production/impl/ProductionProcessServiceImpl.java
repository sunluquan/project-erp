package com.slq.service.production.impl;

import java.util.ArrayList;
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
import com.slq.common.constant.ProductionConstant;
import com.slq.mapper.production.ProductionProcessMapper;
import com.slq.pojo.production.ManufactureSum;
import com.slq.pojo.production.ProcessDesign;
import com.slq.pojo.production.ProductionProcess;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.service.IGatherService;
import com.slq.service.production.IManufactureService;
import com.slq.service.production.IProductionProcessMaterialService;
import com.slq.service.production.IProductionProcessService;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductionProcessServiceImpl extends ServiceImpl<ProductionProcessMapper, ProductionProcess>
		implements IProductionProcessService {

	@Autowired
	private IProductionProcessMaterialService productionProcessMaterialService;
	@Autowired
	private IManufactureService manufactureService;
	//制入库单的业务层
	@Autowired
	private IGatherService gatherService;
	
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int insert(Integer manufacture_id, List<ProductionProcess> productionProcessList) {
		// TODO Auto-generated method stub
		return this.baseMapper.batchInsert(manufacture_id, productionProcessList);
	}

	// 通过 根据产品编号 通过processDes
	// 查询这个工序总表下的工序详细 :processDetail
	// 然后转换为生产工序 transition:转换的意思
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductionProcess> getProcessDetailTransitionProductionProcess(Integer manufacture_id) {

		return this.baseMapper.getProcessDetailTransitionProductionProcess(manufacture_id);
	}

	// 失效
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int register(ManufactureSum manufacture) {
		ProductionProcess productionProcess = manufacture.getProductionProcess1();
<<<<<<< HEAD
		// 未完成的审核状态
		Integer process_finish_tag = productionProcess.getProcess_finish_tag();//
		// 如何审核为完成时的审核的话
		if (process_finish_tag != null && process_finish_tag == ProductionConstant.FINISH_CHECK_PROCESS_FINISH_TAG) {

=======
		// 工序的状态
		Integer process_finish_tag = productionProcess.getProcess_finish_tag();//
		// 如何审核为完成时的审核的话
		if (process_finish_tag != null && process_finish_tag == ProductionConstant.FINISH_CHECK_PROCESS_FINISH_TAG) {
			//process_finish_tag = ProductionConstant.FINISH_PROCESS_FINISH_TAG;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		} else {
			// 将它设置为未完成的审核状态
			process_finish_tag = ProductionConstant.UNFINISHED_CHECK_PROCESS_FINISH_TAG;
		}

		return 0;
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int updateProductionProcess_process_finish_tag(Integer manufacture_id, Integer process_finish_tag,
			Float labour_hour_amount) {
		LambdaUpdateWrapper<ProductionProcess> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionProcess::getProcess_finish_tag, process_finish_tag);
		updateWrapper.set(ProductionProcess::getReal_labour_hour_amount, labour_hour_amount);
		updateWrapper.eq(ProductionProcess::getId, manufacture_id);
		return this.baseMapper.update(null, updateWrapper);
	}

	//修改当前生产工序实际物料价格 实际工时价格
	//实际工时数 等都使用+=的方法进行修改
	//这样的好处就是安全性和可靠性会高一些
	//一开始是使用重新计算之前的实际物料价格+当前物料价格 后再进行修改
	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public int update(ProductionProcess productionProcess) {
//		LambdaUpdateWrapper<ProductionProcess> updateWrapper = Wrappers.lambdaUpdate();
//		updateWrapper.set(ProductionProcess::getProcess_finish_tag, productionProcess.getProcess_finish_tag());
//		updateWrapper.set(ProductionProcess::getReal_labour_hour_amount,
//				productionProcess.getReal_labour_hour_amount());
//		updateWrapper.set(ProductionProcess::getReal_subtotal, productionProcess.getReal_subtotal());
//		updateWrapper.eq(ProductionProcess::getId, productionProcess.getId());
//		return this.baseMapper.update(null, updateWrapper);
		//修改一些实际的属性和状态
		return this.baseMapper.updateReal(productionProcess);
	}

	// 复核生产登记的时候
	// 联合查询生产工序 和生产过程工序
	// 在联合查询生产工序物料和生产过程工序物料
	@Override
	public ProductionProcess getProductionProcessCourseByProcessIdAndmanufactureId(Integer manufacture_id,
			String process_id) {
		ProductionProcess productionProcess = this.baseMapper
				.getProductionProcessCourseByProcessIdAndmanufactureId(manufacture_id, process_id);
		// 生产工序单编号
		Integer production_process_id = productionProcess.getId();
		// 生产工序过程编号
		Integer production_process_course_id = productionProcess.getProductionProcessCourse().getId();
		List<ProductionProcessMaterial> productionProcessMaterials = productionProcessMaterialService
				.getProductionProcessMaterialAndUseAmount(production_process_id, production_process_course_id);
		productionProcess.setProductionProcessMaterials(productionProcessMaterials);
		return productionProcess;
	}

	@CacheEvict(allEntries = true)
	@Override
	public int update_process_finish_tag(ProductionProcess productionProcess) {
		LambdaUpdateWrapper<ProductionProcess> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionProcess::getProcess_finish_tag, productionProcess.getProcess_finish_tag());
		// updateWrapper.set(ProductionProcess::getReal_amount,
		// productionProcess.getReal_amount());
		updateWrapper.eq(ProductionProcess::getId, productionProcess.getId());
		return this.baseMapper.update(null, updateWrapper);
	}

	// 根据生产工序得父级也就是 manufacture生产主表
	// 下得所有工序得状态 是否有登记状态为 完成时得审核中 和未完成时得审核中
	// 还有交接状态为审核交接得状态得话就是大于0
	@Override
	public Integer getCheckFinishCount(Integer parent_id) {
		List<Integer> process_finish_tags = new ArrayList<Integer>();
		// 当前生产工序为登记过但是没有完工的审核中
		process_finish_tags.add(ProductionConstant.UNFINISHED_CHECK_PROCESS_FINISH_TAG);
		// 当前生产工序为登记过并且完成的审核中
		process_finish_tags.add(ProductionConstant.FINISH_CHECK_PROCESS_FINISH_TAG);
		// 当前生产工序状态为交接审核中的
		Integer process_transfer_tag = ProductionConstant.UNFINISHED_RANSFER_PROCESS_TRANSFER_TAG;
		LambdaQueryWrapper<ProductionProcess> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(ProductionProcess::getParent_id, parent_id);
		//and和 or连用 带括号的  必须要带括号
		queryWrapper.and(wrapper->wrapper.in(ProductionProcess::getProcess_finish_tag, process_finish_tags)
				.or().eq( ProductionProcess::getProcess_transfer_tag, process_transfer_tag));
		return this.baseMapper.selectCount(queryWrapper);
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public Integer handover(ProductionProcess productionProcess) {
		// 设置 工序交接状态为审核中
		Integer process_transfer_tag = ProductionConstant.UNFINISHED_RANSFER_PROCESS_TRANSFER_TAG;
		productionProcess.setProcess_transfer_tag(process_transfer_tag);
		LambdaUpdateWrapper<ProductionProcess> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionProcess::getProcess_transfer_tag, process_transfer_tag);
		// 设置该工序得合格数量
		updateWrapper.set(ProductionProcess::getReal_amount, productionProcess.getReal_amount());
		updateWrapper.eq(ProductionProcess::getId, productionProcess.getId());
		Integer i = this.baseMapper.update(null, updateWrapper);
		// 将当前工序总表得状态改写为审核中
		Integer manufacture_procedure_tag = ProductionConstant.UNREVIEWED_MANUFACTURE_PROCEDURE_TAG;
		i = manufactureService.setManufacture_procedure_tag(productionProcess.getParent_id(),
				manufacture_procedure_tag);
		return i;
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public boolean checker_handover(ProductionProcess productionProcess) {
		// 设置 工序交接状态为审核通过
		Integer process_transfer_tag = ProductionConstant.FINISH_RANSFER_PROCESS_TRANSFER_TAG;
		productionProcess.setProcess_transfer_tag(process_transfer_tag);
		LambdaUpdateWrapper<ProductionProcess> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.set(ProductionProcess::getProcess_transfer_tag, process_transfer_tag);
		// 设置该工序得合格数量
		updateWrapper.set(ProductionProcess::getReal_amount, productionProcess.getReal_amount());
		updateWrapper.eq(ProductionProcess::getId, productionProcess.getId());
		Integer i = this.baseMapper.update(null, updateWrapper);
		//判断当前工序合格数量是否等于设计的数量
		//如果不等于就进行
		//报损报溢
		//得到数值差 设计数量-合格数量
		Integer numerical_difference= productionProcess.getDemand_amount()-productionProcess.getReal_amount();
		if(numerical_difference!=0) {
			//需要修改下一道工序的设计数量
			//需要写报损报溢单
		}
		//查询生产总单下的工序状态  是否有审核中的工序登记
		//和审核中的交接
		Integer check_finish=getCheckFinishCount(productionProcess.getParent_id());
		if(check_finish>0) {
			//说明还有审核中的工序登记和交接
			return false;
		}else {
			//获取没有交接完成的数量
			Integer manufacture_procedure_tag=null;
			Integer notTransferCount = getNotProcess_transfer_tag_finish_count(productionProcess.getParent_id());
			if(notTransferCount==0) {
				//如果交接状态为0 就说明它的交接状态都是交接完成的
				//那就说明这个产品的内部生产已经完成了
				//就将这个生产主表的生产状态设置为生产已完成
				//这个状态在下面那个修改的方法中写了
				//manufacture_procedure_tag=ProductionConstant.FINISH_MANUFACTURE_PROCEDURE_TAG;
				//不仅要修改它的生产状态
				//还需要设置它的实际物料总成本 实际工时总成本 实际生产数量等
				//实际合格数量就为当前最后一道工序的实际合格数量
				ManufactureSum manufacture=new ManufactureSum();
				manufacture.setId(productionProcess.getParent_id());
				//获取通过生产总表的id获取该总表下的
				//工序集合的实际总物料 和 实际工时总价格
				//和manufacture实体
				manufacture=manufactureService.getManufactureReal(manufacture.getId());
				//设置整个生产完成的合格数量为当前最后一次交接的合格数量
				manufacture.setTested_amount(productionProcess.getReal_amount());
				//设置当前生产主表的生产状态为已经完成
//				manufacture.setManufacture_procedure_tag(manufacture_procedure_tag);
				//修改当前生产主表的实际总物料价格
				//实际总工时数 实际总合格数量
				//和生产状态
				manufactureService.updateReal(manufacture);
				//成品的入库
				gatherService.finishedProductStorage(manufacture); 
			}else {
				//如果没有交接状态的数量大于0的话
				//那就说明这个产品的内部生产还未完成
				//就有将这个生产主表的生产状态设置为生产中
				manufacture_procedure_tag=ProductionConstant.EXECUTE_MANUFACTURE_PROCEDURE_TAG;
				//修改生产主表的生产状态
				manufactureService.setManufacture_procedure_tag(productionProcess.getParent_id(), manufacture_procedure_tag);
			}
			return true;
		}
	}


	@Override
	public Integer getNotProcess_transfer_tag_finish_count(Integer parent_id) {
		Integer process_transfer_tag = ProductionConstant.FINISH_RANSFER_PROCESS_TRANSFER_TAG;
		LambdaQueryWrapper<ProductionProcess> queryWrapper=Wrappers.lambdaQuery();
		//查询不等于  交接状态为完成的状态的数量
		queryWrapper.notIn(ProductionProcess::getProcess_transfer_tag, process_transfer_tag);
		queryWrapper.eq(ProductionProcess::getParent_id, parent_id);
		return this.baseMapper.selectCount(queryWrapper);
	}

}
