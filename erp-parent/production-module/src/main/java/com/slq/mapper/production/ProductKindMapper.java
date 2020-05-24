package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
<<<<<<< HEAD
<<<<<<< HEAD
import com.slq.common.Params;
=======
>>>>>>> warehouse
=======
import com.slq.common.Params;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
import com.slq.pojo.production.ProductKind;

@Mapper
public interface ProductKindMapper extends BaseMapper<ProductKind>{

	List<ProductKind> getProductKindAll();
	
	List<ProductKind> getKindChildren(Integer id);
<<<<<<< HEAD
<<<<<<< HEAD

	List<ProductKind> searchProductKind(Params params);

=======
>>>>>>> warehouse
=======

	List<ProductKind> searchProductKind(Params params);

>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
