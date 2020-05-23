package com.slq.pojo.production;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/***
 * 生产计划总表
 * @author 孙陆泉
 *
 */
@Data
@TableName("production_plan")
public class ProductionPlan implements Serializable{
	
	private static final long serialVersionUID=1L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String production_id;
	private String designer;//设计人
	private String production_reason;//生产理由
	private String register;//登记人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date register_time;//登记时间
	private String checker;//
	private String check_suggestion;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;
	private Integer check_tag;
	private Integer production_state;
	private String remark;//备注
	@TableField(exist = false)
	private List<ProductionPlanDetail> productionPlanDetails;
	@TableField(exist = false)
	ProductionPlanDetail productionPlanDetail;
}
