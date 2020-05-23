package com.slq.service.stock.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.ParseInfo;
import com.slq.mapper.common.*;
import com.slq.pojo.User;
import com.slq.pojo.production.Product;
import com.slq.pojo.production.ProductKind;
import com.slq.pojo.production.ProductUse;
import com.slq.pojo.stock.*;
import com.slq.pojo.warehouse.Storehouse;
import com.slq.service.stock.*;
import com.slq.util.ActiveUser;
@Service
@Transactional
@CacheConfig(cacheNames = "myCache")
public class StockServicelmpl implements IStockService {
	@Autowired
	private StockMapper1 stockMapper;
	@Override
	public List<Product> product_add(String a) {
		// TODO Auto-generated method stub
		System.out.println("fds");
		return stockMapper.product_add(a);
	}
	@Override
	public List<ProductUse> use() {
		// TODO Auto-generated method stub
		System.out.println(stockMapper.use().get(0).getUseName()+"的啥借口");
		return stockMapper.use();
	}
	@Override
	public List<ProductKind> pkind() {
		// TODO Auto-generated method stub
		return stockMapper.pkind();
	}
	@Override
	public int insert_stock(List<stockapply> goodsPictureList) {
		// TODO Auto-generated method stub
		stockapply a=goodsPictureList.get(0);
		List<Product> pro=a.getProduct();
		int b=stockMapper.insert_stocka(a.getAppid(),a.getApptype(),a.getJe());
		if(b>0) {
			for (int c=0;c<a.getProduct().size();c++) {
				
				stockMapper.insert_stockapplydetails(a.getAppid(),pro.get(c).getProduct_id(),pro.get(c).getProduct_name(),pro.get(c).getMobile(),pro.get(c).getList_price(),pro.get(c).getCost_price(),pro.get(c).getReal_cost_price(),pro.get(c).getList_price());
			}
		}
		return b;
	}
	@Override
	public List<stockapply> select_stock() {
		// TODO Auto-generated method stub
		return stockMapper.select_stock();
	}
	@Override
	public List<stockapplydetails> select_stockapplydetails(int id) {
		// TODO Auto-generated method stub
		return stockMapper.select_stockapplydetails(id);
	}
	@Override
	public int update_stock(int id) {
		// TODO Auto-generated method stub
		return stockMapper.update_stock(id);
	}
	public int update_s(int id) {
		// TODO Auto-generated method stub
		return stockMapper.update_s(id);
	}
	@Override
	public List<stockapply> select_stock_id() {
		// TODO Auto-generated method stub
		return stockMapper.select_stock_id();
	}
	@Override
	public List<provideInfo> provideInfo() {
		// TODO Auto-generated method stub
		return stockMapper.select_provideInfo();
	}
	@Override
	public int insert_stockorder(List<stockorder> goodsPictureList) {
		stockorder a=goodsPictureList.get(0);
		List<stockapplydetails> b=a.getStockapplydetails();
		int con=stockMapper.select_stockorder(a.getStoid(),a.getStopeoname(),a.getStodept(),a.getJe());
		if(con>0) {
			System.out.println(b.size()+"jgdskjghajkgfkj");
           for (int c=0;c<b.size();c++) {
				stockMapper.insert_stockorderdetails(a.getStoid(),b.get(c).getApdmateridlid(),b.get(c).getApdmateridlname(),b.get(c).getApdunit(),b.get(c).getMobile());
			}
		}
		return con;
	}
	@Override
	public List<stockorder> select_stockorder() {
		// TODO Auto-generated method stub
		return stockMapper.s_stockorder();
	}
	@Override
	public List<stockapplydetails> select_stockorderdetails(int id) {
		// TODO Auto-generated method stub
		return stockMapper.select_stockorderdetails(id);
	}
	@Override
	public int update_stockorder(int id) {
		// TODO Auto-generated method stub
		return stockMapper.update_stockorder(id);
	}
	@Override
	public int update_order(int id) {
		// TODO Auto-generated method stub
		return stockMapper.update_order(id);
	}
	@Override
	public List<Storehouse> select_storehouse() {
		// TODO Auto-generated method stub
		return stockMapper.select_storehouse();
	}
	@Override
	public int insert_stockstoragetable(List<stockstoragetable> goodsPictureList) {
		stockstoragetable a=goodsPictureList.get(0);
		List<stockorderdetails> b=a.getStockorderdetails();
		int con=stockMapper.select_stockstoragetable(a.getCid(),a.getStockstorageno(),a.getPurveyname(),a.getDepotname(),a.getStodept(),a.getJe());
		if(con>0) {
			System.out.println(b.get(0).getSodmaterielid()+"jgdskjghajkgfkj");
           for (int c=0;c<b.size();c++) {
        	   stockMapper.update_q(a.getCid());
				stockMapper.insert_stockstoragedetails(a.getStockstorageno(),b.get(c).getSodmaterielid(),b.get(c).getSodmaterielname(),b.get(c).getSodamoun(),b.get(c).getSodsum());
			}
		}
		return con;
	}
	@Override
	public List<stockstoragetable> s_stockstoragetable() {
		return stockMapper.s_stockstoragetable();
	}
	@Override
	public List<stockstoragedetails> select_stockstoragedetails(int id) {
		return stockMapper.select_stockstoragedetails(id);
	}
	@Override
	public int update_stockstoragetable(int cid) {
		int a=stockMapper.update_stockstoragetable(cid);
		if(a>0) {
			stockMapper.update_qwe(cid);
		}
		return a;
	}
	@Override
	public List<stockorder> qwe_stockorder() {
		return stockMapper.qwe_stockorder();
	}
	@Override
	@Transactional
	public void insertStockOrder(stockorder stockOrder,List<stockorderdetails> stockOrderDetails) {
		User user=((ActiveUser)SecurityUtils.getSubject().getPrincipal()).getUser();
		stockOrder.setStoempstock(user.getUid());
		stockOrder.setStostate(0);
		for(stockorderdetails sod:stockOrderDetails) {
			stockMapper.updateOrderDetails(sod.getSodid());
		}
		stockMapper.insertStockOrder(stockOrder);
		stockMapper.insertStockOrderDetails(stockOrderDetails);
	}
	@Override
	public void insertStockOrderDetails(List<stockorderdetails> stockOrderDetails) {
		stockMapper.insertStockOrderDetails(stockOrderDetails);
	}
	@Override
	public void updateOrderDetails(int stoId) {
		stockMapper.updateOrderDetails(stoId);
	}
}
