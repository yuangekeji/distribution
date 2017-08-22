/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : distribution

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-08-13 11:17:07
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lijingxin', '13842648081', '13842648081@126.com');
