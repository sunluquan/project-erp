package com.slq.pojo.warehouse;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class Safetystock implements Serializable{
  private Integer id;
  private String safetystockId;
  private String productId;
  private Integer minamount;
  private Integer maxamount;
  private Integer sid;
  private Integer maxCapacityAmount;
  private String storeUnit;
  private String register;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date registertime;
  private String checker;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date checktime;
  private String checktag;
  private String configStatus;
  private String changeStatus;
  private String product_id;
  private String product_name;
  private String product_rank;
  private String product_unit;
  private double realCostPrice;
  private String kindName;
  private String useName;
  private String storeName;
  private String storeAddress;
  private Integer amount;
  private Integer qwe;
  private Integer qwes;
}
