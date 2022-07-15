import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.xdevapi.DatabaseObject.DbObjectStatus;

public class AccidentClaim extends DatabaseConnection{
    Scanner sc = new Scanner(System.in);
    
    public void createClaim () {
        
        DisplayDesign go = new DisplayDesign();
        Validation valid = new Validation();
        int policyID;
        boolean policyIDExist;
        // String dateFormat = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$";
        String accidentDate, accidentAddress, accidentDesc, damageDesc;
        double repairCost;

        try  {
        go.printBox("FILE AN ACCIDENT CLAIM");
        System.out.print("Enter policy number: ");
        policyID = sc.nextInt();
        
        policyIDExist = valid.searchPolicyID("SELECT policynumber from policy WHERE policynumber = " + policyID
        + " AND (status = 'Active' OR status = 'Expired')"); //gets a return value for us to determine if the given value matches any policy number

        if (policyIDExist == true){ //executes if there is policyID in the table
            while (true){ //endless loop until inputs are satisfied/no errors
                try {
            go.printBox("FILE AN ACCIDENT CLAIM FOR POLICY #" + policyID);
            
            System.out.println("NOTE: Date must be in range of effective and expiration date of the policy.");
            System.out.println("Policy effectivity date: " + effectiveDate(policyID) + " - " + expireDate(policyID));
            System.out.print("\nDate of accident: [YYYY-MM-DD]: ");
            accidentDate = sc.next();
            accidentDate += sc.nextLine();

            if (valid.accidentDateCheck(accidentDate, policyID) == true){ //validates the date that was gathered from user input
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

                } catch (InputMismatchException e){
                    System.out.print("INVALID! Estimated repair cost must be a number");
                    go.pauseClear();
                }
            }

        }
        else { //executes if there is no policy number on the given value from the user, or if the policy is inactive or cancelled
            System.out.println("INVALID. The given policy number is either INACTIVE, CANCELLED, CLAIMED or NOT EXISTING!");
            go.pauseClear();
        }
        
        }
        catch (InputMismatchException e){
            System.out.println("No policy number found!");
            sc.nextLine();
            go.pauseClear();
        }
    }



    public void processClaim (String accidentDate, String accidentAddress, String accidentDesc, String damageDesc, double repairCost, int policyID){
        dbConnect();
        try {

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
            stmt.executeUpdate("UPDATE policy set status = 'Claimed' where policynumber = " + policyID);
            rs = stmt.executeQuery("SELECT MAX(claimnumber) from accident"); // gets the recently added id in the table

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

    public String effectiveDate (int policyID){
        dbConnect();
        String effectDate = "";
        try {
            rs = stmt.executeQuery("SELECT effectdate from policy WHERE policynumber = " + policyID);
            if (rs.next()){
                effectDate = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return effectDate;
    }

    public String expireDate (int policyID){
        dbConnect();
        String expireDate = "";
        try {
            rs = stmt.executeQuery("SELECT expiredate from policy WHERE policynumber = " + policyID);
            if (rs.next()){
                expireDate = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return expireDate;
    }

    public void showClaim (int policyID){

    }

}
