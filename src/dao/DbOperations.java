package dao;

import javax.swing.*;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbOperations {
    public static void setDataOrDelete(String Query, String msg) {
        try {
            Connection con = ConnectionProvider.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(Query);
            if (!msg.equals(""))
                JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getData(String query) {
        try {
            Connection con = ConnectionProvider.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}