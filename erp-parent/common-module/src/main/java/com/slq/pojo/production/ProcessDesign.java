package com.slq.pojo.production;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/***
 * 产品工序设计总表
 * @author 孙陆泉
 *
 */
@Data
@TableName("product_process_design")
public class ProcessDesign implements Serializable{
	
	private static final long serialVersionUID=1L;
	public ProcessDesign(Integer id, String checker, Date check_time, Integer check_tag) {
		super();
		this.id = id;
		this.checker = checker;
		this.check_time = check_time;
		this.check_tag = check_tag;
	}
	
	public ProcessDesign() {
		super();
	}
	public ProcessDesign(Integer id, String checker, Date check_time, String check_suggestion, Integer check_tag) {
		super();
		this.id = id;
		this.checker = checker;
		this.check_time = check_time;
		this.check_suggestion = check_suggestion;
		this.check_tag = check_tag;
	}
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String process_id;//工序设计单号
	private String product_id;//产品编号
	@TableField(exist = false)
	private Product product;//
	private float manhour_cost_price_sum;//工时总成本  可能不需要字段
	private float material_cost_price_sum;//物料总成本  可能不需要
	private String designer;
	private String register;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date register_time;
	private String checker;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;
	private String check_suggestion;//审核没通过时候的意见
	private Integer check_tag;//审核状态 0等待审核 1审核通过 2审核未通过
	private String changer;//更改人
	private Date change_time;//改变时件
	private Integer process_material_tag;//工序物料状态 -1为默认的也就是没有设计 0工序物料设计再审核中 1物料已经设计 2驳回状态
	private String message;//设计要求
	@TableField(exist = false)
	private List<ProcessDetail> processDetails;
	@TableField(exist = false)
	private List<ProcessMaterialDetail> processMaterialDetails;
	
}
