package com.slq.pojo.stock;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slq.pojo.stock.RecedeDetails;

/**
 * 采购退货主表
 * @author lqy
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class StockRecede {
	//采购退货单号
	private int recId;
	//单据日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date recDate;
	//供应商编号
	private int peoId;
	//供应商名称
	private String purveyName;
	//供应商地址
	private String peoAddress;
	//仓库编号
	private int stoId;
	//仓库名称
	private String depotName;
	//是否重新入库
	private int aNewStorage;
	private String[] anewStorage=new String[] {"是","否"};
	//采购人员姓名
	private String stoPerson;
	//采购人员Id
	private int stoPersonId;
	//采购部门
	private String stoDept;
	//采购部门编号
	private int stoDeptId;
	//复核人员
	private String auditingPerson;
	//备注
	private String recRemark;
	//
	private int eState;
	private String[]eStates=new String[] {"未审核","审核通过","被驳回"};
	//退货详情
	private List<RecedeDetails> recedeDetails;
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	public int getPeoId() {
		return peoId;
	}
	public void setPeoId(int peoId) {
		this.peoId = peoId;
	}
	public String getPurveyName() {
		return purveyName;
	}
	public void setPurveyName(String purveyName) {
		this.purveyName = purveyName;
	}
	public String getPeoAddress() {
		return peoAddress;
	}
	public void setPeoAddress(String peoAddress) {
		this.peoAddress = peoAddress;
	}
	public int getStoId() {
		return stoId;
	}
	public void setStoId(int stoId) {
		this.stoId = stoId;
	}
	public String getDepotName() {
		return depotName;
	}
	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}
	public String getaNewStorage() {
		return anewStorage[this.aNewStorage];
	}
	public void setaNewStorage(int aNewStorage) {
		this.aNewStorage = aNewStorage;
	}
	public String getStoPerson() {
		return stoPerson;
	}
	public void setStoPerson(String stoPerson) {
		this.stoPerson = stoPerson;
	}
	public int getStoPersonId() {
		return stoPersonId;
	}
	public void setStoPersonId(int stoPersonId) {
		this.stoPersonId = stoPersonId;
	}
	public String getStoDept() {
		return stoDept;
	}
	public void setStoDept(String stoDept) {
		this.stoDept = stoDept;
	}
	public int getStoDeptId() {
		return stoDeptId;
	}
	public void setStoDeptId(int stoDeptId) {
		this.stoDeptId = stoDeptId;
	}
	public String getAuditingPerson() {
		return auditingPerson;
	}
	public void setAuditingPerson(String auditingPerson) {
		this.auditingPerson = auditingPerson;
	}
	public String getRecRemark() {
		return recRemark;
	}
	public void setRecRemark(String recRemark) {
		this.recRemark = recRemark;
	}
	public String geteState() {
		return eStates[this.eState];
	}
	public void seteState(int eState) {
		this.eState = eState;
	}
	public List<RecedeDetails> getRecedeDetails() {
		return recedeDetails;
	}
	public void setRecedeDetails(List<RecedeDetails> recedeDetails) {
		this.recedeDetails = recedeDetails;
	}
	
}
