import java.sql.*;

public class DatabaseConnection { // this class will be used for checking the database connectivity

    Connection conn;
    Statement stmt;

    public DatabaseConnection connect() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
            Statement stmt = conn.createStatement();
        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
        }
        return this;
    }
}
