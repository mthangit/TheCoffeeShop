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