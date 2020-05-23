package com.slq.pojo.warehouse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/*
 * 入库表
 */
@Data
@TableName("s_gather")
public class Gather implements Serializable{
	
<<<<<<< HEAD
	
=======
>>>>>>> warehouse
	public Gather(String gatherId, String stockid, String storer, String reasonexact, Integer amountSum,
			double costPriceSum, Integer gatheredAmountSum, String remark, String register, Date registerTime) {
		super();
		this.gatherId = gatherId;
		this.stockid = stockid;
		this.storer = storer;
		this.reasonexact = reasonexact;
		this.amountSum = amountSum;
		this.costPriceSum = costPriceSum;
		this.gatheredAmountSum = gatheredAmountSum;
		this.remark = remark;
		this.register = register;
		this.registerTime = registerTime;
	}
<<<<<<< HEAD
	public Gather() {
		super();
	}
=======
	
	public Gather() {
		super();
	}
//	public Gather(String gatherId, String stockid, String storer, String reasonexact, Integer amountSum,
//		double costPriceSum, Integer gatheredAmountSum, String remark, String register, Date registerTime) {
//	super();
//	this.gatherId = gatherId;
//	this.stockid = stockid;
//	this.storer = storer;
//	this.reasonexact = reasonexact;
//	this.amountSum = amountSum;
//	this.costPriceSum = costPriceSum;
//	this.gatheredAmountSum = gatheredAmountSum;
//	this.remark = remark;
//	this.register = register;
//	this.registerTime = registerTime;
//}
>>>>>>> warehouse
	@TableId
	private Integer id;					//序号
	private String gatherId;        //入库单编号
	private String stockid;           //外来入库编号
	private String storer;				//入库人
	private String reasonexact;			//入库详细理由
	private Integer amountSum;			//总数量
	private double costPriceSum;		//总金额
	private Integer gatheredAmountSum;	//确认入库总数量
	private String remark;				//备注
	private String register;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//登记人
	private Date registerTime;		//登记时间
	private String checker;				//审核人
	private String checkTime;			//审核时间
	private Integer checkTag;			//审核状态0:等待审核1:审核通过2:审核未通过
<<<<<<< HEAD
	private String attemper;			//调度人
	private String attemmperTime;		//调度时间
	private String storeTag;			//库存标记1:已登记2:已调度
	@TableField(exist = false)
	private List<GatherDetails> gatherDetails;
//	private String productId;
//	private String productName;
//	private double realCostPrice;
//	private String useName;
//	private String stockstorageno;
//	private Integer amount;//	int	COMMENT 'N				数量',
//	private Integer paidAmount;
//	private double subtotal;
//	private double unitprice;
//	private Integer stockamount;//	DOUBLE COMMENT	'N				单价',
//	private double moneys;//	DOUBLE COMMENT	'N				金额',
//	private String materielid;
//	private String materielname;
//	private String storeName;
//	private Integer maxamount;
//	private String storeAddress;
=======
	private String attemper;           //调度人
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//登记人
	private Date attemmperTime;		//调度时间
	private Integer storeTag;      //库存标记1:已登记2:已调度
	private Integer payTag;
	@TableField(exist = false)
	private List<GatherDetails> gatherDetails;
	@TableField(exist = false)
	private List<Safetystock> safetystock;
     
	private List<Stock> stocks;
	private String productId;
	@TableField(exist = false)
	private String productName;
	@TableField(exist = false)
	private double realCostPrice;
	@TableField(exist = false)
	private String useName;
	@TableField(exist = false)
	private String stockstorageno;
	@TableField(exist = false)
	private Integer mustAmount;
	@TableField(exist = false)
	private Integer amount;//	int	COMMENT 'N				数量',
	@TableField(exist = false)
    private Integer paidAmount;
	@TableField(exist = false)
	private double subtotal;
	@TableField(exist = false)
	private double unitprice;
	@TableField(exist = false)
	private Integer stockamount;//	DOUBLE COMMENT	'N				单价',
	@TableField(exist = false)
	private double moneys;//	DOUBLE COMMENT	'N				金额',
	@TableField(exist = false)
	private String materielid;
	@TableField(exist = false)
	private String materielname;
	@TableField(exist = false)
	private String storeName;
	@TableField(exist = false)
	private Integer maxamount;
	@TableField(exist = false)
	private String storeAddress;
	@TableField(exist = false)
	private int qwe;
	@TableField(exist = false)
	private int sid;
	@TableField(exist = false)
	private double summoneys;
	@TableField(exist = false)
	private Integer sumnum;
	
	
>>>>>>> warehouse
}
