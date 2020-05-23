package com.slq.common.constant;

public interface ProductionConstant {

	public String PRODUCTION_PREFIX="scjh";
	public Integer REJECT=2;//驳回
	public Integer UNREVIEWED=0;//未审核 审核中的
	public Integer PASS=1;//审核通过
	//生产详细表的下派工状态 当然可能不需要  后续
	Integer NOT_MANUFACTURE_TAG=0;//没有派工
	Integer HAVE_MANUFACTURE_TAG=1;//已经派工
	
	String MANUFACTURE_PREFIX="pg";
	//制造产品状态
	Integer UNREVIEWED_MANUFACTURE_CHECK_TAG=0;
	Integer PASS_MANUFACTURE_CHECK_TAG=1;
	Integer REJECT_MANUFACTURE_CHECK_TAG=2;
	//生产过程标志  execute
	Integer NOT_MANUFACTURE_PROCEDURE_TAG=-1;//没有登记过 等待状态
	//已经有登记  但是还没有完工的状态
	Integer EXECUTE_MANUFACTURE_PROCEDURE_TAG=0;//生产中 执行的状态 已经
	Integer FINISH_MANUFACTURE_PROCEDURE_TAG=1;//已经完工的状态
	//审核中的状态  是当内部只要有生产登记和交接等审核的时候
	//就需要改变当前状态为  审核中的状态
	Integer UNREVIEWED_MANUFACTURE_PROCEDURE_TAG=2;//审核中的状态 
	//生产工序表ProdutionProcess
	//工序完成标志状态:0可以登记的状态 1完成
	//2登记了但是当前工序未完成的审核中 
	//3当前工序完成的审核中
	Integer NOT_START_PROCESS_FINISH_TAG=0;//
	Integer FINISH_PROCESS_FINISH_TAG=1;//
	Integer UNFINISHED_CHECK_PROCESS_FINISH_TAG=2;//
	Integer FINISH_CHECK_PROCESS_FINISH_TAG=3;//
	//工序交接标志:0未交接 1交接成功了 2交接的审核中
	Integer NOT_TRANSFER_PROCESS_TRANSFER_TAG=0;
	Integer FINISH_RANSFER_PROCESS_TRANSFER_TAG=1;
	Integer UNFINISHED_RANSFER_PROCESS_TRANSFER_TAG=2;
	//生产工序过程表ProductionProcessCourse
	//
	Integer UNREVIEWED_PRODUCTION_PROCESS_COURSE_CHECK_TAG=0;//审核状态为未通过
	Integer PASS_PRODUCTION_PROCESS_COURSE_CHECK_TAG=1;//审核状态为通过
	//Integer MANUFACTURE_PROCEDURE_TAG=0;
}
