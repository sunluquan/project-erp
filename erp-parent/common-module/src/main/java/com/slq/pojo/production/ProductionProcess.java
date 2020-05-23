package com.slq.pojo.production;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
/***
 * 生产工序
 * @author 孙陆泉
 *
 */
@TableName("production_process")
@Data
public class ProductionProcess implements Serializable{

	private final static long serialVersionUID=3833269558470912480L;
	public ProductionProcess(Integer details_number, String process_id, String process_name, Float labour_hour_amount,
			Float subtotal, Float module_subtotal, Integer demand_amount,Float cost_price, Integer process_finish_tag,
			Integer process_transfer_tag) {
		super();
		this.details_number = details_number;
		this.process_id = process_id;
		this.process_name = process_name;
		this.labour_hour_amount = labour_hour_amount;
		this.subtotal = subtotal;
		this.module_subtotal = module_subtotal;
		this.demand_amount=demand_amount;
		this.cost_price = cost_price;
		this.process_finish_tag = process_finish_tag;
		this.process_transfer_tag = process_transfer_tag;
	}
	public ProductionProcess() {
		super();
	}
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer parent_id;//manufactureSum的id
	private Integer details_number;
	private String process_id;//工序编号
	private String process_name;//工序名称
	private String time_unit;//工时单位
	private Float labour_hour_amount;//设计工时数
	private Float real_labour_hour_amount;//实际工时数
	private Float subtotal;//设计工时成本
	private Float real_subtotal;//实际工时成本
	private Float module_subtotal;//设计物料成本
	private Float real_module_subtotal;//实际物料成本
	private Float cost_price;//单位工时成本
	private Integer demand_amount;//工序投产数量
	private Integer real_amount;//工序合格数量
	private Integer process_finish_tag;//工序完成标志0: 未开始1: 已完成2: 未完成 3: 已审核 4审核中 5审核未通过
	private Integer process_transfer_tag;//工序交接标志0: 未交接1: 已审核(已交接) 2: 未审核 
	@TableField(exist = false)
	private ProductionProcessCourse productionProcessCourse;
	//生产工序物料
	@TableField(exist = false)
	private List<ProductionProcessMaterial> productionProcessMaterials;
}
