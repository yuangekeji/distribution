/*
Navicat MySQL Data Transfer

Source Server         : product_dist
Source Server Version : 50719
Source Host           : 59.110.42.11:3303
Source Database       : distribution_prod

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-09-10 22:50:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_flow_history
-- ----------------------------
DROP TABLE IF EXISTS `account_flow_history`;
CREATE TABLE `account_flow_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `create_time` datetime DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '支出 -1 进账-2（支出：复投，转出，提现；收入：分红包奖金，转入，奖金）',
  `total_amt` decimal(15,2) DEFAULT NULL COMMENT '发生总金额',
  `bonus_amt` decimal(15,2) DEFAULT NULL COMMENT '奖金币',
  `seed_amt` decimal(15,2) DEFAULT NULL COMMENT '种子币',
  `flow_type` varchar(255) DEFAULT NULL COMMENT '资金流向类型（支出：复投1，转出2，提现3, 报单单 7, 折扣单 8；收入：分红包奖金4，转入5，动态奖金6,管理员充值 9））',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_flow_history
-- ----------------------------

-- ----------------------------
-- Table structure for account_manager
-- ----------------------------
DROP TABLE IF EXISTS `account_manager`;
CREATE TABLE `account_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `total_bonus` decimal(15,2) DEFAULT NULL COMMENT '累计奖金',
  `seed_amt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '种子币金额',
  `bonus_amt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '奖金币金额可提现',
  `advance_amt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '提现总额',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='账户管理表master';

-- ----------------------------
-- Records of account_manager
-- ----------------------------
INSERT INTO `account_manager` VALUES ('1', '1', '0.00', '0.00', '0.00', '0.00', '1', '2017-09-10 15:43:18', '1', '2017-09-10 17:16:51');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mobile` varchar(255) NOT NULL COMMENT '电话',
  `name` varchar(255) NOT NULL COMMENT '名字',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '17758674319', '李晓伟', '96E79218965EB72C92A549DD5A330112', '1', '2017-09-10 14:10:39', '1', '2017-09-10 14:10:50', '2');

-- ----------------------------
-- Table structure for advance
-- ----------------------------
DROP TABLE IF EXISTS `advance`;
CREATE TABLE `advance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提现编号',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '开户银行',
  `card_no` varchar(30) DEFAULT NULL COMMENT '银行卡号',
  `card_name` varchar(50) DEFAULT NULL COMMENT '开户人姓名',
  `req_amt` decimal(15,2) DEFAULT NULL COMMENT '申请提现金额',
  `act_amt` decimal(15,2) DEFAULT NULL COMMENT '实际提现金额',
  `fee_amt` decimal(15,2) DEFAULT NULL COMMENT '手续费',
  `request_date` date DEFAULT NULL COMMENT '申请提现日期',
  `approve_date` date DEFAULT NULL COMMENT '审批日期',
  `statues` char(1) DEFAULT NULL COMMENT '提现状态(提交申请1，成功提现2，提现失败3)',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现表master';

-- ----------------------------
-- Records of advance
-- ----------------------------

-- ----------------------------
-- Table structure for basic_manage
-- ----------------------------
DROP TABLE IF EXISTS `basic_manage`;
CREATE TABLE `basic_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_code` varchar(10) NOT NULL COMMENT '类型',
  `detail_code` varchar(10) NOT NULL COMMENT 'code',
  `detail_nm` varchar(50) NOT NULL COMMENT '名称',
  `buy_amt` decimal(15,2) DEFAULT NULL COMMENT '购买金额',
  `buy_qty` int(11) DEFAULT NULL COMMENT '购买数量',
  `devidend_cnt` int(11) DEFAULT NULL COMMENT '分红包个数',
  `sales_bonus_per` decimal(6,2) DEFAULT NULL COMMENT '销售奖比例',
  `sales_bonus_per1` decimal(6,2) DEFAULT NULL COMMENT '一代销售奖比例',
  `sales_bonus_per2` decimal(6,2) DEFAULT NULL COMMENT '二代销售奖比例',
  `profit_per` decimal(6,2) DEFAULT NULL COMMENT '公司分红比例',
  `discount` decimal(6,2) DEFAULT NULL COMMENT '会员折扣',
  `max_percent` decimal(6,2) DEFAULT NULL COMMENT '最大比例',
  `min_percent` decimal(6,2) DEFAULT NULL COMMENT '最小比例',
  `max_amt` decimal(15,2) DEFAULT NULL COMMENT '最大金额',
  `min_amt` decimal(15,2) DEFAULT NULL COMMENT '最小金额',
  `recommend_cnt` int(6) DEFAULT NULL COMMENT '推荐人数',
  `remain_cnt` int(3) DEFAULT NULL COMMENT '见点奖个数',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`,`type_code`,`detail_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='基本配置管理表';

-- ----------------------------
-- Records of basic_manage
-- ----------------------------
INSERT INTO `basic_manage` VALUES ('1', 'D01', '00', '会员奖励', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('2', 'D01', '01', '普卡', '600.00', '1', '1', '18.00', '5.00', '8.00', '30.00', '40.00', null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('3', 'D01', '02', '铜卡', '1800.00', '3', '3', '18.00', '5.00', '8.00', '30.00', '40.00', null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('4', 'D01', '03', '银卡', '3000.00', '5', '5', '18.00', '5.00', '8.00', '30.00', '40.00', null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('5', 'D01', '04', '金卡', '9000.00', '15', '15', '18.00', '5.00', '8.00', '30.00', '40.00', null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('6', 'D01', '05', '白金卡', '30000.00', '50', '50', '18.00', '5.00', '8.00', '30.00', '40.00', null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('7', 'D01', '06', '黑金卡', '60000.00', '100', '100', '18.00', '5.00', '8.00', '30.00', '40.00', null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('8', 'D02', '00', '分红包奖励', null, null, null, null, null, null, null, null, null, null, '5.00', '0.00', null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('9', 'D02', '01', '当日分红包比例', null, null, null, null, null, null, null, null, '20.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('10', 'D02', '02', '分红包满额比例', null, null, null, null, null, null, null, null, '75.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('11', 'D03', '00', '见点奖', null, null, null, null, null, null, null, null, null, null, '4.00', '0.00', null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('12', 'D03', '01', '当日见点奖比例', null, null, null, null, null, null, null, null, '10.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('13', 'D04', '00', '见点奖点位', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('14', 'D04', '01', '销售会员个数0', null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('15', 'D04', '02', '销售会员个数1', null, null, null, null, null, null, null, null, null, null, null, null, '1', '3', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('16', 'D04', '03', '销售会员个数2', null, null, null, null, null, null, null, null, null, null, null, null, '2', '7', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('17', 'D04', '04', '销售会员个数3', null, null, null, null, null, null, null, null, null, null, null, null, '3', '7', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('18', 'D04', '05', '销售会员个数4', null, null, null, null, null, null, null, null, null, null, null, null, '4', '11', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('19', 'D04', '06', '销售会员个数5', null, null, null, null, null, null, null, null, null, null, null, null, '5', '11', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('20', 'D04', '07', '销售会员个数6，6+', null, null, null, null, null, null, null, null, null, null, null, null, '6', '15', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('21', 'D05', '00', '级差奖', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('22', 'D05', '01', '主任', null, null, null, null, null, null, null, null, '3.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('23', 'D05', '02', '经理', null, null, null, null, null, null, null, null, '2.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('24', 'D05', '03', '总监', null, null, null, null, null, null, null, null, '2.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('25', 'D05', '04', '董事', null, null, null, null, null, null, null, null, '2.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('26', 'D05', '05', '全国董事', null, null, null, null, null, null, null, null, '1.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('27', 'D06', '00', '运营中心及工作室', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('28', 'D06', '01', '运营中心1（无工作室）', null, null, null, null, null, null, null, null, '3.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('29', 'D06', '02', '运营中心2（有工作室）', null, null, null, null, null, null, null, null, '1.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('30', 'D06', '03', '工作室', null, null, null, null, null, null, null, null, '3.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('31', 'D07', '00', '管理费', null, null, null, null, null, null, null, null, '6.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('32', 'D08', '00', '提现手续费', null, null, null, null, null, null, null, null, '6.00', null, null, null, null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('33', 'D09', '00', '提现下限', null, null, null, null, null, null, null, null, null, null, null, '100.00', null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');
INSERT INTO `basic_manage` VALUES ('34', 'D10', '00', '晋升标准', null, null, null, null, null, null, null, null, null, null, null, '150000.00', null, null, '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for bonus_cache_pool
-- ----------------------------
DROP TABLE IF EXISTS `bonus_cache_pool`;
CREATE TABLE `bonus_cache_pool` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `total_amount` double(10,2) DEFAULT NULL COMMENT '数量',
  `pool_type` varchar(255) DEFAULT NULL COMMENT '1-分红包池，2-见点奖池，3-其他',
  `remarks` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资金发放池表';

-- ----------------------------
-- Records of bonus_cache_pool
-- ----------------------------
INSERT INTO `bonus_cache_pool` VALUES ('1', '分红包池', '0.00', '1', '内置数据', '2017-09-07 22:41:28', '1', '2017-09-08 10:03:16', '1');
INSERT INTO `bonus_cache_pool` VALUES ('2', '见点奖池', '0.00', '2', '内置数据', '2017-09-07 22:41:28', '1', '2017-09-08 10:03:16', '1');

-- ----------------------------
-- Table structure for bonus_pool
-- ----------------------------
DROP TABLE IF EXISTS `bonus_pool`;
CREATE TABLE `bonus_pool` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `total_amount` double(10,2) DEFAULT NULL COMMENT '数量',
  `pool_type` varchar(255) DEFAULT NULL COMMENT '1-分红包池，2-见点奖池，3-其他',
  `remarks` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资金池表';

-- ----------------------------
-- Records of bonus_pool
-- ----------------------------
INSERT INTO `bonus_pool` VALUES ('1', '分红包池', '0.00', '1', '内置数据', '2017-09-10 22:40:24', '1', '2017-09-10 14:50:00', '0');
INSERT INTO `bonus_pool` VALUES ('2', '见点奖池', '0.00', '2', '内置数据', '2017-09-10 22:40:24', '1', '2017-09-10 14:53:00', '0');

-- ----------------------------
-- Table structure for bonus_pool_history
-- ----------------------------
DROP TABLE IF EXISTS `bonus_pool_history`;
CREATE TABLE `bonus_pool_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `add_remove` int(1) DEFAULT NULL COMMENT '增加还是扣除 1-增加，0-扣除',
  `old_amout` double(10,2) DEFAULT NULL,
  `amout` double(10,2) DEFAULT NULL COMMENT '数量',
  `new_amout` double(10,2) DEFAULT NULL,
  `pool_id` int(11) DEFAULT NULL COMMENT '资金池id',
  `remarks` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金池出入明细表';

-- ----------------------------
-- Records of bonus_pool_history
-- ----------------------------

-- ----------------------------
-- Table structure for china_president_bonus
-- ----------------------------
DROP TABLE IF EXISTS `china_president_bonus`;
CREATE TABLE `china_president_bonus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `bonus_amout` decimal(10,2) DEFAULT NULL,
  `bonus_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '0-未结算，1-已结算',
  `balance_time` datetime DEFAULT NULL COMMENT '结算时间',
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全国董事人均日奖金明细';

-- ----------------------------
-- Records of china_president_bonus
-- ----------------------------

-- ----------------------------
-- Table structure for date_bonus_history
-- ----------------------------
DROP TABLE IF EXISTS `date_bonus_history`;
CREATE TABLE `date_bonus_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `date` datetime DEFAULT NULL COMMENT '获奖日期',
  `total_sales` decimal(15,2) DEFAULT NULL COMMENT '总营业额',
  `dividend_total` decimal(15,2) DEFAULT NULL COMMENT '可用于分红包领取的金额',
  `jd_bonus_total` decimal(15,2) DEFAULT NULL COMMENT '可用于见点奖领取的金额',
  `use_dividend_total` decimal(15,2) DEFAULT NULL COMMENT '今日发放分红包总金额',
  `use_jd_bonus_total` decimal(15,0) DEFAULT NULL COMMENT '发放的见点奖',
  `remain_dividend` decimal(15,0) DEFAULT NULL COMMENT '营业额20%中剩余分红包',
  `remain_jd_bonus` decimal(15,0) DEFAULT NULL COMMENT '10%剩余的见点奖金',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `alarm_status` int(1) DEFAULT '0' COMMENT '分红包发放状态',
  `jd_alarm_status` int(1) DEFAULT '0' COMMENT '见点奖发放状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日分红包和见点奖奖金发放明细';

-- ----------------------------
-- Records of date_bonus_history
-- ----------------------------

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(255) DEFAULT NULL COMMENT '字典code',
  `dic_name` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `dic_type` varchar(255) DEFAULT NULL COMMENT '所属模块',
  `dic_des` varchar(255) DEFAULT NULL COMMENT '字典描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', 'member_level1', '普卡(600)', 'member_level', '会员级别');
INSERT INTO `dictionary` VALUES ('2', 'member_level2', '铜卡(1800)', 'member_level', '会员级别');
INSERT INTO `dictionary` VALUES ('3', 'member_level3', '银卡(3000)', 'member_level', '会员级别');
INSERT INTO `dictionary` VALUES ('4', 'member_level4', '金卡(9000)', 'member_level', '会员级别');
INSERT INTO `dictionary` VALUES ('5', 'member_level5', '白金卡(30000)', 'member_level', '会员级别');
INSERT INTO `dictionary` VALUES ('6', 'member_level6', '黑金卡(60000)', 'member_level', '会员级别');
INSERT INTO `dictionary` VALUES ('9', 'post_level1', '普通会员', 'post_level', '爵位(职务等级)');
INSERT INTO `dictionary` VALUES ('10', 'post_level2', '主任', 'post_level', '爵位(职务等级)');
INSERT INTO `dictionary` VALUES ('11', 'post_level3', '经理', 'post_level', '爵位(职务等级)');
INSERT INTO `dictionary` VALUES ('12', 'post_level4', '总监', 'post_level', '爵位(职务等级)');
INSERT INTO `dictionary` VALUES ('13', 'post_level5', '董事', 'post_level', '爵位(职务等级)');
INSERT INTO `dictionary` VALUES ('14', 'post_level6', '全国董事', 'post_level', '爵位(职务等级)');
INSERT INTO `dictionary` VALUES ('15', '工商银行', '工商银行', 'bank_name', '银行名称');
INSERT INTO `dictionary` VALUES ('16', '建设银行', '建设银行', 'bank_name', '银行名称');
INSERT INTO `dictionary` VALUES ('17', '农业银行', '农业银行', 'bank_name', '银行名称');
INSERT INTO `dictionary` VALUES ('18', '中国银行', '中国银行', 'bank_name', '银行名称');
INSERT INTO `dictionary` VALUES ('21', '招商银行', '招商银行', 'bank_name', '银行名称');
INSERT INTO `dictionary` VALUES ('22', '交通银行', '交通银行', 'bank_name', '银行名称');
INSERT INTO `dictionary` VALUES ('23', '邮政银行', '邮政银行', 'bank_name', '银行名称');

-- ----------------------------
-- Table structure for dividend
-- ----------------------------
DROP TABLE IF EXISTS `dividend`;
CREATE TABLE `dividend` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `order_no` bigint(15) DEFAULT NULL COMMENT '订单编号',
  `order_amount` decimal(11,2) DEFAULT NULL COMMENT '订单金额',
  `dividend_count` int(255) DEFAULT NULL COMMENT '分红包个数',
  `received_amount` decimal(11,2) DEFAULT NULL COMMENT '已领取金额',
  `remain_amount` decimal(11,2) DEFAULT NULL COMMENT '剩余金额',
  `dividend_limit` decimal(11,2) DEFAULT NULL COMMENT '领取上限',
  `dividend_status` varchar(255) DEFAULT NULL COMMENT '1 领取中 2 领取完',
  `mgmt_fee` decimal(15,2) DEFAULT NULL COMMENT '管理费',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='分红包表';

-- ----------------------------
-- Records of dividend
-- ----------------------------
INSERT INTO `dividend` VALUES ('1', '1', '1', '170910131258099', '600.00', '1', '0.00', '0.00', '800.00', '1', '48.00', '1', '2017-09-08 12:18:28', '0', '2017-09-10 14:50:00');

-- ----------------------------
-- Table structure for dividend_history
-- ----------------------------
DROP TABLE IF EXISTS `dividend_history`;
CREATE TABLE `dividend_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dividend_id` int(11) DEFAULT NULL COMMENT '分红包订单id',
  `received_time` datetime DEFAULT NULL COMMENT '领取时间',
  `devidend_count` int(11) DEFAULT NULL COMMENT '分红包个数',
  `amount` decimal(15,2) DEFAULT NULL COMMENT '单个分红包金额',
  `total_received` decimal(15,2) DEFAULT NULL COMMENT '领取总金额',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `mgmt_fee` decimal(15,2) DEFAULT NULL COMMENT '管理费',
  `balance_status` int(1) DEFAULT '0' COMMENT '0-未结算，1-已结算',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分红包领取明细表';

-- ----------------------------
-- Records of dividend_history
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_name` varchar(255) NOT NULL COMMENT '物品名称',
  `goods_num` int(11) DEFAULT '0' COMMENT '库存数量',
  `goods_price` decimal(15,2) NOT NULL COMMENT '物品价格',
  `list_time` datetime DEFAULT NULL COMMENT '上市时间',
  `goods_type` varchar(255) DEFAULT NULL COMMENT '产品类型',
  `specifications` varchar(255) DEFAULT NULL COMMENT '产品规格',
  `net_content` varchar(255) DEFAULT NULL COMMENT '净含量',
  `product_standard` varchar(255) DEFAULT NULL COMMENT '产品标准',
  `license_key` varchar(255) DEFAULT NULL COMMENT '许可证号',
  `storage_conditions` varchar(255) DEFAULT NULL COMMENT '贮藏条件',
  `quality_guarantee_period` varchar(255) DEFAULT NULL COMMENT '保质期',
  `edible_method` varchar(255) DEFAULT NULL COMMENT '食用方法',
  `matters_needing_attention` varchar(255) DEFAULT NULL COMMENT '注意事项',
  `manufacturer` varchar(255) DEFAULT NULL COMMENT '生产商',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `place_of_origin` varchar(255) DEFAULT NULL COMMENT '产地',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(255) DEFAULT 'N' COMMENT '删除标志(Y:已删除,N:未删除)',
  `info` varchar(5000) DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '瑶酿通補 6支装/盒', '100', '600.00', '2017-08-28 23:57:18', '01', null, null, null, null, null, null, null, null, null, null, null, '2017-08-28 23:58:12', 'N', null);

-- ----------------------------
-- Table structure for job_logs
-- ----------------------------
DROP TABLE IF EXISTS `job_logs`;
CREATE TABLE `job_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) DEFAULT NULL,
  `run_time` datetime DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `remarks` varchar(4000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='job执行记录表';

-- ----------------------------
-- Records of job_logs
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_phone` varchar(255) NOT NULL COMMENT '会员手机号',
  `login_password` varchar(255) NOT NULL COMMENT '登录密码',
  `query_password` varchar(255) DEFAULT NULL COMMENT '查询密码',
  `pay_password` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `recommend_id` int(11) NOT NULL COMMENT '推荐人ID',
  `node_id` int(11) DEFAULT NULL COMMENT '放置节点的会员ID',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员姓名',
  `member_level` varchar(255) NOT NULL COMMENT '会员等级',
  `ID_number` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `express_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `status` varchar(255) NOT NULL DEFAULT 'N' COMMENT '激活状态(Y:已激活,N:未激活)',
  `delete_flag` varchar(255) DEFAULT 'N' COMMENT '删除标志(Y:已删除,N:未删除)',
  `role_id` int(11) DEFAULT '1' COMMENT '??ID',
  `member_post` varchar(255) DEFAULT NULL COMMENT '职务等级',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `order_amount` decimal(15,2) DEFAULT NULL COMMENT '订单金额',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
  `money_status` varchar(255) DEFAULT 'N' COMMENT '会员激活款到账状态(N:未到账,Y:已到账)',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bank_user_name` varchar(255) DEFAULT NULL COMMENT '开户姓名',
  `card_number` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `is_operator` varchar(255) DEFAULT NULL COMMENT '是否是运营中心',
  `is_sales_dept` varchar(255) DEFAULT NULL COMMENT '是否是销售部（工作中心）',
  `first_agent_cnt` int(11) DEFAULT NULL COMMENT '一代个数',
  `recommend_name` varchar(255) DEFAULT NULL COMMENT '推荐人名',
  `node_name` varchar(255) DEFAULT NULL COMMENT '放置节点名',
  `linkman_phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_phone` (`member_phone`),
  UNIQUE KEY `node_id` (`node_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '17758674319', '96E79218965EB72C92A549DD5A330112', 'E3CEB5881A0A1FDAAD01296D7554868D', '1A100D2C0DAB19C4430E7D73762B3423', '0', '1', '李晓伟', 'member_level1', '41293019730515042X', '广西南宁市青秀区民族大道127号铂宫国际19楼1913号', 'Y', 'N', '1', 'post_level1', '0', '2017-09-10 14:28:06', '1', '2017-09-10 17:16:54', '600.00', '李晓伟', 'Y', '招商银行', '李晓伟', '6214857718962972', 'N', 'N', '0', 'ADMIN', 'ADMIN', null);

-- ----------------------------
-- Table structure for member_bonus
-- ----------------------------
DROP TABLE IF EXISTS `member_bonus`;
CREATE TABLE `member_bonus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `bonus_date` datetime NOT NULL COMMENT '发奖日期',
  `bonus_type` varchar(255) NOT NULL COMMENT '奖金类型:0-销售佣金，1-1代奖，2-2代奖，3-分红包奖，4-见点奖，5-级差奖，6-全国董事奖，7-工作室奖，8-运营中心奖，9-运营中心扶持奖',
  `amout` double(10,2) NOT NULL COMMENT '奖金数量',
  `manage_fee` double(10,2) DEFAULT NULL COMMENT '管理费',
  `actual_amout` double(10,2) DEFAULT NULL COMMENT '实际所得',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `order_no` bigint(15) DEFAULT NULL COMMENT '订单号',
  `order_date` datetime DEFAULT NULL COMMENT '订单日期',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员奖金表';

-- ----------------------------
-- Records of member_bonus
-- ----------------------------

-- ----------------------------
-- Table structure for member_charge
-- ----------------------------
DROP TABLE IF EXISTS `member_charge`;
CREATE TABLE `member_charge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `charge_amt` decimal(15,2) DEFAULT NULL COMMENT '充值金额',
  `create_id` int(11) DEFAULT NULL COMMENT '充值管理员id',
  `create_time` datetime DEFAULT NULL COMMENT '充值时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='会员充值表（管理员操作的）';

-- ----------------------------
-- Records of member_charge
-- ----------------------------

-- ----------------------------
-- Table structure for member_node
-- ----------------------------
DROP TABLE IF EXISTS `member_node`;
CREATE TABLE `member_node` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `left_id` int(11) DEFAULT NULL COMMENT '左边节点主键',
  `right_id` int(11) DEFAULT NULL COMMENT '右边节点主键',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父节点主键',
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `member_total` int(255) unsigned DEFAULT '0' COMMENT '备用下级数量,废弃字段',
  `sales_total` double(10,0) DEFAULT '0' COMMENT '备用销售业绩总数，废弃字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员节点二叉树表';

-- ----------------------------
-- Records of member_node
-- ----------------------------
INSERT INTO `member_node` VALUES ('1', null, null, '0', '2017-09-10 21:01:19', '1', '2017-09-10 20:12:52', '1', '0', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `no` int(10) DEFAULT NULL COMMENT '显示顺序',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `parent_menu` int(10) DEFAULT NULL COMMENT '一级菜单',
  `menu_link` varchar(255) DEFAULT NULL COMMENT '连接url',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2', '会员中心', '0', '', 'icon-user');
INSERT INTO `menu` VALUES ('2', null, '会员中心', '1', 'app.member.overview', 'icon-user');
INSERT INTO `menu` VALUES ('3', null, '我的提现', '1', 'app.advance', 'icon-diamond');
INSERT INTO `menu` VALUES ('4', '3', '销售中心', '0', '', 'icon-handbag');
INSERT INTO `menu` VALUES ('5', null, '分销中心', '4', 'app.recommend', null);
INSERT INTO `menu` VALUES ('6', null, '奖金明细', '4', 'app.bonus', null);
INSERT INTO `menu` VALUES ('7', null, '我的分红包', '4', 'app.dividend', null);
INSERT INTO `menu` VALUES ('9', '1', '订购商城', '0', '', 'icon-present');
INSERT INTO `menu` VALUES ('10', null, '商品列表', '9', 'app.product', null);
INSERT INTO `menu` VALUES ('11', null, '我的订单', '9', 'app.order', null);
INSERT INTO `menu` VALUES ('12', '1', '会员管理', '0', '', 'icon-user');
INSERT INTO `menu` VALUES ('13', null, '会员管理', '12', 'app.admMember', null);
INSERT INTO `menu` VALUES ('14', null, '运营中心管理', '12', 'app.admOperator', null);
INSERT INTO `menu` VALUES ('15', '2', '销售管理', '0', '', 'icon-handbag');
INSERT INTO `menu` VALUES ('16', null, '分红包管理', '15', 'app.admDividend', null);
INSERT INTO `menu` VALUES ('17', null, '分销管理', '15', 'app.admBonus', null);
INSERT INTO `menu` VALUES ('19', null, '订单管理', '15', 'app.admOrder', null);
INSERT INTO `menu` VALUES ('20', null, '提现管理', '15', 'app.admAdvance', null);
INSERT INTO `menu` VALUES ('21', '3', '商品管理', '0', '', 'icon-basket');
INSERT INTO `menu` VALUES ('22', null, '商品列表', '21', 'app.admProduct', null);
INSERT INTO `menu` VALUES ('23', '4', '基本配置', '0', '', 'icon-settings');
INSERT INTO `menu` VALUES ('24', null, '基本配置', '23', 'app.admBasicSetting', null);
INSERT INTO `menu` VALUES ('25', '5', '权限管理', '0', '', 'icon-lock');
INSERT INTO `menu` VALUES ('26', null, '管理员列表', '25', 'app.admin', null);
INSERT INTO `menu` VALUES ('27', null, '账户管理', '1', 'app.account', null);
INSERT INTO `menu` VALUES ('28', null, '推荐网络图', '1', 'app.graph', 'icon-graph');

-- ----------------------------
-- Table structure for node_bonus_history
-- ----------------------------
DROP TABLE IF EXISTS `node_bonus_history`;
CREATE TABLE `node_bonus_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mebmer_id` int(11) DEFAULT NULL COMMENT '奖金所有人',
  `bonus_amount` double(10,2) DEFAULT NULL COMMENT '奖金数量',
  `from_node_id` int(11) DEFAULT NULL COMMENT '获得奖金来源节点',
  `status` int(1) DEFAULT NULL COMMENT '0-未领取，1-已领取，2-已结算。',
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `order_no` bigint(15) DEFAULT NULL,
  `order_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='见点奖明细';

-- ----------------------------
-- Records of node_bonus_history
-- ----------------------------

-- ----------------------------
-- Table structure for operation_apply
-- ----------------------------
DROP TABLE IF EXISTS `operation_apply`;
CREATE TABLE `operation_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  `total_order_amount` decimal(15,2) DEFAULT NULL COMMENT '总订单金额',
  `status` varchar(255) DEFAULT 'wait' COMMENT '审核状态(''wait'':待审核，''pass'':通过，''refuse'':拒绝)',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人(自己)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间(申请时间)',
  `update_id` int(11) DEFAULT NULL COMMENT '审批人',
  `update_time` datetime DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_apply
-- ----------------------------

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) NOT NULL COMMENT '订单号',
  `goods_cd` varchar(50) NOT NULL COMMENT '商品编号',
  `order_amt` decimal(15,2) NOT NULL COMMENT '订单金额',
  `order_qty` int(11) NOT NULL COMMENT '订购数量',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单管理表detail';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1', '170910131258099', '1', '600.00', '1', '1', '2017-09-10 12:38:02', '1', '2017-09-10 12:38:02');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) NOT NULL COMMENT '订单号',
  `order_category` char(1) DEFAULT NULL COMMENT '订单类型（1.报单，2.复投， 3.折扣订单）',
  `order_amt` decimal(15,2) DEFAULT NULL COMMENT '订单金额',
  `order_qty` int(11) DEFAULT NULL COMMENT '订购数量',
  `discount` int(11) DEFAULT NULL COMMENT '折扣',
  `act_amt` decimal(15,2) unsigned DEFAULT NULL COMMENT '实付金额',
  `express_fee` decimal(15,2) DEFAULT NULL COMMENT '快递费',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `receive_name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `express_address` varchar(500) DEFAULT NULL COMMENT '收货地址',
  `member_level` varchar(100) DEFAULT NULL COMMENT '会员等级',
  `order_statues` char(1) DEFAULT NULL COMMENT '订单状态（1.待支付\n2.待发货\n3.待收货\n4.订单完成）',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单master';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1', '170910131258099', '1', '600.00', '1', '0', '600.00', '0.00', '1', '李晓伟', '大连市', 'member_level5', '2', '1', '2017-09-10 13:12:59', '1', '2017-09-10 20:52:57');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '会员');
INSERT INTO `role` VALUES ('2', '超级管理员');
INSERT INTO `role` VALUES ('3', '财务');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('2', '12');
INSERT INTO `role_menu` VALUES ('2', '13');
INSERT INTO `role_menu` VALUES ('2', '14');
INSERT INTO `role_menu` VALUES ('2', '15');
INSERT INTO `role_menu` VALUES ('2', '16');
INSERT INTO `role_menu` VALUES ('2', '17');
INSERT INTO `role_menu` VALUES ('2', '18');
INSERT INTO `role_menu` VALUES ('2', '19');
INSERT INTO `role_menu` VALUES ('2', '20');
INSERT INTO `role_menu` VALUES ('2', '22');
INSERT INTO `role_menu` VALUES ('2', '23');
INSERT INTO `role_menu` VALUES ('2', '24');
INSERT INTO `role_menu` VALUES ('2', '25');
INSERT INTO `role_menu` VALUES ('2', '26');
INSERT INTO `role_menu` VALUES ('3', '15');
INSERT INTO `role_menu` VALUES ('3', '16');
INSERT INTO `role_menu` VALUES ('3', '17');
INSERT INTO `role_menu` VALUES ('3', '18');
INSERT INTO `role_menu` VALUES ('3', '19');
INSERT INTO `role_menu` VALUES ('3', '20');
INSERT INTO `role_menu` VALUES ('1', '27');
INSERT INTO `role_menu` VALUES ('1', '28');
INSERT INTO `role_menu` VALUES ('2', '21');

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '转账编号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `receive_id` int(11) NOT NULL COMMENT '收款人id',
  `transfer_amt` decimal(15,2) NOT NULL COMMENT '转账金额',
  `transfer_time` datetime NOT NULL COMMENT '转账时间',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `member_phone` varchar(255) DEFAULT NULL COMMENT '会员电话号',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员名字',
  `receive_phone` varchar(255) DEFAULT NULL COMMENT '收款会员电话号',
  `receive_name` varchar(255) DEFAULT NULL COMMENT '收款会员名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转账表master';

-- ----------------------------
-- Records of transfer
-- ----------------------------

-- ----------------------------
-- Function structure for getChildList
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildList`(rootId INT) RETURNS varchar(1000) CHARSET utf8
  BEGIN
    DECLARE sChildList VARCHAR(1000);
    DECLARE sChildTemp VARCHAR(1000);
    SET sChildTemp =cast(rootId as CHAR);
    WHILE sChildTemp is not null DO
      IF (sChildList is not null) THEN
        SET sChildList = concat(sChildList,',',sChildTemp);
      ELSE
        SET sChildList = concat(sChildTemp);
      END IF;
      SELECT group_concat(id) INTO sChildTemp FROM member_node where FIND_IN_SET(parent_id,sChildTemp)>0;
    END WHILE;
    RETURN sChildList;
  END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getParentList
-- ----------------------------
DROP FUNCTION IF EXISTS `getParentList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getParentList`(rootId INT) RETURNS varchar(1000) CHARSET utf8
  BEGIN
    DECLARE sParentList varchar(1000);
    DECLARE sParentTemp varchar(1000);
    SET sParentTemp =cast(rootId as CHAR);
    WHILE sParentTemp is not null DO
      IF (sParentList is not null) THEN
        SET sParentList = concat(sParentTemp,',',sParentList);
      ELSE
        SET sParentList = concat(sParentTemp);
      END IF;
      SELECT group_concat(parent_id) INTO sParentTemp FROM member_node where FIND_IN_SET(id,sParentTemp)>0;
    END WHILE;
    RETURN sParentList;
  END
;;
DELIMITER ;

-- 商品添加字段
ALTER TABLE `goods`
ADD COLUMN `info`  varchar(5000) NULL COMMENT '介绍';

-- 添加字段表基础数据
insert into dictionary(dic_code,dic_name,dic_type,dic_des) values('01','保健品','goods_type','商品名称');

ALTER TABLE `goods`
ADD COLUMN `imge_url`  varchar(5000) NULL COMMENT '图片路径';

ALTER TABLE `goods`
ADD COLUMN `status`  varchar(255) NULL COMMENT '状态(Y:上架,N:下架)';

-- 订单添加字段
ALTER TABLE `order_master`
	ADD COLUMN `recevive_phone` VARCHAR(255) NULL DEFAULT NULL COMMENT '收货人电话' AFTER `express_address`,
	ADD COLUMN `remark` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注' AFTER `recevive_phone`;

-- admin添加字段:管理员禁用/启用功能
ALTER TABLE `admin`
	ADD COLUMN `delete_flag` VARCHAR(255) NULL DEFAULT 'Y' COMMENT '管理员禁用/启用(启用:Y; 禁用: N)' AFTER `role_id`;