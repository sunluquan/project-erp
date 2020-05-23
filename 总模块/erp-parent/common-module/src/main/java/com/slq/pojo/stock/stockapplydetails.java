package com.slq.pojo.stock;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class stockapplydetails {
	private int appid;//int;//(20)  AUTO_INCREMENT COMMENT '请购明细单编号',
	private int apdrows;// int(20) not null COMMENT '外键 请购主表id',
	private String apdmateridlid;// int not null COMMENT '物料编号',
	private String apdmateridlname;// VARCHAR(20) NOT null COMMENT '物料名称',
	private int apdunit;// int not null COMMENT '数量',
	private double listprice;// double not null COMMENT '标准进价',
	private double costprice;// double not null COMMENT '单价',
	private double realcos;
	private double apdintomoney;// double not null COMMENT '进价金额',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date appdate;// DATE not null COMMENT '日期',
	private String apdremark;// varchar(500) COMMENT '备注',
	
	private double mobile;
	
	public stockapplydetails(int appid, int apdrows, String apdmateridlid, String apdmateridlname, int apdunit,
			double listprice, double costprice, double realcos, double apdintomoney, Date appdate, String apdremark,
			double mobile) {
		super();
		this.appid = appid;
		this.apdrows = apdrows;
		this.apdmateridlid = apdmateridlid;
		this.apdmateridlname = apdmateridlname;
		this.apdunit = apdunit;
		this.listprice = listprice;
		this.costprice = costprice;
		this.realcos = realcos;
		this.apdintomoney = apdintomoney;
		this.appdate = appdate;
		this.apdremark = apdremark;
		this.mobile = mobile;
	}
	public double getMobile() {
		return mobile;
	}
	public void setMobile(double mobile) {
		this.mobile = mobile;
	}
	public stockapplydetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public stockapplydetails(int appid, int apdrows, String apdmateridlid, String apdmateridlname, int apdunit,
			double listprice, double costprice, double realcos, double apdintomoney, Date appdate, String apdremark) {
		super();
		this.appid = appid;
		this.apdrows = apdrows;
		this.apdmateridlid = apdmateridlid;
		this.apdmateridlname = apdmateridlname;
		this.apdunit = apdunit;
		this.listprice = listprice;
		this.costprice = costprice;
		this.realcos = realcos;
		this.apdintomoney = apdintomoney;
		this.appdate = appdate;
		this.apdremark = apdremark;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public int getApdrows() {
		return apdrows;
	}
	public void setApdrows(int apdrows) {
		this.apdrows = apdrows;
	}
	public String getApdmateridlid() {
		return apdmateridlid;
	}
	public void setApdmateridlid(String apdmateridlid) {
		this.apdmateridlid = apdmateridlid;
	}
	public String getApdmateridlname() {
		return apdmateridlname;
	}
	public void setApdmateridlname(String apdmateridlname) {
		this.apdmateridlname = apdmateridlname;
	}
	public int getApdunit() {
		return apdunit;
	}
	public void setApdunit(int apdunit) {
		this.apdunit = apdunit;
	}
	public double getListprice() {
		return listprice;
	}
	public void setListprice(double listprice) {
		this.listprice = listprice;
	}
	public double getCostprice() {
		return costprice;
	}
	public void setCostprice(double costprice) {
		this.costprice = costprice;
	}
	public double getRealcos() {
		return realcos;
	}
	public void setRealcos(double realcos) {
		this.realcos = realcos;
	}
	public double getApdintomoney() {
		return apdintomoney;
	}
	public void setApdintomoney(double apdintomoney) {
		this.apdintomoney = apdintomoney;
	}
	public Date getAppdate() {
		return appdate;
	}
	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}
	public String getApdremark() {
		return apdremark;
	}
	public void setApdremark(String apdremark) {
		this.apdremark = apdremark;
	}
	
	
}
