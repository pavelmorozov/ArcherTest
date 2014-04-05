CREATE DATABASE  IF NOT EXISTS `archer_base` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `archer_base`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: archer_base
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role` varchar(15) NOT NULL DEFAULT 'Guest',
  `pass` varchar(63) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin@mail.com','2014-01-12 13:08:22','ADMIN','edb0e96701c209ab4b50211c856c50c4'),(2,'user1@mail.com','2014-01-12 13:08:22','USER','73dbb4ed51752f4d60afaeec8c9733e8'),(3,'user2@mail.com','2014-01-12 13:09:16','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(4,'user3@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(5,'user4@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(6,'user5@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(7,'user6@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(8,'user7@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(9,'user8@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(10,'user9@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(11,'user10@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(12,'user11@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(13,'user12@mail.com','2014-02-24 17:46:39','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(14,'user13@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(15,'user14@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(16,'user15@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(17,'user16@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(18,'user17@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(19,'user18@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(20,'user19@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(21,'user20@mail.com','2014-02-27 07:10:25','USER','b584a1e82ecc5b70cc6343b7d6d150ad'),(298,'pavelmorozov@mail.ru','2014-04-05 09:55:38','USER','04eda44db4bb78d6eebc6c107a239747');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL,
  `admin` bigint(20) NOT NULL,
  `amount` decimal(14,2) NOT NULL,
  `regDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_user_regRecord` (`user`),
  KEY `FK_admin_RegRecord` (`admin`),
  CONSTRAINT `FK_admin_RegRecord` FOREIGN KEY (`admin`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_user_regRecord` FOREIGN KEY (`user`) REFERENCES `user` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (1,2,1,100.00,'2014-03-10 12:56:09'),(2,3,1,110.00,'2014-03-10 12:56:09'),(3,4,1,120.00,'2014-03-10 12:56:09'),(4,4,1,130.00,'2014-03-10 12:56:09'),(5,8,1,150.00,'2014-03-10 17:05:33'),(6,8,1,150.00,'2014-03-17 15:34:26'),(7,7,1,20.00,'2014-03-17 15:34:43'),(8,11,1,150.00,'2014-03-18 06:36:46'),(9,17,1,1000.00,'2014-03-18 06:37:10'),(10,17,1,100.00,'2014-03-18 06:37:22'),(11,17,1,50.00,'2014-03-18 06:37:33'),(12,16,1,10.00,'2014-03-18 06:37:45'),(13,16,1,300.00,'2014-03-18 06:38:00'),(14,18,1,30.00,'2014-03-18 06:38:20'),(15,19,1,100.00,'2014-03-18 06:38:41'),(16,19,1,200.00,'2014-03-18 06:38:51'),(17,19,1,100.00,'2014-03-18 06:39:07'),(18,15,1,700.00,'2014-03-18 06:39:18'),(19,6,1,30.00,'2014-03-18 06:39:50'),(20,6,1,30.00,'2014-03-18 06:40:02'),(21,6,1,50.00,'2014-03-18 06:40:09'),(22,7,1,100.00,'2014-03-18 06:40:17'),(23,2,1,1000.00,'2014-03-20 05:36:15'),(104,4,1,1.25,'2014-03-28 16:36:40'),(109,3,1,100.00,'2014-03-28 20:51:27'),(111,2,1,15.00,'2014-04-04 12:12:52'),(113,298,1,1500.00,'2014-04-05 09:57:32'),(114,298,1,10.25,'2014-04-05 09:58:02'),(115,298,1,100.00,'2014-04-05 09:58:24');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `account` bigint(20) NOT NULL,
  `balance` decimal(14,2) NOT NULL,
  PRIMARY KEY (`account`),
  KEY `FK_User_Account` (`account`),
  CONSTRAINT `FK_User_Account` FOREIGN KEY (`account`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,2715.00),(3,400.00),(4,1501.25),(5,150.00),(6,280.00),(7,233.00),(8,410.00),(9,7587.12),(10,42.75),(11,150.00),(12,33.70),(13,12.00),(14,0.00),(15,700.00),(16,310.00),(17,1150.00),(18,30.00),(19,400.00),(20,0.00),(21,0.00),(298,1610.25);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validation`
--

DROP TABLE IF EXISTS `validation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `validation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guest` bigint(20) NOT NULL,
  `uuid` varchar(127) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Account` (`guest`),
  CONSTRAINT `FK_Account` FOREIGN KEY (`guest`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validation`
--

LOCK TABLES `validation` WRITE;
/*!40000 ALTER TABLE `validation` DISABLE KEYS */;
INSERT INTO `validation` VALUES (1,3,'7sdfsgh515er1ger');
/*!40000 ALTER TABLE `validation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'archer_base'
--

--
-- Dumping routines for database 'archer_base'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-05 15:47:03
