package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.production.ProcessMaterialDetail;
@Mapper
public interface ProcessMaterialDetailMapper extends BaseMapper<ProcessMaterialDetail>{

	List<ProcessMaterialDetail> getProcessMaterialDesignByParentId(Integer parentId);
	
	//这个和上面有区别
	List<ProcessMaterialDetail> getProcessMaterialByParentId(Integer parentId);

	int batchInsert(@Param("id")Integer id, @Param("processMaterialDetails")List<ProcessMaterialDetail> processMaterialDetails);

	List<ProcessMaterialDetail> getProcessMaterialDesignByParentId1(Integer parent_id);
}
