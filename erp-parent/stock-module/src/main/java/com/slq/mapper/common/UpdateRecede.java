package com.slq.mapper.common;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
/**
 * 修改退货单
 * @author lqy
 */
import org.apache.ibatis.annotations.Update;

import com.slq.pojo.stock.*;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
public interface UpdateRecede {
	/**
	 * 修改退货状态
	 * @param detSequ
	 */
	@Update(value = {"update recedeDetails set detStatus=0 where detsequ=#{detSequ}"})
	public void updateRecedeReturn(int detSequ);
	@Update(value = {"update recedeDetails set detStatus=1 where detsequ=#{detSequ}"})
	public void updateRecedeNoReturn(int detSequ);
	/**
	 * 修改退货信息
	 */
	@Update(value = {"update recDate=#{recDate},peoId=#{peoId},purveyName=#{purveyName},peoAddress=#{peoAddress},stoId=#{stoId},depotName=#{depotName},aNewStorage=#{aNewStorage},stoPerson=#{stoPerson},stoPersonId=#{stoPersonId},auditingPerson=#{auditingPerson},recremark=#{recRemark} where recId=#{recId}"})
	public void updateRecedeInfo(StockRecede sr);
	//删除退货详细表商品
	@Delete(value = {"delete from recedeDetails where recid=#{recId}"})
	public void deleteRecedeGoods(int recId);
	//添加退货详细表商品
	@Insert({"<script>",
		"insert into recedeDetails values ",
		"<foreach collection='list' item='item' index='index' separator=','>",
		"(#{item.recId},#{item.detSequ},#{item.matId},#{item.matName},#{item.detNum},#{item.detagioPrice},#{item.detDiscount},#{item.detPrice},#{item.detamtMoney},#{item.detDate},#{item.detMark} ) ",
		"</foreach>", "</script>"
		})
	public void insertRecedeGoods(@Param("rd") List<RecedeDetails> rd);
}


