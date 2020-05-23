package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.production.ProductionProcessMaterial;
import com.slq.pojo.production.ProductionProcessMaterialCourse;

@Mapper
public interface ProductionProcessMaterialCourseMapper extends BaseMapper<ProductionProcessMaterialCourse>{

	int batchInsert(@Param("manufacture_id")Integer manufacture_id, 
			@Param("list")List<ProductionProcessMaterial> productionProcessMaterials);

	int batchUpdate(@Param("list")List<ProductionProcessMaterial> productionProcessMaterials);

}
