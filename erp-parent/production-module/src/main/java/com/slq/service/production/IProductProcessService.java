package com.slq.service.production;

import java.util.List;

import com.slq.pojo.production.ProductProcess;

public interface IProductProcessService {

	List<ProductProcess> selectUsableProcess();

}
