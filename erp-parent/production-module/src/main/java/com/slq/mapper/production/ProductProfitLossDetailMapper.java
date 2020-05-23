package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.production.ProductProfitLossDetail;

@Mapper
public interface ProductProfitLossDetailMapper extends BaseMapper<ProductProfitLossDetail>{

	Integer batchInsert(@Param("id")Integer id,@Param("list") List<ProductProfitLossDetail> productProfitLossDetails);

	int batchUpdate(@Param("list")List<ProductProfitLossDetail> productProfitLossDetails);

}
