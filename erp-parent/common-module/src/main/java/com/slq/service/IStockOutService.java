package com.slq.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.warehouse.sPay;
import com.slq.pojo.warehouse.sPayDetails;

public interface IStockOutService {

	int addStockOut(sPay spay);								//添加出库单还有详细单
	List<sPay> selectByTag();								//查询所有未审核的出库单
	sPay selectByPayId(String id);							//根据出库单编号查询出库单对象
	List<sPay> selectAll();									//查询所有出库单
	int upStatus(sPay spay);								//审核通过,审核修改出库单状态
	int upNotStatus(sPay spay);								//审核未通过
	PageInfo<sPay> searchSpay(Params params);				//分页查询
	List<sPay> selectTags();								//查询所有通过审核的出库单
	sPay selectDetails(sPayDetails spay);					//根据出库单编号还有产品编号查询单个产品的调度详细	
	int selectCount();										//查询所有未审核的出库单的总数
	sPay selectDetails(String payId, String productId);		//根据出库单编号和产品编号查询调度详细
	int upSum(sPayDetails sPayDetails);						//调度页面，修改产品的出库数量和调度状态
	List<sPay> selectRegists();								//查询所有已调度出库单
	int selectRegistCount();								//查询所有已调度出库单的总数
	int aksure(sPay spay);									//修改整条出库单的调度状态
	int checkInOne(sPayDetails sPayDetails);				//修改产品的登记状态和库存数量

}
