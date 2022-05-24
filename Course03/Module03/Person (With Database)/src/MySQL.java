import java.sql.*;

public class MySQL {

    // initialization for mysql used for connection later

    private String dbURL = "jdbc:mysql://localhost:3306/";
    private String dbName = "sampleejava";
    private final String USER = "root";
    private final String PASS = "";
    private String id, firstname, lastname;

    public void viewAll() { // DISPLAYS ALL RESULTS FROM YOUR DATABASE TABLE

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(dbURL + dbName, USER, PASS);

            stmt = conn.createStatement();
            String sql = "select * from Person";
            ResultSet rs = stmt.executeQuery(sql);

            // Query results
            System.out.printf("%-5s %-12s %-12s\n", "ID", "FIRSTNAME", "LASTNAME");
            while (rs.next()) {

                // The field type needs to be specified
                id = rs.getString("id");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");

                // Print information
                System.out.printf("%-5s %-12s %-12s", id, firstname, lastname);
                System.out.print("\n");
            }
            // Disconnect database
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // JDBC error
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection again
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

    }

    public void distinctView() { // DISPLAYS ALL RESULTS FROM YOUR DATABASE TABLE

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(dbURL + dbName, USER, PASS);

            stmt = conn.createStatement();
            String sql = "select distinct firstname from person";
            ResultSet rs = stmt.executeQuery(sql);

            // Query results
            System.out.println ("FIRSTNAME");
            while (rs.next()) {

                // The field type needs to be specified
                firstname = rs.getString("firstname");

                // Print information
                System.out.println(firstname);
            }
            // Disconnect database
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // JDBC error
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection again
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

    }
}
