package com.slq.util;

import java.io.Serializable;

import lombok.Data;
@Data
public class ManufactureChart implements Serializable{

	private String year_month;
	private String product_id;
	private String product_name;
	private Float price_sum;//某一个月产品生产的实际成本
	private Float real_material_price_sum;//某一个月的月产品生产实际物料成本
	private Float real_labour_price_sum;//某一个月的月产品生产实际工时成本
	private Integer tested_amount_sum;//某一个月的月产品生产数量
	private Integer amount_sum;//设计
	private Float material_price_sum;//设计
	private Float labour_price_sum;//设计
}
