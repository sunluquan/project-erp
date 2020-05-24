package com.slq.pojo.warehouse;

import java.io.Serializable;

import lombok.Data;

@Data
public class Storehouse implements Serializable{
	private Integer sid;
	private String  storeName;
	private String  storeAddress;
	private Integer use_type;
<<<<<<< HEAD
<<<<<<< HEAD
=======
	private Integer maxamount;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	private Integer storeStatus;//0可用 1不可用
	private String  storeRemark;//备注
	private Integer  id;
	
}
