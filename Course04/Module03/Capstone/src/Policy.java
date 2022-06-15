import java.sql.*;
import java.util.*;

public class Policy extends DatabaseConnection {
    private Scanner sc = new Scanner(System.in);
    private Calendar calendar = Calendar.getInstance();
    private DisplayDesign go = new DisplayDesign();

    public void createPolicy() {
        calendar = Calendar.getInstance();
        PolicyHolder holder = new PolicyHolder();
        int searchAcc, accNum, month, day, year, monthExp, policyID;
        go.clearConsole();

        try {
            go.printBox("QUOTE POLICY");
            System.out.print("Enter account number: ");
            searchAcc = sc.nextInt();

            accNum = searchAccount(searchAcc); // search for an account in database table

            if (accNum != 0) {

                while (true) {
                    try {
                        go.clearConsole();
                        go.printBox("QUOTE POLICY FOR ACCOUNT #" + accNum);

                        System.out.println("POLICY EFFECTIVE DATE");
                        System.out.print("Month [1-12]: ");
                        month = sc.nextInt() - 1; // minus 1 because january starts with index 0

                        System.out.print("Day [1-31]: ");
                        day = sc.nextInt();

                        System.out.print("Year [" + calendar.get(Calendar.YEAR) + " onwards, 4 digits]: ");
                        year = sc.nextInt();

                        if ((month > 12 && month <= 0) || (day > 31 && day <= 0) || String.valueOf(year).length() > 4) { // if user puts incorrect date
                            System.out.println("INVALID input! Please follow instructions.");
                            go.pauseClear();
                        }


                        else {

                            if ((month < (calendar.get(Calendar.MONTH) + 1)) &&
                                    (day < calendar.get(Calendar.DATE)) && (year <= calendar.get(Calendar.YEAR))) {
                                System.out.println("INVALID! You must put a date later than the current day!");
                                go.pauseClear();
                            } else {
                                monthExp = month + 6; // sets the expiry month

                                policyID = processPolicyQuery(month, day, year, monthExp, accNum); // gets the policy
                                                                                                   // number from return
                                                                                                   // value
                                // passes the account number and policy number to be inserted in the
                                // policyholder table
                                go.pauseClear();

                                holder.policyHolder(accNum, policyID); // calls the policyholder class and sends the
                                                                       // parameter's values to it
                                break;
                            }
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("INVALID input! Try again.");
                        sc.nextLine();
                        go.pauseClear();
                    }
                }

            }

            else {
                System.out.println("No account found! Try again later");
            }

        } catch (InputMismatchException e) {
            System.out.println("INVALID input! Try again.");
            sc.nextLine();
            go.pauseClear();
        }

    }

    public int searchAccount(int searchAcc) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery("SELECT id from customer WHERE id = " + searchAcc); // search the account
                                                                                                 // number from query

            if (rs.next() == true) {
                return rs.getInt(1);
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e);
        }
        return 0;
    }

    public int processPolicyQuery(int month, int day, int year, int monthExp, int accNum) { // method for processing the
                                                                                            // query in
        int policyID = 0; // mysql
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            String query = "INSERT into policy (effectdate, expiredate, policyowner) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql
            // injection

            // conversion of util date to mysql date, because we cannot insert direct util
            // date in mysql table
            calendar.set(year, month, day);
            java.util.Date dateUtil = calendar.getTime();
            java.sql.Date date = new java.sql.Date(dateUtil.getTime()); // for creating effective date

            calendar.set(year, monthExp, day);
            java.util.Date dateExpUtil = calendar.getTime();
            java.sql.Date dateExp = new java.sql.Date(dateExpUtil.getTime()); // for policy's expiry date

            preparedStmt.setDate(1, (java.sql.Date) date);
            preparedStmt.setDate(2, (java.sql.Date) dateExp);
            preparedStmt.setInt(3, accNum);

            preparedStmt.execute(); // finally executes the query

            ResultSet rs = stmt.executeQuery("select policynumber, effectdate, " +
                    " expiredate from policy where policynumber = (select MAX(policynumber) from policy)");
            while (rs.next()) {
                policyID = rs.getInt(1); // stores the policy number id to a variable for us to be return later
                System.out.println("POLICY #" + policyID +
                        "\nEffective Date: " + rs.getString(2) +
                        "\nExpiry Date: " + rs.getString(3));
            }

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
            System.out.println("Please try again later!");
        }
        return policyID; // returns the number from query result
    }

    public void cancelPolicy() {
        int policyID;
        boolean policyIDExist;
        String choice, delConfirm;
        int newExpYear, newExpMonth, newExpDay;

        go.clearConsole();
        go.printBox("CANCEL A POLICY");
        try {
            System.out.print("Enter policy #: ");
            policyID = sc.nextInt();

            policyIDExist = searchPolicyID(policyID);

            if (policyIDExist == true) {
                System.out.print(
                        "[1] - Remove/Delete policy \n[2] - Change expiration date to an earlier date \n\nChoose: ");
                choice = sc.next();
                choice += sc.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print(
                                "Are you sure to remove policy #" + policyID + "? [1] - Yes / [Any] - No: "); // confirmation
                                                                                                              // before
                                                                                                              // deleting
                                                                                                              // from
                                                                                                              // table
                        delConfirm = sc.nextLine();
                        if (delConfirm.equals("1")) {
                            removePolicy(policyID);
                        } else {
                            System.out.println("Removal of policy #" + policyID + " has been cancelled.");
                        }
                        break;

                    case "2":
                    System.out.println("Current Expiry Date: " + showCurrentExpiryDate(policyID));
                    System.out.print("Month [1-12]: ");
                    newExpMonth = sc.nextInt();
                    System.out.print("Day: [1-31]: ");
                    newExpDay = sc.nextInt();
                    System.out.print("Year [4 digits]: ");
                    newExpYear = sc.nextInt();

                    if ((newExpMonth > 12 && newExpMonth <= 0) || (newExpDay > 31 && newExpDay <= 0) 
                    || String.valueOf(newExpYear).length() > 4) { // if user puts incorrect date
                        System.out.println("INVALID input! Please follow instructions, try again later.");
                    }

                    else {
                        changeExpiryDate(newExpMonth, newExpDay, newExpYear, policyID);
                    }

                        break;

                    default:
                        System.out.println("INVALID choice! Try again.");
                        break;
                }
            }

            else {
                System.out.print("No policy found.");
            }

        } catch (InputMismatchException e) {
            System.out.println("INVALID! Please try again.");
            sc.nextLine();
            go.pauseClear();
        }

    }

    public boolean searchPolicyID(int policyID) {
        dbConnect();
        try {
            rs = stmt.executeQuery("SELECT policynumber from policy WHERE policynumber = " + policyID);
            // number from query

            if (!rs.isBeforeFirst()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public void removePolicy(int policyID) {
        dbConnect();
        try {
            int result = stmt.executeUpdate("DELETE from policy where policynumber = " + policyID);

            if (result == 0) { // executes if the table didn't have any change, and the row results returned 0
                System.out.println("Removal of policy #" + policyID + " has failed. Try again later.");
            } else {
                System.out.println("Cancellation of policy #" + policyID + " is complete!");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String showCurrentExpiryDate (int policyID) {
        dbConnect();
        try {
            rs = stmt.executeQuery("SELECT expiredate from policy where policynumber = " + policyID);
            // number from query

            if (!rs.next()) {
                return "No Date";
            } else {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return "No Date";
        }
    }

    public void changeExpiryDate (int newExpMonth, int newExpDay, int newExpYear, int policyID) {
        dbConnect();
        try {
            rs = stmt.executeQuery("SELECT MONTH (effectdate), DAY (effectdate), YEAR (effectdate)," +
            " MONTH (expiredate), DAY (expiredate), YEAR (expiredate) from policy WHERE policynumber = " + policyID);

            while (rs.next()){
                int effectMonth = rs.getInt(1);
                int effectDay = rs.getInt(2);
                int effectYear = rs.getInt(3);
                int oldExpMonth = rs.getInt(4);
                int oldExpDay = rs.getInt(5);
                int oldExpYear = rs.getInt(6);

                if ( (newExpMonth < effectMonth) || (newExpDay < effectDay) || (newExpYear < effectYear) ){
                    System.out.println("INVALID! New expiry date must be greater than the effective date. \nTry again later.");
                }
                else if ( (newExpMonth > oldExpMonth) || (newExpDay > oldExpDay) || (newExpYear > oldExpYear) ){
                    System.out.println("INVALID! You cannot extend the current expiry date! \nTry again later.");
                }
                else {
                    preparedStmt = conn.prepareStatement("UPDATE policy set expiredate = ? where policynumber = ?");
                    preparedStmt.setString(1, newExpYear + "-" + newExpMonth + "-" + newExpDay);
                    preparedStmt.setInt(2, policyID);
                    preparedStmt.execute();
                    System.out.println("Sucessfully cancelled policy! New expiry date: " 
                    + newExpYear + "-" + newExpMonth + "-" + newExpDay);
                }
            }

        } catch (SQLException e){
            System.out.println(e);
        }
    }

}
