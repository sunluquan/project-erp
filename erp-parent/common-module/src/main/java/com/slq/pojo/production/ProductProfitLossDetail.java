package com.slq.pojo.production;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 工序损益详细单
 * @author 孙陆泉
 *
 */
@Data
@TableName("product_profit_loss_detail")
public class ProductProfitLossDetail  implements Serializable{

	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer parent_id;
	private String product_id;
	private String product_name;
	private Float cost_price;//单价
	private Integer profit_loss_amount;//申领退回数量
	private Float subtotal;//总价
}
