package com.slq.service.production;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.Product;

public interface IProductService {

	PageInfo<Product> productSearch(Params params);

	int insertProduct(Product product);

	List<Product> checker_search();

	Product getCheckerProduct(Integer id);

	Product getProductById(Integer id);

	int update(Product product);

	int updateCheckTag(Product product);

	int rejectProductDesign(Integer id, String cause);
	int rejectProductDesign(Product product);
	//查询所有产品  物料为设计但是通过审核了的
	List<Product> getProductWantMaterialDesign();
	// 查询所有物料 物料通过审核了的
	List<Product> getNotProductWantMaterialDesign();
	
	
	//MaterialController下的查询
	PageInfo<Product> searchMaterial(Params params);
	//设置物料状态的
	int update_material_tag(String product_id, int material_tag);
	//获取符合制定工序的产品
	List<Product> getAccordMakingProcessProducts();

	int updateProcess_tag(String product_id, Integer Process_tag);
	//修改产品表下的工序设计状态为已经设计 并且设置实际成本价
	int updateProcessTagAndRealCostPrice(String product_id, Integer process_tag, float real_cost_price);

	PageInfo<Product> searchCanProductionProduct(Params params);
	//获取符合制定派工单的产品
		//按产品划分
	List<Product> getMeetDispatchingProduction();
}
