package com.slq.pojo.stock;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class stockorderdetails {
	private int sodid;// int AUTO_INCREMENT COMMENT '单据号',
	private int sodlineid;// int COMMENT '外键',
	private String sodmaterielid;// int COMMENT '物料编号',
	private String sodmaterielname;// VARCHAR(20) NOT null COMMENT '物料名称',
	private int sodamoun;// int not null COMMENT '数量',
	private double sodpreprice;// double  COMMENT '折扣前单价',
	private double sodagio;// double COMMENT '折数（%）',
	private double sodprice;// double not null COMMENT '单价',
	private double sodsum;// double not null COMMENT '进价金额',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date sodsdate;// DATE not null COMMENT '日期',
	private String sodsmark;// varchar(500) COMMENT '备注',
	public stockorderdetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public stockorderdetails(int sodid, int sodlineid, String sodmaterielid, String sodmaterielname, int sodamoun,
			double sodpreprice, double sodagio, double sodprice, double sodsum, Date sodsdate, String sodsmark) {
		super();
		this.sodid = sodid;
		this.sodlineid = sodlineid;
		this.sodmaterielid = sodmaterielid;
		this.sodmaterielname = sodmaterielname;
		this.sodamoun = sodamoun;
		this.sodpreprice = sodpreprice;
		this.sodagio = sodagio;
		this.sodprice = sodprice;
		this.sodsum = sodsum;
		this.sodsdate = sodsdate;
		this.sodsmark = sodsmark;
	}
	public int getSodid() {
		return sodid;
	}
	public void setSodid(int sodid) {
		this.sodid = sodid;
	}
	public int getSodlineid() {
		return sodlineid;
	}
	public void setSodlineid(int sodlineid) {
		this.sodlineid = sodlineid;
	}
	public String getSodmaterielid() {
		return sodmaterielid;
	}
	public void setSodmaterielid(String sodmaterielid) {
		this.sodmaterielid = sodmaterielid;
	}
	public String getSodmaterielname() {
		return sodmaterielname;
	}
	public void setSodmaterielname(String sodmaterielname) {
		this.sodmaterielname = sodmaterielname;
	}
	public int getSodamoun() {
		return sodamoun;
	}
	public void setSodamoun(int sodamoun) {
		this.sodamoun = sodamoun;
	}
	public double getSodpreprice() {
		return sodpreprice;
	}
	public void setSodpreprice(double sodpreprice) {
		this.sodpreprice = sodpreprice;
	}
	public double getSodagio() {
		return sodagio;
	}
	public void setSodagio(double sodagio) {
		this.sodagio = sodagio;
	}
	public double getSodprice() {
		return sodprice;
	}
	public void setSodprice(double sodprice) {
		this.sodprice = sodprice;
	}
	public double getSodsum() {
		return sodsum;
	}
	public void setSodsum(double sodsum) {
		this.sodsum = sodsum;
	}
	public Date getSodsdate() {
		return sodsdate;
	}
	public void setSodsdate(Date sodsdate) {
		this.sodsdate = sodsdate;
	}
	public String getSodsmark() {
		return sodsmark;
	}
	public void setSodsmark(String sodsmark) {
		this.sodsmark = sodsmark;
	}
	@Override
	public String toString() {
		return "stockorderdetails [sodid=" + sodid + ", sodlineid=" + sodlineid + ", sodmaterielid=" + sodmaterielid
				+ ", sodmaterielname=" + sodmaterielname + ", sodamoun=" + sodamoun + ", sodpreprice=" + sodpreprice
				+ ", sodagio=" + sodagio + ", sodprice=" + sodprice + ", sodsum=" + sodsum + ", sodsdate=" + sodsdate
				+ ", sodsmark=" + sodsmark + "]";
	}
}
