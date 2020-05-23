package com.slq.pojo.warehouse;

import java.util.List;

import lombok.Data;

/*
 * 出库表
 */
@Data
public class sPay {
	private Integer id;				//序号
	private String payId;			//出库单编号
	private String storer;			//出库人记录
	private Integer amountSum;		//出库总数量
	private Integer costPriceSum;	//总金额
	private String reasonexact;		//出库详细理由
	private String remark;			//备注
	private String register;		//登记人
	private String registerTime;	//登记时间
	private String checker;			//审核人
	private String checkTime;		//审核时间
	private String checkTag;		//审核状态 0:等待审核1:审核通过2:审核未通过
	private String checkNotReason;	//审核未通过的理由
	private String attemper;		//调度人
	private String attemmperTime;	//调度时间
	private String storeTag;		//库存标记0:1:已登记 2:已调度
	private sPayDetails spayDetails1;
	private List<sPayDetails> spayDetails;//出库详细表
}
