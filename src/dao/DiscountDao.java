/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.DiscountModel;

/**
 *
 * @author HUY THUC
 */
public class DiscountDao {
    public static ArrayList<DiscountModel> getVoucher(String code){
        ArrayList<DiscountModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM discount WHERE discountName LIKE '%" + code + "%' AND CURDATE() BETWEEN discountStartDay AND discountEndDay");
            while(rs.next()){
                DiscountModel discount = new DiscountModel();
                discount.setDiscountName(rs.getString("discountName"));
                discount.setDiscountQuantity(rs.getInt("discountQuantity"));
                discount.setDiscountValue(rs.getInt("discountValue"));
                arrayList.add(discount);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
                
    }
    
     public static void updateQuantityVoucher(String name){
        
        String query1 = "CALL SP_ApplyDiscount('"+name+"')";
        DbOperations.setDataOrDelete(query1,"");
    }
     
    public static ArrayList<DiscountModel> getAllRecords(){
        ArrayList<DiscountModel> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select *from discount");
            while (rs.next()){
                DiscountModel discount = new DiscountModel();
                discount.setDiscountID(rs.getString(1));
                discount.setDiscountName(rs.getString(2));
                discount.setDiscountQuantity(rs.getInt(3));
                discount.setDiscountValue(rs.getInt(4));
                discount.setDiscountStart(rs.getDate(5));
                discount.setDiscountEnd(rs.getDate(6));
                arrayList.add(discount);
                    
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }   
    
    public static void update(DiscountModel discount){
        String query = "CALL SP_UpdateDiscount('" + discount.getDiscountName() + "', " + discount.getDiscountQuantity() + ", " + discount.getDiscountValue() + ", '" + discount.getDiscountStart() + "', '" + discount.getDiscountEnd() + "', '" + discount.getDiscountID() + "')";
        DbOperations.setDataOrDelete(query, "Update thanh cong");
        System.out.println(query);
    }
    
    public static void delete(String id){
        String query = "delete from discount where discountID = '"+id+"' ";
        DbOperations.setDataOrDelete(query, "Xoa thanh cong");
        System.out.println(query);
    }
    
    public static void save(DiscountModel discount){
        String query = "CALL SP_AddDiscount('" + discount.getDiscountID() + "','" + discount.getDiscountName() + "', " + discount.getDiscountQuantity() + ", " + discount.getDiscountValue() + ", '" + discount.getDiscountStart() + "', '" + discount.getDiscountEnd() + "')";
        DbOperations.setDataOrDelete(query, "Thêm thành công");
    }
}
