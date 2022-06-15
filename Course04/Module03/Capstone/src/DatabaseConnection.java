import java.sql.*;

public class DatabaseConnection { 

    Connection conn;
    Statement stmt;
    PreparedStatement preparedStmt;
    ResultSet rs;

    public DatabaseConnection dbConnect() { //for connecting to the database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("INVALID! " + e);
        }
        return this;
    }

    public void checkPolicyStatus () { //updates the table to set the status row in policy table given the conditions below
        dbConnect();
        try {
            stmt.executeUpdate("UPDATE policy SET status = "
            + "\nCASE "
            + "\nWHEN effectdate > CURRENT_DATE THEN 'Inactive'"
            + "\nWHEN effectdate = CURRENT_DATE THEN 'Active'"
            + "\nWHEN effectdate < CURRENT_DATE AND expiredate > CURRENT_DATE THEN 'Active'"
            + "\nWHEN expiredate < CURRENT_DATE THEN 'Expired'"
            + "\nEND "
            + "\nWHERE status != 'Cancelled' OR status IS NULL");
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
