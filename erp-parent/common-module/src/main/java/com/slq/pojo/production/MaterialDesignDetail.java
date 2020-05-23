package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 产品物料设计明细
 * @author 孙陆泉
 *
 */
@Data
@TableName("material_design_detail")
public class MaterialDesignDetail implements Serializable{
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer parent_id;
	private String product_id;
	@TableField(exist = false)
	private String product_name;
	@TableField(exist = false)
	private Product product;
	private Integer amount;//数量
	//private float cost_price;//价格
	private Integer residual_amount;//可用数量
	@TableField(exist = false)
	private String product_unit;
	@TableField(exist=false)
	private float cost_price;
	@TableField(exist=false)
	private String useName;
}
