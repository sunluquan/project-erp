package com.slq.service.stock;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.slq.pojo.stock.*;

/**
 * 修改退货单
 * @author lqy
 *
 */
public interface UpdateRecedeService {
	 void updateRecedeReturn(int detSequ);
	 void updateRecedeNoReturn(int detSequ);
	 void updateRecedeInfo(StockRecede sr);
	 void deleteRecedeGoods(int recId,List<RecedeDetails> rd);
	 void insertRecedeGoods(List<RecedeDetails> rd);
}
