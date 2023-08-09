/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HUY THUC
 */
public class FoodItemModel {
    private int itemID;
    private String itemName;
    private String categoryName;
    private String itemPrice;   

    public FoodItemModel() {
    }
    public FoodItemModel(int id, String itemName, String itemPrice, String categoryName) {
        this.itemID = id;
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.itemPrice = itemPrice;
    }
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    
}
