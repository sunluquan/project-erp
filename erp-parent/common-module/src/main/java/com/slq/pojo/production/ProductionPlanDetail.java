package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 生产详细表
 * @author 孙陆泉
 *
 */
@Data
@TableName("production_plan_detail")
public class ProductionPlanDetail implements Serializable{
	private static final long serialVersionUID=1L;
	@TableId
	private Integer id;
	private Integer parent_id;
	private String product_id;
	private Integer amount;
	private Integer manufacture_tag;//派工标志0: 未派工1: 已派工
	private String manufacture_id;
	//pu.useName,p.product_name,p.cost_price,p.product_unit
	@TableField(exist = false)
	private String useName;
	@TableField(exist = false)
	private String product_name;
	@TableField(exist = false)
	private Float cost_price;
	@TableField(exist = false)
	private Float real_cost_price;
	@TableField(exist = false)
	private String product_unit;
	@TableField(exist = false)
<<<<<<< HEAD
	private String message;
	@TableField(exist = false)
=======
>>>>>>> warehouse
	private ProductionPlan productionPlan;
	
}
