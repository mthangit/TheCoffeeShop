-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 11, 2023 lúc 05:18 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `coffeeshop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `billID` int(11) NOT NULL,
  `billDate` date NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `billPrice` int(11) NOT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `employeeID` int(11) NOT NULL,
  `customerID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill_detail`
--

CREATE TABLE `bill_detail` (
  `billID` int(11) NOT NULL,
  `itemName` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `itemID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Bẫy `category`
--
DELIMITER $$
CREATE TRIGGER `check_duplicate_category` AFTER INSERT ON `category` FOR EACH ROW BEGIN
    DECLARE count INT;
    
    SELECT COUNT(*) INTO count
    FROM category
    WHERE categoryName = NEW.categoryName;
    
    IF count > 1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Duplicate category name';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `customerDOB` date NOT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `customerAddress` varchar(50) DEFAULT NULL,
  `customerEmail` varchar(50) NOT NULL,
  `customerStartDate` date NOT NULL,
  `customerRate` varchar(20) DEFAULT NULL,
  `customerPoint` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Bẫy `customers`
--
DELIMITER $$
CREATE TRIGGER `check_duplicate_customerPhone` AFTER INSERT ON `customers` FOR EACH ROW BEGIN
    DECLARE count INT;
    
    SELECT COUNT(*) INTO count
    FROM customers
    WHERE customerPhone = NEW.customerPhone;
    
    IF count > 1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Duplicate customer phone';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_start_date` AFTER INSERT ON `customers` FOR EACH ROW BEGIN
    IF NEW.customerStartDate <= NEW.customerDOB THEN
      SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'customerStartDate must be earlier than customerDOB';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `increment_customerID` BEFORE INSERT ON `customers` FOR EACH ROW BEGIN
  IF NEW.customerID IS NULL THEN
    SET NEW.customerID = (SELECT COALESCE(MAX(customerID), 0) + 1 FROM customers);
  END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `set_default_values` BEFORE INSERT ON `customers` FOR EACH ROW BEGIN
    SET NEW.customerPoint = 0;
    SET NEW.customerRate = 'Member';
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tg_customerbirthday_check_insert` BEFORE INSERT ON `customers` FOR EACH ROW BEGIN
	 DECLARE v_startdate DATE;
	 SELECT customerStartDate INTO v_startdate
	 FROM customers
	 WHERE customerID = NEW.customerID;
	 
    IF (NEW.customerDOB > CURDATE() OR NEW.customerDOB > v_startdate) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer birthday!';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tg_customerphone_check_insert` BEFORE INSERT ON `customers` FOR EACH ROW BEGIN
    IF (NEW.customerPhone NOT REGEXP '^[0-9]{10}$') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer phone!';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tg_customerphone_check_update` BEFORE UPDATE ON `customers` FOR EACH ROW BEGIN
    IF (NEW.customerPhone NOT REGEXP '^[0-9]{10}$') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer phone!';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tg_customerrate_check_insert` BEFORE INSERT ON `customers` FOR EACH ROW BEGIN
    IF (NEW.customerRate NOT IN ('Member', 'Silver', 'Gold')) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer rate!';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `discount`
--

CREATE TABLE `discount` (
  `discountID` varchar(20) NOT NULL,
  `discountName` varchar(50) NOT NULL,
  `discountQuantity` int(11) NOT NULL,
  `discountValue` int(11) NOT NULL,
  `discountStartDay` date NOT NULL,
  `discountEndDay` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `employeeID` int(11) NOT NULL,
  `employeeName` varchar(30) NOT NULL,
  `employeePhone` varchar(20) NOT NULL,
  `employeeAddress` varchar(50) NOT NULL,
  `employeeCID` varchar(20) NOT NULL,
  `employeeHometown` varchar(100) NOT NULL,
  `employeeType` varchar(30) NOT NULL,
  `employeePosition` varchar(30) NOT NULL,
  `employeeTaxID` varchar(20) DEFAULT NULL,
  `employeeContractID` varchar(11) DEFAULT NULL,
  `employeeEmail` varchar(50) NOT NULL DEFAULT '',
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `food_item`
--

CREATE TABLE `food_item` (
  `itemID` int(11) NOT NULL,
  `itemName` varchar(40) NOT NULL,
  `itemPrice` int(11) NOT NULL,
  `categoryName` varchar(50) NOT NULL,
  `categoryID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Bẫy `food_item`
--
DELIMITER $$
CREATE TRIGGER `check_item_price_INSERT` AFTER UPDATE ON `food_item` FOR EACH ROW BEGIN
    IF NEW.itemPrice <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'itemPrice must be greater than 0';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_item_price_UPDATE` AFTER UPDATE ON `food_item` FOR EACH ROW BEGIN
    IF NEW.itemPrice <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'itemPrice must be greater than 0';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `system_access`
--

CREATE TABLE `system_access` (
  `sysUsername` varchar(20) NOT NULL,
  `sysPassword` varchar(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  `employeeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billID`),
  ADD KEY `employeeID` (`employeeID`),
  ADD KEY `customerID` (`customerID`);

--
-- Chỉ mục cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD PRIMARY KEY (`billID`,`itemID`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`),
  ADD KEY `categoryName` (`categoryName`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerID`),
  ADD UNIQUE KEY `customerPhone_2` (`customerPhone`),
  ADD KEY `customerPhone_3` (`customerPhone`);

--
-- Chỉ mục cho bảng `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`discountID`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeID`) USING BTREE;

--
-- Chỉ mục cho bảng `food_item`
--
ALTER TABLE `food_item`
  ADD PRIMARY KEY (`itemID`,`categoryID`),
  ADD KEY `categoryName` (`categoryName`),
  ADD KEY `categoryID` (`categoryID`);

--
-- Chỉ mục cho bảng `system_access`
--
ALTER TABLE `system_access`
  ADD PRIMARY KEY (`sysUsername`),
  ADD KEY `employeeID` (`employeeID`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`),
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`);

--
-- Các ràng buộc cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD CONSTRAINT `bill_detail_ibfk_1` FOREIGN KEY (`billID`) REFERENCES `bill` (`billID`);

--
-- Các ràng buộc cho bảng `food_item`
--
ALTER TABLE `food_item`
  ADD CONSTRAINT `food_item_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`);

--
-- Các ràng buộc cho bảng `system_access`
--
ALTER TABLE `system_access`
  ADD CONSTRAINT `system_access_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
