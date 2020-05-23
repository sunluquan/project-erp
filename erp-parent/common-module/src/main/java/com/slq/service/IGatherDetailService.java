package com.slq.service;

import java.util.List;

import com.slq.pojo.warehouse.GatherDetails;

public interface IGatherDetailService {

	int batchInsertFinishedGatherDetail(Integer id, List<GatherDetails> gatherDetailsList);
}
