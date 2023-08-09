package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmployeeModel;

public class EmployeeDAO {

    public static ArrayList<EmployeeModel> getListEmployees() {
        ArrayList<EmployeeModel> arrayList = new ArrayList<>();
        try {
            String query = "Select * from employees";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(rs.getInt("employeeID"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setEmployeePhone(rs.getString("employeePhone"));
                employee.setEmployeeAddress(rs.getString("employeeAddress"));
                employee.setEmployeeCID(rs.getString("employeeCID"));
                employee.setEmployeeHometown(rs.getString("employeeHometown"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setEmployeePosition(rs.getString("employeePosition"));
                employee.setEmployeeTaxID(rs.getString("employeeTaxID"));
                employee.setEmployeeContractID(rs.getString("employeeContractID"));
                employee.setEmployeeEmail(rs.getString("employeeEmail"));
                employee.setStatus(rs.getString("status"));
                arrayList.add(employee);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }
    public static ArrayList<EmployeeModel> getListEmployeeNotHaveAccount() {
        ArrayList<EmployeeModel> arrayList = new ArrayList<>();
        try {
            String query = "Select * from employees where employeeID not in (select employeeID from system_access)";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(rs.getInt("employeeID"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setEmployeePhone(rs.getString("employeePhone"));
                employee.setEmployeeAddress(rs.getString("employeeAddress"));
                employee.setEmployeeCID(rs.getString("employeeCID"));
                employee.setEmployeeHometown(rs.getString("employeeHometown"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setEmployeePosition(rs.getString("employeePosition"));
                employee.setEmployeeTaxID(rs.getString("employeeTaxID"));
                employee.setEmployeeContractID(rs.getString("employeeContractID"));
                employee.setEmployeeEmail(rs.getString("employeeEmail"));
                employee.setStatus(rs.getString("status"));
                arrayList.add(employee);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    public static ArrayList<EmployeeModel> getListEmployeesByType(String type) {
        ArrayList<EmployeeModel> arrayList = new ArrayList<>();
        try {
            String query = "Select * from employees where employeeType = '" + type + "'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(rs.getInt("employeeID"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setEmployeePhone(rs.getString("employeePhone"));
                employee.setEmployeeAddress(rs.getString("employeeAddress"));
                employee.setEmployeeCID(rs.getString("employeeCID"));
                employee.setEmployeeHometown(rs.getString("employeeHometown"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setEmployeeContractID(rs.getString("employeeContractID"));
                employee.setEmployeeEmail(rs.getString("employeeEmail"));
                arrayList.add(employee);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<EmployeeModel> getListEMployeeByName(String name) {
        ArrayList<EmployeeModel> arrayList = new ArrayList<>();
        try {
            String query = "Select * from employees where employeeName like '%" + name + "%'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(rs.getInt("employeeID"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setEmployeePhone(rs.getString("employeePhone"));
                employee.setEmployeeAddress(rs.getString("employeeAddress"));
                employee.setEmployeeCID(rs.getString("employeeCID"));
                employee.setEmployeeHometown(rs.getString("employeeHometown"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setEmployeeContractID(rs.getString("employeeContractID"));
                employee.setEmployeeEmail(rs.getString("employeeEmail"));
                arrayList.add(employee);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrayList;
    }

    public static EmployeeModel getEmployeeByID(int id){
        EmployeeModel employeeModel = new EmployeeModel();
        String query = "select * from employees where employeeID = " + id;
        ResultSet rs = DbOperations.getData(query);
        try {
            while(rs.next()){
                employeeModel.setId(rs.getInt("employeeID"));
                employeeModel.setEmployeeName(rs.getString("employeeName"));
                employeeModel.setEmployeePhone(rs.getString("employeePhone"));
                employeeModel.setEmployeeAddress(rs.getString("employeeAddress"));
                employeeModel.setEmployeeCID(rs.getString("employeeCID"));
                employeeModel.setEmployeeHometown(rs.getString("employeeHometown"));
                employeeModel.setEmployeeType(rs.getString("employeeType"));
                employeeModel.setEmployeePosition(rs.getString("employeePosition"));
                employeeModel.setEmployeeTaxID(rs.getString("employeeTaxID"));
                employeeModel.setEmployeeContractID(rs.getString("employeeContractID"));
                employeeModel.setEmployeeEmail(rs.getString("employeeEmail"));
                employeeModel.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return employeeModel;
    }



    public static int getMaxID() {
        int maxID = 0;
        ResultSet rs = DbOperations.getData("select max(employeeID) from employees");
        if (rs != null) {
            try {
                if (rs.next()) {
                    maxID = rs.getInt(1);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return maxID;
    }

    public static void updateID() {
        int maxID = getMaxID();
        String query = "ALTER TABLE employees AUTO_INCREMENT = " + (maxID + 1);
        DbOperations.setDataOrDelete(query, "update id thanh cong");
    }

    public static ArrayList<String> getEmployeePosition() throws SQLException {
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet rs = DbOperations.getData("select distinct employeePosition from employees");
        while (rs.next()) {
            arrayList.add(rs.getString("employeePosition"));
        }
        return arrayList;
    }

    public static boolean addEmployee(String id, String name, String phone, String cid, String address, String hometown,
            String email, String type, String position, String taxID, String contractID) {
        String query = "insert into employees values(" + id + ", '" + name + "', '" + phone + "', '" + address + "', '"
                + cid + "', '" + hometown + "', '" + type + "', '" + position + "', '" + taxID + "', '" + contractID
                + "', '" + email + "', 'Đang làm việc')";
        DbOperations.setDataOrDelete(query, "Thêm nhân viên thành công");
        return true;
    }

public static boolean updateEmployee(EmployeeModel employee) {
    String query = "UPDATE employees SET " +
        "employeeName = '" + employee.getEmployeeName() + "', " +
        "employeePhone = '" + employee.getEmployeePhone() + "', " +
        "employeeAddress = '" + employee.getEmployeeAddress() + "', " +
        "employeeCID = '" + employee.getEmployeeCID() + "', " +
        "employeeHometown = '" + employee.getEmployeeHometown() + "', " +
        "employeeType = '" + employee.getEmployeeType() + "', " +
        "employeePosition = '" + employee.getEmployeePosition() + "', " +
        "employeeTaxID = '" + employee.getEmployeeTaxID() + "', " +
        "employeeContractID = '" + employee.getEmployeeContractID() + "', " +
        "employeeEmail = '" + employee.getEmployeeEmail() + "', " +
        "status = '" + employee.getEmployeeStatus() + "'" +
        "WHERE employeeID = " + employee.getId();
    DbOperations.setDataOrDelete(query, "Cập nhật nhân viên thành công");
    return true;
}
}
