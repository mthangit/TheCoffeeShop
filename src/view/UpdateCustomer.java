/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

import dao.CustomerDAO;
import model.CustomerModel;

/**
 *
 * @author manht
 */
public class UpdateCustomer extends javax.swing.JDialog {
    private CustomerModel customer;

    /**
     * Creates new form UpdateCustomer
     */
    public UpdateCustomer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setCustomer(int id) {
        customer = CustomerDAO.getCustomerByID(id);
        txtName.setText(customer.getCustomerName());
        dateDOB.setDate(customer.getCustomerDOB());
        txtAddress.setText(customer.getCustomerAddress());
        txtPhone.setText(customer.getCustomerPhone());
        txtEmail.setText(customer.getCustomerEmail());
        txtRate.setText(customer.getCustomerRate());
        txtPoint.setText(String.valueOf(customer.getCustomerPoint()));

    }

    public boolean isNotEmpty() {
        if (txtName.getText().isEmpty() || dateDOB.getDate() == null || txtAddress.getText().isEmpty()
                || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty() || txtRate.getText().isEmpty()
                || txtPoint.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    public void setCustomer() {
        customer.setCustomerName(txtName.getText());
        customer.setCustomerDOB(new java.sql.Date(dateDOB.getDate().getTime()));
        customer.setCustomerAddress(txtAddress.getText());
        customer.setCustomerPhone(txtPhone.getText());
        customer.setCustomerEmail(txtEmail.getText());
        customer.setCustomerRate(txtRate.getText());
        customer.setCustomerPoint(Integer.parseInt(txtPoint.getText()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbUpdateCustomer = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        lbPoint = new javax.swing.JLabel();
        lbRate = new javax.swing.JLabel();
        lbDOB = new javax.swing.JLabel();
        txtRate = new javax.swing.JTextField();
        txtPoint = new javax.swing.JTextField();
        lbAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        dateDOB = new com.toedter.calendar.JDateChooser();
        btnUpdateInfo = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbUpdateCustomer.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        lbUpdateCustomer.setForeground(new java.awt.Color(204, 0, 0));
        lbUpdateCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUpdateCustomer.setText("CẬP NHẬT THÔNG TIN");
        jPanel1.add(lbUpdateCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, 428, 44));

        lbName.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbName.setText("Họ và Tên");
        jPanel1.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 76, 102, 39));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 76, 320, 39));

        lbPhone.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbPhone.setText("Số điện thoại");
        jPanel1.add(lbPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 127, 102, 39));

        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneKeyTyped(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 127, 320, 39));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 178, 320, 39));

        lbEmail.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbEmail.setText("Email");
        jPanel1.add(lbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 178, 102, 39));

        lbPoint.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbPoint.setText("Điểm");
        jPanel1.add(lbPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 229, 102, 39));

        lbRate.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbRate.setText("Hạng");
        jPanel1.add(lbRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 280, 102, 39));

        lbDOB.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbDOB.setText("Ngày sinh");
        jPanel1.add(lbDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 331, 102, 39));

        txtRate.setEditable(false);
        txtRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRateActionPerformed(evt);
            }
        });
        jPanel1.add(txtRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 280, 320, 39));

        txtPoint.setEditable(false);
        txtPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPointActionPerformed(evt);
            }
        });
        jPanel1.add(txtPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 229, 320, 39));

        lbAddress.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbAddress.setText("Địa chỉ");
        jPanel1.add(lbAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 382, 102, 39));

        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 382, 320, 39));
        jPanel1.add(dateDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 331, 320, 39));

        btnUpdateInfo.setBackground(new java.awt.Color(255, 153, 153));
        btnUpdateInfo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateInfo.setText("Cập nhật");
        btnUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInfoActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdateInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 150, 35));

        btnCancel.setBackground(new java.awt.Color(204, 204, 204));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setText("Huỷ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 153, 35));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insertcustomerbg.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhoneKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtPhoneKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || this.txtPhone.getText().length() >= 10) {
            evt.consume();
        }
    }// GEN-LAST:event_txtPhoneKeyTyped

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtNameActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_txtPhoneActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtEmailActionPerformed

    private void txtRateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtRateActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtRateActionPerformed

    private void txtPointActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPointActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtPointActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtAddressActionPerformed

    private void btnUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUpdateInfoActionPerformed
        // TODO add your handling code here:
        if (isNotEmpty()) {
            setCustomer();
            if (CustomerDAO.updateCustomer(customer)) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi cập nhật");
            }
        }
    }// GEN-LAST:event_btnUpdateInfoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateCustomer dialog = new UpdateCustomer(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdateInfo;
    private com.toedter.calendar.JDateChooser dateDOB;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbDOB;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbPoint;
    private javax.swing.JLabel lbRate;
    private javax.swing.JLabel lbUpdateCustomer;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPoint;
    private javax.swing.JTextField txtRate;
    // End of variables declaration//GEN-END:variables
}
