import java.sql.*;
import java.util.Scanner;

public class CustomerAccount extends DatabaseConnection{
    private Scanner sc = new Scanner(System.in);
    DisplayDesign go = new DisplayDesign();
    Validation valid = new Validation();

    public void createAccount() {
        String firstname, lastname, address;
        go.clearConsole(); // clears the terminal

        go.printBox("CREATE CUSTOMER ACCOUNT");
        System.out.print("Firstname: ");
        firstname = sc.next();
        firstname += sc.nextLine(); // this takes the user input if the user typed with space

        System.out.print("Lastname: ");
        lastname = sc.nextLine();

        System.out.print("Address: ");
        address = sc.nextLine();

        if (firstname.isBlank() || lastname.isBlank() || address.isBlank()) { // if user doesn't put anything
            System.out.println("INVALID input! Don't leave it blank!");
            go.pauseClear();
        } else {
            processQuery(firstname, lastname, address); // sends the values in the parameter in order to execute it in
                                                        // the query
        }

    }

    public void processQuery(String firstname, String lastname, String address) { // method for processing the query in
                                                                                  // mysql
        dbConnect();
        try {
            String query = ("INSERT INTO customer (firstname, lastname, address) VALUES (?, ?, ?)"); // executes a query
                                                                                                     // based on the
                                                                                                     // given value in
                                                                                                     // the parameter
            preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql
                                                                           // injection
            preparedStmt.setString(1, firstname);
            preparedStmt.setString(2, lastname);
            preparedStmt.setString(3, address);

            preparedStmt.execute(); // finally executes the query

            rs = stmt.executeQuery("SELECT MAX(id) from customer"); // gets the recently added id in the table

            while (rs.next()) {
                System.out.println("Successfully created account with an account number of " + rs.getInt(1)); // prints
                                                                                                              // the
                                                                                                              // account
                                                                                                              // number
            }

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
            System.out.println("Please try again later!");
        }
    }

    public void searchAccount () {
        String firstName = "", lastName = "";
        boolean validated = false;

        while (!validated) {
        go.printBox("SEARCH CUSTOMER ACCOUNT");

        System.out.print("Firstname: ");
        firstName = sc.next();
        firstName += sc.nextLine();

        System.out.print("Lastname: ");
        lastName = sc.nextLine();

        validated = valid.name(firstName, lastName);
        }

        if (validated == false){
            System.out.println("No customer found.");
        }
        
        else {
            viewAccount(firstName, lastName); //executes the query to show the tables
        }
    }

    public void viewAccount (String firstName, String lastName) {
        dbConnect();
        int policyID = 0;
        try{
            rs = stmt.executeQuery("SELECT * FROM customer WHERE firstname = '" + firstName
             + "' and lastname = '" + lastName + "'");
             
             while (rs.next()){
                 policyID = rs.getInt(1);
                 go.printBox("CUSTOMER INFORMATION || NUMBER: " + policyID);
                 System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
                 System.out.println("Address: " + rs.getString(4));
                }
                
                
            rs2 = stmt.executeQuery("SELECT policynumber, status from policy where policyowner = " + policyID);
            while (rs2.next()){
                go.printBox("LIST OF POLICIES OWNED");
                System.out.printf("%-15s %10s", "POLICY NUMBER", "STATUS");
                System.out.printf("%-15s %10s", rs.getInt(1), rs.getString(2));
                System.out.println();
            }
        } catch (SQLException e){
            System.out.println(e);
        }
    }

}
