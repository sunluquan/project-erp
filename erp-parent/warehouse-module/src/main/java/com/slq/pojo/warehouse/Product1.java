package com.slq.pojo.warehouse;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class Product1 implements Serializable{
   private Integer id;
   private String productId;
   private String productName;
   private Integer product_kind_id;
   private Integer useType;
   private String initial;
   private String productRank;
   private String productUnit;   
   private Integer list_price;
   private Integer cost_price;
   private Integer REAL_COST_PRICE;
   private String register;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")   
   private Date create_time;
   private String checker;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
   private Date check_time;
   private Integer check_tag;
   private Integer material_tag;
   private Integer process_ag;
   private String inventory_allocation_tag;
   private String changer;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
   private Date change_time;
   private String message;
   private String FACTORY_NAME;
   private String kindName;
   private String useName;

  
}
