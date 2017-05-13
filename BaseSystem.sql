/*
 Navicat Premium Data Transfer

 Source Server         : MariaDB On Docker
 Source Server Type    : MariaDB
 Source Server Version : 100122
 Source Host           : localhost
 Source Database       : BaseSystem

 Target Server Type    : MariaDB
 Target Server Version : 100122
 File Encoding         : utf-8

 Date: 05/13/2017 22:16:03 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `App`
-- ----------------------------
DROP TABLE IF EXISTS `App`;
CREATE TABLE `App` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `appid` varchar(255) NOT NULL,
  `secret` varchar(255) NOT NULL,
  `domain` varchar(255) NOT NULL,
  `ban` varchar(2) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `App`
-- ----------------------------
BEGIN;
INSERT INTO `App` VALUES ('1', '博客', 'sdrgsald', 'ihdfsvasn', 'http%3a%2f%2fblog.godmod.top', '0', '2017-05-13 11:00:39', null);
COMMIT;

-- ----------------------------
--  Table structure for `Token`
-- ----------------------------
DROP TABLE IF EXISTS `Token`;
CREATE TABLE `Token` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `appid` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `redirect_uri` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `authorization_code` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `access_token` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `used` varchar(2) COLLATE utf8mb4_bin NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `Token`
-- ----------------------------
BEGIN;
INSERT INTO `Token` VALUES ('11', 'jiangzhou', 'sdrgsald', '%2Fwp-admin', '07296a060d22022f614b67400e682008', '422a4c4b482c68070f60410e694e0926', '1', '2017-05-13 22:09:39', '2017-05-13 22:10:46'), ('12', 'jiangzhou', 'sdrgsald', '%2Fwp-admin', '04602e6a07086f0343232667270e6f44', '046444640d056a4062052f0a4a4c2e25', '1', '2017-05-13 22:11:00', '2017-05-13 22:11:11');
COMMIT;

-- ----------------------------
--  Table structure for `User`
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `User`
-- ----------------------------
BEGIN;
INSERT INTO `User` VALUES ('1', 'jiangzhou', 'jiangzhou', null, null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
