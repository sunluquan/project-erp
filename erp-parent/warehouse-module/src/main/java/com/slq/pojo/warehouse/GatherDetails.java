package com.slq.pojo.warehouse;

import lombok.Data;

/*
 * 入库明细
 */
@Data
public class GatherDetails {
		private Integer id;				//序号
		private Integer sid;	//仓库编号
		private String productId;		//产品编号
		private String productName;		//产品名称
		private Integer amount;			//数量
		private String productUnit;		//单位
		private double realCostPrice;		//单价
		private double subtotal;		//小计
		private Integer paidAmount;		//确认入库数量
		private String payTag;			//入库标志1:已登记2:已调度
}
