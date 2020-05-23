package com.slq.pojo.stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class stockorder implements Serializable{
	private int stoid;// int AUTO_INCREMENT COMMENT '单据号',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date stodate;// DATE not null COMMENT '日期',
	private String stopeoname;// varchar(20) COMMENT '供货商',
	private int  stopeoid;// int COMMENT '供货商id',
	private String  stopeoadress;// varchar(50) COMMENT '供货商地址',
	private int stostate;// int not null  DEFAULT 0 COMMENT '单况：0-未结案，1-已结案，2-无效',
	private String [] stostates=new String[] {"未结案","已结案","无效"};
	private String  stoadress;// varchar(50)  COMMENT '送货地址',
	private String stoempstock;// varchar(10) COMMENT '采购人员',
	private int stopersonid;// int COMMENT '采购人员id',
	private String stodept;// varchar(20) COMMENT '采购部门',
	private int stodeptid;// int COMMENT '采购部门id',
	private String stoempcheck;// varchar(10) COMMENT '复核人员',
	private int checkstate;// int not null DEFAULT 0 COMMENT '默认0 未审核，1：已审核  2：无效',
	private String stomark;// varchar(200) COMMENT '备注',
	//详情
	private List<stockapplydetails> stockapplydetails;
	//采购详情
	private List<stockorderdetails> stockorderDetails;
	//金额
	private int je;
	
	public stockorder(int stoid, Date stodate, String stopeoname, int stopeoid, String stopeoadress, int stostate,
			String stoadress, String stoempstock, int stopersonid, String stodept, int stodeptid, String stoempcheck,
			int checkstate, String stomark, List<stockapplydetails> stockapplydetails, int je) {
		super();
		this.stoid = stoid;
		this.stodate = stodate;
		this.stopeoname = stopeoname;
		this.stopeoid = stopeoid;
		this.stopeoadress = stopeoadress;
		this.stostate = stostate;
		this.stoadress = stoadress;
		this.stoempstock = stoempstock;
		this.stopersonid = stopersonid;
		this.stodept = stodept;
		this.stodeptid = stodeptid;
		this.stoempcheck = stoempcheck;
		this.checkstate = checkstate;
		this.stomark = stomark;
		this.stockapplydetails = stockapplydetails;
		this.je = je;
	}

	public List<stockapplydetails> getStockapplydetails() {
		return stockapplydetails;
	}

	public void setStockapplydetails(List<stockapplydetails> stockapplydetails) {
		this.stockapplydetails = stockapplydetails;
	}

	public int getJe() {
		return je;
	}

	public void setJe(int je) {
		this.je = je;
	}

	public stockorder() {
		super();
	}
	
	public int getStoid() {
		return stoid;
	}
	public void setStoid(int stoid) {
		this.stoid = stoid;
	}
	public Date getStodate() {
		return stodate;
	}
	public void setStodate(Date stodate) {
		this.stodate = stodate;
	}
	public String getStopeoname() {
		return stopeoname;
	}
	public void setStopeoname(String stopeoname) {
		this.stopeoname = stopeoname;
	}
	public int getStopeoid() {
		return stopeoid;
	}
	public void setStopeoid(int stopeoid) {
		this.stopeoid = stopeoid;
	}
	public String getStopeoadress() {
		return stopeoadress;
	}
	public void setStopeoadress(String stopeoadress) {
		this.stopeoadress = stopeoadress;
	}
	public String getStostate() {
		return stostates[this.stostate];
	}
	public void setStostate(int stostate) {
		this.stostate = stostate;
	}
	public String getStoadress() {
		return stoadress;
	}
	public void setStoadress(String stoadress) {
		this.stoadress = stoadress;
	}
	public String getStoempstock() {
		return stoempstock;
	}
	public void setStoempstock(String stoempstock) {
		this.stoempstock = stoempstock;
	}
	public int getStopersonid() {
		return stopersonid;
	}
	public void setStopersonid(int stopersonid) {
		this.stopersonid = stopersonid;
	}
	public String getStodept() {
		return stodept;
	}
	public void setStodept(String stodept) {
		this.stodept = stodept;
	}
	public int getStodeptid() {
		return stodeptid;
	}
	public void setStodeptid(int stodeptid) {
		this.stodeptid = stodeptid;
	}
	public String getStoempcheck() {
		return stoempcheck;
	}
	public void setStoempcheck(String stoempcheck) {
		this.stoempcheck = stoempcheck;
	}
	public int getCheckstate() {
		return this.checkstate;
	}
	public void setCheckstate(int checkstate) {
		this.checkstate = checkstate;
	}
	public String getStomark() {
		return stomark;
	}
	public void setStomark(String stomark) {
		this.stomark = stomark;
	}

	public List<stockorderdetails> getStockorderDetails() {
		return stockorderDetails;
	}

	public void setStockorderDetails(List<stockorderdetails> stockorderDetails) {
		this.stockorderDetails = stockorderDetails;
	}

	@Override
	public String toString() {
		return "stockorder [stoid=" + stoid + ", stodate=" + stodate + ", stopeoname=" + stopeoname + ", stopeoid="
				+ stopeoid + ", stopeoadress=" + stopeoadress + ", stostate=" + stostate + ", stoadress=" + stoadress
				+ ", stoempstock=" + stoempstock + ", stopersonid=" + stopersonid + ", stodept=" + stodept
				+ ", stodeptid=" + stodeptid + ", stoempcheck=" + stoempcheck + ", checkstate=" + checkstate
				+ ", stomark=" + stomark + ", stockapplydetails=" + stockapplydetails + ", stockorderDetails="
				+ stockorderDetails + ", je=" + je + ", getStockapplydetails()=" + getStockapplydetails() + ", getJe()="
				+ getJe() + ", getStoid()=" + getStoid() + ", getStodate()=" + getStodate() + ", getStopeoname()="
				+ getStopeoname() + ", getStopeoid()=" + getStopeoid() + ", getStopeoadress()=" + getStopeoadress()
				+ ", getStostate()=" + getStostate() + ", getStoadress()=" + getStoadress() + ", getStoempstock()="
				+ getStoempstock() + ", getStopersonid()=" + getStopersonid() + ", getStodept()=" + getStodept()
				+ ", getStodeptid()=" + getStodeptid() + ", getStoempcheck()=" + getStoempcheck() + ", getCheckstate()="
				+ getCheckstate() + ", getStomark()=" + getStomark() + ", getStockorderDetails()="
				+ getStockorderDetails() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
