package com.slq.pojo.stock;

public class provideInfo {
	private int peoid;// int AUTO_INCREMENT COMMENT '供应商编号',
	private String peoname;// varchar(50) not null COMMENT '供应商全称',
	private String peoshortname;// varchar(50) COMMENT	'供应商简称',
	private String peoascription;//	varchar(50)	COMMENT			'帐款归属',
	private int peopeeid;//	int	COMMENT	'供应商类型表id',
	private String peopeename;//	Varchar(50) COMMENT	'供应商类型表名称（新增）',
	private String peoarea;//	Varchar(50) COMMENT	'地区',
	private String peoprincipal;//	Varchar(50)				COMMENT	'负责人',
	private String peolinkman;//	Varchar(50)				COMMENT	'联系人',
	private String peophone;// 	Varchar(50)		COMMENT			'联系电话',
	private String peobankaccounts;//	Varchar(50)				COMMENT	'银行帐号',
	private String peoemail;//	Varchar(50)				COMMENT	'电子邮件',
	private double peoremained;//u	DOUBLE			COMMENT		'剩余额度',
	private double peomonthreckoning;//	DOUBLE	COMMENT			'31	每月结帐日',
	private double Peoqcprepaymoney;//	DOUBLE		COMMENT			'期初预付款',
	private double peoqcdealmoney;//	DOUBLE			COMMENT		'期初应付款',
	private double peoqmprepaymoney;//	DOUBLE		COMMENT			'期末预付款',
	private double peoqmdealmoney;//	DOUBLE			COMMENT		'期末应付款',
	private String peoremark;//	Varchar(2000)				COMMENT	'备注',
	public provideInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public provideInfo(int peoid, String peoname, String peoshortname, String peoascription, int peopeeid,
			String peopeename, String peoarea, String peoprincipal, String peolinkman, String peophone,
			String peobankaccounts, String peoemail, double peoremained, double peomonthreckoning,
			double peoqcprepaymoney, double peoqcdealmoney, double peoqmprepaymoney, double peoqmdealmoney,
			String peoremark) {
		super();
		this.peoid = peoid;
		this.peoname = peoname;
		this.peoshortname = peoshortname;
		this.peoascription = peoascription;
		this.peopeeid = peopeeid;
		this.peopeename = peopeename;
		this.peoarea = peoarea;
		this.peoprincipal = peoprincipal;
		this.peolinkman = peolinkman;
		this.peophone = peophone;
		this.peobankaccounts = peobankaccounts;
		this.peoemail = peoemail;
		this.peoremained = peoremained;
		this.peomonthreckoning = peomonthreckoning;
		Peoqcprepaymoney = peoqcprepaymoney;
		this.peoqcdealmoney = peoqcdealmoney;
		this.peoqmprepaymoney = peoqmprepaymoney;
		this.peoqmdealmoney = peoqmdealmoney;
		this.peoremark = peoremark;
	}
	public int getPeoid() {
		return peoid;
	}
	public void setPeoid(int peoid) {
		this.peoid = peoid;
	}
	public String getPeoname() {
		return peoname;
	}
	public void setPeoname(String peoname) {
		this.peoname = peoname;
	}
	public String getPeoshortname() {
		return peoshortname;
	}
	public void setPeoshortname(String peoshortname) {
		this.peoshortname = peoshortname;
	}
	public String getPeoascription() {
		return peoascription;
	}
	public void setPeoascription(String peoascription) {
		this.peoascription = peoascription;
	}
	public int getPeopeeid() {
		return peopeeid;
	}
	public void setPeopeeid(int peopeeid) {
		this.peopeeid = peopeeid;
	}
	public String getPeopeename() {
		return peopeename;
	}
	public void setPeopeename(String peopeename) {
		this.peopeename = peopeename;
	}
	public String getPeoarea() {
		return peoarea;
	}
	public void setPeoarea(String peoarea) {
		this.peoarea = peoarea;
	}
	public String getPeoprincipal() {
		return peoprincipal;
	}
	public void setPeoprincipal(String peoprincipal) {
		this.peoprincipal = peoprincipal;
	}
	public String getPeolinkman() {
		return peolinkman;
	}
	public void setPeolinkman(String peolinkman) {
		this.peolinkman = peolinkman;
	}
	public String getPeophone() {
		return peophone;
	}
	public void setPeophone(String peophone) {
		this.peophone = peophone;
	}
	public String getPeobankaccounts() {
		return peobankaccounts;
	}
	public void setPeobankaccounts(String peobankaccounts) {
		this.peobankaccounts = peobankaccounts;
	}
	public String getPeoemail() {
		return peoemail;
	}
	public void setPeoemail(String peoemail) {
		this.peoemail = peoemail;
	}
	public double getPeoremained() {
		return peoremained;
	}
	public void setPeoremained(double peoremained) {
		this.peoremained = peoremained;
	}
	public double getPeomonthreckoning() {
		return peomonthreckoning;
	}
	public void setPeomonthreckoning(double peomonthreckoning) {
		this.peomonthreckoning = peomonthreckoning;
	}
	public double getPeoqcprepaymoney() {
		return Peoqcprepaymoney;
	}
	public void setPeoqcprepaymoney(double peoqcprepaymoney) {
		Peoqcprepaymoney = peoqcprepaymoney;
	}
	public double getPeoqcdealmoney() {
		return peoqcdealmoney;
	}
	public void setPeoqcdealmoney(double peoqcdealmoney) {
		this.peoqcdealmoney = peoqcdealmoney;
	}
	public double getPeoqmprepaymoney() {
		return peoqmprepaymoney;
	}
	public void setPeoqmprepaymoney(double peoqmprepaymoney) {
		this.peoqmprepaymoney = peoqmprepaymoney;
	}
	public double getPeoqmdealmoney() {
		return peoqmdealmoney;
	}
	public void setPeoqmdealmoney(double peoqmdealmoney) {
		this.peoqmdealmoney = peoqmdealmoney;
	}
	public String getPeoremark() {
		return peoremark;
	}
	public void setPeoremark(String peoremark) {
		this.peoremark = peoremark;
	}
}
