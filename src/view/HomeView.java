/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import javax.swing.JOptionPane;

import dao.UserDAO;

/**
 *
 * @author MANH THANG
 */
public class HomeView extends javax.swing.JFrame {
    private LoginView loginView;
    private OrderView orderView;
    private EmployeeView employeeView;
    private CustomerView customerView;
    private CategoryView categoryView;
    private BillView billView;
    private AccountView accountView;
    private ManageDiscountView manageDiscountView;
    private ReportView reportView;
    /**
     * Creates new form HomeView
     */
    public HomeView() {
        initComponents();
    }

    public void EmployeeView() {
        this.btnDiscount.setVisible(false);
        this.btnEmployee.setVisible(false);
        this.btnItem.setVisible(false);
        this.btnAccount.setVisible(false);
        this.lbType.setText("Employee");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lbHome = new javax.swing.JLabel();
        btnOrder = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnBill = new javax.swing.JButton();
        btnDiscount = new javax.swing.JButton();
        btnItem = new javax.swing.JButton();
        btnEmployee = new javax.swing.JButton();
        btnStatistics = new javax.swing.JButton();
        lbType = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 630));

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pinglogo.png"))); // NOI18N

        lbHome.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        lbHome.setForeground(new java.awt.Color(204, 0, 0));
        lbHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHome.setText("TRANG CHỦ");

        btnOrder.setBackground(new java.awt.Color(255, 255, 204));
        btnOrder.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnOrder.setForeground(new java.awt.Color(204, 0, 0));
        btnOrder.setText("Order");
        btnOrder.setToolTipText("");
        btnOrder.setBorder(null);
        btnOrder.setBorderPainted(false);
        btnOrder.setInheritsPopupMenu(true);
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnCustomer.setBackground(new java.awt.Color(255, 255, 204));
        btnCustomer.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(204, 0, 0));
        btnCustomer.setText("Quản lý khách hàng");
        btnCustomer.setBorder(null);
        btnCustomer.setBorderPainted(false);
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnBill.setBackground(new java.awt.Color(255, 255, 204));
        btnBill.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnBill.setForeground(new java.awt.Color(204, 0, 0));
        btnBill.setText("Quản lý hoá đơn");
        btnBill.setBorder(null);
        btnBill.setBorderPainted(false);
        btnBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillActionPerformed(evt);
            }
        });

        btnDiscount.setBackground(new java.awt.Color(255, 255, 204));
        btnDiscount.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnDiscount.setForeground(new java.awt.Color(204, 0, 0));
        btnDiscount.setText("Quản lý khuyến mãi");
        btnDiscount.setBorder(null);
        btnDiscount.setBorderPainted(false);
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        btnItem.setBackground(new java.awt.Color(255, 255, 204));
        btnItem.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnItem.setForeground(new java.awt.Color(204, 0, 0));
        btnItem.setText("Quản lý sản phẩm");
        btnItem.setBorder(null);
        btnItem.setBorderPainted(false);
        btnItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemActionPerformed(evt);
            }
        });

        btnEmployee.setBackground(new java.awt.Color(255, 255, 204));
        btnEmployee.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnEmployee.setForeground(new java.awt.Color(204, 0, 0));
        btnEmployee.setText("Quản lý nhân viên");
        btnEmployee.setBorder(null);
        btnEmployee.setBorderPainted(false);
        btnEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeActionPerformed(evt);
            }
        });

        btnStatistics.setBackground(new java.awt.Color(255, 255, 204));
        btnStatistics.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnStatistics.setForeground(new java.awt.Color(204, 0, 0));
        btnStatistics.setText("Thống kê báo cáo");
        btnStatistics.setBorder(null);
        btnStatistics.setBorderPainted(false);
        btnStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticsActionPerformed(evt);
            }
        });

        lbType.setFont(new java.awt.Font("Open Sans", 2, 16)); // NOI18N
        lbType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbType.setText("Admin");
        lbType.setToolTipText("");

        btnLogout.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(204, 0, 0));
        btnExit.setFont(new java.awt.Font("Open Sans", 1, 15)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnAccount.setBackground(new java.awt.Color(255, 255, 204));
        btnAccount.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        btnAccount.setForeground(new java.awt.Color(204, 0, 0));
        btnAccount.setText("Quản lý tài khoản");
        btnAccount.setBorder(null);
        btnAccount.setBorderPainted(false);
        btnAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(lbHome, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbType, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnLogout))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnItem, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)
                                .addComponent(btnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(109, 109, 109)
                                    .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(btnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(btnAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(lbHome))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbType)
                        .addGap(6, 6, 6)
                        .addComponent(btnLogout)
                        .addGap(6, 6, 6)
                        .addComponent(btnExit)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnItem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình?", "Message", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountActionPerformed
        // TODO add your handling code here:
        accountView = new AccountView();
        accountView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAccountActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOrderActionPerformed
        // TODO add your handling code here:
        orderView = new OrderView();
        if (UserDAO.getType().equals("employee")) {
            orderView.EmployeeType();
        }
        orderView.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnOrderActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCustomerActionPerformed
        // TODO add your handling code here:
        customerView = new CustomerView();
        if (UserDAO.getType().equals("employee")) {
            customerView.EmployeeView();
        }
        customerView.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnCustomerActionPerformed

    private void btnBillActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBillActionPerformed
        // TODO add your handling code here:
        billView = new BillView();
        billView.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnBillActionPerformed

    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDiscountActionPerformed
        // TODO add your handling code here:
        manageDiscountView = new ManageDiscountView();
        manageDiscountView.setVisible(true);
        this.dispose();

    }// GEN-LAST:event_btnDiscountActionPerformed

    private void btnEmployeeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEmployeeActionPerformed
        // TODO add your handling code here:
        employeeView = new EmployeeView();
        employeeView.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnEmployeeActionPerformed

    private void btnItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnItemActionPerformed
        // TODO add your handling code here:
        categoryView = new CategoryView();
        if (UserDAO.getType().equals("employee")) {
            categoryView.EmployeeView();
        }
        categoryView.setVisible(true);
        this.dispose();
        
    }// GEN-LAST:event_btnItemActionPerformed

    private void btnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnStatisticsActionPerformed
        // TODO add your handling code here:
        reportView = new ReportView();
        reportView.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnStatisticsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất khỏi ứng dụng không?", "Xác nhận đăng xuất khỏi ứng dụng", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
            loginView = new LoginView();
            UserDAO.getInstance();
            UserDAO.setUser(null);
            loginView.setVisible(true);
            this.dispose();
        }
    }// GEN-LAST:event_btnLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        // exit the frame if click yes
        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng ứng dụng không", "Xác nhận đóng ứng dụng", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }

    // GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccount;
    private javax.swing.JButton btnBill;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnEmployee;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnItem;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnStatistics;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbType;
    // End of variables declaration//GEN-END:variables
}