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
 * 产品物料设计
 * @author 孙陆泉
 *
 */
@TableName("product_material_design")
@Data
public class ProductMaterialDesign implements Serializable{

	
	public ProductMaterialDesign(Integer id, String module_describe, String checker, Integer check_tag,
			Date check_time) {
		super();
		this.id = id;
		this.module_describe = module_describe;
		this.checker = checker;
		this.check_tag = check_tag;
		this.check_time = check_time;
	}
	public ProductMaterialDesign() {
		super();
	}
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String design_id;
	private String product_id;//产品编号
	@TableField(exist = false)
	private String product_name;//产品名称
	private String designer;//设计人
	private String module_describe;//设计要求
	private String register;//登记人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date register_time;//登记事件
	private String checker;//检查关
	private Integer check_tag;//0等待审核 1审核通过 2审核未通过
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;
	private String changer;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date change_time;
	@TableField(exist = false)
	private List<MaterialDesignDetail> materials;
}
