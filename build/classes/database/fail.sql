-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for coffeeshop
CREATE DATABASE IF NOT EXISTS `coffeeshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `coffeeshop`;

-- Dumping data for table coffeeshop.discount: ~10 rows (approximately)
INSERT INTO `discount` (`discountID`, `discountName`, `discountQuantity`, `discountValue`, `discountStartDay`, `discountEndDay`) VALUES
	('541', 'Tám tháng3', 100, 1, '2023-06-08', '2023-06-09'),
	('D05', 'HKT', 9, 50, '2023-08-06', '2023-12-25'),
	('D07', 'SN7', 10, 5, '2023-06-04', '2023-08-04'),
	('D08', 'SN8', 10, 5, '2023-06-04', '2023-08-04'),
	('D09', 'SN9', 9, 5, '2023-06-04', '2023-08-04'),
	('D10', 'SN10', 100, 5, '2023-06-04', '2023-08-04'),
	('D11', 'SN11', 10, 5, '2023-06-04', '2023-09-16'),
	('D13', 'SILVER', 10, 27, '2023-06-04', '2023-08-04'),
	('D14', 'GOLD', 10, 50, '2023-06-04', '2023-08-04'),
	('D30', 'New Discount', 10, 70, '2023-06-01', '2023-06-30');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
