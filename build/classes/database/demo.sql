-- Kiểm tra ngày sinh khách hàng
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
DELIMITER;

-- Kiểm tra số điện thoại khách hàng
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

DELIMITER //
CREATE TRIGGER tg_customerphone_check_update
BEFORE UPDATE ON customers
FOR EACH ROW
BEGIN
    IF (NEW.customerPhone NOT REGEXP '^[0-9]{10}$') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer phone!';
    END IF;
END//
DELIMITER;

--1Tự động setrank và point sau khi thêm mới khách hàng
DELIMITER //
CREATE TRIGGER set_default_values
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    SET NEW.customerPoint = 0;
    SET NEW.customerRate = 'Member';
END//
DELIMITER ;
 
---2     
DELIMITER //
CREATE TRIGGER check_start_date
AFTER INSERT ON customers
FOR EACH ROW
BEGIN
    IF NEW.customerStartDate <= NEW.customerDOB THEN
      SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'customerStartDate must be earlier than customerDOB';
    END IF;
END//
DELIMITER ;

---3 
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
--
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

--
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


----
DELIMITER //
CREATE PROCEDURE AddCategory (
    IN categoryName VARCHAR(20)
)
BEGIN
    IF categoryName IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category name is required';
    ELSE
        INSERT INTO category (categoryName)
        VALUES (categoryName);
    END IF;
END//
DELIMITER ;
---CALL AddCategory('New Category');
--Các bước thực hiện: 
--[1]  Nhập thông tin bộ dữ liệu category
--[2]  Kiểm tra thông tin chỉ có 1 thuộc tính là tên category
--khoản. 
--[3.1] Không thỏa, thông báo lỗi. 
--[3.2] Chấm dứt stored procedure. 
--[3]  Thêm bộ dữ liệu mới cho bảng category


DELIMITER //

CREATE PROCEDURE UpdateFoodItem(IN p_itemID INT, IN p_newPrice VARCHAR(50), IN p_newCategoryName VARCHAR(20), IN p_newItemName VARCHAR(40))
BEGIN
    -- START TRANSACTION;
    
    -- Đảm bảo tính nhất quán và tránh lost update bằng cách sử dụng isolation level SERIALIZABLE
    -- SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    
    -- Cập nhật giá, tên danh mục và tên món ăn cho food item
    UPDATE food_item
    SET itemPrice = p_newPrice, categoryName = p_newCategoryName, itemName = p_newItemName
    WHERE itemID = p_itemID;
    
    -- COMMIT;
END //

DELIMITER ;

----

-------------------------------------------------------------
---- unrepeatable read--
-- khi sesion A đang truy vấn doanh thu ngày hôm nay thì thèn B lúc này lại thêm một hóa đơn cho khách hàng thì sau đó thèn A sẽ thấy sự thay đổi của
-- doanh thu khi thực hiện truy vấn lần tiếp theo--
-- hướng giải quyết dùng seraial lizable để ngăn ngừa thằng B in Bill cho đến khi  thèn A commit vào hệ thống
DELIMITER //

CREATE PROCEDURE `AddBill`(
  IN p_billDate DATE,
  IN p_customerName VARCHAR(50),
  IN p_billPrice INT,
  IN p_customersPhone VARCHAR(50)
)
BEGIN
  INSERT INTO `bill` (`billDate`, `customerName`, `billPrice`, `customersPhone`)
  VALUES (p_billDate, p_customerName, p_billPrice, p_customersPhone);
END //

DELIMITER ;
----
----


--- PHAN TOM READ

---
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
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE `ViewBillsByDate`(IN search_date VARCHAR(20))
BEGIN
  -- Truy vấn các bill với ngày truyền vào
  SET @query = CONCAT("SELECT * FROM bill WHERE billDate LIKE '%", search_date, "%'");
  PREPARE statement FROM @query;
  EXECUTE statement;
  DEALLOCATE PREPARE statement;
END //

DELIMITER ;
---SESSION A






---- non repeatable

---ssA
DELIMITER //

CREATE PROCEDURE GetBillPriceByMonthYear(IN inputMonth INT, IN inputYear INT)
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
END //

DELIMITER ;

--CALL GetBillPriceByMonthYear(4,2023);


-- DELIMITER //

CREATE PROCEDURE addDiscount(
    IN p_discountID VARCHAR(20),
    IN p_discountName VARCHAR(50),
    IN p_discountQuantity INT,
    IN p_discountValue INT,
    IN p_discountStartDay DATE,
    IN p_discountEndDay DATE
)
BEGIN
    INSERT INTO discount (discountID, discountName, discountQuantity, discountValue, discountStartDay, discountEndDay)
    VALUES (p_discountID, p_discountName, p_discountQuantity, p_discountValue, p_discountStartDay, p_discountEndDay);
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE updateDiscount(
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
END //

DELIMITER ;
---
DELIMITER //

CREATE PROCEDURE GetSalesForDate(IN saleDate DATE)
BEGIN
    SELECT fi.itemName, SUM(bd.detailQuantity) AS totalQuantity, bd.itemPrice, SUM(bd.detailQuantity * bd.itemPrice) AS totalAmount
    FROM bill_details bd
    INNER JOIN bill b ON bd.billID = b.billID
    INNER JOIN food_item fi ON bd.itemID = fi.itemID
    WHERE DATE(b.billDate) = saleDate
    GROUP BY fi.itemName
    ORDER BY totalQuantity DESC;
END //

DELIMITER ;


----
DELIMITER //

CREATE PROCEDURE GetBestSellingDrinksInTimeRange(IN startDate DATE, IN endDate DATE)
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
END //

DELIMITER ;

---
DELIMITER //

CREATE PROCEDURE GetBestSellingDrinks()
BEGIN
    SELECT fi.itemName, fi.itemPrice, SUM(bd.detailQuantity) AS totalQuantity
    FROM bill_details bd
    INNER JOIN food_item fi ON bd.itemID = fi.itemID
    GROUP BY bd.itemID
    ORDER BY totalQuantity DESC, fi.itemPrice DESC;
END //

DELIMITER 
