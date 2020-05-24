package com.slq.pojo.production;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/***
 * 产品用途表
 * @author 孙陆泉
 *
 */
@TableName("product_use")
@Data

public class ProductUse {
	
	@TableId(type = IdType.AUTO)
	private Integer id;
	//用途的名称
	private String useName;
	//用途的描述
	@TableField(value = "`describe`")
	private String describe;
	
	private Integer parent_id;
	
	
}
