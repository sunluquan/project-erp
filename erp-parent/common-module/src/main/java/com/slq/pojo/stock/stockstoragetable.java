package com.slq.pojo.stock;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class stockstoragetable {
	private int id;
	private int cid;
	private int stockstorageno;//	INT	COMMENT 'N	Y	N		单号（自动生成',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date storagedate;
	private int purveyid;//	int	COMMENT 'N				供货商ID',
	private String purveyname;//	Varchar(50)	COMMENT 'N				供货商名称',
	private String purveyaddress;//	Varchar(50)	COMMENT 'Y				供货商地址',
	private int depotid;//int	COMMENT 'N				仓库ID',
	private String depotname;//	Varchar(50)	COMMENT 'n				仓库名称',
	private String stoperson;//	Varchar(50)	COMMENT 'N				采购人员',
	private String stodept;//	Varchar(50)	COMMENT 'n				采购人员所属部门名称',
	private int stodeptid;//	int	COMMENT 'N				部门ID',
	private String audituserid;//	Varchar(50)	COMMENT 'Y				审核人员ID',
	private String repeatuserid;//	Varchar(50)	COMMENT 'Y				复核人员ID',
	private String remark;//	Varchar(2000)	COMMENT 'Y				备注',
	private int estate;//	int	 DEFAULT 0 COMMENT 'N			Defaule(0)	单据状态（是否以审核）',	
	private int je;
	
	//详情
	private List<stockorderdetails> stockorderdetails;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public stockstoragetable(int id, int cid, int stockstorageno, Date storagedate, int purveyid, String purveyname,
			String purveyaddress, int depotid, String depotname, String stoperson, String stodept, int stodeptid,
			String audituserid, String repeatuserid, String remark, int estate, int je,
			List<stockorderdetails> stockorderdetails) {
		super();
		this.id = id;
		this.cid = cid;
		this.stockstorageno = stockstorageno;
		this.storagedate = storagedate;
		this.purveyid = purveyid;
		this.purveyname = purveyname;
		this.purveyaddress = purveyaddress;
		this.depotid = depotid;
		this.depotname = depotname;
		this.stoperson = stoperson;
		this.stodept = stodept;
		this.stodeptid = stodeptid;
		this.audituserid = audituserid;
		this.repeatuserid = repeatuserid;
		this.remark = remark;
		this.estate = estate;
		this.je = je;
		this.stockorderdetails = stockorderdetails;
	}

	public int getJe() {
		return je;
	}

	public void setJe(int je) {
		this.je = je;
	}

	public List<stockorderdetails> getStockorderdetails() {
		return stockorderdetails;
	}

	public void setStockorderdetails(List<stockorderdetails> stockorderdetails) {
		this.stockorderdetails = stockorderdetails;
	}

	public stockstoragetable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getStockstorageno() {
		return stockstorageno;
	}
	public void setStockstorageno(int stockstorageno) {
		this.stockstorageno = stockstorageno;
	}
	public Date getStoragedate() {
		return storagedate;
	}
	public void setStoragedate(Date storagedate) {
		this.storagedate = storagedate;
	}
	public int getPurveyid() {
		return purveyid;
	}
	public void setPurveyid(int purveyid) {
		this.purveyid = purveyid;
	}
	public String getPurveyname() {
		return purveyname;
	}
	public void setPurveyname(String purveyname) {
		this.purveyname = purveyname;
	}
	public String getPurveyaddress() {
		return purveyaddress;
	}
	public void setPurveyaddress(String purveyaddress) {
		this.purveyaddress = purveyaddress;
	}
	public int getDepotid() {
		return depotid;
	}
	public void setDepotid(int depotid) {
		this.depotid = depotid;
	}
	public String getDepotname() {
		return depotname;
	}
	public void setDepotname(String depotname) {
		this.depotname = depotname;
	}
	public String getStoperson() {
		return stoperson;
	}
	public void setStoperson(String stoperson) {
		this.stoperson = stoperson;
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
	public String getAudituserid() {
		return audituserid;
	}
	public void setAudituserid(String audituserid) {
		this.audituserid = audituserid;
	}
	public String getRepeatuserid() {
		return repeatuserid;
	}
	public void setRepeatuserid(String repeatuserid) {
		this.repeatuserid = repeatuserid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getEstate() {
		return estate;
	}
	public void setEstate(int estate) {
		this.estate = estate;
	}
	
	
}
