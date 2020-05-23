package com.slq.service.stock;

import com.slq.pojo.stock.*;

/**
 * 退货审核Service
 * @author lqy
 *
 */
public interface ExamineService {
	void examineRecede(int recId);
	void rejectRecede(StockRecede sr);
}
