/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : distribution

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-08-26 12:27:31
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
  `type` varchar(255) DEFAULT NULL COMMENT '支出/收入（支出：复投，转出，提现；收入：分红包奖金，转入，奖金）',
  `total_amt` decimal(15,2) DEFAULT NULL COMMENT '发生总金额',
  `bonus_amt` decimal(15,2) DEFAULT NULL COMMENT '奖金币',
  `seed_amt` decimal(15,2) DEFAULT NULL COMMENT '种子币',
  `flow_type` varchar(255) DEFAULT NULL COMMENT '资金流向类型（支出：复投，转出，提现；收入：分红包奖金，转入，奖金））',
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_code` varchar(10) NOT NULL COMMENT '类型',
  `detail_code` varchar(10) NOT NULL COMMENT 'code',
  `detail_nm` varchar(50) NOT NULL COMMENT '名称',
  `buy_amt` DECIMAL(15,2) NULL DEFAULT '0.00' COMMENT '购买金额',
  `buy_qty` INT(11) NULL DEFAULT '0' COMMENT '购买数量',
  `devidend_cnt` INT(11) NULL DEFAULT '0' COMMENT '分红包个数',
  `sales_bonus_per` DECIMAL(3,2) NULL DEFAULT '0.00' COMMENT '销售奖比例',
  `sales_bonus_per1` DECIMAL(3,2) NULL DEFAULT '0.00' COMMENT '一代销售奖比例',
  `sales_bonus_per2` DECIMAL(3,2) NULL DEFAULT '0.00' COMMENT '二代销售奖比例',
  `profit_per` DECIMAL(2,2) NULL DEFAULT '0.00' COMMENT '公司分红比例',
  `discount` DECIMAL(2,2) NULL DEFAULT '0.00' COMMENT '会员折扣',
  `max_percent` decimal(3,2) NOT NULL COMMENT '最大比例',
  `min_percent` decimal(3,2) NOT NULL COMMENT '最小比例',
  `max_amt` decimal(15,2) NOT NULL COMMENT '最大金额',
  `min_amt` decimal(15,2) NOT NULL COMMENT '最小金额',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
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
  `mgmt_fee` decimal(15,2) DEFAULT NULL COMMENT '管理费',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
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
  `status` varchar(255) NOT NULL DEFAULT 'N' COMMENT '激活状态(Y:已激活,N:未激活)',
  `delete_flag` varchar(255) DEFAULT 'N' COMMENT '删除标志(Y:已删除,N:未删除)',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `member_post` varchar(255) DEFAULT NULL COMMENT '职务等级',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` int(11) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `order_amount` decimal(15,2) DEFAULT NULL COMMENT '订单金额',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
  `money_status` varchar(255) DEFAULT NULL COMMENT '打款状态',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bank_user_name` varchar(255) DEFAULT NULL COMMENT '开户姓名',
  `card_number` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `is_operator` varchar(255) DEFAULT NULL COMMENT '是否是运营中心',
  `is_sales_dept` varchar(255) DEFAULT NULL COMMENT '是否是销售部（工作中心）',
  `first_agent_cnt` int(11) DEFAULT NULL COMMENT '一代个数',
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2', '会员中心', '0', '');
INSERT INTO `menu` VALUES ('2', null, '会员中心', '1', 'app.member.overview');
INSERT INTO `menu` VALUES ('3', null, '我的提现', '1', 'app.advance');
INSERT INTO `menu` VALUES ('4', '3', '销售中心', '0', null);
INSERT INTO `menu` VALUES ('5', null, '分销中心', '4', 'app.recommend');
INSERT INTO `menu` VALUES ('6', null, '奖金明细', '4', 'app.bonus');
INSERT INTO `menu` VALUES ('7', null, '我的分红包', '4', 'app.dividend');
INSERT INTO `menu` VALUES ('8', null, '运营中心申请', '4', 'app.operator');
INSERT INTO `menu` VALUES ('9', '1', '订购商城', '0', null);
INSERT INTO `menu` VALUES ('10', null, '商品列表', '9', 'app.product');
INSERT INTO `menu` VALUES ('11', null, '我的订单', '9', 'app.order');
INSERT INTO `menu` VALUES ('12', '1', '会员管理', '0', null);
INSERT INTO `menu` VALUES ('13', null, '会员管理', '12', 'app.admMember');
INSERT INTO `menu` VALUES ('14', null, '运营中心管理', '12', 'app.admOperator');
INSERT INTO `menu` VALUES ('15', '2', '销售管理', '0', null);
INSERT INTO `menu` VALUES ('16', null, '分红包管理', '15', 'app.admDividend');
INSERT INTO `menu` VALUES ('17', null, '分销记录', '15', 'app.admBonus');
INSERT INTO `menu` VALUES ('18', null, '报单记录', '15', 'app.admRecommend');
INSERT INTO `menu` VALUES ('19', null, '订单记录', '15', 'app.admOrder');
INSERT INTO `menu` VALUES ('20', null, '提现记录', '15', 'app.admAdvance');
INSERT INTO `menu` VALUES ('21', '3', '商品管理', '0', '');
INSERT INTO `menu` VALUES ('22', null, '商品列表', '21', 'app.admProduct');
INSERT INTO `menu` VALUES ('23', '4', '基本配置', '0', '');
INSERT INTO `menu` VALUES ('24', null, '基本配置', '23', 'app.admBasicSetting');
INSERT INTO `menu` VALUES ('25', '5', '权限管理', '0', null);
INSERT INTO `menu` VALUES ('26', null, '管理员列表', '25', 'app.admin');
INSERT INTO `menu` VALUES ('27', null, '账户管理', '1', 'app.admAccount');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `order_category` char(1) DEFAULT NULL COMMENT '订单分类',
  `order_amt` decimal(15,2) DEFAULT NULL COMMENT '订单金额',
  `order_qty` int(11) DEFAULT NULL COMMENT '订购数量',
  `discount` int(11) DEFAULT NULL COMMENT '折扣',
  `act_amt` decimal(15,2) DEFAULT NULL COMMENT '实付金额',
  `express_fee` decimal(15,2) DEFAULT NULL COMMENT '快递费',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
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
  `transfer_time` datetime NOT NULL COMMENT '转账时间',
  `create_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转账表master';

-- ----------------------------
-- Records of transfer
-- ----------------------------


-- ----------------------------
-- jingxin 8.26.14:44
-- ----------------------------

insert into role_menu values (1,27);

update menu set menu_link = 'app.account' where id = 27;

-- Bright 添加字典表 2017年8月26日15:57:24
CREATE TABLE `dictionary` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`dic_code`  varchar(255) NULL COMMENT '字典code' ,
`dic_name`  varchar(255) NULL COMMENT '字典名称' ,
`dic_type`  varchar(255) NULL COMMENT '所属模块' ,
`dic_des`  varchar(255) NULL COMMENT '字典描述' ,
PRIMARY KEY (`id`)
);
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

-- Bright 去掉会员表一些字段必填 2017年8月26日17:28:14
ALTER TABLE `member`
MODIFY COLUMN `query_password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '查询密码',
MODIFY COLUMN `pay_password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付密码',
MODIFY COLUMN `node_id`  int(11) NULL COMMENT '放置节点的会员ID';

-- Bright 新会员给角色默认值 2017年8月26日17:51:43
ALTER TABLE `member`
MODIFY COLUMN `role_id`  int(11) NULL DEFAULT 1 COMMENT '角色ID';

-- Bright 设置默认未打款状态 2017年8月26日17:52:00
ALTER TABLE `member`
MODIFY COLUMN `money_status`  varchar(255) NULL DEFAULT 'N' COMMENT '打款状态(N:未打款,Y:已打款)';

-- Bright 添加推荐人姓名，节点人姓名字段 2017年8月27日13:20:47
ALTER TABLE `member`
ADD COLUMN `recommend_name`  varchar(255) NULL COMMENT '推荐者姓名',
ADD COLUMN `node_name`  varchar(255) NULL COMMENT '放置到的节点的人的姓名';

-- lijingxin
ALTER TABLE `transfer`
  ADD COLUMN `member_phone`  varchar(255) NULL COMMENT '会员电话号',
  ADD COLUMN `member_name`  varchar(255) NULL COMMENT '会员名字',
  ADD COLUMN `receive_phone`  varchar(255) NULL COMMENT '收款会员电话号',
  ADD COLUMN `receive_name`  varchar(255) NULL COMMENT '收款会员名字';

--Bright 2017年8月30日21:37:32
CREATE TABLE `operation_request` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT 'ID主键' ,
`member_id`  int NULL COMMENT '会员ID' ,
`total_order_amount`  decimal(15,2) NULL COMMENT '总订单金额' ,
`status`  varchar(255) NULL DEFAULT 'wait' COMMENT '审核状态(\'wait\':待审核，\'pass\':通过，\'refuse\':拒绝)' ,
`create_id`  int NULL COMMENT '创建人(自己)' ,
`create_time`  datetime NULL COMMENT '创建时间(申请时间)' ,
`update_id`  int NULL COMMENT '审批人' ,
`update_time`  datetime NULL COMMENT '审批时间' ,
PRIMARY KEY (`id`)
);
ALTER TABLE `member`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `member_phone`);