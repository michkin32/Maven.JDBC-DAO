package daos;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/Jbdc";
    public static final String USER = "testuser";
    public static final String PASS = "testPass";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] args)  {
        Connection connection = MakeConnection.getConnection();
    }
}