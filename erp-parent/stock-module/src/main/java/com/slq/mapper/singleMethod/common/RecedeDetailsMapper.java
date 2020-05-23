package com.slq.mapper.singleMethod.common;
/**
 * 退货详情
 * @author lqy
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.slq.pojo.stock.*;
@Mapper
public interface RecedeDetailsMapper {
	@Select(value = "select * from recedeDetails where recid=#{recId}")
	public List<RecedeDetails> selectAllRecede(@Param("recId")int recId);
	@Select(value = "select * from stockRecede where recid=#{recId}")
	public StockRecede selectOne(@Param("recId")int recId);
}