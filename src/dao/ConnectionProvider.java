package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static Properties properties;
    private static Connection connection;

    static {
        properties = loadProperties();
        registerDriver();
    }

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("connection.properties")) {
            Properties props = new Properties();
            props.load(fis);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc tệp cấu hình kết nối.", e);
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy driver JDBC của MySQL.", e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            String host = properties.getProperty("ServerID");
            String port = properties.getProperty("Port");
            String dbname = properties.getProperty("Database");
            String username = properties.getProperty("Username");
            String password = properties.getProperty("Password");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException("Không thể kết nối đến cơ sở dữ liệu MySQL.", e);
            }
        }
        return connection;
    }

    public static void closeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException("Đóng kết nối không thành công.", e);
            }
        }
    }
}
