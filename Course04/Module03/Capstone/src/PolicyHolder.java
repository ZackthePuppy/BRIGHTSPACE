import java.sql.*;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PolicyHolder extends Policy {
    Scanner sc = new Scanner(System.in);
    DisplayDesign go = new DisplayDesign();
    Calendar calendar = Calendar.getInstance();
    Vehicle vehicle = new Vehicle();

    public void policyHolder(int accNum, int policyID) {
        go.clearConsole();
        String choice;
        boolean mainLoop = true, hasPolicyHolder;
        int policyNum, searchAcc, driverYear;

        while (mainLoop) { // endless loop unless input was satisfied
            go.printBox("POLICY HOLDER FOR ACC #" + accNum);
            System.out.println("[1] - Add new Policy Holder \n[2] - Select Existing Policy Holder");
            System.out.print("\nChoose: ");
            choice = sc.next();

            switch (choice) {
                case "1":
                    addPolicyHolder(accNum); // calls the addPolicyHolder method and passes the parameter
                    break;

                case "2":
                    try {
                        go.printBox("List of Policy Holders in Account #" + accNum);
                        hasPolicyHolder = listOfHolders(accNum);

                        if (hasPolicyHolder == true) { // proceed if there is exisiting policyholders
                            System.out.print("Select Policy Holder ID: ");
                            searchAcc = sc.nextInt();

                            policyNum = searchAccount(searchAcc); // calls the method, then stores the return value in a
                                                                  // variable
                            driverYear = getYearIssued(searchAcc); // gets the num of years since driver license was
                                                                   // first
                                                                   // issued

                            if (policyNum != 0) { // executes if id found in query
                                vehicle.policyHolderVehicle(policyNum, accNum, driverYear, policyID);
                                mainLoop = false; // finally ends the endless loop because existing policy holder was
                                                  // finally used
                            }

                            else {
                                System.out.println("No ID matches with the given input!");
                                go.pauseClear();
                            }
                        }

                        else {
                            System.out.println("No existing Policy Holder! You need to add first.");
                            go.pauseClear();
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("INVALID ID! Try again.");
                        sc.nextLine();
                        go.pauseClear();
                    }
                    break;

                default:
                    System.out.println("INVALID choice!");
                    go.pauseClear();
                    break;
            }
        }

    }

    public void addPolicyHolder(int accNum) { // method for adding policy holder in specific customer acc
                                              // num
        String firstname, lastname, birthDate, address, licenseNum;
        int licenseDay, licenseMonth, licenseYear;

        while (true) {
            go.printBox("ADD NEW POLICY HOLDER FOR ACC #" + accNum);
            System.out.print("Firstname: ");
            firstname = sc.next();
            firstname += sc.nextLine();

            System.out.print("Lastname: ");
            lastname = sc.nextLine();

            System.out.print("Date of Birth: ");
            birthDate = sc.nextLine();

            System.out.print("Address: ");
            address = sc.nextLine();

            System.out.print("License Number: ");
            licenseNum = sc.nextLine();

            if (firstname.isBlank() || lastname.isBlank() || birthDate.isBlank() || address.isBlank()
                    || licenseNum.isBlank()) { // validation if there are no inputs
                System.out.println("INVALID! Please don't leave it blank!");
                go.pauseClear();
            }

            else {
                try {
                    go.printBox("Driver's License Date Issued");
                    System.out.print("Month [1-12]: ");
                    licenseMonth = sc.nextInt() - 1;

                    System.out.print("Day [1-31]: ");
                    licenseDay = sc.nextInt();

                    System.out.print("Year: ");
                    licenseYear = sc.nextInt();

                    if ((licenseMonth < 1 && licenseMonth > 12) && (licenseDay < 1 && licenseDay > 31)) { // validation
                                                                                                          // of
                                                                                                          // date
                        System.out.println("Month or Day is INVALID!");
                        go.pauseClear();
                    }

                    else if (String.valueOf(licenseYear).length() != 4) { // if year is not 4 digit
                        System.out.println("INVALID! Year must be 4 digits.");
                        go.pauseClear();
                    }

                    else {
                        policyHolderQuery(firstname, lastname, birthDate, address, licenseNum, licenseDay, licenseMonth,
                                licenseYear, accNum); // sends all the values in another method so that it can be
                                                      // inserted on tables
                        break; // breaks the endless loop because the input was satisfied and passes it in
                               // parameter
                    }

                } catch (InputMismatchException e) {
                    System.out.println("INVALID input!");
                    sc.nextLine();
                    go.pauseClear();
                }
            }
        }

    }

    public void policyHolderQuery(String firstname, String lastname, String birthDate, String address,
            String licenseNum, int licenseDay, int licenseMonth, int licenseYear, int accNum) { // for inserting
                                                                                                // PolicyHolder values
                                                                                                // into the table

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            String query = ("INSERT INTO policyholder (firstname, lastname, birthdate, licensenumber," +
                    " licensedate, policyowner) VALUES (?, ?, ?, ?, ?, ?)"); // executes a query based on the given
                                                                             // value in the parameter

            PreparedStatement preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql
                                                                           // injection

            calendar.set(licenseYear, licenseMonth, licenseDay);
            java.util.Date dateUtil = calendar.getTime();
            java.sql.Date date = new java.sql.Date(dateUtil.getTime()); // conversion of util date into mysql date

            preparedStmt.setString(1, firstname);
            preparedStmt.setString(2, lastname);
            preparedStmt.setString(3, birthDate);
            preparedStmt.setString(4, licenseNum);
            preparedStmt.setDate(5, date);
            preparedStmt.setInt(6, accNum);

            preparedStmt.execute(); // finally executes the query

            ResultSet rs = stmt.executeQuery("select firstname, lastname " +
                    " from policyholder where id = (select MAX(id) from policyholder)"); // gets the recently added id
                                                                                         // in the table

            while (rs.next()) {
                System.out.println("Success! Policy Holder Name: " + rs.getString(1) + " " + rs.getString(2)); // prints
                                                                                                               // the
                                                                                                               // account
                                                                                                               // number
            }

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
            System.out.println("Please try again later!");
        }
    }

    public boolean listOfHolders(int accNum) { // displays list of policyholders under specific acc that doesn't have
                                               // any
                                               // policies yet
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {
            String query = "select id, firstname, lastname from policyholder where policyowner = "
                    + accNum + " and policy is NULL";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                System.out.println("NO Policy Holders in this Account!");
                return false;
            } else {
                System.out.printf("%-5s %-15s %15s\n", "ID#", "FIRSTNAME", "LASTNAME");
                while (rs.next()) { // PRINTS the lists of policy holders
                    System.out.printf("%-5s %-15s %15s\n", rs.getString(1), rs.getString(2), rs.getString(3));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public int searchAccount(int searchAcc) { // this method will search the id of policy holder
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt
                    .executeQuery("SELECT id from policyholder WHERE id = " + searchAcc + " and policy is NULL"); // search
                                                                                                                  // the
                                                                                                                  // account
            // number from query

            if (rs.next() == true) {
                return rs.getInt(1);
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e);
        }
        return 0;
    }

    public int getYearIssued(int searchAcc) { // this method will get the date issued year from a policyholder
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("select YEAR(licensedate) from policyholder" +
                    " where id = " + searchAcc + " and policy is NULL"); // search the account
                                                                         // number from query

            if (rs.next() == true) {
                return (calendar.get(Calendar.YEAR) - rs.getInt(1));
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e);
        }
        return 0;
    }

}
