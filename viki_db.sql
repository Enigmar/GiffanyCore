/*
Navicat MySQL Data Transfer

Source Server         : viki
Source Server Version : 50719
Source Host           : viki.lan:3306
Source Database       : viki_db

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-09-04 15:02:30
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parentskills
-- ----------------------------
INSERT INTO `parentskills` VALUES ('1', '0', null, null, null);

-- ----------------------------
-- Table structure for `parentskills_assignment`
-- ----------------------------
DROP TABLE IF EXISTS `parentskills_assignment`;
CREATE TABLE `parentskills_assignment` (
  `parentskill_named_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) NOT NULL,
  `parentskill_id` int(11) NOT NULL,
  PRIMARY KEY (`parentskill_named_id`,`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parentskills_assignment
-- ----------------------------
INSERT INTO `parentskills_assignment` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `speech_content`
-- ----------------------------
DROP TABLE IF EXISTS `speech_content`;
CREATE TABLE `speech_content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of speech_content
-- ----------------------------
INSERT INTO `speech_content` VALUES ('1', 'wetter');
INSERT INTO `speech_content` VALUES ('2', 'aktuell');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of subskills
-- ----------------------------
INSERT INTO `subskills` VALUES ('1', 'WeatherTemplate', 'getWeather', 'weatherKey=0b33ae42ba7acd32ab5ca39535b7568f#location=blieskastel');

-- ----------------------------
-- Table structure for `subskills_assignment`
-- ----------------------------
DROP TABLE IF EXISTS `subskills_assignment`;
CREATE TABLE `subskills_assignment` (
  `subskill_named_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) NOT NULL,
  `subskill_id` int(11) NOT NULL,
  `parentskill_id` int(11) NOT NULL,
  PRIMARY KEY (`subskill_named_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of subskills_assignment
-- ----------------------------
INSERT INTO `subskills_assignment` VALUES ('1', '2', '1', '1');
