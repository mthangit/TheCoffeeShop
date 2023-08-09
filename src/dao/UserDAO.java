package dao;

import model.UserModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private static Connection connection = ConnectionProvider.getConnection();
    private static UserModel userLogin = null;
    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public static void setUser(UserDAO userDAO) {
        instance = userDAO;
    }

    public static UserModel getAccessByUserName(String username) {
        UserModel userLogin = null;
        try {
            String query = "SELECT * FROM system_access WHERE sysUsername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("sysPassword");
                String type = resultSet.getString("type");
                int id = resultSet.getInt("employeeID");
                userLogin = new UserModel(username, password, type, id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return userLogin;
    }

    public static boolean checkLogin(String username, String password) {
        userLogin = getAccessByUserName(username);
        if (userLogin == null) {
            return false;
        } else
            return userLogin.getPassword().equals(password);
    }

    public static boolean isEmployee() {
        return userLogin.getType().equals("employee");
    }

    public static boolean isManager() {
        return userLogin.getType().equals("manager");
    }

    public static boolean addUser(UserModel user) {
        try {
            String query = "INSERT INTO system_access VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getType());
            statement.setInt(4, user.getEmployeeID());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static ArrayList<UserModel> getAllUser() {
        ArrayList<UserModel> listUser = new ArrayList<>();
        try {
            String query = "SELECT * FROM system_access";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("sysUsername");
                String password = resultSet.getString("sysPassword");
                String type = resultSet.getString("type");
                int employeeID = resultSet.getInt("employeeID");
                listUser.add(new UserModel(username, password, type, employeeID));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return listUser;
    }

    public static UserModel getUserByName(String username) {
        UserModel user = null;
        try {
            String query = "SELECT * FROM system_access WHERE sysUsername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("sysPassword");
                String type = resultSet.getString("type");
                int employeeID = resultSet.getInt("employeeID");
                user = new UserModel(username, password, type, employeeID);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }

    public static Object getType() {
        return userLogin.getType();
    }

    public static ArrayList<UserModel> getListUserByName(String name) {
        ArrayList<UserModel> listUser = new ArrayList<>();
        try {
            String query = "SELECT * FROM system_access WHERE sysUsername LIKE '%" + name + "%'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("sysUsername");
                String password = resultSet.getString("sysPassword");
                String type = resultSet.getString("type");
                int employeeID = resultSet.getInt("employeeID");
                listUser.add(new UserModel(username, password, type, employeeID));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return listUser;
    }

    public static String getEmployeeNameByUserName(String userName) {
        String name = "";
        int id = 0;
        try {
            String query = "SELECT * FROM system_access WHERE sysUsername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("employeeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "SELECT * FROM employees WHERE employeeID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("employeeName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static boolean updateUser(UserModel user) {
        try {
            String query = "UPDATE system_access SET sysPassword = ?, type = ? WHERE sysUsername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getType());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean deleteUser(String username) {
        try {
            String query = "DELETE FROM system_access WHERE sysUsername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static UserModel getUserLogin() {
        return userLogin;
    }

    public static boolean isExisted(int id){
        try {
            String query = "SELECT * FROM system_access WHERE employeeID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
