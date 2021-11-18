/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 120.77.213.159:3307
 Source Schema         : wiki

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 17/11/2021 23:53:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `category1_id` bigint DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面',
  `doc_count` int DEFAULT '0' COMMENT '文档数',
  `view_count` int DEFAULT '0' COMMENT '阅读数',
  `vote_count` int DEFAULT '0' COMMENT '点赞数',
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电子书';

-- ----------------------------
-- Records of ebook
-- ----------------------------
BEGIN;
INSERT INTO `ebook` VALUES (1, 'Spring Boot 入门教程', 202, 201, '零基础入门 Java 开发，企业级应用开发最佳首选框架', 'https://img.mukewang.com/szimg/604f2bb6099d6a8803240324.jpg', 13, 388, 15, '2021-11-01 23:14:53', '2021-11-01 23:15:00');
INSERT INTO `ebook` VALUES (2, 'Vue 入门教程', NULL, NULL, '零基础入门 Vue 开发，企业级应用开发最佳首选框架', 'http://127.0.0.1:8880/file/cover1.png', 0, 0, 0, '2021-11-02 23:15:05', '2021-11-02 23:15:09');
INSERT INTO `ebook` VALUES (3, 'Python 入门教程', NULL, NULL, '零基础入门 Python 开发，企业级应用开发最佳首选框架', NULL, 0, 0, 0, '2021-11-03 23:15:16', '2021-11-03 23:15:19');
INSERT INTO `ebook` VALUES (4, 'Mysql 入门教程', NULL, NULL, '零基础入门 Mysql 开发，企业级应用开发最佳首选框架', NULL, 0, 0, 0, '2021-11-04 23:15:26', '2021-11-04 23:15:30');
INSERT INTO `ebook` VALUES (5, 'web 入门教程', NULL, NULL, '零基础入门 web开发，企业级应用开发最佳首选框架', NULL, NULL, NULL, NULL, '2021-11-05 23:15:37', '2021-11-05 23:15:43');
INSERT INTO `ebook` VALUES (6, 'Spring Boot 测试', 203, 201, '零基础入坑 hahhahhhahhahahhahhahahahhahbxjabjxah', 'http://127.0.0.1:8880/file/cover1.png', 13, 336, 15, '2021-11-06 23:15:49', '2021-11-06 23:15:53');
INSERT INTO `ebook` VALUES (114980306611736576, 'Vue项目实战', 100, 101, NULL, NULL, NULL, NULL, NULL, '2021-11-07 23:15:59', '2021-11-07 23:16:03');
INSERT INTO `ebook` VALUES (116198433982910464, '123', NULL, NULL, '1222222', '123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ebook` VALUES (116198911336648704, '123123123', NULL, NULL, '123123123', '123123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ebook` VALUES (116199072301453312, '111111111', NULL, NULL, '111111111', '11111111', NULL, NULL, NULL, '2021-11-17 23:34:51', '2021-11-17 23:34:40');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
