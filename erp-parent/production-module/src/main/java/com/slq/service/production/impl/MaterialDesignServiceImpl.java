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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.common.constant.ProductConstant;
import com.slq.mapper.production.MaterialDesignMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.ProductMaterialDesign;
import com.slq.service.production.IMaterialDesignDetailService;
import com.slq.service.production.IMaterialDesignService;
import com.slq.service.production.IProductService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;
/***
 * 产品物料设计组成
 * @author 孙陆泉
 *
 */
@Transactional
@Service
@CacheConfig(cacheNames = "productionModule")
public class MaterialDesignServiceImpl extends ServiceImpl<MaterialDesignMapper, ProductMaterialDesign> implements IMaterialDesignService{

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IMaterialDesignDetailService materialDesignDetailService;
	
	
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<ProductMaterialDesign> search(Params params) {
		return PageHelper.startPage(params.getPageNum(),params.getPageSize())
			.doSelectPageInfo(()->
					this.baseMapper.search(params)
					);
	}
	
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int insert(ProductMaterialDesign productMaterialDesign) {
		String message="新增产品物料组成设计失败!";
		User user =((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		productMaterialDesign.setRegister(user.getUid());
		productMaterialDesign.setDesign_id(IDGenerateUtil.getID(ProductConstant.PRODUCT_MATERIAL_DESIGN));
		//设置为审核中
		productMaterialDesign.setCheck_tag(ProductConstant.UNREVIEWED);
		this.getBaseMapper().insert(productMaterialDesign);
		System.out.println("productMaterialDesign.getDesign_id():"+productMaterialDesign.getDesign_id());
		if(productMaterialDesign.getId()>0) {
			int i = materialDesignDetailService.insert(productMaterialDesign.getId(),productMaterialDesign.getMaterials());
			if(i>0) {
				//设置物料状态为审核中
				i = productService.update_material_tag(productMaterialDesign.getProduct_id(),ProductConstant.MATERIAL_DESIGN_CHECKER);
				if(i>0) {
					return productMaterialDesign.getId();
				}
				message="商品修改状态失败!";
			}else
				message="物料详细新增失败!";
		}
		throw new RuntimeException(message);
	}
	//查询所有的  再审核中的产品物料设计单
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<ProductMaterialDesign> checkerMaterialDesign() {

		return this.baseMapper.checkerMaterialDesign();
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public ProductMaterialDesign getMateralDesignById(Integer id) {
		// TODO Auto-generated method stub
		return this.baseMapper.getMateralDesignById(id);
	}
	//驳回
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int materialDesignReject(ProductMaterialDesign productMaterialDesign){
		String message="物料单状态设置失败!";
		User user =((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		Integer id=productMaterialDesign.getId();
		//商品编号
		String product_id=productMaterialDesign.getProduct_id();
		//驳回原因
		String module_describe=productMaterialDesign.getModule_describe();
		String uid=user.getUid();
		//设置状态为沒有通过
		Integer check_tag= ProductConstant.MATERIAL_DESIGN_REJECT;
		Date current_time=new Date();
		productMaterialDesign=new ProductMaterialDesign
						(id, module_describe, uid, check_tag, current_time);
		int i = this.baseMapper.updateById(productMaterialDesign);
		if(i>0) {
			return i;
			//注释这个的原因  请看ProcessDesignServiceImpl下的processDesignReject方法
			//Integer material_tag=ProductConstant.MATERIAL_DESIGN_CHECKER;
//			i = productService.update_material_tag(product_id, material_tag);
//			if(i>0) {
//				return i;
//			}
//			message="产品状态设置失败!";
		}
		throw new RuntimeException(message);
	}
	//通过
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int materialDesignPass(ProductMaterialDesign productMaterialDesign){
		String message="物料单状态设置失败!";
		User user =((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		//物料单id
		Integer id=productMaterialDesign.getId();
		//商品编号
		String product_id=productMaterialDesign.getProduct_id();
		String uid=user.getUid();
		//设置状态为通过
		Integer check_tag=ProductConstant.MATERIAL_DESIGN_PASS;
		Date current_time=new Date();
		productMaterialDesign=new ProductMaterialDesign
				(id, null, uid, check_tag, current_time);
		int i=this.baseMapper.updateById(productMaterialDesign);
		if(i>0) {
			//设置产品表对应的物料设计标志为已经设计
			i = productService.update_material_tag(product_id, ProductConstant.ACCOMPLISH_MATERIAL_DESIGN);
			if(i>0) {
				return i;
			}
			message="产品状态设置失败!";
		}
		throw new RuntimeException(message);
	}
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int update(ProductMaterialDesign design) {
		String message="修改失败!";
		User user =((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
		design.setChange_time(null);
		design.setCheck_tag(null);
		design.setChanger(null);
		design.setProduct_id(null);
		design.setRegister(null);
		design.setRegister_time(null);
		design.setChange_time(new Date());
		design.setChanger(user.getUid());
		//设置状态为审核中
		design.setCheck_tag(ProductConstant.MATERIAL_DESIGN_UNREVIEWED);
		int i = materialDesignDetailService.deleteByParentId(design.getId());
		if(i>0) {
			i = materialDesignDetailService.insert(design.getId(), design.getMaterials());
			if(i>0) {
				i = this.baseMapper.updateById(design);
				if(i>0) {
					return i;
				}
			}
		}
		throw new RuntimeException(message);
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public int checkMaterialUseState(String product_id) {
		
		return this.baseMapper.checkMaterialUseState(product_id);
	}
	

	/***
	 * i = productService.update_material_tag(productMaterialDesign.getProduct_id(),ProductConstant.ACCOMPLISH_MATERIAL_DESIGN);
				if(i>0) {
					
				}else
					message="物料状态设置失败!";
	 */
}
