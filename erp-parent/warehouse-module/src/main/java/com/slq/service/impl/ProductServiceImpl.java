package com.slq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slq.pojo.production.Product;
import com.slq.service.IProductService;

@Service 
//@Transactional
//@CacheConfig(cacheNames = "myCache")
public class ProductServiceImpl1 implements IProductService{
 

@Override
public List<Product> selectProList() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Product selectByid(String product_id) {
	// TODO Auto-generated method stub
	return null;
}
}
