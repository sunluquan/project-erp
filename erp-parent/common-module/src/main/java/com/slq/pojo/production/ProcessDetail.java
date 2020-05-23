package com.slq.pojo.production;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 工序设计单详细表
 * @author 孙陆泉
 *
 */
@Data
@TableName("process_detail")
public class ProcessDetail implements Serializable{

	private static final long serialVersionUID=1L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer parent_id;//指定与ProcessDesign表关联的id
	private Integer details_number;//工序序号  执行的工序顺序
	private String process_id;//指定与productProcess表相关联
	private float  labour_hour_amount;//工时数  
	private String time_unit;//工序时间单位
	private float cost_price;//时间单位工时价格
	private Integer material_tag;//当前工序物料标志0: 未设计1: 已设计
	private Integer material_change_tag;//当前工序物料变更标志0: 未变更1: 已变更
	@TableField(exist = false)
	private ProductProcess process;
	@TableField(exist = false)
	private List<ProcessMaterialDetail> processMaterials;//工序的物料
	@TableField(exist = false)
	private List<MaterialDesignDetail> materials;
	//private String process_describe;//工序描述
}
