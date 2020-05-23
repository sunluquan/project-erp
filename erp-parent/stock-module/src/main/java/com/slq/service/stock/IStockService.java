package com.slq.service.stock;

import java.util.List;

import com.slq.pojo.production.Product;
import com.slq.pojo.production.ProductKind;
import com.slq.pojo.production.ProductUse;
import com.slq.pojo.stock.*;
import com.slq.pojo.warehouse.Storehouse;



public interface IStockService {

	List<Product> product_add(String a);

	List<ProductUse> use();

	List<ProductKind> pkind();

	int insert_stock(List<stockapply> goodsPictureList);

	List<stockapply> select_stock();

	List<stockapplydetails> select_stockapplydetails(int id);

	int update_stock(int id);

	int update_s(int id);

	List<stockapply> select_stock_id();

	List<provideInfo> provideInfo();

	int insert_stockorder(List<stockorder> goodsPictureList);

	List<stockorder> select_stockorder();

	List<stockapplydetails> select_stockorderdetails(int id);

	int update_stockorder(int id);

	int update_order(int id);

	List<Storehouse> select_storehouse();

	int insert_stockstoragetable(List<stockstoragetable> goodsPictureList);

	List<stockstoragetable> s_stockstoragetable();

	List<stockstoragedetails> select_stockstoragedetails(int id);

	int update_stockstoragetable(int cid);

	List<stockorder> qwe_stockorder();
	
	void insertStockOrder(stockorder stockOrder,List<stockorderdetails> stockOrderDetails);
	
	void insertStockOrderDetails(List<stockorderdetails> stockOrderDetails);
	
	void updateOrderDetails(int stoId);
}
