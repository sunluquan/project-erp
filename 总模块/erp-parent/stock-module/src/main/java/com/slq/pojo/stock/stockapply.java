package com.slq.pojo.stock;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.slq.pojo.production.Product;
import com.slq.pojo.stock.stockapplydetails;

public class stockapply {
	private int appid;// int(20)  AUTO_INCREMENT COMMENT '请购单编号',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date appdate;// DATE not null COMMENT '请购单日期',
	private String apptype;// VARCHAR(20) not null COMMENT '请购采购类型  商品采购 原材料采购 用品采购',
	private  int appcirce;// int not null DEFAULT 0 COMMENT '	默认0 未结案，1：已结案  2：无效',
	private String appperson;// VARCHAR(50) not null COMMENT '请购人员',
	private String apppersonid;// VARCHAR(50) not null COMMENT '请购人员id',
	private String appauditingperson;// varchar(20) COMMENT '复核人员',
	private int appauditing;// int not null DEFAULT 0 COMMENT '	默认0 未审核，1：已审核  2：无效',
	private int enables;// int DEFAULT 0 COMMENT '	默认0 不删除，1：删除 ',
	private List<stockapplydetails> stockapplydetails;
	//详情
	private List<Product> product;
	//金额
	private int je;
	
	public stockapply(int appid, Date appdate, String apptype, int appcirce, String appperson, String apppersonid,
			String appauditingperson, int appauditing, int enables,
			List<stockapplydetails> stockapplydetails, List<Product> product, int je) {
		super();
		this.appid = appid;
		this.appdate = appdate;
		this.apptype = apptype;
		this.appcirce = appcirce;
		this.appperson = appperson;
		this.apppersonid = apppersonid;
		this.appauditingperson = appauditingperson;
		this.appauditing = appauditing;
		this.enables = enables;
		this.stockapplydetails = stockapplydetails;
		this.product = product;
		this.je = je;
	}
	
	public int getJe() {
		return je;
	}

	public void setJe(int je) {
		this.je = je;
	}

	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	public List<stockapplydetails> getStockapplydetails() {
		return stockapplydetails;
	}
	public void setStockapplydetails(List<stockapplydetails> stockapplydetails) {
		this.stockapplydetails = stockapplydetails;
	}
	
	public stockapply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public Date getAppdate() {
		return appdate;
	}
	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public int getAppcirce() {
		return appcirce;
	}
	public void setAppcirce(int appcirce) {
		this.appcirce = appcirce;
	}
	public String getAppperson() {
		return appperson;
	}
	public void setAppperson(String appperson) {
		this.appperson = appperson;
	}
	public String getApppersonid() {
		return apppersonid;
	}
	public void setApppersonid(String apppersonid) {
		this.apppersonid = apppersonid;
	}
	public String getAppauditingperson() {
		return appauditingperson;
	}
	public void setAppauditingperson(String appauditingperson) {
		this.appauditingperson = appauditingperson;
	}
	public int getAppauditing() {
		return appauditing;
	}
	public void setAppauditing(int appauditing) {
		this.appauditing = appauditing;
	}
	public int getEnables() {
		return enables;
	}
	public void setEnables(int enables) {
		this.enables = enables;
	}
}
