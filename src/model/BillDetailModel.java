package model;

public class BillDetailModel{
    private int billID;
    private String itemName;
    private int totalPrice;
    private int detailQuantity;
    private int itemID;

    public BillDetailModel(int billID, String itemName, int itemPrice, int detailQuantity, int itemID) {
        this.billID = billID;
        this.itemName = itemName;
        this.totalPrice = itemPrice;
        this.detailQuantity = detailQuantity;
        this.itemID = itemID;
    }

    public BillDetailModel() {
    }

    public int getBillID() {
        return billID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
    
    public int getDetailQuantity() {
        return detailQuantity;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTotalPrice(int itemPrice) {
        this.totalPrice = itemPrice;
    }

    public void setDetailQuantity(int detailQuantity) {
        this.detailQuantity = detailQuantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
