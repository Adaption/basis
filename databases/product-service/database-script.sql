-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: product_service
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `categoryName_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Gaming Mouse','Đây là con chuột chuyên game.'),(2,'Gaming Keyboard','Là bàn phím chơi game á chời!');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT 'New Product',
  `price` float NOT NULL DEFAULT '0',
  `discount` float NOT NULL DEFAULT '0',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` datetime DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  `enabled` int(1) NOT NULL DEFAULT '1',
  `description` varchar(1000) DEFAULT 'N/A',
  `attributes` json DEFAULT NULL,
  `images` json DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Razer Lancehead',3590000,0.17,'2018-05-02 01:28:10',NULL,1,1,'Chuột không dây, đẹp nhưng mắc vãi đái.','{\"Đèn LED\": \"RGB\", \"Bảo hành\": \"24 tháng\", \"Tình trạng\": \"Mới 100% - Full box\", \"Nhà sản xuất\": \"Razer\"}','[\"http://product.hstatic.net/1000026716/product/lancew.jpg\", \"http://product.hstatic.net/1000026716/product/gearvn-razer-lancehead-wireless-3.jpg\", \"http://product.hstatic.net/1000026716/product/gearvn-razer-lancehead-wireless-1.jpg\", \"http://product.hstatic.net/1000026716/product/rzr_lancehead_dongle_v01-14x9inch.png\"]',1,1),(2,'Logitech G903',3990000,0,'2018-05-03 20:29:44',NULL,1,1,'Con chuột mắc nhứt của hãng Logitech.','{\"Đèn LED\": \"16,8 triệu màu\", \"Bảo hành\": \"24 tháng (1 đổi 1)\", \"Tình trạng\": \"Mới 100% - Full box\", \"Nhà sản xuất\": \"Logitech\"}','[\"http://product.hstatic.net/1000026716/product/gearvn_g903_w_7a90e8a2113a45b09b80d390ae6a903f.jpg\", \"http://product.hstatic.net/1000026716/product/gearvn-g903-2.jpg\", \"http://product.hstatic.net/1000026716/product/gearvn-g903-3.jpg\", \"http://product.hstatic.net/1000026716/product/gearvn-g903-4.jpg\", \"http://product.hstatic.net/1000026716/product/gearvn-g903-5.jpg\"]',1,1),(3,'Razer Blackwidow X Chroma',3890000,0,'2018-05-04 10:58:08',NULL,1,1,'Là cái bàn phím cơ có đèn LED đủ màu, switch Razer xanh lá.','{\"Đèn LED\": \"16,8 triệu màu\", \"Bảo hành\": \"24 tháng (1 đổi 1)\", \"Tình trạng\": \"Mới 100%\", \"Nhà sản xuất\": \"Razer\"}','[\"http://hstatic.net/716/1000026716/1/2016/8-30/ra2.png\", \"http://hstatic.net/716/1000026716/1/2016/4-11/razer-blackwidow-x-chroma-2.png\", \"http://hstatic.net/716/1000026716/1/2016/4-11/razer-blackwidow-x-chroma-4.png\"]',2,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `website_product_attribute`
--

DROP TABLE IF EXISTS `website_product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `website_product_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `attribute_name_UNIQUE` (`attribute_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `website_product_attribute`
--

LOCK TABLES `website_product_attribute` WRITE;
/*!40000 ALTER TABLE `website_product_attribute` DISABLE KEYS */;
INSERT INTO `website_product_attribute` VALUES (4,'Bảo hành'),(1,'Nhà sản xuất'),(2,'Tình trạng'),(3,'Đèn LED');
/*!40000 ALTER TABLE `website_product_attribute` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-07  5:53:14
