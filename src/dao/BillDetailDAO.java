package dao;

import model.BillDetailModel;

public class BillDetailDAO {
    public static boolean addBillDetail(BillDetailModel billDetail){
        String query = "INSERT INTO bill_detail VALUES ('" + billDetail.getBillID() + "', '" + billDetail.getItemName() + "', '" + billDetail.getDetailQuantity() + "', '" + billDetail.getTotalPrice() + "', '" + billDetail.getItemID() + "')";
        DbOperations.setDataOrDelete(query, "");
        // String query1 = "Call SP_UpdateItemID('" + billDetail.getItemName() + "');";
        // DbOperations.setDataOrDelete(query1, "");
        return true;
    }

    public static boolean deleteBillDetailByID(int billID){
        String query = "DELETE FROM bill_detail WHERE billID = '" + billID + "'";
        DbOperations.setDataOrDelete(query, "");
        return true;
    }
}
