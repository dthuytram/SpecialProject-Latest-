-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ticket
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKlxcbsh4odc9s9spjfw4jgxyqw` (`role_id`),
  CONSTRAINT `FKlxcbsh4odc9s9spjfw4jgxyqw` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKsh2vm0ay5sevg0dor8v3xe0ok` FOREIGN KEY (`user_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK943irua9hqdyxtlc3v3sm2yox` (`country_id`),
  CONSTRAINT `FK943irua9hqdyxtlc3v3sm2yox` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'K97 Phạm Như Xương','2000-11-30','Tram3011','admin@gmail.com','Dang Thuy Tram',_binary '','2018380821','$2a$12$TjjwgOonuiVeeBw08dhxGODG9nXqn7VDjrZcqpsFE421y0Hle5qn.','0392426831',1),(2,'K97 Phạm Như Xương','2000-11-30','Tram301120','tramdangthuy3011@gmail.com','Hoang Hong Nhung',_binary '','2018380821','$2a$12$eY/cAXpUqgFg4tD2tmHAfevGkvJaCggSWF.07.gtIeWJyG6VcHYlS','0392426831',2);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline_type`
--

DROP TABLE IF EXISTS `airline_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airline_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `del_flag_airline` bit(1) DEFAULT NULL,
  `image_airline` varchar(255) DEFAULT NULL,
  `name_airline` varchar(255) DEFAULT NULL,
  `price_airline` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_type`
--

LOCK TABLES `airline_type` WRITE;
/*!40000 ALTER TABLE `airline_type` DISABLE KEYS */;
INSERT INTO `airline_type` VALUES (1,_binary '','/assets/imgs/VNAirlines.png','Hãng hàng không Vietnam Airlines (VNA)','23000000'),(2,NULL,'/assets/imgs/vietjet.jpg','Hãng hàng không Vietjet Air (VJ)','1000000'),(3,NULL,'/assets/imgs/pacific.jpg','Hãng hàng không Jetstar Pacific Airlines (BL)','35000000'),(4,NULL,'/assets/imgs/','Hãng hàng không Bamboo Airways (QH)','12300000'),(5,NULL,'/assets/imgs/','VIETSTAR AIRLINES','1000000'),(6,NULL,'/assets/imgs/','PACIFIC AIRLINES ','8000000'),(7,NULL,'/assets/imgs/','VIETRAVEL AIRLINES',NULL),(8,NULL,'',NULL,NULL),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `airline_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'Việt Nam'),(2,'Singapore'),(3,'Indonesia'),(4,'Hàn Quốc'),(5,'Nhật Bản'),(6,'Thái Lan'),(7,'Lào'),(8,'CamPuChia'),(9,'Mỹ'),(10,'Anh'),(11,'Hà Lan '),(12,'Philipines');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_customer` varchar(255) DEFAULT NULL,
  `birthday_customer` date DEFAULT NULL,
  `del_flag_customer` bit(1) DEFAULT NULL,
  `email_customer` varchar(255) DEFAULT NULL,
  `gender_customer` bit(1) DEFAULT NULL,
  `id_card_customer` varchar(255) DEFAULT NULL,
  `image_customer` longtext,
  `name_customer` varchar(255) DEFAULT NULL,
  `phone_customer` varchar(255) DEFAULT NULL,
  `point_customer` int DEFAULT NULL,
  `id_country` bigint DEFAULT NULL,
  `id_customer_type` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhd8mq4i0xoiistpmfmdulufab` (`id_country`),
  KEY `FKco2ecx7i1s5kh8ho8gqas7jtq` (`id_customer_type`),
  CONSTRAINT `FKco2ecx7i1s5kh8ho8gqas7jtq` FOREIGN KEY (`id_customer_type`) REFERENCES `customer_type` (`id`),
  CONSTRAINT `FKhd8mq4i0xoiistpmfmdulufab` FOREIGN KEY (`id_country`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_customer_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (1,'Khách hàng thường'),(2,'Khách hàng cao cấp');
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_employee` varchar(255) DEFAULT NULL,
  `birthday_employee` date DEFAULT NULL,
  `code_employee` varchar(255) DEFAULT NULL,
  `del_flag_employee` bit(1) DEFAULT NULL,
  `email_employee` varchar(255) DEFAULT NULL,
  `gender_employee` bit(1) DEFAULT NULL,
  `name_employee` varchar(255) DEFAULT NULL,
  `phone_employee` varchar(255) DEFAULT NULL,
  `id_employee_type` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiosvmx3x5u7b37o6oqf5t1vut` (`id_employee_type`),
  CONSTRAINT `FKiosvmx3x5u7b37o6oqf5t1vut` FOREIGN KEY (`id_employee_type`) REFERENCES `employee_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type`
--

DROP TABLE IF EXISTS `employee_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_employee_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type`
--

LOCK TABLES `employee_type` WRITE;
/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
INSERT INTO `employee_type` VALUES (1,'Nhân viên bán vé'),(2,'Nhân viên bán vé trong quầy'),(3,'Quản lí phòng'),(4,'Quản lí vé cao cấp');
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_flight` varchar(255) DEFAULT NULL,
  `date_end` varchar(255) DEFAULT NULL,
  `date_start` varchar(255) DEFAULT NULL,
  `del_flag_flight` bit(1) DEFAULT NULL,
  `from_flight` varchar(255) DEFAULT NULL,
  `to_flight` varchar(255) DEFAULT NULL,
  `id_airline_type` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7613w56ppa98c984odhn9d4h5` (`id_airline_type`),
  CONSTRAINT `FK7613w56ppa98c984odhn9d4h5` FOREIGN KEY (`id_airline_type`) REFERENCES `airline_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'0001','2022-11-01 16:30','2022-11-01 18:40',_binary '','Hồ Chí Minh (SGN)','Hà Nội (HAN)',1),(2,'0002','2022-11-01 18:30','2022-11-01 20:45',_binary '','Hồ Chí Minh (SGN)','Hà Nội (HAN)',2),(3,'0003','2022-11-01 19:30','2022-11-01 21:45',NULL,'Hồ Chí Minh (SGN)','Hà Nội (HAN)',3),(4,'0004','2022-11-01 23:00','2022-11-01 1:10',_binary '','Hồ Chí Minh (SGN)','Hà Nội (HAN)',4),(5,'0005','2022-11-01 17:20','2022-11-01 19:15',_binary '','Hồ Chí Minh (SGN)','Hà Nội (HAN)',5),(6,'0006','2022-11-01 19:10','2022-11-01 23:40',NULL,'Hồ Chí Minh (SGN)','Hà Nội (HAN)',1),(7,'0007','2022-11-01 20:15','2022-11-01 10:40',NULL,'Hồ Chí Minh (SGN)','Hà Nội (HAN)',2),(8,'0008','2022-11-01 17:35','2022-11-01 22:10',NULL,'Hồ Chí Minh (SGN)','Hà Nội (HAN)',3),(9,'0009','2022-11-01 17:10','2022-11-01 20:40',NULL,'Hồ Chí Minh (SGN)','Hà Nội (HAN)',1),(10,'0010','2022-11-01 19:00','2022-11-01 20:40',NULL,'Hồ Chí Minh (SGN)','Hà Nội (HAN)',1);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_news` varchar(255) DEFAULT NULL,
  `date_news` date DEFAULT NULL,
  `del_flag_news` bit(1) DEFAULT NULL,
  `description_news` longtext,
  `image_news` longtext,
  `name_news` varchar(255) DEFAULT NULL,
  `title_news` varchar(255) DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoylsulymenyd6acn7nki758n3` (`id_category`),
  CONSTRAINT `FKoylsulymenyd6acn7nki758n3` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_EMPLOYEE'),(3,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_seat` varchar(255) DEFAULT NULL,
  `del_flag_seat` bit(1) DEFAULT NULL,
  `position_seat` int NOT NULL,
  `status_seat` bit(1) DEFAULT NULL,
  `id_flight` bigint DEFAULT NULL,
  `id_seat_type` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43n18cyyc09xwxteodwkf2ldb` (`id_flight`),
  KEY `FKa5cpjal7fcnw04lwrp02124gb` (`id_seat_type`),
  CONSTRAINT `FK43n18cyyc09xwxteodwkf2ldb` FOREIGN KEY (`id_flight`) REFERENCES `flight` (`id`),
  CONSTRAINT `FKa5cpjal7fcnw04lwrp02124gb` FOREIGN KEY (`id_seat_type`) REFERENCES `seat_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,'0001',_binary '',1,_binary '\0',1,1),(2,'0002',_binary '',2,_binary '\0',1,1),(3,'0003',_binary '',3,_binary '\0',1,1),(4,'0004',_binary '',4,_binary '\0',1,1),(5,'0005',_binary '',5,_binary '\0',1,1),(6,'0006',_binary '',6,_binary '\0',1,1),(7,'0007',_binary '',7,_binary '\0',1,1),(8,'0008',_binary '',8,_binary '\0',1,1),(9,'0009',_binary '',9,_binary '\0',1,1),(10,'0011',_binary '',11,_binary '\0',1,1),(11,'0010',_binary '',10,_binary '\0',1,1),(12,'0015',_binary '',15,_binary '\0',1,1),(13,'0012',_binary '',12,_binary '\0',1,1),(14,'0013',_binary '',13,_binary '\0',1,1),(15,'0014',_binary '',14,_binary '\0',1,1),(16,'0015',_binary '',15,_binary '\0',1,1),(17,'0016',_binary '',16,_binary '\0',1,1),(18,'0017',_binary '',17,_binary '\0',1,1),(19,'0018',_binary '',18,_binary '\0',1,1),(20,'0019',_binary '',19,_binary '\0',1,1),(21,'0020',_binary '',20,_binary '\0',1,1),(22,'0021',_binary '',21,_binary '\0',1,1),(23,'0022',_binary '',16,_binary '\0',1,1),(24,'0023',_binary '',17,_binary '\0',1,1),(25,'0024',_binary '',18,_binary '\0',1,1),(26,'0025',_binary '',19,_binary '\0',1,1),(27,'0026',_binary '',20,_binary '\0',1,1),(28,'0027',_binary '',27,_binary '\0',1,1),(29,'0028',_binary '',28,_binary '\0',1,1),(30,'0029',_binary '',29,_binary '\0',1,1),(31,'0030',_binary '',30,_binary '\0',1,1);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_type`
--

DROP TABLE IF EXISTS `seat_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_seat_type` varchar(255) DEFAULT NULL,
  `price_seat_type` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_type`
--

LOCK TABLES `seat_type` WRITE;
/*!40000 ALTER TABLE `seat_type` DISABLE KEYS */;
INSERT INTO `seat_type` VALUES (1,'hangthuonggia',5861000),(2,'hangphothong',1288000);
/*!40000 ALTER TABLE `seat_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birthday_ticket` date DEFAULT NULL,
  `buyer_ticket` varchar(255) DEFAULT NULL,
  `code_ticket` varchar(255) DEFAULT NULL,
  `date_ticket` date DEFAULT NULL,
  `del_flag_ticket` bit(1) DEFAULT NULL,
  `email_ticket` varchar(255) DEFAULT NULL,
  `gender_ticket` bit(1) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `phone_ticket` varchar(255) DEFAULT NULL,
  `point_ticket` int DEFAULT NULL,
  `price_ticket` double DEFAULT NULL,
  `status_ticket` bit(1) DEFAULT NULL,
  `id_customer` bigint DEFAULT NULL,
  `id_employee` bigint DEFAULT NULL,
  `id_seat` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK996h4t6iax2acas3vh1ysox3m` (`id_customer`),
  KEY `FKcrjn2y5k9tku2sm67c93m0vta` (`id_employee`),
  KEY `FK6acg2rnspg9ha5yv8j89j83od` (`id_seat`),
  CONSTRAINT `FK6acg2rnspg9ha5yv8j89j83od` FOREIGN KEY (`id_seat`) REFERENCES `seat` (`id`),
  CONSTRAINT `FK996h4t6iax2acas3vh1ysox3m` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKcrjn2y5k9tku2sm67c93m0vta` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'2022-01-01','Đặng Thuỳ Trâm','0001','2022-11-03',_binary '','tramdang@gmail.com',_binary '','201828082','0392426832',NULL,1200000,_binary '',NULL,NULL,14),(2,'2022-01-01','Nguyễn Thị Nga','0002','2022-11-03',_binary '','tramdang@gmail.com',_binary '','201828082','0392426832',NULL,2300000,_binary '',NULL,NULL,13),(3,'2022-01-01','Hoàng Thị Xoan','0003','2022-11-03',_binary '','tramdang@gmail.com',_binary '','201828082','0392426832',NULL,NULL,NULL,NULL,NULL,15),(4,'2022-01-01','Hồ Hoài Anh','0004','2022-11-03',_binary '','tramdang@gmail.com',_binary '','201828082','0392426832',NULL,NULL,NULL,NULL,NULL,12),(5,'2022-01-01','Lưu Hương Giang','0005','2022-11-03',_binary '','tramdang@gmail.com',_binary '\0','201828082',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'2022-01-01','Lưu Thiên Hương','0006','2022-11-03',_binary '','tramdang@gmail.com',_binary '\0','201828082',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'2022-10-29','Hoàng Thái Hậu','0007',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'2022-10-29','Phạm Duy Mạnh','0008',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'2022-10-29','Võ Đăc Quy','0009',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'2022-10-29','Hạnh Thị Hà','0010',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'2022-11-09','Nguyễn Võ Uyên Nhi','0011',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'2022-11-09','Mai Đăng Chơn','0012',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'2022-11-09','Thái Thị Thu','0013',NULL,_binary '','tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'2022-11-09','Mạnh Thường Quân','0014',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'2022-11-09','Mạnh Thường Quân','0015',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'2022-11-09','Mạnh Thường Quân','0016',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'2022-11-09','Mạnh Thường Quân','0017',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'2022-11-09','Mạnh Thường Quân','0018',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'2022-11-09','Mạnh Thường Quân','0019',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'2022-11-09','Mạnh Thường Quân','0020',NULL,NULL,'tramdang@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_history`
--

DROP TABLE IF EXISTS `ticket_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birthday_ticket` date DEFAULT NULL,
  `buyer_ticket` varchar(255) DEFAULT NULL,
  `code_ticket` varchar(255) DEFAULT NULL,
  `date_ticket` date DEFAULT NULL,
  `del_flag_ticket` bit(1) DEFAULT NULL,
  `email_ticket` varchar(255) DEFAULT NULL,
  `gender_ticket` bit(1) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `phone_ticket` varchar(255) DEFAULT NULL,
  `point_ticket` int DEFAULT NULL,
  `price_ticket` double DEFAULT NULL,
  `status_ticket` bit(1) DEFAULT NULL,
  `id_customer` bigint DEFAULT NULL,
  `id_employee` bigint DEFAULT NULL,
  `id_seat` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8vl8t20er4ax2c9ovkm8xkcsf` (`id_customer`),
  KEY `FKm5mrlg5bxaw444f97ll5wtq26` (`id_employee`),
  KEY `FK6rydku901mgwialv5qwey8je1` (`id_seat`),
  CONSTRAINT `FK6rydku901mgwialv5qwey8je1` FOREIGN KEY (`id_seat`) REFERENCES `seat` (`id`),
  CONSTRAINT `FK8vl8t20er4ax2c9ovkm8xkcsf` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKm5mrlg5bxaw444f97ll5wtq26` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_history`
--

LOCK TABLES `ticket_history` WRITE;
/*!40000 ALTER TABLE `ticket_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-03 18:23:06
