/*
 Navicat Premium Data Transfer

 Source Server         : BetterMe
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : hsr_mybatis

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/04/2020 02:16:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_resume
-- ----------------------------
DROP TABLE IF EXISTS `tb_resume`;
CREATE TABLE `tb_resume`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_resume
-- ----------------------------
INSERT INTO `tb_resume` VALUES (1, NULL, '李四', NULL);
INSERT INTO `tb_resume` VALUES (2, '东莞', 'Pussy', '15900000001');
INSERT INTO `tb_resume` VALUES (3, 'Chengdu', 'Tom', '18400000000');
INSERT INTO `tb_resume` VALUES (4, '成都', 'John', '133000000');
INSERT INTO `tb_resume` VALUES (5, 'Beilin', 'Jack', '137000000');
INSERT INTO `tb_resume` VALUES (6, '', '', '');

SET FOREIGN_KEY_CHECKS = 1;
