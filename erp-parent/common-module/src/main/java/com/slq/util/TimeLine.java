package com.slq.util;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TimeLine implements Serializable{

	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date registerTime;//登记时间
	
	private String registerContent;//登记内容
	
	private String register;//登记人
	
	private String checker;//审核人
	
	private Date checkTime;//
	
	private String describe;//描述
}
