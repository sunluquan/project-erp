package com.slq.pojo.production;
/***
 * 工序的描述表
 * @author 孙陆泉
 *
 */

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@Data
@TableName("product_process")
public class ProductProcess implements Serializable{

	@TableId(type = IdType.AUTO)
	private Integer id;
	private String processName;
	private String message;
	//有值的情况下默认查询的时候 会默认加上这个条件
	@TableField(whereStrategy = FieldStrategy.DEFAULT)
	private Integer state=1;//1可用状态 2不可用
	/***
	 * CREATE TABLE `product_process`(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '',
	processName VARCHAR(50) NOT NULL COMMENT '工序名称',
	message VARCHAR(200) DEFAULT NULL COMMENT '工序描述',
	state INT DEFAULT 1 COMMENT '1可用 2不可用'
)COMMENT '工序设计表 就是有那些工序' DEFAULT CHARSET utf8;
	 */
}
