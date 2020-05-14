package com.slq.pojo.production;
/***
 * 产品设计表
 * @author 孙陆泉
 *
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName("product")
public class Product implements Serializable{
 
	private static final long serialVersionUID=-6900261053238870093L;
	public Product(Integer id , String checker, Date check_time, Integer check_tag) {
		super();
		this.id=id;
		this.checker = checker;
		this.check_time = check_time;
		this.check_tag = check_tag;
	}

	public Product() {
		super();
	}

	private Integer id;//序号
	
	private String product_id;//编号
	
	private String product_name;//产品名称
	
	private Integer product_kind_id;//最终的类别编号
	@TableField(exist = false)
	private String kindName;
	
	private Integer use_type;//用途编号
	@TableField(exist=false)
	private String useName;
	
	private String initial;//首字母
	
	private String product_rank;//商品的档次级别
	
	private String product_unit;//产品的单位
	
	private float list_price;//市场价格
	
	private float cost_price;//计划成本价
	
	private float real_cost_price;//实际的成本价
	
	private String register;//登记人账号
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date create_time;//登记时间
	
	private String checker;//审核人账号
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;//审核时间
	
	private Integer check_tag;//审核状态 0未审核 1审核通过 2审核失败
	
	private Integer material_tag;//-1为物料从未设计过 0 物料审核中或者驳回状态 1物料已经设计
	
	//private Integer procedure_tag;//废除0工序未设计 1工序已经设计
	private Integer process_tag;//-1为工序从未设计 0 工序设计审核中或者驳回 1工序已经设计
	
	private Integer inventory_allocation_tag;//库存分配情况 0未分配  1已分配
	
	private String changer;//变更人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date change_time;//变更时间
	
	private String message;//信息
	
	private String factory_name;
}
