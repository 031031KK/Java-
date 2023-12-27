/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : db_finance_mgr

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2023-08-27 14:26:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(32) DEFAULT NULL COMMENT '账号',
  `nickname` varchar(64) DEFAULT NULL COMMENT '姓名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '管理员', '123456');

-- ----------------------------
-- Table structure for `t_bill`
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账单ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(64) DEFAULT NULL COMMENT '账单主题',
  `amount` double DEFAULT NULL COMMENT '金额',
  `card_id` int(11) DEFAULT NULL COMMENT '卡号ID',
  `bill_type` int(11) DEFAULT NULL COMMENT '账单类型：0收入，1支出',
  `pay_date` varchar(32) DEFAULT NULL COMMENT '账单时间',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `card_id` (`card_id`),
  CONSTRAINT `t_bill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_bill_ibfk_2` FOREIGN KEY (`card_id`) REFERENCES `t_card` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='收支账单表';

-- ----------------------------
-- Records of t_bill
-- ----------------------------
INSERT INTO `t_bill` VALUES ('1', '1', '饮食支出', '100', '1', '1', '2023-08-25', '生活饮食菜品。', '2023-08-26 16:10:34', '2023-08-27 12:35:18');
INSERT INTO `t_bill` VALUES ('2', '1', '外卖收入', '400', '1', '0', '2023-08-25', '外卖收入400', '2023-08-26 17:14:52', '2023-08-26 17:14:52');
INSERT INTO `t_bill` VALUES ('3', '1', '买衣服支出', '300', '2', '1', '2023-08-26', '买衣服支出300', '2023-08-26 17:21:50', '2023-08-26 17:21:50');
INSERT INTO `t_bill` VALUES ('4', '2', '请客吃饭消费', '500', '3', '1', '2023-08-26', '请客消费500', '2023-08-27 12:56:12', '2023-08-27 12:56:56');
INSERT INTO `t_bill` VALUES ('5', '3', '衣服消费支出', '300', '4', '1', '2023-08-27', '衣服消费支出', '2023-08-27 14:22:15', '2023-08-27 14:22:15');
INSERT INTO `t_bill` VALUES ('6', '3', '送外卖收入', '400', '4', '0', '2023-08-26', '一天送外卖收入', '2023-08-27 14:23:13', '2023-08-27 14:23:13');

-- ----------------------------
-- Table structure for `t_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `card_no` varchar(32) DEFAULT NULL COMMENT '卡号',
  `bank` varchar(64) DEFAULT NULL COMMENT '银行',
  `balance` double DEFAULT NULL COMMENT '余额',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_card_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='银行卡表';

-- ----------------------------
-- Records of t_card
-- ----------------------------
INSERT INTO `t_card` VALUES ('1', '1', '1111111111111111', '中国银行', '9980', '中国银行储蓄卡', '2023-08-26 13:38:36', '2023-08-26 20:04:23');
INSERT INTO `t_card` VALUES ('2', '1', '1111111111111112', '招商银行', '20000', '招商银行储蓄卡', '2023-08-26 17:20:50', '2023-08-26 17:51:34');
INSERT INTO `t_card` VALUES ('3', '2', '1111111111111113', '建设银行', '11500', '建设银行储蓄卡', '2023-08-27 12:55:27', '2023-08-27 14:23:57');
INSERT INTO `t_card` VALUES ('4', '3', '1111111111111114', '中信银行', '21100', '中信银行储蓄卡', '2023-08-27 14:21:31', '2023-08-27 14:21:34');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `username` varchar(32) DEFAULT NULL COMMENT '学号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别：0男，1女',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `telephone` varchar(32) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮件',
  `profession` varchar(32) DEFAULT NULL COMMENT '专业',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '2023001', '123456', '张小斐', '1', '2000-10-01', '11111111111', '1111@qq.com', '学生', '2023-08-26 11:07:22', '2023-08-27 14:18:31');
INSERT INTO `t_user` VALUES ('2', '2023002', '123456', '李思思', '1', '1995-05-10', '11111111112', '1112@qq.com', '教师', '2023-08-26 13:28:48', '2023-08-26 13:28:48');
INSERT INTO `t_user` VALUES ('3', '2023003', '123456', '王小二', '0', '1995-05-20', '11111111113', '1113@qq.com', '教师', '2023-08-27 14:20:30', '2023-08-27 14:23:30');
