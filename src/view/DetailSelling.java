/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import dao.DbOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUY THUC
 */
public class DetailSelling extends javax.swing.JDialog {

    /**
     * Creates new form DetailSelling
     */
    public DetailSelling(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public void populateTable() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString1 = dateFormat.format(dateChoose.getDate());
        java.sql.Date sqlDate1 = java.sql.Date.valueOf(dateString1);

    // Thực hiện câu truy vấn để lấy dữ liệu từ cơ sở dữ liệu
    String query = "CALL SP_GetSalesForDate('"+sqlDate1+"');";
    System.out.println(query);
    ResultSet resultSet = DbOperations.getData(query);

    try {
        // Lấy DefaultTableModel từ JTable đã tạo sẵn
        DefaultTableModel tableModel = (DefaultTableModel) tblStatisticsDaily.getModel();

        // Xóa các hàng hiện có trong DefaultTableModel
        tableModel.setRowCount(0);

        // Lặp qua các hàng trong ResultSet và thêm vào DefaultTableModel
        while (resultSet.next()) {
            Object[] rowData = {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)};
            tableModel.addRow(rowData);
        }

        // Đóng ResultSet
        resultSet.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStatisticsDaily = new javax.swing.JTable();
        dateChoose = new com.toedter.calendar.JDateChooser();
        btnDisplay = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        lbTitle1 = new javax.swing.JLabel();
        lbChooseDate = new javax.swing.JLabel();
        lbTitle2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblStatisticsDaily.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Số lượng bán được", "Giá sản phẩm", "Tổng tiền"
            }
        ));
        tblStatisticsDaily.setRowHeight(30);
        jScrollPane1.setViewportView(tblStatisticsDaily);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 590, 230));
        getContentPane().add(dateChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 250, 30));

        btnDisplay.setBackground(new java.awt.Color(255, 255, 204));
        btnDisplay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDisplay.setText("Xem");
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });
        getContentPane().add(btnDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        btnExport.setBackground(new java.awt.Color(204, 255, 204));
        btnExport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExport.setText("Xuất File báo cáo");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        getContentPane().add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 150, 30));

        lbTitle1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle1.setForeground(new java.awt.Color(204, 0, 0));
        lbTitle1.setText("THỐNG KÊ CHI TIẾT");
        getContentPane().add(lbTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 230, 40));

        lbChooseDate.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbChooseDate.setText("Chọn ngày");
        getContentPane().add(lbChooseDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 80, 30));

        lbTitle2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle2.setForeground(new java.awt.Color(204, 0, 0));
        lbTitle2.setText("SẢN PHẨM BÁN TRONG NGÀY");
        getContentPane().add(lbTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itembg.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        // TODO add your handling code here:
        populateTable();
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Thống kê chi tiết");
        MessageFormat footer = new MessageFormat("CoffeeShop");
        try {
            tblStatisticsDaily.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, header);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetailSelling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailSelling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailSelling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailSelling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetailSelling dialog = new DetailSelling(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnExport;
    private com.toedter.calendar.JDateChooser dateChoose;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbChooseDate;
    private javax.swing.JLabel lbTitle1;
    private javax.swing.JLabel lbTitle2;
    private javax.swing.JTable tblStatisticsDaily;
    // End of variables declaration//GEN-END:variables
}
