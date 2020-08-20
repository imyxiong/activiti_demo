/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : demo_activiti

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 20/08/2020 11:49:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(64) CHARACTER SET utf8 NOT NULL,
  `department` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'worker', 'development', 'worker1');
INSERT INTO `tb_user` VALUES (2, 'worker', 'development', 'worker2');
INSERT INTO `tb_user` VALUES (3, 'department_manager', 'development', 'department_manager1');
INSERT INTO `tb_user` VALUES (4, 'department_manager', 'development', 'department_manager2');
INSERT INTO `tb_user` VALUES (5, 'manager', NULL, 'manager1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
