package com.slq.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.slq.pojo.production.Product;
import com.slq.pojo.production.ProductKind;
import com.slq.pojo.production.ProductUse;
import com.slq.pojo.stock.*;
import com.slq.pojo.warehouse.Storehouse;


@Mapper
public interface StockMapper1 {

	List<stockapply> select_stock_id();

	List<Product> product_add(@Param("name") String d);

	List<ProductUse> use();

	List<ProductKind> pkind();

	int insert_stocka(@Param("appid") int appid,@Param("apptype") String apptype,@Param("je") int je);
	
	int insert_stockapplydetails(int appid, String apdmateridlid, int mobile, double list_price, double cost_price,
			double real_cost_price, double list_price2);

	void insert_stockapplydetails(@Param("apdrows")int appid,@Param("apdmateridlid") String parseInt,@Param("apdmateridlname") String product_name,@Param("apdunit") int mobile,@Param("list_price") double list_price,
			@Param("cost_price")double cost_price,@Param("REAL_COST_PRICE") double real_cost_price,@Param("apdintomoney") double list_price2);

	List<stockapply> select_stock();

	List<stockapplydetails> select_stockapplydetails(@Param("id")int id);

	int update_stock(@Param("id")int id);

	int update_s(@Param("id")int id);

	List<provideInfo> select_provideInfo();

	int select_stockorder(@Param("stoid")int stoid,@Param("stopeoname") String stopeoname,@Param("stodept") String stodept,@Param("je") int je);
	int insert_stockorderdetails(@Param("stoid")int stoid,@Param("sodmaterielid") String apdmateridlid,@Param("sodmaterielname") String apdmateridlname,@Param("sodamoun") int apdunit,@Param("sodsum") double mobile);
	    //insert_stockorderdetails
	List<stockorder> s_stockorder();

	List<stockapplydetails> select_stockorderdetails(@Param("id")int id);

	int update_stockorder(@Param("id")int id);

	int update_order(@Param("id")int id);

	List<Storehouse> select_storehouse();

	int select_stockstoragetable(@Param("cid")int cid,@Param("stockstorageno")int stockstorageno,@Param("purveyname") String purveyname,@Param("depotname") String depotname, @Param("stodept")String stodept, @Param("je")int je);

	List<stockstoragetable> s_stockstoragetable();
	    //insert_stockorderdetails
	int insert_stockstoragedetails(@Param("storagedetailno")int stockstorageno,@Param("materielid") String sodmaterielid,@Param("materielname") String sodmaterielname,@Param("amount") int sodamoun,
			@Param("moneys") double sodsum);

	List<stockstoragedetails> select_stockstoragedetails(@Param("id")int id);

	int update_stockstoragetable(@Param("cid")int cid);

	int update_qwe(@Param("cid")int cid);

	List<stockorder> qwe_stockorder();

	int update_q(@Param("cid")int cid);
	//订单退货主表批量添加
	void insertStockOrder(stockorder stockOrder);
	//订单退货详细表批量添加
	void insertStockOrderDetails(List<stockorderdetails> stockOrderDetails);
	//修改订单状态
	void updateOrderDetails(int stoId);
}
