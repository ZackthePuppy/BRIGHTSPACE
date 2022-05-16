import java.util.InputMismatchException;
import java.util.Scanner;

/**

* @author Mike Lemuel Chan

* @Description: Calculates the economic policy based on conditions that are given

* @CreatedDate: 5/12/2022

* @ModifiedDate: 5/12/2022

*/

public class EconomicPolicy {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int growthRate, inflation;
        System.out.println("ECONOMIC POLICY");

        try { //executes the code smoothly if no errors occur
            System.out.print("Enter Growth Rate: ");
            growthRate = sc.nextInt(); //gets the value of growthRate on user input

            System.out.print("Enter Inflation Rate: ");
            inflation = sc.nextInt(); //gets the value of inflation on user input

            System.out.println("\nRecommended Economic Policy: ");

            if (growthRate < 1) { //executes if the value of growthRate is less than 1
                if (inflation < 3) { //if the value of inflation is less than 3
                    System.out.println("Increase welfare spending, "
                            + "reduce personal taxes, and decrease discount rate.");
                } else { //otherwise, execute this
                    System.out.println("Reduce business taxes.");
                }
            }

            else if (growthRate > 4) {
                if (inflation < 1) {
                    System.out.println("Increase personal and business taxes, "
                            + "and decrease discount rate.");
                } else if (inflation > 3) {
                    System.out.println("Increase discount rate.");
                } else { //if the value of inflation is between 1 and 3
                    System.out.println("No change in economic policy.");
                }
            }

            else { //executes if the value of growthRate is between 1 and 4
                System.out.println("No change in economic policy.");
            }

            sc.close();

        }

        catch (InputMismatchException e) { //catches the error if ever the user puts character instead of numbers
            System.out.println("ERROR! Invalid Input!!!");
        }
    }
}
