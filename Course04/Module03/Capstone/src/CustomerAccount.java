import java.sql.*;
import java.util.Scanner;

public class CustomerAccount extends DatabaseConnection {
    private Scanner sc = new Scanner(System.in);
    private DisplayDesign go = new DisplayDesign();
    private Validation valid = new Validation();

    public void createAccount() { //method for creating an account
        String firstName, lastName, address;
        go.clearConsole(); // clears the terminal

        go.printBox("CREATE CUSTOMER ACCOUNT");
        System.out.print("Firstname: ");
        firstName = sc.next();
        firstName += sc.nextLine(); // this takes the user input if the user typed with space

        System.out.print("Lastname: ");
        lastName = sc.nextLine();

        System.out.print("Address: ");
        address = sc.nextLine();

        if (valid.customerAccount(firstName, lastName, address) == true){ //validates the inputs from validation class
            processQuery(firstName, lastName, address); // sends the values in the parameter in order to insert it in
        }
    }

    public void processQuery(String firstname, String lastname, String address) { // method for processing the query in
                                                                                  // mysql
        dbConnect();
        try {
            String query = ("INSERT INTO customer (firstname, lastname, address)" 
            +" VALUES (?, ?, ?)"); // executes a query based on the given value in the parameter
            preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql injection
            preparedStmt.setString(1, firstname);
            preparedStmt.setString(2, lastname);
            preparedStmt.setString(3, address);

            preparedStmt.execute(); // finally executes the query

            rs = stmt.executeQuery("SELECT MAX(id) from customer"); // gets the recently added id in the table

            while (rs.next()) {
                System.out.println("Successfully created account with an account number of " 
                + rs.getInt(1)); // prints the account number
            }


        } catch (SQLException e) { // handles errors
            System.out.println("INVALID. " + e.getMessage());
            System.out.println("Please try again later!");
        }
    }

    public void searchAccount() { //method for OPTION #5
        String firstName = "", lastName = "";
        go.clearConsole();

            go.printBox("SEARCH CUSTOMER ACCOUNT");

            System.out.print("Firstname: ");
            firstName = sc.next();
            firstName += sc.nextLine();

            System.out.print("Lastname: ");
            lastName = sc.nextLine();

        if (valid.name(firstName, lastName) == true) { //validates the input using the method from validation class
            viewAccount(firstName, lastName); // executes the query to show the tables
        }
        
    }

    public void viewAccount(String firstName, String lastName) { //view the customer account's information, will be called by the method above
        dbConnect();
        int customerID = 0;
        try {
            rs = stmt.executeQuery("SELECT * FROM customer WHERE firstname = '" + firstName
                    + "' and lastname = '" + lastName + "'");

            if (rs.isBeforeFirst()) { // if resultset obtains record
                go.clearConsole();
                while (rs.next()) {
                    customerID = rs.getInt(1);
                    go.printBox("CUSTOMER INFORMATION || ACCOUNT NUMBER: " + customerID);
                    System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
                    System.out.println("Address: " + rs.getString(4));
                }

                rs2 = stmt.executeQuery("SELECT policynumber, status from policy where policyowner = " + customerID);
                go.printBox("LIST OF POLICIES OWNED BY CUSTOMER");
                System.out.printf("%-15s | %10s\n", "POLICY NUMBER", "STATUS");

                while (rs2.next()) {
                    System.out.printf("%-15s | %10s\n", rs2.getInt(1), rs2.getString(2));
                }

                rs3 = stmt.executeQuery("SELECT firstname, lastname, address, licensenumber, licensedate, policy from policyholder "
                + "WHERE policyowner = " + customerID);

                go.printBox("LIST OF POLICY HOLDERS UNDER CUSTOMER ACCOUNT #" + customerID);
                System.out.printf("%-18s | %-15s | %-15s | %-15s\n", 
                "NAME", "ADDRESS", "LICENSE", "DATE ISSUED");

                while (rs3.next()){
                    System.out.printf("%-18s | %-15s | %-15s | %-15s\n", 
                    rs3.getString(1) + " " + rs3.getString(2),
                    rs3.getString(3), rs3.getString(4), 
                    rs3.getString(5));
                }
            }

            else {
                System.out.println("No customer account found.");
            }

        } catch (SQLException e) {
            System.out.println("INVALID. " + e.getMessage());
        }
    }

}
