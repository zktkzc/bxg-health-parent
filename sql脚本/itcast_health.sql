-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: itcast_health
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_checkgroup`
--

DROP TABLE IF EXISTS `t_checkgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_checkgroup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `helpCode` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_checkgroup`
--

LOCK TABLES `t_checkgroup` WRITE;
/*!40000 ALTER TABLE `t_checkgroup` DISABLE KEYS */;
INSERT INTO `t_checkgroup` VALUES (5,'0001','一般检查1','YBJC','0','一般检查','无'),(6,'0002','视力色觉','SLSJ','0','视力色觉',NULL),(7,'0003','血常规','XCG','0','血常规',NULL),(8,'0004','尿常规','NCG','0','尿常规',NULL),(9,'0005','肝功三项','GGSX','0','肝功三项',NULL),(10,'0006','肾功三项','NGSX','0','肾功三项',NULL),(11,'0007','血脂四项','XZSX','0','血脂四项',NULL),(12,'0008','心肌酶三项','XJMSX','0','心肌酶三项',NULL),(13,'0009','甲功三项','JGSX','0','甲功三项',NULL),(14,'0010','子宫附件彩超','ZGFJCC','2','子宫附件彩超',NULL),(15,'0011','胆红素三项','DHSSX','0','胆红素三项',NULL);
/*!40000 ALTER TABLE `t_checkgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_checkgroup_checkitem`
--

DROP TABLE IF EXISTS `t_checkgroup_checkitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` int NOT NULL DEFAULT '0',
  `checkitem_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`checkgroup_id`,`checkitem_id`),
  KEY `item_id` (`checkitem_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `item_id` FOREIGN KEY (`checkitem_id`) REFERENCES `t_checkitem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_checkgroup_checkitem`
--

LOCK TABLES `t_checkgroup_checkitem` WRITE;
/*!40000 ALTER TABLE `t_checkgroup_checkitem` DISABLE KEYS */;
INSERT INTO `t_checkgroup_checkitem` VALUES (5,28),(5,29),(5,30),(5,31),(5,32),(6,33),(6,34),(6,35),(6,36),(6,37),(7,38),(7,39),(7,40),(7,41),(7,42),(7,43),(7,44),(7,45),(7,46),(7,47),(7,48),(7,49),(7,50),(7,51),(7,52),(7,53),(7,54),(7,55),(7,56),(8,57),(8,58),(8,59),(8,60),(8,61),(8,62),(8,63),(8,64),(8,65),(8,66),(8,67),(8,68),(8,69),(8,70),(8,71),(9,72),(9,73),(9,74),(10,75),(10,76),(10,77),(11,78),(11,79),(11,80),(11,81),(12,82),(12,83),(12,84),(13,85),(13,86),(13,87),(14,88),(14,89),(15,91),(15,92);
/*!40000 ALTER TABLE `t_checkgroup_checkitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_checkitem`
--

DROP TABLE IF EXISTS `t_checkitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_checkitem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `type` char(1) DEFAULT NULL COMMENT '查检项类型,分为检查和检验两种',
  `attention` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_checkitem`
--

LOCK TABLES `t_checkitem` WRITE;
/*!40000 ALTER TABLE `t_checkitem` DISABLE KEYS */;
INSERT INTO `t_checkitem` VALUES (28,'0001','身高','0','0-100',5,'1','无','身高'),(29,'0002','体重','0','0-100',5,'1','无','体重'),(30,'0003','体重指数','0','0-100',5,'1','无','体重指数'),(31,'0004','收缩压','0','0-100',5,'1','无','收缩压'),(32,'0005','舒张压','0','0-100',5,'1','无','舒张压'),(33,'0006','裸视力（右）','0','0-100',5,'1','无','裸视力（右）'),(34,'0007','裸视力（左）','0','0-100',5,'1','无','裸视力（左）'),(35,'0008','矫正视力（右）','0','0-100',5,'1','无','矫正视力（右）'),(36,'0009','矫正视力（左）','0','0-100',5,'1','无','矫正视力（左）'),(37,'0010','色觉','0','0-100',5,'1','无','色觉'),(38,'0011','白细胞计数','0','0-100',10,'2','无','白细胞计数'),(39,'0012','红细胞计数','0','0-100',10,'2',NULL,'红细胞计数'),(40,'0013','血红蛋白','0','0-100',10,'2',NULL,'血红蛋白'),(41,'0014','红细胞压积','0','0-100',10,'2',NULL,'红细胞压积'),(42,'0015','平均红细胞体积','0','0-100',10,'2',NULL,'平均红细胞体积'),(43,'0016','平均红细胞血红蛋白含量','0','0-100',10,'2',NULL,'平均红细胞血红蛋白含量'),(44,'0017','平均红细胞血红蛋白浓度','0','0-100',10,'2',NULL,'平均红细胞血红蛋白浓度'),(45,'0018','红细胞分布宽度-变异系数','0','0-100',10,'2',NULL,'红细胞分布宽度-变异系数'),(46,'0019','血小板计数','0','0-100',10,'2',NULL,'血小板计数'),(47,'0020','平均血小板体积','0','0-100',10,'2',NULL,'平均血小板体积'),(48,'0021','血小板分布宽度','0','0-100',10,'2',NULL,'血小板分布宽度'),(49,'0022','淋巴细胞百分比','0','0-100',10,'2',NULL,'淋巴细胞百分比'),(50,'0023','中间细胞百分比','0','0-100',10,'2',NULL,'中间细胞百分比'),(51,'0024','中性粒细胞百分比','0','0-100',10,'2',NULL,'中性粒细胞百分比'),(52,'0025','淋巴细胞绝对值','0','0-100',10,'2',NULL,'淋巴细胞绝对值'),(53,'0026','中间细胞绝对值','0','0-100',10,'2',NULL,'中间细胞绝对值'),(54,'0027','中性粒细胞绝对值','0','0-100',10,'2',NULL,'中性粒细胞绝对值'),(55,'0028','红细胞分布宽度-标准差','0','0-100',10,'2',NULL,'红细胞分布宽度-标准差'),(56,'0029','血小板压积','0','0-100',10,'2',NULL,'血小板压积'),(57,'0030','尿比重','0','0-100',10,'2',NULL,'尿比重'),(58,'0031','尿酸碱度','0','0-100',10,'2',NULL,'尿酸碱度'),(59,'0032','尿白细胞','0','0-100',10,'2',NULL,'尿白细胞'),(60,'0033','尿亚硝酸盐','0','0-100',10,'2',NULL,'尿亚硝酸盐'),(61,'0034','尿蛋白质','0','0-100',10,'2',NULL,'尿蛋白质'),(62,'0035','尿糖','0','0-100',10,'2',NULL,'尿糖'),(63,'0036','尿酮体','0','0-100',10,'2',NULL,'尿酮体'),(64,'0037','尿胆原','0','0-100',10,'2',NULL,'尿胆原'),(65,'0038','尿胆红素','0','0-100',10,'2',NULL,'尿胆红素'),(66,'0039','尿隐血','0','0-100',10,'2',NULL,'尿隐血'),(67,'0040','尿镜检红细胞','0','0-100',10,'2',NULL,'尿镜检红细胞'),(68,'0041','尿镜检白细胞','0','0-100',10,'2',NULL,'尿镜检白细胞'),(69,'0042','上皮细胞','0','0-100',10,'2',NULL,'上皮细胞'),(70,'0043','无机盐类','0','0-100',10,'2',NULL,'无机盐类'),(71,'0044','尿镜检蛋白定性','0','0-100',10,'2',NULL,'尿镜检蛋白定性'),(72,'0045','丙氨酸氨基转移酶','0','0-100',10,'2','无','丙氨酸氨基转移酶'),(73,'0046','天门冬氨酸氨基转移酶','0','0-100',10,'2',NULL,'天门冬氨酸氨基转移酶'),(74,'0047','Y-谷氨酰转移酶','0','0-100',10,'2',NULL,'Y-谷氨酰转移酶'),(75,'0048','尿素','0','0-100',10,'2',NULL,'尿素'),(76,'0049','肌酐','0','0-100',10,'2',NULL,'肌酐'),(77,'0050','尿酸','0','0-100',10,'2',NULL,'尿酸'),(78,'0051','总胆固醇','0','0-100',10,'2',NULL,'总胆固醇'),(79,'0052','甘油三酯','0','0-100',10,'2',NULL,'甘油三酯'),(80,'0053','高密度脂蛋白胆固醇','0','0-100',10,'2',NULL,'高密度脂蛋白胆固醇'),(81,'0054','低密度脂蛋白胆固醇','0','0-100',10,'2',NULL,'低密度脂蛋白胆固醇'),(82,'0055','磷酸肌酸激酶','0','0-100',10,'2',NULL,'磷酸肌酸激酶'),(83,'0056','磷酸肌酸激酶同工酶','0','0-100',10,'2',NULL,'磷酸肌酸激酶同工酶'),(84,'0057','乳酸脱氢酶','0','0-100',10,'2',NULL,'乳酸脱氢酶'),(85,'0058','三碘甲状腺原氨酸','0','0-100',10,'2',NULL,'三碘甲状腺原氨酸'),(86,'0059','甲状腺素','0','0-100',10,'2',NULL,'甲状腺素'),(87,'0060','促甲状腺激素','0','0-100',10,'2',NULL,'促甲状腺激素'),(88,'0061','子宫','2','0-100',10,'2',NULL,'子宫'),(89,'0062','附件','2','0-100',10,'2',NULL,'附件'),(90,'0063','总胆红素','0','0-100',10,'2',NULL,'总胆红素'),(91,'0064','直接胆红素','0','0-100',10,'2',NULL,'直接胆红素'),(92,'0065','间接胆红素','0','0-100',10,'2',NULL,'间接胆红素');
/*!40000 ALTER TABLE `t_checkitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member`
--

DROP TABLE IF EXISTS `t_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fileNumber` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `regTime` date DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member`
--

LOCK TABLES `t_member` WRITE;
/*!40000 ALTER TABLE `t_member` DISABLE KEYS */;
INSERT INTO `t_member` VALUES (82,NULL,'小明','1','123456789000999999','18811679442','2019-03-08',NULL,NULL,NULL,NULL),(83,NULL,'王美丽','1','132333333333333','13412345678','2019-03-11',NULL,NULL,NULL,NULL),(85,NULL,NULL,NULL,NULL,NULL,'2019-03-06',NULL,NULL,NULL,NULL),(86,NULL,NULL,NULL,NULL,NULL,'2019-04-04',NULL,NULL,NULL,NULL),(87,NULL,NULL,NULL,NULL,NULL,'2019-02-06',NULL,NULL,NULL,NULL),(88,NULL,NULL,NULL,NULL,NULL,'2019-04-10',NULL,NULL,NULL,NULL),(89,NULL,NULL,NULL,NULL,NULL,'2018-12-01',NULL,NULL,NULL,NULL),(90,NULL,NULL,NULL,NULL,NULL,'2018-12-02',NULL,NULL,NULL,NULL),(91,NULL,NULL,NULL,NULL,NULL,'2018-02-01',NULL,NULL,NULL,NULL),(92,'2023123092','张三','1','150223193207273797','13812345678','2023-12-27',NULL,NULL,'1932-07-27',NULL),(93,'2023122993','李四','1','420202198602183552','13855586385','2023-12-28',NULL,NULL,'1986-02-18',NULL);
/*!40000 ALTER TABLE `t_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `linkUrl` varchar(128) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `priority` int DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `parentMenuId` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`parentMenuId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (1,'会员管理',NULL,'2',1,'fa-user-md',NULL,NULL,1),(2,'会员档案','member.html','/2-1',1,NULL,NULL,1,2),(3,'体检上传',NULL,'/2-2',2,NULL,NULL,1,2),(4,'会员统计',NULL,'/2-3',3,NULL,NULL,1,2),(5,'预约管理',NULL,'3',2,'fa-tty',NULL,NULL,1),(6,'预约列表','ordersettinglist.html','/3-1',1,NULL,NULL,5,2),(7,'预约设置','ordersetting.html','/3-2',2,NULL,NULL,5,2),(8,'套餐管理','setmeal.html','/3-3',3,NULL,NULL,5,2),(9,'检查组管理','checkgroup.html','/3-4',4,NULL,NULL,5,2),(10,'检查项管理','checkitem.html','/3-5',5,NULL,NULL,5,2),(11,'健康评估',NULL,'4',3,'fa-stethoscope',NULL,NULL,1),(12,'中医体质辨识',NULL,'/4-1',1,NULL,NULL,11,2),(13,'统计分析',NULL,'5',4,'fa-heartbeat',NULL,NULL,1),(14,'会员数量','report_member.html','/5-1',1,NULL,NULL,13,2),(15,'系统设置',NULL,'6',5,'fa-users',NULL,NULL,1),(16,'菜单管理','menu.html','/6-1',1,NULL,NULL,15,2),(17,'权限管理','permission.html','/6-2',2,NULL,NULL,15,2),(18,'角色管理','role.html','/6-3',3,NULL,NULL,15,2),(19,'用户管理','user.html','/6-4',4,NULL,NULL,15,2),(20,'套餐占比','report_setmeal.html','/5-2',2,NULL,NULL,13,2),(21,'运营数据','report_business.html','/5-3',3,NULL,NULL,13,2);
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL COMMENT '员会id',
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `orderType` varchar(8) DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
  `orderStatus` varchar(8) DEFAULT NULL COMMENT '预约状态（是否到诊）',
  `setmeal_id` int DEFAULT NULL COMMENT '餐套id',
  PRIMARY KEY (`id`),
  KEY `key_member_id` (`member_id`),
  KEY `key_setmeal_id` (`setmeal_id`),
  CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`),
  CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (2,82,'2019-03-08','微信预约','已到诊',6),(3,82,'2019-03-11','微信预约','已取消',11),(4,82,'2019-03-11','微信预约','已取消',7),(5,83,'2019-03-11','微信预约','已取消',7),(6,82,'2019-03-13','微信预约','未到诊',6),(7,82,'2019-03-15','微信预约','未到诊',6),(8,82,'2019-03-19','微信预约','未到诊',5),(9,84,'2019-03-20','微信预约','未到诊',6),(10,84,'2019-03-28','微信预约','未到诊',11),(12,82,'2023-07-31','微信预约','未到诊',5),(16,93,'2023-12-29','微信预约','未到诊',5),(17,92,'2023-12-30','微信预约','未到诊',5);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ordersetting`
--

DROP TABLE IF EXISTS `t_ordersetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_ordersetting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `number` int DEFAULT NULL COMMENT '可预约人数',
  `reservations` int DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ordersetting`
--

LOCK TABLES `t_ordersetting` WRITE;
/*!40000 ALTER TABLE `t_ordersetting` DISABLE KEYS */;
INSERT INTO `t_ordersetting` VALUES (13,'2019-03-04',100,100),(14,'2019-03-05',200,0),(15,'2019-03-06',300,0),(16,'2019-03-07',200,0),(17,'2019-03-08',200,1),(18,'2019-03-09',200,0),(19,'2019-03-10',200,0),(20,'2019-03-11',200,3),(21,'2019-03-13',300,1),(22,'2019-03-14',600,0),(23,'2019-03-15',500,1),(24,'2019-03-16',500,0),(25,'2019-03-17',400,0),(26,'2019-03-19',300,1),(27,'2019-04-01',300,0),(28,'2019-04-02',300,0),(29,'2019-04-19',300,0),(30,'2019-03-20',200,1),(31,'2019-05-01',400,0),(32,'2019-03-28',200,1),(33,'2019-04-03',400,0),(34,'2019-09-30',800,0),(35,'2019-04-04',400,0),(36,'2019-04-05',300,0),(55,'2023-12-29',200,1),(58,'2023-12-28',200,0),(59,'2023-12-30',100,0),(60,'2023-12-31',200,0),(61,'2023-12-30',0,1);
/*!40000 ALTER TABLE `t_ordersetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'新增检查项','CHECKITEM_ADD',NULL),(2,'删除检查项','CHECKITEM_DELETE',NULL),(3,'编辑检查项','CHECKITEM_EDIT',NULL),(4,'查询检查项','CHECKITEM_QUERY',NULL),(5,'新增检查组','CHECKGROUP_ADD',NULL),(6,'删除检查组','CHECKGROUP_DELETE',NULL),(7,'编辑检查组','CHECKGROUP_EDIT',NULL),(8,'查询检查组','CHECKGROUP_QUERY',NULL),(9,'新增套餐','SETMEAL_ADD',NULL),(10,'删除套餐','SETMEAL_DELETE',NULL),(11,'编辑套餐','SETMEAL_EDIT',NULL),(12,'查询套餐','SETMEAL_QUERY',NULL),(13,'预约设置','ORDERSETTING',NULL),(14,'查看统计报表','REPORT_VIEW',NULL),(15,'新增菜单','MENU_ADD',NULL),(16,'删除菜单','MENU_DELETE',NULL),(17,'编辑菜单','MENU_EDIT',NULL),(18,'查询菜单','MENU_QUERY',NULL),(19,'新增角色','ROLE_ADD',NULL),(20,'删除角色','ROLE_DELETE',NULL),(21,'编辑角色','ROLE_EDIT',NULL),(22,'查询角色','ROLE_QUERY',NULL),(23,'新增用户','USER_ADD',NULL),(24,'删除用户','USER_DELETE',NULL),(25,'编辑用户','USER_EDIT',NULL),(26,'查询用户','USER_QUERY',NULL);
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'系统管理员','ROLE_ADMIN',NULL),(2,'健康管理师','ROLE_HEALTH_MANAGER',NULL);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `t_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_menu` (
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_Reference_10` (`menu_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `t_role_menu` WRITE;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_Reference_12` (`permission_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(1,14),(2,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26);
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_setmeal`
--

DROP TABLE IF EXISTS `t_setmeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_setmeal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(8) DEFAULT NULL,
  `helpCode` varchar(16) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_setmeal`
--

LOCK TABLES `t_setmeal` WRITE;
/*!40000 ALTER TABLE `t_setmeal` DISABLE KEYS */;
INSERT INTO `t_setmeal` VALUES (5,'入职无忧体检套餐（男女通用）','0001','RZTJ','0','18-60',300,'入职体检套餐','入职体检套餐','https://tkzc00-bxg-health-parent.oss-cn-beijing.aliyuncs.com/ab96ab1d-ec26-4b00-b38b-add8b97e48b8.jpg'),(6,'粉红珍爱(女)升级TM12项筛查体检套餐','0002','FHZA','2','18-60',1200,'本套餐针对宫颈(TCT检查、HPV乳头瘤病毒筛查）、乳腺（彩超，癌抗125），甲状腺（彩超，甲功验血）以及胸片，血常规肝功等有全面检查，非常适合女性全面疾病筛查使用。',NULL,'3bd90d2c-4e82-42a1-a401-882c88b06a1a2.jpg'),(7,'阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐','0003','YGBM','0','55-100',1400,'本套餐主要针对常见肿瘤筛查，肝肾、颈动脉、脑血栓、颅内血流筛查，以及风湿、颈椎、骨密度检查。',NULL,'ee7dcf84-8a3a-4ab9-b981-9c5d272fd58d3.jpg'),(9,'孕前检查套餐（女）-精英版','0005','YQJCNV','2','18-50',1500,'孕前检查套餐（女）-精英版',NULL,'ac3b5a4d-33a5-4f37-bd49-99e06ce17d202.jpg'),(11,'珍爱高端升级肿瘤12项筛查（男女单人）','0006','ZAGD','0','14-20',2400,'本套餐是一款针对生化五项检查，心，肝，胆，胃，甲状腺，颈椎，肺功能，脑部检查（经颅多普勒）以及癌症筛查，适合大众人群体检的套餐。',NULL,'https://tkzc00-bxg-health-parent.oss-cn-beijing.aliyuncs.com/8d9a6255-e585-434c-91d4-e9248677f003.jpg'),(20,'测试套餐','0999','TEST','0','10-30',200,NULL,NULL,'https://tkzc00-bxg-health-parent.oss-cn-beijing.aliyuncs.com/21401188-5457-4787-9a5c-58b55bca42d5.jpg');
/*!40000 ALTER TABLE `t_setmeal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_setmeal_checkgroup`
--

DROP TABLE IF EXISTS `t_setmeal_checkgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_setmeal_checkgroup` (
  `setmeal_id` int NOT NULL DEFAULT '0',
  `checkgroup_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`setmeal_id`,`checkgroup_id`),
  KEY `checkgroup_key` (`checkgroup_id`),
  CONSTRAINT `checkgroup_key` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_setmeal_checkgroup`
--

LOCK TABLES `t_setmeal_checkgroup` WRITE;
/*!40000 ALTER TABLE `t_setmeal_checkgroup` DISABLE KEYS */;
INSERT INTO `t_setmeal_checkgroup` VALUES (5,5),(6,5),(7,5),(9,5),(11,5),(20,5),(5,6),(6,6),(7,6),(9,6),(11,6),(20,6),(5,7),(6,7),(7,7),(9,7),(11,7),(20,7),(5,8),(6,8),(7,8),(9,8),(11,8),(5,9),(6,9),(7,9),(9,9),(5,10),(6,10),(7,10),(9,10),(5,11),(6,11),(7,11),(9,11),(6,12),(7,12),(9,12),(6,13),(7,13),(9,13),(6,14),(7,14),(9,14),(6,15),(7,15),(9,15);
/*!40000 ALTER TABLE `t_setmeal_checkgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `remark` varchar(32) DEFAULT NULL,
  `station` varchar(1) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,NULL,NULL,'admin','$2a$10$u/BcsUUqZNWUxdmDhbnoeeobJy6IBsL1Gn/S0dMxI2RbSgnMKJ.4a',NULL,NULL,NULL),(2,NULL,NULL,'xiaoming','$2a$10$3xW2nBjwBM3rx1LoYprVsemNri5bvxeOd/QfmO7UDFQhW2HRHLi.C',NULL,NULL,NULL),(3,NULL,NULL,'test','$2a$10$zYJRscVUgHX1wqwu90WereuTmIg6h/JGirGG4SWBsZ60wVPCgtF8W',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_8` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-29 12:52:27
