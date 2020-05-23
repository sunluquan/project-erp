package com.slq.service.production;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProductKind;

public interface IProductKindService {

	List<ProductKind> getKinds();

	PageInfo<ProductKind> searchProductKind(Params params);

	int insert(ProductKind productKind);

}
