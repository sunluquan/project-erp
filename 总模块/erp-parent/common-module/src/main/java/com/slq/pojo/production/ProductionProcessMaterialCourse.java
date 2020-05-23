package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("production_process_material_course")
public class ProductionProcessMaterialCourse implements Serializable{

	private static final long serialVersionUID=1L;
	@TableId
	private Integer id;
	private Integer parent_id;
	private Integer details_number;//序号x
	private String product_id;
	private String product_name;
	private Float cost_price;//单个物料成本
	private Integer use_amount;//使用数量
	private Float subtotal;//物料成本小计
}
