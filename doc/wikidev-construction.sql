/*
 Navicat Premium Data Transfer

 Source Server         : wikidevABC123
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : rm-uf6470s9615e13hc4no.mysql.rds.aliyuncs.com:3306
 Source Schema         : wikidev

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 16/11/2021 18:11:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `parent` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类';

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL COMMENT '文档id',
  `content` mediumtext NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文档内容';



-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ebook_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '电子书id',
  `parent` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  `view_count` int(11) DEFAULT '0' COMMENT '阅读数',
  `vote_count` int(11) DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文档';

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `category1_id` bigint(20) DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint(20) DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面',
  `doc_count` int(11) DEFAULT '0' COMMENT '文档数',
  `view_count` int(11) DEFAULT '0' COMMENT '阅读数',
  `vote_count` int(11) DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电子书';

-- ----------------------------
-- Table structure for ebook_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `ebook_snapshot`;
CREATE TABLE `ebook_snapshot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ebook_id` bigint(20) DEFAULT '0' COMMENT '电子书id',
  `date` date DEFAULT NULL COMMENT '快照日期',
  `view_count` int(11) DEFAULT '0' COMMENT '阅读数',
  `vote_count` int(11) DEFAULT '0' COMMENT '点赞数',
  `view_increase` int(11) DEFAULT '0' COMMENT '阅读增长',
  `vote_increase` int(11) DEFAULT '0' COMMENT '点赞增长',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ebook_id_date_unique` (`ebook_id`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=1430 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电子书快照表';



-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `login_name` varchar(50) NOT NULL COMMENT '登陆名',
  `name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` char(32) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_unique` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

-- ----------------------------
-- Function structure for NVL
-- ----------------------------
DROP FUNCTION IF EXISTS `NVL`;
delimiter ;;
CREATE FUNCTION `NVL`(filedValue varchar(255),-- 字段原值
                      ifNullValue varchar(255))
 RETURNS varchar(255) CHARSET gbk
BEGIN
   DECLARE result varchar(255);
  -- DECLARE EXIT HANDLER FOR SQLEXCEPTION -- 异常处理

  IF filedValue ='' OR filedValue IS NULL THEN
    SET result = ifNullValue ;
  ELSE
    SET result = filedValue;
  END IF;
  RETURN result;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
