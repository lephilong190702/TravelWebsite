-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: traveldb
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int DEFAULT NULL,
  `payment_id` int DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `booking_numberofaudult` int DEFAULT NULL,
  `booking_numberofchidren` int DEFAULT NULL,
  `booking_adultunitprice` float DEFAULT NULL,
  `booking_childunitprice` float DEFAULT NULL,
  `booking_notes` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`booking_id`),
  KEY `fk_booking_tour_package_idx` (`tour_id`),
  KEY `fk_booking_payment_idx` (`payment_id`),
  CONSTRAINT `fk_booking_payment` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  CONSTRAINT `fk_booking_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (3,1,3,NULL,23,25,500000,300000,NULL),(4,8,4,NULL,1,0,3500000,1000000,NULL),(5,4,5,NULL,4,1,600000,400000,NULL),(6,7,6,NULL,1,2,3000000,500000,NULL),(7,34,7,NULL,2,1,400000,200000,NULL),(8,6,8,NULL,1,1,1200000,600000,NULL),(9,33,9,NULL,1,1,1400000,500000,NULL),(10,25,10,NULL,1,1,100000,50000,NULL),(11,23,11,NULL,2,3,100000,50000,NULL),(12,35,12,NULL,1,2,800000,500000,NULL),(13,48,12,NULL,1,1,600000,400000,NULL),(14,1,13,NULL,1,1,500000,300000,NULL),(15,43,14,NULL,1,1,1500000,500000,NULL),(16,44,15,NULL,5,2,1300000,200000,NULL),(17,44,16,NULL,10,15,1300000,200000,NULL),(18,32,17,NULL,5,2,1000000,700000,NULL),(19,47,18,NULL,6,3,700000,400000,NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `news_id` int DEFAULT NULL,
  `comment_content` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `comment_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_tour_idx` (`tour_id`),
  KEY `fk_comment_news_idx` (`news_id`),
  KEY `fk_comment_user_idx` (`user_id`),
  CONSTRAINT `fk_comment_news` FOREIGN KEY (`news_id`) REFERENCES `news` (`news_id`),
  CONSTRAINT `fk_comment_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,4,NULL,'Test','2023-08-23 00:00:00'),(2,1,2,NULL,'Hello','2023-08-22 00:00:00'),(7,1,4,NULL,'Bye','2023-08-23 00:00:00'),(8,1,2,NULL,'Xịn','2023-08-20 00:00:00'),(9,1,5,NULL,'Ghê','2023-07-21 00:00:00'),(10,1,4,NULL,'aaaaaaaaaaaaa',NULL),(11,1,4,NULL,'Chán thặc đếy','2023-08-24 00:02:40'),(13,NULL,4,NULL,NULL,'2023-08-24 14:24:21'),(14,NULL,4,NULL,NULL,'2023-08-24 14:24:28'),(15,1,4,NULL,'aaaaaaaaaaaa','2023-08-24 14:25:02'),(16,1,4,NULL,'ủa','2023-08-24 14:26:01'),(17,1,4,NULL,'Yeah, toẹt zời, xong cmt','2023-08-24 14:27:53'),(18,1,4,NULL,'aaaaaaaaa','2023-08-25 15:43:24'),(19,1,4,NULL,'xong','2023-08-30 11:35:54'),(20,1,4,NULL,'tuyệt với','2023-08-30 11:38:27'),(21,1,5,NULL,'5 sao','2023-08-30 11:43:03'),(22,NULL,5,1,'Bình luận đầu tiên','2023-08-30 14:25:06'),(23,2,5,NULL,'Quao, đẹp ghê','2023-08-30 14:25:57'),(24,NULL,5,2,'Đắt dữ v','2023-08-30 14:29:23'),(25,NULL,4,2,'Đắt thiệt ','2023-08-30 14:31:27'),(26,NULL,4,2,'Có cái nào rẻ hơn khum\n','2023-08-30 14:31:44'),(27,NULL,5,2,'Có tô dát bạc 1 triệu á\n','2023-08-30 14:32:30'),(28,NULL,5,2,'Hôm bữa tui mới ăn xong','2023-08-30 14:32:40'),(29,NULL,5,2,'Ngon','2023-08-30 14:32:44'),(30,NULL,5,9,'Đúng đúng đúng, nên bảo tồn\n','2023-08-30 14:43:24'),(31,3,5,NULL,'Thác gì mà nhiều nước, chê','2023-08-30 14:54:58'),(32,1,5,NULL,'Đẹp z','2023-08-31 23:54:15'),(33,25,4,NULL,'Dễ thương v shao','2023-09-01 01:03:59'),(34,1,5,NULL,'Hyhy','2023-09-01 13:53:46'),(35,32,5,NULL,'Hello','2023-09-02 23:47:21'),(36,47,5,NULL,'Đẹp quá','2023-09-02 23:48:58');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination` (
  `destination_id` int NOT NULL AUTO_INCREMENT,
  `destination_city` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `destination_country` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `destination_description` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `destination_imageurl` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `destination_averagerating` float DEFAULT NULL,
  `destination_tournumber` int DEFAULT NULL,
  PRIMARY KEY (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES (1,'Nha Trang','Việt Nam','Nha Trang là một thành phố ven biển và là trung tâm chính trị, kinh tế, văn hóa, khoa học kỹ thuật và du lịch của tỉnh Khánh Hòa, Việt Nam',NULL,NULL,NULL),(2,'Đà Lạt','Việt Nam','Đà Lạt là thành phố tỉnh lỵ trực thuộc tỉnh Lâm Đồng, nằm trên cao nguyên Lâm Viên, thuộc vùng Tây Nguyên, Việt Nam.',NULL,NULL,NULL),(3,'Hà Nội','Việt Nam','Hà Nội, thủ đô của Việt Nam, nổi tiếng với kiến trúc trăm tuổi và nền văn hóa phong phú với sự ảnh hưởng của khu vực Đông Nam Á, Trung Quốc và Pháp.',NULL,NULL,NULL),(4,'TP Hồ Chí Minh','Việt Nam','Thành phố Hồ Chí Minh (thường được gọi là Sài Gòn) là một thành phố ở miền nam Việt Nam nổi tiếng với vai trò nòng cốt trong chiến tranh Việt Nam.',NULL,NULL,NULL),(7,'Huế','Việt Nam','Huế là thành phố tỉnh lỵ của tỉnh Thừa Thiên Huế, Việt Nam. Huế là một trong những trung tâm về văn hóa – du lịch, y tế chuyên sâu, giáo dục đào tạo, khoa học công nghệ của Miền Trung - Tây Nguyên và cả nước.',NULL,NULL,NULL),(10,'Phú Quốc','Việt Nam','Phú Quốc là một thành phố đảo trực thuộc tỉnh Kiên Giang, Việt Nam. Đảo Phú Quốc cùng các đảo nhỏ lân cận và quần đảo Thổ Chu hợp lại tạo thành Thành phố Phú Quốc ở vịnh Thái Lan, đây là thành phố đảo đầu tiên được thành lập của Việt Nam.',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int DEFAULT NULL,
  `employee_firstname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `employee_lastname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `employee_email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `fk_tour_employee_idx` (`tour_id`),
  CONSTRAINT `fk_tour_employee` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,30,'Thanh','Hải','hairach@gmail.com'),(2,2,'Bình ','Phong','phongwibu@gmail.com'),(3,25,'Thanh','Hy','hykazuha@gmail.com'),(4,32,'Phi','Long','long@gmail.com'),(5,25,'Meow','Meow','meomeo@gmail.com');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int DEFAULT NULL,
  `image_url` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`image_id`),
  KEY `fk_image_tour_idx` (`tour_id`),
  CONSTRAINT `fk_image_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692518990/e0kfivmaww4xwhrtg1kh.jpg'),(2,1,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692518992/zzpuymnr0o7ufly6vhsb.jpg'),(3,2,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692519233/ds78phmrtaoyxuyij3dh.jpg'),(4,2,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692519235/uykcpxeiyulrgrngs0oj.jpg'),(5,2,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692519237/qjiuw6znyctwvmxmwe2v.jpg'),(6,1,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692519279/v4mvljkwhbh5hzoqnrs5.jpg'),(7,1,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692519577/lcvfru685lxfrtjaa7af.jpg'),(8,1,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692519587/bhfbb5gesltb7dyj551h.jpg'),(9,3,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692520070/b8zomtwjlvxih97jdgqd.jpg'),(10,3,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692520072/ntn8ep3mss7rnrt8xhhr.jpg'),(11,3,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692520074/iw0gjigy0pybkkurrpvw.jpg'),(12,3,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692520076/mjc380stotsexdcn8o63.jpg'),(13,3,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692520077/gsozokjwesjatusf9wkx.jpg'),(14,4,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692589307/my5j0enncjr8lfmtaupx.jpg'),(15,4,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692589309/zap2r7zzgbksjqa005e1.jpg'),(16,4,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692589311/kfx6vmqvulw6ctzsoqv0.jpg'),(17,5,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692590113/jnti12hljecmcqnumjg8.jpg'),(18,5,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692590115/f11gpaomksmzvok1rfbv.jpg'),(19,5,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692590117/rrqp8ocvcgv8v9ezumyv.jpg'),(20,6,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692593296/khhf2xzsexawmcerlzf2.jpg'),(21,6,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692593299/q1tbkw4483ohqbsohs9d.jpg'),(22,6,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692593301/mdvrvz1zbpstizrxvhla.jpg'),(23,6,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692593303/davitidcszmxhfy46jih.jpg'),(24,7,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692594312/ewcx6vrpptldpttimmdr.jpg'),(25,7,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692594314/r1bpsrbdqppzxkabh1me.jpg'),(26,7,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692594316/vdh9myjuoyugnasmd2jr.jpg'),(27,7,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692594318/qmfz7ognwdd2bdruxy3o.jpg'),(28,7,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692594319/htppfo5sq1d6vqomtmjf.jpg'),(29,8,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612187/er27mf6kpyggde5g4mep.jpg'),(30,8,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612189/yr8lakxniujor3vhuv4p.jpg'),(31,8,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612191/wac1wj6fohsmfmmu9qho.jpg'),(32,8,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612193/s09zic3tcs83muqri5bs.jpg'),(33,23,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612213/kzzkaueua5hhlu8ed5bl.jpg'),(34,23,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612215/ofac5ceztuuaqatxlzwi.jpg'),(35,23,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612217/blsd2khtu6wazmau8z6a.jpg'),(36,23,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612219/jsy70h66qkjhbjz2fcx7.jpg'),(37,23,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612221/qcokryk7yv9don7ldtgr.jpg'),(38,24,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612241/pi0kbdgkmrmqyxyyvct6.jpg'),(39,24,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612243/pui9jwnl1s4tefsrbjmh.jpg'),(40,24,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612244/xshm6k2thah5m1amkx2o.jpg'),(41,24,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612247/pmduuyjo2gipsqt1w4dh.jpg'),(42,24,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612249/jatrem2o70awvg5tnpkq.jpg'),(43,25,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612275/xfuwynfiiimaedrjcu23.jpg'),(44,25,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612277/l3zwqjhmmgh9u63umykb.jpg'),(45,25,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612279/tfs90cblzbgn9hvg4jsa.jpg'),(46,26,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612322/v3le9rgsjpvaifijbdyi.jpg'),(47,26,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612324/qh0cwdff2ecccmxlmvo5.jpg'),(48,26,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612326/s3knxsxbvj1uytzj6wpa.jpg'),(49,26,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612327/skuycajfuau9mmtrgjlt.jpg'),(50,26,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612329/wsvpv3bxoxjxtujqox9b.jpg'),(51,26,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612351/zjggwdqjdhpavpzaaxwt.jpg'),(52,27,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612380/ytrjwcusaeeljcujdld4.jpg'),(53,27,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612382/mbno1sd2oq2lrfpokxoz.jpg'),(54,27,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612384/xzzyjuoy05gjmryytaqf.jpg'),(55,27,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612387/r0dw35lrunxhsosinq5f.jpg'),(56,28,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612415/zhhwvqzqpqgzmywrgg6y.jpg'),(57,28,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612417/lvesimhfz2gyqbhllqyo.jpg'),(58,28,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612419/uxnlvxntlyahoqpm2txk.jpg'),(59,28,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612421/ecqvkzycpukskpddeqpt.jpg'),(60,28,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612442/esk4l8qarwiuodro6nsx.jpg'),(61,29,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612463/brotl20o7egmfavvpzne.jpg'),(62,29,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612465/friggkwxzylu8rangvot.jpg'),(63,29,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612467/joyofvwa1aprdhu7xt94.jpg'),(64,29,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612469/nggvvvqrujhkmhhtloxo.jpg'),(65,29,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612509/xeg1rylhi90ssorcndcl.jpg'),(66,30,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612532/uizmbgwymp0qqmerqfus.jpg'),(67,30,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612534/oxaljqb50n4gi0dvva6w.jpg'),(68,30,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612535/x1l59mtlmz7xfhuar1jq.jpg'),(69,30,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612538/nfgtaxcubiyogktaez8m.jpg'),(70,30,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612553/x0rwpex7kgyrcn1g8j8t.jpg'),(71,31,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612601/opcqmchnacqlsyk2qhhv.jpg'),(72,31,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612603/i7cbancfpjkuh31dabi5.jpg'),(73,31,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612605/regi8mie3xpihpvygtwo.jpg'),(74,31,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612673/xyz2fj1xs5u7sfwdlh53.jpg'),(75,32,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612695/tbvcmtzc6p5mvifjw2jw.jpg'),(76,32,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612697/pnyn4r6cmbqqkyf3qi4b.jpg'),(77,32,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612760/js1ac5dpev3ppzt1qcrx.jpg'),(78,33,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612780/m19udhifkfmci7iwsal7.jpg'),(79,33,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612782/fllwlclzvgmnehxnksmh.jpg'),(80,34,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612807/mwnignt6txkvqo9rv2zb.jpg'),(81,34,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612809/ndsib9hsjq4ugz6lgcdh.jpg'),(82,34,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612811/st9xa5cl7cksyy3tlte3.jpg'),(83,34,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692612813/zr6kn6trnjseq83vdc5w.jpg'),(84,34,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613024/tqyekl4tuwdiddgv4cmw.jpg'),(85,35,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613049/cg5wsxjtvxygx2n2eaes.jpg'),(86,35,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613052/s5hxp2lczgzsk6joujyj.jpg'),(87,35,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613054/neu1qmkdfxouc8f4vmhs.jpg'),(88,35,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613056/py0ywdvmmtw7sjf1nndo.jpg'),(89,35,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613370/exg86qccnqjtfzlvnxho.jpg'),(90,36,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613391/cpy1p45sjeqbifi3wlan.jpg'),(91,36,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613393/nejxd3tpsbbl9m669oie.jpg'),(92,37,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613478/ows8btcrzzdsxozdupug.jpg'),(93,37,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613480/bymoqcwaqc4pd20vhmr6.jpg'),(94,37,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613482/jm9qqjykscjvxqhmup05.jpg'),(95,37,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613484/dacdg6hsopmuvvwjzt1p.jpg'),(96,37,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613486/rp7909zfkn8qt9ettsrf.jpg'),(97,36,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613501/slyj4tbgatpqbwjix8tx.jpg'),(98,37,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613518/ngrthdc8ymrsyee3z5x0.jpg'),(99,38,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613553/sk4yw8lskoagbjfgys3y.jpg'),(100,38,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613555/eholsfwoqobi7mn3ixu4.jpg'),(101,38,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613557/bnhvi2pbcmthqnj8h2mp.jpg'),(102,38,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613598/vqogtpycfao2tbpihcih.jpg'),(103,39,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613689/v4qul8jtctis3szdxhsw.jpg'),(104,39,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613692/gbqh1yhvbs2gwtwsedv6.jpg'),(105,39,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613696/gbtlch1xatrinqbcadzj.jpg'),(106,39,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613701/eh2dy01qbswbgvzre6et.jpg'),(107,40,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613724/fdxmvri6kw6eowpdstdy.jpg'),(108,40,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613726/t1mblmm7jp0wlqdyerqd.jpg'),(109,40,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613729/rtsxxcep0p3xwhu9erod.jpg'),(110,40,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613731/nhdxlcxi93xmxf73axca.jpg'),(111,41,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613767/xxlscaoawod3uvraycel.jpg'),(112,41,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613769/mpkseahtseynknssacbz.jpg'),(113,41,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613771/zlrdrximnbwtdlbhtkdw.jpg'),(114,41,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692613773/qckxq6xgvhiecjwnldv5.jpg'),(115,42,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614629/vjlbjs8gaehxnbbl7eh6.jpg'),(116,42,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614632/o7mlrtdzgle7ezetqe9y.jpg'),(117,42,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614636/quyharmheq8jyfaoghan.jpg'),(118,42,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614639/xklco6pwjjchulwwp4gf.jpg'),(119,43,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614719/n0nxwpvsd0xno9u8bstp.jpg'),(120,43,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614721/hk14rjm21btpelankbu7.jpg'),(121,43,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614723/q29hpjsxkqmyacmzkbsa.jpg'),(122,44,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614744/oxzkhs3sqyuq89tgzdma.jpg'),(123,44,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614746/ckduijjesb7g6wwzatp5.jpg'),(124,44,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614748/km5fsq89au7hoeqva6cc.jpg'),(125,44,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614750/c9jftoadwivpoxfe7rza.jpg'),(126,44,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614752/okeus4jq6ytnlj74rhgs.jpg'),(127,45,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614837/mb6hms5lbee0w3pmpll9.jpg'),(128,45,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614839/gx9pu3m3d6mxtf4oml8t.jpg'),(129,45,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614841/qivhgyu5s7k1sbhccqjw.jpg'),(130,45,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614843/nxdcf7als3kojzltqxel.jpg'),(131,46,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614912/mm8jf2mbflzyqzfwsajy.jpg'),(132,46,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614914/t7dxibltocyi3xt2qfed.jpg'),(133,46,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614916/rvmhpmloe71brkcgjukg.jpg'),(134,47,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614951/ooixnkwibpf76ceo8hd8.jpg'),(135,47,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614953/zcjhsno1or2rgrggsxkv.jpg'),(136,47,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614955/ji100akcaeq3nq1pe8ap.jpg'),(137,47,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614956/inazhfhhfdkfmz2cgnhx.jpg'),(138,47,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614958/g278pprxbyeouhl5y4py.jpg'),(139,48,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614984/bd5u6gd9n9sxtmzpfwex.jpg'),(140,48,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614986/bkux7tiokxt6jhkp0epc.jpg'),(141,48,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614988/emwjceopmvtzlkgqewht.jpg'),(142,48,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692614990/phsbnznnwdybbetcyv39.jpg'),(143,49,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615026/msacucojye0rxlpb91eb.jpg'),(144,49,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615027/o04od6qswxndcatxsci1.jpg'),(145,49,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615030/dlsufvoy3llf4sn5uvor.jpg'),(146,49,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615032/avam0zwp8shepcxanbo1.jpg'),(147,54,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615054/modsmpkhy7b7gsrteuzp.jpg'),(148,54,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615055/kq7ws8n5d2wzietop1ge.jpg'),(149,57,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615086/nhmliyjf1lk6mmwoogue.jpg'),(150,57,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615088/fbheyukxgv0iiqyvwjzj.jpg'),(151,54,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615130/gzs9ag4yhhmzepd6d3az.jpg'),(152,57,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692615143/s4zhq8pzzrqpuodz56ni.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `news_id` int NOT NULL AUTO_INCREMENT,
  `news_title` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `news_content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `news_postdate` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `news_imageurl` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'48 giờ ở Hà Nội','Lịch trình dành cho những người lần đầu đến Hà Nội và muốn trải nghiệm những nét đặc trưng nhất của thủ đô.  ',NULL,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692356797/gpxqlee09ivj7yzzblzv.jpg'),(2,'Tô phở dát vàng 4 triệu đồng trong tòa nhà cao nhất TP HCM','Nhà hàng trong tòa nhà chọc trời ở TP HCM đang phục vụ món phở giá 4 triệu đồng với nguyên liệu đắt đỏ, như vàng lá, bò wagyu, nấm trufle.',NULL,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692357181/fggz5eiyxsxpwcoji3xc.jpg'),(9,'Người làm du lịch kêu gọi không để hòn Trống Mái đổ sập','Các chuyên gia du lịch cho rằng hòn Trống Mái ở vịnh Hạ Long, Quảng Ninh, là điểm đến mang tính biểu tượng nên bắt buộc phải bảo tồn, gia cố sớm.',NULL,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692357250/lqfwanc63cb9wluvmt5y.jpg'),(10,'Hội An vào top 9 nơi tuyệt nhất thế giới vì \'cái gì cũng có\'','Hội An, thành phố thuộc tỉnh Quảng Nam với dân số 120.000 người, được các chuyên gia du lịch đánh giá là một điểm đến \"ăn ảnh\". Nơi đây gây ấn tượng với du khách quốc tế nhờ lối kiến trúc thời Pháp thuộc, những ngôi đền trang trí công phu, chùa Cầu Nhật Bản.',NULL,'https://res.cloudinary.com/dvgpizkep/image/upload/v1692357314/j8rolgg6au8zlhzywlrz.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `payment_createdate` datetime DEFAULT NULL,
  `payment_amount` float DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk_payment_user_idx` (`user_id`),
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,5,'2023-08-31 22:44:09',NULL),(2,5,'2023-08-31 23:54:25',NULL),(3,5,'2023-01-10 00:02:50',NULL),(4,5,'2023-09-01 00:45:13',NULL),(5,5,'2023-06-01 00:48:58',NULL),(6,5,'2023-09-01 00:49:46',NULL),(7,4,'2023-03-01 00:53:26',NULL),(8,4,'2023-04-01 01:00:40',NULL),(9,4,'2023-10-01 01:01:14',NULL),(10,4,'2023-09-01 01:04:06',NULL),(11,5,'2023-12-01 01:35:17',NULL),(12,5,'2023-11-01 01:38:44',NULL),(13,5,'2022-08-01 13:53:53',NULL),(14,14,'2022-07-01 20:20:54',NULL),(15,14,'2023-09-01 20:22:39',NULL),(16,5,'2023-02-01 20:23:30',NULL),(17,5,'2023-09-02 23:47:58',NULL),(18,5,'2023-07-02 23:49:57',NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour` (
  `tour_id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int DEFAULT NULL,
  `tour_title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `tour_adultprice` float NOT NULL DEFAULT '0',
  `tour_childprice` float NOT NULL DEFAULT '0',
  `tour_startdate` date DEFAULT NULL,
  `tour_enddate` date DEFAULT NULL,
  `tour_duration` time DEFAULT NULL,
  `tour_maximumpeople` int DEFAULT NULL,
  `tour_description` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`tour_id`),
  KEY `fk_tour_package_destination_idx` (`destination_id`),
  CONSTRAINT `fk_tour_destination` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (1,1,'Tham quan Hòn Tằm và tắm bùn tại resort',500000,300000,'2023-08-02','2023-08-05',NULL,NULL,NULL),(2,1,'Trải nghiệm Spa có tắm bùn',300000,100000,'2023-07-02','2023-07-06',NULL,NULL,NULL),(3,1,'Tour thác Ba Hồ có hướng dẫn',1000000,700000,'2023-05-10','2023-05-20',NULL,NULL,NULL),(4,1,'Tour tham quan Hòn Mun, Làng Chài và Bãi Tranh',600000,400000,'2023-01-02','2023-01-10',NULL,NULL,NULL),(5,1,'Tour thác Ba Hồ có hướng dẫn',1000000,500000,'2022-10-02','2022-10-10',NULL,NULL,NULL),(6,1,'Tour tham quan nửa Ngày',1200000,600000,'2022-05-10','2022-05-15',NULL,NULL,NULL),(7,1,'Du thuyền hoàng hôn với bữa tối',3000000,500000,'2022-01-10','2022-01-15',NULL,NULL,NULL),(8,1,'Tour trekking đi thác với bữa trưa',3500000,1000000,'2023-05-20','2023-05-25',NULL,NULL,NULL),(23,2,'Vé vào cửa Fairytale Land và Hầm rượu Vĩnh Tiến',100000,50000,'2023-03-15','2023-03-16',NULL,NULL,NULL),(24,2,'Tour Đà Lạt trong ngày có hướng dẫn viên',400000,200000,'2023-02-10','2023-02-12',NULL,NULL,NULL),(25,2,'Trải nghiệm nông trại cún',100000,50000,'2022-03-12','2022-03-14',NULL,NULL,NULL),(26,3,'Tour Vịnh Hạ Long cả ngày có kèm bữa trưa, đi từ Hà Nội',1400000,700000,'2022-07-02','2022-08-02',NULL,NULL,NULL),(27,3,'Tour đi bộ tham quan thành phố có hướng dẫn viên',100000,50000,'2023-04-08','2022-04-10',NULL,NULL,NULL),(28,3,'Tour ẩm thực đường phố có hướng dẫn',110000,70000,'2022-09-09','2022-09-12',NULL,NULL,NULL),(29,3,'Đi thuyền trên vịnh Hạ Long cả ngày',800000,500000,'2021-07-02','2021-07-10',NULL,NULL,NULL),(30,3,'Tour tham quan Ninh Bình cả ngày',1100000,700000,'2021-06-05','2021-06-10',NULL,NULL,NULL),(31,3,'Tour tham quan Hà Nội',900000,500000,'2021-09-02','2021-09-04',NULL,NULL,NULL),(32,3,'Tour cánh đồng lúa Tam Cốc & hang Múa ở Ninh Bình',1000000,700000,'2021-03-12','2021-03-20',NULL,NULL,NULL),(33,3,'Tour xe máy tham quan thành phố có hướng dẫn',1400000,500000,'2021-02-10','2021-02-15',NULL,NULL,NULL),(34,3,'Thẻ xe buýt tham quan hop-on, hop-off',400000,200000,'2022-12-12','2022-12-20',NULL,NULL,NULL),(35,3,'Lớp học nấu ăn và tour tham quan chợ',800000,500000,'2022-11-01','2022-11-02',NULL,NULL,NULL),(36,3,'Thuê áo dài và trải nghiệm tại Hà Nội trong một ngày',220000,100000,'2023-08-05','2023-08-10',NULL,NULL,NULL),(37,3,'Thẻ xe buýt tham quan hop-on, hop-off',400000,200000,'2023-08-13','2023-08-15',NULL,NULL,NULL),(38,3,'Tour đi bộ khám phá ẩm thực đường phố và xem múa rối trong nửa ngày',110000,60000,'2023-08-16','2023-08-16',NULL,NULL,NULL),(39,3,'Tour tham quan thành phố',1000000,500000,'2023-08-20','2023-08-20',NULL,NULL,NULL),(40,3,'Tour Bái Đính, Tràng An và Hang Múa cả ngày',1300000,400000,'2023-08-10','2023-08-10',NULL,NULL,NULL),(41,3,'Tour ẩm thực phố cổ',400000,200000,'2023-08-19','2023-08-23',NULL,NULL,NULL),(42,3,'Du ngoạn Tràng An',1200000,600000,'2023-08-21','2023-08-23',NULL,NULL,NULL),(43,3,'Du thuyền trên Vịnh Hạ Long',1500000,500000,'2023-08-20','2023-08-25',NULL,NULL,NULL),(44,3,'Tour chùa Bái Đính và Tràng An có bữa trưa',1300000,200000,'2023-08-15','2023-08-20',NULL,NULL,NULL),(45,3,'Tour tham quan Ninh Bình bằng thuyền',1800000,1400000,'2023-08-25','2023-08-26',NULL,NULL,NULL),(46,3,'Tour khám phá ẩm thực đường phố Hà Nội',700000,400000,'2023-08-13','2023-08-15',NULL,NULL,NULL),(47,3,'Tour tham quan đền ở Hoa Lư và Tam Cốc',700000,400000,'2023-08-05','2023-08-10',NULL,NULL,NULL),(48,3,'Tour cả ngày tham quan chùa Bái Đính và Tràng An',600000,400000,'2023-08-15','2023-08-15',NULL,NULL,NULL),(49,3,'Tour tham quan vịnh Hạ Long cả ngày',550000,300000,'2023-08-20','2023-08-25',NULL,NULL,NULL),(54,1,'Tour Thác Ba Hồ khởi hành từ Nha Trang',4000000,200000,'2023-08-02','2023-08-05',NULL,NULL,NULL),(57,7,'Tour tham quan các địa danh ở Huế',800000,400000,'2023-08-20','2023-08-25',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `user_password` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `user_email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `user_dob` date DEFAULT NULL,
  `user_phone` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `user_imageurl` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `user_role` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_username_UNIQUE` (`user_username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$zf5xcWO73U1raazpR1ry0.NZJKc6UVWTIyhczSrNsJF0THuS8QDJW','admin@gmail.com',NULL,'1234567890','','ROLE_ADMIN'),(2,'long001','$2a$10$vEBaZT4xJ3zUCgc6BbBd4.7rHcVKSy8IrG0lNqTPZC8ubyx4zIV9e','long001@gmail.com',NULL,'123456789','','ROLE_USER'),(4,'meo','$2a$10$GVQjzSLtLlsWJJM0g5pAhewaM2XRfJbd1ZCGNf51CLsNwYCPJ7uzK','meo@gmail.com',NULL,'1234567892','https://res.cloudinary.com/dvgpizkep/image/upload/v1691671628/fbsqz4xnhzr2ttc2awnq.jpg','ROLE_USER'),(5,'lephilong1907','$2a$10$EcjurM9PF.yHGX/igHLdAe8kBB5CEluRu6JbCzbuenYhyQZo5Ag8O','lephilong1907@gmail.com',NULL,'1234566781','https://res.cloudinary.com/dvgpizkep/image/upload/v1692008357/g5cqjdzyxqqn7tluanu4.jpg','ROLE_USER'),(6,'long002','$2a$10$bV3UkclVTSAYtxMcDogF7OTTRbNxyIY/9cmjtj0kb3vhGyW713fqG','long002@gmail.com',NULL,'1212121','https://res.cloudinary.com/dvgpizkep/image/upload/v1692433921/nui0sjna6kvedwxddblv.jpg','ROLE_USER'),(7,'nhi','$2a$10$673QZaKpFMItm1/mGmR5Veqy2rBoM.MNjVRnDmzvaMKsf.AvdxAFi','nhi@gmail.com',NULL,'123456789','https://res.cloudinary.com/dvgpizkep/image/upload/v1692434196/jtrg5ndr2yhapwdoruai.jpg','ROLE_USER'),(8,'meo1','$2a$10$g1y34xeD2pwMhbcYlsva0efnvcHBc/4bV22T03uP2aQH2Qcla5hmO','meo1@gmail.com',NULL,'1234564321','https://res.cloudinary.com/dvgpizkep/image/upload/v1692642395/mtv7ibmf06hrvidd6akm.jpg','ROLE_USER'),(9,'meo2','$2a$10$dPYtEOlRFFM1s28n872cxOblP1/smEXp6pWUTO4xwkGYOhV.23KZm','meo2@gmail.com',NULL,'1233211231','https://res.cloudinary.com/dvgpizkep/image/upload/v1692642494/acnos88svpedfmnfyazs.jpg','ROLE_USER'),(10,'nhanvien001','$2a$10$TdRtn.xrHpn3ZYogR/MADOq9pIMQ8yYRubraXeNVJ/x6wQ.u8hxXS','nhanvien001@gmail.com',NULL,'1232434231','https://res.cloudinary.com/dvgpizkep/image/upload/v1692643875/svxi4ejcb8jetututcbt.jpg','ROLE_EMPLOYEE'),(12,'meo999','$2a$10$9O9o5A3cf7GMhkosNVNGE.ZJ1WNdK2pcIFNqn3P19x/ikHZ2KtzZq','meo999@gmail.com',NULL,'123456321','https://res.cloudinary.com/dvgpizkep/image/upload/v1692812390/a8hwo2xfjwlekqcdpsi6.jpg','ROLE_USER'),(13,'meo001','$2a$10$LSKdSoQR3ZzZGXJj/NjSuurwczSls6BD/Ruqa8VYhNoFfiZLngFJS','meo001@gmail.com',NULL,'123456775','https://res.cloudinary.com/dvgpizkep/image/upload/v1693050718/givz6f4qac6qy8ncud6u.jpg','ROLE_USER'),(14,'nhi123','$2a$10$kCFcsnkUbHLpyuuYuw8Kwu5RpFMMaFlHlb5JHRJyFE2sMSfophtm6','nhi@gmail.com',NULL,'123321455','https://res.cloudinary.com/dvgpizkep/image/upload/v1693313929/bx5waxepmgs2hcdqpior.jpg','ROLE_USER');
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

-- Dump completed on 2023-09-03 11:13:08
