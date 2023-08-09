/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.FoodItemModel;
import java.sql.*;

/**
 *
 * @author HUY THUC
 */
public class FoodItemDao {
    public static ArrayList<FoodItemModel> getAllRecords() {
        ArrayList<FoodItemModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from food_item");
            while (rs.next()) {
                FoodItemModel fooditem = new FoodItemModel();
                fooditem.setItemID(rs.getInt("itemID"));
                fooditem.setItemName(rs.getString("itemName"));
                fooditem.setCategoryName(rs.getString("categoryName"));
                fooditem.setItemPrice(rs.getString("itemPrice"));
                arrayList.add(fooditem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }

    public static FoodItemModel getFoodItemByName(String name) {
        FoodItemModel fooditem = new FoodItemModel();
        try {
            String query = "select *from food_item where itemName ='" + name + "'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                fooditem.setItemName(rs.getString(2));
                fooditem.setCategoryName(rs.getString(4));
                fooditem.setItemPrice(rs.getString(3));
                fooditem.setItemID(rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return fooditem;
    }

    public static ArrayList<FoodItemModel> getAllRecordsByCategory(String category) {
        ArrayList<FoodItemModel> arrayList = new ArrayList<>();
        try {
            String query = "select *from food_item where categoryName ='" + category + "'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                FoodItemModel fooditem = new FoodItemModel();
                fooditem.setItemName(rs.getString("itemName"));
                fooditem.setCategoryName(rs.getString("categoryName"));
                int itemPrice = rs.getInt("itemPrice");
                fooditem.setItemPrice(String.valueOf(itemPrice));
                fooditem.setItemID(rs.getInt("itemID"));
                arrayList.add(fooditem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }

    public static ArrayList<FoodItemModel> getAllRecordsByCategoryAndName(String category, String name) {
        ArrayList<FoodItemModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from food_item where categoryName ='" + category + "'"
                    + "and itemName like '%" + name + "%'");
            while (rs.next()) {
                FoodItemModel fooditem = new FoodItemModel();
                fooditem.setItemName(rs.getString("itemName"));
                fooditem.setCategoryName(rs.getString("categoryName"));
                int itemPrice = rs.getInt("itemPrice");
                fooditem.setItemPrice(String.valueOf(itemPrice));
                fooditem.setItemID(rs.getInt("itemID"));
                arrayList.add(fooditem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }

    public static ArrayList<FoodItemModel> fillterFoodItemByName(String name, String category) {
        ArrayList<FoodItemModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData(
                    "select *from food_item itemName like '%" + name + "%' and categoryName ='" + category + "'");
            while (rs.next()) {
                FoodItemModel fooditem = new FoodItemModel();
                fooditem.setItemName(rs.getString("name"));
                arrayList.add(fooditem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }

    public static boolean addFoodItem(FoodItemModel food) {
        int id = food.getItemID();
        String name = food.getItemName();
        int price = Integer.parseInt(food.getItemPrice());
        String categoryName = food.getCategoryName();
        String query = "insert into food_item values(" + id + ",'" + name + "'," + price + ",'" + categoryName
                + "', 1)";
        String query1 = "CALL SP_UpdateFoodItemCategoryID('" + categoryName + "');";
        DbOperations.setDataOrDelete(query, "");
        DbOperations.setDataOrDelete(query1, "Thêm món thành công");
        return true;
    }

    public static int getMaxFoodItemID() {
        int id = 0;
        try {
            ResultSet rs = DbOperations.getData("select max(itemID) from food_item");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public static ArrayList<FoodItemModel> getListFoodItemByNameAndCategory(String name, String category) {
        ArrayList<FoodItemModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from food_item where itemName like '%" + name + "%'"
                    + "and categoryName ='" + category + "'");
            while (rs.next()) {
                FoodItemModel fooditem = new FoodItemModel();
                fooditem.setItemName(rs.getString("itemName"));
                fooditem.setCategoryName(rs.getString("categoryName"));
                int itemPrice = rs.getInt("itemPrice");
                fooditem.setItemPrice(String.valueOf(itemPrice));
                fooditem.setItemID(rs.getInt("itemID"));
                arrayList.add(fooditem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }

    public static ArrayList<FoodItemModel> getListFoodItemByCategory(String category) {
        ArrayList<FoodItemModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from food_item where categoryName ='" + category + "'");
            while (rs.next()) {
                FoodItemModel fooditem = new FoodItemModel();
                fooditem.setItemName(rs.getString("itemName"));
                fooditem.setCategoryName(rs.getString("categoryName"));
                int itemPrice = rs.getInt("itemPrice");
                fooditem.setItemPrice(String.valueOf(itemPrice));
                fooditem.setItemID(rs.getInt("itemID"));
                arrayList.add(fooditem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }

    // public static boolean updateFoodItem(FoodItemModel food) {
    // int id = food.getItemID();
    // String name = food.getItemName();
    // int price = Integer.parseInt(food.getItemPrice());
    // String categoryName = food.getCategoryName();
    // String query = "update food_item set itemName = '" + name + "', itemPrice = "
    // + price + ", categoryName = '" + categoryName + "' where itemID = " + id;
    // DbOperations.setDataOrDelete(query, "Cập nhật món thành công");
    // return true;
    // }
    public static boolean updateFoodItem(FoodItemModel food) {
        int id = food.getItemID();
        String name = food.getItemName();
        int price = Integer.parseInt(food.getItemPrice());
        String categoryName = food.getCategoryName();

        Connection connection = ConnectionProvider.getConnection();
        try {
            connection.setAutoCommit(false);
            Statement lockTableStatement = connection.createStatement();
            lockTableStatement.execute("LOCK TABLES food_item WRITE");

            // Gọi stored procedure
            CallableStatement updateProcedure = connection.prepareCall("CALL SP_UpdateFoodItem(?, ?, ?, ?)");
            updateProcedure.setInt(1, id);
            updateProcedure.setInt(2, price);
            updateProcedure.setString(3, categoryName);
            updateProcedure.setString(4, name);
            updateProcedure.execute();
            // Commit giao dịch
            connection.commit();
            // Mở khóa bảng
            Statement unlockTableStatement = connection.createStatement();
            unlockTableStatement.execute("UNLOCK TABLES");
            JOptionPane.showMessageDialog(null, "Cập nhật món thành công");
        } catch (SQLException e) {
            // Xử lý exception
            e.printStackTrace();

            // Rollback giao dịch nếu có lỗi
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Đóng kết nối
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static boolean deleteFoodItem(int id) {
        String query = "Call sp_deletefooditem(" + id + ")";
        Connection connection = ConnectionProvider.getConnection();
        try {
            CallableStatement cstm = connection.prepareCall(query);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deleteFoodItemByCategory(String categoryName) {
        String query = "delete from food_item where categoryName = '" + categoryName + "'";
        DbOperations.setDataOrDelete(query, "");
        return true;
    }

}
