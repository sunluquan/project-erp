package com.slq.pojo.warehouse;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/*
 * 入库表
 */
@Data
public class Gather {
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
	private String checkTag;			//审核状态0:等待审核1:审核通过2:审核未通过
	private String attemper;			//调度人
	private String attemmperTime;		//调度时间
	private String storeTag;			//库存标记1:已登记2:已调度
	private List<GatherDetails> gatherDetails;
	private String productId;
	private String productName;
	private String useName;
	private Integer stockstorageno;
	private Integer amount;//	int	COMMENT 'N				数量',
	private double unitprice;//	DOUBLE COMMENT	'N				单价',
	private double moneys;//	DOUBLE COMMENT	'N				金额',
	private Integer materielid;
	private String materielname;
	private String storeName;
	private Integer maxamount;
	private String storeAddress;
}
