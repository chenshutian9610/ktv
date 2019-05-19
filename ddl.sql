-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ktv
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户 id',
  `song_id` bigint(20) DEFAULT '0' COMMENT '歌曲编号',
  `generation` int(11) DEFAULT '0' COMMENT '年代'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `singer_info`
--

DROP TABLE IF EXISTS `singer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `singer_info` (
  `singer_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `singer_name` varchar(40) DEFAULT '' COMMENT '歌手姓名',
  `singer_sex` tinyint(4) DEFAULT '-1' COMMENT '性别：( 1 : 男 ) ( 0 : 女 )( -1 : 未知)',
  `singer_nationality` varchar(40) DEFAULT '' COMMENT '所在国籍',
  PRIMARY KEY (`singer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='歌星信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `song_info`
--

DROP TABLE IF EXISTS `song_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_info` (
  `song_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `singer_id` bigint(20) DEFAULT '0' COMMENT '歌手编号',
  `song_name` varchar(255) DEFAULT '' COMMENT '歌名',
  `song_name_abbr` varchar(40) DEFAULT '' COMMENT '歌名缩写',
  `song_language` varchar(40) DEFAULT '' COMMENT '语种',
  `song_file_name` varchar(255) DEFAULT '' COMMENT '歌曲文件名',
  `duration` varchar(8) DEFAULT '' COMMENT '时长',
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='歌曲信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT '' COMMENT '账户名',
  `real_name` varchar(40) DEFAULT '' COMMENT '真实姓名',
  `password` varchar(40) DEFAULT '' COMMENT '密码',
  `user_sex` tinyint(4) DEFAULT '0' COMMENT '性别：( 1 : 男 ) ( 0 : 女 )',
  `birthday` datetime DEFAULT NULL COMMENT '出生时间',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-17 22:34:09
