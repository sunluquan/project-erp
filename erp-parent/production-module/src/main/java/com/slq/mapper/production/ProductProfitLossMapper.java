package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.common.Params;
import com.slq.pojo.production.ProductProfitLoss;

@Mapper
public interface ProductProfitLossMapper extends BaseMapper<ProductProfitLoss>{

	List<ProductProfitLoss> searchProductProfitLoss(Params params);


}
