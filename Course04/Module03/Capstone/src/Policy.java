import java.sql.*;
import java.util.*;


public class Policy {
    private Scanner sc = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();
    private int policyHolderID;
    private double policyPremium;

    public void createPolicy() {
        calendar = Calendar.getInstance();
        DisplayDesign go = new DisplayDesign();
        PolicyHolder holder = new PolicyHolder();
        Vehicle vehicle = new Vehicle();
        int searchAcc, accNum, month, day, year, monthExp;

        go.clearConsole();

        try {
            go.printBox("QUOTE POLICY");
            System.out.print("Enter account number: ");
            searchAcc = sc.nextInt();

            accNum = searchAccount(searchAcc); // search for an account in database table


            
            if (accNum != 0) {
                holder.policyHolder(accNum); //calls the policyholder class and sends the parameter's values to it

                while (true) {
                    try {
                        go.clearConsole();
                        go.printBox("QUOTE POLICY FOR ACCOUNT #" + accNum);

                        System.out.println(getPolicyHolderID());
                        System.out.println(getPolicyPremium());

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
                            

                            processPolicyQuery(month, day, year, monthExp, accNum, getPolicyHolderID(), getPolicyPremium()); // gets the policy
                                                                                                // number from return
                                                                                                // value
                            // passes the account number and policy number to be inserted in the
                            // policyholder table
                            go.pauseClear();
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

    public int processPolicyQuery(int month, int day, int year, int monthExp, int accNum, int policyHolderNum, double policyPremium) { // method for processing the
                                                                                            // query in
        int policyNumber = 0; // mysql
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            String query = "INSERT into policy (effectdate, expiredate, policyowner, policypremium) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql
            // injection

            //conversion of util date to mysql date, because we cannot insert direct util date in mysql table
            calendar.set(year, month, day);
            java.util.Date dateUtil = calendar.getTime();
            java.sql.Date date = new java.sql.Date(dateUtil.getTime());
            calendar.set(year, monthExp, day);
            java.util.Date dateExpUtil = calendar.getTime();
            java.sql.Date dateExp = new java.sql.Date(dateExpUtil.getTime());

            preparedStmt.setDate(1, (java.sql.Date) date);
            preparedStmt.setDate(2, (java.sql.Date) dateExp);
            preparedStmt.setInt(3, accNum);
            preparedStmt.setDouble(4, policyPremium);

            preparedStmt.execute(); // finally executes the query

            ResultSet rs = stmt.executeQuery("select policynumber, effectdate, " +
            " expiredate from policy where policynumber = (select MAX(policynumber) from policy)");
            while (rs.next()) {
                policyNumber = rs.getInt(1); //stores the policy number to a variable for us to be return later
            System.out.println("Successfully created policy (#" + policyNumber + ")" +
                    "\nEffective Date: " + rs.getString(2) +
                    "\nExpiry Date: " + rs.getString(3));
            }

            stmt.executeUpdate("UPDATE policyholder set policy = " + policyNumber + " where id = " + policyHolderNum + ""); //updates the policy holder, we will put the policy number into them


        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
            System.out.println("Please try again later!");
        }
        return policyNumber; //returns the number from query result

    }


    
    public int getPolicyHolderID() {
        return this.policyHolderID;
    }

    public double getPolicyPremium() {
        return this.policyPremium;
    }

    public void setPolicyHolderID(int policyHolderNum) {
        this.policyHolderID = policyHolderNum;
    }

    public void setPolicyPremium(double policyPremium) {
        this.policyPremium = policyPremium;
    }

    public void call (double hatdog){

    }


}
