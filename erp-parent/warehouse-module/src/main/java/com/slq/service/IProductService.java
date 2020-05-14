package com.slq.service;

import java.util.List;

import com.slq.pojo.production.Product;

public interface IProductService {
  List<Product> selectProList();
  Product selectByid(String product_id);
}
