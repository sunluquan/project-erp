package com.slq.pojo.production;

<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> warehouse
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/***
 * 产品类别表
 * @author 孙陆泉
 *
 */
@TableName("product_kind")
@Data
<<<<<<< HEAD
public class ProductKind implements Serializable{

	private static final long serialVersionUID=1L;
=======
public class ProductKind {

>>>>>>> warehouse
	@TableId(type = IdType.AUTO)
	private Integer id;
	//父级产品类别编号
	private Integer parentId;
	//产品种类名称
	private String kindName;
	//备注
<<<<<<< HEAD
	@TableField(value = "`describe`")
	private String describe;
	
	private Integer state;
	@TableField(exist = false)
	private ProductKind parent;
=======
	private String describe;
>>>>>>> warehouse
	@TableField(exist = false)//不是数据库中的字段
	private List<ProductKind> children;
	public void setChildren(List<ProductKind> children) {
		if(children==null||children.isEmpty()) {
			children=null;
		}
		this.children = children;
	}
	
}
