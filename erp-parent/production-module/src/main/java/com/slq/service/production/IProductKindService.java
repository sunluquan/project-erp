package com.slq.service.production;

import java.util.List;

<<<<<<< HEAD
<<<<<<< HEAD
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
=======
>>>>>>> warehouse
=======
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.pojo.production.ProductKind;

public interface IProductKindService {

	List<ProductKind> getKinds();

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	PageInfo<ProductKind> searchProductKind(Params params);

	int insert(ProductKind productKind);

<<<<<<< HEAD
=======
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
