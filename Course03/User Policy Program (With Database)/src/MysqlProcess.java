import java.sql.*;

public class MysqlProcess {

    //initialization for mysql used for connection later

    private String dbURL = "jdbc:mysql://localhost:3306/";
    private String dbName = "sampleejava";
    private final String USER = "root";
    private final String PASS = "";

    private DisplayDesign go = new DisplayDesign();
    private String policyNo, userId, dateReg, policyTypeId;

    public void viewspecific(String value) { //method for searching a value from mysql tables
        go.clearConsole();
        go.printBox("RESULTS where date_registered is less than: " + value);
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(dbURL + dbName, USER, PASS);

            stmt = conn.createStatement();
            String sql = "select * from user_policies where date_registered <= '" + value + "' ";
            ResultSet rs = stmt.executeQuery(sql);

            // Query results
            while (rs.next()) {
                // The field type needs to be specified
                policyNo = rs.getString("policy_no");
                userId = rs.getString("user_id");
                dateReg = rs.getString("date_registered");
                policyTypeId = rs.getString("policy_type_id");
                // Print information
                System.out.printf("%-10s %-10s %-19s %-14s", "POLICY #", "USER ID", "DATE REGISTERED",
                        "POLICY TYPE ID" + "\n");
                System.out.printf("%-10s %-10s %-19s %-14s", policyNo, userId, dateReg, policyTypeId);
                System.out.print("\n");
            }

            // Disconnect database
            rs.close();
            stmt.close();
            conn.close();

        }

        catch (SQLException se) {
            // JDBC error
            se.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            // Close the database connection again
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

    }

    public void viewAll() { // DISPLAYS ALL RESULTS FROM YOUR DATABASE TABLE
        go.clearConsole();
        go.printBox("VIEW ALL RESULTS");

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(dbURL + dbName, USER, PASS);

            stmt = conn.createStatement();
            String sql = "select * from user_policies";
            ResultSet rs = stmt.executeQuery(sql);

            // Query results
            System.out.printf("%-10s %-10s %-19s %-14s", "POLICY #", "USER ID", "DATE REGISTERED",
                    "POLICY TYPE ID" + "\n");
            while (rs.next()) {

                // The field type needs to be specified
                policyNo = rs.getString("policy_no");
                userId = rs.getString("user_id");
                dateReg = rs.getString("date_registered");
                policyTypeId = rs.getString("policy_type_id");

                // Print information
                System.out.printf("%-10s %-10s %-19s %-14s", policyNo, userId, dateReg, policyTypeId);
                System.out.print("\n");
            }

            // Disconnect database
            rs.close();
            stmt.close();
            conn.close();

        }

        catch (SQLException se) {
            // JDBC error
            se.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
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
