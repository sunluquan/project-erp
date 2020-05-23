package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slq.common.Params;
import com.slq.pojo.warehouse.sPay;
import com.slq.pojo.warehouse.sPayDetails;

@Mapper
public interface StockOutMapper {

	public int addStockOut(sPay spay);

	public List<sPay> selectByTag();

	public sPay selectByPayId(String id);

	public int upStatus(sPay spay);

	public List<sPay> selectAll();

	public int upNotStatus(sPay spay);

	public List<sPay> searchSpay(Params params);

	public List<sPay> selectTags();

	public sPay selectDetails(sPayDetails spay);

	public int selectCount();

	public sPay selectDetails(String payId);

	public int upSum(sPayDetails detail);

	public List<sPay> selectRegists();

	public int selectRegistCount();

	public int aksure(sPay spay);

	public int updateStoreTag(String alreadyRegistration);

}
