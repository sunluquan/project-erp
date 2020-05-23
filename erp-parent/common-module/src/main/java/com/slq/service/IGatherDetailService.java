package com.slq.service;

import java.util.List;

import com.slq.pojo.warehouse.GatherDetails;

public interface IGatherDetailService {

<<<<<<< HEAD
	int batchInsertFinishedGatherDetail(Integer id, List<GatherDetails> gatherDetailsList);
=======
	int batchInsertFinishedGatherDetail(String gatherId, List<GatherDetails> gatherDetailsList);
>>>>>>> warehouse
}
