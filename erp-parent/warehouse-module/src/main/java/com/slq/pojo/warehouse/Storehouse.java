package com.slq.pojo.warehouse;

import java.io.Serializable;

import lombok.Data;

@Data
public class Storehouse implements Serializable{
	private Integer sid;
	private String  storeName;
	private String  storeAddress;
	private Integer useType;
	private Integer storeStatus;
	private String  storeRemark;
	private String productId;
	
}
