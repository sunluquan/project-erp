package com.slq.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.slq.util.OrderingRule;

import lombok.Data;

@Data
public class Params implements Serializable{

	//private static final long serialVersionUID = 1L;
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	private String keyWord;

	private OrderingRule orderingRule;//排序规则
	
	private String[] dateTimeRange;
	//补充的参数
	private Map<String, Object> replenishParams;

	public Map<String, Object> getReplenishParams() {
		return replenishParams;
	}

	public void setReplenishParams(String key,Object value) {
		if(this.replenishParams==null) {
			this.replenishParams=new HashMap<String, Object>();
		}
		this.replenishParams.put(key, value);
	}
	
	
	
}
