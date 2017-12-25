/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : examination

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2017-12-25 14:55:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` varchar(255) NOT NULL,
  `answer_json` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `exam_id` varchar(255) DEFAULT NULL,
  `exam_name` varchar(255) DEFAULT NULL,
  `result_json` varchar(255) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
  `id` varchar(255) NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classify
-- ----------------------------
INSERT INTO `classify` VALUES ('3cf02cbdd96641dd81923925e777166e', '2017-12-19 20:15:45', '蜗牛', '847e33a46ea14a9b81825a586257be0e');
INSERT INTO `classify` VALUES ('847e33a46ea14a9b81825a586257be0e', '2017-12-19 20:13:20', '周杰伦', '');
INSERT INTO `classify` VALUES ('d835ee555b554003a3a9d436454d6387', '2017-12-19 20:13:27', '陈奕迅', '');
INSERT INTO `classify` VALUES ('68d989cccf6b40c298abcbc421a44aef', '2017-12-19 20:13:37', '薛之谦', '');
INSERT INTO `classify` VALUES ('ae2bbd2c9cbb493a8a3f2b1030a14378', '2017-12-19 20:13:43', '林俊杰', '');
INSERT INTO `classify` VALUES ('2b1a5a73cffd4b31a206473e56b051c5', '2017-12-19 20:15:26', '听妈妈的话', '847e33a46ea14a9b81825a586257be0e');
INSERT INTO `classify` VALUES ('ba85f12216c844abbe996f5e67eb21d8', '2017-12-19 20:15:37', '稻香', '847e33a46ea14a9b81825a586257be0e');
INSERT INTO `classify` VALUES ('24ddb031c6ef4715b2c897ca00fdf8bc', '2017-12-19 20:16:05', '不要说话', 'd835ee555b554003a3a9d436454d6387');
INSERT INTO `classify` VALUES ('a2c234bb890246d6aec99a37e8e8a4a9', '2017-12-19 20:16:10', '淘汰', 'd835ee555b554003a3a9d436454d6387');
INSERT INTO `classify` VALUES ('398ef7d7934e4c3fb5af07ea389b1d6b', '2017-12-19 20:16:17', '浮夸', 'd835ee555b554003a3a9d436454d6387');
INSERT INTO `classify` VALUES ('183bfda7e944427792fb8fc569d6eb0d', '2017-12-19 20:16:39', '暧昧', '68d989cccf6b40c298abcbc421a44aef');
INSERT INTO `classify` VALUES ('1e97a2ec6dbf412eb51be6b6c8c245b2', '2017-12-19 20:16:46', '动物世界', '68d989cccf6b40c298abcbc421a44aef');
INSERT INTO `classify` VALUES ('edd6f28799ae4d52958d96ab641e9502', '2017-12-19 20:16:59', '演员', '68d989cccf6b40c298abcbc421a44aef');
INSERT INTO `classify` VALUES ('d8e8b65dad8d48c3859f1b919ff578fc', '2017-12-19 20:17:07', '江南', 'ae2bbd2c9cbb493a8a3f2b1030a14378');
INSERT INTO `classify` VALUES ('50a9648d7ed44157a312c7466c3d7058', '2017-12-19 20:17:19', '曹操', 'ae2bbd2c9cbb493a8a3f2b1030a14378');
INSERT INTO `classify` VALUES ('57238cb40aa04098af8c11663ae5159a', '2017-12-19 20:17:31', '醉赤壁', 'ae2bbd2c9cbb493a8a3f2b1030a14378');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` varchar(255) NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `paper_json` varchar(255) DEFAULT NULL,
  `papername` varchar(255) DEFAULT NULL,
  `classify_id` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4smq5uw4wolgn1ira9j7jd8pi` (`classify_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('86362144d73c4ffcb67910554ac2bcc5', '2017-12-25 14:23:18', '[\"903f3f7e2f4141aa993f4116d24c3e09\",\"597dd74164aa49e092f664b1792691a5\",\"95cf8d70537d4596935253dc0a6901a1\",\"091ae9bd2d1549108970280494d204b7\"]', 'tetd', '2b1a5a73cffd4b31a206473e56b051c5', '4oBGXsU9bzlxbUK1y5MsTtXpYq6H9g');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` varchar(255) NOT NULL,
  `analysis` varchar(255) DEFAULT NULL,
  `determine` varchar(255) DEFAULT NULL,
  `newstime` datetime DEFAULT NULL,
  `options_json` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `question_difficulty` varchar(255) DEFAULT NULL,
  `question_type` varchar(255) DEFAULT NULL,
  `right_key` varchar(255) DEFAULT NULL,
  `tag_json` varchar(255) DEFAULT NULL,
  `classify_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3gltm0v0tf5g2t7oijkb7gec9` (`classify_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('091ae9bd2d1549108970280494d204b7', null, null, '2017-12-25 13:54:40', '[\"正确\",\"错误\"]', '交通标志和交通标线不属于交通信号。', null, '判断题', '错误', '[]', '2b1a5a73cffd4b31a206473e56b051c5');
INSERT INTO `subject` VALUES ('903f3f7e2f4141aa993f4116d24c3e09', null, null, '2017-12-25 13:54:40', '[\"A : 小型汽车\",\"B : 轻型自动挡载货汽车\",\"C : 低速载货汽车\",\"D : 456\"]', '准驾车型为小型自动挡汽车的，可以驾驶以下哪种车型？', null, '选择题', 'C', '[]', '2b1a5a73cffd4b31a206473e56b051c5');
INSERT INTO `subject` VALUES ('597dd74164aa49e092f664b1792691a5', null, null, '2017-12-25 13:54:40', '[\"正确\",\"错误\"]', '驾驶人记分没有达到满分，有罚款尚未缴纳的，记分转入下一记分周期。', null, '判断题', '正确', '[]', '2b1a5a73cffd4b31a206473e56b051c5');
INSERT INTO `subject` VALUES ('95cf8d70537d4596935253dc0a6901a1', null, null, '2017-12-25 13:54:40', '[\"正确\",\"错误\"]', '造成交通事故后逃逸，尚不构成犯罪的一次记12分。', null, '判断题', '正确', '[]', '2b1a5a73cffd4b31a206473e56b051c5');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `account` varchar(255) DEFAULT NULL,
  `autograph` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `headimgs` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4oBGXsU9bzlxbUK1y5MsTtXpYq6H9g', 'Koala', '此人较懒，暂无信息', '1990-01-01', '2017-12-19 21:29:27', '保密', '保密', '/images/default_koala.jpg', '4oBGXsU9bz', '保密', '保密', '保密', '考拉', '保密', 'E053F56BDF663B5284472F175C508924', 'FOREVER', '超级管理员', '保密');
INSERT INTO `user` VALUES ('45c428053bcd41d48164391dc07d8a95', '123456', '此人较懒，暂无信息', '1990-01-01', '2017-12-19 21:50:44', '保密', 'l1278945971@163.com', '/images/default_head.png', '96882d7e36', '保密', '保密', '保密', '96882d7e36', '保密', 'E10ADC3949BA59ABBE56E057F20F883E', 'STUDENTS', '普通用户', '保密');
