package com.slq.pojo.warehouse;
import java.io.Serializable;

import lombok.Data;

/*
 * 出库明细表
 */
//@Data
public class sPayDetails implements Serializable{
	private Integer id;				//序号
	private String parentId;		//父级序号
	private Integer warehouseId;	//仓库编号
	private String productId;		//产品编号
	private String productName;		//产品名称
	private Integer amount;			//申请出库数量
	private Integer detailSum;		//本次出库数量
	private Integer checkInSum;		//登记出库数量
	private String amounUnit;		//单位
	private double costPrice;		//单价
	private String payTag;			//出库标志1:已登记2"已调度
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public Integer getDetailSum() {
		return detailSum;
	}
	public void setDetailSum(Integer detailSum) {
		this.detailSum = detailSum;
	}
	public void setAmounUnit(String amounUnit) {
		this.amounUnit = amounUnit;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getAmounUnit() {
		return amounUnit;
	}
	public void setAmountUnit(String amounUnit) {
		this.amounUnit = amounUnit;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public String getPayTag() {
		return payTag;
	}
	public void setPayTag(String payTag) {
		this.payTag = payTag;
	}
	public Integer getCheckInSum() {
		return checkInSum;
	}
	public void setCheckInSum(Integer checkInSum) {
		this.checkInSum = checkInSum;
	}
}
