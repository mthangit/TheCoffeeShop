-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 10, 2023 lúc 12:27 PM
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

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddBill` (IN `p_billDate` DATE, IN `p_customerName` VARCHAR(50), IN `p_billPrice` INT, IN `p_customersPhone` VARCHAR(50))   BEGIN
  INSERT INTO `bill` (`billDate`, `customerName`, `billPrice`, `customersPhone`)
  VALUES (p_billDate, p_customerName, p_billPrice, p_customersPhone);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddCategory` (IN `categoryName` VARCHAR(20))   BEGIN
    IF categoryName IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category name is required';
    ELSE
        INSERT INTO category (categoryName)
        VALUES (categoryName);
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addDiscount` (IN `p_discountID` VARCHAR(20), IN `p_discountName` VARCHAR(50), IN `p_discountQuantity` INT, IN `p_discountValue` INT, IN `p_discountStartDay` DATE, IN `p_discountEndDay` DATE)   BEGIN
    INSERT INTO discount (discountID, discountName, discountQuantity, discountValue, discountStartDay, discountEndDay)
    VALUES (p_discountID, p_discountName, p_discountQuantity, p_discountValue, p_discountStartDay, p_discountEndDay);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_customer` (IN `p_customerName` VARCHAR(50), IN `p_customerDOB` DATE, IN `p_customerAddress` VARCHAR(50), IN `p_customerPhone` VARCHAR(20), IN `p_customerEmail` VARCHAR(50))   BEGIN
  INSERT INTO customers (customerID, customerName, customerDOB, customerPhone, customerAddress, customerEmail, customerStartDate, customerRate, customerPoint)
  VALUES (NULL, p_customerName, p_customerDOB, p_customerPhone, p_customerAddress, p_customerEmail, CURDATE(), 'Member', 0);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ApplyDiscount` (IN `p_name` VARCHAR(255))   BEGIN
    DECLARE v_quantity INT;
    
    -- Lấy giá trị hiện tại của quantity
    SELECT discountQuantity INTO v_quantity FROM discount WHERE discountName = p_name;
    
    -- Kiểm tra giá trị quantity
    IF v_quantity > 0 THEN
        -- Giảm quantity đi 1
        UPDATE discount SET discountQuantity = discountQuantity - 1 WHERE discountName = p_name;
        
        -- Kiểm tra nếu quantity = 0, xóa discount
        IF v_quantity - 1 = 0 THEN
            DELETE FROM discount WHERE discountName = p_name;
        END IF;
        
        -- SELECT CONCAT('Applied discount: ', p_name) AS message;
    -- ELSE
      --  SELECT CONCAT('Discount not available: ', p_name) AS message;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetBestSellingDrinksInTimeRange` (IN `startDate` DATE, IN `endDate` DATE)   BEGIN
    SELECT itemName, itemPrice, totalQuantity, totalAmount
    FROM (
        SELECT bd.itemName, fi.itemPrice, SUM(bd.quantity) AS totalQuantity, SUM(bd.quantity * fi.itemPrice) AS totalAmount
        FROM bill_detail bd
        INNER JOIN food_item fi ON bd.itemName = fi.itemName
        INNER JOIN bill b ON bd.billID = b.billID
        WHERE b.billDate BETWEEN startDate AND endDate
        GROUP BY bd.itemName

        UNION ALL

        SELECT fi2.itemName, fi2.itemPrice, 0 AS totalQuantity, 0 AS totalAmount
        FROM food_item fi2
        WHERE fi2.itemName NOT IN (SELECT itemName FROM bill_detail)
    ) AS result
    ORDER BY totalQuantity DESC, itemPrice DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetBillPriceByMonth` (IN `yearInput` INT)   BEGIN
    DECLARE monthCounter INT DEFAULT 1;
    DECLARE totalMonths INT DEFAULT 12;
    
    -- Create a temporary table to store the result
    CREATE TEMPORARY TABLE IF NOT EXISTS tempBillPriceByMonth (
        `month` INT,
        `price` INT
    );

    -- Initialize the temporary table with 0 price for all months
    WHILE monthCounter <= totalMonths DO
        INSERT INTO tempBillPriceByMonth (`month`, `price`) VALUES (monthCounter, 0);
        SET monthCounter = monthCounter + 1;
    END WHILE;

    -- Update the temporary table with actual prices
    UPDATE tempBillPriceByMonth
    INNER JOIN (
        SELECT MONTH(billDate) AS `month`, SUM(billPrice) AS `price`
        FROM bill
        WHERE YEAR(billDate) = yearInput
        GROUP BY `month`
    ) AS billSummary ON tempBillPriceByMonth.`month` = billSummary.`month`
    SET tempBillPriceByMonth.`price` = billSummary.`price`;

    -- Select the result from the temporary table
    SELECT `month`, `price` FROM tempBillPriceByMonth;

    -- Calculate and display the total price
    SELECT CONCAT('Tổng cộng: ', SUM(`price`)) AS `Total Price` FROM tempBillPriceByMonth;

    -- Drop the temporary table
    DROP TABLE IF EXISTS tempBillPriceByMonth;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetBillPriceByMonthYear` (IN `inputMonth` INT, IN `inputYear` INT)   BEGIN
  DECLARE lastDay INT;
  DECLARE currentDay INT;
  
  -- Xác định ngày cuối cùng trong tháng
  SET lastDay = DAY(LAST_DAY(CONCAT(inputYear, '-', inputMonth, '-01')));
  
  -- Tạo bảng tạm để lưu trữ kết quả
  CREATE TEMPORARY TABLE IF NOT EXISTS tempDates (
    billDate DATE
  );
  
  -- Tạo danh sách các ngày từ ngày đầu tiên đến ngày cuối cùng của tháng
  SET currentDay = 1;
  WHILE currentDay <= lastDay DO
    INSERT INTO tempDates (billDate)
    VALUES (DATE_ADD(CONCAT(inputYear, '-', inputMonth, '-01'), INTERVAL (currentDay - 1) DAY));
    
    SET currentDay = currentDay + 1;
  END WHILE;
  
  -- Truy vấn dữ liệu từ bảng tạm và hiển thị kết quả
  SELECT tempDates.billDate, IFNULL(SUM(bill.billPrice), 0) AS billPrice
  FROM tempDates
  LEFT JOIN bill ON tempDates.billDate = bill.billDate
  GROUP BY tempDates.billDate;
  
  -- Xóa bảng tạm
  DROP TABLE IF EXISTS tempDates;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSalesForDate` (IN `saleDate` DATE)   BEGIN
    SELECT bd.itemName, SUM(bd.quantity) AS totalQuantity, fi.itemPrice, SUM(bd.totalPrice) AS totalAmount
    FROM bill_detail bd
    INNER JOIN bill b ON bd.billID = b.billID
    LEFT JOIN food_item fi ON bd.itemName = fi.itemName
    WHERE DATE(b.billDate) = saleDate
    GROUP BY bd.itemName
    ORDER BY totalQuantity DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateCustomerPointsPLus` (IN `customerPhoneNumber` VARCHAR(50), IN `additionalPoints` INT)   BEGIN
    UPDATE customers
    SET customerPoint = customerPoint + additionalPoints
    WHERE customerPhone = customerPhoneNumber;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDiscount` (IN `p_discountName` VARCHAR(50), IN `p_discountQuantity` INT, IN `p_discountValue` INT, IN `p_discountStartDay` DATE, IN `p_discountEndDay` DATE, IN `p_discountID` VARCHAR(50))   BEGIN
    UPDATE discount
    SET
        discountName = p_discountName,
        discountQuantity = p_discountQuantity,
        discountValue = p_discountValue,
        discountStartDay = p_discountStartDay,
        discountEndDay = p_discountEndDay
    WHERE
        discountID = p_discountID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateFoodItem` (IN `p_itemID` INT, IN `p_newPrice` VARCHAR(50), IN `p_newCategoryName` VARCHAR(20), IN `p_newItemName` VARCHAR(40))   BEGIN
    -- START TRANSACTION;
    
    -- Đảm bảo tính nhất quán và tránh lost update bằng cách sử dụng isolation level SERIALIZABLE
    -- SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    
    -- Cập nhật giá, tên danh mục và tên món ăn cho food item
    UPDATE food_item
    SET itemPrice = p_newPrice, categoryName = p_newCategoryName, itemName = p_newItemName
    WHERE itemID = p_itemID;
    
    -- COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateFoodItemCategoryID` (IN `categoryNameParam` VARCHAR(50))   BEGIN
    UPDATE food_item fi
    JOIN category c ON fi.categoryName = c.categoryName
    SET fi.categoryID = c.categoryID
    WHERE c.categoryName = categoryNameParam;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_itemID` (IN `itemNameParam` VARCHAR(50))   BEGIN
  DECLARE itemIDValue INT;

  -- Lấy itemID từ bảng food_item dựa trên itemName
  SELECT itemID INTO itemIDValue
  FROM food_item
  WHERE itemName = itemNameParam
  LIMIT 1;

  -- Cập nhật itemID trong bảng bill_detail
  UPDATE bill_detail
  SET itemID = itemIDValue
  WHERE itemName = itemNameParam;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_rate` (IN `customer_phone` VARCHAR(255))   BEGIN
  DECLARE customer_point INT;
  DECLARE customer_rank VARCHAR(255);
  SELECT customerPoint INTO customer_point FROM customers WHERE customerPhone = customer_phone;
  IF customer_point >= 0 AND customer_point <= 149 THEN
    SET customer_rank = 'Member';
  ELSEIF customer_point >= 150 AND customer_point <= 299 THEN
    SET customer_rank = 'Silver';
  ELSE
    SET customer_rank = 'Gold';
  END IF;
  UPDATE customers SET customerRate = customer_rank WHERE customerPhone = customer_phone;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ViewBillsByDate` (IN `search_date` VARCHAR(20))   BEGIN
  -- Truy vấn các bill với ngày truyền vào
  SET @query = CONCAT("SELECT * FROM bill WHERE billDate LIKE '%", search_date, "%'");
  PREPARE statement FROM @query;
  EXECUTE statement;
  DEALLOCATE PREPARE statement;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ViewTotalBillAmountByDate` (IN `search_date` VARCHAR(20), OUT `total_amount` INT)   BEGIN
  -- Truy vấn tổng tiền của các bill với ngày truyền vào
  SET @query = CONCAT("SELECT SUM(billPrice) INTO @total_amount FROM bill WHERE billDate LIKE '%", search_date, "%'");
  PREPARE statement FROM @query;
  EXECUTE statement;
  DEALLOCATE PREPARE statement;
  
  -- Gán giá trị tổng tiền vào biến OUT
  SET total_amount = @total_amount;
END$$

--
-- Các hàm
--
CREATE DEFINER=`root`@`localhost` FUNCTION `GetNumberOfBills` (`saleDate` DATE) RETURNS INT(11)  BEGIN
    DECLARE numberOfBills INT;
    
    SELECT COUNT(*) INTO numberOfBills
    FROM bill
    WHERE DATE(billDate) = saleDate;
    
    RETURN numberOfBills;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getTotalPriceByDay` (`saleDate` DATE) RETURNS INT(11)  BEGIN
    DECLARE totalPrice INT;
    
    SELECT sum(billPrice) INTO totalPrice
    FROM bill
    WHERE DATE(billDate) = saleDate;
    
    RETURN totalPrice;
END$$

DELIMITER ;

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

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`billID`, `billDate`, `customerName`, `billPrice`, `customerPhone`, `employeeID`, `customerID`) VALUES
(1, '2023-06-10', 'Vũ Anh Khoa', 95000, '0983666421', 1014, 101),
(2, '2023-06-10', 'Thang', 156750, NULL, 1014, NULL),
(3, '2023-06-10', 'Hồ Hồng Liên', 35000, '0929478051', 1003, 104),
(4, '2023-06-10', 'Vũ Anh Khoa', 35000, '0983666421', 1003, 101);

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

--
-- Đang đổ dữ liệu cho bảng `bill_detail`
--

INSERT INTO `bill_detail` (`billID`, `itemName`, `quantity`, `totalPrice`, `itemID`) VALUES
(1, 'Dâu latte', 1, 70000, 124),
(1, 'kem vani', 1, 25000, 139),
(2, 'Pudding', 1, 10000, 135),
(2, 'Mì trộn trứng', 1, 35000, 137),
(2, 'kem vani', 1, 25000, 139),
(3, 'Pudding', 1, 10000, 135),
(3, 'kem vani', 1, 25000, 139),
(4, 'Mì trộn trứng', 1, 35000, 137);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`categoryID`, `categoryName`) VALUES
(7, 'Ăn vặt'),
(1, 'Cà phê'),
(5, 'Freeze'),
(8, 'Kem'),
(4, 'Sữa tươi'),
(6, 'Topping'),
(3, 'Trà'),
(2, 'Trà sữa');

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
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`customerID`, `customerName`, `customerDOB`, `customerPhone`, `customerAddress`, `customerEmail`, `customerStartDate`, `customerRate`, `customerPoint`) VALUES
(101, 'Vũ Anh Khoa', '1998-07-30', '0983666421', 'Tân Nguyên, Bình Dương', 'khoava3007@gmail.com', '2022-12-25', 'Gold', 50228),
(102, 'Vũ Ngọc Oanh', '1999-06-03', '0938888777', 'Quận 5, TPHCM', 'oanhvn0306@gmail.com', '2022-12-25', 'Gold', 543),
(103, 'Dương Ngọc Hà', '2000-09-25', '0597473612', 'Thủ Đức, TPHCM', 'hadn2509@gmail.com', '2022-12-25', 'Gold', 1826),
(104, 'Hồ Hồng Liên', '2003-10-18', '0929478051', 'Quận Bình Thạnh, TPHCM', 'lienhh1810@gmail.com', '2022-12-25', 'Gold', 371),
(105, 'Lý Quang Vũ', '1986-12-08', '0936552156', 'Quận 4, TPHCM', 'vulq0812@gmail.com', '2022-12-25', 'Gold', 459),
(106, 'Nguyễn Cao Phong', '1985-03-20', '0784562333', 'Quận 4, TPHCM', 'phongnc2003@gmail.co', '2022-12-25', 'Silver', 242),
(107, 'Ngô Hà Thanh', '1989-11-06', '0798695531', 'Dĩ An, Bình Dương', 'thanhnh0611@gmail.co', '2022-12-26', 'Member', 140),
(108, 'Đinh Hồng Ngọc', '1999-12-11', '0965128894', 'Quận 1, TPHCM', 'ngocdh1112@gmail.com', '2022-12-26', 'Silver', 208),
(109, 'Phạm Gia Lập', '2000-12-30', '0165842337', 'Biên Hòa, Đồng Nai', 'lappg3012@gmail.com', '2022-12-26', 'Gold', 493),
(110, 'Ngô Khắc Duy', '1998-01-05', '0263975846', 'Quận 5, TPHCM', 'duynk0501@gmail.com', '2022-12-26', 'Member', 7),
(111, 'Lương Hữu Nam', '2001-08-26', '0797232455', 'Thủ Đức, TPHCM', 'namlh2608@gmail.com', '2022-12-27', 'Gold', 546),
(112, 'Nguyễn Khánh Ly', '1992-06-06', '0903651888', 'Quận 7, TPHCM', 'lynk0606@gmail.com', '2022-12-27', 'Gold', 2228),
(113, 'Bùi Thảo Vy', '1991-09-30', '0937265598', 'Quận 10, TPHCM', 'vybt3009@gmail.com', '2022-12-27', 'Silver', 224),
(114, 'Trang Minh Hà', '2004-12-12', '0797797797', 'Quận 11, TPHCM', 'hatm1212@gmail.com', '2022-12-27', 'Silver', 170),
(115, 'Huỳnh Chí Công', '1997-01-20', '0365111541', 'Biên Hòa, Đồng Nai', 'conghc2001@gmail.com', '2022-12-27', 'Silver', 172),
(116, 'Lục Thái Đức', '2003-05-23', '0975684235', 'Thủ Đức, TPHCM', 'duclt2305@gmail.com', '2022-12-27', 'Silver', 483),
(117, 'Đặng Minh Anh', '1994-04-12', '0325614298', 'Quận 4, TPHCM', 'anhdm1204@gmail.com', '2022-12-27', 'Gold', 497),
(118, 'Nguyễn Huy Thông', '1993-10-26', '0236956659', 'Quận 11, TPHCM', 'thongnh2610@gmail.co', '2022-12-27', 'Gold', 720),
(119, 'Phạm Ngọc Trường', '1994-01-29', '0935621152', 'Quận 8, TPHCM', 'truongpn2901@gmail.c', '2022-12-27', 'Silver', 199),
(120, 'Hồ Kim Ngân', '2002-01-18', '0978145235', 'Vĩnh Cửu, Đồng Nai', 'nganhk1801@gmail.com', '2022-12-28', 'Silver', 390015),
(121, 'Phạm Thị Quỳnh Anh', '1987-09-14', '0938177977', 'Quận 1, TPHCM', 'anhptq1409@gmail.com', '2022-12-28', 'Silver', 151),
(122, 'Trần Kiều Thu', '1997-02-11', '0934136582', 'Quận 1, TPHCM', 'thutk1102@gmail.com', '2022-12-28', 'Silver', 150),
(123, 'Bùi Thùy Linh', '2005-03-28', '0968142103', 'Quận 11, TPHCM', 'linhbt2803@gmail.com', '2022-12-28', 'Gold', 331),
(124, 'Hoàng Vân Anh', '1984-12-22', '0751328039', 'Quận 5, TPHCM', 'anhhv2212@gmail.com', '2022-12-28', 'Member', 97),
(125, 'Đỗ Trúc Lam', '1992-08-22', '0888931523', 'Quận 1, TPHCM', 'lamdt2208@gmail.com', '2022-12-28', 'Member', 82),
(126, 'Lê Ngọc Hà', '1995-10-07', '0895124480', 'Quận 5, TPHCM', 'haln0710@gmail.com', '2022-12-28', 'Member', 73),
(127, 'Phùng Phượng Loan', '1986-05-30', '0895361254', 'Quận 1, TPHCM', 'loanpp3005@gmail.com', '2022-12-28', 'Silver', 264),
(128, 'Lê Ánh Dương', '2000-07-22', '0123654888', 'Quận 10, TPHCM', 'duongla2207@gmail.co', '2022-12-29', 'Member', 7),
(129, 'Ngô Phương Duy', '1995-06-07', '0938122000', 'Quận 1, TPHCM', 'duynp0706@gmail.com', '2022-12-29', 'Silver', 256),
(130, 'Nguyễn Hữu Hoàng', '1998-02-01', '0123456789', 'Quận Bình Thạnh, TPHCM', 'hoangnh0102@gmail.co', '2022-12-29', 'Gold', 612),
(131, 'Mai Kim Liên', '1982-05-13', '0123654987', 'Quận 5, TPHCM', 'lienmk1305@gmail.com', '2022-12-29', 'Member', 89),
(132, 'Đoàn Quốc Tân', '1990-04-27', '0123678459', 'Quận 8, TPHCM', 'tandq2704@gmail.com', '2022-12-30', 'Silver', 163),
(133, 'Trần Ngọc Hân', '1995-11-27', '0532146973', 'Quận 10, TPHCM', 'hantn2711@gmail.com', '2022-12-30', 'Silver', 266),
(134, 'Châu Ngọc Linh', '1984-05-03', '0862143579', 'Quận 11, TPHCM', 'linhcn0305@gmail.com', '2022-12-30', 'Member', 99),
(135, 'Cao Thành Nhân', '1987-02-21', '0361248957', 'Quận 10, TPHCM', 'nhanct2102@gmail.com', '2022-12-31', 'Member', 0),
(136, 'John Doe', '1990-01-01', '123456789', '123 Main St', 'johndoe@example.com', '2023-06-05', 'Member', 45),
(137, 'dương', '2003-08-04', '0908909098', 'thủ đức', 'duong@gmail.com', '2023-06-06', 'Member', 14),
(138, 'tứ', '2023-06-02', '0909909090', 'thu duc', 'tu123@gmail.com', '2023-06-10', 'Member', 0);

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

--
-- Đang đổ dữ liệu cho bảng `discount`
--

INSERT INTO `discount` (`discountID`, `discountName`, `discountQuantity`, `discountValue`, `discountStartDay`, `discountEndDay`) VALUES
('D05', 'HKT', 8, 50, '2023-08-06', '2023-12-25'),
('D07', 'SN7', 7, 5, '2023-06-04', '2023-08-04'),
('D08', 'SN8', 10, 5, '2023-06-04', '2023-08-04'),
('D09', 'SN9', 9, 5, '2023-06-04', '2023-08-04'),
('D10', 'SN10', 100, 5, '2023-06-04', '2023-08-04'),
('D11', 'SN11', 10, 5, '2023-06-04', '2023-09-16'),
('D13', 'SILVER', 10, 27, '2023-06-04', '2023-08-04'),
('D14', 'GOLD', 10, 50, '2023-06-04', '2023-08-04'),
('D30', 'New Discount', 10, 70, '2023-06-01', '2023-06-30'),
('FTT', '30-4', 20, 10, '2024-04-30', '2024-05-02');

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

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`employeeID`, `employeeName`, `employeePhone`, `employeeAddress`, `employeeCID`, `employeeHometown`, `employeeType`, `employeePosition`, `employeeTaxID`, `employeeContractID`, `employeeEmail`, `status`) VALUES
(1001, 'Trần Ngân Anh', '0978444231', 'Quận 8, TPHCM', '272944111', 'Đà Nẵng', 'parttime', 'waitress', '', '', 'anhtn123@gmail.com', 'Đang làm việc'),
(1002, 'Nguyễn Đăng Quang', '0165224873', 'Thủ Đức, TPHCM', '272152342', 'Hà Nội', 'fulltime', 'barista', '8321019787', '10', 'quangnd123@gmail.com', 'Đang làm việc'),
(1003, 'Trịnh Ngọc Minh', '0938452110', 'Quận 10, TPHCM', '278544122', 'Long An', 'parttime', 'waiter', NULL, NULL, 'minhtn123@gmail.com', 'Đang làm việc'),
(1004, 'Hồ Trường Thanh', '0937456223', 'Quận 10, TPHCM', '271641522', 'Nghệ An', 'parttime', 'waiter', NULL, NULL, 'thanhht123@gmail.com', 'Đang làm việc'),
(1005, 'Đàm Hiếu Hạnh', '0985423112', 'Quận Bình Thạnh, TPHCM', '272666444', 'Quảng Bình', 'parttime', 'waiter', NULL, NULL, 'hanhdh123@gmail.com', 'Đang làm việc'),
(1006, 'Dương Thảo My', '0354621000', 'Quận 7, TPHCM', '272894555', 'Điện Biên', 'parttime', 'waitress', NULL, NULL, 'mydt123@gmail.com', 'Đang làm việc'),
(1007, 'Bạch An Hạ', '0895633177', 'Quận 7, TPHCM', '272315264', 'Đồng Tháp', 'fulltime', 'manager', '8463543138', '11', 'haba123@gmail.com', 'Đang làm việc'),
(1008, 'Ngô Phương Uyên', '0124551239', 'Quận 1, TPHCM', '272874410', 'Đà Nẵng', 'fulltime', 'chef', '8469912456', '12', 'uyennp123@gmail.com', 'Đang làm việc'),
(1009, 'Lê Thu Tiên', '0797232566', 'Quận 11, TPHCM', '272999100', 'Gia Lai', 'parttime', 'waitress', '', '', 'tienlt123@gmail.com', 'Đã nghỉ'),
(1010, 'Giang Ngọc Thảo', '0791235865', 'Quận 5, TPHCM', '272102333', 'Vũng Tàu', 'parttime', 'waitress', '', '', 'thaogn123@gmail.com', 'Đang làm việc'),
(1011, 'Nguyễn Minh Minh', '0125746352', 'Quận 5, TPHCM', '2272111643', 'Phan Thiết', 'parttime', 'waiter', NULL, NULL, 'minhnm123@gmail.com', 'Đang làm việc'),
(1012, 'Tạ Mỹ Loan', '0365785416', 'Thủ Đức, TPHCM', '272419999', 'Tây Nguyên', 'fulltime', 'accountant', '8382964120', '13', 'loantm123@gmail.com', 'Đang làm việc'),
(1013, 'Kiều Tùng Lâm', '0241536987', 'Quận 8, TPHCM', '273542006', 'Quảng Ngãi', 'fulltime', 'barista', '8836125875', '14', 'lamkt123@gmail.com', 'Đang làm việc'),
(1014, 'hoang manh thang', '0365785416', 'Hóc Môn, TPHCM', '034435053860', 'TPHCM', 'parttime', 'chef', '', '', 'thang@gmail.com', 'Đã nghỉ'),
(1015, 'thuc', '4465454734', 'thu duc', '657675675674', 'qn', 'parttime', 'accountant', 'null', 'null', 'thuc@gmail.com', 'Đang làm việc');

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
-- Đang đổ dữ liệu cho bảng `food_item`
--

INSERT INTO `food_item` (`itemID`, `itemName`, `itemPrice`, `categoryName`, `categoryID`) VALUES
(101, 'Cà phê đen', 25000, 'Cà phê', 1),
(102, 'Cà phê sữa', 25000, 'Cà phê', 1),
(103, 'Bạc xỉu', 30000, 'Cà phê', 1),
(104, 'Trà sữa trân châu đen', 60000, 'Trà sữa', 2),
(105, 'Trà sữa trân châu hoàng kim', 66000, 'Trà sữa', 2),
(106, 'Trà sữa pudding đậu đỏ', 64000, 'Trà sữa', 2),
(107, 'Trà sữa sương sáo', 60000, 'Trà sữa', 2),
(108, 'Trà sữa trà xanh', 57000, 'Trà sữa', 2),
(109, 'Trà sữa chocolate', 64000, 'Trà sữa', 2),
(110, 'Trà sữa đào', 66000, 'Trà sữa', 2),
(111, 'Trà sữa dâu', 68000, 'Trà sữa', 2),
(112, 'Trà sữa khoai môn', 64000, 'Trà sữa', 2),
(113, 'Trà sữa oolong 3J', 70000, 'Trà sữa', 2),
(114, 'Trà sữa xoài trân châu đen', 70000, 'Trà sữa', 2),
(115, 'Trà sữa choco mint', 67000, 'Trà sữa', 2),
(116, 'Trà sữa Okinawa', 70000, 'Trà sữa', 2),
(117, 'Trà bí đao', 53000, 'Trà ', 3),
(118, 'Trà oolong kem sữa', 64000, 'Trà ', 3),
(119, 'Trà alisan kem sữa', 64000, 'Trà ', 3),
(120, 'Trà oolong vải', 60000, 'Trà ', 3),
(121, 'Trà đen đào', 62000, 'Trà ', 3),
(122, 'Trà chanh aiyu trân châu trắng', 60000, 'Trà ', 3),
(123, 'Sữa tươi Okinawa', 64000, 'Sữa tươi', 4),
(124, 'Dâu latte', 70000, 'Sữa tươi', 4),
(125, 'Okinawa latte', 70000, 'Sữa tươi', 4),
(126, 'Mint cookie smoothie', 67000, 'Freeze', 5),
(127, 'Strawberry choco cookie smoothie', 70000, 'Freeze', 5),
(128, 'Strawberry oreo smoothie', 68000, 'Freeze', 5),
(129, 'Matcha đá xay', 68000, 'Freeze', 5),
(130, 'Khoai môn đá xay', 68000, 'Freeze', 5),
(131, 'Trân châu đen', 10000, 'Topping', 6),
(132, 'Trân châu trắng', 10000, 'Topping', 6),
(133, 'Đậu đỏ', 11000, 'Topping', 6),
(134, 'Sương sáo', 10000, 'Topping', 6),
(135, 'Pudding', 10000, 'Topping', 6),
(136, 'Kem sữa', 17000, 'Topping', 6),
(137, 'Mì trộn trứng', 35000, 'Ăn vặt', 7),
(138, 'Khoai tây chiên', 20000, 'Ăn vặt', 7),
(139, 'kem vani', 25000, 'Kem', 8);

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
-- Đang đổ dữ liệu cho bảng `system_access`
--

INSERT INTO `system_access` (`sysUsername`, `sysPassword`, `type`, `employeeID`) VALUES
('admin', '123', 'manager', 1014),
('employee', '1', 'employee', 1001),
('minh', '1', 'employee', 1003);

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
