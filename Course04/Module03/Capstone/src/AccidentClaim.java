import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccidentClaim {
    Scanner sc = new Scanner(System.in);
    
    public void createClaim () {
        
        DisplayDesign go = new DisplayDesign();
        int policyID;
        boolean policyIDExist;
        String dateFormat = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$";
        String accidentDate, accidentAddress, accidentDesc, damageDesc;
        double repairCost;

        try  {
        go.printBox("FILE AN ACCIDENT CLAIM");
        System.out.print("Enter policy number: ");
        policyID = sc.nextInt();
        
        policyIDExist = searchPolicyID(policyID); //gets a return value for us to determine if the given value matches any policy number

        if (policyIDExist == true){ //executes if there is policyID in the table
            while (true){ //endless loop until inputs are satisfied/no errors
                try {
            go.printBox("FILE AN ACCIDENT CLAIM FOR POLICY #" + policyID);
                
            System.out.print("Date of accident: [YYYY/MM/DD]: ");
            accidentDate = sc.next();
            accidentDate += sc.nextLine();

            if (accidentDate.matches(dateFormat)){ //will only execute if given input matches the regex for proper date format
                System.out.print("Address where accident happened: ");
                accidentAddress = sc.nextLine();

                System.out.print("Description (Accident): ");
                accidentDesc = sc.nextLine();

                System.out.print("Description (Damage to vehicle): ");
                damageDesc = sc.nextLine();

                if (accidentAddress.isBlank() || accidentDesc.isBlank() || damageDesc.isBlank()){ //validates whether the user leaves it empty
                    System.out.println("INVALID! Please fill all the inputs!");
                    go.pauseClear();
                }

                else { //executes if user doesn't leave the inputs blank
                    System.out.print("Estimated cost of repair: ");
                    repairCost = sc.nextDouble();

                    if (repairCost <= 0){ //validates if user puts zero/negative number
                        System.out.println("INVALID! Cost cannot be zero/negative!");
                    }

                    else { //finally executes if no errors occured
                        processClaim(accidentDate, accidentAddress, accidentDesc, damageDesc, repairCost, policyID);
                        go.pauseClear();
                        break;
                    }

                }
            }
            else {
                System.out.print("INVALID! Please follow proper date format");
                go.pauseClear();
            }

                } catch (InputMismatchException e){
                    System.out.print("INVALID! Estimated repair cost must be a number");
                    go.pauseClear();
                }
            }

        }
        else { //executes if there is no policy number on the given value from the user
            System.out.println("No policy number found!");
            go.pauseClear();
        }
        
        }
        catch (InputMismatchException e){
            System.out.println("INVALID input!");
            go.pauseClear();
        }
    }

    public boolean searchPolicyID (int policyID) { //search policynumber in policy table, then returns true if there is policynumber on the given input by the user

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery("SELECT policynumber from policy WHERE policynumber = " + policyID); // search the account
                                                                                                 // number from query

            if (rs.next() == true) {
                return true;
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e);
        }
        return false;

    }


    public void processClaim (String accidentDate, String accidentAddress, String accidentDesc, String damageDesc, double repairCost, int policyID){

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            String query = ("INSERT into accident (date, address, accidentdescription, damagedescription, repaircost, policy)" + 
            " VALUES (?, ?, ?, ?, ?, ?)"); // executes a query based on the given value in the parameter

            PreparedStatement preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql
                                                                           // injection

            preparedStmt.setString(1, accidentDate);
            preparedStmt.setString(2, accidentAddress);
            preparedStmt.setString(3, accidentDesc);
            preparedStmt.setString(4, damageDesc);
            preparedStmt.setDouble(5, repairCost);
            preparedStmt.setInt(6, policyID);

            preparedStmt.execute(); // finally executes the query

            ResultSet rs = stmt.executeQuery("SELECT MAX(claimnumber) from accident"); // gets the recently added id in the table

            while (rs.next()) {
                System.out.println("Successfully filed an accident claim (#" + rs.getInt(1) + ") from Policy #" + policyID); // prints
                                                                                                              // the
                                                                                                              // account
                                                                                                              // number
            }

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
        }
    }

}
