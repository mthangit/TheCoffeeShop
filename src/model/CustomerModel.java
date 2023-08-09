package model;

import java.sql.Date;
// customerRate: "Member", "Silver", "Gold"
public class CustomerModel {
    private int customerID = 0;
    private String customerName;
    private Date customerDOB;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private Date customerStartDate = new Date(System.currentTimeMillis());
    private String customerRate = "Member";
    private int customerPoint = 0;

    public CustomerModel(){
    }
    public CustomerModel(int customerID, String customerName, Date customerDOB, String customerAddress, String customerPhone, String customerEmail, Date customerStartDate, String customerRate, int customerPoint) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerDOB = customerDOB;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerStartDate = customerStartDate;
        this.customerRate = customerRate;
        this.customerPoint = customerPoint;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName= customerName;
    }
    public Date getCustomerDOB() {
        return customerDOB;
    }
    public void setCustomerDOB(Date customerDOB) {
        this.customerDOB = customerDOB;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress= customerAddress;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail= customerEmail;
    }
    public Date getCustomerStartDate() {
        return customerStartDate;
    }
    public void setCustomerStartDate(Date customerStartDate) {
        this.customerStartDate = customerStartDate;
    }
    public String getCustomerRate() {
        return customerRate;
    }
    public void setCustomerRate(String customerRate) {
        this.customerRate = customerRate;
    }
    public int getCustomerPoint() {
        return customerPoint;
    }
    public void setCustomerPoint(int customerPoint) {
        this.customerPoint = customerPoint;
    }
    
}
