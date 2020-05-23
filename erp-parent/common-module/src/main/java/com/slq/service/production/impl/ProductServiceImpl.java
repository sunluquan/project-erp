package com.slq.service.production.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.common.constant.Constant;
import com.slq.common.constant.ProductConstant;
import com.slq.mapper.UserMapper;
import com.slq.mapper.production.ProductKindMapper;
import com.slq.mapper.production.ProductMapper;
import com.slq.pojo.User;
import com.slq.pojo.production.Product;
import com.slq.pojo.production.Use;
import com.slq.service.IUserService;
import com.slq.service.production.IProductService;
import com.slq.service.production.IUseService;
import com.slq.util.ActiveUser;
import com.slq.util.IDGenerateUtil;
import com.slq.util.PinYingUtil;

@Service
@Transactional
@CacheConfig(cacheNames = "productionModule")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

	@Autowired
	private ProductKindMapper productKindMapper;
	@Autowired
	private IUseService useService;
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<Product> productSearch(Params params) {
		// productMapper.productSearch(params);
		LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<Product>();
		// like 模糊查询名称
//		queryWrapper.like(Product::getProduct_name, params.getKeyWord()).like(Product::getInitial, params.getKeyWord());
//		queryWrapper.apply(condition, applySql, value) 
		// queryWrapper.apply("", value)
		// queryWrapper.getBaseMapper().p
//		Page<Product> productPage=new Page<Product>(params.getPageNum(), params.getPageSize());
//		productPage.setRecords(this.baseMapper.productSearch(params));
		Map<String, Object> map = params.getReplenishParams();
		// 判断是否有选中的类别
		String key = "kinds";
		if (map != null && !map.isEmpty()) {
			String[] checkKinds = (String[]) map.get(map.keySet().toArray()[0]);
			String checkKinds1 = JSON.toJSONString(checkKinds);
			checkKinds1 = checkKinds1.substring(1, checkKinds1.length() - 1);
			// 不需要判断是否为null或者 “”因为在xml文件中判断过了
			params.setReplenishParams(key, checkKinds1);
		}
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
				.doSelectPageInfo(() -> this.baseMapper.productSearch(params));
		// return productPage;
	}

	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int insertProduct(Product product) {
		Use use = useService.getUse(product.getUse_type());
		String prefix = PinYingUtil.getShortPinyin(use.getUseName());
		product.setProduct_id(IDGenerateUtil.getID(prefix));
		product.setInitial(PinYingUtil.getShortPinyin(product.getProduct_name()));
		Subject subject = SecurityUtils.getSubject();
		User user = ((ActiveUser) subject.getPrincipal()).getUser();
		product.setRegister(user.getUid());
		int i = this.baseMapper.insert(product);
		// int i=this.insertProduct(product);
		if (i > 0) {
			return i;
		}
		throw new RuntimeException("新增失败!");
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<Product> checker_search() {
//		LambdaQueryWrapper<Product> queryWrapper=Wrappers.lambdaQuery();
//		
//		//查询所有未审核
//		queryWrapper.eq(Product::getCheck_tag, Constant.UNREVIEWED);
//		return this.baseMapper.selectList(queryWrapper);
		return this.baseMapper.checkerProduct();
	}

	// 审核时候用到
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public Product getCheckerProduct(Integer id) {
		LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery();
		// queryWrapper.select(columns)
		queryWrapper.eq(Product::getId, id);
		queryWrapper.select(Product.class, info -> !info.getColumn().equals("initial")
				&& !info.getColumn().equals("process_tag") && !info.getColumn().equals("material_tag")
				&& !info.getColumn().equals("inventory_allocation_tag") && !info.getColumn().equals("check_tag"));
		// queryWrapper.select(User.class,info->!info.getColumn().equals("create_time")
		// &&!info.getColumn().equals("manager_id"));
		return this.baseMapper.selectOne(queryWrapper);
	}

	// 修改时候用到
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public Product getProductById(Integer id) {
		LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery();
		// queryWrapper.select(columns)
		queryWrapper.eq(Product::getId, id);
		queryWrapper.select(Product.class,
				info -> !info.getColumn().equals("initial") && !info.getColumn().equals("process_tag")
						&& !info.getColumn().equals("material_tag")
						&& !info.getColumn().equals("inventory_allocation_tag") && !info.getColumn().equals("checker")
						&& !info.getColumn().equals("check_time") && !info.getColumn().equals("check_tag"));
		// queryWrapper.select(User.class,info->!info.getColumn().equals("create_time")
		// &&!info.getColumn().equals("manager_id"));
		return this.baseMapper.selectOne(queryWrapper);
	}

	// 修改普通的修改
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int update(Product product) {
		product.setInitial(PinYingUtil.getShortPinyin(product.getProduct_name()));
		// 修改人
		Subject subject = SecurityUtils.getSubject();
		User user = ((ActiveUser) subject.getPrincipal()).getUser();
		product.setChanger(user.getUid());
		// 修改时间
		product.setChange_time(new Date());
		// 设置未未审核
		product.setCheck_tag(Constant.UNREVIEWED);
		int i = this.baseMapper.updateById(product);
		if (i > 0) {
			return i;
		}
		throw new RuntimeException("修改提交失败!");
	}

	// 核审的时候的修改
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int updateCheckTag(Product product) {
		product.setInitial(PinYingUtil.getShortPinyin(product.getProduct_name()));
		// 设置审核人
		Subject subject = SecurityUtils.getSubject();
		User user = ((ActiveUser) subject.getPrincipal()).getUser();
		product.setChecker(user.getUid());
		product.setCheck_tag(Constant.PASS);
		// 设置审核时间
		product.setCheck_time(new Date());
		int i = this.baseMapper.updateById(product);
		if (i > 0) {
			return i;
		}
		throw new RuntimeException("审核提交失败!");
	}
	// 驳回产品设计
	// 属性名称

//描述
//
//示例
//
//methodName
//
//当前方法名
//
//#root.methodName
//
//method
//
//当前方法
//
//#root.method.name
//
//target
//
//当前被调用的对象
//
//#root.target
//
//targetClass
//
//当前被调用的对象的class
//
//#root.targetClass
//
//args
//
//当前方法参数组成的数组
//
//#root.args[0]
//
//caches
	//旧的驳回
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int rejectProductDesign(Integer id, String cause) {
//		LambdaUpdateWrapper<Product> queryWrapper=Wrappers.lambdaUpdate();
		Subject subject = SecurityUtils.getSubject();
		User user = ((ActiveUser) subject.getPrincipal()).getUser();
//		queryWrapper.eq(Product::getId, id);
		Product product = new Product(id, user.getUid(), new Date(), Constant.REJECT);
		product.setMessage(cause);
		// queryWrapper.setEntity(product);
		int i = this.baseMapper.updateById(product);
		if (i > 0) {
			return i;
		}
		throw new RuntimeException("驳回失败");
	}
	//新的驳回方法
	@Transactional
	@CacheEvict(allEntries = true)
	@Override
	public int rejectProductDesign(Product product) {
		Subject subject = SecurityUtils.getSubject();
		User user = ((ActiveUser) subject.getPrincipal()).getUser();
		LambdaUpdateWrapper<Product> updateWrapper=Wrappers.lambdaUpdate();
		//设置产品的状态为驳回状态
		Integer reject = Constant.REJECT;
		Date current_time=new Date();
		String checker=user.getUid();
		//产品编号
		Integer id=product.getId();
		//
		String message=product.getMessage();
		updateWrapper.set(Product::getCheck_tag,reject);
		updateWrapper.set(Product::getCheck_time, current_time);
		updateWrapper.set(Product::getChecker, checker);
		updateWrapper.set(Product::getMessage, message);
		updateWrapper.eq(Product::getId,id);
		return this.baseMapper.update(null, updateWrapper);
	}
	// 查询所有产品 未设计但是通过审核了的
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<Product> getProductWantMaterialDesign() {

		return this.baseMapper.getProductWantMaterialDesign(ProductConstant.WANT_MATERIAL_DESIGN_USE);
	}

	// 查询所有物料 物料通过审核了的
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<Product> getNotProductWantMaterialDesign() {

		return this.baseMapper.getNotProductWantMaterialDesign(ProductConstant.NOT_WANT_MATERIAL_DESIGN_USE);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<Product> searchMaterial(Params params) {
		params.setReplenishParams("array", ProductConstant.NOT_WANT_MATERIAL_DESIGN_USE);
		return PageHelper.startPage(params.getPageNum(), params.getPageSize())
				.doSelectPageInfo(()->
						this.baseMapper.searchMaterial(params)
						);
	}
	//设置产品物料状态的
	@CacheEvict(allEntries = true)
	@Override
	public int update_material_tag(String product_id, int material_tag) {
		LambdaUpdateWrapper<Product> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.set(Product::getMaterial_tag, material_tag);
		updateWrapper.eq(Product::getProduct_id,product_id);
		return this.baseMapper.update(null, updateWrapper);
	}
	/***
	 * 获取符合制定工序的产品
	 */
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<Product> getAccordMakingProcessProducts() {
		//获取产品
		Integer [] uses=ProductConstant.WANT_MATERIAL_DESIGN_USE;
		//并且审核通过
		Integer check_tag=ProductConstant.PASS;
		//物料为已经设计
		Integer material_tag=ProductConstant.ACCOMPLISH_MATERIAL_DESIGN;
		//工序从未设计过的
		Integer process_tag=ProductConstant.NOT_MAKING_PROCESS;
		
		
		return this.baseMapper.getProducts(uses,check_tag,material_tag,process_tag,null);
	}
	@Transactional
	@Override
	public int updateProcess_tag(String product_id,Integer process_tag) {
		LambdaUpdateWrapper<Product> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(Product::getProduct_id,product_id);
		updateWrapper.set(Product::getProcess_tag, process_tag);
		return this.baseMapper.update(null, updateWrapper);
	}
	//修改产品表下的工序设计状态为已经设计 并且设置实际成本价
	@Transactional
	@Override
	public int updateProcessTagAndRealCostPrice(String product_id, Integer process_tag, float real_cost_price) {
		LambdaUpdateWrapper<Product> updateWrapper=Wrappers.lambdaUpdate();
		updateWrapper.eq(Product::getProduct_id,product_id);
		updateWrapper.set(Product::getProcess_tag, process_tag);
		updateWrapper.set(Product::getReal_cost_price,real_cost_price);
		return this.baseMapper.update(null, updateWrapper);
	}
	//这个是在新增生产计划的时候选择产品的时候用到
		//他需要能够生产  也就是  物料设计 工序设计  工序物料设计 等都要为已经设计
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public PageInfo<Product> searchCanProductionProduct(Params params) {
		//产品审核状态为通过
		Integer check_tag=ProductConstant.PASS;
		//产品物料状态为已经设计
		Integer material_tag=ProductConstant.ACCOMPLISH_MATERIAL_DESIGN;
		//产品工序状态为已经设计
		Integer process_tag=ProductConstant.ACCOMPLISH_MANING_PROCESS;
		return PageHelper.startPage(params.getPageNum(),params.getPageSize())
					.doSelectPageInfo(()->
						this.baseMapper.searchCanProductionProduct(check_tag,material_tag,process_tag,params)
					);
	}
	//获取符合制定派工单的产品
		//按产品划分
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<Product> getMeetDispatchingProduction() {
		
		return this.baseMapper.getMeetDispatchingProduction();
	}

	@Override
	public List<Product> getNotConfigureInventoryProducts() {
//		LambdaUpdateWrapper<Product> updateWrapper=Wrappers.lambdaUpdate();
//		updateWrapper.eq(Product::getCheck_tag, ProductConstant.PASS);
//		//获取未分配库存的产品
//		updateWrapper.eq(Product::getInventory_allocation_tag, ProductConstant.NOT_INVENTORY_ALLOCATION);
//		return this.baseMapper.selectList(updateWrapper);
		List<Product> products =this.baseMapper.getNotConfigureInventoryProducts();
		System.out.println(JSON.toJSONString(products));
		return products;
	}

	
}
