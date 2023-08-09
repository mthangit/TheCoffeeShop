/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.CategoryDAO;
import dao.FoodItemDao;
import model.CategoryModel;
import model.FoodItemModel;

/**
 *
 * @author MANH THANG
 */
public class CategoryView extends javax.swing.JFrame {
    private int categoryID;
    private int foodItemID;

    /**
     * Creates new form CategoryView
     */
    public CategoryView() {
        initComponents();
    }

    public void EmployeeView() {
        this.lblType.setText("Employee");
    }

    public void setType(String type) {
        lblType.setText(type);
    }

    public void updateCategoryID() {
        categoryID = CategoryDAO.getMaxCategoryID();
        categoryID = categoryID + 1;
    }

    public void updateFoodItemID() {
        foodItemID = FoodItemDao.getMaxFoodItemID();
        foodItemID = foodItemID + 1;
    }

    public void setTxtCategory() {
        if (comboBoxCategory.getSelectedItem().toString().equals("Không chọn"))
            txtCategory.setText("");
        else
            txtCategory.setText(comboBoxCategory.getSelectedItem().toString());
    }

    public void setDataFoodItemTable(Iterator<FoodItemModel> iter) {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
        while (iter.hasNext()) {
            FoodItemModel fooditemObj = iter.next();
            dtm.addRow(new Object[] { fooditemObj.getItemID(), fooditemObj.getItemName(),
                    fooditemObj.getItemPrice(),
                    fooditemObj.getCategoryName() });
        }
    }

    public void setDataCategoryTable(Iterator<CategoryModel> iter) {
        DefaultTableModel dtm = (DefaultTableModel) tblCategory.getModel();
        dtm.setRowCount(0);
        while (iter.hasNext()) {
            CategoryModel categoryObj = iter.next();
            dtm.addRow(new Object[] { categoryObj.getCategoryID(), categoryObj.getCategoryName() });
        }

    }

    public void showDataFoodItemTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.getAllRecords();
        setDataFoodItemTable(list.iterator());
    }

    public void showDataCategoryTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblCategory.getModel();
        dtm.setRowCount(0);
        ArrayList<CategoryModel> list = CategoryDAO.getAllRecords();
        setDataCategoryTable(list.iterator());
    }

    public void showItemByCategory(String category) {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.getAllRecordsByCategory(category);
        setDataFoodItemTable(list.iterator());
    }

    public void showItemByCategoryAndName(String category, String name) {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.getAllRecordsByCategoryAndName(category, name);
        Iterator<FoodItemModel> itr = list.iterator();
        while (itr.hasNext()) {
            FoodItemModel fooditemObj = itr.next();
            dtm.addRow(new Object[] { fooditemObj.getItemID(), fooditemObj.getItemName(),
                    fooditemObj.getItemPrice(),
                    fooditemObj.getCategoryName() });
        }
    }

    public void clearFoodItemTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
    }

    public void clearCategoryTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblCategory.getModel();
        dtm.setRowCount(0);
    }

    public void clearFoodItemData() {
        txtCategory.setText("");
        txtItemName.setText("");
        txtPriceFoodItem.setText("");
        clearFoodItemTable();
        comboBoxCategory.setSelectedIndex(0);
    }

    public void clearCategoryData() {
        txtCategoryName.setText("");
        clearCategoryTable();
    }

    public void getListFoodItemByNameAndCategory(String name, String category) {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.getListFoodItemByNameAndCategory(name, category);
        setDataFoodItemTable(list.iterator());
    }

    public void getListFoodItemByCategory(String category) {
        DefaultTableModel dtm = (DefaultTableModel) tblFoodItem.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.getListFoodItemByCategory(category);
        setDataFoodItemTable(list.iterator());
    }

    public void updateCategoryComboBox() {
        comboBoxCategory.removeAllItems();
        comboBoxCategory.addItem("Không chọn");
        ArrayList<CategoryModel> list = CategoryDAO.getAllRecords();
        Iterator<CategoryModel> itr = list.iterator();
        while (itr.hasNext()) {
            CategoryModel categoryObj = itr.next();
            comboBoxCategory.addItem(categoryObj.getCategoryName());
        }
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        JscrollPane = new javax.swing.JScrollPane();
        tblFoodItem = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        btnAddCategory = new javax.swing.JButton();
        btnDeleteCategory = new javax.swing.JButton();
        lbCategory = new javax.swing.JLabel();
        comboBoxCategory = new javax.swing.JComboBox<>();
        lbItemName = new javax.swing.JLabel();
        lbPriceFoodItem = new javax.swing.JLabel();
        txtPriceFoodItem = new javax.swing.JTextField();
        btnClearSelected = new javax.swing.JButton();
        txtItemName = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        btnUpdateItem = new javax.swing.JButton();
        lbCategoryName = new javax.swing.JLabel();
        txtCategoryName = new javax.swing.JTextField();
        btnAddItem = new javax.swing.JButton();
        btnDeleteItem = new javax.swing.JButton();
        btnRefreshItem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pinglogo.png"))); // NOI18N
        jPanel1.add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 133, 90));

        lblType.setFont(new java.awt.Font("Open Sans", 2, 16)); // NOI18N
        lblType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblType.setText("Admin");
        lblType.setToolTipText("");
        jPanel1.add(lblType, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 72, -1));

        btnBack.setBackground(new java.awt.Color(255, 255, 204));
        btnBack.setFont(new java.awt.Font("Open Sans", 3, 14)); // NOI18N
        btnBack.setText("Trở về");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(1011, 41, -1, -1));

        lbTitle.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(204, 0, 0));
        lbTitle.setText("QUẢN LÝ SẢN PHẨM");
        jPanel1.add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 63, 476, 53));

        JscrollPane.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        JscrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JscrollPaneMouseClicked(evt);
            }
        });

        tblFoodItem.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Tên sản phấm", "Giá tiền", "Tên danh mục"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblFoodItem.setRowHeight(30);
        tblFoodItem.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tblFoodItem.setSelectionForeground(new java.awt.Color(204, 0, 0));
        tblFoodItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFoodItemMouseClicked(evt);
            }
        });
        JscrollPane.setViewportView(tblFoodItem);

        jPanel1.add(JscrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 130, 537, 441));

        jScrollPane1.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID danh mục", "Tên danh mục"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblCategory.setRowHeight(30);
        tblCategory.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tblCategory.setSelectionForeground(new java.awt.Color(204, 0, 0));
        tblCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategory);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 240, 252));

        btnAddCategory.setBackground(new java.awt.Color(255, 204, 204));
        btnAddCategory.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnAddCategory.setText("Thêm danh mục");
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 240, 30));

        btnDeleteCategory.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteCategory.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnDeleteCategory.setText("Xoá danh mục");
        btnDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCategoryActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeleteCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 240, 30));

        lbCategory.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbCategory.setText("Danh mục");
        jPanel1.add(lbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 286, 84, 19));

        comboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không chọn" }));
        comboBoxCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxCategoryMouseClicked(evt);
            }
        });
        comboBoxCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCategoryActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 130, 260, 32));

        lbItemName.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbItemName.setText("Tên sản phẩm");
        jPanel1.add(lbItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 322, 90, 19));

        lbPriceFoodItem.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbPriceFoodItem.setText("Giá tiền");
        jPanel1.add(lbPriceFoodItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 358, 84, 19));
        jPanel1.add(txtPriceFoodItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 352, 164, 30));

        btnClearSelected.setBackground(new java.awt.Color(204, 255, 204));
        btnClearSelected.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnClearSelected.setText("Bỏ chọn");
        btnClearSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSelectedActionPerformed(evt);
            }
        });
        jPanel1.add(btnClearSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 240, 30));

        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 316, 164, 30));

        txtCategory.setEditable(false);
        txtCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryActionPerformed(evt);
            }
        });
        jPanel1.add(txtCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 164, 30));

        btnUpdateItem.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdateItem.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnUpdateItem.setText("Cập nhật");
        btnUpdateItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateItemActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdateItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 239, 30));

        lbCategoryName.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        lbCategoryName.setText("Tên danh mục");
        jPanel1.add(lbCategoryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 462, -1, 30));
        jPanel1.add(txtCategoryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 140, 30));

        btnAddItem.setBackground(new java.awt.Color(255, 255, 153));
        btnAddItem.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnAddItem.setText("Thêm sản phẩm");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 239, 30));

        btnDeleteItem.setBackground(new java.awt.Color(255, 255, 102));
        btnDeleteItem.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnDeleteItem.setText("Xoá sản phẩm");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeleteItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, 239, 30));

        btnRefreshItem.setBackground(new java.awt.Color(204, 255, 204));
        btnRefreshItem.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        btnRefreshItem.setText("Làm mới ");
        btnRefreshItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshItemActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefreshItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 239, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itembg.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 590));

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

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddCategoryActionPerformed
        // TODO add your handling code here:
        int id = CategoryDAO.getMaxCategoryID() + 1;
        if (!txtCategoryName.getText().equals("")) {
            if (CategoryDAO.addCategory(id, txtCategoryName.getText())) {
                showDataCategoryTable();
                comboBoxCategory.addItem(txtCategoryName.getText());
                txtCategoryName.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm danh mục thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên danh mục");
        }
    }// GEN-LAST:event_btnAddCategoryActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddItemActionPerformed
        // TODO add your handling code here:
        if (!txtItemName.getText().equals("") && !txtPriceFoodItem.getText().equals("")) {
            int id = FoodItemDao.getMaxFoodItemID() + 1;
            String categoryName = txtCategory.getText();
            String itemName = txtItemName.getText();
            String itemPrice = txtPriceFoodItem.getText();
            FoodItemModel fooditemObj = new FoodItemModel(id, itemName, itemPrice, categoryName);
            if (FoodItemDao.addFoodItem(fooditemObj)) {
                showItemByCategory(categoryName);
                txtItemName.setText("");
                txtPriceFoodItem.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm và giá tiền");
        }
    }// GEN-LAST:event_btnAddItemActionPerformed

    private void btnRefreshItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRefreshItemActionPerformed
        // TODO add your handling code here:
        clearFoodItemData();
        showDataFoodItemTable();
        btnDeleteItem.setEnabled(false);
        btnUpdateItem.setEnabled(false);
    }// GEN-LAST:event_btnRefreshItemActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteItemActionPerformed
        // TODO add your handling code here:
        int index = tblFoodItem.getSelectedRow();
        if (index >= 0) {
            int id = Integer.parseInt(tblFoodItem.getValueAt(index, 0).toString());
            String category = tblFoodItem.getValueAt(index, 3).toString();
            if (FoodItemDao.deleteFoodItem(id)) {
                showItemByCategory(category);
                txtItemName.setText("");
                txtPriceFoodItem.setText("");
                btnDeleteItem.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xoá");
        }
    }// GEN-LAST:event_btnDeleteItemActionPerformed

    private void comboBoxCategoryMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_comboBoxCategoryMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_comboBoxCategoryMouseClicked

    private void tblFoodItemMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblFoodItemMouseClicked
        // TODO add your handling code here:
        int index = tblFoodItem.getSelectedRow();
        if (index >= 0) {
            String itemName = tblFoodItem.getValueAt(index, 1).toString();
            txtCategory.setText(tblFoodItem.getValueAt(index, 3).toString());
            txtItemName.setText(itemName);
            txtPriceFoodItem.setText(tblFoodItem.getValueAt(index, 2).toString());
            btnDeleteItem.setEnabled(true);
            btnUpdateItem.setEnabled(true);
            // btnAddCategory.setEnabled(false);
        } else {
            btnDeleteItem.setEnabled(false);
        }
    }// GEN-LAST:event_tblFoodItemMouseClicked

    private void btnDeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteCategoryActionPerformed
        // TODO add your handling code here:
        int index = tblCategory.getSelectedRow();
        if (index >= 0) {
            int categoryID = Integer.parseInt(tblCategory.getValueAt(index, 0).toString());
            String categoryName = txtCategoryName.getText();
            if (CategoryDAO.deleteCategory(categoryID)) {
                showDataCategoryTable();
                comboBoxCategory.removeItem(categoryName);

                txtCategoryName.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Xoá danh mục thất bại, danh mục đang được sử dụng");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn danh mục cần xoá");
        }
    }// GEN-LAST:event_btnDeleteCategoryActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình?", "Message",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }// GEN-LAST:event_formWindowClosing

    private void JscrollPaneMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_JscrollPaneMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_JscrollPaneMouseClicked

    private void txtCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtCategoryActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtCategoryActionPerformed

    private void btnClearSelectedActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnClearSelectedActionPerformed
        // TODO add your handling code here:
        tblCategory.clearSelection();
        btnDeleteCategory.setEnabled(false);
        txtCategoryName.setText("");
    }// GEN-LAST:event_btnClearSelectedActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jScrollPane1MouseClicked

    private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblCategoryMouseClicked
        // TODO add your handling code here:
        int index = tblCategory.getSelectedRow();
        if (index >= 0) {
            txtCategoryName.setText(tblCategory.getValueAt(index, 1).toString());
            btnDeleteCategory.setEnabled(true);
            // btnAddCategory.setEnabled(false);
        } else {
            btnDeleteCategory.setEnabled(false);
        }
    }// GEN-LAST:event_tblCategoryMouseClicked

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtItemNameActionPerformed

    private void btnUpdateItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        int index = tblFoodItem.getSelectedRow();
        if (index >= 0) {
            int id = Integer.parseInt(tblFoodItem.getValueAt(index, 0).toString());
            String category = txtCategory.getText();
            String name = txtItemName.getText();
            String price = txtPriceFoodItem.getText();
            FoodItemModel foodItemModel = new FoodItemModel(id, name, price, category);
            if (FoodItemDao.updateFoodItem(foodItemModel)) {
                showItemByCategoryAndName(category, name);
                btnUpdateItem.setEnabled(false);
                btnDeleteItem.setEnabled(false);
                txtItemName.setText("");
                txtPriceFoodItem.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xoá");
        }

    }// GEN-LAST:event_btnSearchActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new HomeView().setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnBackActionPerformed

    private void comboBoxCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_categoryComboBoxActionPerformed
        // TODO add your handling code here:
        setTxtCategory();
        String category = txtCategory.getText();
        if (!category.equals("")) {
            getListFoodItemByCategory(category);
            this.txtItemName.setText("");
            this.txtPriceFoodItem.setText("");
        } else {
            clearFoodItemData();
        }
    }// GEN-LAST:event_categoryComboBoxActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        ArrayList<CategoryModel> list = CategoryDAO.getAllRecords();
        Iterator<CategoryModel> itr = list.iterator();
        while (itr.hasNext()) {
            CategoryModel categoryObj = itr.next();
            comboBoxCategory.addItem(categoryObj.getCategoryName());
        }
        showDataCategoryTable();
        showDataFoodItemTable();
        btnDeleteCategory.setEnabled(false);
        btnUpdateItem.setEnabled(false);
        btnDeleteItem.setEnabled(false);
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CategoryView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoryView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoryView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoryView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoryView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JscrollPane;
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClearSelected;
    private javax.swing.JButton btnDeleteCategory;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnRefreshItem;
    private javax.swing.JButton btnUpdateItem;
    private javax.swing.JComboBox<String> comboBoxCategory;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbCategoryName;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPriceFoodItem;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblType;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblFoodItem;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtCategoryName;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPriceFoodItem;
    // End of variables declaration//GEN-END:variables
}
