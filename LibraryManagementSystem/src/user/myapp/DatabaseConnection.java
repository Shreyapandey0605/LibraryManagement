package user.myapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection conn = null;
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/lib"; // Replace with your DB URL
    private static final String USER = "root";  // Replace with your database username
    private static final String PASSWORD = "123456";  // Replace with your database password

    
    // Method to get the connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);  // Create the connection
    }
}

