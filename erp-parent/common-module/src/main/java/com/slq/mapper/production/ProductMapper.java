package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.common.Params;
import com.slq.pojo.production.Product;


@Mapper
public interface ProductMapper extends BaseMapper<Product>{

	List<Product> productSearch(Params params);

	List<Product> checkerProduct();
	//查询所有产品  未设计但是通过审核了的
	List<Product> getProductWantMaterialDesign( Integer[] wantMaterialDesignUse);
	//查询所有不需要设计物料的 如物料本身 是通过审核了的
	List<Product> getNotProductWantMaterialDesign(Integer[] notWantMaterialDesignUse);
//查询是物料的
	List<Product> searchMaterial(Params params);
	//通用型查询
	/***
	 * 
	 * @param uses :用途类型  数值
	 * @param check_tag ：产品审核状态
	 * @param material_tag：物料设计状态
	 * @param process_tag：工序设计状态
	 * @param inventory_allocation_tag ：库存设计状态
	 * @return
	 */
	List<Product> getProducts(@Param("uses")Integer[] uses, 
			@Param("check_tag")Integer check_tag, 
			@Param("material_tag")Integer material_tag,
			@Param("process_tag")Integer process_tag,
			@Param("inventory_allocation_tag")Integer inventory_allocation_tag);

	List<Product> searchCanProductionProduct(
			@Param("check_tag")Integer check_tag, 
			@Param("material_tag")Integer material_tag,
			@Param("process_tag")Integer process_tag,
			@Param("params")Params params);
	//获取符合制定派工单的产品
		//按产品划分
	List<Product> getMeetDispatchingProduction();

	List<Product> getNotConfigureInventoryProducts();
}
