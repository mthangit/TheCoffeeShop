/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.OpenFilePdf;
import dao.BillDao;
import dao.BillDetailDAO;
import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.FoodItemDao;
import dao.UserDAO;
import dao.DiscountDao;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.Iterator;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.BillDetailModel;
import model.BillModel;
import model.CategoryModel;
import model.CustomerModel;
import model.FoodItemModel;
import model.DiscountModel;

public class OrderView extends javax.swing.JFrame {

    private int billId = 1;
    private int tongTien = 0;
    private int itemPrice = 0;
    private int itemTotal = 0;
    private HomeView homeView;

    // public int discountValue = 100;
    /**
     * Creates new form OrderView
     */
    public OrderView() {
        initComponents();
        txtItemName.setEditable(false);
        txtItemPrice.setEditable(false);
        txtItemTotal.setEditable(false);
        btnThem.setEnabled(false);
        btnPrintBill.setEnabled(false);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) spinnerQuantity.getEditor()).getTextField();
        tf.setEnabled(false);

    }

    public void EmployeeType() {
        lbType.setText("Employee");
    }

    // public void clear() {
    // txtCustomerName.setText("");
    // txtCustomerPhone.setText("");
    // txtItemName.setText("");
    // txtItemPrice.setText("");
    // // txtItemTotal.setText("");
    // txtPhoneCheck.setText("");
    // jSpinner.setValue(0);
    // DefaultTableModel tableOrder = (DefaultTableModel) tblOrder.getModel();
    // tableOrder.setRowCount(0);

    // tblProduct.clearSelection();
    // tblPhoneAndName.clearSelection();
    // itemTotal = 0;
    // tongTien = 0;
    // txtVoucher.setText("");
    // txtValue.setText("");
    // txtRate.setText("");
    // lblGrandTotal.setText("0");
    // }

    public void clearItemFields() {
        txtItemName.setText("");
        txtItemPrice.setText("");
        spinnerQuantity.setValue(0);
        txtItemTotal.setText("");
        tblProduct.clearSelection();
        btnThem.setEnabled(false);
    }

    public void clearDiscountField() {
        txtVoucherCheck.setText("");
        txtVoucher.setText("");
        txtValue.setText("");
        lblGrandTotal.setText("0");
        tblDiscount.clearSelection();
    }

    public void clearCustomerField() {
        txtCustomerName.setText("");
        txtCustomerPhone.setText("");
        txtPhoneCheck.setText("");
        tblPhoneAndName.clearSelection();
        DefaultTableModel tableOrder = (DefaultTableModel) tblOrder.getModel();
        tableOrder.setRowCount(0);
        txtRate.setText("");
    }

    public void validateField() {
        String customerName = txtCustomerName.getText();
        if (!customerName.equals("") && tongTien > 0) {
            btnPrintBill.setEnabled(true);
        } else
            btnPrintBill.setEnabled(false);
    }

    public void getNameByPhone(String phone) {
        DefaultTableModel dtm = (DefaultTableModel) tblPhoneAndName.getModel();
        dtm.setRowCount(0);
        ArrayList<CustomerModel> list = CustomerDAO.getNameByPhone(phone);
        Iterator<CustomerModel> itr = list.iterator();
        while (itr.hasNext()) {
            CustomerModel customerObj = itr.next();
            dtm.addRow(new Object[] { customerObj.getCustomerPhone(), customerObj.getCustomerName() });
        }
    }

    public void getVoucher(String code) {
        DefaultTableModel dtm = (DefaultTableModel) tblDiscount.getModel();
        dtm.setRowCount(0);
        ArrayList<DiscountModel> list = DiscountDao.getVoucher(code);
        Iterator<DiscountModel> itr = list.iterator();
        while (itr.hasNext()) {
            DiscountModel discountObj = itr.next();
            dtm.addRow(new Object[] { discountObj.getDiscountName(), discountObj.getDiscountQuantity(),
                    discountObj.getDiscountValue() });
        }
    }

    public void updateTable(ArrayList<DiscountModel> list) {
        DefaultTableModel model = (DefaultTableModel) tblDiscount.getModel();
        model.setRowCount(0);

        Iterator<DiscountModel> itr = list.iterator();
        while (itr.hasNext()) {
            DiscountModel discountObj = itr.next();
            model.addRow(new Object[] { discountObj.getDiscountName(), discountObj.getDiscountQuantity(),
                    discountObj.getDiscountValue() });
        }
    }

    public void itemNameByCategory(String category) {
        DefaultTableModel dtm = (DefaultTableModel) tblProduct.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.getAllRecordsByCategory(category);
        Iterator<FoodItemModel> itr = list.iterator();
        while (itr.hasNext()) {
            FoodItemModel fooditemObj = itr.next();
            dtm.addRow(new Object[] { fooditemObj.getItemName() });
        }
    }

    public void filterFoodItemByName(String name, String category) {
        DefaultTableModel dtm = (DefaultTableModel) tblProduct.getModel();
        dtm.setRowCount(0);
        ArrayList<FoodItemModel> list = FoodItemDao.fillterFoodItemByName(name, category);
        Iterator<FoodItemModel> itr = list.iterator();
        while (itr.hasNext()) {
            FoodItemModel fooditemObj = itr.next();
            dtm.addRow(new Object[] { fooditemObj.getItemName() });
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lbPhoneCheck = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPhoneAndName = new javax.swing.JTable();
        txtPhoneCheck = new javax.swing.JTextField();
        checkBoxNewCustomer = new javax.swing.JCheckBox();
        lbCategory = new javax.swing.JLabel();
        comboBoxCategory = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        lbItemName = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        lbQuantity = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        spinnerQuantity = new javax.swing.JSpinner();
        txtItemPrice = new javax.swing.JTextField();
        txtItemTotal = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnPrintBill = new javax.swing.JButton();
        lbTotalMoney = new javax.swing.JLabel();
        lblGrandTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtCustomerName = new javax.swing.JTextField();
        txtCustomerPhone = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        lbPhone = new javax.swing.JLabel();
        lbInfo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbVoucher = new javax.swing.JLabel();
        txtVoucherCheck = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDiscount = new javax.swing.JTable();
        lbDiscountApplied = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        txtValue = new javax.swing.JTextField();
        lbValue = new javax.swing.JLabel();
        lbPercent = new javax.swing.JLabel();
        btnRefreshDiscount = new javax.swing.JButton();
        lbType = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        txtRate = new javax.swing.JTextField();
        lbIcon = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 222, 191));
        setMinimumSize(new java.awt.Dimension(1100, 630));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitle.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(195, 55, 55));
        lbTitle.setText("ORDER");
        getContentPane().add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 140, -1));

        jPanel1.setBackground(new java.awt.Color(255, 222, 191));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 68, 243, 0));

        lbPhoneCheck.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPhoneCheck.setText("Nhập số điện thoại");
        jPanel1.add(lbPhoneCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 30));

        tblPhoneAndName.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null },
                        { null, null }
                },
                new String[] {
                        "Số điện thoại", "Họ tên"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblPhoneAndName.setRowHeight(30);
        tblPhoneAndName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhoneAndNameMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPhoneAndName);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 250, 180));

        txtPhoneCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneCheckActionPerformed(evt);
            }
        });
        txtPhoneCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneCheckKeyReleased(evt);
            }
        });
        jPanel1.add(txtPhoneCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 30));

        checkBoxNewCustomer.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        checkBoxNewCustomer.setText("KH mới");
        checkBoxNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxNewCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(checkBoxNewCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 80, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 270, 270));

        lbCategory.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbCategory.setText("Chọn danh mục");
        getContentPane().add(lbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        comboBoxCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCategoryActionPerformed(evt);
            }
        });
        getContentPane().add(comboBoxCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 260, 30));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null }
                },
                new String[] {
                        "Tên sản phẩm"
                }) {
            boolean[] canEdit = new boolean[] {
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblProduct.setRowHeight(30);
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 260, 170));

        lbItemName.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbItemName.setText("Tên");
        getContentPane().add(lbItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 37, -1));

        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 180, 30));

        lbQuantity.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbQuantity.setText("Số lượng");
        getContentPane().add(lbQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 70, -1));

        lbPrice.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbPrice.setText("Giá");
        getContentPane().add(lbPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 43, 20));

        lbTotal.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lbTotal.setText("Tổng cộng");
        getContentPane().add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 80, -1));

        spinnerQuantity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerQuantityStateChanged(evt);
            }
        });
        getContentPane().add(spinnerQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 180, 30));

        txtItemPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemPriceActionPerformed(evt);
            }
        });
        getContentPane().add(txtItemPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 180, 30));

        txtItemTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemTotalActionPerformed(evt);
            }
        });
        getContentPane().add(txtItemTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 180, 30));

        btnThem.setBackground(new java.awt.Color(255, 153, 153));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        getContentPane().add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 80, -1));

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, 80, -1));

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Tên sản phẩm", "Giá", "Số lượng", "Tổng cộng"
                }));
        tblOrder.setRowHeight(30);
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOrder);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 480, 190));

        btnPrintBill.setBackground(new java.awt.Color(204, 255, 204));
        btnPrintBill.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrintBill.setText("Print Bill");
        btnPrintBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintBillActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrintBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 540, 90, -1));

        lbTotalMoney.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        lbTotalMoney.setForeground(new java.awt.Color(204, 0, 0));
        lbTotalMoney.setText("Tổng tiền");
        getContentPane().add(lbTotalMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 100, -1));

        lblGrandTotal.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        lblGrandTotal.setText("000");
        getContentPane().add(lblGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 222, 191));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCustomerName.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        txtCustomerName.setEnabled(false);
        jPanel3.add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 250, 30));

        txtCustomerPhone.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        txtCustomerPhone.setEnabled(false);
        txtCustomerPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerPhoneKeyReleased(evt);
            }
        });
        jPanel3.add(txtCustomerPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 250, 30));

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setText("Tên khách hàng");
        jPanel3.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 129, 30));

        lbPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPhone.setText("Số điện thoại");
        jPanel3.add(lbPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 158, -1));

        lbInfo.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lbInfo.setText("- Thông tin khách hàng -");
        jPanel3.add(lbInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 190, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 243, 0));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 270, 180));

        lbVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbVoucher.setText("Nhập voucher");
        getContentPane().add(lbVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, 30));

        txtVoucherCheck.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        txtVoucherCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoucherCheckActionPerformed(evt);
            }
        });
        txtVoucherCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVoucherCheckKeyReleased(evt);
            }
        });
        getContentPane().add(txtVoucherCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 150, 30));

        tblDiscount.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Mã voucher", "Số lượng", "Giá trị (%)"
                }));
        tblDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiscountMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDiscount);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 420, 110));

        lbDiscountApplied.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDiscountApplied.setText("Mã voucher áp dụng");
        getContentPane().add(lbDiscountApplied, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, -1, 20));

        txtVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoucherActionPerformed(evt);
            }
        });
        getContentPane().add(txtVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, 180, -1));

        txtValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValueActionPerformed(evt);
            }
        });
        getContentPane().add(txtValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, 150, -1));

        lbValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbValue.setText("Giá trị");
        getContentPane().add(lbValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 500, 50, -1));

        lbPercent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPercent.setText("%");
        getContentPane().add(lbPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 500, 30, 20));

        btnRefreshDiscount.setBackground(new java.awt.Color(204, 255, 255));
        btnRefreshDiscount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefreshDiscount.setText("Refresh");
        btnRefreshDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshDiscountActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 480, 92, -1));

        lbType.setFont(new java.awt.Font("Open Sans", 2, 14)); // NOI18N
        lbType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbType.setText("Admin");
        getContentPane().add(lbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 80, 30));

        btnBack.setBackground(new java.awt.Color(255, 255, 204));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setText("Trở về");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(1011, 30, 80, 28));

        txtRate.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        txtRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRate.setEnabled(false);
        getContentPane().add(txtRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 310, 140, 30));

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pinglogo.png"))); // NOI18N
        getContentPane().add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 80));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bgyellow.png"))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        homeView = new HomeView();
        if (UserDAO.getType().equals("employee")) {
            homeView.EmployeeView();
        }
        homeView.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnBackActionPerformed

    private void txtCustomerPhoneKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCustomerPhoneKeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txtCustomerPhoneKeyReleased

    private void checkBoxNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_checkBoxNewCustomerActionPerformed
        // TODO add your handling code here:
        if (checkBoxNewCustomer.isSelected()) {
            txtCustomerName.setEnabled(true);
            txtCustomerName.setText("");
            txtCustomerPhone.setText("");
            txtRate.setText("");
            tblPhoneAndName.clearSelection();
        } else {
            txtCustomerName.setEnabled(false);
            txtCustomerPhone.setEnabled(false);
        }
    }// GEN-LAST:event_checkBoxNewCustomerActionPerformed

    private void comboBoxCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String category = (String) comboBoxCategory.getSelectedItem();
        itemNameByCategory(category);

    }// GEN-LAST:event_jComboBox1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String name = txtItemName.getText();
        String price = txtItemPrice.getText();
        String quantity = String.valueOf(spinnerQuantity.getValue());
        DefaultTableModel dtm = (DefaultTableModel) tblOrder.getModel();
        dtm.addRow(new Object[] { name, price, quantity, itemTotal });
        tongTien = tongTien + itemTotal;

        lblGrandTotal.setText(String.valueOf(tongTien));

        clearItemFields();
        validateField();
    }// GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnXoaActionPerformed

    private void txtItemTotalActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtItemTotalActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtItemTotalActionPerformed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtItemNameActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        // lblbill.setText(BillDao.getBillID());
        billId = BillDao.getBillID() + 1;
        ArrayList<CategoryModel> list = CategoryDAO.getAllRecords();
        Iterator<CategoryModel> itr = list.iterator();
        while (itr.hasNext()) {
            CategoryModel categoryObj = itr.next();
            comboBoxCategory.addItem(categoryObj.getCategoryName());
        }
        String category = (String) comboBoxCategory.getSelectedItem();
        itemNameByCategory(category);
        getNameByPhone("");
        getVoucher("");
    }// GEN-LAST:event_formComponentShown

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblProductMouseClicked
        // TODO add your handling code here:
        int index = tblProduct.getSelectedRow();
        TableModel model = tblProduct.getModel();
        String ItemName = model.getValueAt(index, 0).toString();
        FoodItemModel fooditem = FoodItemDao.getFoodItemByName(ItemName);
        txtItemName.setText(fooditem.getItemName());
        txtItemPrice.setText(fooditem.getItemPrice());
        spinnerQuantity.setValue(1);
        txtItemTotal.setText(fooditem.getItemPrice());
        itemPrice = Integer.parseInt(fooditem.getItemPrice());
        itemTotal = Integer.parseInt(fooditem.getItemPrice());
        btnThem.setEnabled(true);
    }// GEN-LAST:event_tblProductMouseClicked

    private void spinnerQuantityStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_jSpinnerStateChanged
        // TODO add your handling code here:
        int quantity = (Integer) spinnerQuantity.getValue();
        if (quantity <= 1) {
            spinnerQuantity.setValue(1);
            quantity = 1;

        }

        itemTotal = itemPrice * quantity;
        txtItemTotal.setText(String.valueOf(itemTotal));
    }// GEN-LAST:event_jSpinnerStateChanged

    private void txtItemPriceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtItemPriceActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtItemPriceActionPerformed

    private void txtPhoneCheckActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPhoneCheckActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtPhoneCheckActionPerformed

    private void txtPhoneCheckKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtPhoneCheckKeyReleased
        // TODO add your handling code here:
        String phone = txtPhoneCheck.getText();
        getNameByPhone(phone);

    }// GEN-LAST:event_txtPhoneCheckKeyReleased

    private void tblPhoneAndNameMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblPhoneAndNameMouseClicked
        // TODO add your handling code here:
        int index = tblPhoneAndName.getSelectedRow();
        TableModel model = tblPhoneAndName.getModel();
        String phone = model.getValueAt(index, 0).toString();
        String name = model.getValueAt(index, 1).toString();
        CustomerModel customer = CustomerDAO.getCustomerByPhone(phone);
        txtCustomerPhone.setText(phone);
        txtCustomerPhone.setEnabled(false);
        txtCustomerName.setEnabled(false);
        txtCustomerName.setText(name);
        txtRate.setText(customer.getCustomerRate());
        checkBoxNewCustomer.setSelected(false);
    }// GEN-LAST:event_tblPhoneAndNameMouseClicked

    private int thanhtien() {
        if (txtValue.getText().equals(""))
            return tongTien;
        else {
            int discountValue = Integer.parseInt(txtValue.getText());
            int thanhtien = tongTien - tongTien * discountValue / 100;
            return thanhtien;
        }

    }

    public void showBill(BillModel bill) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(new Date());
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
        String path = "D:\\Bill\\";
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "" + billId + ".pdf"));
            doc.open();
            Paragraph cafeName = new Paragraph("				PING Coffee\n");
            doc.add(cafeName);
            Paragraph starLine = new Paragraph(
                    "*------------------------------------------------------------------------------------------------------------------------*");
            doc.add(starLine);
            Paragraph paragraph = new Paragraph(
                    "\tBill Id:" + billId + "\nName: " + bill.getCustomerName() + "\n" + "Gia tien"
                            + tongTien + "\n");
            doc.add(paragraph);
            PdfPTable tbl = new PdfPTable(4);
            tbl.addCell("Name");
            tbl.addCell("Price");
            tbl.addCell("Quantity");
            tbl.addCell("Total");
            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                String n = tblOrder.getValueAt(i, 0).toString();
                String p = tblOrder.getValueAt(i, 1).toString();
                String q = tblOrder.getValueAt(i, 2).toString();
                String t = tblOrder.getValueAt(i, 3).toString();
                tbl.addCell(n);
                tbl.addCell(p);
                tbl.addCell(q);
                tbl.addCell(t);
            }
            doc.add(tbl);
            doc.add(starLine);
            Paragraph paragraph1 = new Paragraph("\n\tap dung voucher: " + thanhtien());

            if (thanhtien() != 0)
                doc.add(paragraph1);
            Paragraph msgtks = new Paragraph("Cam on va hen gap lai");
            doc.add(msgtks);
            OpenFilePdf.openById(String.valueOf(billId));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, sqlDate);
        }
        doc.close();

    }

    public void addBillDetail(BillModel bill) {
        for (int i = 0; i < tblOrder.getRowCount(); i++) {
            BillDetailModel billDetail = new BillDetailModel();
            String itemName = tblOrder.getValueAt(i, 0).toString();
            int itemID = FoodItemDao.getFoodItemByName(itemName).getItemID();
            int itemQuantity = Integer.parseInt((String) tblOrder.getValueAt(i, 2));
            int itemTotal = (int) tblOrder.getValueAt(i, 3);
            System.out.println(itemName + "-" + itemID + " -" + itemQuantity + " -" + itemTotal);
            billDetail.setBillID(bill.getBillID());
            billDetail.setItemName(itemName);
            billDetail.setDetailQuantity(itemQuantity);
            billDetail.setTotalPrice(itemTotal);
            billDetail.setItemID(itemID);
            BillDetailDAO.addBillDetail(billDetail);
        }
    }

    private void btnPrintBillActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPrintBillActionPerformed
        // TODO add your handling code here:
        // int discountValue = Integer.parseInt(txtValue.getText());
        // int thanhtien = tongTien - tongTien*discountValue/100;
        String customerName = txtCustomerName.getText();
        String customerMobileNumber = txtCustomerPhone.getText();
        if (customerMobileNumber.isEmpty()) {
            customerMobileNumber = "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(new Date());
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
        String total = String.valueOf(tongTien);
        BillModel bill = new BillModel();
        bill.setBillID(billId);
        bill.setCustomerName(customerName);
        bill.setCustomerPhone(customerMobileNumber);
        bill.setBillDate(sqlDate);
        bill.setBillPrice(thanhtien());
        BillDao.save(bill);
        addBillDetail(bill);
        BillDao.updateCustomerPoint(bill);
        DiscountDao.updateQuantityVoucher(txtVoucher.getText());
        String path = "D:\\Bill\\";
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "" + billId + ".pdf"));
            doc.open();
            Paragraph cafeName = new Paragraph("				PING Coffee\n");
            doc.add(cafeName);
            Paragraph starLine = new Paragraph(
                    "*------------------------------------------------------------------------------------------------------------------------*");
            doc.add(starLine);
            Paragraph paragraph = new Paragraph(
                    "\tBill Id:" + billId + "\nName: " + customerName + "\nGia tien: " + tongTien + "\n\n");
            doc.add(paragraph);
            PdfPTable tbl = new PdfPTable(4);
            tbl.addCell("Name");
            tbl.addCell("Price");
            tbl.addCell("Quantity");
            tbl.addCell("Total");
            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                String n = tblOrder.getValueAt(i, 0).toString();
                String p = tblOrder.getValueAt(i, 1).toString();
                String q = tblOrder.getValueAt(i, 2).toString();
                String t = tblOrder.getValueAt(i, 3).toString();
                tbl.addCell(n);
                tbl.addCell(p);
                tbl.addCell(q);
                tbl.addCell(t);
            }
            doc.add(tbl);
            doc.add(starLine);
            Paragraph paragraph1 = new Paragraph("\n\tThanh tien sau khi ap dung voucher: " + thanhtien());

            if (thanhtien() != 0)
                doc.add(paragraph1);
            Paragraph msgtks = new Paragraph("\nCam on va hen gap lai");
            doc.add(msgtks);
            OpenFilePdf.openById(String.valueOf(billId));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, sqlDate);
        }
        doc.close();
        // setVisible(false);
        ArrayList<DiscountModel> list = DiscountDao.getVoucher("");
        updateTable(list);
        // new OrderView().setVisible(true);
        billId++;
        tongTien = 0;
        clearItemFields();
        clearDiscountField();
        clearCustomerField();
    }// GEN-LAST:event_btnPrintBillActionPerformed

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblOrderMouseClicked
        // TODO add your handling code here:
        int index = tblOrder.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Xoa san pham da chon", "Select",
                JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            TableModel model = tblOrder.getModel();
            String total = model.getValueAt(index, 3).toString();
            tongTien = tongTien - Integer.parseInt(total);
            lblGrandTotal.setText(String.valueOf(tongTien));
            ((DefaultTableModel) tblOrder.getModel()).removeRow(index);
        }
        if (tblOrder.getRowCount() == 0) {
            btnPrintBill.setEnabled(false);
        }
    }// GEN-LAST:event_tblOrderMouseClicked

    private void txtVoucherCheckActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtVoucherCheckActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_txtVoucherCheckActionPerformed

    private void txtVoucherActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtVoucherActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtVoucherActionPerformed

    private void txtVoucherCheckKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtVoucherCheckKeyReleased
        // TODO add your handling code here:
        String code = txtVoucherCheck.getText();
        getVoucher(code);
    }// GEN-LAST:event_txtVoucherCheckKeyReleased

    private void txtValueActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtValueActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtValueActionPerformed

    private void tblDiscountMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblDiscountMouseClicked
        // TODO add your handling code here:
        int index = tblDiscount.getSelectedRow();
        TableModel model = tblDiscount.getModel();
        String name = model.getValueAt(index, 0).toString();
        String value = model.getValueAt(index, 2).toString();
        // int discountValue = Integer.parseInt(txtValue.getText());
        // tongTien = tongTien - tongTien*discountValue/100;
        txtVoucher.setText(name);
        txtValue.setText(value);
        int discountValue = Integer.parseInt(txtValue.getText());
        int thanhtien = tongTien - tongTien * discountValue / 100;
        lblGrandTotal.setText(String.valueOf(thanhtien));
    }// GEN-LAST:event_tblDiscountMouseClicked

    private void btnRefreshDiscountActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRefreshDiscountActionPerformed
        // TODO add your handling code here:
        clearDiscountField();
        lblGrandTotal.setText(String.valueOf(tongTien));
    }// GEN-LAST:event_btnRefreshDiscountActionPerformed

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
            java.util.logging.Logger.getLogger(OrderView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrintBill;
    private javax.swing.JButton btnRefreshDiscount;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox checkBoxNewCustomer;
    private javax.swing.JComboBox<String> comboBoxCategory;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbDiscountApplied;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPercent;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbPhoneCheck;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbQuantity;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTotalMoney;
    private javax.swing.JLabel lbType;
    private javax.swing.JLabel lbValue;
    private javax.swing.JLabel lbVoucher;
    private javax.swing.JLabel lblGrandTotal;
    private javax.swing.JSpinner spinnerQuantity;
    private javax.swing.JTable tblDiscount;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblPhoneAndName;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtCustomerPhone;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemPrice;
    private javax.swing.JTextField txtItemTotal;
    private javax.swing.JTextField txtPhoneCheck;
    private javax.swing.JTextField txtRate;
    private javax.swing.JTextField txtValue;
    private javax.swing.JTextField txtVoucher;
    private javax.swing.JTextField txtVoucherCheck;
    // End of variables declaration//GEN-END:variables
}
