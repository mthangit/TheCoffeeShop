package model;

public class EmployeeModel {
    private int id;
    private String employeeName;
    private String employeePhone;
    private String employeeAddress;
    private String employeeCID;
    private String employeeHometown;
    private String employeeType;
    private String employeePosition;
    private String employeeTaxID;
    private String employeeContractID;
    private String employeeEmail;
    private String status;
    public EmployeeModel() {
    }

    public EmployeeModel(int id, String employeeName, String employeePhone, String employeeAddress, String employeeCID,
            String employeeHometown, String employeeType, String employeePosition,
            String employeeEmail) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeAddress = employeeAddress;
        this.employeeCID = employeeCID;
        this.employeeHometown = employeeHometown;
        this.employeeType = employeeType;
        this.employeePosition = employeePosition;
        this.employeeTaxID = "null";
        this.employeeContractID = "null";
        this.employeeEmail = employeeEmail;
        this.status = "Đang làm việc";
    }

    public EmployeeModel(String employeeName, String employeePhone, String employeeAddress, String employeeCID,
            String employeeHometown, String employeeType, String employeePosition, String employeeTaxID,
            String employeeContractID, String employeeEmail) {
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeAddress = employeeAddress;
        this.employeeCID = employeeCID;
        this.employeeHometown = employeeHometown;
        this.employeeType = employeeType;
        this.employeePosition = employeePosition;
        this.employeeTaxID = employeeTaxID;
        this.employeeContractID = employeeContractID;
        this.employeeEmail = employeeEmail;
        this.status = "Đang làm việc";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeCID() {
        return employeeCID;
    }

    public void setEmployeeCID(String employeeCID) {
        this.employeeCID = employeeCID;
    }

    public String getEmployeeHometown() {
        return employeeHometown;
    }

    public void setEmployeeHometown(String employeeHometown) {
        this.employeeHometown = employeeHometown;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getEmployeeTaxID() {
        return employeeTaxID;
    }

    public void setEmployeeTaxID(String employeeTaxID) {
        this.employeeTaxID = employeeTaxID;
    }

    public String getEmployeeContractID() {
        return employeeContractID;
    }

    public void setEmployeeContractID(String employeeContractID) {
        this.employeeContractID = employeeContractID;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeStatus() {
        return status;
    }

}   
