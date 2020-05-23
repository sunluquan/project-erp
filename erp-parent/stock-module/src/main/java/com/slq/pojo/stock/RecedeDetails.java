package com.slq.pojo.stock;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slq.pojo.stock.StockRecede;

/**
 * 采购退货明细表
 * @author lqy
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RecedeDetails {
	//采购退货单号
	private int recId;
	//序号
	private int detSequ;
	//物料编号
	private String matId;
	//物料名称
	private String matName;
	//数量
	private double detNum;
	//折扣前单价
	private double detagioPrice;
	//折扣数
	private double detDiscount;
	//单价
	private double detPrice;
	//金额
	private double detamtMoney;
	//日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date detDate;
	//备注
	private String detMark;
	//订单
	private StockRecede stockRecede;
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public int getDetSequ() {
		return detSequ;
	}
	public void setDetSequ(int detSequ) {
		this.detSequ = detSequ;
	}
	public String getMatId() {
		return matId;
	}
	public void setMatId(String matId) {
		this.matId = matId;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public double getDetNum() {
		return detNum;
	}
	public void setDetNum(double detNum) {
		this.detNum = detNum;
	}
	public double getDetagioPrice() {
		return detagioPrice;
	}
	public void setDetagioPrice(double detagioPrice) {
		this.detagioPrice = detagioPrice;
	}
	public double getDetDiscount() {
		return detDiscount;
	}
	public void setDetDiscount(double detDiscount) {
		this.detDiscount = detDiscount;
	}
	public double getDetPrice() {
		return detPrice;
	}
	public void setDetPrice(double detPrice) {
		this.detPrice = detPrice;
	}
	public double getDetamtMoney() {
		return detamtMoney;
	}
	public void setDetamtMoney(double detamtMoney) {
		this.detamtMoney = detamtMoney;
	}
	public Date getDetDate() {
		return detDate;
	}
	public void setDetDate(Date detDate) {
		this.detDate = detDate;
	}
	public String getDetMark() {
		return detMark;
	}
	public void setDetMark(String detMark) {
		this.detMark = detMark;
	}
	public StockRecede getStockRecede() {
		return stockRecede;
	}
	public void setStockRecede(StockRecede stockRecede) {
		this.stockRecede = stockRecede;
	}
	@Override
	public String toString() {
		return "RecedeDetails [recId=" + recId + ", detSequ=" + detSequ + ", matId=" + matId + ", matName=" + matName
				+ ", detNum=" + detNum + ", detagioPrice=" + detagioPrice + ", detDiscount=" + detDiscount
				+ ", detPrice=" + detPrice + ", detamtMoney=" + detamtMoney + ", detDate=" + detDate + ", detMark="
				+ detMark + ", stockRecede=" + stockRecede + "]";
	}
}
