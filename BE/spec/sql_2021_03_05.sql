-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hrm
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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `courses_id` char(15) NOT NULL,
  `courses_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courses_level` char(15) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `teacher` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `current_members` int NOT NULL,
  `max_members` int NOT NULL,
  `deadline` date NOT NULL,
  `status` int NOT NULL,
  `room` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`courses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('N3K01','N3 Giao Tiep','N3','2021-02-23','2021-02-23','Nguyen Ngoc Trinh',6,20,'2021-02-23',1,'F5301'),('N4K01','N4 Tieu Chuan','N4','2021-02-23','2021-02-23','Hoang Thanh Anh',2,20,'2021-02-23',2,'F3301'),('N4K02','N4 Ban Dem','N4','2021-02-23','2021-02-23','Mai Phuong Thuy',1,20,'2021-02-23',3,'F5301'),('N5K02','N5 Tieu Chuan','N5','2021-02-23','2021-02-23','Hoang Thanh Anh',1,20,'2021-02-23',1,'F4301'),('N5K03','N5 Cap Toc','N5','2021-02-23','2021-02-23','Nguyen Hong Chau',0,20,'2021-02-23',0,'F6301');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_table` (
  `id` int NOT NULL,
  `weeksday` char(15) NOT NULL,
  `starttime` time NOT NULL,
  `endtime` time NOT NULL,
  `courses_id` char(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courses_id` (`courses_id`),
  CONSTRAINT `time_table_ibfk_1` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`courses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_table`
--

LOCK TABLES `time_table` WRITE;
/*!40000 ALTER TABLE `time_table` DISABLE KEYS */;
INSERT INTO `time_table` VALUES (1,'Monday','08:00:00','10:00:00','N5K02'),(2,'Thursday','08:00:00','10:00:00','N5K02'),(3,'Friday','08:00:00','10:00:00','N5K02'),(4,'Monday','15:00:00','17:00:00','N3K01'),(5,'Thursday','15:00:00','17:00:00','N4K01'),(6,'Friday','15:00:00','17:00:00','N3K01'),(7,'Tuesday','19:00:00','21:00:00','N4K02'),(8,'Wednesday','19:00:00','21:00:00','N4K02'),(9,'Friday','18:00:00','20:00:00','N4K02');
/*!40000 ALTER TABLE `time_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` char(15) NOT NULL,
  `full_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) NOT NULL,
  `department` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `sex` int NOT NULL,
  `birth_date` date NOT NULL,
  `jlpt_level` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('BRYCENVN340','NGO HUNG','ngohung@gmail.com','DEV_EMS','0905.234.456',2,'2021-02-23',' N3'),('BRYCENVN341','MAI VAN DAT','datmai@gmail.com','DEV_EMS','0906.235.256',2,'2021-02-23','N3'),('BRYCENVN342','NGUYEN VAN HIEN','vanhien@gmail.com','DEV_BAS','0325698754',1,'2021-02-23','N2'),('BRYCENVN343','LE CONG TU','tudeptrai@gmail.com','DEV_BAS','0702.65.68.77',1,'2021-02-23','N4'),('BRYCENVN348','NGO HUNG','lenguyen@gmail.com','DEV_EMS','0702.65.68.99',1,'2021-02-23','N5'),('BRYCENVN349','LE DINH HIEU','dinhhieu@gmail.com','DEV_EMS','0905.123.123',1,'2021-02-23','N5'),('BRYCENVN500','LE NGUYEN THI','ngthi87@gmail.com','DEV_EMS','0905.179.103',1,'1995-07-02','N5'),('BRYCENVN501','LE VIET QUOC','quoclv89@gmail.com','DEV_EMS','0348.975.996',1,'1993-03-21','N3'),('BRYCENVN502','DUONG VU THONG','vuthongvuboi09@gmail.com','DEV_EMS','0379.234.884',1,'1998-02-23',' N3'),('BRYCENVN503','TRIEU TRI HUNG','trihugdoandu756@gmail.com','DEV_EMS','0751.898.859',1,'1996-05-28','N5'),('BRYCENVN504','TRUONG HOANG THAI','hoangthaitu99@gmail.com','DEV_BAS','0972.467.869',1,'1992-02-28','N2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_courses`
--

DROP TABLE IF EXISTS `users_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_courses` (
  `id` int NOT NULL,
  `user_id` char(15) DEFAULT NULL,
  `courses_id` char(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `courses_id` (`courses_id`),
  CONSTRAINT `users_courses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `users_courses_ibfk_2` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`courses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_courses`
--

LOCK TABLES `users_courses` WRITE;
/*!40000 ALTER TABLE `users_courses` DISABLE KEYS */;
INSERT INTO `users_courses` VALUES (1,'BRYCENVN348','N5K02'),(2,'BRYCENVN340','N4K02'),(3,'BRYCENVN341','N3K01'),(4,'BRYCENVN342','N5K02'),(5,'BRYCENVN343','N5K03'),(6,'BRYCENVN500','N3K01'),(7,'BRYCENVN501','N3K01'),(8,'BRYCENVN502','N3K01'),(9,'BRYCENVN503','N3K01'),(10,'BRYCENVN504','N3K01');
/*!40000 ALTER TABLE `users_courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-05 17:11:03
