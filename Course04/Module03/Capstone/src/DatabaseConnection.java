import java.sql.*;

public class DatabaseConnection { //this class is all about connecting to database, and updates table from every refresh in main menu
    Connection conn;
    Statement stmt, stmt2, stmt3;
    PreparedStatement preparedStmt;
    ResultSet rs, rs2, rs3;
    ResultSetMetaData metadata;
    String dbName = "PAS";

    DisplayDesign go = new DisplayDesign();

    protected void checkDatabase() { // this method will check if database exists
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + ". A new database will be created.");
            createDatabase(); // calls this method if no database found with the given database name
        }
    }

    protected void createDatabase() { // creates a new database and table if there is no database found by default
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            stmt = conn.createStatement();
            // these are the queries in order to create a database and tables
            stmt.executeUpdate("CREATE DATABASE " + dbName);

            stmt.executeUpdate("USE " + dbName);

            stmt.executeUpdate("CREATE TABLE `accident` ("
                    + "          `claimnumber` int(5) NOT NULL AUTO_INCREMENT,"
                    + "          `date` varchar(200) NOT NULL,"
                    + "          `address` varchar(200) NOT NULL,"
                    + "          `accidentdescription` longtext NOT NULL,"
                    + "          `damagedescription` longtext NOT NULL,"
                    + "          `repaircost` double NOT NULL,"
                    + "          `policy` int(50) NOT NULL,"
                    + "          PRIMARY KEY (`claimnumber`),"
                    + "          UNIQUE KEY `policy` (`policy`)"
                    + "        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

            stmt.executeUpdate("CREATE TABLE `customer` ("
                    + "          `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "          `firstname` varchar(50) NOT NULL,"
                    + "          `lastname` varchar(50) NOT NULL,"
                    + "          `address` longtext NOT NULL,"
                    + "          PRIMARY KEY (`id`),"
                    + "          UNIQUE KEY `firstname` (`firstname`)"
                    + "        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

            stmt.executeUpdate("CREATE TABLE `policy` ("
                    + "          `policynumber` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "          `effectdate` date NOT NULL,"
                    + "          `expiredate` date NOT NULL,"
                    + "          `policyowner` int(4) DEFAULT NULL,"
                    + "          `policypremium` double DEFAULT NULL,"
                    + "          `status` varchar(50) DEFAULT NULL,"
                    + "          UNIQUE KEY `policynumber` (`policynumber`)"
                    + "        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

            stmt.executeUpdate("CREATE TABLE `policyholder` ("
                    + "          `id` int(11) NOT NULL AUTO_INCREMENT,"
                    + "          `firstname` varchar(50) NOT NULL,"
                    + "          `lastname` varchar(50) NOT NULL,"
                    + "          `birthdate` varchar(50) NOT NULL,"
                    + "          `address` longtext NOT NULL,"
                    + "          `licensenumber` varchar(50) NOT NULL,"
                    + "          `licensedate` date NOT NULL,"
                    + "          `policy` int(255) DEFAULT NULL,"
                    + "          `policyowner` int(255) NOT NULL,"
                    + "          PRIMARY KEY (`id`),"
                    + "          UNIQUE KEY `licensenumber` (`licensenumber`)"
                    + "        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

            stmt.executeUpdate("CREATE TABLE `vehicle` ("
                    + "          `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "          `brand` varchar(20) NOT NULL,"
                    + "          `model` varchar(20) NOT NULL,"
                    + "          `year` year(4) NOT NULL,"
                    + "          `type` varchar(50) NOT NULL,"
                    + "          `fuel` varchar(50) NOT NULL,"
                    + "          `price` double NOT NULL,"
                    + "          `color` varchar(50) NOT NULL,"
                    + "          `premiumcharge` double NOT NULL,"
                    + "          `policyholder` int(255) NOT NULL,"
                    + "          UNIQUE KEY `id` (`id`)"
                    + "        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

            go.pauseClear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected DatabaseConnection dbConnect() { // for connecting to the database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.out.println("INVALID! " + e.getMessage());
        } 
        finally {
            try {
                if (rs != null && rs2 != null && rs3 != null ){
                if (rs.next()) {
                        rs.isBeforeFirst();
                }
                if (rs2.next()) {
                        rs2.isBeforeFirst();
                }
                if (rs3.next()) {
                        rs3.isBeforeFirst();
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        }
        return this;
    }

    protected void checkPolicyStatus() { // updates the table to set the status row in policy table given the conditions
                                         // below
        dbConnect();
        try {
            stmt.executeUpdate("UPDATE policy SET status = "
                    + "\nCASE "
                    + "\nWHEN effectdate > CURRENT_DATE THEN 'Inactive'"
                    + "\nWHEN effectdate = CURRENT_DATE THEN 'Active'"
                    + "\nWHEN effectdate < CURRENT_DATE AND expiredate > CURRENT_DATE THEN 'Active'"
                    + "\nWHEN expiredate <= CURRENT_DATE THEN 'Expired'"
                    + "\nEND "
                    + "\nWHERE (status != 'Cancelled' AND status != 'Claimed') OR status IS NULL");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
