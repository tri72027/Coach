-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: coach
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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `car_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `code` varchar(10) NOT NULL,
  `license_plates` varchar(45) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,9,'LS01','75B-000425','Limousine',50000),(2,12,'LS02','75B-001422','Limousine',70000),(3,17,'LS03','75B-002735','Limousine',100000);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `current_choose_seat`
--

DROP TABLE IF EXISTS `current_choose_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `current_choose_seat` (
  `choose_seat_id` bigint NOT NULL AUTO_INCREMENT,
  `seats` varchar(255) DEFAULT NULL,
  `car_id` bigint DEFAULT NULL,
  `trip_id` bigint DEFAULT NULL,
  PRIMARY KEY (`choose_seat_id`),
  KEY `FK7vdss23j9rvk9udv95ly6i0vj` (`car_id`),
  KEY `FK3p40to64oslg9r1614acwy5db` (`trip_id`),
  CONSTRAINT `FK3p40to64oslg9r1614acwy5db` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`),
  CONSTRAINT `FK7vdss23j9rvk9udv95ly6i0vj` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_choose_seat`
--

LOCK TABLES `current_choose_seat` WRITE;
/*!40000 ALTER TABLE `current_choose_seat` DISABLE KEYS */;
INSERT INTO `current_choose_seat` VALUES (1,'A09',2,5),(80,'A03',1,37),(81,'A06',1,37);
/*!40000 ALTER TABLE `current_choose_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `info` (
  `info_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `identification_number` varchar(12) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`info_id`),
  KEY `FKlwgnpuilbh37r12bu32bgfbow` (`user_id`),
  CONSTRAINT `FKlwgnpuilbh37r12bu32bgfbow` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` VALUES (1,'204 P1','tri72027@gmail.com','Nguy???n V??n Minh Tr??','191848429','0702427138',1),(2,'25 Nguy???n V??n C???','asdhasd@gmail.,com','Nguy???n Xuaan T??n An','123456789','0123456789',NULL),(3,NULL,'as3434sd@gmail.,com','Nguy???n V??n A','123456789','4892134',NULL),(4,NULL,'ABC@hiler.com','Nguy???n V??n ABC','12323213123','7024271638',NULL),(5,NULL,'tri7203@ga.com','Nguy???n V??n Minh Tr??','12323213123','+84702427138',NULL),(6,NULL,'tri7203@ga.com','Nguy???n V??n Minh Tr??','123123123','+84702427138',NULL),(7,NULL,'tri7203@ga.com','Nguy???n V??n Minh Tr??','12323213123','+84702427138',NULL),(8,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(9,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','12323213123','+84702427138',NULL),(10,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(11,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(12,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(13,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(14,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(15,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(16,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(17,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(18,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(19,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(20,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(21,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(22,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(23,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(24,NULL,'ABC@hiler.com','Nguy???n V??n ABC','12323213123','7024271638',NULL),(25,NULL,'tri7203@ga.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(26,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(27,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL),(28,NULL,'tri72027@gmail.com','Nguy???n V??n Minh Tr??','123123123213','+84702427138',NULL);
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `province_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'BG','B???c Giang'),(2,'BK','B???c K???n'),(3,'CB','Cao B???ng'),(4,'HG','H?? Giang'),(5,'LS','L???ng S??n'),(6,'PT','Ph?? Th???'),(7,'QN','Qu???ng Ninh'),(8,'TN','Th??i Nguy??n'),(9,'TQ','Tuy??n Quang'),(10,'LC','L??o Cai'),(11,'YB','Y??n B??i'),(12,'DB','??i???n Bi??n'),(13,'HB','H??a B??nh'),(14,'LC','Lai Ch??u'),(15,'SL','S??n La'),(16,'BN','B???c Ninh'),(17,'HNA','H?? Nam'),(18,'HD','H???i D????ng'),(19,'HY','H??ng Y??n'),(20,'ND','Nam ?????nh'),(21,'NB','Ninh B??nh'),(22,'TB','Th??i B??nh'),(23,'VP','V??nh Ph??c'),(24,'HN','H?? N???i'),(25,'HP','H???i Ph??ng'),(26,'HT','H?? T??nh'),(27,'NA','Ngh??? An'),(28,'QB','Qu???ng B??nh'),(29,'QT','Qu???ng Tr???'),(30,'TH','Thanh H??a'),(31,'TTH','Hu???'),(32,'DL','?????k L???k'),(33,'DN','?????k N??ng'),(34,'GL','Gia Lai'),(35,'KT','Kon Tum'),(36,'LD','L??m ?????ng'),(37,'BD','B??nh ?????nh'),(38,'BT','B??nh Thu???n'),(39,'KH','Kh??nh H??a'),(40,'NT','Ninh Thu???n'),(41,'PY','Ph?? Y??n'),(42,'QNA','Qu???ng Nam'),(43,'QNG','Qu???ng Ng??i'),(44,'??N','???? N???ng'),(45,'BRVT','B?? R???a???V??ng T??u'),(46,'BD','B??nh D????ng'),(47,'BP','B??nh Ph?????c'),(48,'DNAI','?????ng Nai'),(49,'TN','T??y Ninh'),(50,'HCM','H??? Ch?? Minh'),(51,'AG','An Giang'),(52,'BL','B???c Li??u'),(53,'BTRE','B???n Tre'),(54,'CM','C?? Mau'),(55,'??T','?????ng Th??p'),(56,'HG','H???u Giang'),(57,'KG','Ki??n Giang'),(58,'LA','Long An'),(59,'ST','S??c Tr??ng'),(60,'TG','Ti???n Giang'),(61,'TV','Tr?? Vinh'),(62,'VL','V??nh Long'),(63,'CT','C???n Th??');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `description` varchar(600) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'AD','Quy???n Qu???n Tr??? Cao Nh???t','Qu???n Tr??? H??? Th???ng'),(2,'US','Quy???n c???a ng?????i d??ng','Ng?????i d??ng');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `route_id` bigint NOT NULL AUTO_INCREMENT,
  `departure_time` varchar(255) DEFAULT NULL,
  `journey_time` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `province_end` bigint DEFAULT NULL,
  `province_start` bigint DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  KEY `FKf35sld3wxdk9m2o6ocfth9y87` (`province_end`),
  KEY `FKppwgd1o09bh957ommhjuai46k` (`province_start`),
  CONSTRAINT `FKf35sld3wxdk9m2o6ocfth9y87` FOREIGN KEY (`province_end`) REFERENCES `province` (`province_id`),
  CONSTRAINT `FKppwgd1o09bh957ommhjuai46k` FOREIGN KEY (`province_start`) REFERENCES `province` (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'8h','20h',150000,24,31),(2,'7h30','2h',120000,44,31),(3,'10h30','20h',150000,31,24),(4,'12h30','2h',120000,31,44);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_ticket`
--

DROP TABLE IF EXISTS `status_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_ticket` (
  `status_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_ticket`
--

LOCK TABLES `status_ticket` WRITE;
/*!40000 ALTER TABLE `status_ticket` DISABLE KEYS */;
INSERT INTO `status_ticket` VALUES (1,'???? s??? d???ng'),(2,'???? thanh to??n');
/*!40000 ALTER TABLE `status_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_trip`
--

DROP TABLE IF EXISTS `status_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_trip` (
  `status_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_trip`
--

LOCK TABLES `status_trip` WRITE;
/*!40000 ALTER TABLE `status_trip` DISABLE KEYS */;
INSERT INTO `status_trip` VALUES (1,'???? ch???y'),(2,'??ang ch???');
/*!40000 ALTER TABLE `status_trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_seats` int NOT NULL,
  `date` date NOT NULL,
  `price` double NOT NULL,
  `seats` varchar(255) NOT NULL,
  `ticket_code` varchar(20) NOT NULL,
  `status` bigint DEFAULT NULL,
  `trip_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FK9xuwjcepe2ptko10brqmk6w7k` (`status`),
  KEY `FKjp9yu1pw75obvo1aaao6os7vk` (`trip_id`),
  KEY `FKdvt57mcco3ogsosi97odw563o` (`user_id`),
  CONSTRAINT `FK9xuwjcepe2ptko10brqmk6w7k` FOREIGN KEY (`status`) REFERENCES `status_ticket` (`status_id`),
  CONSTRAINT `FKdvt57mcco3ogsosi97odw563o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKjp9yu1pw75obvo1aaao6os7vk` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (19,1,'2021-02-25',170000,'A07','LS01A07',2,61,NULL),(20,1,'2021-02-25',170000,'A01','LS01A01',2,63,NULL),(21,2,'2021-02-25',340000,'A05,A09','LS01A05A09',2,61,NULL),(22,2,'2021-02-25',340000,'A04,A08','LS01A04A08',2,63,NULL),(23,4,'2021-02-25',680000,'A01,A02,A06,A05','LS01A01A02A06A05',2,69,NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `trip_id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `car_id` bigint NOT NULL,
  `route_id` bigint NOT NULL,
  `status` bigint NOT NULL,
  PRIMARY KEY (`trip_id`),
  KEY `FKmjbnpu0uj7uodbn1f5j0kp9v6` (`car_id`),
  KEY `FKeva4adpyk6glllffnw5ypj20j` (`route_id`),
  KEY `FK6fyqaeaonuxfy24r6she8mclh` (`status`),
  CONSTRAINT `FK6fyqaeaonuxfy24r6she8mclh` FOREIGN KEY (`status`) REFERENCES `status_trip` (`status_id`),
  CONSTRAINT `FKeva4adpyk6glllffnw5ypj20j` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`),
  CONSTRAINT `FKmjbnpu0uj7uodbn1f5j0kp9v6` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,'2021-02-02',1,1,2),(2,'2021-02-02',2,1,2),(3,'2021-02-02',3,1,2),(4,'2021-02-02',1,2,2),(5,'2021-02-02',2,2,2),(6,'2021-02-22',1,1,2),(7,'2021-02-22',1,2,2),(8,'2021-02-22',1,3,2),(9,'2021-02-22',1,4,2),(10,'2021-02-22',2,1,2),(11,'2021-02-22',2,2,2),(12,'2021-02-22',2,3,2),(13,'2021-02-22',2,4,2),(14,'2021-02-23',1,1,2),(15,'2021-02-23',1,2,2),(16,'2021-02-23',1,3,2),(17,'2021-02-23',1,4,2),(18,'2021-02-23',2,1,2),(19,'2021-02-23',2,2,2),(20,'2021-02-23',2,3,2),(21,'2021-02-23',2,4,2),(22,'2021-02-26',1,1,2),(37,'2021-02-26',1,2,2),(38,'2021-02-26',1,3,2),(39,'2021-02-26',1,4,2),(40,'2021-02-26',2,1,2),(41,'2021-02-26',2,2,2),(42,'2021-02-26',2,3,2),(43,'2021-02-26',2,4,2),(60,'2021-02-27',1,1,2),(61,'2021-02-27',1,2,2),(62,'2021-02-27',1,3,2),(63,'2021-02-27',1,4,2),(64,'2021-02-27',2,1,2),(65,'2021-02-27',2,2,2),(66,'2021-02-27',2,3,2),(67,'2021-02-27',2,4,2),(68,'2021-02-28',1,1,2),(69,'2021-02-28',1,2,2),(70,'2021-02-28',1,3,2),(71,'2021-02-28',1,4,2),(72,'2021-02-28',2,1,2),(73,'2021-02-28',2,2,2),(74,'2021-02-28',2,3,2),(75,'2021-02-28',2,4,2),(76,'2021-03-01',1,1,2),(77,'2021-03-01',1,2,2),(78,'2021-03-01',1,3,2),(79,'2021-03-01',1,4,2),(80,'2021-03-01',2,1,2),(81,'2021-03-01',2,2,2),(82,'2021-03-01',2,3,2),(83,'2021-03-01',2,4,2),(84,'2021-03-02',1,2,2),(85,'2021-03-02',1,3,2),(86,'2021-03-02',1,4,2),(87,'2021-03-02',2,1,2),(88,'2021-03-02',2,2,2),(89,'2021-03-02',2,3,2),(90,'2021-03-02',2,4,2);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` date NOT NULL,
  `modify_time` date DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2021-02-02',NULL,'123456','tri72027',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 17:54:28
