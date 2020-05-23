package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 生产工序物料
 * @author 孙陆泉
 *
 */
@Data
@TableName("production_process_material")
public class ProductionProcessMaterial implements Serializable{

	private final static long serialVersionUID=1L;
	public ProductionProcessMaterial(String product_id, String product_name, Float cost_price, Integer amount,
			Float subtotal) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.cost_price = cost_price;
		this.amount = amount;
		this.subtotal = subtotal;
	}
	public ProductionProcessMaterial( String product_name, Float cost_price, Integer amount,
			Float subtotal) {
		super();
		this.product_name = product_name;
		this.cost_price = cost_price;
		this.amount = amount;
		this.subtotal = subtotal;
	}
	public ProductionProcessMaterial() {
		super();
	}
	@TableId(type=IdType.AUTO)
	private Integer id;
	private Integer parent_id;//productionProcess的id
	private Integer process_number;//工序序号  x
	private String product_id;//物料编号
	private String product_name;//物料名称
	private Float cost_price;//物料单价
<<<<<<< HEAD
	//当前工序的单个产品的物料设计数量 如1个产品的组装工序需要2个物料  则single_material_design_amount=2
	//不管这个产品的设计数量有多少
	private Integer  single_material_design_amount;
=======
>>>>>>> warehouse
	private Integer amount;//设计数量
	private Integer renew_amount;//已从库存领料数量
	private Integer real_amount;//实际使用数量
	@TableField(exist = false)
	private Integer use_amount=0;//本次使用数量
	private Float subtotal;//设计物料成本小计
	private Float real_subtotal;//实际物料成本小计
	@TableField(exist =false)
	private ProductionProcessMaterialCourse productionProcessMaterialCourse;
	
	
}
