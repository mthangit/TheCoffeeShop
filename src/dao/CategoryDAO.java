package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.xdevapi.PreparableStatement;

import model.CategoryModel;

public class CategoryDAO {
    public CategoryDAO() {
    }

    public static boolean addCategory(int id, String categoryName) {
        try {
            String query = "insert into category values(" + id + ", '" + categoryName + "')";
            DbOperations.setDataOrDelete(query, "Thêm danh mục thành công");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static ArrayList<CategoryModel> getAllRecords() {
        ArrayList<CategoryModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from category");
            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryID(rs.getInt("categoryID"));
                category.setCategoryName(rs.getString("categoryName"));
                arrayList.add(category);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return arrayList;
    }
    public static int getMaxCategoryID(){
        int max = 0;
        try {
            ResultSet rs = DbOperations.getData("select max(categoryID) from category");
            while (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return max;
    }
    public static boolean updateCategory(int categoryID, String categoryName) {
        try {
            String query = "update category set categoryName = '" + categoryName + "' where categoryID = " + categoryID;
            DbOperations.setDataOrDelete(query, "Cập nhật danh mục thành công");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public static boolean deleteCategory(int categoryID) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            String query = "Call SP_Deletecategory(" + categoryID + ")";
            java.sql.CallableStatement cstm = connection.prepareCall(query);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()){
                String message = rs.getString("Message");
                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            cstm.close();
            connection.close();
            return true;
        } catch (Exception e) {

        }
    
        return false;
    }

    public static int getIDByName(String name){
        int id = 0;
        try {
            ResultSet rs = DbOperations.getData("select categoryID from category where categoryName = '" + name + "'");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
}
