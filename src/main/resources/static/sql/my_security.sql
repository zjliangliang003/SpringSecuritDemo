/*
 Navicat Premium Data Transfer

 Source Server         : localsql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : my_security

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 19/10/2020 17:28:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `authorityId` int(255) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authorityName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称，ROLE_开头，全大写',
  `authorityRemark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限描述',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `authorityContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限内容，可访问的url，多个时用,隔开',
  PRIMARY KEY (`authorityId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES (1, 'ROLE_USER', '普通用户', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '/sys/**');
INSERT INTO `sys_authority` VALUES (2, 'ROLE_SA', '超级管理员', '2019-09-10 10:08:58', '2020-09-10 10:08:58', '/sys/**,/logging');
INSERT INTO `sys_authority` VALUES (3, 'ROLE_ADMIN', '管理员', '2019-09-10 10:08:58', '2020-06-10 10:08:58', '/sys/**');
INSERT INTO `sys_authority` VALUES (5, 'ROLE_ANONYMOUS', '匿名用户', '2020-10-19 03:07:15', '2020-10-19 03:10:15', 'sys/**');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menuPath` varchar(62) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menuParentId` int(11) UNSIGNED NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '/sys', 0, '2020-10-15 11:05:00', '2020-10-15 11:05:03');
INSERT INTO `sys_menu` VALUES (2, '菜单管理', '/sys/sysMenu/menu', 1, '2020-10-15 11:06:14', '2020-10-15 11:06:17');
INSERT INTO `sys_menu` VALUES (3, '权限管理', '/sys/sysAuthority/authority', 1, '2020-10-15 11:06:39', '2020-10-15 11:06:42');
INSERT INTO `sys_menu` VALUES (4, '用户管理', '/sys/sysUser/user', 1, '2020-10-15 11:07:08', '2020-10-15 11:07:10');
INSERT INTO `sys_menu` VALUES (5, '系统设置', '/sys/sysSetting/setting', 1, '2020-10-15 11:07:32', '2020-10-15 11:07:34');

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `id` int(255) NOT NULL COMMENT '表id',
  `sysName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称',
  `sysLogo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统logo图标',
  `sysBottomText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统底部信息',
  `sysNoticeText` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '系统公告',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `userInitPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户管理：初始、重置密码',
  `sysColor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统颜色',
  `sysApiEncrypt` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'API加密 Y/N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES (1, 'Base_Admin', 'https://avatar.gitee.com/uploads/0/5137900_huanzi-qch.png!avatar100?1562729811', '© 2019 - 2020  XXX系统', '<p><span style=\"border: 1px solid rgb(0, 0, 0);\"><strong><em><span style=\"border: 1px solid rgb(0, 0, 0); text-decoration: underline;\">aaaa</span></em></strong></span></p>', '2019-09-17 10:15:38', '2020-10-19 09:16:05', '123456', 'rgba(118, 194, 227, 1)', 'N');

-- ----------------------------
-- Table structure for sys_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_authority`;
CREATE TABLE `sys_user_authority`  (
  `userAuthorityId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户权限表id',
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `authorityId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`userAuthorityId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_authority
-- ----------------------------
INSERT INTO `sys_user_authority` VALUES ('1', '2', '1', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_authority` VALUES ('2', '2', '3', '2019-09-17 12:09:47', '2019-09-17 12:09:47');
INSERT INTO `sys_user_authority` VALUES ('3', '1', '2', '2019-09-17 12:00:37', '2019-09-17 12:00:37');

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu`  (
  `userMenuId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updataTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`userMenuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------
INSERT INTO `sys_user_menu` VALUES (1, 1, 1, '2020-10-15 11:15:34', '2020-10-15 11:15:37');
INSERT INTO `sys_user_menu` VALUES (2, 1, 2, '2020-10-15 11:15:44', '2020-10-15 11:15:47');
INSERT INTO `sys_user_menu` VALUES (3, 1, 3, '2020-10-15 11:16:04', '2020-10-15 11:16:07');
INSERT INTO `sys_user_menu` VALUES (4, 1, 4, '2020-10-15 11:16:14', '2020-10-15 11:16:17');
INSERT INTO `sys_user_menu` VALUES (5, 1, 5, '2020-10-15 11:16:25', '2020-10-15 11:16:27');
INSERT INTO `sys_user_menu` VALUES (6, 2, 1, '2020-10-15 11:16:55', '2020-10-15 11:16:58');
INSERT INTO `sys_user_menu` VALUES (7, 2, 3, '2020-10-15 11:17:06', '2020-10-15 11:17:09');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userId` int(255) NOT NULL COMMENT '用户ID',
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '软删除标识，Y/N',
  `limitedIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '限制允许登录的IP集合',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '账号失效时间，超过时间将不能登录系统',
  `lastChangePwdTime` datetime(0) NOT NULL COMMENT '最近修改密码时间，超出时间间隔，提示用户修改密码',
  `limitMultiLogin` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否允许账号同一个时刻多人在线，Y/N',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'super', '超级用户', '$2a$10$slaUJ3fIIdc9rEu0yixHyuosQW247.skwPwWrxgKvGuZl9Yi/NbhC', 'Y', NULL, NULL, '2020-10-14 14:43:45', 'N', '2020-10-14 14:43:49', '2020-10-14 14:44:33');
INSERT INTO `t_user` VALUES (2, 'admin', '管理员', '$2a$10$slaUJ3fIIdc9rEu0yixHyuosQW247.skwPwWrxgKvGuZl9Yi/NbhC', 'N', NULL, NULL, '2020-10-14 14:45:12', 'N', '2020-10-14 14:45:17', '2020-10-14 14:45:20');

SET FOREIGN_KEY_CHECKS = 1;
