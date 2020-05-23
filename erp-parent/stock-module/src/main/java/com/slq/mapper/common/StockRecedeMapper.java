package com.slq.mapper.common;
/**
 * 采购退货
 * @author lqy
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.slq.pojo.stock.*;
@Mapper
public interface StockRecedeMapper {
	@Select(value = {"select * from stockrecede where estate=0"})
	@Results(id="selectAll" ,value = {
			@Result(property = "recId",column = "recid"),
			@Result(property = "recDate",column = "recdate"),
			@Result(property = "peoId",column = "peoid"),
			@Result(property = "purveyName",column = "purveyname"),
			@Result(property = "peoAddress",column = "peoaddress"),
			@Result(property = "stoId",column = "stoid"),
			@Result(property = "depotName",column = "depotname"),
			@Result(property = "aNewStorage",column = "anewstorage"),
			@Result(property = "stoPerson",column = "stoperson"),
			@Result(property = "stoPersonId",column = "stopersonid"),
			@Result(property = "stoDept",column = "stodept"),
			@Result(property = "stoDeptId",column = "stodeptid"),
			@Result(property = "auditingPerson",column = "auditingperson"),
			@Result(property = "recRemark",column = "recremark"),
			@Result(property = "eState",column = "estate"),
			@Result(property = "recedeDetails",column = "recId",many = @Many(select = "com.slq.mapper.singleMethod.common.RecedeDetailsMapper.selectAllRecede",fetchType = FetchType.LAZY))
	})
	public List<StockRecede> selectAllStock();
	@Insert(value = {"insert into stockRecede values(${null},${now()},#{peoId},#{purveyName},#{peoAddress},#{stoId},#{depotName},#{anewStorage},#{stoPerson},#{stoPersonId},#{stoDept},#{stoDeptId},#{auditingPerson},#{recRemark},${default})"})
	public void insertStockRecede(StockRecede stockRecede);
	@Insert(value = {"insert into recedeDetails values(${null},#{detSequ},#{matId},#{matName},#{detNum},#{detagioPrice},#{detDiscount},#{detPrice},#{detamtMoney},#{detDate},#{detMark})"})
	public void insertStockRD(RecedeDetails recedeDetails);
	@Update(value = {"update stockRecede set auditingperson=#{auditingPerson},recremark=#{recRemark},estate=#{eState} where recId=#{recId}"})
	public void updateStockOD(StockRecede stockRecede);
	@Update(value = {"update recedeDetails set matid=#{matId},matName=#{matName},detNum=#{detNum},detagioPrice=#{detagioPrice},detdiscount=#{detDiscount},detprice=#{detPrice},detamtMoney=#{detamtMoney},detmark=#{detMark} where detSequ=#{detSequ} and recid=#{recId}"})
	public void updateStockRD(RecedeDetails recedeDetails);
	@Delete(value = {"delete from stockrecede where recId=#{recId}"})
	public void deleteStockOD(int recId);
	@Delete(value = {"delete from recedeDetails where detsequ=#{detSequ}"})
	public void deleteStockRD(int detSequ);
	@Select(value = {"select * from stockrecede where recId=#{recId}"})
	@Results(id="selectOnes" ,value = {
			@Result(property = "recId",column = "recid"),
			@Result(property = "recDate",column = "recdate"),
			@Result(property = "peoId",column = "peoid"),
			@Result(property = "purveyName",column = "purveyname"),
			@Result(property = "peoAddress",column = "peoaddress"),
			@Result(property = "stoId",column = "stoid"),
			@Result(property = "depotName",column = "depotname"),
			@Result(property = "aNewStorage",column = "anewstorage"),
			@Result(property = "stoPerson",column = "stoperson"),
			@Result(property = "stoPersonId",column = "stopersonid"),
			@Result(property = "stoDept",column = "stodept"),
			@Result(property = "stoDeptId",column = "stodeptid"),
			@Result(property = "auditingPerson",column = "auditingperson"),
			@Result(property = "recRemark",column = "recremark"),
			@Result(property = "eState",column = "estate"),
			@Result(property = "recedeDetails",column = "recId",many = @Many(select = "com.slq.mapper.singleMethod.common.RecedeDetailsMapper.selectAllRecede",fetchType = FetchType.LAZY))
	})
	public StockRecede selectOne(@Param("recId")int recId);
	@Select(value = {"select * from stockrecede where estate=2"})
	@Results(id="selectAllCheck" ,value = {
			@Result(property = "recId",column = "recid"),
			@Result(property = "recDate",column = "recdate"),
			@Result(property = "peoId",column = "peoid"),
			@Result(property = "purveyName",column = "purveyname"),
			@Result(property = "peoAddress",column = "peoaddress"),
			@Result(property = "stoId",column = "stoid"),
			@Result(property = "depotName",column = "depotname"),
			@Result(property = "aNewStorage",column = "anewstorage"),
			@Result(property = "stoPerson",column = "stoperson"),
			@Result(property = "stoPersonId",column = "stopersonid"),
			@Result(property = "stoDept",column = "stodept"),
			@Result(property = "stoDeptId",column = "stodeptid"),
			@Result(property = "auditingPerson",column = "auditingperson"),
			@Result(property = "recRemark",column = "recremark"),
			@Result(property = "eState",column = "estate"),
			@Result(property = "recedeDetails",column = "recId",many = @Many(select = "com.slq.mapper.singleMethod.common.RecedeDetailsMapper.selectAllRecede",fetchType = FetchType.LAZY))
	})
	public List<StockRecede> selectAllCheck();
	@Select(value = {"select * from stockrecede where estate=1"})
	@Results(id="selectAllNoCheck" ,value = {
			@Result(property = "recId",column = "recid"),
			@Result(property = "recDate",column = "recdate"),
			@Result(property = "peoId",column = "peoid"),
			@Result(property = "purveyName",column = "purveyname"),
			@Result(property = "peoAddress",column = "peoaddress"),
			@Result(property = "stoId",column = "stoid"),
			@Result(property = "depotName",column = "depotname"),
			@Result(property = "aNewStorage",column = "anewstorage"),
			@Result(property = "stoPerson",column = "stoperson"),
			@Result(property = "stoPersonId",column = "stopersonid"),
			@Result(property = "stoDept",column = "stodept"),
			@Result(property = "stoDeptId",column = "stodeptid"),
			@Result(property = "auditingPerson",column = "auditingperson"),
			@Result(property = "recRemark",column = "recremark"),
			@Result(property = "eState",column = "estate"),
			@Result(property = "recedeDetails",column = "recId",many = @Many(select = "com.slq.mapper.singleMethod.common.RecedeDetailsMapper.selectAllRecede",fetchType = FetchType.LAZY))
	})
	public List<StockRecede> selectAllNoCheck();
	
}
