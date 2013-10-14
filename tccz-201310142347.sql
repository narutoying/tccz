/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : tccz

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2013-10-14 23:46:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bandar_note`
-- ----------------------------
DROP TABLE IF EXISTS `bandar_note`;
CREATE TABLE `bandar_note` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bandar_note_number` varchar(50) NOT NULL,
  `enterprise_id` int(11) NOT NULL,
  `type` char(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `draw_date` date NOT NULL,
  `expire_date` date NOT NULL,
  `margin_amount` bigint(20) DEFAULT NULL,
  `exposure_amount` bigint(20) DEFAULT NULL,
  `exposure_close_amount` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bandar_note
-- ----------------------------
INSERT INTO `bandar_note` VALUES ('1', '123', '1', 'FULL_MARGIN', '100000', '2013-10-08', '2013-10-11', '100000', null, null, '2013-10-10 14:36:44', '2013-10-10 14:36:46');
INSERT INTO `bandar_note` VALUES ('3', '1234', '1', 'OPEN', '999999900', '2013-10-12', '2013-10-19', '0', '999999900', '0', '2013-10-12 13:52:56', '2013-10-12 13:52:56');
INSERT INTO `bandar_note` VALUES ('4', '1', '1', 'FULL_MARGIN', '10000', '2013-10-12', '2013-10-19', '10000', '0', '0', '2013-10-12 13:53:37', '2013-10-12 13:53:37');
INSERT INTO `bandar_note` VALUES ('5', '2', '1', 'FULL_MARGIN', '12300', '2013-10-13', '2013-10-19', '12300', '0', '0', '2013-10-12 14:06:28', '2013-10-12 14:24:41');

-- ----------------------------
-- Table structure for `discount`
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bandar_note_number` varchar(100) DEFAULT NULL COMMENT '银票号',
  `proposer_id` int(11) DEFAULT NULL COMMENT '申请人(企业名)id',
  `amount` bigint(20) DEFAULT NULL COMMENT '金额(分）',
  `state` char(30) DEFAULT NULL COMMENT '贴现状态(在库、已转贴、再转贴、已回购、已托收）',
  `expire_date` date DEFAULT NULL COMMENT '到期日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES ('1', '123', '1', '100', 'IN_STORE', '2013-10-26', '2013-09-17 14:51:47', '2013-09-17 14:51:49');
INSERT INTO `discount` VALUES ('2', '1', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('3', '2', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('4', '3', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('5', '4', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('6', '5', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('7', '6', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('8', '7', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('9', '8', '1', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('10', '9', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('11', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('12', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('13', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('14', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('15', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('16', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('17', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('18', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('19', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('20', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('21', '1', '2', '100', 'IN_STORE', '2013-09-23', null, null);
INSERT INTO `discount` VALUES ('22', '1', '2', '100', 'IN_STORE', '2013-09-19', null, null);
INSERT INTO `discount` VALUES ('23', '1', '2', '100', 'IN_STORE', '2013-09-21', null, null);
INSERT INTO `discount` VALUES ('24', '1', '2', '100', 'IN_STORE', '2013-09-22', null, null);
INSERT INTO `discount` VALUES ('25', '123', '1', '100', 'IN_STORE', '2013-09-23', '2013-09-23 10:50:07', '2013-09-23 10:50:07');
INSERT INTO `discount` VALUES ('31', '78910', '2', '123400', 'RE_DISCOUNT', '2013-09-28', '2013-09-24 20:45:00', '2013-09-24 23:37:58');
INSERT INTO `discount` VALUES ('32', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 15:05:45', '2013-09-27 15:05:45');
INSERT INTO `discount` VALUES ('33', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 15:07:46', '2013-09-27 15:07:46');
INSERT INTO `discount` VALUES ('34', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 16:09:24', '2013-09-27 16:09:24');
INSERT INTO `discount` VALUES ('35', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 16:10:37', '2013-09-27 16:10:37');
INSERT INTO `discount` VALUES ('36', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 16:11:19', '2013-09-27 16:11:19');
INSERT INTO `discount` VALUES ('37', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 16:28:53', '2013-09-27 16:28:53');
INSERT INTO `discount` VALUES ('38', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 16:33:25', '2013-09-27 16:33:25');
INSERT INTO `discount` VALUES ('39', '123456', '1', '10000', 'IN_STORE', '2013-09-27', '2013-09-27 21:32:35', '2013-09-27 21:32:35');
INSERT INTO `discount` VALUES ('40', '123456', '1', '999920000', 'IN_STORE', '2013-09-27', '2013-09-27 21:41:24', '2013-09-27 21:41:24');
INSERT INTO `discount` VALUES ('41', '123', '2', '1000', 'IN_STORE', '2013-09-28', '2013-09-27 22:50:25', '2013-09-27 23:08:45');
INSERT INTO `discount` VALUES ('42', '456', '2', '101', 'IN_STORE', '2013-09-30', '2013-09-27 22:55:06', '2013-09-29 14:24:16');
INSERT INTO `discount` VALUES ('43', '123456789', '2', '100000000', 'IN_STORE', '2013-10-10', '2013-10-08 09:42:45', '2013-10-08 09:42:45');

-- ----------------------------
-- Table structure for `discount_change`
-- ----------------------------
DROP TABLE IF EXISTS `discount_change`;
CREATE TABLE `discount_change` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `discount_id` int(11) NOT NULL COMMENT '所属贴现id',
  `state` char(30) NOT NULL COMMENT '变更状态',
  `create_time` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount_change
-- ----------------------------
INSERT INTO `discount_change` VALUES ('1', '30', 'RE_DISCOUNT', '2013-09-23 22:38:07');
INSERT INTO `discount_change` VALUES ('2', '31', 'IN_STORE', '2013-09-24 20:45:00');
INSERT INTO `discount_change` VALUES ('3', '0', 'IN_STORE', '2013-09-24 21:00:36');
INSERT INTO `discount_change` VALUES ('4', '0', 'TRANSFER', '2013-09-24 21:01:50');
INSERT INTO `discount_change` VALUES ('5', '0', 'IN_STORE', '2013-09-24 21:02:25');
INSERT INTO `discount_change` VALUES ('6', '0', 'IN_STORE', '2013-09-24 21:07:01');
INSERT INTO `discount_change` VALUES ('7', '31', 'IN_STORE', '2013-09-24 21:09:13');
INSERT INTO `discount_change` VALUES ('8', '31', 'TRANSFER', '2013-09-24 21:10:04');
INSERT INTO `discount_change` VALUES ('9', '31', 'TRANSFER', '2013-09-24 21:10:45');
INSERT INTO `discount_change` VALUES ('10', '31', 'RE_DISCOUNT', '2013-09-24 23:08:54');
INSERT INTO `discount_change` VALUES ('11', '31', 'RE_DISCOUNT', '2013-09-24 23:09:02');
INSERT INTO `discount_change` VALUES ('12', '31', 'RE_DISCOUNT', '2013-09-24 23:37:58');
INSERT INTO `discount_change` VALUES ('13', '32', 'IN_STORE', '2013-09-27 15:05:45');
INSERT INTO `discount_change` VALUES ('14', '33', 'IN_STORE', '2013-09-27 15:07:46');
INSERT INTO `discount_change` VALUES ('15', '34', 'IN_STORE', '2013-09-27 16:09:24');
INSERT INTO `discount_change` VALUES ('16', '35', 'IN_STORE', '2013-09-27 16:10:37');
INSERT INTO `discount_change` VALUES ('17', '36', 'IN_STORE', '2013-09-27 16:11:19');
INSERT INTO `discount_change` VALUES ('18', '37', 'IN_STORE', '2013-09-27 16:28:53');
INSERT INTO `discount_change` VALUES ('19', '38', 'IN_STORE', '2013-09-27 16:33:25');
INSERT INTO `discount_change` VALUES ('20', '39', 'IN_STORE', '2013-09-27 21:32:35');
INSERT INTO `discount_change` VALUES ('21', '40', 'IN_STORE', '2013-09-27 21:41:24');
INSERT INTO `discount_change` VALUES ('22', '41', 'IN_STORE', '2013-09-27 22:50:25');
INSERT INTO `discount_change` VALUES ('23', '42', 'IN_STORE', '2013-09-27 22:55:06');
INSERT INTO `discount_change` VALUES ('24', '41', 'IN_STORE', '2013-09-27 23:08:45');
INSERT INTO `discount_change` VALUES ('25', '42', 'IN_STORE', '2013-09-27 23:08:50');
INSERT INTO `discount_change` VALUES ('26', '42', 'IN_STORE', '2013-09-29 14:24:01');
INSERT INTO `discount_change` VALUES ('27', '42', 'IN_STORE', '2013-09-29 14:24:16');
INSERT INTO `discount_change` VALUES ('28', '43', 'IN_STORE', '2013-10-08 09:42:45');

-- ----------------------------
-- Table structure for `enterprise`
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '企业名',
  `account_number` varchar(100) DEFAULT NULL COMMENT '账户号',
  `legal_person_id` int(11) NOT NULL COMMENT '法人代表id',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES ('1', '香塘', '123', '1', '2013-09-17 14:52:50', '2013-09-17 14:52:52');
INSERT INTO `enterprise` VALUES ('2', '民生', '456', '1', '2013-09-21 21:30:17', '2013-09-21 21:30:18');

-- ----------------------------
-- Table structure for `floating_loan`
-- ----------------------------
DROP TABLE IF EXISTS `floating_loan`;
CREATE TABLE `floating_loan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `loaner_id` int(11) NOT NULL,
  `biz_side_type` char(50) NOT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `expire_date` date NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of floating_loan
-- ----------------------------
INSERT INTO `floating_loan` VALUES ('3', '2', 'CORPORATE', '12300', '2013-10-14', '2013-10-16', '2013-10-14 14:31:14', '2013-10-14 14:31:14');
INSERT INTO `floating_loan` VALUES ('4', '1', 'PRIVATE', '1000000', '2013-10-14', '2013-10-22', '2013-10-14 14:31:41', '2013-10-14 14:31:41');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '人名',
  `account_number` varchar(100) DEFAULT NULL COMMENT '账户号',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '香塘法人', '124', '2013-09-17 14:52:32', '2013-09-17 14:52:34');
