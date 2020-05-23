package com.slq.mapper.common;
import org.apache.ibatis.annotations.Mapper;
/**
 * 退货审核
 * @author lqy
 *
 */
import org.apache.ibatis.annotations.Update;

import com.slq.pojo.stock.*;
@Mapper
public interface ExamineRecede {
	@Update("update stockrecede set estate=1 where recId=#{recId}")
	public void examineRecede(int recId);
	@Update("update stockrecede set estate=2,recRemark=#{recRemark} where recId=#{recId}")
	public void rejectRecede(StockRecede sr);
}
