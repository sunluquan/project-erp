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
 * 生产工序过程表
 * @author 孙陆泉
 *
 */
@TableName("production_process_course")
@Data
public class ProductionProcessCourse implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	public ProductionProcessCourse() {
		super();
	}
	public ProductionProcessCourse(Integer parent_id, String process_id, String process_name, 
			String time_unit, Float cost_price, Float subtotal) {
		super();
		this.parent_id = parent_id;
		this.process_id = process_id;
		this.process_name = process_name;
		this.time_unit = time_unit;
		this.cost_price = cost_price;
		this.subtotal = subtotal;
	}
	@TableId
	private Integer id;
	private Integer parent_id;//父级序号 也和生产总表挂钩
	private Integer details_number;
	private String process_id;//工序编号
	private String process_name;//工序名称
	private Float labour_hour_amount;//本次工时数
	private String time_unit;
	private Float cost_price;//单位工时成本
	private Float subtotal;//工时成本小计
	private Integer reg_count;//登记次数
	private String responsible_person;//负责人
	private String register;//登记人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date register_time;//登记时间
	private String checker;//审核人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;//审核时间
	private Integer check_tag;//0审核中 1审核已通过 2审核未通过
	private String procedure_describe;//描述
	@TableField(exist = false)//生产工序物料
	private List<ProductionProcessMaterialCourse> productionProcessMaterialCourses;
	@TableField(exist =false)
	private List<ProductionProcessMaterial> productionProcessMaterials;
}
