package com.slq.pojo.warehouse;

import java.io.Serializable;

import lombok.Data;

@Data
public class Storehouse implements Serializable{
	private Integer sid;
	private String  storeName;
	private String  storeAddress;
	private Integer use_type;
	private Integer maxamount;
	private Integer storeStatus;//0可用 1不可用
	private String  storeRemark;//备注
	private Integer  id;
	
}
