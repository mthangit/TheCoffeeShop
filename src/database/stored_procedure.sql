

-- Procedure tự động giảm số lượng voucher khi sử dụng
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApplyDiscount`(IN p_name VARCHAR(255))
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
END$$
DELIMITER ;

-- Procedure tự động cập nhật điểm cho khách hàng
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_rate`(IN customer_phone VARCHAR(255))
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
END$$
DELIMITER ;

-- Procedure thêm khách hàng
DELIMITER //

CREATE PROCEDURE add_customer(
  IN p_customerName VARCHAR(50),
  IN p_customerDOB DATE,
  IN p_customerPhone VARCHAR(20),
  IN p_customerAddress VARCHAR(50),
  IN p_customerEmail VARCHAR(50)
)
BEGIN
  INSERT INTO customers (customerName, customerDOB, customerPhone, customerAddress, customerEmail, customerStartDate, customerRate, customerPoint)
  VALUES (p_customerName, p_customerDOB, p_customerPhone, p_customerAddress, p_customerEmail, CURDATE(), 'Member', 0);
  
  SET @newCustomerID = LAST_INSERT_ID();
  
  SELECT @newCustomerID AS newCustomerID;
END //

DELIMITER ;

-- Trigger tự động tăng ID cho khách hàng
DELIMITER //

CREATE TRIGGER increment_customerID
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
  IF NEW.customerID IS NULL THEN
    SET NEW.customerID = (SELECT COALESCE(MAX(customerID), 0) + 1 FROM customers);
  END IF;
END //

DELIMITER ;
