import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AccidentClaim extends DatabaseConnection{

    Scanner sc = new Scanner(System.in);
    DisplayDesign go = new DisplayDesign();
    Validation valid = new Validation();
    
    public void createClaim () { //method for creating claim

        int policyID;
        boolean policyIDExist;
        String accidentDate, accidentAddress, accidentDesc, damageDesc;
        double repairCost;

        try  {
        go.printBox("FILE AN ACCIDENT CLAIM");
        System.out.print("Enter policy number: ");
        policyID = sc.nextInt();
        
        policyIDExist = valid.searchID("SELECT policynumber from policy WHERE policynumber = " + policyID
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

                } catch (InputMismatchException e){ //if repair cost contains letters/symbols
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


    //this method will insert values to accident table (the claims) if there was no error in the process
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

    public String effectiveDate (int policyID){//returns effective date from a query and will be used for printing effective date
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

    public String expireDate (int policyID){ //for expiry date
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

    public void searchClaim (){ //for OPTION #7
        int claimID;
        String claimNumber;
        try {
            System.out.print("Type the claim number: ");
            claimNumber = sc.next();
            claimNumber += sc.nextLine();

            if (valid.claimNumberFormat(claimNumber) == true){ //checks if format of claimnumber is correct
                claimID = Integer.parseInt(claimNumber.substring(1)); //cuts the 'C' from claim number

                if (valid.searchID("SELECT claimnumber from accident WHERE claimnumber = " + claimID) == true) { //checks if there is existing claim on the table
                    go.clearConsole();
                    go.printBox("CLAIM #C" + claimID + " INFORMATION");
                    showClaim(claimID); //prints the claim's information
                }
            }

            else {
                System.out.println("No claim number found! Try again later.");
                go.pauseClear();
            }

        } catch (InputMismatchException e) {
            System.out.println("No claim number found! Try again later.");
            sc.nextLine();
        }
    }

    public void showClaim (int claimID) { //displays the claim information
        dbConnect();
        String claimInformation [] [] = new String [2] [6];
        try {

                //saves the output in 2d array for easy output later
                claimInformation [0][0] = "Date of Accident: ";
                claimInformation [0][1] = "Accident Address: ";
                claimInformation [0][2] = "Accident Description: ";
                claimInformation [0][3] = "Damage Description: ";
                claimInformation [0][4] = "Repair Cost: ";
                claimInformation [0][5] = "Policy number: ";
            
            rs = stmt.executeQuery("SELECT * from accident where claimnumber = " + claimID);
            while (rs.next()){
                for (int x = 1; x <= 6; x++){
                    claimInformation [1][x-1] = rs.getString(x+1); //saves the resultset in 2d array
                }
            }

            for (int x = 0; x < 6; x++){ //prints the 2d arrays contents, which contains claim information
                System.out.printf("%-25s %20s\n", claimInformation[0][x],  claimInformation[1][x]);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
