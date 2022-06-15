import java.sql.*;
import java.util.Scanner;

public class CustomerAccount extends DatabaseConnection{
    private Scanner sc = new Scanner(System.in);

    public void createAccount() {
        DisplayDesign go = new DisplayDesign();
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

}
