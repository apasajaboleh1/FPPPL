/*
SQLyog Ultimate v8.6 Beta2
MySQL - 5.6.24 : Database - etool
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`etool` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `etool`;

/*Table structure for table `harga_tol` */

CREATE TABLE `harga_tol` (
  `id_harga` int(10) NOT NULL AUTO_INCREMENT,
  `golongan` int(2) NOT NULL,
  `harga` int(10) NOT NULL,
  `asal` varchar(255) NOT NULL,
  `tujuan` varchar(255) NOT NULL,
  PRIMARY KEY (`id_harga`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `harga_tol` */

insert  into `harga_tol`(`id_harga`,`golongan`,`harga`,`asal`,`tujuan`) values (1,1,3000,'Waru','Sidoarjo'),(2,2,4000,'Waru','Sidoarjo'),(3,3,5000,'Waru','Sidoarjo'),(4,4,6000,'Waru','Sidoarjo'),(5,5,7500,'Waru','Sidoarjo'),(6,1,4000,'Waru','Porong'),(7,2,5000,'Waru','Porong'),(8,3,8000,'Waru','Porong'),(9,4,10000,'Waru','Porong'),(10,5,12000,'Waru','Porong'),(11,1,3000,'Sidoarjo','Waru'),(12,2,4000,'Sidoarjo','Waru'),(13,3,5000,'Sidoarjo','Waru'),(14,4,6000,'Sidoarjo','Waru'),(15,5,7500,'Sidoarjo','Waru'),(16,1,3000,'Sidoarjo','Porong'),(17,2,4000,'Sidoarjo','Porong'),(18,3,5000,'Sidoarjo','Porong'),(19,4,6000,'Sidoarjo','Porong'),(20,5,7500,'Sidoarjo','Porong'),(21,1,3000,'Porong','Sidoarjo'),(22,2,4000,'Porong','Sidoarjo'),(23,3,5000,'Porong','Sidoarjo'),(24,4,6000,'Porong','Sidoarjo'),(25,5,7500,'Porong','Sidoarjo'),(26,1,4000,'Porong','Waru'),(27,2,5000,'Porong','Waru'),(28,3,8000,'Porong','Waru'),(29,4,10000,'Porong','Waru'),(30,5,12000,'Porong','Waru');

/*Table structure for table `history_popup` */

CREATE TABLE `history_popup` (
  `id_historypopup` int(11) NOT NULL AUTO_INCREMENT,
  `id_pengguna` int(11) NOT NULL,
  `jumlah_topup` int(9) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_historypopup`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `history_popup` */

insert  into `history_popup`(`id_historypopup`,`id_pengguna`,`jumlah_topup`,`waktu`) values (1,3,0,'2015-12-17 21:08:12'),(2,3,0,'2015-12-17 21:08:52'),(3,3,20000,'2015-12-17 21:09:18'),(4,3,20000,'2015-12-17 21:11:43');

/*Table structure for table `history_transaksi` */

CREATE TABLE `history_transaksi` (
  `id_transaksi` int(10) NOT NULL AUTO_INCREMENT,
  `id_pengguna` int(10) NOT NULL,
  `id_harga` int(10) NOT NULL,
  `tanggal_transaksi` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_operator` int(11) NOT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `id_pengguna` (`id_pengguna`),
  KEY `id_harga` (`id_harga`),
  CONSTRAINT `history_transaksi_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  CONSTRAINT `history_transaksi_ibfk_2` FOREIGN KEY (`id_harga`) REFERENCES `harga_tol` (`id_harga`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;

/*Data for the table `history_transaksi` */

insert  into `history_transaksi`(`id_transaksi`,`id_pengguna`,`id_harga`,`tanggal_transaksi`,`id_operator`) values (11,3,10,'2015-12-10 00:00:00',0),(12,3,10,'2015-12-10 00:00:00',0),(13,3,10,'2015-12-10 00:00:00',0),(14,3,10,'2015-12-10 00:00:00',0),(15,3,6,'2015-12-10 00:00:00',0),(16,3,10,'2015-12-12 00:00:00',0),(17,3,7,'2015-12-12 00:00:00',0),(18,3,10,'2015-12-12 00:00:00',0),(19,3,10,'2015-12-12 00:00:00',0),(20,3,8,'2015-12-12 00:00:00',0),(21,3,8,'2015-12-12 00:00:00',0),(22,3,10,'2015-12-12 00:00:00',0),(23,3,8,'2015-12-12 00:00:00',0),(24,3,8,'2015-12-12 00:00:00',0),(25,3,8,'2015-12-12 00:00:00',0),(26,3,8,'2015-12-12 00:00:00',0),(27,3,8,'2015-12-12 00:00:00',0),(28,3,8,'2015-12-12 00:00:00',0),(29,3,10,'2015-12-12 00:00:00',0),(30,3,10,'2015-12-12 00:00:00',0),(31,3,10,'2015-12-12 00:00:00',0),(32,3,10,'2015-12-12 00:00:00',0),(33,3,10,'2015-12-12 00:00:00',0),(34,3,10,'2015-12-12 00:00:00',0),(35,1,10,'2015-12-12 00:00:00',0),(36,3,10,'2015-12-12 00:00:00',0),(37,1,10,'2015-12-12 00:00:00',0),(38,3,10,'2015-12-12 00:00:00',0),(39,3,10,'2015-12-12 00:00:00',0),(40,1,10,'2015-12-12 00:00:00',0),(41,3,10,'2015-12-12 00:00:00',0),(42,1,10,'2015-12-12 00:00:00',0),(43,1,10,'2015-12-12 00:00:00',0),(44,1,10,'2015-12-12 00:00:00',0),(45,1,10,'2015-12-12 00:00:00',0),(46,1,10,'2015-12-12 00:00:00',0),(47,3,10,'2015-12-12 00:00:00',0),(48,1,10,'2015-12-12 00:00:00',0),(49,1,10,'2015-12-12 00:00:00',0),(50,1,10,'2015-12-12 00:00:00',0),(51,1,10,'2015-12-12 00:00:00',0),(52,3,10,'2015-12-12 00:00:00',0),(53,1,10,'2015-12-12 00:00:00',0),(54,1,10,'2015-12-12 00:00:00',0),(55,1,10,'2015-12-12 00:00:00',0),(56,3,10,'2015-12-12 00:00:00',0),(57,1,10,'2015-12-12 00:00:00',0),(58,3,10,'2015-12-12 00:00:00',0),(59,3,10,'2015-12-12 00:00:00',0),(60,1,10,'2015-12-12 00:00:00',0),(61,3,10,'2015-12-12 00:00:00',0),(62,1,10,'2015-12-12 00:00:00',0),(63,3,10,'2015-12-12 00:00:00',0),(64,1,10,'2015-12-12 00:00:00',0),(65,3,10,'2015-12-12 00:00:00',0),(66,3,10,'2015-12-12 00:00:00',0),(67,1,10,'2015-12-12 00:00:00',0),(68,3,10,'2015-12-12 00:00:00',0),(69,1,10,'2015-12-12 00:00:00',0),(70,3,10,'2015-12-12 00:00:00',0),(71,1,10,'2015-12-12 00:00:00',0),(72,3,10,'2015-12-12 00:00:00',0),(73,1,10,'2015-12-12 00:00:00',0),(74,1,10,'2015-12-12 00:00:00',0),(75,1,10,'2015-12-12 00:00:00',0),(76,3,10,'2015-12-17 21:12:06',1);

/*Table structure for table `operator` */

CREATE TABLE `operator` (
  `id_operator` int(11) NOT NULL AUTO_INCREMENT,
  `nama_operator` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `alamat_operator` varchar(255) NOT NULL,
  PRIMARY KEY (`id_operator`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `operator` */

insert  into `operator`(`id_operator`,`nama_operator`,`username`,`password`,`alamat_operator`) values (1,'lazaro','alvin','a04cca766a885687e33bc6b114230ee9','KX9/10 Surabaya');

/*Table structure for table `pengguna` */

CREATE TABLE `pengguna` (
  `id_pengguna` int(10) NOT NULL AUTO_INCREMENT,
  `nama_pengguna` varchar(255) NOT NULL,
  `alamat_pengguna` varchar(255) NOT NULL,
  `saldo` int(10) NOT NULL,
  `barcode_data` blob NOT NULL,
  PRIMARY KEY (`id_pengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `pengguna` */

insert  into `pengguna`(`id_pengguna`,`nama_pengguna`,`alamat_pengguna`,`saldo`,`barcode_data`) values (1,'Anonymous','Anonymous',0,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0§\0\0\02\0\0\0sY_\0\0\0PLTEÿÿÿ\0\0\0UÂÓ~\0\0\0	pHYs\0\0Ä\0\0Ä•+\0\0\0qIDAT8cøüch`ÀLCPô’Ða.÷5šÛ÷^©š>¹<yÍ¨è€‰†ô\0ŒÏÏ7Î©œù\0U­YÎÍÍugg ªýÙ€\"ÊR›vvF†èü4LÒg6§UüD³\r¤«{å(òD±–\Z\0{Åù~£×\0\0\0\0IEND®B`‚'),(3,'Gideon Siburian','Keputih Surabaya',22000,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0õ\0\0\02\0\0\0¾’*\0\0\0PLTEÿÿÿ\0\0\0UÂÓ~\0\0\0	pHYs\0\0Ä\0\0Ä•+\0\0\0£IDAT8cøü`h`À8Få˜=2:29×´Tšm\\âìÉk³ä‹É4‰ ˆä5£ò£ò„åé–~@¤!ÐEXå+Àò68åÏ0€äÓ‘mD–?ððóñù	…ŸÍóÛa“O6Ë¹Ù`c8Û,™q3ù;Éæon6°å™ù±ÉŸI6ãÊKâšŸ†S¾öççã3lþ~üÆ›<60*O ~\0\0D€<Y€Ú‘¶\0\0\0\0IEND®B`‚');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
