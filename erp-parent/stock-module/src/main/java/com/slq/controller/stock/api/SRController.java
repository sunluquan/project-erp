package com.slq.controller.stock.api;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
/**
 * 采购退货的控制器
 * @author lqy
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slq.mapper.common.UpdateRecede;
import com.slq.mapper.singleMethod.common.*;
import com.slq.pojo.stock.RecedeDetails;
import com.slq.pojo.stock.StockRecede;
import com.slq.service.stock.*;
import com.slq.util.DefaultResponseData;
import com.slq.util.ResponseData;
@RestController
@RequestMapping("/api/Recede")   
public class SRController {
	public SRController() {
		super();
		System.out.println("采购模块");
	}
	@Autowired
	StockRecedeService srService;
	@Autowired
	RecedeDetailsService rds;
	@Autowired
	ExamineService es;
	@Autowired
	UpdateRecedeService urs;
	@Autowired
	UpdateRecede ur;
	//查询所有商品
	@RequestMapping("/selectAll")
	public ResponseData selectAll() {
		List<StockRecede> sr=srService.selectAllStock();
		return DefaultResponseData.successResponseData(sr);
	}
	//查询所有未审核商品
	@RequestMapping("/selectAllNoCheck")
	public ResponseData selectAllNoCheck() {
		return DefaultResponseData.successResponseData(srService.selectAllNoCheck());
	}
	@RequestMapping("/delete/{recId}")
	@Transactional
	public ResponseData delete(@PathVariable int recId) {
		System.out.println(recId);
		try {
			
			srService.deleteStockOD(recId);
			srService.deleteStockRD(recId);   
		}catch(Exception ex){
			return DefaultResponseData.DELETE_ERROR;
		}
		return DefaultResponseData.successResponseData(200);
	}
	@RequestMapping("/byId/{recId}")
	public ResponseData selectOne(@PathVariable int recId) {
		return DefaultResponseData.successResponseData(srService.selectOne(recId));
	}
	//审核商品
	@RequestMapping("/pass")
	public ResponseData passRecede(int recId) {
		try {
			es.examineRecede(recId);
		}catch(Exception ex) {
			return DefaultResponseData.UPDATE_ERROR;
		}
		return DefaultResponseData.successResponseData(200);
	}
	//复核商品
	@RequestMapping("/reject")
	public ResponseData rejectRecede(@RequestBody StockRecede sr) {
		try {
			es.rejectRecede(sr);
		}catch(Exception ex) {
			ex.printStackTrace();
			return DefaultResponseData.UPDATE_ERROR;
		}
		return DefaultResponseData.successResponseData(200);
	}
	//查询所有未审核商品
	@RequestMapping("/selectAllCheck")
	public ResponseData selectRejectRecede() {
		return DefaultResponseData.successResponseData(srService.selectAllCheck());
	}
	//修改退货单主表
	@RequestMapping("/update/{recId}")
	public ResponseData updateRecede(@PathVariable int recId) {
		try {
			urs.updateRecedeInfo(srService.selectOne(recId));
		}catch(Exception ex) {
			return DefaultResponseData.UPDATE_ERROR;
		}
		return DefaultResponseData.successResponseData(200);
	}
	//查询所有退货详细表商品
//	updateRecede
	@RequestMapping("/updateRecede/{recId}")
	public ResponseData selectUpdate(@PathVariable int recId) {
		System.out.println(recId+"fdasfdasdf");
		System.out.println(srService.selectOne(recId));
		return DefaultResponseData.successResponseData(srService.selectOne(recId));
	}
	//修改退货详细表商品列表
	@RequestMapping("/updateRecedeGoods")
//	@RequestParam(value = "recedeDetails",required = true) List<RecedeDetails> recedeDetails,@RequestParam(value = "recId",required = true) int recId
//	@RequestBody List<RecedeDetails> rd,@RequestBody int recId
	public ResponseData updateRecedeGoods(@RequestBody StockRecede sr) {
		try {
			List<RecedeDetails> rd=sr.getRecedeDetails();
			urs.deleteRecedeGoods(sr.getRecId(),rd);
		}catch(Exception ex) {
			ex.printStackTrace();
			return DefaultResponseData.ADD_ERROR;
		}
		return DefaultResponseData.successResponseData(200);
	}
}
