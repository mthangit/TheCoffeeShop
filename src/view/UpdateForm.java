/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

import dao.EmployeeDAO;
import model.EmployeeModel;

/**
 *
 * @author manht
 */
public class UpdateForm extends javax.swing.JDialog {
        private EmployeeModel employeeModel;

        /**
         * Creates new form UpdateForm
         */
        public UpdateForm(java.awt.Frame parent, boolean modal) {
                super(parent, modal);
                initComponents();
        }

        public void setEmployee(int id) {
                employeeModel = EmployeeDAO.getEmployeeByID(id);
                if (isPartTime()) {
                        this.txtTaxID.setText("");
                        this.txtContractID.setText("");
                        this.txtTaxID.setEnabled(false);
                        this.txtContractID.setEnabled(false);
                }
                txtName.setText(employeeModel.getEmployeeName());
                txtPhone.setText(employeeModel.getEmployeePhone());
                txtAddress.setText(employeeModel.getEmployeeAddress());
                txtCID.setText(employeeModel.getEmployeeCID());
                txtHometown.setText(employeeModel.getEmployeeHometown());
                txtEmail.setText(employeeModel.getEmployeeEmail());
                txtTaxID.setText(employeeModel.getEmployeeTaxID());
                txtContractID.setText(employeeModel.getEmployeeContractID());
                comboBoxType.setSelectedItem(employeeModel.getEmployeeType());
                comboBoxPosition.setSelectedItem(employeeModel.getEmployeePosition());
                comboboxStatus.setSelectedItem(employeeModel.getEmployeeStatus());
        }

        public void setDataEmployee() {
                employeeModel.setEmployeeName(txtName.getText());
                employeeModel.setEmployeePhone(txtPhone.getText());
                employeeModel.setEmployeeAddress(txtAddress.getText());
                employeeModel.setEmployeeCID(txtCID.getText());
                employeeModel.setEmployeeHometown(txtHometown.getText());
                employeeModel.setEmployeeEmail(txtEmail.getText());
                employeeModel.setEmployeeType(comboBoxType.getSelectedItem().toString());
                employeeModel.setEmployeePosition(comboBoxPosition.getSelectedItem().toString());
                if (isPartTime()) {
                        employeeModel.setEmployeeTaxID("");
                        employeeModel.setEmployeeContractID("");
                } else {
                        employeeModel.setEmployeeTaxID(txtTaxID.getText());
                        employeeModel.setEmployeeContractID(txtContractID.getText());
                }
                employeeModel.setStatus(comboboxStatus.getSelectedItem().toString());
        }

        public boolean isPartTime() {
                if (employeeModel.getEmployeeType().equals("parttime")) {
                        return true;
                }
                return false;
        }

        public boolean checkValidCID() {
                int length = this.txtCID.getText().length();
                if (!txtCID.getText().isEmpty()) {
                        if (length != 12 && length != 9) {
                                JOptionPane.showMessageDialog(rootPane, "Sai định dạng CCCD", "Lỗi định dạng",
                                                JOptionPane.ERROR_MESSAGE);
                                this.txtCID.requestFocus();
                                return false;
                        }
                }
                return true;
        }

        public boolean checkValidEmail() {
                if (!txtEmail.getText().isEmpty()) {
                        if (!isValidEmail(this.txtEmail.getText())) {
                                JOptionPane.showMessageDialog(rootPane, "Sai định dạng email", "Lỗi định dạng",
                                                JOptionPane.ERROR_MESSAGE);
                                this.txtEmail.requestFocus();
                                return false;
                        }
                }
                return true;
        }

        public boolean checkValidPhone() {
                if (!txtPhone.getText().isEmpty()) {
                        if (this.txtPhone.getText().length() != 10) {
                                JOptionPane.showMessageDialog(rootPane, "Sai định dạng điện thoại", "Lỗi định dạng",
                                                JOptionPane.ERROR_MESSAGE);
                                this.txtPhone.requestFocus();
                                return false;
                        }
                }
                return true;
        }

        public boolean isNotEmpty() {
                String type = comboBoxType.getSelectedItem().toString();
                if (type.equals("parttime")) {
                        if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty()
                                        || txtAddress.getText().isEmpty()
                                        || txtCID.getText().isEmpty() || txtHometown.getText().isEmpty()
                                        || txtEmail.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(rootPane, "Không được để trống", "Lỗi định dạng",
                                                JOptionPane.ERROR_MESSAGE);
                                return false;
                        }
                } else {
                        if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty()
                                        || txtAddress.getText().isEmpty()
                                        || txtCID.getText().isEmpty() || txtHometown.getText().isEmpty()
                                        || txtEmail.getText().isEmpty()
                                        || txtTaxID.getText().isEmpty() || txtContractID.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(rootPane, "Không được để trống", "Lỗi định dạng",
                                                JOptionPane.ERROR_MESSAGE);
                                return false;
                        }
                }
                return true;
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbUpdateemployee = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtCID = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        lbAddress = new javax.swing.JLabel();
        lbHometown = new javax.swing.JLabel();
        txtHometown = new javax.swing.JTextField();
        lbCID = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbType = new javax.swing.JLabel();
        comboBoxType = new javax.swing.JComboBox<>();
        lbPosition = new javax.swing.JLabel();
        comboBoxPosition = new javax.swing.JComboBox<>();
        lbTaxID = new javax.swing.JLabel();
        txtTaxID = new javax.swing.JTextField();
        lbContractID = new javax.swing.JLabel();
        txtContractID = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        comboboxStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbUpdateemployee.setBackground(new java.awt.Color(255, 255, 255));
        lbUpdateemployee.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        lbUpdateemployee.setForeground(new java.awt.Color(204, 0, 0));
        lbUpdateemployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUpdateemployee.setText("CHỈNH SỬA THÔNG TIN NHÂN VIÊN");
        lbUpdateemployee.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lbUpdateemployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 725, 46));

        lbName.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbName.setText("Họ và tên");
        jPanel1.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 88, 35));

        txtName.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 70, 215, 35));

        lbPhone.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbPhone.setText("Số điện thoại");
        jPanel1.add(lbPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 88, 35));

        txtPhone.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        txtPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneFocusLost(evt);
            }
        });
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneKeyTyped(evt);
            }
        });
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 130, 215, 35));

        txtCID.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        txtCID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCIDFocusLost(evt);
            }
        });
        txtCID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCIDKeyTyped(evt);
            }
        });
        jPanel1.add(txtCID, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 130, 255, 35));

        txtAddress.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 192, 593, 35));

        lbAddress.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbAddress.setText("Địa chỉ");
        jPanel1.add(lbAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 192, 88, 35));

        lbHometown.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbHometown.setText("Quê quán");
        jPanel1.add(lbHometown, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 254, 88, 35));

        txtHometown.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        jPanel1.add(txtHometown, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 254, 215, 35));

        lbCID.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbCID.setText("Số CCCD");
        jPanel1.add(lbCID, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 73, 35));

        lbEmail.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbEmail.setText("Email");
        jPanel1.add(lbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 70, 73, 35));

        txtEmail.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 70, 255, 35));

        lbType.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbType.setText("Loại nhân viên");
        jPanel1.add(lbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 315, -1, 35));

        comboBoxType.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        comboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "parttime", "fulltime" }));
        comboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTypeActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxType, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 315, 216, 35));

        lbPosition.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbPosition.setText("Vị trí");
        jPanel1.add(lbPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 315, 77, 35));

        comboBoxPosition.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        comboBoxPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "waitress", "barista", "waiter", "manager", "chef", "accountant" }));
        comboBoxPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPositionActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 315, 256, 35));

        lbTaxID.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbTaxID.setText("Mã số thuế");
        jPanel1.add(lbTaxID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 376, 88, 35));

        txtTaxID.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        jPanel1.add(txtTaxID, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 376, 215, 35));

        lbContractID.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbContractID.setText("Mã hợp đồng");
        jPanel1.add(lbContractID, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 376, 82, 35));

        txtContractID.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        jPanel1.add(txtContractID, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 376, 257, 35));

        btnCancel.setBackground(new java.awt.Color(204, 204, 204));
        btnCancel.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnCancel.setText("Huỷ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 429, 177, 39));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdate.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 429, 177, 39));

        lbStatus.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbStatus.setText("Tình trạng");
        jPanel1.add(lbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 254, 77, 35));

        comboboxStatus.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        comboboxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm việc", "Đã nghỉ" }));
        jPanel1.add(comboboxStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 254, 256, 35));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itembg.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

        public boolean isValidEmail(String email) {
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                return email.matches(emailRegex);
        }

        private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
                // TODO add your handling code here:
                int result = JOptionPane.showConfirmDialog(rootPane, "Thoát khỏi chỉnh sửa", "Thông báo thoát",
                                JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                        this.dispose();
                } else {
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
        }// GEN-LAST:event_btnCancelActionPerformed

        private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEmailFocusLost
                // TODO add your handling code here:
                // if (!txtEmail.getText().isEmpty()) {
                // if (!isValidEmail(this.txtEmail.getText())) {
                // JOptionPane.showMessageDialog(rootPane, "Sai định dạng email", "Lỗi định
                // dạng",
                // JOptionPane.ERROR_MESSAGE);
                // this.txtEmail.requestFocus();
                // }
                // }
        }// GEN-LAST:event_txtEmailFocusLost

        private void txtPhoneFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtPhoneFocusLost
                // TODO add your handling code here:
                // if (!txtPhone.getText().isEmpty()) {
                // if (this.txtPhone.getText().length() != 10) {
                // JOptionPane.showMessageDialog(rootPane, "Sai định dạng điện thoại", "Lỗi định
                // dạng",
                // JOptionPane.ERROR_MESSAGE);
                // this.txtPhone.requestFocus();
                // }
                // }
        }// GEN-LAST:event_txtPhoneFocusLost

        private void txtCIDFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCIDFocusLost
                // TODO add your handling code here:
                // int length = this.txtCID.getText().length();
                // if (!txtCID.getText().isEmpty()) {
                // if (length != 12 && length != 9) {
                // JOptionPane.showMessageDialog(rootPane, "Sai định dạng CCCD", "Lỗi định
                // dạng",
                // JOptionPane.ERROR_MESSAGE);
                // this.txtCID.requestFocus();
                // }
                // }
        }// GEN-LAST:event_txtCIDFocusLost

        private void txtCIDKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCIDKeyTyped
                // TODO add your handling code here:
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) || this.txtCID.getText().length() >= 12) {
                        evt.consume();
                }
        }// GEN-LAST:event_txtCIDKeyTyped

        private void txtPhoneKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtPhoneKeyTyped
                // TODO add your handling code here:
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) || this.txtPhone.getText().length() >= 10) {
                        evt.consume(); // Không cho phép nhập ký tự và vượt quá độ dài 10
                }
        }// GEN-LAST:event_txtPhoneKeyTyped

        private void comboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comboBoxTypeActionPerformed
                // TODO add your handling code here:
                if (this.comboBoxType.getSelectedItem().toString().equals("fulltime")) {
                        this.txtTaxID.setEnabled(true);
                        this.txtContractID.setEnabled(true);
                } else {
                        this.txtTaxID.setEnabled(false);
                        this.txtContractID.setEnabled(false);
                }
        }// GEN-LAST:event_comboBoxTypeActionPerformed

        private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed
                // TODO add your handling code here:
                if (isNotEmpty() && checkValidCID() && checkValidEmail() && checkValidPhone()) {
                        setDataEmployee();
                        EmployeeDAO.updateEmployee(employeeModel);
                        this.dispose();
                }
        }// GEN-LAST:event_btnAddActionPerformed

        private void comboBoxPositionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comboBoxPositionActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_comboBoxPositionActionPerformed

        private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
                // TODO add your handling code here:
        }// GEN-LAST:event_formComponentShown

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
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                }
                // </editor-fold>

                /* Create and display the dialog */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                UpdateForm dialog = new UpdateForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboBoxPosition;
    private javax.swing.JComboBox<String> comboBoxType;
    private javax.swing.JComboBox<String> comboboxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbCID;
    private javax.swing.JLabel lbContractID;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbHometown;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbPosition;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTaxID;
    private javax.swing.JLabel lbType;
    private javax.swing.JLabel lbUpdateemployee;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCID;
    private javax.swing.JTextField txtContractID;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHometown;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtTaxID;
    // End of variables declaration//GEN-END:variables
}
