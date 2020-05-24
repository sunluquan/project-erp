package com.slq.pojo.stock;

public class stockstoragedetails {
	private int id;// int AUTO_INCREMENT COMMENT '单据号',
	private int storagedetailno;//	varchar(20) COMMENT	'N	Y	Y		引用采购入库单单号',
	private String materielid;//	int COMMENT	'N物料ID',
	private String materielname;//	Varchar(50) COMMENT	'N				物料名称',
	private String units;//	Varchar(20) COMMENT	'N				单位',
	private int amount;//	int	COMMENT 'N				数量',
	private double unitprice;//	DOUBLE COMMENT	'N				单价',
	private double moneys;//	DOUBLE COMMENT	'N				金额',
	private int enables;//	int	COMMENT			'默认1不删除，0：删除	标识是否删除',
	public stockstoragedetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public stockstoragedetails(int id, int storagedetailno, String materielid, String materielname, String units,
			int amount, double unitprice, double moneys, int enables) {
		super();
		this.id = id;
		this.storagedetailno = storagedetailno;
		this.materielid = materielid;
		this.materielname = materielname;
		this.units = units;
		this.amount = amount;
		this.unitprice = unitprice;
		this.moneys = moneys;
		this.enables = enables;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoragedetailno() {
		return storagedetailno;
	}
	public void setStoragedetailno(int storagedetailno) {
		this.storagedetailno = storagedetailno;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getMaterielname() {
		return materielname;
	}
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getMoneys() {
		return moneys;
	}
	public void setMoneys(double moneys) {
		this.moneys = moneys;
	}
	public int getEnables() {
		return enables;
	}
	public void setEnables(int enables) {
		this.enables = enables;
	}
	
	
}
