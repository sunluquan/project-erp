package com.slq.service.stock.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slq.mapper.common.UpdateRecede;
import com.slq.pojo.stock.*;
import com.slq.service.stock.UpdateRecedeService;

@Service
public class UpdateRecedeServiceImpl implements UpdateRecedeService{
	@Autowired
	UpdateRecede ur;

	@Override
	public void updateRecedeReturn(int detSequ) {
		ur.updateRecedeReturn(detSequ);
	}

	@Override
	public void updateRecedeNoReturn(int detSequ) {
		ur.updateRecedeNoReturn(detSequ);
	}

	@Override
	public void updateRecedeInfo(StockRecede sr) {
		ur.updateRecedeInfo(sr);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRecedeGoods(int recId,List<RecedeDetails> rd) {
		System.out.println("删除方法");
		ur.deleteRecedeGoods(recId);
		System.out.println("添加方法");
		for(RecedeDetails r:rd) {
			System.err.println("a"+r);
		}
		ur.insertRecedeGoods(rd);
		
	}

	@Override
	public void insertRecedeGoods(List<RecedeDetails> rd) {
		ur.insertRecedeGoods(rd);
	}
}
