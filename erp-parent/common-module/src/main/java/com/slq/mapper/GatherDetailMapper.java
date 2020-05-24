package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slq.pojo.warehouse.GatherDetails;

import feign.Param;
@Mapper
public interface GatherDetailMapper extends BaseMapper<GatherDetails>{

<<<<<<< HEAD
<<<<<<< HEAD
	int batchInsertFinishedGatherDetail(@Param("id")Integer id, @Param("list") List<GatherDetails> gatherDetailsList);
=======
	int batchInsertFinishedGatherDetail(@Param("id")String id, @Param("list") List<GatherDetails> gatherDetailsList);
>>>>>>> warehouse
=======
	int batchInsertFinishedGatherDetail(@Param("id")Integer id, @Param("list") List<GatherDetails> gatherDetailsList);
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6

}
