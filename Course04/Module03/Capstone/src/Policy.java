import java.sql.*;
import java.util.*;


public class Policy {
    private Scanner sc = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public void createPolicy() {
        calendar = Calendar.getInstance();
        DisplayDesign go = new DisplayDesign();
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

                        System.out.print("Year [" + calendar.get(Calendar.YEAR) + " onwards]: ");
                        year = sc.nextInt();

                        if ( (month > 12 && month <= 0) ||  (day > 31 && day <= 0) ) { // if
                                                                                                                      // user
                                                                                                                      // puts
                                                                                                                      // incorrect
                                                                                                                      // date
                            System.out.println("INVALID input! Please follow instructions.");
                            go.pauseClear();
                        }

                        else {

                            if (month < (calendar.get(Calendar.MONTH) + 1) && day < calendar.get(Calendar.DATE) && year <= calendar.get(Calendar.YEAR) ){
                                System.out.println("INVALID! You must put a date later than the current day!");
                                go.pauseClear();
                            }
                            else {
                            monthExp = month + 6; // sets the expiry month
                            

                            policyID = processPolicyQuery(month, day, year, monthExp, accNum); // gets the policy
                                                                                                // number from return
                                                                                                // value
                            // passes the account number and policy number to be inserted in the
                            // policyholder table
                            go.pauseClear();
                            
                            holder.policyHolder(accNum, policyID); //calls the policyholder class and sends the parameter's values to it
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

            //conversion of util date to mysql date, because we cannot insert direct util date in mysql table
            calendar.set(year, month, day);
            java.util.Date dateUtil = calendar.getTime();
            java.sql.Date date = new java.sql.Date(dateUtil.getTime()); //for creating effective date

            calendar.set(year, monthExp, day);
            java.util.Date dateExpUtil = calendar.getTime();
            java.sql.Date dateExp = new java.sql.Date(dateExpUtil.getTime()); //for policy's expiry date

            preparedStmt.setDate(1, (java.sql.Date) date);
            preparedStmt.setDate(2, (java.sql.Date) dateExp);
            preparedStmt.setInt(3, accNum);

            preparedStmt.execute(); // finally executes the query

            ResultSet rs = stmt.executeQuery("select policynumber, effectdate, " +
            " expiredate from policy where policynumber = (select MAX(policynumber) from policy)");
            while (rs.next()) {
                policyID = rs.getInt(1); //stores the policy number id to a variable for us to be return later
            System.out.println("POLICY #" + policyID  +
                    "\nEffective Date: " + rs.getString(2) +
                    "\nExpiry Date: " + rs.getString(3));
            }


        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
            System.out.println("Please try again later!");
        }
        return policyID; //returns the number from query result

    }


    

}
