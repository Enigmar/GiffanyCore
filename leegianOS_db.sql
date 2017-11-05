/*
Navicat MySQL Data Transfer

Source Server         : viki
Source Server Version : 50719
Source Host           : viki.lan:3306
Source Database       : viki_db

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-05 19:27:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `parentskills`
-- ----------------------------
DROP TABLE IF EXISTS `parentskills`;
CREATE TABLE `parentskills` (
  `parentskill_id` int(11) NOT NULL AUTO_INCREMENT,
  `standalone` tinyint(1) NOT NULL DEFAULT '1',
  `java_class` varchar(255) DEFAULT NULL,
  `java_method` varchar(255) DEFAULT NULL,
  `serial_data` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`parentskill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `parentskills_assignment`
-- ----------------------------
DROP TABLE IF EXISTS `parentskills_assignment`;
CREATE TABLE `parentskills_assignment` (
  `word_group_id` int(11) NOT NULL,
  `parentskill_id` int(11) NOT NULL,
  PRIMARY KEY (`word_group_id`,`parentskill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `speech_synonyms`
-- ----------------------------
DROP TABLE IF EXISTS `speech_synonyms`;
CREATE TABLE `speech_synonyms` (
  `synonym_id` int(11) NOT NULL AUTO_INCREMENT,
  `word_group_id` int(11) NOT NULL,
  `synonym` varchar(255) NOT NULL,
  PRIMARY KEY (`synonym_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `speech_word_group`
-- ----------------------------
DROP TABLE IF EXISTS `speech_word_group`;
CREATE TABLE `speech_word_group` (
  `word_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `word_example` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`word_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `subskills`
-- ----------------------------
DROP TABLE IF EXISTS `subskills`;
CREATE TABLE `subskills` (
  `subskill_id` int(11) NOT NULL AUTO_INCREMENT,
  `java_class` varchar(255) DEFAULT NULL,
  `java_method` varchar(255) DEFAULT NULL,
  `serial_data` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`subskill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `subskills_assignment`
-- ----------------------------
DROP TABLE IF EXISTS `subskills_assignment`;
CREATE TABLE `subskills_assignment` (
  `word_group_id` int(11) NOT NULL,
  `subskill_id` int(11) NOT NULL,
  `parentskill_id` int(11) NOT NULL,
  PRIMARY KEY (`word_group_id`,`subskill_id`,`parentskill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

