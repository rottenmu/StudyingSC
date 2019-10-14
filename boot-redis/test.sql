/*
Navicat MySQL Data Transfer

Source Server         : 106.12.215.254
Source Server Version : 50726
Source Host           : 106.12.215.254:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-28 19:51:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '100', '1');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '100', '1');
