/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.BillModel;
import model.CustomerModel;

/**
 *
 * @author HUY THUC
 */
public class BillDao {
    public static int getBillID() {
        int id = 1;
        try {
            ResultSet rs = DbOperations.getData("select max(billID) from bill");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }

    public static void save(BillModel bill) {
        String customerPhone = bill.getCustomerPhone();
        int employeeID = UserDAO.getUserLogin().getEmployeeID();
        int customerID = CustomerDAO.getCustomerIDByPhone(customerPhone);
        String query = "insert into bill values('" + bill.getBillID() + "','" + bill.getBillDate() + "','"
                + bill.getCustomerName() + "','" + bill.getBillPrice() + "','" + bill.getCustomerPhone() + "', '" + employeeID + "', '" + customerID + "')";
        if (customerPhone.equals("")) {
            query = "insert into bill values('" + bill.getBillID() + "','" + bill.getBillDate() + "','"
                    + bill.getCustomerName() + "','" + bill.getBillPrice() + "',NULL, '" + employeeID + "', NULL)";
        }
        DbOperations.setDataOrDelete(query, "Thêm bill thành công");
    }

    public static void updateCustomerPoint(BillModel bill) {
        String query = "UPDATE customers SET customerPoint = customerPoint +(" + bill.getBillPrice()
                + "/10000)  WHERE customers.customerPhone  = '" + bill.getCustomerPhone() + "' ";
        DbOperations.setDataOrDelete(query, "");
        String query1 = "CALL  SP_UpdateRate('" + bill.getCustomerPhone() + "')";
        DbOperations.setDataOrDelete(query1, "");
    }

    public static ArrayList<BillModel> getAllRecordByINC(String date) {
        ArrayList<BillModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from bill where billDate like'%" + date + "%'");
            while (rs.next()) {
                BillModel bill = new BillModel();
                bill.setBillID(rs.getInt("billID"));
                bill.setCustomerName(rs.getString("customerName"));
                bill.setCustomerPhone(rs.getString("customerPhone"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setBillPrice(rs.getInt("billPrice"));

                arrayList.add(bill);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    public static ArrayList<BillModel> getAllRecordByNameAndINC(String date, String name) {
        ArrayList<BillModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from bill where billDate like'%" + date + "%' and customerName like'%" + name + "%'");
            while (rs.next()) {
                BillModel bill = new BillModel();
                bill.setBillID(rs.getInt("billID"));
                bill.setCustomerName(rs.getString("customerName"));
                bill.setCustomerPhone(rs.getString("customerPhone"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setBillPrice(rs.getInt("billPrice"));

                arrayList.add(bill);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static ArrayList<BillModel> getAllRecordByDESC(String date) {
        ArrayList<BillModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations
                    .getData("select *from bill where billDate like'%" + date + "%' order by billID DESC");
            while (rs.next()) {
                BillModel bill = new BillModel();
                bill.setBillID(rs.getInt("billID"));
                bill.setCustomerName(rs.getString("customerName"));
                bill.setCustomerPhone(rs.getString("customerPhone"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setBillPrice(rs.getInt("billPrice"));

                arrayList.add(bill);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    public static ArrayList<BillModel> getAllRecordByNameAndDESC(String date, String name) {
        ArrayList<BillModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations
                    .getData("select *from bill where billDate like'%" + date + "%' and customerName like'%" + name + "%' order by billID DESC");
            while (rs.next()) {
                BillModel bill = new BillModel();
                bill.setBillID(rs.getInt("billID"));
                bill.setCustomerName(rs.getString("customerName"));
                bill.setCustomerPhone(rs.getString("customerPhone"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setBillPrice(rs.getInt("billPrice"));

                arrayList.add(bill);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static ArrayList<BillModel> getAllBill(){
        ArrayList<BillModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from bill");
            while (rs.next()) {
                BillModel bill = new BillModel();
                bill.setBillID(rs.getInt("billID"));
                bill.setCustomerName(rs.getString("customerName"));
                bill.setCustomerPhone(rs.getString("customerPhone"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setBillPrice(rs.getInt("billPrice"));

                arrayList.add(bill);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static void deleteBillByID(int id) {
        BillDetailDAO.deleteBillDetailByID(id);
        String query = "delete from bill where billID = '" + id + "'";
        DbOperations.setDataOrDelete(query, "Xóa bill thành công");
        }

    public static ArrayList<BillModel> getBillByName(String name){
        ArrayList<BillModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from bill where customerName like'%" + name + "%'");
            while (rs.next()) {
                BillModel bill = new BillModel();
                bill.setBillID(rs.getInt("billID"));
                bill.setCustomerName(rs.getString("customerName"));
                bill.setCustomerPhone(rs.getString("customerPhone"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setBillPrice(rs.getInt("billPrice"));

                arrayList.add(bill);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    // public static ArrayList<BillModel> getAllRecordByPhone(String phone){
    // ArrayList<BillModel> arrayList = new ArrayList<>();
    // try {
    // ResultSet rs = DbOperations.getData("select *from bill where billDate
    // like'%"+date+"%' order by billID DESC");
    // while(rs.next()){
    // BillModel bill = new BillModel();
    // bill.setBillID(rs.getInt("billID"));
    // bill.setCustomerName(rs.getString("customerName"));
    // bill.setCustomerPhone(rs.getString("customersPhone"));
    // bill.setBillDate(rs.getDate("billDate"));
    // bill.setBillPrice(rs.getInt("billPrice"));
    //
    // arrayList.add(bill);
    //
    // }
    // }
    // catch(Exception e){
    // JOptionPane.showMessageDialog(null, e);
    // }
    // return arrayList;

    // }

}
