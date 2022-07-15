import java.sql.*;
import java.util.*;

public class Policy extends DatabaseConnection {
    private Scanner sc = new Scanner(System.in);
    private Calendar calendar = Calendar.getInstance();
    private DisplayDesign go = new DisplayDesign();
    private Validation valid = new Validation();

    public void createPolicy() {
        calendar = Calendar.getInstance();
        PolicyHolder holder = new PolicyHolder();
        int accNum, month, day, year, monthExp, policyID;
        go.clearConsole();

        try {
            go.printBox("QUOTE POLICY");
            System.out.print("Enter account number: ");
            accNum = sc.nextInt();

            if (searchAccount(accNum) == true) {
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

                        if (valid.date(year, month, day) == true) {

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
                            // }
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

    public boolean searchAccount(int searchAcc) { // this method will search for id in customer table
        dbConnect();
        try {
            ResultSet rs = stmt.executeQuery("SELECT id from customer WHERE id = " + searchAcc); // search the account
                                                                                                 // number from query
            if (rs.next() == true) { // checks if rs has result
                return true; // returns true if query finds a result
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int processPolicyQuery(int month, int day, int year, int monthExp, int accNum) { // method for processing the
                                                                                            // query in mysql
        dbConnect();
        int policyID = 0;
        try {
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

            policyIDExist = valid.searchPolicyID("SELECT policynumber from policy WHERE policynumber = " + policyID);

            if (policyIDExist == true) {
                System.out.print(
                        "[1] - Remove/Delete policy \n[2] - Change expiration date to an earlier date \n\nChoose: ");
                choice = sc.next();
                choice += sc.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Are you sure to remove policy #" + policyID
                                + "? [1] - Yes / [Any] - No: "); // confirmation before deleting from table
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

                        if (valid.dateRange(newExpMonth, newExpDay, newExpYear, policyID) == true) {
                            changeExpiryDate(newExpYear, newExpMonth, newExpDay, policyID);
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
            System.out.println(e.getMessage());
        }
    }

    public String showCurrentExpiryDate(int policyID) {
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
            System.out.println(e.getMessage());
            return "No Date";
        }
    }

    public void changeExpiryDate(int newExpYear, int newExpMonth, int newExpDay, int policyID) {
        dbConnect();
        try {
            preparedStmt = conn
                    .prepareStatement("UPDATE policy SET expiredate = ?, status = 'Cancelled' WHERE policynumber = ?");
            preparedStmt.setString(1, newExpYear + "-" + newExpMonth + "-" + newExpDay);
            preparedStmt.setInt(2, policyID);
            preparedStmt.execute();
            System.out.println("Sucessfully cancelled policy! New expiry date: "
                    + newExpYear + "-" + newExpMonth + "-" + newExpDay);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchPolicy() {
        int policyID;
        try {
            System.out.print("Type the policy number: ");
            policyID = sc.nextInt();

            if (valid.searchPolicyID("SELECT policynumber from policy WHERE policynumber = " + policyID) == true) {
                go.clearConsole();
                go.printBox("POLICY #" + policyID + " INFORMATION");
                showPolicy(policyID);
            }

            else {
                System.out.println("No policy number found! Try again later.");
                go.pauseClear();
            }

        } catch (InputMismatchException e) {
            System.out.println("No policy number found! Try again later.");
            sc.nextLine();
        }
    }

    public void showPolicy(int policyID) {
        dbConnect();
        String firstName = "", lastName = "";
        String[][] policyInformation = new String[2][5];
        int policyHolderID = 0, vehicleCount = 0;
        
        try {
            policyInformation[1][0] = "EFFECTIVE DATE";
            policyInformation[1][1] = "EXPIRATION DATE";
            policyInformation[1][2] = "ACCOUNT NUMBER";
            policyInformation[1][3] = "POLICY PREMIUM";
            policyInformation[1][4] = "POLICY STATUS";

            rs = stmt.executeQuery("SELECT effectdate, expiredate, policyowner,"
                    + " policypremium, status from policy WHERE policynumber = "
                    + policyID);
                    
            if (rs.next()) {
                policyInformation[0][0] = rs.getString(1);
                policyInformation[0][1] = rs.getString(2);
                policyInformation[0][2] = rs.getString(3);
                policyInformation[0][3] = rs.getString(4);
                policyInformation[0][4] = rs.getString(5);

            }

            for (int y = 0; y < 5; y++) {
                System.out.printf("%-20s %15s\n", policyInformation[1][y],
                        policyInformation[0][y]);
            }

            rs2 = stmt.executeQuery("SELECT id, firstname, lastname from policyholder"
                    + " WHERE policy = " + policyID);

            if (rs2.next()) {
                policyHolderID = rs2.getInt(1);
                firstName = rs2.getString(2);
                lastName = rs2.getString(3);
            }

            System.out.println("\nPOLICY HOLDER NAME: " + firstName + " " + lastName);

            rs3 = stmt.executeQuery("SELECT COUNT(*) from vehicle where policyholder = "
                    + policyHolderID);

            if (rs3.next()) {
                vehicleCount = rs3.getInt(1);
            }

            System.out.println("NUMBER OF VEHICLES ENROLLED: " + vehicleCount + "\n");

        } catch (SQLException e) {
            System.out.println("Sorry. " + e.getMessage());
        }
    }

}
