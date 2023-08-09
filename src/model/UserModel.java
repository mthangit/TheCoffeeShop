package model;

public class UserModel {
    private String username;
    private String password;
    private String type;
    private int employeeID;

    public UserModel(String username, String password, String type, int employeeID) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.employeeID = employeeID;
    }

    public UserModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public String toString() {
        String result = "Username: " + username + "\n" +
                "Password: " + password + "\n" +
                "Type: " + type;
        return result;
    }
}
