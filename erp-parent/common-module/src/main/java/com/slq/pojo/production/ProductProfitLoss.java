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
 * 产品损益表
 * @author 孙陆泉
 *
 */
@Data
@TableName("product_profit_loss")
public class ProductProfitLoss implements Serializable{
	
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String manufacture_id;
	private String product_id;//产品编号
	private String product_name;//产品名称
	private String process_id;//工序编号
	private Integer production_process_id;//生产工序编号
	private String process_name;//工序名称
	private String responsible_person;//负责人
	private String remark;
	private String register;//
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date register_time;
	private String checker;
	private String check_suggestion;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date check_time;
	private Integer check_tag;//check_tag '审核状态 0审核中 1审核已通过 2审核未通过'
	@TableField(exist = false)
	private List<ProductProfitLossDetail> productProfitLossDetails; 
}
