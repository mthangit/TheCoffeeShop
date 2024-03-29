/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import dao.DbOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUY THUC
 */
public class ReportByMonthView extends javax.swing.JDialog {

    /**
     * Creates new form ReportByMonthView
     */
    public ReportByMonthView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public void populateTable() {
    // Thực hiện câu truy vấn để lấy dữ liệu từ cơ sở dữ liệu
    String query = "CALL SP_GetBillPriceByMonthYear("+comboBoxMonth.getSelectedItem().toString()+","+comboBoxYear.getSelectedItem().toString()+")";
    ResultSet resultSet = DbOperations.getData(query);

    try {
        // Lấy DefaultTableModel từ JTable đã tạo sẵn
        DefaultTableModel tableModel = (DefaultTableModel) tblStatisticsMonthly.getModel();

        // Xóa các hàng hiện có trong DefaultTableModel
        tableModel.setRowCount(0);

        // Lặp qua các hàng trong ResultSet và thêm vào DefaultTableModel
        while (resultSet.next()) {
            Object[] rowData = {resultSet.getString(1), resultSet.getString(2)};
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
        tblStatisticsMonthly = new javax.swing.JTable();
        jTitle = new javax.swing.JLabel();
        lbMonth = new javax.swing.JLabel();
        lbYear = new javax.swing.JLabel();
        btnDisplay = new javax.swing.JButton();
        comboBoxMonth = new javax.swing.JComboBox<>();
        comboBoxYear = new javax.swing.JComboBox<>();
        btnExport = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblStatisticsMonthly.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Tổng tiền"
            }
        ));
        tblStatisticsMonthly.setRowHeight(30);
        jScrollPane1.setViewportView(tblStatisticsMonthly);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 450, 230));

        jTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTitle.setForeground(new java.awt.Color(204, 0, 0));
        jTitle.setText("THỐNG KÊ DOANH THU THÁNG");
        getContentPane().add(jTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 370, -1));

        lbMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMonth.setText("Tháng");
        getContentPane().add(lbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 50, 30));

        lbYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbYear.setText("Năm");
        getContentPane().add(lbYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 40, 30));

        btnDisplay.setBackground(new java.awt.Color(255, 255, 204));
        btnDisplay.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnDisplay.setText("Xem");
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });
        getContentPane().add(btnDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 70, -1));

        comboBoxMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        comboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMonthActionPerformed(evt);
            }
        });
        getContentPane().add(comboBoxMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 61, -1));

        comboBoxYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025" }));
        getContentPane().add(comboBoxYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 80, -1));

        btnExport.setBackground(new java.awt.Color(204, 255, 204));
        btnExport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExport.setText("Xuất File báo cáo");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        getContentPane().add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 150, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bgyellow.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 380));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        // TODO add your handling code here:
        populateTable();
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Thống kê chi tiết tháng");
        MessageFormat footer = new MessageFormat("CoffeeShop");
        try {
            tblStatisticsMonthly.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, header);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void comboBoxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMonthActionPerformed

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
            java.util.logging.Logger.getLogger(ReportByMonthView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportByMonthView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportByMonthView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportByMonthView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReportByMonthView dialog = new ReportByMonthView(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> comboBoxMonth;
    private javax.swing.JComboBox<String> comboBoxYear;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTitle;
    private javax.swing.JLabel lbMonth;
    private javax.swing.JLabel lbYear;
    private javax.swing.JTable tblStatisticsMonthly;
    // End of variables declaration//GEN-END:variables
}
