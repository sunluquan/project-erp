package com.slq.pojo.production;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/***
 * 制造总单
 * @author 孙陆泉
 *
 */
@TableName("manufacture_sum")
@Data
public class ManufactureSum implements Serializable{

	private static final long serialVersionUID=1L;
	@TableId
	private Integer id;
	private String manufacture_id;
	private String product_id;
	private String product_name;
	private int amount;
	private Integer tested_amount;
	private String production_id_group;//x
	private Float material_cost_price_sum;//设计物料总价
	private Float real_material_cost_price_sum;//实际的物料总价
	private Float labour_cost_price_sum;//设计的工时总价
	private Float real_labour_cost_price_sum;//实际的工时总价
	private String designer;//制定人
	private String register;//登记人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date register_time;
	private String checker;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;
	private String check_suggestion;
	private String remark;
	private Integer check_tag;//审核标志0: 等待审核1: 审核通过2: 审核不通过
	private Integer manufacture_procedure_tag;//生产过程标志
	
	@TableField(exist = false)
	private Float real_cost_price;
	@TableField(exist = false)
	private ProcessDesign processDesign;
	@TableField(exist = false)
	private List<ProcessDetail> processDetails;//工序详细
	@TableField(exist = false)
	private List<ProductionPlanDetail> productionPlanDetails;//生产的产品集合
	//生产工序类
	@TableField(exist = false)
	private List<ProductionProcess> productionProcess;
	//生产工序过程类
	@TableField(exist = false)
	private List<ProductionProcessCourse> productionProcessCourses;
	//新增登记的时候使用
	@TableField(exist = false)
	private ProductionProcess productionProcess1;
	@TableField(exist = false)
	private ProductionProcessCourse productionProcessCourse;
	
}
