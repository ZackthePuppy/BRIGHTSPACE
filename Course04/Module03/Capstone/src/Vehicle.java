import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vehicle {
    Scanner sc = new Scanner(System.in);

    public void policyHolderVehicle(int policyHolderID, int accNum, int driverYear, int policyID) {
        RatingEngine ratingEngine = new RatingEngine();
        DisplayDesign go = new DisplayDesign();
        int vehicleNum, year;
        double vehiclePrice, vehiclePriceFactor = 0, premium, totalPremium = 0;
        String brand, model, type, fuel, color;
        go.clearConsole();

        while (true) {
            try {
                go.printBox("Policy Holder ID#" + policyHolderID + " | Account #" + accNum);
                System.out.print("Number of Vehicle: ");
                vehicleNum = sc.nextInt();

                if (vehicleNum <= 0) {
                    System.out.println("INVALID! Input cannot be zero/negative!");
                    go.pauseClear();
                }

                else {
                    for (int x = 1; x <= vehicleNum; x++) {
                        try {
                            go.printBox("Vehicle #" + x);

                            System.out.print("Make: ");
                            brand = sc.next();
                            brand += sc.nextLine();

                            System.out.print("Model: ");
                            model = sc.nextLine();

                            System.out.print("Year: ");
                            year = sc.nextInt();

                            if (year <= 1) {
                                vehiclePriceFactor = 1.0;
                            } else if (year <= 3) {
                                vehiclePriceFactor = 0.8;
                            } else if (year <= 5) {
                                vehiclePriceFactor = 0.7;
                            } else if (year <= 10) {
                                vehiclePriceFactor = 0.6;
                            } else if (year <= 15) {
                                vehiclePriceFactor = 0.4;
                            } else if (year <= 20) {
                                vehiclePriceFactor = 0.2;
                            } else if (year <= 40) {
                                vehiclePriceFactor = 0.1;
                            }

                            while (true) { // endless loop if user didnt choose correct vehicle type
                                System.out.print(
                                        "Type \n[1] - 4-door sedan \n[2] - 2-door Sports Car \n[3] - SUV \n[4] - Truck \n\nChoose: ");
                                String choosetype = sc.next();

                                if (choosetype.equals("1")) {
                                    type = "4-Door Sedan";
                                    break;
                                } else if (choosetype.equals("2")) {
                                    type = "2-Door Sports Car";
                                    break;
                                } else if (choosetype.equals("3")) {
                                    type = "SUV";
                                    break;
                                } else if (choosetype.equals("4")) {
                                    type = "Truck";
                                    break;
                                } else {
                                    System.out.println("INVALID CHOICE");
                                }

                            }

                            while (true) { // endless loop if user didnt choose correct fuel type
                                System.out
                                        .print("FuelType \n[1] - Diesel \n[2] - Electric \n[3] - Petrol \n\nChoose: ");
                                String choosetype = sc.next();

                                if (choosetype.equals("1")) {
                                    fuel = "Diesel";
                                    break;
                                } else if (choosetype.equals("2")) {
                                    fuel = "Electric";
                                    break;
                                } else if (choosetype.equals("3")) {
                                    fuel = "Petrol";
                                    break;
                                } else {
                                    System.out.println("INVALID CHOICE");
                                }

                            }

                            System.out.print("Purchase Price: ");
                            vehiclePrice = sc.nextDouble();

                            System.out.print("Color: ");
                            color = sc.next();
                            color += sc.nextLine();

                            if (brand.isBlank() || model.isBlank() || color.isBlank()) {
                                System.out.println("INVALID! Please FILL all the input!");
                                x--;
                                go.pauseClear();
                            }

                            else if (year > 40) {
                                System.out.println("Vehicle with more than 40 years old is not eligible!");
                                x--;
                                go.pauseClear();
                            }

                            else if (year < 0 || vehiclePrice <= 0) {
                                System.out.println("INVALID! Year/Price cannot be negative number!");
                                x--;
                            }

                            else { // finally runs/adds the values when there is no errors
                                premium = ratingEngine.engine(vehiclePrice, vehiclePriceFactor,
                                        driverYear); // calls the rating engine class, then it calculates the premium
                                                     // charge, and adds it on policypremium

                                totalPremium += premium; // adds the current premium of 1 vehicle into the total policy
                                                         // premium

                                addVehicle(brand, model, year, type, fuel, vehiclePrice, color, premium,
                                        policyHolderID); // calls method to insert it in a table

                                System.out.println("Vehicle added! Premium Charge: " + premium);
                                go.pauseClear();
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("INVALID input!");
                            go.pauseClear();
                            x--; // prevents incrementing the loop, so it will stay in current vehicle if user
                                 // typed wrong input
                        }

                    }
                    System.out.println("Finished adding vehicles! Total Premium: " + totalPremium);
                    addPolicyPremium(totalPremium, policyID); // calls the method in order to update the policy table
                                                              // and add the total premium
                    setPolicyHolderPolicy(policyID, policyHolderID);
                    go.pauseClear();
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("INVALID input! Try again.");
                sc.nextLine();
                go.pauseClear();
            }

        }

    }

    public void addVehicle(String brand, String model, int year, String type, String fuel, double price, String color,
            double premiumcharge, int policyHolderNum) { // this method will add the vehicle to the table

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {

            String query = ("INSERT into vehicle (brand, model, year, type, fuel, " +
                    "price, color, premiumcharge, policyholder) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"); // executes a
                                                                                                      // query based on
                                                                                                      // the given
            // value in the parameter

            PreparedStatement preparedStmt = conn.prepareStatement(query); // used preparedStatement to prevent sql
                                                                           // injection

            preparedStmt.setString(1, brand);
            preparedStmt.setString(2, model);
            preparedStmt.setInt(3, year);
            preparedStmt.setString(4, type);
            preparedStmt.setString(5, fuel);
            preparedStmt.setDouble(6, price);
            preparedStmt.setString(7, color);
            preparedStmt.setDouble(8, premiumcharge);
            preparedStmt.setInt(9, policyHolderNum);

            preparedStmt.execute(); // finally executes the query

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
        }
    }

    public void addPolicyPremium(double policyPremium, int policyID) { // this method will add the total policy premium
                                                                       // in policy table
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(
                    "UPDATE policy set policypremium = " + policyPremium + " WHERE policynumber = " + policyID + ""); // updates
                                                                                                                      // the
                                                                                                                      // policytable
                                                                                                                      // so
                                                                                                                      // we
                                                                                                                      // can
                                                                                                                      // store
                                                                                                                      // the
                                                                                                                      // totalPremium
                                                                                                                      // into
                                                                                                                      // it

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
        }

    }

    public void setPolicyHolderPolicy(int policyID, int policyHolderID) { // this method will update the policyholder,
                                                                          // and adds the poilcynumber that corresponds
                                                                          // to him/her
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas", "root", "");
                Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(
                    "UPDATE policyholder set policy = " + policyID + " WHERE id = " + policyHolderID + "");

        } catch (SQLException e) { // handles errors
            System.out.println("INVALID! " + e);
        }

    }

}
