/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : distribution

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-08-22 21:31:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_manager
-- ----------------------------
DROP TABLE IF EXISTS `account_manager`;
CREATE TABLE `account_manager` (
  `id` int(11) NOT NULL COMMENT '会员id',
  `seed_amt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '种子币金额',
  `bonus_amt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '奖金币金额',
  `advance_amt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '提现总额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户管理表master';

-- ----------------------------
-- Records of account_manager
-- ----------------------------

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL COMMENT '电话',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `password` varchar(255) DEFAULT NULL COMMENT '密码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for advance
-- ----------------------------
DROP TABLE IF EXISTS `advance`;
CREATE TABLE `advance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提现编号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `bank_name` varchar(100) NOT NULL COMMENT '开户银行',
  `card_no` varchar(30) NOT NULL COMMENT '银行卡号',
  `card_name` varchar(50) NOT NULL COMMENT '开户人姓名',
  `req_amt` decimal(15,2) NOT NULL COMMENT '申请提现金额',
  `act_amt` decimal(15,2) NOT NULL COMMENT '实际提现金额',
  `fee_amt` decimal(15,2) NOT NULL COMMENT '手续费',
  `request_date` date NOT NULL COMMENT '申请提现日期',
  `approve_date` date NOT NULL COMMENT '审批日期',
  `statues` char(1) NOT NULL COMMENT '提现状态',
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(10) NOT NULL COMMENT '类型',
  `detail_code` varchar(10) NOT NULL COMMENT 'code',
  `detail_nm` varchar(50) NOT NULL COMMENT '名称',
  `max_percent` decimal(3,2) NOT NULL COMMENT '最大比例',
  `min_percent` decimal(3,2) NOT NULL COMMENT '最小比例',
  `max_amt` decimal(15,2) NOT NULL COMMENT '最大金额',
  `min_amt` decimal(15,2) NOT NULL COMMENT '最小金额',
  PRIMARY KEY (`id`,`type_code`,`detail_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基本配置管理表';

-- ----------------------------
-- Records of basic_manage
-- ----------------------------

-- ----------------------------
-- Table structure for date_bonus_history
-- ----------------------------
DROP TABLE IF EXISTS `date_bonus_history`;
CREATE TABLE `date_bonus_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL COMMENT '获奖日期',
  `total_sales` decimal(15,2) DEFAULT NULL COMMENT '总营业额',
  `dividend_total` decimal(15,2) DEFAULT NULL COMMENT '可用于分红包领取的金额',
  `jd_bonus_total` decimal(15,2) DEFAULT NULL COMMENT '可用于见点奖领取的金额',
  `use_dividend_total` decimal(15,2) DEFAULT NULL COMMENT '今日发放分红包总金额',
  `use_jd_bonus_total` decimal(15,0) DEFAULT NULL COMMENT '发放的见点奖',
  `remain_dividend` decimal(15,0) DEFAULT NULL COMMENT '营业额20%中剩余分红包',
  `remain_jd_bonus` decimal(15,0) DEFAULT NULL COMMENT '10%剩余的见点奖金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日分红包和见点奖奖金发放明细';

-- ----------------------------
-- Records of date_bonus_history
-- ----------------------------

-- ----------------------------
-- Table structure for dividend
-- ----------------------------
DROP TABLE IF EXISTS `dividend`;
CREATE TABLE `dividend` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `order_amount` decimal(11,2) DEFAULT NULL COMMENT '订单金额',
  `dividend_count` int(255) DEFAULT NULL COMMENT '分红包个数',
  `received_amount` decimal(11,2) DEFAULT NULL COMMENT '已领取金额',
  `remain_amount` decimal(11,2) DEFAULT NULL COMMENT '剩余金额',
  `dividend_limit` decimal(11,2) DEFAULT NULL COMMENT '领取上限',
  `dividend_status` varchar(255) DEFAULT NULL COMMENT '1 领取中 2 领取完',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分红包表';

-- ----------------------------
-- Records of dividend
-- ----------------------------

-- ----------------------------
-- Table structure for dividend_history
-- ----------------------------
DROP TABLE IF EXISTS `dividend_history`;
CREATE TABLE `dividend_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dividend_id` int(11) DEFAULT NULL COMMENT '分红包订单id',
  `received_time` datetime DEFAULT NULL COMMENT '领取时间',
  `devidend_count` int(11) DEFAULT NULL COMMENT '分红包个数',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '单个分红包金额',
  `total_received` decimal(11,2) DEFAULT NULL COMMENT '领取总金额',
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
  `id` int(11) NOT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for invest
-- ----------------------------
DROP TABLE IF EXISTS `invest`;
CREATE TABLE `invest` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '复投编号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `invest_cnt` int(11) NOT NULL COMMENT '复投单数',
  `seed_amt` decimal(15,2) DEFAULT '0.00' COMMENT '种子币复投金额',
  `bonus_amt` decimal(15,2) DEFAULT '0.00' COMMENT '奖金复投金额',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复投表master';

-- ----------------------------
-- Records of invest
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_phone` varchar(255) NOT NULL COMMENT '会员手机号',
  `login_password` varchar(255) NOT NULL COMMENT '登录密码',
  `query_password` varchar(255) NOT NULL COMMENT '查询密码',
  `pay_password` varchar(255) NOT NULL COMMENT '支付密码',
  `recommend_id` int(11) NOT NULL COMMENT '推荐人ID',
  `node_id` int(11) NOT NULL COMMENT '放置节点的会员ID',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员姓名',
  `member_level` varchar(255) NOT NULL COMMENT '会员等级',
  `ID_number` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `express_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(255) NOT NULL DEFAULT 'N' COMMENT '激活状态(Y:已激活,N:未激活)',
  `delete_flag` varchar(255) DEFAULT 'N' COMMENT '删除标志(Y:已删除,N:未删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for member_level
-- ----------------------------
DROP TABLE IF EXISTS `member_level`;
CREATE TABLE `member_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level_category` int(11) NOT NULL DEFAULT '1' COMMENT '会员等级',
  `buy_amt` decimal(15,2) DEFAULT '0.00' COMMENT '购买金额',
  `buy_qty` int(11) DEFAULT '0' COMMENT '购买数量',
  `devidend_cnt` int(11) DEFAULT '0' COMMENT '分红包个数',
  `sales_bonus_per` decimal(3,2) DEFAULT '0.00' COMMENT '销售奖比例',
  `sales_bonus_per1` decimal(3,2) DEFAULT '0.00' COMMENT '一代销售奖比例',
  `sales_bonus_per2` decimal(3,2) DEFAULT '0.00' COMMENT '二代销售奖比例',
  `profit_per` decimal(2,2) DEFAULT '0.00' COMMENT '公司分红比例',
  `discount` decimal(2,2) DEFAULT '0.00' COMMENT '会员折扣',
  PRIMARY KEY (`id`,`level_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';

-- ----------------------------
-- Records of member_level
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2', '会员中心', '0', '');
INSERT INTO `menu` VALUES ('2', null, '会员中心', '1', 'app.member');
INSERT INTO `menu` VALUES ('3', null, '账户管理', '1', 'app.account');
INSERT INTO `menu` VALUES ('4', '3', '销售中心', '0', null);
INSERT INTO `menu` VALUES ('5', null, '分销中心', '4', 'app.recommend');
INSERT INTO `menu` VALUES ('6', null, '奖金明细', '4', 'app.bonus');
INSERT INTO `menu` VALUES ('7', null, '我的分红包', '4', 'app.dividend');
INSERT INTO `menu` VALUES ('8', null, '运营中心申请', '4', 'app.operator');
INSERT INTO `menu` VALUES ('9', '1', '订购商城', '0', null);
INSERT INTO `menu` VALUES ('10', null, '商品列表', '9', 'app.product');
INSERT INTO `menu` VALUES ('11', null, '我的订单', '9', 'app.order');
INSERT INTO `menu` VALUES ('12', '1', '会员管理', '0', null);
INSERT INTO `menu` VALUES ('13', null, '会员管理', '12', 'app.adm-member');
INSERT INTO `menu` VALUES ('14', null, '运营中心管理', '12', 'app.adm-operator');
INSERT INTO `menu` VALUES ('15', '2', '销售管理', '0', null);
INSERT INTO `menu` VALUES ('16', null, '分红包管理', '15', 'app.adm-dividend');
INSERT INTO `menu` VALUES ('17', null, '分销记录', '15', 'app.adm-bonus');
INSERT INTO `menu` VALUES ('18', null, '报单记录', '15', 'app.adm-recommend');
INSERT INTO `menu` VALUES ('19', null, '订单记录', '15', 'app.adm-order');
INSERT INTO `menu` VALUES ('20', null, '提现记录', '15', 'app.adm-advance');
INSERT INTO `menu` VALUES ('21', '3', '商品管理', '0', '');
INSERT INTO `menu` VALUES ('22', null, '商品列表', '21', 'app.adm-product');
INSERT INTO `menu` VALUES ('23', '4', '基本配置', '0', '');
INSERT INTO `menu` VALUES ('24', null, '基本配置', '23', 'app.adm-setting');
INSERT INTO `menu` VALUES ('25', '5', '权限管理', '0', null);
INSERT INTO `menu` VALUES ('26', null, '管理员列表', '25', 'app.adm-admini');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `order_category` char(1) DEFAULT NULL COMMENT '订单分类',
  `order_amt` decimal(15,2) NOT NULL COMMENT '订单金额',
  `order_qty` int(11) NOT NULL COMMENT '订购数量',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `receive_name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `express_address` varchar(500) DEFAULT NULL COMMENT '收货地址',
  `member_level` varchar(2) DEFAULT NULL COMMENT '会员等级',
  `order_statues` char(1) DEFAULT NULL COMMENT '订单状态',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单管理表master';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL COMMENT '订单号',
  `goods_cd` varchar(50) NOT NULL COMMENT '商品编号',
  `order_amt` decimal(15,2) NOT NULL COMMENT '订单金额',
  `order_qty` int(11) NOT NULL COMMENT '订购数量',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`,`goods_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单管理表detail';

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recommend_id` int(11) DEFAULT NULL COMMENT '推荐人ID',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员姓名',
  `member_mobile` varchar(255) DEFAULT NULL COMMENT '会员手机',
  `login_password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `node_id` int(255) DEFAULT NULL COMMENT '放置节点的会员ID',
  `member_level` varchar(255) DEFAULT NULL COMMENT '会员等级',
  `order_amount` int(11) DEFAULT NULL COMMENT '订单金额',
  `commodity_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `express_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(255) NOT NULL DEFAULT 'N' COMMENT '激活状态(Y:已激活,N:未激活)',
  `delete_flag` varchar(255) DEFAULT 'N' COMMENT '删除标志(Y:已删除,N:未删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报单表';

-- ----------------------------
-- Records of recommend
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

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
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL
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
INSERT INTO `role_menu` VALUES ('2', '21');
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

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '转账编号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `receive_id` int(11) NOT NULL COMMENT '收款人id',
  `transfer_amt` decimal(15,2) NOT NULL COMMENT '转账金额',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转账表master';

-- ----------------------------
-- Records of transfer
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试用户表';

-- ----------------------------
-- Records of user
-- ----------------------------


-- Bright 2017年8月22日21:41:48
ALTER TABLE `member`
ADD COLUMN `role_id`  int NULL COMMENT '角色ID' AFTER `delete_flag`,
ADD COLUMN `member_post`  varchar(255) NULL COMMENT '职务等级' AFTER `role_id`;

--Bright 2017年8月26日09:02:50
ALTER TABLE `admin`
ADD COLUMN `role_id`  int NULL COMMENT '角色ID';

--Bright 2017年8月26日10:07:57
ALTER TABLE `member`
ADD COLUMN `order_amount`  decimal(15,2) NULL COMMENT '订单金额';