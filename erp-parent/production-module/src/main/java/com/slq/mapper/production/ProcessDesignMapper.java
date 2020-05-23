package com.slq.mapper.production;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.slq.common.Params;
import com.slq.pojo.production.ProcessDesign;
@Mapper
public interface ProcessDesignMapper extends BaseMapper<ProcessDesign>{

	List<ProcessDesign> getProcessList(@Param("check_tag")Integer check_tag,@Param("process_material_tag")Integer process_material_tag);

	ProcessDesign getProcessDesignById(Integer id);

	List<ProcessDesign> processDesignsearch(Params params);

	ProcessDesign getProcessDesignJoinMaterialDetail(Integer id);

	List<ProcessDesign> processMaterialSearch(Params params);

	ProcessDesign getProcessDesignByProductId(String product_id);

}
