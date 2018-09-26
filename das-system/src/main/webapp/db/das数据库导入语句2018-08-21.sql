/*
Navicat MySQL Data Transfer

Source Server         : xkmysql
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : das

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-08-21 20:58:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_path` varchar(500) DEFAULT NULL COMMENT '菜单URL',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单',
  `level` int(11) DEFAULT NULL COMMENT '菜单级别',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `is_enable` tinyint(4) DEFAULT NULL COMMENT '状态:1启用,0禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES ('1', '权限管理', 'hplus/index_v1.html', '0', '1', null, null, null, null, null, null);
INSERT INTO `s_menu` VALUES ('2', '协会工作人员', 'hplus/graph_echarts.html', '1', '2', null, null, null, null, null, null);
INSERT INTO `s_menu` VALUES ('3', '事务所会员', 'hplus/graph_flot.html', '1', '2', null, null, null, null, null, null);
INSERT INTO `s_menu` VALUES ('4', '协会工作人员的子菜单', 'hplus/graph_morris.html', '2', '3', null, null, null, null, null, null);
INSERT INTO `s_menu` VALUES ('5', '工作人的子菜单', 'hplus/graph_rickshaw.html', '4', '4', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(300) DEFAULT NULL COMMENT '说明',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `is_enable` tinyint(4) DEFAULT NULL COMMENT '状态:1启用,0禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', '超级管理员', null, '0', '1', null, null, null, null);
INSERT INTO `s_role` VALUES ('2', '普通管理员', null, '0', '1', null, null, null, null);
INSERT INTO `s_role` VALUES ('3', '游客', null, '0', '1', null, null, null, null);

-- ----------------------------
-- Table structure for s_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_role_menu`;
CREATE TABLE `s_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `is_enable` tinyint(4) DEFAULT NULL COMMENT '状态:1启用,0禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_menu
-- ----------------------------
INSERT INTO `s_role_menu` VALUES ('1', '1', '1', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('2', '1', '2', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('3', '1', '3', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('4', '1', '4', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('5', '1', '5', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('6', '2', '1', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('7', '2', '2', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('8', '2', '3', null, null, null, null, null, null);
INSERT INTO `s_role_menu` VALUES ('9', '2', '4', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for s_role_user
-- ----------------------------
DROP TABLE IF EXISTS `s_role_user`;
CREATE TABLE `s_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `is_enable` tinyint(4) DEFAULT NULL COMMENT '状态:1启用,0禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of s_role_user
-- ----------------------------
INSERT INTO `s_role_user` VALUES ('1', '1', '1', null, null, null, null, null, null);
INSERT INTO `s_role_user` VALUES ('2', '1', '2', null, null, null, null, null, null);
INSERT INTO `s_role_user` VALUES ('3', '1', '3', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `user_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `login_fail_count` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_del` varchar(255) DEFAULT NULL,
  `is_enable` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('管理员', 'admin', '1', '1', '', '', null, '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for s_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `s_user_login_log`;
CREATE TABLE `s_user_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ----------------------------
-- Records of s_user_login_log
-- ----------------------------
