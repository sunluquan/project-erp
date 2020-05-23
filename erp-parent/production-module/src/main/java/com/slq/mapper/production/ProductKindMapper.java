package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
<<<<<<< HEAD
import com.slq.common.Params;
=======
>>>>>>> warehouse
import com.slq.pojo.production.ProductKind;

@Mapper
public interface ProductKindMapper extends BaseMapper<ProductKind>{

	List<ProductKind> getProductKindAll();
	
	List<ProductKind> getKindChildren(Integer id);
<<<<<<< HEAD

	List<ProductKind> searchProductKind(Params params);

=======
>>>>>>> warehouse
}
