package model;

public class CategoryModel {
    private int categoryID;
    private String categoryName;

    public CategoryModel() {
    }
    public CategoryModel(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    public int getCategoryID() {
        return this.categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
