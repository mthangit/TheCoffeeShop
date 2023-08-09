


-- Dumping structure for procedure coffeeshop.GetBestSellingDrinksInTimeRange
DELIMITER //
CREATE PROCEDURE `GetBestSellingDrinksInTimeRange`(IN startDate DATE, IN endDate DATE)
BEGIN
    SELECT itemName, itemPrice, totalQuantity, totalAmount
    FROM (
        SELECT fi.itemName, fi.itemPrice, SUM(bd.detailQuantity) AS totalQuantity, SUM(bd.detailQuantity * fi.itemPrice) AS totalAmount
        FROM bill_details bd
        INNER JOIN food_item fi ON bd.itemID = fi.itemID
        INNER JOIN bill b ON bd.billID = b.billID
        WHERE b.billDate BETWEEN startDate AND endDate
        GROUP BY bd.itemID

        UNION ALL

        SELECT fi2.itemName, fi2.itemPrice, 0 AS totalQuantity, 0 AS totalAmount
        FROM food_item fi2
        WHERE fi2.itemID NOT IN (SELECT itemID FROM bill_details)
    ) AS result
    ORDER BY totalQuantity DESC, itemPrice DESC;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.GetBillPriceByMonth
DELIMITER //
CREATE PROCEDURE `GetBillPriceByMonth`(
    IN yearInput INT
)
BEGIN
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
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.GetBillPriceByMonthYear
DELIMITER //
CREATE PROCEDURE `GetBillPriceByMonthYear`(IN inputMonth INT, IN inputYear INT)
BEGIN
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
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.GetSalesForDate
DELIMITER //
CREATE PROCEDURE `GetSalesForDate`(IN saleDate DATE)
BEGIN
    SELECT fi.itemName, SUM(bd.detailQuantity) AS totalQuantity, bd.itemPrice, SUM(bd.detailQuantity * bd.itemPrice) AS totalAmount
    FROM bill_details bd
    INNER JOIN bill b ON bd.billID = b.billID
    INNER JOIN food_item fi ON bd.itemID = fi.itemID
    WHERE DATE(b.billDate) = saleDate
    GROUP BY fi.itemName
    ORDER BY totalQuantity DESC;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.UpdateCustomerPointsPLus
DELIMITER //
CREATE PROCEDURE `UpdateCustomerPointsPLus`(
    IN customerPhoneNumber VARCHAR(50),
    IN additionalPoints INT
)
BEGIN
    UPDATE customers
    SET customerPoint = customerPoint + additionalPoints
    WHERE customerPhone = customerPhoneNumber;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.updateDiscount
DELIMITER //
CREATE PROCEDURE `updateDiscount`(
    IN p_discountName VARCHAR(50),
    IN p_discountQuantity INT,
    IN p_discountValue INT,
    IN p_discountStartDay DATE,
    IN p_discountEndDay DATE,
    IN p_discountID VARCHAR(50)
)
BEGIN
    UPDATE discount
    SET
        discountName = p_discountName,
        discountQuantity = p_discountQuantity,
        discountValue = p_discountValue,
        discountStartDay = p_discountStartDay,
        discountEndDay = p_discountEndDay
    WHERE
        discountID = p_discountID;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.UpdateFoodItem

-- Dumping structure for procedure coffeeshop.ViewBillsByDate
DELIMITER //
CREATE PROCEDURE `ViewBillsByDate`(IN search_date VARCHAR(20))
BEGIN
  -- Truy vấn các bill với ngày truyền vào
  SET @query = CONCAT("SELECT * FROM bill WHERE billDate LIKE '%", search_date, "%'");
  PREPARE statement FROM @query;
  EXECUTE statement;
  DEALLOCATE PREPARE statement;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.ViewTotalBillAmountByDate
DELIMITER //
CREATE PROCEDURE `ViewTotalBillAmountByDate`(IN search_date VARCHAR(20), OUT total_amount INT)
BEGIN
  -- Truy vấn tổng tiền của các bill với ngày truyền vào
  SET @query = CONCAT("SELECT SUM(billPrice) INTO @total_amount FROM bill WHERE billDate LIKE '%", search_date, "%'");
  PREPARE statement FROM @query;
  EXECUTE statement;
  DEALLOCATE PREPARE statement;
  
  -- Gán giá trị tổng tiền vào biến OUT
  SET total_amount = @total_amount;
END//
DELIMITER ;

-- Dumping structure for trigger coffeeshop.check_duplicate_category
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER check_duplicate_category
AFTER INSERT ON category
FOR EACH ROW
BEGIN
    DECLARE count INT;
    
    SELECT COUNT(*) INTO count
    FROM category
    WHERE categoryName = NEW.categoryName;
    
    IF count > 1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Duplicate category name';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.check_duplicate_customerPhone
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER check_duplicate_customerPhone
AFTER INSERT ON customers
FOR EACH ROW
BEGIN
    DECLARE count INT;
    
    SELECT COUNT(*) INTO count
    FROM customers
    WHERE customerPhone = NEW.customerPhone;
    
    IF count > 1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Duplicate customer phone';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.check_item_price_INSERT
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER check_item_price_INSERT
AFTER UPDATE ON food_item
FOR EACH ROW
BEGIN
    IF NEW.itemPrice <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'itemPrice must be greater than 0';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.check_item_price_UPDATE
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER check_item_price_UPDATE
AFTER UPDATE ON food_item
FOR EACH ROW
BEGIN
    IF NEW.itemPrice <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'itemPrice must be greater than 0';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.check_start_date
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_start_date` AFTER INSERT ON `customers` FOR EACH ROW BEGIN
    IF NEW.customerStartDate <= NEW.customerDOB THEN
      SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'customerStartDate must be earlier than customerDOB';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.set_default_values
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER set_default_values
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    SET NEW.customerPoint = 0;
    SET NEW.customerRate = 'Member';
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.tg_customerbirthday_check_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER tg_customerbirthday_check_insert
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
	 DECLARE v_startdate DATE;
	 SELECT customerStartDate INTO v_startdate
	 FROM customers
	 WHERE customerID = NEW.customerID;
	 
    IF (NEW.customerDOB > CURDATE() OR NEW.customerDOB > v_startdate) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer birthday!';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.tg_customerphone_check_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER tg_customerphone_check_insert
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    IF (NEW.customerPhone NOT REGEXP '^[0-9]{10}$') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer phone!';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.tg_customerphone_check_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER tg_customerphone_check_update
BEFORE UPDATE ON customers
FOR EACH ROW
BEGIN
    IF (NEW.customerPhone NOT REGEXP '^[0-9]{10}$') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer phone!';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger coffeeshop.tg_customerrate_check_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER tg_customerrate_check_insert
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    IF (NEW.customerRate NOT IN ('Member', 'Silver', 'Gold')) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer rate!';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

