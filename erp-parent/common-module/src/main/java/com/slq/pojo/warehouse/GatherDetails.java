package com.slq.pojo.warehouse;

import java.io.Serializable;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.baomidou.mybatisplus.annotation.TableField;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/*
 * 入库明细
 */
@Data
@TableName("s_gather_details")
public class GatherDetails implements Serializable{
	
		public GatherDetails(String productId, String productName, double realCostPrice, double subtotal,
			Integer mustAmount, Integer payTag) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.realCostPrice = realCostPrice;
		this.subtotal = subtotal;
		this.mustAmount = mustAmount;
		this.payTag = payTag;
	}
		public GatherDetails() {
		super();
	}
		@TableId
		private Integer id;				//序号
<<<<<<< HEAD
<<<<<<< HEAD
		private Integer parentId;       //制定入库单父级
=======
		private String parentId;       //制定入库单父级
>>>>>>> warehouse
=======
		private Integer parentId;       //制定入库单父级
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		private Integer sid;	//仓库编号
		private String productId;		//产品编号
		private String productName;		//产品名称
		private Integer amount;			//数量
		private String productUnit;		//单位
		private double realCostPrice;		//单价
		private double subtotal;		//小计
		private Integer paidAmount;		//确认入库数量
		private Integer mustAmount;     //因入库数量
		private Integer payTag;//0 1 2	
		private Integer stockamount;//入库标志1:已登记2:已调度
		private String storeName;
		private String storeAddress;
<<<<<<< HEAD
<<<<<<< HEAD
=======
	    private	Storehouse storehouse;
		@TableField(exist = false)
		private int qwe;
		@TableField(exist = false)
		private int qwes;
		@TableField(exist = false)
		private String pid;
		private Integer maxamount;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
