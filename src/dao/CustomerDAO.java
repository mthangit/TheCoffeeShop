package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import model.CustomerModel;

public class CustomerDAO {
    private static Connection connection = ConnectionProvider.getConnection();
    public static boolean addCustomer(CustomerModel customer) {
        try {
            String query = "Call SP_AddCustomer(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getCustomerName());
            statement.setDate(2, customer.getCustomerDOB());
            statement.setString(3, customer.getCustomerAddress());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getCustomerEmail());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static ArrayList<CustomerModel> getListCustomer() {
        ArrayList<CustomerModel> listCustomer = new ArrayList<CustomerModel>();
        try {
            String query = "SELECT * FROM customers where customerPhone != 'NULL'";
            java.sql.ResultSet resultSet = DbOperations.getData(query);
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String customerName = resultSet.getString("customerName");
                java.sql.Date customerDOB = resultSet.getDate("customerDOB");
                String customerAddress = resultSet.getString("customerAddress");
                String customerPhone = resultSet.getString("customerPhone");
                String customerEmail = resultSet.getString("customerEmail");
                java.sql.Date customerStartDate = resultSet.getDate("customerStartDate");
                String customerRate = resultSet.getString("customerRate");
                int customerPoint = resultSet.getInt("customerPoint");
                CustomerModel customer = new CustomerModel(customerID, customerName, customerDOB, customerAddress,
                        customerPhone, customerEmail, customerStartDate, customerRate, customerPoint);
                listCustomer.add(customer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return listCustomer;
    }
    public static CustomerModel getCustomerByPhone(String phone){
        CustomerModel customer = null;
        try {
            String query = "SELECT * FROM customers WHERE customerPhone = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, phone);
            java.sql.ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String customerName = resultSet.getString("customerName");
                java.sql.Date customerDOB = resultSet.getDate("customerDOB");
                String customerAddress = resultSet.getString("customerAddress");
                String customerPhone = resultSet.getString("customerPhone");
                String customerEmail = resultSet.getString("customerEmail");
                java.sql.Date customerStartDate = resultSet.getDate("customerStartDate");
                String customerRate = resultSet.getString("customerRate");
                int customerPoint = resultSet.getInt("customerPoint");
                customer = new CustomerModel(customerID, customerName, customerDOB, customerAddress,
                        customerPhone, customerEmail, customerStartDate, customerRate, customerPoint);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }

    public static ArrayList<CustomerModel> getListCustomerByPhone(String phone){
        ArrayList<CustomerModel> listCustomer = new ArrayList<CustomerModel>();
        String query = "SELECT * FROM customers WHERE customerPhone like '%" + phone + "%'";
        ResultSet resultSet = DbOperations.getData(query);
        try {
            while (resultSet.next()){
                int customerID = resultSet.getInt("customerID");
                String customerName = resultSet.getString("customerName");
                java.sql.Date customerDOB = resultSet.getDate("customerDOB");
                String customerAddress = resultSet.getString("customerAddress");
                String customerPhone = resultSet.getString("customerPhone");
                String customerEmail = resultSet.getString("customerEmail");
                java.sql.Date customerStartDate = resultSet.getDate("customerStartDate");
                String customerRate = resultSet.getString("customerRate");
                int customerPoint = resultSet.getInt("customerPoint");
                CustomerModel customer = new CustomerModel(customerID, customerName, customerDOB, customerAddress,
                        customerPhone, customerEmail, customerStartDate, customerRate, customerPoint);
                listCustomer.add(customer);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listCustomer;
    }

    public static CustomerModel getCustomerByID(int id){
        CustomerModel customer = null;
        try {
            String query = "SELECT * FROM customers WHERE customerID = ? and customerStartDate != 'NULL'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            java.sql.ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String customerName = resultSet.getString("customerName");
                java.sql.Date customerDOB = resultSet.getDate("customerDOB");
                String customerAddress = resultSet.getString("customerAddress");
                String customerPhone = resultSet.getString("customerPhone");
                String customerEmail = resultSet.getString("customerEmail");
                java.sql.Date customerStartDate = resultSet.getDate("customerStartDate");
                String customerRate = resultSet.getString("customerRate");
                int customerPoint = resultSet.getInt("customerPoint");
                customer = new CustomerModel(customerID, customerName, customerDOB, customerAddress,
                        customerPhone, customerEmail, customerStartDate, customerRate, customerPoint);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }
    // public static boolean removeCustomerDiscount(int ID) {
    //     try {
    //         String query = "Delete from discount where customerID = ?";
    //         PreparedStatement statement = connection.prepareStatement(query);
    //         statement.setInt(1, ID);
    //         statement.executeUpdate();
    //         statement.close();
    //         return true;
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    public static boolean removeCustomer(CustomerModel customer) {
        try {
            String query = "update customers set customerPoint = 0, customerPhone = NULL where customerID = ?";
            PreparedStatement statement1 = connection.prepareStatement(query);
            statement1.setInt(1, customer.getCustomerID());
            statement1.executeUpdate();
            statement1.close();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static String getCustomeRateByPhone(String phone){
        String rate = "";

        return rate;
    }

    public static boolean updateCustomer(CustomerModel customer){
        try {
            String query = "UPDATE customers SET customerName = ?, customerDOB = ?, customerAddress = ?, customerPhone = ?, customerEmail = ?, customerStartDate = ?, customerRate = ?, customerPoint = ? WHERE customerID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getCustomerName());
            statement.setDate(2, customer.getCustomerDOB());
            statement.setString(3, customer.getCustomerAddress());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getCustomerEmail());
            statement.setDate(6, customer.getCustomerStartDate());
            statement.setString(7, customer.getCustomerRate());
            statement.setInt(8, customer.getCustomerPoint());
            statement.setInt(9, customer.getCustomerID());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

        public static ArrayList<CustomerModel> getNameByPhone(String phone) {
        ArrayList<CustomerModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from customers where customerPhone like'%" + phone + "%'");
            while (rs.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setCustomerPhone(rs.getString("customerPhone"));
                customer.setCustomerName(rs.getString("customerName"));
                arrayList.add(customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static int getCustomerIDByPhone(String phone){
        int id = 0;
        try {
            ResultSet rs = DbOperations.getData("select *from customers where customerPhone ='" + phone + "'");
            while (rs.next()) {
                id = rs.getInt("customerID");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }

}
