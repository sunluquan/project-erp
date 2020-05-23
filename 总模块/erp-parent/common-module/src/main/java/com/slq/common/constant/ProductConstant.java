package com.slq.common.constant;
/***
 * 产品相关的公共类
 * @author 孙陆泉
 *
 */
public interface ProductConstant {
	//再工序表中使用          
	Integer DOWN_STATE=2;//不可用
	Integer UP_STATE=1; //可用
	//产品的
	int REJECT=2;//驳回
	int UNREVIEWED=0;//未审核 审核中的
	int PASS=1;//审核通过
	//产品库存设置状态inventory_allocation_tagcomplete
	int NOT_INVENTORY_ALLOCATION=0;//未分配库存的状态
	int COMPLETE_INVENTORY_ALLOCATION=1;//已经分配了
	//物料设计单的
	int MATERIAL_DESIGN_REJECT=2;//驳回
	int MATERIAL_DESIGN_PASS=1;//审核通过
	int MATERIAL_DESIGN_UNREVIEWED=0;//审核中
	//产品表夏的 material_tag字段的
	Integer NOT_MATERIAL_DESIGN=-1;//物料未设计
	Integer MATERIAL_DESIGN_CHECKER=0;//物料为审核中 或者驳回状态
	Integer ACCOMPLISH_MATERIAL_DESIGN=1;//物料已经设计
	//需要物料设计的有哪些  有1编号 商品 2编号部件
	Integer [] WANT_MATERIAL_DESIGN_USE= {1,2};
	//不需要物料设计的时 3 物料
	Integer [] NOT_WANT_MATERIAL_DESIGN_USE = {3};

	//工序相关的
	//产品中工序相关的
	//工序未设置的 
	Integer NOT_MAKING_PROCESS=-1;
	//工序再审核中或者被驳回
	Integer MAKING_PROCESS_CHECKER=0;
	//工序已经设计的
	Integer ACCOMPLISH_MANING_PROCESS=1;
	//工序总表下的check_tag字段的值
	int PROCESS_DESIGN_REJECT=2;//驳回
	int PROCESS_DESIGN_PASS=1;//审核通过
	int PROCESS_DESIGN_UNREVIEWED=0;//审核中
	//工序总表下的process_material_tag 工序物料设计
	Integer NOT_PROCESS_MATERIAL_DESIGN=-1;//工序物料从未设计过
	Integer UNREVIEWED_PROCESS_MATERIAL_DESIGN=0;//工序物料在审核中
	Integer ACCOMPLISH_PROCESS_MATERIAL_DESIGN=1;//工序物料已经设计
	Integer REJECT_PROCESS_MATERIAL_DESIGN=2;//设计过但是没有通过审核
	//工序详细表下的material_tag状态值
	Integer NOT_PROCESS_MATERIAL_TAG=0;//没有设计
	Integer ACCOMPLISH_PROCESS_MATERIAL_TAG=1;//已经设计过
	//产品设计的前缀
	String PRODUCT_DESIGN="cpsj";
	//产品物料设计
	String PRODUCT_MATERIAL_DESIGN="cpwlsj";
	//工序设计
	String PROCESS_DESIGN="gxsj";
}
