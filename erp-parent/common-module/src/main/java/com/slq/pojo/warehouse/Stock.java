package com.slq.pojo.warehouse;

import lombok.Data;
/*
 * 库存表
 */
@Data
public class Stock {
	private Integer id;			//序号
	private String stockId;		//库存编号
	private String productId;	//产品编号
<<<<<<< HEAD
<<<<<<< HEAD
	private Integer amount;		//当前存储量
=======
	private Integer amount;	
	private Integer sid;//当前存储量
>>>>>>> warehouse
=======
	private Integer amount;		//当前存储量
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	private String registMan;	//登记人
	private String registTime;	//登记时间
	private String checkMan;	//复核人
	private String checkTime;	//复核时间
	private String checkTag;	//审核状态
	private String storeName;
	private String productName;
	private String kindName;
	private String useName;
	private String productRank;
	private String productUnit;
	private Integer minamount;
	private Integer maxamount;
	private Integer maxCapacityAmount;
<<<<<<< HEAD
<<<<<<< HEAD
=======
	private double realCostPrice;
	private Integer paidAmount; 	
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
