

-- Dumping structure for function coffeeshop.GetNumberOfBills
DELIMITER //
CREATE FUNCTION `GetNumberOfBills`(`saleDate` DATE) RETURNS int(11)
BEGIN
    DECLARE numberOfBills INT;
    
    SELECT COUNT(*) INTO numberOfBills
    FROM bill
    WHERE DATE(billDate) = saleDate;
    
    RETURN numberOfBills;
END//
DELIMITER ;

-- Dumping structure for function coffeeshop.getTotalPriceByDay
DELIMITER //
CREATE FUNCTION `getTotalPriceByDay`(`saleDate` DATE) RETURNS int(11)
BEGIN
    DECLARE totalPrice INT;
    
    SELECT sum(billPrice) INTO totalPrice
    FROM bill
    WHERE DATE(billDate) = saleDate;
    
    RETURN totalPrice;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_AddBill
DELIMITER //
CREATE PROCEDURE `SP_AddBill`(
  IN p_billDate DATE,
  IN p_customerName VARCHAR(50),
  IN p_billPrice INT,
  IN p_customerPhone VARCHAR(20),
  IN p_employeeID INT,
  IN p_customerID INT
)
BEGIN
  INSERT INTO bill (billDate, customerName, billPrice, customerPhone, employeeID, customerID)
  VALUES (p_billDate, p_customerName, p_billPrice, p_customerPhone, p_employeeID, p_customerID);
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_AddCategory
DELIMITER //
CREATE PROCEDURE `SP_AddCategory`(
	IN `categoryName` VARCHAR(20)
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

-- Dumping structure for procedure coffeeshop.SP_AddCustomer
DELIMITER //
CREATE PROCEDURE `SP_AddCustomer`(
	IN `p_customerName` VARCHAR(50),
	IN `p_customerDOB` DATE,
	IN `p_customerAddress` VARCHAR(50),
	IN `p_customerPhone` VARCHAR(20),
	IN `p_customerEmail` VARCHAR(50)
)
BEGIN
  INSERT INTO customers (customerID, customerName, customerDOB, customerPhone, customerAddress, customerEmail, customerStartDate, customerRate, customerPoint)
  VALUES (NULL, p_customerName, p_customerDOB, p_customerPhone, p_customerAddress, p_customerEmail, CURDATE(), 'Member', 0);
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_AddDiscount
DELIMITER //
CREATE PROCEDURE `SP_AddDiscount`(IN `p_discountID` VARCHAR(20), IN `p_discountName` VARCHAR(50), IN `p_discountQuantity` INT, IN `p_discountValue` INT, IN `p_discountStartDay` DATE, IN `p_discountEndDay` DATE)
BEGIN
    INSERT INTO discount (discountID, discountName, discountQuantity, discountValue, discountStartDay, discountEndDay)
    VALUES (p_discountID, p_discountName, p_discountQuantity, p_discountValue, p_discountStartDay, p_discountEndDay);
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_ApplyDiscount
DELIMITER //
CREATE PROCEDURE `SP_ApplyDiscount`(
	IN `p_name` VARCHAR(255)
)
BEGIN
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
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_Deletecategory
DELIMITER //
CREATE PROCEDURE `SP_Deletecategory`(
	IN `p_categoryID` INT
)
BEGIN
  -- Biến cờ để đánh dấu xem categoryID có tồn tại hay không
  DECLARE categoryExists INT DEFAULT 0;
  
  -- Biến cờ để đánh dấu xem có food_item trong bill_detail của category hay không
  DECLARE hasFoodItemInBillDetail INT DEFAULT 0;
  
  -- Biến lưu trữ số lượng food_item đã xóa
  DECLARE deletedItemCount INT DEFAULT 0;
  
  -- Biến để kiểm tra xem có food_item nào được xóa hay không
  DECLARE foodItemsDeleted BOOLEAN DEFAULT FALSE;
  
  -- Tạo bảng tạm để lưu trữ danh sách food_item đã xóa
  CREATE TEMPORARY TABLE IF NOT EXISTS temp_DeletedFoodItems (
    itemID INT,
    itemName VARCHAR(40)
  );
  
  -- Tìm kiếm categoryID trong bảng category
  SELECT COUNT(*) INTO categoryExists
  FROM category
  WHERE categoryID = p_categoryID;
  
  -- Nếu không tìm thấy categoryID, thông báo và dừng procedure
  IF categoryExists = 0 THEN
    SELECT 'Không tìm thấy categoryID.' AS Message;
    SET foodItemsDeleted = FALSE;
  ELSE
    -- Kiểm tra xem food_item của category có phát sinh trong bill_detail hay không
    SELECT COUNT(*) INTO hasFoodItemInBillDetail
    FROM bill_detail
    WHERE itemID IN (
      SELECT itemID
      FROM food_item
      WHERE categoryID = p_categoryID
    );
    
    -- Nếu có food_item của category trong bill_detail, thông báo và dừng procedure
    IF hasFoodItemInBillDetail > 0 THEN
      SELECT 'Không thể xóa category vì có food_item của category trong bill_detail.' AS Message;
      SET foodItemsDeleted = FALSE;
    ELSE
      -- Đếm số lượng food_item sẽ bị xóa
      SELECT COUNT(*) INTO deletedItemCount
      FROM food_item
      WHERE categoryID = p_categoryID;
      
    
      -- Lấy danh sách food_item đã xóa
      IF deletedItemCount > 0 THEN
        INSERT INTO temp_DeletedFoodItems (itemID, itemName)
        SELECT itemID, itemName
        FROM food_item
        WHERE categoryID = p_categoryID;
        SET foodItemsDeleted = TRUE;
      END IF;
      
      -- Xóa food_item của category trong bảng food_item
      DELETE FROM food_item WHERE categoryID = p_categoryID;
      
      -- Kiểm tra xem có food_item nào được xóa hay không
      IF deletedItemCount > 0 THEN
        SELECT CONCAT('Đã xóa ', deletedItemCount, ' food_item của category ', p_categoryID, ' trong bảng food_item.') AS Message;
      ELSE
        SELECT 'Không có food_item để xóa.' AS Message;
      END IF;
      
      -- Hiển thị danh sách food_item đã xóa từ bảng tạm
      IF foodItemsDeleted THEN
        SELECT CONCAT('Đã xóa food_item ', itemID, ': ', itemName) AS DeletedItem
        FROM temp_DeletedFoodItems;
      END IF;
      
      -- Xóa bảng tạm
      DROP TEMPORARY TABLE IF EXISTS temp_DeletedFoodItems;
      
      -- Xóa category trong bảng category
      DELETE FROM category WHERE categoryID = p_categoryID;
      
      IF foodItemsDeleted THEN
        SELECT CONCAT('Đã xóa category ', p_categoryID, ' trong bảng category.') AS Message;
      END IF;
    END IF;
  END IF;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_GetBestSellingDrinksInTimeRange
DELIMITER //
CREATE PROCEDURE `SP_GetBestSellingDrinksInTimeRange`(
	IN `startDate` DATE,
	IN `endDate` DATE
)
BEGIN
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
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_GetBillPriceByMonth
DELIMITER //
CREATE PROCEDURE `SP_GetBillPriceByMonth`(
	IN `yearInput` INT
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

-- Dumping structure for procedure coffeeshop.SP_GetBillPriceByMonthYear
DELIMITER //
CREATE PROCEDURE `SP_GetBillPriceByMonthYear`(
	IN `inputMonth` INT,
	IN `inputYear` INT
)
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

-- Dumping structure for procedure coffeeshop.SP_GetSalesForDate
DELIMITER //
CREATE PROCEDURE `SP_GetSalesForDate`(
	IN `saleDate` DATE
)
BEGIN
    SELECT bd.itemName, SUM(bd.quantity) AS totalQuantity, fi.itemPrice, SUM(bd.totalPrice) AS totalAmount
    FROM bill_detail bd
    INNER JOIN bill b ON bd.billID = b.billID
    LEFT JOIN food_item fi ON bd.itemName = fi.itemName
    WHERE DATE(b.billDate) = saleDate
    GROUP BY bd.itemName
    ORDER BY totalQuantity DESC;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_ListEmployeesByStatus
DELIMITER //
CREATE PROCEDURE `SP_ListEmployeesByStatus`(
	IN `statusInput` VARCHAR(30)
)
BEGIN
    -- Khai báo biến và con trỏ
    DECLARE done INT DEFAULT FALSE;
    DECLARE empID INT;
    DECLARE empName VARCHAR(30);
    DECLARE empStatus VARCHAR(30);
    DECLARE cur CURSOR FOR SELECT employeeID, employeeName, status FROM employees WHERE status = statusInput;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Tạo bảng tạm để lưu trữ kết quả
    CREATE TEMPORARY TABLE IF NOT EXISTS tempEmployees (
        employeeID INT,
        employeeName VARCHAR(30),
        employeeStatus VARCHAR(30)
    );

    -- Mở con trỏ
    OPEN cur;

    -- Lặp qua các dòng trong con trỏ và chèn dữ liệu vào bảng tạm
    FETCH cur INTO empID, empName, empStatus;
    REPEAT
        IF NOT done THEN
            INSERT INTO tempEmployees (employeeID, employeeName, employeeStatus) VALUES (empID, empName, empStatus);
        END IF;
        FETCH cur INTO empID, empName, empStatus;
    UNTIL done END REPEAT;

    -- Đóng con trỏ
    CLOSE cur;

    -- Truy vấn dữ liệu từ bảng tạm và hiển thị kết quả
    SELECT employeeID, employeeName, employeeStatus FROM tempEmployees;

    -- Kiểm tra số dòng trả về
    SELECT CONCAT('Số nhân viên: ', COUNT(*)) AS 'Tổng số nhân viên' FROM tempEmployees;

    -- Xóa bảng tạm
    DROP TABLE IF EXISTS tempEmployees;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_UpdateCustomerPointsPLus
DELIMITER //
CREATE PROCEDURE `SP_UpdateCustomerPointsPLus`(
	IN `customerPhoneNumber` VARCHAR(50),
	IN `additionalPoints` INT
)
BEGIN
    UPDATE customers
    SET customerPoint = customerPoint + additionalPoints
    WHERE customerPhone = customerPhoneNumber;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_UpdateDiscount
DELIMITER //
CREATE PROCEDURE `SP_UpdateDiscount`(
	IN `p_discountName` VARCHAR(50),
	IN `p_discountQuantity` INT,
	IN `p_discountValue` INT,
	IN `p_discountStartDay` DATE,
	IN `p_discountEndDay` DATE,
	IN `p_discountID` VARCHAR(50)
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

-- Dumping structure for procedure coffeeshop.SP_UpdateFoodItem
DELIMITER //
CREATE PROCEDURE `SP_UpdateFoodItem`(
	IN `p_itemID` INT,
	IN `p_newPrice` VARCHAR(50),
	IN `p_newCategoryName` VARCHAR(20),
	IN `p_newItemName` VARCHAR(40)
)
BEGIN
    -- START TRANSACTION;
    
    -- Đảm bảo tính nhất quán và tránh lost update bằng cách sử dụng isolation level SERIALIZABLE
    -- SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    
    -- Cập nhật giá, tên danh mục và tên món ăn cho food item
    UPDATE food_item
    SET itemPrice = p_newPrice, categoryName = p_newCategoryName, itemName = p_newItemName
    WHERE itemID = p_itemID;
    
    -- COMMIT;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_UpdateFoodItemCategoryID
DELIMITER //
CREATE PROCEDURE `SP_UpdateFoodItemCategoryID`(
	IN `categoryNameParam` VARCHAR(50)
)
BEGIN
    UPDATE food_item fi
    JOIN category c ON fi.categoryName = c.categoryName
    SET fi.categoryID = c.categoryID
    WHERE c.categoryName = categoryNameParam;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_UpdateItemId
DELIMITER //
CREATE PROCEDURE `SP_UpdateItemId`(
	IN `itemNameParam` VARCHAR(50)
)
BEGIN
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
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_UpdateRate
DELIMITER //
CREATE PROCEDURE `SP_UpdateRate`(
	IN `customer_phone` VARCHAR(255)
)
BEGIN
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
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_ViewBillsByDate
DELIMITER //
CREATE PROCEDURE `SP_ViewBillsByDate`(
	IN `search_date` VARCHAR(20)
)
BEGIN
  -- Truy vấn các bill với ngày truyền vào
  SET @query = CONCAT("SELECT * FROM bill WHERE billDate LIKE '%", search_date, "%'");
  PREPARE statement FROM @query;
  EXECUTE statement;
  DEALLOCATE PREPARE statement;
END//
DELIMITER ;

-- Dumping structure for procedure coffeeshop.SP_ViewTotalBillAmountByDate
DELIMITER //
CREATE PROCEDURE `SP_ViewTotalBillAmountByDate`(
	IN `search_date` VARCHAR(20),
	OUT `total_amount` INT
)
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

