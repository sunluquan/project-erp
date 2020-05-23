/*
Navicat MySQL Data Transfer

Source Server         : PUBG2
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : project1

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-05-23 15:26:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `deptName` varchar(20) NOT NULL COMMENT '部门名称',
  `deptRemark` varchar(20) DEFAULT NULL COMMENT '部门说明',
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '生产部', '生产产品的部门');
INSERT INTO `dept` VALUES ('2', '销售部', '销售产品的部门');
INSERT INTO `dept` VALUES ('3', '采购部', '采购产品的部门');
INSERT INTO `dept` VALUES ('4', '财务部', '财务管理的部门');

-- ----------------------------
-- Table structure for `manufacture_sum`
-- ----------------------------
DROP TABLE IF EXISTS `manufacture_sum`;
CREATE TABLE `manufacture_sum` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `manufacture_id` varchar(50) NOT NULL COMMENT '派工单编号',
  `product_id` varchar(50) NOT NULL COMMENT '产品编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `amount` int(11) NOT NULL COMMENT '投产数量',
  `tested_amount` int(11) DEFAULT '0' COMMENT '合格数量',
  `production_id_group` varchar(400) DEFAULT NULL COMMENT '生产计划序号组',
  `material_cost_price_sum` decimal(10,2) DEFAULT '0.00' COMMENT '设计物料总成本',
  `real_material_cost_price_sum` decimal(10,2) DEFAULT '0.00' COMMENT '实际物料总成本',
  `labour_cost_price_sum` decimal(10,2) DEFAULT '0.00' COMMENT '设计工时总成本',
  `real_labour_cost_price_sum` decimal(10,2) DEFAULT '0.00' COMMENT '实际工时总成本',
  `designer` varchar(50) DEFAULT NULL COMMENT '工单制定人',
  `register` varchar(50) DEFAULT NULL COMMENT '登记人',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记时间',
  `checker` varchar(50) DEFAULT NULL COMMENT '审核人',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `check_suggestion` varchar(400) DEFAULT NULL COMMENT '审核意见',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `check_tag` int(11) NOT NULL DEFAULT '0' COMMENT '审核标志0: 等待审核1: 审核通过2: 审核不通过',
  `manufacture_procedure_tag` int(11) NOT NULL DEFAULT '0' COMMENT '生产过程标志0: 待登记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='生产总表';

-- ----------------------------
-- Records of manufacture_sum
-- ----------------------------
INSERT INTO `manufacture_sum` VALUES ('22', 'PG20200512000001', 'CP20200511000001', '笔记本电脑', '1100', '0', null, '665500.00', '0.00', '556600.00', '0.00', '123', '123', '2020-05-12 09:38:11', '123', '2020-05-12 09:38:23', null, '', '1', '0');
INSERT INTO `manufacture_sum` VALUES ('23', 'PG20200513000002', 'CP20200511000001', '笔记本电脑', '1200', '1200', null, '726000.00', '0.00', '607200.00', '0.00', '123', '123', '2020-05-13 00:46:21', '123', '2020-05-19 06:46:26', null, '', '1', '1');
INSERT INTO `manufacture_sum` VALUES ('24', 'PG20200513000003', 'CP20200511000001', '笔记本电脑', '50', '0', null, '30250.00', '705250.00', '25300.00', '1270.00', '123', '123', '2020-05-13 02:44:49', '123', '2020-05-13 02:45:04', null, '', '1', '1');
INSERT INTO `manufacture_sum` VALUES ('25', 'PG20200521000001', 'CP20200511000001', '?????', '1005', '1005', null, '608025.00', '0.00', '508530.00', '0.00', '123', '123', '2020-05-21 00:39:49', '123', '2020-05-21 00:40:17', null, '', '1', '1');
INSERT INTO `manufacture_sum` VALUES ('26', 'PG20200521000001', 'CP20200511000001', '笔记本电脑', '1000', '1000', null, '605000.00', '0.00', '506000.00', '0.00', '123', '123', '2020-05-21 02:15:21', '123', '2020-05-21 02:15:42', null, '', '1', '1');
INSERT INTO `manufacture_sum` VALUES ('27', 'PG20200521000002', 'CP20200511000001', '笔记本电脑', '1000', '1000', null, '605000.00', '0.00', '506000.00', '0.00', '123', '123', '2020-05-21 02:43:14', '123', '2020-05-21 02:46:09', null, '', '1', '1');

-- ----------------------------
-- Table structure for `material_design_detail`
-- ----------------------------
DROP TABLE IF EXISTS `material_design_detail`;
CREATE TABLE `material_design_detail` (
  `parent_id` int(11) NOT NULL COMMENT '父级序号 外键为material_design',
  `product_id` varchar(20) NOT NULL COMMENT '物料编号',
  `amount` int(11) NOT NULL COMMENT '数量',
  `residual_amount` int(11) DEFAULT NULL COMMENT '可用数量',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='产品物料组成明细';

-- ----------------------------
-- Records of material_design_detail
-- ----------------------------
INSERT INTO `material_design_detail` VALUES ('8', 'YCL20200511000003', '1', '0', '13');
INSERT INTO `material_design_detail` VALUES ('8', 'YCL20200511000002', '1', '0', '14');
INSERT INTO `material_design_detail` VALUES ('8', 'YCL20200511000001', '2', '0', '15');
INSERT INTO `material_design_detail` VALUES ('9', 'YCL20200511000003', '1', '0', '16');
INSERT INTO `material_design_detail` VALUES ('9', 'YCL20200511000002', '2', '0', '17');
INSERT INTO `material_design_detail` VALUES ('9', 'YCL20200511000001', '1', '0', '18');

-- ----------------------------
-- Table structure for `process_detail`
-- ----------------------------
DROP TABLE IF EXISTS `process_detail`;
CREATE TABLE `process_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '他和 parent_id 为共同的主键',
  `parent_id` int(11) NOT NULL COMMENT '父级序号 的外键为product_procedure_design产品工序设计的id',
  `details_number` int(11) NOT NULL COMMENT '工序序号--执行顺序',
  `process_id` int(11) NOT NULL,
  `labour_hour_amount` decimal(10,2) NOT NULL COMMENT '工时数',
  `process_describe` varchar(200) DEFAULT NULL COMMENT '工序描述',
  `time_unit` varchar(20) NOT NULL COMMENT '单位',
  `cost_price` decimal(10,2) NOT NULL COMMENT '单位工时成本',
  `material_tag` int(11) NOT NULL DEFAULT '0' COMMENT '当前工序物料标志0: 未设计1: 已设计',
  `material_change_tag` int(11) NOT NULL DEFAULT '0' COMMENT '当前工序物料变更标志0: 未变更1: 已变更',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='产品生产工序明细';

-- ----------------------------
-- Records of process_detail
-- ----------------------------
INSERT INTO `process_detail` VALUES ('22', '6', '1', '7', '1.00', null, '/(天 day)', '500.00', '1', '0');
INSERT INTO `process_detail` VALUES ('23', '6', '2', '8', '0.10', null, '/(天 day)', '50.00', '0', '0');
INSERT INTO `process_detail` VALUES ('24', '6', '3', '9', '0.10', null, '/(天 day)', '10.00', '1', '0');
INSERT INTO `process_detail` VALUES ('25', '7', '1', '7', '0.00', null, '/(分钟 minute)', '0.00', '1', '0');
INSERT INTO `process_detail` VALUES ('26', '7', '2', '9', '0.00', null, '/(分钟 minute)', '0.00', '1', '0');
INSERT INTO `process_detail` VALUES ('27', '7', '3', '8', '0.00', null, '/(分钟 minute)', '0.00', '1', '0');
INSERT INTO `process_detail` VALUES ('28', '8', '1', '7', '5.60', null, '/(分钟 minute)', '5.00', '0', '0');
INSERT INTO `process_detail` VALUES ('29', '8', '2', '9', '7.80', null, '/(分钟 minute)', '6.00', '0', '0');
INSERT INTO `process_detail` VALUES ('30', '8', '3', '8', '4.50', null, '/(分钟 minute)', '6.00', '0', '0');

-- ----------------------------
-- Table structure for `process_material_detail`
-- ----------------------------
DROP TABLE IF EXISTS `process_material_detail`;
CREATE TABLE `process_material_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父级序号 外键  为 product_procedure_details产品工序明细',
  `details_number` int(11) DEFAULT NULL COMMENT '工序物料序号',
  `material_id` int(11) NOT NULL,
  `useAmount` int(11) NOT NULL COMMENT '本工序需要数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='产品生产工序物料明细';

-- ----------------------------
-- Records of process_material_detail
-- ----------------------------
INSERT INTO `process_material_detail` VALUES ('51', '22', null, '15', '2');
INSERT INTO `process_material_detail` VALUES ('52', '22', null, '14', '1');
INSERT INTO `process_material_detail` VALUES ('53', '24', null, '13', '1');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `product_id` varchar(50) NOT NULL COMMENT '产品编号',
  `product_name` varchar(50) NOT NULL COMMENT '产品的名称*',
  `product_kind_id` int(11) DEFAULT NULL COMMENT '产品分类编号*',
  `use_type` int(11) NOT NULL COMMENT '用途类型id*',
  `initial` varchar(20) NOT NULL COMMENT '产品名称首字母*',
  `product_rank` varchar(20) DEFAULT NULL COMMENT '商品档次级别',
  `product_unit` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `list_price` decimal(10,2) NOT NULL COMMENT '市场单价*',
  `cost_price` decimal(10,2) NOT NULL COMMENT '计划成本价*',
  `REAL_COST_PRICE` decimal(10,2) DEFAULT NULL COMMENT '实际的成本价',
  `register` varchar(20) NOT NULL COMMENT '登记人*',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间*',
  `checker` varchar(20) DEFAULT NULL COMMENT '检验员*',
  `check_time` datetime DEFAULT NULL COMMENT '检验时间*',
  `check_tag` int(11) DEFAULT '0' COMMENT '0等待审核 1审核通过 2审核未通过*',
  `material_tag` int(11) DEFAULT '-1' COMMENT '-1从未设计 0 设计过可能再审核中也可以是驳回状态 1物料已经设计审核通过',
  `process_tag` int(11) NOT NULL DEFAULT '-1' COMMENT '-1为工序从未设计 0 工序设计审核中或者驳回 1工序已经设计',
  `inventory_allocation_tag` varchar(20) DEFAULT '0' COMMENT 'null为未分配 0分配审核中  1分配成功 审核通过 2审核未通过',
  `changer` varchar(20) DEFAULT NULL COMMENT '变更人',
  `change_time` date DEFAULT NULL COMMENT '变更时间',
  `message` varchar(100) DEFAULT NULL,
  `FACTORY_NAME` varchar(50) DEFAULT NULL COMMENT '供货商 制造商',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='产品设计表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('17', 'CP20200511000001', '笔记本电脑', '3', '1', 'bjbdn', '高级档次', '台', '6000.00', '3000.00', '1111.00', '123', '2020-05-11 08:46:16', '123', '2020-05-11 00:49:13', '1', '1', '1', '0', null, null, '', '');
INSERT INTO `product` VALUES ('18', 'YCL20200511000001', 'cpu', null, '3', 'cpu', '高级档次', '个', '500.00', '200.00', '0.00', '123', '2020-05-11 08:47:50', '123', '2020-05-11 00:49:09', '1', '-1', '-1', '0', null, null, '', '');
INSERT INTO `product` VALUES ('19', 'YCL20200511000002', '内存条', '3', '3', 'nct', '高级档次', '个', '500.00', '200.00', '0.00', '123', '2020-05-11 08:48:15', '123', '2020-05-11 00:49:04', '1', '-1', '-1', '0', null, null, '', '');
INSERT INTO `product` VALUES ('20', 'YCL20200511000003', '电脑包装箱', null, '3', 'dnbzx', '高级档次', '箱', '10.00', '5.00', '0.00', '123', '2020-05-11 08:48:42', '123', '2020-05-11 00:48:49', '1', '-1', '-1', '0', null, null, '', '');
INSERT INTO `product` VALUES ('22', 'CP20200511000002', '小笔记本电脑', '3', '1', 'xbjbdn', '高级档次', '台', '6000.00', '3000.00', '605.00', '123', '2020-05-11 08:59:56', '123', '2020-05-11 01:29:06', '1', '1', '1', '0', null, null, '', '');
INSERT INTO `product` VALUES ('23', 'CP20200518000001', '123', '3', '1', '123', '高级档次', '台', '5000.00', '3000.00', '101.80', '123', '2020-05-18 09:55:40', '123', '2020-05-18 02:18:25', '1', '1', '1', '0', '123', '2020-05-18', '', '');

-- ----------------------------
-- Table structure for `production_plan`
-- ----------------------------
DROP TABLE IF EXISTS `production_plan`;
CREATE TABLE `production_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `production_id` varchar(50) NOT NULL COMMENT '生产计划编号',
  `designer` varchar(50) DEFAULT NULL COMMENT '设计人',
  `production_reason` varchar(50) DEFAULT NULL COMMENT '生产理由',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `register` varchar(50) DEFAULT NULL COMMENT '登记人',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记时间',
  `checker` varchar(50) DEFAULT NULL COMMENT '复核人',
  `check_suggestion` varchar(400) DEFAULT NULL COMMENT '审核意见',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `check_tag` varchar(20) NOT NULL DEFAULT '0' COMMENT '审核标志0: 等待审核1: 审核通过2: 审核不通过',
  `production_state` int(11) DEFAULT '-1' COMMENT '保留字段  生产状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='产品生产计划';

-- ----------------------------
-- Records of production_plan
-- ----------------------------
INSERT INTO `production_plan` VALUES ('22', 'SCJH20200519000004', '123', '新产品的生产', '', '123', '2020-05-19 03:01:24', null, null, null, '1', '-1');
INSERT INTO `production_plan` VALUES ('23', 'SCJH20200521000001', '123', '??????', '', '123', '2020-05-21 00:39:01', null, null, null, '1', '-1');
INSERT INTO `production_plan` VALUES ('24', 'SCJH20200521000001', '123', '新产品的生产', '', '123', '2020-05-21 02:14:41', null, null, null, '1', '-1');
INSERT INTO `production_plan` VALUES ('25', 'SCJH20200521000002', '123', '新产品的生产', '', '123', '2020-05-21 02:42:32', null, null, null, '1', '-1');
INSERT INTO `production_plan` VALUES ('26', 'SCJH20200521000003', '123', '新产品的生产', '', '123', '2020-05-21 02:56:52', null, null, null, '1', '-1');
INSERT INTO `production_plan` VALUES ('27', 'SCJH20200522000004', '123', '新产品的生产', '', '123', '2020-05-22 00:39:29', null, null, null, '1', '-1');

-- ----------------------------
-- Table structure for `production_plan_detail`
-- ----------------------------
DROP TABLE IF EXISTS `production_plan_detail`;
CREATE TABLE `production_plan_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT 'production_plan的id',
  `product_id` varchar(50) NOT NULL COMMENT '产品编号',
  `amount` int(11) NOT NULL COMMENT '数量',
  `manufacture_tag` int(11) NOT NULL DEFAULT '0' COMMENT '派工标志0: 未派工1: 已派工',
  `manufacture_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='产品生产计划详细表';

-- ----------------------------
-- Records of production_plan_detail
-- ----------------------------
INSERT INTO `production_plan_detail` VALUES ('18', '15', 'CP20200511000001', '1000', '1', '23');
INSERT INTO `production_plan_detail` VALUES ('19', '16', 'CP20200511000001', '100', '1', '23');
INSERT INTO `production_plan_detail` VALUES ('20', '17', 'CP20200511000001', '100', '1', '23');
INSERT INTO `production_plan_detail` VALUES ('21', '18', 'CP20200511000001', '50', '1', '24');
INSERT INTO `production_plan_detail` VALUES ('22', '22', 'CP20200511000001', '5', '1', '25');
INSERT INTO `production_plan_detail` VALUES ('23', '23', 'CP20200511000001', '1000', '1', '25');
INSERT INTO `production_plan_detail` VALUES ('24', '24', 'CP20200511000001', '1000', '1', '26');
INSERT INTO `production_plan_detail` VALUES ('25', '25', 'CP20200511000001', '1000', '1', '27');
INSERT INTO `production_plan_detail` VALUES ('26', '26', 'CP20200511000002', '1', '0', null);
INSERT INTO `production_plan_detail` VALUES ('27', '27', 'CP20200518000001', '1', '0', null);
INSERT INTO `production_plan_detail` VALUES ('28', '27', 'CP20200511000001', '1', '0', null);

-- ----------------------------
-- Table structure for `production_process`
-- ----------------------------
DROP TABLE IF EXISTS `production_process`;
CREATE TABLE `production_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父级序号 和生产总表对应  为外键',
  `details_number` int(11) NOT NULL COMMENT '工序序号',
  `process_id` varchar(50) NOT NULL COMMENT '工序编号',
  `process_name` varchar(50) DEFAULT NULL COMMENT '工序名称',
  `labour_hour_amount` decimal(10,2) DEFAULT '0.00' COMMENT '设计工时数',
  `real_labour_hour_amount` decimal(10,2) DEFAULT '0.00' COMMENT '实际工时数',
  `subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '设计工时成本',
  `real_subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '实际工时成本',
  `module_subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '设计物料成本',
  `real_module_subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '实际物料成本',
  `cost_price` decimal(10,2) DEFAULT '0.00' COMMENT '单位工时成本',
  `demand_amount` int(11) DEFAULT '0' COMMENT '工序投产数量',
  `real_amount` int(11) DEFAULT '0' COMMENT '工序合格数量',
  `process_finish_tag` int(11) NOT NULL DEFAULT '0' COMMENT '工序完成标志0: 未开始1: 已完成2: 未完成 3: 已审核 4审核中 5审核未通过',
  `process_transfer_tag` int(11) NOT NULL DEFAULT '0' COMMENT '工序交接标志0: 未交接1: 已审核(已交接) 2: 未审核 ',
  `time_unit` varchar(50) DEFAULT NULL COMMENT '工时单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='生产工序表';

-- ----------------------------
-- Records of production_process
-- ----------------------------
INSERT INTO `production_process` VALUES ('43', '22', '1', '7', '组装', '1100.00', '1100.00', '550000.00', '550000.00', '660000.00', '660000.00', '500.00', '1100', '1100', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('44', '22', '2', '8', '测试', '110.00', '110.00', '5500.00', '5500.00', '0.00', '0.00', '50.00', '1100', '1100', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('45', '22', '3', '9', '包装', '110.00', '0.00', '1100.00', '0.00', '5500.00', '0.00', '10.00', '1100', '0', '3', '0', '/(天 day)');
INSERT INTO `production_process` VALUES ('46', '23', '1', '7', '组装', '1200.00', '1200.00', '600000.00', '600000.00', '720000.00', '720000.00', '500.00', '1200', '0', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('47', '23', '2', '8', '测试', '120.00', '120.00', '6000.00', '6000.00', '0.00', '0.00', '50.00', '1200', '1200', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('48', '23', '3', '9', '包装', '120.00', '120.00', '1200.00', '1200.00', '6000.00', '6000.00', '10.00', '1200', '1200', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('49', '24', '1', '7', '组装', '50.00', '50.00', '25000.00', '25000.00', '30000.00', '45000.00', '500.00', '50', '0', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('50', '24', '2', '8', '测试', '5.00', '5.00', '250.00', '250.00', '0.00', '0.00', '50.00', '50', '50', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('51', '24', '3', '9', '包装', '5.00', '5.00', '50.00', '50.00', '250.00', '250.00', '10.00', '50', '0', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('52', '25', '1', '7', '??', '1005.00', '1005.00', '502500.00', '502500.00', '603000.00', '603000.00', '500.00', '1005', '1005', '1', '1', '/(? day)');
INSERT INTO `production_process` VALUES ('53', '25', '2', '8', '??', '100.50', '1005.00', '5025.00', '50250.00', '0.00', '0.00', '50.00', '1005', '1005', '1', '1', '/(? day)');
INSERT INTO `production_process` VALUES ('54', '25', '3', '9', '??', '100.50', '1005.00', '1005.00', '10050.00', '5025.00', '5025.00', '10.00', '1005', '1005', '1', '1', '/(? day)');
INSERT INTO `production_process` VALUES ('55', '26', '1', '7', '组装', '1000.00', '1000.00', '500000.00', '500000.00', '600000.00', '600000.00', '500.00', '1000', '1000', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('56', '26', '2', '8', '测试', '100.00', '100.00', '5000.00', '5000.00', '0.00', '0.00', '50.00', '1000', '1000', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('57', '26', '3', '9', '包装', '100.00', '100.00', '1000.00', '1000.00', '5000.00', '0.00', '10.00', '1000', '1000', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('58', '27', '1', '7', '组装', '1000.00', '0.00', '500000.00', '0.00', '600000.00', '600000.00', '500.00', '1000', '1000', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('59', '27', '2', '8', '测试', '100.00', '0.00', '5000.00', '0.00', '0.00', '0.00', '50.00', '1000', '1000', '1', '1', '/(天 day)');
INSERT INTO `production_process` VALUES ('60', '27', '3', '9', '包装', '100.00', '0.00', '1000.00', '0.00', '5000.00', '5000.00', '10.00', '1000', '1000', '1', '1', '/(天 day)');

-- ----------------------------
-- Table structure for `production_process_course`
-- ----------------------------
DROP TABLE IF EXISTS `production_process_course`;
CREATE TABLE `production_process_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号 必须要 因为它是生产工序物料记录过程的外键',
  `parent_id` int(11) NOT NULL COMMENT '父级序号 也和生产总表挂钩',
  `details_number` int(11) DEFAULT NULL COMMENT '工序序号',
  `process_id` varchar(50) NOT NULL COMMENT '工序编号',
  `process_name` varchar(50) DEFAULT NULL COMMENT '工序名称',
  `labour_hour_amount` decimal(10,2) DEFAULT '0.00' COMMENT '本次工时数',
  `time_unit` varchar(20) DEFAULT NULL COMMENT '时间单位',
  `cost_price` decimal(10,2) DEFAULT '0.00' COMMENT '单位工时成本',
  `subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '工时成本小计',
  `procedure_describe` varchar(400) DEFAULT NULL COMMENT '工序描述',
  `reg_count` int(11) NOT NULL DEFAULT '0' COMMENT '登记次数',
  `responsible_person` varchar(50) DEFAULT NULL COMMENT '负责人',
  `register` varchar(50) DEFAULT NULL COMMENT '登记人',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记时间',
  `checker` varchar(50) DEFAULT NULL COMMENT '审核人',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `check_tag` int(11) DEFAULT '0' COMMENT '0审核中 1审核已通过 2审核未通过 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='生产工序过程记录';

-- ----------------------------
-- Records of production_process_course
-- ----------------------------
INSERT INTO `production_process_course` VALUES ('19', '24', null, '7', '组装', '25.00', '/(天 day)', '500.00', '12500.00', '', '0', '123', '123', '2020-05-13 02:45:39', '123', '2020-05-13 02:45:50', '1');
INSERT INTO `production_process_course` VALUES ('20', '24', null, '7', '组装', '50.00', '/(天 day)', '500.00', '25000.00', '', '0', '123', '123', '2020-05-13 02:56:12', '123', '2020-05-13 03:04:32', '1');
INSERT INTO `production_process_course` VALUES ('21', '22', null, '7', '组装', '550.00', '/(天 day)', '500.00', '275000.00', '', '0', '123', '123', '2020-05-13 03:27:36', '123', '2020-05-13 03:28:02', '1');
INSERT INTO `production_process_course` VALUES ('22', '22', null, '7', '组装', '550.00', '/(天 day)', '500.00', '275000.00', '', '0', '123', '123', '2020-05-13 03:28:31', '123', '2020-05-13 03:28:51', '1');
INSERT INTO `production_process_course` VALUES ('23', '22', null, '8', '测试', '110.00', '/(天 day)', '50.00', '5500.00', '', '0', '123', '123', '2020-05-13 03:29:41', '123', '2020-05-13 03:30:27', '1');
INSERT INTO `production_process_course` VALUES ('24', '22', null, '9', '包装', '110.00', '/(天 day)', '10.00', '1100.00', '', '0', '123', '123', '2020-05-13 03:30:11', null, null, '0');
INSERT INTO `production_process_course` VALUES ('25', '24', null, '8', '测试', '5.00', '/(天 day)', '50.00', '250.00', '', '0', '123', '123', '2020-05-13 03:53:34', '123', '2020-05-13 03:53:56', '1');
INSERT INTO `production_process_course` VALUES ('26', '24', null, '9', '包装', '5.00', '/(天 day)', '10.00', '50.00', '', '0', '123', '123', '2020-05-13 03:56:17', '123', '2020-05-13 03:56:33', '1');
INSERT INTO `production_process_course` VALUES ('27', '23', null, '7', '组装', '1200.00', '/(天 day)', '500.00', '600000.00', '', '0', '123', '123', '2020-05-19 06:47:03', '123', '2020-05-19 06:47:16', '1');
INSERT INTO `production_process_course` VALUES ('28', '23', null, '8', '测试', '120.00', '/(天 day)', '50.00', '6000.00', '', '0', '123', '123', '2020-05-19 06:48:17', '123', '2020-05-19 06:48:25', '1');
INSERT INTO `production_process_course` VALUES ('29', '23', null, '9', '包装', '120.00', '/(天 day)', '10.00', '1200.00', '', '0', '123', '123', '2020-05-19 06:49:03', '123', '2020-05-19 06:49:12', '1');
INSERT INTO `production_process_course` VALUES ('30', '25', null, '7', '??', '1005.00', '/(? day)', '500.00', '502500.00', '', '0', '123', '123', '2020-05-21 00:40:48', '123', '2020-05-21 00:41:00', '1');
INSERT INTO `production_process_course` VALUES ('31', '25', null, '8', '??', '1005.00', '/(? day)', '50.00', '50250.00', '', '0', '123', '123', '2020-05-21 00:41:32', '123', '2020-05-21 00:41:53', '1');
INSERT INTO `production_process_course` VALUES ('32', '25', null, '9', '??', '1005.00', '/(? day)', '10.00', '10050.00', '', '0', '123', '123', '2020-05-21 00:41:41', '123', '2020-05-21 00:41:55', '1');
INSERT INTO `production_process_course` VALUES ('33', '26', null, '7', '组装', '1000.00', '/(天 day)', '500.00', '500000.00', '', '0', '123', '123', '2020-05-21 02:16:01', '123', '2020-05-21 02:16:23', '1');
INSERT INTO `production_process_course` VALUES ('34', '26', null, '8', '测试', '100.00', '/(天 day)', '50.00', '5000.00', '', '0', '123', '123', '2020-05-21 02:16:06', '123', '2020-05-21 02:16:28', '1');
INSERT INTO `production_process_course` VALUES ('35', '26', null, '9', '包装', '100.00', '/(天 day)', '10.00', '1000.00', '', '0', '123', '123', '2020-05-21 02:16:13', '123', '2020-05-21 02:16:31', '1');
INSERT INTO `production_process_course` VALUES ('36', '27', null, '7', '组装', '0.00', '/(天 day)', '500.00', '0.00', '', '0', '123', '123', '2020-05-21 02:46:57', '123', '2020-05-21 02:47:32', '1');
INSERT INTO `production_process_course` VALUES ('37', '27', null, '8', '测试', '0.00', '/(天 day)', '50.00', '0.00', '', '0', '123', '123', '2020-05-21 02:47:05', '123', '2020-05-21 02:47:36', '1');
INSERT INTO `production_process_course` VALUES ('38', '27', null, '9', '包装', '0.00', '/(天 day)', '10.00', '0.00', '', '0', '123', '123', '2020-05-21 02:47:09', '123', '2020-05-21 02:47:47', '1');
INSERT INTO `production_process_course` VALUES ('39', '27', null, '9', '包装', '0.00', '/(天 day)', '10.00', '0.00', '', '0', '123', '123', '2020-05-21 02:49:08', '123', '2020-05-21 02:52:04', '1');

-- ----------------------------
-- Table structure for `production_process_material`
-- ----------------------------
DROP TABLE IF EXISTS `production_process_material`;
CREATE TABLE `production_process_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父级序号 production_process',
  `process_number` int(11) DEFAULT NULL,
  `product_id` varchar(50) NOT NULL COMMENT '物料编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '物料名称',
  `cost_price` decimal(10,2) DEFAULT '0.00' COMMENT '物料单价',
  `amount` int(11) DEFAULT '0' COMMENT '设计数量',
  `renew_amount` int(11) DEFAULT '0' COMMENT '已从库存领料数量',
  `real_amount` int(11) DEFAULT '0' COMMENT '实际使用数量',
  `subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '设计物料成本小计',
  `real_subtotal` int(11) DEFAULT '0' COMMENT '实际物料成本小计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='生产工序物料表';

-- ----------------------------
-- Records of production_process_material
-- ----------------------------
INSERT INTO `production_process_material` VALUES ('28', '43', null, 'YCL20200511000001', 'cpu', '200.00', '2200', '0', '2200', '440000.00', '660000');
INSERT INTO `production_process_material` VALUES ('29', '43', null, 'YCL20200511000002', '内存条', '200.00', '1100', '0', '1100', '220000.00', '330000');
INSERT INTO `production_process_material` VALUES ('30', '45', null, 'YCL20200511000003', '电脑包装箱', '5.00', '1100', '0', '0', '5500.00', '0');
INSERT INTO `production_process_material` VALUES ('31', '46', null, 'YCL20200511000001', 'cpu', '200.00', '2400', '0', '2400', '480000.00', '480000');
INSERT INTO `production_process_material` VALUES ('32', '46', null, 'YCL20200511000002', '内存条', '200.00', '1200', '0', '1200', '240000.00', '240000');
INSERT INTO `production_process_material` VALUES ('33', '48', null, 'YCL20200511000003', '电脑包装箱', '5.00', '1200', '0', '1200', '6000.00', '6000');
INSERT INTO `production_process_material` VALUES ('34', '49', null, 'YCL20200511000001', 'cpu', '200.00', '100', '0', '100', '20000.00', '30000');
INSERT INTO `production_process_material` VALUES ('35', '49', null, 'YCL20200511000002', '内存条', '200.00', '50', '0', '50', '10000.00', '15000');
INSERT INTO `production_process_material` VALUES ('36', '51', null, 'YCL20200511000003', '电脑包装箱', '5.00', '50', '0', '50', '250.00', '250');
INSERT INTO `production_process_material` VALUES ('37', '52', null, 'YCL20200511000001', 'cpu', '200.00', '2010', '0', '2010', '402000.00', '402000');
INSERT INTO `production_process_material` VALUES ('38', '52', null, 'YCL20200511000002', '???', '200.00', '1005', '0', '1005', '201000.00', '201000');
INSERT INTO `production_process_material` VALUES ('39', '54', null, 'YCL20200511000003', '?????', '5.00', '1005', '0', '1005', '5025.00', '5025');
INSERT INTO `production_process_material` VALUES ('40', '55', null, 'YCL20200511000001', 'cpu', '200.00', '2000', '0', '2000', '400000.00', '400000');
INSERT INTO `production_process_material` VALUES ('41', '55', null, 'YCL20200511000002', '内存条', '200.00', '1000', '0', '1000', '200000.00', '200000');
INSERT INTO `production_process_material` VALUES ('42', '57', null, 'YCL20200511000003', '电脑包装箱', '5.00', '1000', '0', '0', '5000.00', '0');
INSERT INTO `production_process_material` VALUES ('43', '58', null, 'YCL20200511000001', 'cpu', '200.00', '2000', '0', '2000', '400000.00', '400000');
INSERT INTO `production_process_material` VALUES ('44', '58', null, 'YCL20200511000002', '内存条', '200.00', '1000', '0', '1000', '200000.00', '200000');
INSERT INTO `production_process_material` VALUES ('45', '60', null, 'YCL20200511000003', '电脑包装箱', '5.00', '1000', '0', '1000', '5000.00', '5000');

-- ----------------------------
-- Table structure for `production_process_material_course`
-- ----------------------------
DROP TABLE IF EXISTS `production_process_material_course`;
CREATE TABLE `production_process_material_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号 ',
  `parent_id` int(11) NOT NULL COMMENT '父级序号',
  `details_number` int(11) DEFAULT '0' COMMENT '本工序物料序号',
  `product_id` varchar(50) NOT NULL COMMENT '产品编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `cost_price` decimal(10,2) DEFAULT '0.00' COMMENT '单位物料成本',
  `use_amount` int(11) DEFAULT '0' COMMENT '本次使用数量',
  `subtotal` decimal(10,2) DEFAULT '0.00' COMMENT '物料成本小计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='生产工序物料过程记录';

-- ----------------------------
-- Records of production_process_material_course
-- ----------------------------
INSERT INTO `production_process_material_course` VALUES ('20', '19', '0', 'YCL20200511000001', 'cpu', '200.00', '50', '10000.00');
INSERT INTO `production_process_material_course` VALUES ('21', '19', '0', 'YCL20200511000002', '内存条', '200.00', '25', '5000.00');
INSERT INTO `production_process_material_course` VALUES ('22', '20', '0', 'YCL20200511000001', 'cpu', '200.00', '50', '10000.00');
INSERT INTO `production_process_material_course` VALUES ('23', '20', '0', 'YCL20200511000002', '内存条', '200.00', '25', '5000.00');
INSERT INTO `production_process_material_course` VALUES ('24', '21', '0', 'YCL20200511000001', 'cpu', '200.00', '1100', '220000.00');
INSERT INTO `production_process_material_course` VALUES ('25', '21', '0', 'YCL20200511000002', '内存条', '200.00', '550', '110000.00');
INSERT INTO `production_process_material_course` VALUES ('26', '22', '0', 'YCL20200511000001', 'cpu', '200.00', '1100', '220000.00');
INSERT INTO `production_process_material_course` VALUES ('27', '22', '0', 'YCL20200511000002', '内存条', '200.00', '550', '110000.00');
INSERT INTO `production_process_material_course` VALUES ('28', '24', '0', 'YCL20200511000003', '电脑包装箱', '5.00', '1100', '5500.00');
INSERT INTO `production_process_material_course` VALUES ('29', '26', '0', 'YCL20200511000003', '电脑包装箱', '5.00', '50', '250.00');
INSERT INTO `production_process_material_course` VALUES ('30', '27', '0', 'YCL20200511000001', 'cpu', '200.00', '2400', '480000.00');
INSERT INTO `production_process_material_course` VALUES ('31', '27', '0', 'YCL20200511000002', '内存条', '200.00', '1200', '240000.00');
INSERT INTO `production_process_material_course` VALUES ('32', '29', '0', 'YCL20200511000003', '电脑包装箱', '5.00', '1200', '6000.00');
INSERT INTO `production_process_material_course` VALUES ('33', '30', '0', 'YCL20200511000001', 'cpu', '200.00', '2010', '402000.00');
INSERT INTO `production_process_material_course` VALUES ('34', '30', '0', 'YCL20200511000002', '???', '200.00', '1005', '201000.00');
INSERT INTO `production_process_material_course` VALUES ('35', '32', '0', 'YCL20200511000003', '?????', '5.00', '1005', '5025.00');
INSERT INTO `production_process_material_course` VALUES ('36', '33', '0', 'YCL20200511000001', 'cpu', '200.00', '2000', '400000.00');
INSERT INTO `production_process_material_course` VALUES ('37', '33', '0', 'YCL20200511000002', '内存条', '200.00', '1000', '200000.00');
INSERT INTO `production_process_material_course` VALUES ('38', '35', '0', 'YCL20200511000003', '电脑包装箱', '5.00', '0', '0.00');
INSERT INTO `production_process_material_course` VALUES ('39', '36', '0', 'YCL20200511000001', 'cpu', '200.00', '2000', '400000.00');
INSERT INTO `production_process_material_course` VALUES ('40', '36', '0', 'YCL20200511000002', '内存条', '200.00', '1000', '200000.00');
INSERT INTO `production_process_material_course` VALUES ('41', '38', '0', 'YCL20200511000003', '电脑包装箱', '5.00', '1000', '5000.00');
INSERT INTO `production_process_material_course` VALUES ('42', '39', '0', 'YCL20200511000003', '电脑包装箱', '5.00', '0', '0.00');

-- ----------------------------
-- Table structure for `product_kind`
-- ----------------------------
DROP TABLE IF EXISTS `product_kind`;
CREATE TABLE `product_kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL COMMENT '父级产品类别',
  `kindName` varchar(20) DEFAULT NULL COMMENT '产品种类名称',
  `describe` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='产品分类表';

-- ----------------------------
-- Records of product_kind
-- ----------------------------
INSERT INTO `product_kind` VALUES ('1', null, '电子产品', null);
INSERT INTO `product_kind` VALUES ('2', '1', '计算机', null);
INSERT INTO `product_kind` VALUES ('3', '2', '联想小清新笔记本电脑', null);

-- ----------------------------
-- Table structure for `product_material_design`
-- ----------------------------
DROP TABLE IF EXISTS `product_material_design`;
CREATE TABLE `product_material_design` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `design_id` varchar(20) NOT NULL COMMENT '设计编号',
  `product_id` varchar(20) NOT NULL COMMENT '产品编号',
  `designer` varchar(50) NOT NULL COMMENT '设计人',
  `module_describe` varchar(400) DEFAULT NULL COMMENT '设计要求',
  `register` varchar(20) NOT NULL COMMENT '登记人',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记时间',
  `checker` varchar(20) DEFAULT NULL COMMENT '复核人',
  `check_time` datetime DEFAULT NULL COMMENT '复核时间',
  `changer` varchar(20) DEFAULT NULL COMMENT '变更人',
  `change_time` datetime DEFAULT NULL COMMENT '变更时间',
  `check_tag` int(11) NOT NULL DEFAULT '0' COMMENT '0等待审核 1审核通过 2审核未通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品物料组成';

-- ----------------------------
-- Records of product_material_design
-- ----------------------------
INSERT INTO `product_material_design` VALUES ('8', 'CPWLSJ20200511000001', 'CP20200511000001', '', '设计要求: ', '123', '2020-05-11 08:49:29', '123', '2020-05-11 00:49:35', null, null, '1');
INSERT INTO `product_material_design` VALUES ('9', 'CPWLSJ20200511000002', 'CP20200511000002', '', '设计要求: ', '123', '2020-05-11 09:29:20', '123', '2020-05-11 01:29:26', null, null, '1');
INSERT INTO `product_material_design` VALUES ('10', 'CPWLSJ20200518000001', 'CP20200518000001', '', '设计要求: ', '123', '2020-05-18 10:18:53', '123', '2020-05-21 02:11:00', null, null, '1');

-- ----------------------------
-- Table structure for `product_process`
-- ----------------------------
DROP TABLE IF EXISTS `product_process`;
CREATE TABLE `product_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processName` varchar(50) NOT NULL COMMENT '工序名称',
  `message` varchar(200) DEFAULT NULL COMMENT '工序描述',
  `state` int(11) DEFAULT '1' COMMENT '1可用 2不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='工序设计表 就是有那些工序';

-- ----------------------------
-- Records of product_process
-- ----------------------------
INSERT INTO `product_process` VALUES ('7', '组装', '', '1');
INSERT INTO `product_process` VALUES ('8', '测试', '', '1');
INSERT INTO `product_process` VALUES ('9', '包装', '', '1');

-- ----------------------------
-- Table structure for `product_process_design`
-- ----------------------------
DROP TABLE IF EXISTS `product_process_design`;
CREATE TABLE `product_process_design` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `process_id` varchar(50) NOT NULL COMMENT '产品工序设计',
  `product_id` varchar(50) NOT NULL COMMENT '产品编号',
  `message` varchar(400) DEFAULT NULL COMMENT '设计要求',
  `manhour_cost_price_sum` decimal(10,2) DEFAULT NULL COMMENT '工时总成本',
  `material_cost_price_sum` decimal(10,2) DEFAULT NULL COMMENT '物料总成本',
  `designer` varchar(50) DEFAULT NULL COMMENT '设计人',
  `register` varchar(50) DEFAULT NULL COMMENT '登记人',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记时间',
  `checker` varchar(50) DEFAULT NULL COMMENT '检验员',
  `check_time` datetime DEFAULT NULL COMMENT '检验时间',
  `check_suggestion` varchar(400) DEFAULT NULL COMMENT '审核意见',
  `check_tag` int(11) DEFAULT '0' COMMENT '0等待审核 1审核通过 2审核未通过',
  `changer` varchar(50) DEFAULT NULL COMMENT '改变者',
  `change_time` datetime DEFAULT NULL COMMENT '改变时间',
  `process_material_tag` varchar(20) NOT NULL DEFAULT '-1' COMMENT '工序物料状态 -1为默认的也就是没有设计 0工序物料设计再审核中或者处于驳回状态 1物料已经设计 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='产品工序设计表 ';

-- ----------------------------
-- Records of product_process_design
-- ----------------------------
INSERT INTO `product_process_design` VALUES ('6', 'GXSJ20200511000001', 'CP20200511000001', '', '506.00', '605.00', '123', '123', '2020-05-11 08:53:56', '123', '2020-05-11 01:00:31', null, '1', null, null, '1');
INSERT INTO `product_process_design` VALUES ('7', 'GXSJ20200521000001', 'CP20200511000002', '', '0.00', '605.00', '123', '123', '2020-05-21 10:53:47', '123', '2020-05-21 02:54:23', null, '1', null, null, '1');
INSERT INTO `product_process_design` VALUES ('8', 'GXSJ20200522000002', 'CP20200518000001', '', '101.80', null, '123', '123', '2020-05-22 08:36:14', '123', '2020-05-22 00:36:43', null, '1', null, null, '1');

-- ----------------------------
-- Table structure for `product_use`
-- ----------------------------
DROP TABLE IF EXISTS `product_use`;
CREATE TABLE `product_use` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useName` varchar(20) DEFAULT NULL COMMENT '用途名称',
  `describe` varchar(100) DEFAULT NULL COMMENT '备注',
  `parent_id` int(11) DEFAULT NULL COMMENT '根useid对应',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='产品用途表';

-- ----------------------------
-- Records of product_use
-- ----------------------------
INSERT INTO `product_use` VALUES ('1', '商品', '需要生产的商品', '1');
INSERT INTO `product_use` VALUES ('2', '部件', '需要生产的部件', '2');
INSERT INTO `product_use` VALUES ('3', '物料', '需要采购的物料', '3');

-- ----------------------------
-- Table structure for `provideinfo`
-- ----------------------------
DROP TABLE IF EXISTS `provideinfo`;
CREATE TABLE `provideinfo` (
  `peoid` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商编号',
  `peoname` varchar(50) NOT NULL COMMENT '供应商全称',
  `peoshortname` varchar(50) DEFAULT NULL COMMENT '供应商简称',
  `peoascription` varchar(50) DEFAULT NULL COMMENT '帐款归属',
  `peopeeid` int(11) DEFAULT NULL COMMENT '供应商类型表id',
  `peopeename` varchar(50) DEFAULT NULL COMMENT '供应商类型表名称（新增）',
  `peoarea` varchar(50) DEFAULT NULL COMMENT '地区',
  `peoprincipal` varchar(50) DEFAULT NULL COMMENT '负责人',
  `peolinkman` varchar(50) DEFAULT NULL COMMENT '联系人',
  `peophone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `peobankaccounts` varchar(50) DEFAULT NULL COMMENT '银行帐号',
  `peoemail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `peoremainedu` double DEFAULT NULL COMMENT '剩余额度',
  `peomonthreckoning` double DEFAULT NULL COMMENT '31	每月结帐日',
  `Peoqcprepaymoney` double DEFAULT NULL COMMENT '期初预付款',
  `peoqcdealmoney` double DEFAULT NULL COMMENT '期初应付款',
  `peoqmprepaymoney` double DEFAULT NULL COMMENT '期末预付款',
  `peoqmdealmoney` double DEFAULT NULL COMMENT '期末应付款',
  `peoremark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`peoid`),
  KEY `fk_e` (`peopeeid`),
  CONSTRAINT `fk_e` FOREIGN KEY (`peopeeid`) REFERENCES `providetype` (`peeid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of provideinfo
-- ----------------------------
INSERT INTO `provideinfo` VALUES ('2', '长沙集团', '可以的', null, null, '成品', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `provideinfo` VALUES ('3', '邵阳集团', '可以的', null, null, '物料', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `providetype`
-- ----------------------------
DROP TABLE IF EXISTS `providetype`;
CREATE TABLE `providetype` (
  `peeid` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `peename` varchar(50) NOT NULL COMMENT '类别名称',
  `peeremark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`peeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of providetype
-- ----------------------------

-- ----------------------------
-- Table structure for `recededetails`
-- ----------------------------
DROP TABLE IF EXISTS `recededetails`;
CREATE TABLE `recededetails` (
  `recid` int(11) NOT NULL COMMENT '采购退货单号(stockrecede外键)',
  `detsequ` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号(联合主键)',
  `matid` varchar(50) DEFAULT NULL COMMENT '	物料编号(非外键)',
  `matname` varchar(50) DEFAULT NULL COMMENT '	物料名称',
  `detnum` double DEFAULT NULL COMMENT '数量',
  `detagioprice` double DEFAULT NULL COMMENT '折扣前单价',
  `detdiscount` double DEFAULT NULL COMMENT '折数',
  `detprice` double DEFAULT NULL COMMENT '单价',
  `detamtmoney` double DEFAULT NULL COMMENT '金额',
  `detdate` date NOT NULL COMMENT '日期',
  `detmark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`detsequ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of recededetails
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '16');
INSERT INTO `role_permission` VALUES ('1', '18');
INSERT INTO `role_permission` VALUES ('1', '19');
INSERT INTO `role_permission` VALUES ('1', '14');
INSERT INTO `role_permission` VALUES ('1', '15');
INSERT INTO `role_permission` VALUES ('1', '12');
INSERT INTO `role_permission` VALUES ('1', '27');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '13');
INSERT INTO `role_permission` VALUES ('1', '17');
INSERT INTO `role_permission` VALUES ('1', '21');
INSERT INTO `role_permission` VALUES ('1', '22');
INSERT INTO `role_permission` VALUES ('1', '23');
INSERT INTO `role_permission` VALUES ('1', '24');
INSERT INTO `role_permission` VALUES ('1', '28');
INSERT INTO `role_permission` VALUES ('1', '30');
INSERT INTO `role_permission` VALUES ('1', '31');
INSERT INTO `role_permission` VALUES ('1', '32');

-- ----------------------------
-- Table structure for `safetystock`
-- ----------------------------
DROP TABLE IF EXISTS `safetystock`;
CREATE TABLE `safetystock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `safetystockId` varchar(50) DEFAULT NULL COMMENT '配置单编号',
  `productId` varchar(50) NOT NULL COMMENT '产品编号',
  `minamount` int(11) NOT NULL COMMENT '库存报警下限数',
  `maxamount` int(11) NOT NULL COMMENT '库存报警上限数',
  `sid` int(11) NOT NULL COMMENT '仓库编号',
  `maxCapacityAmount` int(11) NOT NULL COMMENT '最大存储量',
  `storeUnit` varchar(5) DEFAULT NULL COMMENT '存储单位',
  `register` varchar(10) NOT NULL COMMENT '设计人',
  `registertime` datetime NOT NULL COMMENT '设计时间',
  `checker` varchar(10) DEFAULT NULL COMMENT '审核人',
  `checktime` datetime DEFAULT NULL COMMENT '审核时间',
  `checktag` varchar(20) NOT NULL DEFAULT '等待审核' COMMENT '审核状态:等待审核,审核通过,审核不通过',
  `configStatus` varchar(20) NOT NULL DEFAULT '等待' COMMENT '配置状态:0.等待,1.完成,2.没有完成',
  `changeStatus` varchar(20) DEFAULT '未变更' COMMENT '变更状态:已变更,未变更',
  `qwe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='安全库存配置表';

-- ----------------------------
-- Records of safetystock
-- ----------------------------
INSERT INTO `safetystock` VALUES ('12', '8d93443d26ca0421e8bdac1', 'CP20200511000002', '100', '600', '1', '1000', '件', '小汤', '2020-05-23 01:10:22', '孙陆泉', '2020-05-23 01:12:18', '审核通过', '完成', '未变更', null);
INSERT INTO `safetystock` VALUES ('13', 'a8e98d526ddc3158f37ac6a', 'CP20200511000002', '400', '900', '2', '1100', '件', '小汤', '2020-05-23 01:10:36', '孙陆泉', '2020-05-23 01:16:20', '审核通过', '完成', '未变更', null);
INSERT INTO `safetystock` VALUES ('14', '8fadade9a4a1ec1f5975bfa', 'YCL20200511000002', '360', '1000', '3', '1200', '件', '小汤', '2020-05-23 01:10:53', '孙陆泉', '2020-05-23 01:16:37', '审核通过', '完成', '未变更', null);
INSERT INTO `safetystock` VALUES ('15', '2116276535c18fa69852919', 'YCL20200511000002', '200', '900', '4', '1400', '件', '小汤', '2020-05-23 01:11:05', '孙陆泉', '2020-05-23 01:16:41', '审核通过', '完成', '未变更', null);

-- ----------------------------
-- Table structure for `spay`
-- ----------------------------
DROP TABLE IF EXISTS `spay`;
CREATE TABLE `spay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `payId` varchar(20) NOT NULL COMMENT '出库单编号',
  `storer` varchar(10) NOT NULL COMMENT '出库人',
  `amountSum` int(11) DEFAULT NULL COMMENT '出库总件数',
  `costPriceSum` int(11) DEFAULT NULL COMMENT '总金额',
  `reasonexact` varchar(30) DEFAULT NULL COMMENT '出库详细理由',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `register` varchar(10) NOT NULL COMMENT '登记人',
  `registerTime` datetime DEFAULT NULL COMMENT '登记时间',
  `checker` varchar(10) DEFAULT NULL COMMENT '审核人',
  `checkTime` datetime DEFAULT NULL COMMENT '审核时间',
  `checkTag` varchar(10) DEFAULT '等待审核' COMMENT '审核状态 等待审核、审核通过、审核未通过',
  `checkNotReason` varchar(50) DEFAULT NULL COMMENT '审核未通过的理由',
  `attemper` varchar(10) DEFAULT NULL COMMENT '调度人',
  `attemmperTime` datetime DEFAULT NULL COMMENT '调度时间',
  `storeTag` varchar(10) DEFAULT '未调度' COMMENT '库存标记 已登记、已调度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库表';

-- ----------------------------
-- Records of spay
-- ----------------------------

-- ----------------------------
-- Table structure for `spaydetails`
-- ----------------------------
DROP TABLE IF EXISTS `spaydetails`;
CREATE TABLE `spaydetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `parentId` varchar(20) NOT NULL COMMENT '父级序号',
  `warehouseId` int(11) DEFAULT NULL COMMENT '仓库编号',
  `productId` varchar(50) NOT NULL COMMENT '产品编号',
  `productName` varchar(50) NOT NULL COMMENT '产品名称',
  `amount` int(11) NOT NULL COMMENT '申请出库数量',
  `detailSum` int(11) DEFAULT '0' COMMENT '本次出库数量',
  `checkInSum` int(11) DEFAULT '0' COMMENT '登记出库数量',
  `amounUnit` varchar(5) DEFAULT NULL COMMENT '单位',
  `costPrice` double DEFAULT NULL COMMENT '单价',
  `payTag` varchar(10) DEFAULT '未调度' COMMENT '出库标志:1.已登记,2.已调度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库明细表';

-- ----------------------------
-- Records of spaydetails
-- ----------------------------

-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `stockId` varchar(50) DEFAULT NULL COMMENT '库存编号',
  `productId` varchar(20) NOT NULL COMMENT '产品编号',
  `sid` int(11) DEFAULT NULL COMMENT '仓库编号',
  `amount` int(11) NOT NULL COMMENT '当前存储量',
  `registMan` varchar(20) NOT NULL COMMENT '登记人',
  `registTime` datetime DEFAULT NULL COMMENT '登记时间',
  `checkMan` varchar(20) DEFAULT NULL COMMENT '复核人',
  `checkTime` datetime DEFAULT NULL COMMENT '复核时间',
  `checkTag` varchar(10) DEFAULT '已审核' COMMENT '审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='库存表';

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('12', 'afb76cdee0', 'CP20200511000002', '2', '770', '孙陆泉', '2020-05-23 01:16:20', '孙陆泉', '2020-05-23 01:23:42', '已审核');
INSERT INTO `stock` VALUES ('13', '41bab6c636', 'YCL20200511000002', '3', '0', '孙陆泉', '2020-05-23 01:16:37', null, null, '未审核');
INSERT INTO `stock` VALUES ('14', '41d819065a', 'YCL20200511000002', '4', '1070', '孙陆泉', '2020-05-23 01:16:41', '孙陆泉', '2020-05-23 01:23:42', '已审核');

-- ----------------------------
-- Table structure for `stockapply`
-- ----------------------------
DROP TABLE IF EXISTS `stockapply`;
CREATE TABLE `stockapply` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '请购单编号',
  `appid` varchar(20) DEFAULT NULL COMMENT '请购单编号',
  `appdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '请购单日期',
  `apptype` varchar(20) NOT NULL COMMENT '请购采购类型  商品采购 原材料采购 用品采购',
  `appcirce` int(11) NOT NULL DEFAULT '0' COMMENT '	默认0 未结案，1：已结案  2：无效',
  `je` int(11) DEFAULT NULL,
  `appperson` varchar(50) NOT NULL DEFAULT '张佳豪' COMMENT '请购人员',
  `apppersonid` varchar(50) DEFAULT NULL COMMENT '请购人员id',
  `appauditingperson` varchar(20) DEFAULT NULL COMMENT '复核人员',
  `appauditing` int(11) NOT NULL DEFAULT '0' COMMENT '	默认0 未审核，1：已审核  2：无效',
  `enables` int(11) DEFAULT '0' COMMENT '	默认0 不删除，1：删除 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockapply
-- ----------------------------
INSERT INTO `stockapply` VALUES ('36', '950976195', '2020-05-13 14:09:55', '成品', '0', '1680', '张佳豪', null, null, '1', '0');
INSERT INTO `stockapply` VALUES ('37', '916343375', '2020-05-13 14:39:01', '成品', '0', '13087', '张佳豪', null, null, '1', '0');
INSERT INTO `stockapply` VALUES ('38', '688775219', '2020-05-14 11:14:52', '物料', '0', '76', '张佳豪', null, null, '0', '0');

-- ----------------------------
-- Table structure for `stockapplydetails`
-- ----------------------------
DROP TABLE IF EXISTS `stockapplydetails`;
CREATE TABLE `stockapplydetails` (
  `appid` int(20) NOT NULL AUTO_INCREMENT COMMENT '请购明细单编号',
  `apdrows` int(11) NOT NULL COMMENT '外键 请购主表id',
  `apdmateridlid` varchar(30) NOT NULL COMMENT '物料编号',
  `apdmateridlname` varchar(20) NOT NULL COMMENT '物料名称',
  `apdunit` int(11) NOT NULL COMMENT '数量',
  `listprice` decimal(10,2) NOT NULL COMMENT '市场单价*',
  `costprice` decimal(10,2) NOT NULL COMMENT '计划成本价*',
  `realcos` decimal(10,2) NOT NULL COMMENT '实际的成本价',
  `apdintomoney` double NOT NULL COMMENT '进价金额',
  `appdate` date NOT NULL COMMENT '日期',
  `apdremark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockapplydetails
-- ----------------------------
INSERT INTO `stockapplydetails` VALUES ('2', '325021304', 'CP20200511000002', '小笔记本电脑', '10', '6000.00', '3000.00', '569.00', '6000', '2020-05-13', null);
INSERT INTO `stockapplydetails` VALUES ('3', '950976195', 'CP20200511000001', '笔记本电脑', '1', '6000.00', '3000.00', '1111.00', '6000', '2020-05-13', null);
INSERT INTO `stockapplydetails` VALUES ('4', '950976195', 'CP20200511000002', '小笔记本电脑', '1', '6000.00', '3000.00', '569.00', '6000', '2020-05-13', null);
INSERT INTO `stockapplydetails` VALUES ('5', '916343375', 'CP20200511000002', '小笔记本电脑', '23', '6000.00', '3000.00', '569.00', '6000', '2020-05-13', null);
INSERT INTO `stockapplydetails` VALUES ('6', '688775219', 'YCL20200511000001', 'cpu', '1', '500.00', '200.00', '12.00', '500', '2020-05-14', null);
INSERT INTO `stockapplydetails` VALUES ('7', '688775219', 'YCL20200511000002', '内存条', '1', '500.00', '200.00', '12.00', '500', '2020-05-14', null);
INSERT INTO `stockapplydetails` VALUES ('8', '688775219', 'YCL20200511000003', '电脑包装箱', '1', '10.00', '5.00', '52.00', '10', '2020-05-14', null);

-- ----------------------------
-- Table structure for `stockapplytype`
-- ----------------------------
DROP TABLE IF EXISTS `stockapplytype`;
CREATE TABLE `stockapplytype` (
  `aptid` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型编号',
  `aptname` varchar(20) NOT NULL COMMENT '类型名称',
  `aptremark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`aptid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockapplytype
-- ----------------------------

-- ----------------------------
-- Table structure for `stockorder`
-- ----------------------------
DROP TABLE IF EXISTS `stockorder`;
CREATE TABLE `stockorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stoid` int(11) DEFAULT NULL COMMENT '单据号',
  `stodate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日期',
  `stopeoname` varchar(20) DEFAULT NULL COMMENT '供货商',
  `stopeoid` int(11) DEFAULT NULL COMMENT '供货商id',
  `stopeoadress` varchar(50) DEFAULT NULL COMMENT '供货商地址',
  `stostate` int(11) NOT NULL DEFAULT '0' COMMENT '单况：0-未结案，1-已结案，2-无效',
  `stoadress` varchar(50) DEFAULT NULL COMMENT '送货地址',
  `stoempstock` varchar(10) DEFAULT '张佳豪' COMMENT '采购人员',
  `je` int(11) DEFAULT NULL,
  `stopersonid` int(11) DEFAULT NULL COMMENT '采购人员id',
  `stodept` varchar(20) DEFAULT NULL COMMENT '采购部门',
  `stodeptid` int(11) DEFAULT NULL COMMENT '采购部门id',
  `stoempcheck` varchar(10) DEFAULT NULL COMMENT '复核人员',
  `checkstate` int(11) NOT NULL DEFAULT '0' COMMENT '默认0 未审核，1：已审核  2：无效',
  `stomark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockorder
-- ----------------------------
INSERT INTO `stockorder` VALUES ('4', '958324371', '2020-05-15 15:51:14', '长沙集团', null, null, '0', null, '张佳豪', '1211', null, '采购部', null, null, '1', null);
INSERT INTO `stockorder` VALUES ('5', '16458915', '2020-05-13 14:39:53', '长沙集团', null, null, '0', null, '张佳豪', '69000', null, '采购部', null, null, '1', null);

-- ----------------------------
-- Table structure for `stockorderdetails`
-- ----------------------------
DROP TABLE IF EXISTS `stockorderdetails`;
CREATE TABLE `stockorderdetails` (
  `sodid` int(11) NOT NULL AUTO_INCREMENT COMMENT '单据号',
  `sodlineid` int(11) DEFAULT NULL COMMENT '外键',
  `sodmaterielid` varchar(30) DEFAULT NULL COMMENT '物料编号',
  `sodmaterielname` varchar(20) NOT NULL COMMENT '物料名称',
  `sodamoun` int(11) NOT NULL COMMENT '数量',
  `sodpreprice` double DEFAULT NULL COMMENT '折扣前单价',
  `sodagio` double DEFAULT NULL COMMENT '折数（%）',
  `sodprice` double DEFAULT NULL COMMENT '单价',
  `sodsum` double NOT NULL COMMENT '进价金额',
  `sodsdate` date NOT NULL COMMENT '日期',
  `sodsmark` varchar(500) DEFAULT NULL COMMENT '备注',
  `sodrecede` int(11) DEFAULT '1' COMMENT '是否退货 0 需要退货，1   不需要退货',
  PRIMARY KEY (`sodid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockorderdetails
-- ----------------------------
INSERT INTO `stockorderdetails` VALUES ('2', '958324371', 'CP20200511000001', '笔记本电脑', '1', null, null, null, '1111', '2020-05-13', null, '1');
INSERT INTO `stockorderdetails` VALUES ('3', '958324371', 'CP20200511000002', '小笔记本电脑', '1', null, null, null, '100', '2020-05-13', null, '1');
INSERT INTO `stockorderdetails` VALUES ('4', '16458915', 'CP20200511000002', '小笔记本电脑', '23', null, null, null, '3000', '2020-05-13', null, '1');

-- ----------------------------
-- Table structure for `stockrecede`
-- ----------------------------
DROP TABLE IF EXISTS `stockrecede`;
CREATE TABLE `stockrecede` (
  `recid` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购退货单号',
  `recdate` date NOT NULL COMMENT '单据日期',
  `peoid` int(11) NOT NULL COMMENT '供应商编号',
  `purveyname` varchar(50) DEFAULT NULL COMMENT '供货商名称',
  `peoaddress` varchar(50) DEFAULT NULL COMMENT '供应商地址',
  `stoid` int(11) DEFAULT NULL COMMENT '仓库编号',
  `depotname` varchar(20) DEFAULT NULL COMMENT '仓库名称',
  `anewstorage` double DEFAULT NULL COMMENT '是否重新入库',
  `stoperson` varchar(50) NOT NULL COMMENT '	采购人员姓名',
  `stopersonid` int(11) DEFAULT NULL COMMENT '采购人员ID',
  `stodept` varchar(50) DEFAULT NULL COMMENT '请购部门',
  `stodeptid` int(11) NOT NULL COMMENT 'Y		部门编号',
  `auditingperson` varchar(50) DEFAULT NULL COMMENT '		复核人员',
  `recremark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `estate` int(11) NOT NULL DEFAULT '0' COMMENT '默认0 未审核，1：已审核  2：无效',
  PRIMARY KEY (`recid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockrecede
-- ----------------------------
INSERT INTO `stockrecede` VALUES ('1', '2020-05-15', '1', '不知道', '不清楚', '1', '在那里', '1', '路人甲', '1', '部门乙', '1', '上司丙', '驳回原由:', '2');
INSERT INTO `stockrecede` VALUES ('2', '2020-05-15', '1', '不知道2', '不清楚2', '1', '在那里2', '1', '路人甲2', '1', '部门乙2', '1', '上司丙2', null, '1');
INSERT INTO `stockrecede` VALUES ('3', '2020-05-15', '1', '不知道2', '不清楚2', '1', '在那里2', '1', '路人甲2', '1', '部门乙2', '1', '上司丙2', '驳回原由:', '2');
INSERT INTO `stockrecede` VALUES ('4', '2020-05-15', '1', '不知道2', '不清楚2', '1', '在那里2', '1', '路人甲2', '1', '部门乙2', '1', '上司丙2', null, '1');

-- ----------------------------
-- Table structure for `stockstoragedetails`
-- ----------------------------
DROP TABLE IF EXISTS `stockstoragedetails`;
CREATE TABLE `stockstoragedetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单据号',
  `storagedetailno` int(11) DEFAULT NULL COMMENT 'N	Y	Y		引用采购入库单单号',
  `materielid` varchar(30) DEFAULT NULL COMMENT 'N物料ID',
  `materielname` varchar(50) DEFAULT NULL COMMENT 'N				物料名称',
  `units` varchar(20) DEFAULT NULL COMMENT 'N				单位',
  `amount` int(11) DEFAULT NULL COMMENT 'N				数量',
  `unitprice` double DEFAULT NULL COMMENT 'N				单价',
  `moneys` double DEFAULT NULL COMMENT 'N				金额',
  `enables` int(11) DEFAULT NULL COMMENT '默认1不删除，0：删除	标识是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockstoragedetails
-- ----------------------------
INSERT INTO `stockstoragedetails` VALUES ('1', '794910196', 'CP20200511000001', '笔记本电脑', null, '1', null, '1111', null);
INSERT INTO `stockstoragedetails` VALUES ('2', '794910196', 'CP20200511000002', '小笔记本电脑', null, '1', null, '100', null);
INSERT INTO `stockstoragedetails` VALUES ('3', '657400111', 'CP20200511000002', '小笔记本电脑', null, '23', null, '3000', null);

-- ----------------------------
-- Table structure for `stockstoragetable`
-- ----------------------------
DROP TABLE IF EXISTS `stockstoragetable`;
CREATE TABLE `stockstoragetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单据号',
  `cid` int(11) DEFAULT NULL,
  `stockstorageno` int(11) DEFAULT NULL COMMENT 'N	Y	N		单号（自动生成',
  `storagedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '	N				单据日期',
  `purveyid` int(11) DEFAULT NULL COMMENT 'N				供货商ID',
  `purveyname` varchar(50) DEFAULT NULL COMMENT 'N				供货商名称',
  `purveyaddress` varchar(50) DEFAULT NULL COMMENT 'Y				供货商地址',
  `depotid` int(11) DEFAULT NULL COMMENT 'N				仓库ID',
  `je` int(11) DEFAULT NULL,
  `depotname` varchar(50) DEFAULT NULL COMMENT 'n				仓库名称',
  `stoperson` varchar(50) DEFAULT '张佳豪' COMMENT 'N				采购人员',
  `stodept` varchar(50) DEFAULT NULL COMMENT 'n				采购人员所属部门名称',
  `stodeptid` int(11) DEFAULT NULL COMMENT 'N				部门ID',
  `audituserid` varchar(50) DEFAULT NULL COMMENT 'Y				审核人员ID',
  `repeatuserid` varchar(50) DEFAULT NULL COMMENT 'Y				复核人员ID',
  `remark` varchar(2000) DEFAULT NULL COMMENT 'Y				备注',
  `estate` int(11) DEFAULT '0' COMMENT 'N			Defaule(0)	单据状态（是否以审核）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stockstoragetable
-- ----------------------------
INSERT INTO `stockstoragetable` VALUES ('22', '958324371', '794910196', '2020-05-13 14:38:24', null, '长沙集团', null, null, '1211', '成品库1', '张佳豪', '采购部', null, null, null, null, '1');
INSERT INTO `stockstoragetable` VALUES ('23', '16458915', '657400111', '2020-05-13 14:39:53', null, '长沙集团', null, null, '69000', '成品库1', '张佳豪', '采购部', null, null, null, null, '1');

-- ----------------------------
-- Table structure for `storehouse`
-- ----------------------------
DROP TABLE IF EXISTS `storehouse`;
CREATE TABLE `storehouse` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '仓库编号',
  `storeName` varchar(20) NOT NULL COMMENT '仓库名称',
  `storeAddress` varchar(50) NOT NULL COMMENT '仓库地址',
  `maxamount` int(11) NOT NULL COMMENT '仓库最大存放数量',
  `use_type` int(11) DEFAULT NULL COMMENT '用途类型id*',
  `storeStatus` int(11) DEFAULT '0' COMMENT '仓库状态 0：可用 1：不可用',
  `storeRemark` varchar(20) DEFAULT NULL COMMENT '仓库说明',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storehouse
-- ----------------------------
INSERT INTO `storehouse` VALUES ('1', '成品库1', '湖南长沙岳麓区', '34000', '1', '0', '存放成品的仓库1');
INSERT INTO `storehouse` VALUES ('2', '成品库2', '北京市海淀区', '5000', '1', '0', '存放成品的仓库2');
INSERT INTO `storehouse` VALUES ('3', '物料库1', '上海市南京路', '7000', '3', '0', '存放物料的仓库1');
INSERT INTO `storehouse` VALUES ('4', '物料库2', '安徽省余杭区', '6200', '3', '0', '存放物料的仓库2');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `pId` int(11) NOT NULL AUTO_INCREMENT,
  `pName` varchar(50) DEFAULT NULL,
  `initial` varchar(20) DEFAULT '' COMMENT '首字母',
  `pType` int(11) DEFAULT '1' COMMENT '1.资源权限  2.菜单权限',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `url` varchar(100) DEFAULT NULL COMMENT '当为菜单权限时的跳转路径',
  `resource` varchar(100) DEFAULT NULL COMMENT '当为资源权限的资源',
  `permissionStatus` int(11) DEFAULT '1' COMMENT '1可用 2禁用',
  `sort` int(11) DEFAULT NULL,
  `describe` varchar(225) DEFAULT NULL,
  `isFixed` int(11) DEFAULT '1' COMMENT '1不固定 2固定',
  `parentId` int(11) DEFAULT NULL,
  `showMenu` bit(1) DEFAULT b'0' COMMENT '0:true 1:fals true显示到菜单  不显示到菜单',
  `componentName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '系統管理', 'xtgl', '2', 'el-icon-s-tools', null, null, '1', '1', '系统管理的菜单', '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('2', '基本管理', 'jbgl', '2', 'el-icon-s-management', null, null, '1', '2', null, '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('4', '用户管理', 'yhgl', '2', 'el-icon-user-solid', 'user.html', null, '1', '1', '用户管理', '1', '1', '', 'user/user');
INSERT INTO `sys_permission` VALUES ('5', '用户权限', 'yhqx', '1', null, null, 'user:*', '1', '5', '用户的相关管理', '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('6', '用户新增权限', 'yhxzqx', '1', null, null, 'user:insert', '1', '6', '用户新增权限', '1', '5', '', null);
INSERT INTO `sys_permission` VALUES ('7', '用户修改权限', 'yhxgqx', '1', null, null, 'user:update', '1', '7', '用户修改权限', '1', '5', '', null);
INSERT INTO `sys_permission` VALUES ('8', '个人中心', 'grzx', '2', 'el-icon-s-home', ' personalcenter.html', null, '1', '8', '自己的基本信息管理', '1', '2', '', 'system/personalcenter');
INSERT INTO `sys_permission` VALUES ('9', '角色管理', 'jsgl', '2', null, 'role.html', null, '1', '9', '角色管理', '1', '1', '', 'role/role');
INSERT INTO `sys_permission` VALUES ('10', '权限管理', 'qxgl', '2', 'el-icon-s-grid', 'permission.html', null, '1', '10', '权限管理', '1', '1', '', 'permission/permission');
INSERT INTO `sys_permission` VALUES ('11', '导航一', 'dhy', '2', null, 'insert', null, '1', '11', '新增权限', '1', '1', '', null);
INSERT INTO `sys_permission` VALUES ('12', '导航二', 'dhe', '1', null, null, null, '1', '12', null, '1', '11', '', null);
INSERT INTO `sys_permission` VALUES ('13', '123', '123', '2', 'el-icon-platform-eleme', '123', null, '1', '13', '123', '1', null, '', '123');
INSERT INTO `sys_permission` VALUES ('14', '新增权限', 'xzqx', '2', null, 'insert', null, '1', '14', '新增权限', '1', '10', '', 'permission/insertPermission');
INSERT INTO `sys_permission` VALUES ('15', '修改权限', 'xgqx', '2', null, 'update/:pId', null, '1', '15', null, '1', '10', '', 'permission/updatePermission');
INSERT INTO `sys_permission` VALUES ('16', '新增用户', 'xzyh', '2', null, 'insert', null, '1', '16', null, '1', '4', '', 'user/insert_user');
INSERT INTO `sys_permission` VALUES ('17', '修改用户', 'xgyh', '2', 'el-icon-platform-eleme', 'update/:uid', null, '1', '17', null, '1', null, '', 'user/update_user');
INSERT INTO `sys_permission` VALUES ('18', '新增角色', 'xzjs', '2', null, 'insert', null, '1', '18', null, '1', '9', '', 'role/insertRole');
INSERT INTO `sys_permission` VALUES ('19', '修改角色', 'xgjs', '2', null, 'update/:rid', null, '1', '19', null, '1', '9', '', 'role/updateRole');
INSERT INTO `sys_permission` VALUES ('20', '产品设计管理', 'cpsjgl', '2', 'el-icon-s-tools', null, null, '1', '20', null, '1', '25', '', null);
INSERT INTO `sys_permission` VALUES ('21', '产品设计', 'cpsj', '2', null, 'product.html', null, '1', '21', null, '1', '20', '', 'production/productdesign/product_design');
INSERT INTO `sys_permission` VALUES ('22', '产品设计核审', 'cpsjhs', '2', null, 'checkerProduct.html', null, '1', '22', null, '1', '20', '', 'production/productdesign/checker_product_design');
INSERT INTO `sys_permission` VALUES ('23', '产品物料组成设计', 'cpwlzcsj', '2', null, 'materialConstitute.html', null, '1', '23', null, '1', '26', '', 'production/material_constitute/material_constitute');
INSERT INTO `sys_permission` VALUES ('24', '产品物料组成设计核审', 'cpwlzcsjhs', '2', null, 'checkerProductMaterialDesign.html', null, '1', '24', null, '1', '26', '', 'production/material_constitute/checker_product_material_design');
INSERT INTO `sys_permission` VALUES ('25', '产品管理', 'cpgl', '2', 'el-icon-box', null, null, '1', '25', null, '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('26', '产品物料设计管理', 'cpwlsjgl', '2', null, null, null, '1', '26', null, '1', '25', '', null);
INSERT INTO `sys_permission` VALUES ('27', '123', '123', '2', null, null, null, '1', '27', null, '1', '1', '', null);
INSERT INTO `sys_permission` VALUES ('28', '产品物料设计查询', 'cpwlsjcx', '2', 'null', 'productMaterialDesign.html', null, '1', '28', null, '1', '26', '', 'production/material_constitute/product_material_design');
INSERT INTO `sys_permission` VALUES ('29', '生产管理', 'scgl', '2', 'el-icon-place', null, null, '1', '29', null, '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('30', '产品生产工序设计', 'cpscgxsj', '2', null, 'makingProcess.html', null, '1', '30', null, '1', '29', '', 'production/process_design/making_process');
INSERT INTO `sys_permission` VALUES ('31', '工序设计核审', 'gxsjhs', '2', 'el-icon-platform-eleme', 'checkerProcess.html', null, '1', '31', null, '1', '29', '', 'production/process_design/checker_process');
INSERT INTO `sys_permission` VALUES ('32', '工序设计单查询', 'gxsjdcx', '2', null, 'process.html', null, '1', '32', null, '1', '29', '', 'production/process_design/process');
INSERT INTO `sys_permission` VALUES ('33', '工序物料制定', 'gxwlzd', '2', null, 'makingProcessMaterial.html', null, '1', '33', null, '1', '29', '', 'production/process_material_design/making_process_material');
INSERT INTO `sys_permission` VALUES ('34', '工序物料核审', 'gxwlhs', '2', null, 'checkerProcessMaterial.html', null, '1', '34', null, '1', '29', '', 'production/process_material_design/checker_process_material');
INSERT INTO `sys_permission` VALUES ('35', '工序物料设计查询', 'gxwlsjcx', '2', null, 'processMaterial.html', null, '1', '35', null, '1', '29', '', 'production/process_material_design/process_material');
INSERT INTO `sys_permission` VALUES ('36', '生产计划管理', 'scjhgl', '2', null, null, null, '1', '36', null, '1', '29', '', null);
INSERT INTO `sys_permission` VALUES ('37', '新增生产计划', 'xzscjh', '2', 'null', 'insert', null, '1', '37', null, '1', '38', '', 'production/production_plan/insert_production_plan');
INSERT INTO `sys_permission` VALUES ('38', '生产计划查询', 'scjhcx', '2', 'null', 'productionPlan.html', null, '1', '38', null, '1', '36', '', 'production/production_plan/production_plan');
INSERT INTO `sys_permission` VALUES ('39', '产品生产计划单核审', 'cpscjhdhs', '2', 'null', 'checkerProductionPlan.html', null, '1', '39', null, '1', '36', '', 'production/production_plan/checker_production_plan');
INSERT INTO `sys_permission` VALUES ('40', '生成派工单', 'scpgd', '2', null, 'dispatching.html', null, '1', '40', null, '1', '36', '', 'production/dispatching/dispatching');
INSERT INTO `sys_permission` VALUES ('41', '核审制定产品', 'hszdcp', '2', null, 'checkerManufacture.html', null, '1', '41', null, '1', '36', '', 'production/dispatching/checker_manufacture');
INSERT INTO `sys_permission` VALUES ('42', '生产制定单查询', 'sczddcx', '2', null, 'manufacture.html', null, '1', '42', null, '1', '36', '', 'production/manufacture/manufacture');
INSERT INTO `sys_permission` VALUES ('43', '内部生产管理', 'nbscgl', '2', null, null, null, '1', '43', null, '1', '29', '', null);
INSERT INTO `sys_permission` VALUES ('44', '生产登记管理', 'scdjgl', '2', 'null', 'internalProductionRegister.html', null, '1', '44', null, '1', '43', '', 'production/Internal_production/internal_production_register');
INSERT INTO `sys_permission` VALUES ('45', '生产登记核审', 'scdjhs', '2', null, 'checkerInternalProductionRegister.html', null, '1', '45', null, '1', '43', '', 'production/Internal_production/checker_internal_production_register');
INSERT INTO `sys_permission` VALUES ('46', '物料报损报溢管理', 'wlbsbygl', '2', null, null, null, '1', '46', null, '1', '29', '', null);
INSERT INTO `sys_permission` VALUES ('47', '报损报溢登记', 'bsbydj', '2', null, 'register_profit_and_loss.html', null, '1', '47', null, '1', '46', '', 'production/profit_and_loss/register_profit_and_loss');
INSERT INTO `sys_permission` VALUES ('48', '核审报损报溢', 'hsbsby', '2', 'null', 'check_profit_and_loss.html', null, '1', '48', null, '1', '46', '', 'production/profit_and_loss/checker_profit_and_loss');
INSERT INTO `sys_permission` VALUES ('49', '查询报损报溢', 'cxbsby', '2', null, 'select_profit_and_loss.html', null, '1', '49', null, '1', '46', '', 'production/profit_and_loss/select_profit_and_loss');
INSERT INTO `sys_permission` VALUES ('50', '部门管理', 'bmgl', '2', 'el-icon-s-tools', 'dept.html', null, '1', '50', '部门管理', '1', '1', '', 'dept/index');
INSERT INTO `sys_permission` VALUES ('51', '部门权限', 'bmqx', '1', null, null, 'dept:*', '1', '51', '部门的相关管理', '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('52', '增加部门', 'zjbm', '2', 'el-icon-s-tools', 'insert', null, '1', '52', '部门管理', '1', '50', '', 'dept/insert');
INSERT INTO `sys_permission` VALUES ('53', '修改部门', 'xgbm', '2', null, 'update/:deptId', null, '1', '53', '修改部门', '1', '50', '', 'dept/update_dept');
INSERT INTO `sys_permission` VALUES ('54', '库存管理', 'kcgl', '2', 'el-icon-s-home', null, null, '1', '54', '库存管理的菜单', '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('55', '仓库', 'ck', '2', 'el-icon-caret-right', null, null, '1', '55', '仓库的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('56', '仓库管理', 'ckgl', '2', 'el-icon-caret-right', 'warehouse.html', null, '1', '56', '仓库管理', '1', '55', '', 'warehouse/warehouse');
INSERT INTO `sys_permission` VALUES ('57', '增加仓库', 'zjck', '2', 'el-icon-s-tools', 'insert', null, '1', '57', '增加仓库', '1', '56', '', 'warehouse/insert');
INSERT INTO `sys_permission` VALUES ('58', '修改仓库', 'xgck', '2', 'el-icon-s-tools', 'update/:sid', null, '1', '58', '修改仓库', '1', '56', '', 'warehouse/update_warehouse');
INSERT INTO `sys_permission` VALUES ('59', '仓库权限', 'ckqx', '1', null, null, 'warehouse:*', '1', '59', '仓库的相关管理', '1', null, '', null);
INSERT INTO `sys_permission` VALUES ('60', '安全库存配置管理', 'aqkcpzgl', '2', 'el-icon-s-check', null, null, '1', '60', '安全库存配置管理的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('61', '制定安全库存配置单', 'zdaqckpzd', '2', 'el-icon-s-tools', 'safetystockss.html', null, '1', '61', '安全库存管理', '1', '60', '', 'safetystockss/safetystockss');
INSERT INTO `sys_permission` VALUES ('62', '安全库存配置单审核', 'aqkcpzdsh', '2', 'el-icon-caret-right', 'review.html', null, '1', '62', '安全库存管理', '1', '60', '', 'review/review');
INSERT INTO `sys_permission` VALUES ('63', '安全库存配置单查询', 'aqckpzdcx', '2', 'el-icon-s-tools', 'selectstock.html', null, '1', '63', '安全库存管理', '1', '60', '', 'selectstock/selectstock');
INSERT INTO `sys_permission` VALUES ('64', '安全库存配置单变更', 'aqkcpzdbg', '2', 'el-icon-caret-right', 'safetystockchange.html', null, '1', '64', '安全库存管理', '1', '60', '', 'safetystockchange/safetystockchange');
INSERT INTO `sys_permission` VALUES ('65', '安全库存配置单', 'aqckpzd', '2', 'el-icon-s-tools', 'safetysheet/:productId', null, '1', '65', '安全库存管理', '1', '61', '', 'safetystockss/safetysheet');
INSERT INTO `sys_permission` VALUES ('66', '安全库存配置审核单', 'aqckpzshd', '2', 'el-icon-s-tools', 'reviewsheet/:safetystockId', null, '1', '66', '安全库存管理', '1', '62', '', 'review/reviewsheet');
INSERT INTO `sys_permission` VALUES ('67', '查询安全库存配置审核单详细', 'aqckpzshd', '2', 'el-icon-s-tools', 'selectstockdetails/:safetystockId', null, '1', '67', '安全库存管理', '1', '63', '', 'selectstock/selectstockdetails');
INSERT INTO `sys_permission` VALUES ('68', '安全库存配置单变更', 'bg', '2', 'el-icon-s-tools', 'updatestockchange/:safetystockId', null, '1', '68', '安全库存管理', '1', '64', '', 'safetystockchange/updatestockchange');
INSERT INTO `sys_permission` VALUES ('70', '出入库调度管理', 'crkddgl', '2', 'el-icon-s-tools', null, null, '1', '70', '入库管理的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('71', '制定入库单', 'zdrkd', '2', 'el-icon-s-tools', 'gascheduling.html', null, '1', '71', '安全库存管理', '1', '70', '', 'gascheduling/gascheduling');
INSERT INTO `sys_permission` VALUES ('72', '入库调度单', 'rkddd', '2', 'el-icon-s-tools', 'gaschedulingsheet/:gatherId', null, '1', '72', '安全库存管理', '1', '71', '', 'gascheduling/gaschedulingsheet');
INSERT INTO `sys_permission` VALUES ('73', '入库调度详细单', 'rkddxxd', '2', 'el-icon-s-tools', 'gaschedulingdetails/:productId', null, '1', '73', '安全库存管理', '1', '71', '', 'gascheduling/gaschedulingdetails');
INSERT INTO `sys_permission` VALUES ('74', '入库登记管理', 'rkdjgl', '2', 'el-icon-s-tools', null, null, '1', '74', '入库登记管理的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('75', '入库申请登记', 'rksqdj', '2', 'el-icon-s-tools', 'inventoryRegistration.html', null, '1', '75', '安全库存管理', '1', '74', '', 'inventoryRegistration/inventoryRegistration');
INSERT INTO `sys_permission` VALUES ('76', '入库登记审核', 'rksqdjsh', '2', 'el-icon-s-tools', 'invtRegstreview.html', null, '1', '76', '安全库存管理', '1', '74', '', 'inventoryRegistration/invtRegstreview');
INSERT INTO `sys_permission` VALUES ('77', '入库登记查询', 'rksqdjcx', '2', 'el-icon-s-tools', 'invtRegstselect.html', null, '1', '77', '安全库存管理', '1', '74', '', 'inventoryRegistration/invtRegstselect');
INSERT INTO `sys_permission` VALUES ('78', '入库登记单', 'rkdjd', '2', 'el-icon-s-tools', 'inventoryRegistrationSheet/:gatherId', null, '1', '78', '安全库存管理', '1', '75', '', 'inventoryRegistration/inventoryRegistrationSheet');
INSERT INTO `sys_permission` VALUES ('79', '入库登记复核单', 'rkdjfhd', '2', 'el-icon-s-tools', 'invtRegstreviewSheet/:gatherId', null, '1', '79', '安全库存管理', '1', '76', '', 'inventoryRegistration/invtRegstreviewSheet');
INSERT INTO `sys_permission` VALUES ('80', '入库登记查询详细', 'rkdjfhd', '2', 'el-icon-s-tools', 'invtRegstselectSheet/:gatherId', null, '1', '80', '安全库存管理', '1', '77', '', 'inventoryRegistration/invtRegstselectSheet');
INSERT INTO `sys_permission` VALUES ('81', '库存', 'kc', '2', 'el-icon-s-tools', 'reserve.html', null, '1', '81', '仓库库存管理', '1', '55', '', 'reserve/reserve');
INSERT INTO `sys_permission` VALUES ('82', '出库申请管理', 'cksqgl', '2', 'el-icon-s-tools', null, null, '1', '82', '出库管理的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('83', '出库申请登记', 'rksqdj', '2', 'el-icon-s-tools', 'stockOut.html', null, '1', '83', '安全库存管理', '1', '82', '', 'stockOut/stockOut');
INSERT INTO `sys_permission` VALUES ('84', '出库申请单', 'aqckpzd', '2', 'el-icon-s-tools', 'stockOut_product', null, '1', '84', '安全库存管理', '1', '83', '', 'stockOut/stockOut_product');
INSERT INTO `sys_permission` VALUES ('85', '出库申请登记预览', 'rksqdjsh', '2', 'el-icon-s-tools', 'stockOutPreview', null, '1', '85', '安全库存管理', '1', '83', '', 'stockOut/stockOutPreview');
INSERT INTO `sys_permission` VALUES ('86', '出库申请登记审核', 'rksqdjsh', '2', 'el-icon-s-tools', 'stockOutCheck.html', null, '1', '86', '安全库存管理', '1', '82', '', 'stockOut/stockOutCheck');
INSERT INTO `sys_permission` VALUES ('87', '出库申请登记审核单', 'rksqdjshd', '2', 'el-icon-s-tools', 'stockOutCheckView/:payId', null, '1', '87', '安全库存管理', '1', '86', '', 'stockOut/stockOutCheckView');
INSERT INTO `sys_permission` VALUES ('88', '出库申请查询', 'rksqcx', '2', 'el-icon-s-tools', 'stockOutSelect.html', null, '1', '88', '安全库存管理', '1', '82', '', 'stockOut/stockOutSelect');
INSERT INTO `sys_permission` VALUES ('89', '出库申请查询单', 'cksqcxxx', '2', 'el-icon-s-tools', 'stockOutSelectAll/:payId', null, '1', '89', '安全库存管理', '1', '88', '', 'stockOut/stockOutSelectAll');
INSERT INTO `sys_permission` VALUES ('90', '出入库调度管理', 'crkddgl', '2', 'el-icon-s-tools', null, null, '1', '90', '出库管理的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('91', '制定出库单', 'zdckd', '2', 'el-icon-s-tools', 'outbound.html', null, '1', '91', '安全库存管理', '1', '90', '', 'outbound/outbound');
INSERT INTO `sys_permission` VALUES ('92', '出库调度单', 'ckddd', '2', 'el-icon-s-tools', 'outboundView/:payId', null, '1', '92', '安全库存管理', '1', '91', '', 'outbound/outboundView');
INSERT INTO `sys_permission` VALUES ('93', '出库调度详细单', 'ckddd', '2', 'el-icon-s-tools', 'outboundResult/:productId/:payId', null, '1', '93', '安全库存管理', '1', '91', '', 'outbound/outboundResult');
INSERT INTO `sys_permission` VALUES ('94', '出库管理', 'crkddgl', '2', 'el-icon-s-tools', null, null, '1', '94', '出库管理的菜单', '1', '54', '', null);
INSERT INTO `sys_permission` VALUES ('95', '出库登记', 'zdckd', '2', 'el-icon-s-tools', 'stockOutRegist.html', null, '1', '95', '安全库存管理', '1', '94', '', 'stockOutRegist/stockOutRegist');
INSERT INTO `sys_permission` VALUES ('96', '出库登记具体单', 'ckddd', '2', 'el-icon-s-tools', 'stockOutRegistView/:payId', null, '1', '96', '安全库存管理', '1', '95', '', 'stockOutRegist/stockOutRegistView');
INSERT INTO `sys_permission` VALUES ('97', '出库登记详细单', 'ckddd', '2', 'el-icon-s-tools', 'outboudRegistResult/:productId/:payId', null, '1', '97', '安全库存管理', '1', '95', '', 'stockOutRegist/outboudRegistResult');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `rId` int(11) NOT NULL AUTO_INCREMENT,
  `rName` varchar(50) DEFAULT NULL,
  `roleStatus` int(11) DEFAULT '1' COMMENT '1可用 2禁用',
  `describe` varchar(100) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  `initial` varchar(20) DEFAULT NULL,
  `isFixed` int(11) DEFAULT '0' COMMENT '0:false不固定 1true 固定',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`rId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '1', '管理员的权限', '2020-04-04 09:36:59', null, 'gly', '1', '1');
INSERT INTO `sys_role` VALUES ('3', '采购员', '1', '采购员', '2020-04-04 07:00:20', null, 'cgy', '0', '2');
INSERT INTO `sys_role` VALUES ('4', '销售员', '1', '销售员', '2020-04-04 08:18:29', null, 'xsy', '0', '3');
INSERT INTO `sys_role` VALUES ('5', '仓库管理', '1', '仓库管理1', '2020-04-04 08:20:36', '2020-04-07 06:44:27', 'ckgl', '0', '4');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uId` varchar(20) DEFAULT NULL,
  `uName` varchar(20) DEFAULT NULL,
  `uPwd` varchar(36) DEFAULT NULL,
  `imgpath` varchar(255) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL COMMENT '常驻地址',
  `sex` int(11) DEFAULT '0' COMMENT '0男 1女',
  `signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `isadmin` int(11) DEFAULT '3' COMMENT '1超级管理员 2管理员 3普通用户',
  `deptid` int(11) DEFAULT NULL COMMENT '部门编号',
  `mgr` int(11) DEFAULT NULL COMMENT '经理编号',
  `initial` varchar(20) DEFAULT NULL,
  `isFixed` int(11) DEFAULT '1' COMMENT '1不固定 2固定',
  `userStatus` int(11) DEFAULT '1' COMMENT '1可用 2警用',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '123', '孙陆泉', '297254e9bfe0b8f39c682eda30bb9be0', '47920544-b0c3-4bff-8ea9-7a643c559df7.jpg', '湖南省邵阳市隆回县', '0', '我好喜欢你啊  孙璇!~!!!', '1', null, null, 'slq', '1', '1', '2020-04-06 16:11:38');
INSERT INTO `sys_user` VALUES ('3', 'sx', '孙璇', '41a5f18e3c24d8db59e447aaf7bd9a93', 'images/default_head_portrait.png', '邵阳', '2', null, '3', null, null, 'sx', '1', '1', '2020-04-06 16:11:38');
INSERT INTO `sys_user` VALUES ('4', 'haha', '哈哈', 'aefab47d68126e8318a8293d36742102', 'default_head_portrait.png', '', '0', null, '3', null, null, 'hh', '1', '1', '2020-04-16 13:56:23');
INSERT INTO `sys_user` VALUES ('5', 'cs', 'cs', 'e6a0c0175760abb9d871171ba4c95893', 'default_head_portrait.png', '123', '0', null, '3', null, null, 'cs', '1', '1', '2020-04-29 08:40:37');

-- ----------------------------
-- Table structure for `s_gather`
-- ----------------------------
DROP TABLE IF EXISTS `s_gather`;
CREATE TABLE `s_gather` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `gatherId` varchar(50) NOT NULL COMMENT '入库单编号',
  `stockid` varchar(50) DEFAULT NULL COMMENT '外来入库编号',
  `storer` varchar(50) NOT NULL COMMENT '入库人',
  `reasonexact` varchar(50) DEFAULT NULL COMMENT '入库理由',
  `amountSum` int(11) DEFAULT NULL COMMENT '总数量',
  `costPriceSum` double DEFAULT NULL COMMENT '总金额',
  `gatheredAmountSum` int(11) DEFAULT NULL COMMENT '确认入库总数量',
  `remark` varchar(40) DEFAULT NULL COMMENT '备注',
  `register` varchar(10) NOT NULL COMMENT '登记人',
  `registerTime` datetime NOT NULL COMMENT '登记时间',
  `checker` varchar(10) DEFAULT NULL COMMENT '审核人',
  `checkTime` datetime DEFAULT NULL COMMENT '审核时间',
  `checkTag` int(11) DEFAULT '0' COMMENT '审核状态:0.等待审核,1.审核通过,2.审核不通过',
  `attemper` varchar(10) DEFAULT NULL COMMENT '调度人',
  `attemmperTime` datetime DEFAULT NULL COMMENT '调度时间',
  `storeTag` int(11) DEFAULT '0' COMMENT '库存标记:1.已登记,2.已调度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='入库表';

-- ----------------------------
-- Records of s_gather
-- ----------------------------
INSERT INTO `s_gather` VALUES ('12', 'RK20200521000009', 'PG20200521000002', '123', '生产入库', '3680', '2613800', '1840', '', '孙陆泉', '2020-05-22 17:23:31', '孙陆泉', null, '2', '孙陆泉', '2020-05-23 00:50:58', '2');

-- ----------------------------
-- Table structure for `s_gather_details`
-- ----------------------------
DROP TABLE IF EXISTS `s_gather_details`;
CREATE TABLE `s_gather_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `parentId` varchar(50) DEFAULT NULL COMMENT '父级单号',
  `productId` varchar(50) DEFAULT NULL COMMENT '产品编号',
  `productName` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `mustAmount` int(11) DEFAULT NULL COMMENT '应入库数量',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `productUnit` varchar(5) DEFAULT NULL COMMENT '单位',
  `realCostPrice` double DEFAULT NULL COMMENT '单价',
  `subtotal` double DEFAULT NULL COMMENT '小计',
  `paidAmount` int(11) DEFAULT NULL COMMENT '登记确入库数量',
  `sid` int(11) DEFAULT NULL COMMENT '仓库编号',
  `payTag` int(11) DEFAULT '0' COMMENT '入库标志:1.已登记,2.已调度',
  `qwe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='入库明细表';

-- ----------------------------
-- Records of s_gather_details
-- ----------------------------
INSERT INTO `s_gather_details` VALUES ('12', 'RK20200521000009', 'CP20200511000002', '小笔记本电脑', '770', '770', '件', '3200', '2464000', '770', '2', '1', '0');
INSERT INTO `s_gather_details` VALUES ('13', 'RK20200521000009', 'YCL20200511000002', '内存条', '1070', '1070', '件', '140', '149800', '1070', '4', '1', '0');

-- ----------------------------
-- Table structure for `s_pay`
-- ----------------------------
DROP TABLE IF EXISTS `s_pay`;
CREATE TABLE `s_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `pay_id` varchar(20) NOT NULL COMMENT '出库单编号',
  `storer` varchar(10) NOT NULL COMMENT '出库人',
  `amount_sum` int(11) NOT NULL COMMENT '出库总数量',
  `cost_price_sum` int(11) DEFAULT NULL COMMENT '总金额',
  `paid_amount_sum` int(11) NOT NULL COMMENT '确认出库总数量',
  `reasonexact` varchar(30) DEFAULT NULL COMMENT '出库详细理由',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `register` varchar(10) NOT NULL COMMENT '登记人',
  `register_time` datetime NOT NULL COMMENT '登记时间',
  `checker` varchar(10) NOT NULL COMMENT '审核人',
  `check_time` datetime NOT NULL COMMENT '审核时间',
  `check_tag` varchar(10) NOT NULL COMMENT '审核状态:0.等待审核,1.审核通过,2.审核不通过',
  `attemper` varchar(10) DEFAULT NULL COMMENT '调度人',
  `attemmper_time` datetime DEFAULT NULL COMMENT '调度时间',
  `store_tag` varchar(10) NOT NULL COMMENT '库存标记:1.已登记,2.已调度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库表';

-- ----------------------------
-- Records of s_pay
-- ----------------------------

-- ----------------------------
-- Table structure for `s_pay_details`
-- ----------------------------
DROP TABLE IF EXISTS `s_pay_details`;
CREATE TABLE `s_pay_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `parent_id` int(11) NOT NULL COMMENT '父级序号',
  `product_id` varchar(50) NOT NULL COMMENT '产品编号',
  `product_name` varchar(50) NOT NULL COMMENT '产品名称',
  `amount` int(11) NOT NULL COMMENT '数量',
  `amount_unit` varchar(5) NOT NULL COMMENT '单位',
  `cost_price` double NOT NULL COMMENT '单价',
  `subtotal` double DEFAULT NULL COMMENT '小计',
  `paid_amount` int(11) NOT NULL COMMENT '确认出库数量',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库编号',
  `pay_tag` varchar(10) NOT NULL COMMENT '出库标志:1.已登记,2.已调度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库明细表';

-- ----------------------------
-- Records of s_pay_details
-- ----------------------------

-- ----------------------------
-- Table structure for `unit_key`
-- ----------------------------
DROP TABLE IF EXISTS `unit_key`;
CREATE TABLE `unit_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_column` varchar(30) NOT NULL COMMENT '单位的键 表名+column列名组成',
  `message` varchar(30) DEFAULT NULL COMMENT '提示信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='单位名称表';

-- ----------------------------
-- Records of unit_key
-- ----------------------------
INSERT INTO `unit_key` VALUES ('1', 'product_rank', '产品的档次级别');
INSERT INTO `unit_key` VALUES ('2', 'product_unit', '产品的计量单位');
INSERT INTO `unit_key` VALUES ('3', 'time_unit', '产品工序设计表下的时间单位');
INSERT INTO `unit_key` VALUES ('4', 'production_reason', '生产计划表下的 生产理由字段');

-- ----------------------------
-- Table structure for `unit_value`
-- ----------------------------
DROP TABLE IF EXISTS `unit_value`;
CREATE TABLE `unit_value` (
  `parent_id` int(11) NOT NULL COMMENT '外键 对应 unit_key的id',
  `unit` varchar(20) NOT NULL COMMENT '单位实际值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unit_value
-- ----------------------------
INSERT INTO `unit_value` VALUES ('1', '高级档次');
INSERT INTO `unit_value` VALUES ('1', '中级档次');
INSERT INTO `unit_value` VALUES ('1', '低级档次');
INSERT INTO `unit_value` VALUES ('2', '台');
INSERT INTO `unit_value` VALUES ('2', '个');
INSERT INTO `unit_value` VALUES ('2', '箱');
INSERT INTO `unit_value` VALUES ('3', '/(分钟 minute)');
INSERT INTO `unit_value` VALUES ('3', '/(小时 hour)');
INSERT INTO `unit_value` VALUES ('3', '/(天 day)');
INSERT INTO `unit_value` VALUES ('4', '新产品的生产');
INSERT INTO `unit_value` VALUES ('4', '当前产品库存不足');

-- ----------------------------
-- Table structure for `use`
-- ----------------------------
DROP TABLE IF EXISTS `use`;
CREATE TABLE `use` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useName` varchar(20) DEFAULT NULL COMMENT '用途名称',
  `describe` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of use
-- ----------------------------
INSERT INTO `use` VALUES ('1', '产品', '需要生产的商品');
INSERT INTO `use` VALUES ('2', '部件', '需要生产的部件');
INSERT INTO `use` VALUES ('3', '原材料', '需要采购的原材料');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4', '1');
INSERT INTO `user_role` VALUES ('5', '1');
INSERT INTO `user_role` VALUES ('1', '3');
INSERT INTO `user_role` VALUES ('1', '5');
