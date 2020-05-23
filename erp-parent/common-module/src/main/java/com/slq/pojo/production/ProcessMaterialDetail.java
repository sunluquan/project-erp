package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
/***
 * 工序物料详细
 * @author 孙陆泉
 * 
 */
@Data
@TableName("process_material_detail")
public class ProcessMaterialDetail implements Serializable{

	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer parent_id;//工序设计详细表
	private Integer details_number;//物料序号  x
	private Integer material_id;//物料id
	private Integer useAmount;//使用数量
	@TableField(exist = false)
	private String product_name;
	@TableField(exist = false)
	private String product_id;
	@TableField(exist = false)
	private String useName;
	@TableField(exist = false)
	private String product_unit;
	@TableField(exist = false)
	private Integer amount;
	@TableField(exist = false)
	private Integer residual_amount;
	@TableField(exist = false)
	private float cost_price;
}
