import java.util.Calendar;
import java.util.Scanner;

/**
 * Java Course 2 Module 1
 *
 * @author Mike Lemuel Chan
 * @Description: The final activity in Brightspace (capstone). This system
 *               simulates the process of insurance specifically automobile.
 * @CreatedDate: 6/6/2022
 * @ModifiedDate: 7/18/2022
 *
 */

public class PASApp {

    public static void main(String[] args) throws Exception { // MAIN METHOD
        /*
         * Instantiation of classes
         * take note that go.clearConsole is for clearing the console
         * and go.pauseClear is for prompting "Enter to continue", and upon
         * hitting ENTER, the terminal will also be cleared.
         */
        DisplayDesign go = new DisplayDesign();
        Scanner sc = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        CustomerAccount customer = new CustomerAccount();
        AccidentClaim claim = new AccidentClaim();
        Policy policy = new Policy();
        DatabaseConnection db = new DatabaseConnection();

        String choice;
        boolean mainLoop = true;

        while (mainLoop) {
            db.checkDatabase(); // checks the connection in database
            db.checkPolicyStatus(); // updates the status of policy everytime the loop continues

            go.printBox("AUTOMOBILE INSURANCE | " + calendar.getTime());
            System.out.println("[1] - Create new Customer Account \n[2] - Get Policy Quote & Buy the Plicy" +
                    "\n[3] - Cancel Specific Policy \n[4] - File an Accident Claim" +
                    "\n[5] - Search for a Customer Account \n[6] - Search & Display Specific Policy" +
                    "\n[7] - Search & Display Specific Claim \n[8] - Exit");
            System.out.print("\nChoose: ");
            choice = sc.next(); // prompts user for choices
            choice += sc.nextLine();

            switch (choice) {
                case "1":
                    customer.createAccount(); // calls the createaccount method under customeraccount class
                    break;

                case "2":
                    policy.createPolicy(); // calls the createpolicy method under policy class
                    break;

                case "3":
                    policy.cancelPolicy();
                    break;

                case "4": // creates an accident claim in specific policy
                    claim.createClaim();
                    break;

                case "5":
                    customer.searchAccount(); // searches for customer account
                    break;

                case "6":
                    policy.searchPolicy(); // searches for specific policy
                    break;

                case "7": // searches for specific claims
                    claim.searchClaim();
                    break;

                case "8": // exits the program
                    System.out.println("Thank you and have a nice day!");
                    go.pauseClear();
                    sc.close();
                    mainLoop = false;
                    db.conn.close();
                    break;

                default: // if input is invalid
                    System.out.println("INVALID choice! Please Try again.");
                    go.pauseClear();

            }
            go.pauseClear(); // pauses and clears the terminal window / console
        }

    }
}
