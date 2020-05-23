package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("`use`")
public class Use implements Serializable{
	
	@TableId(type = IdType.AUTO)
	private Integer id;
	//用途的名称
	private String useName;
	//用途的描述
	@TableField(value = "`describe`")
	private String describe;
	
}
