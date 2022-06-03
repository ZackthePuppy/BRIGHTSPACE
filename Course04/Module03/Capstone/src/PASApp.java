import java.util.Calendar;
import java.util.Scanner;

public class PASApp {
    public static void main(String[] args) throws Exception {
        DisplayDesign go = new DisplayDesign(); // instantiation of class, this is just for the design and cleanliness
                                                // of output
        Scanner sc = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        CustomerAccount createAcc = new CustomerAccount();
        Policy policy = new Policy();
        String choice;
        boolean mainLoop = true;

        while (mainLoop) {

            go.printBox("AUTOMOBILE INSURANCE | " + calendar.getTime());
            System.out.println("[1] - Create new Customer Account \n[2] - Get Policy Quote & Buy the Policy" +
                    "\n[3] - Cancel Specific Policy \n[4] - File an Accident Claim" +
                    "\n[5] - Search for a Customer Account \n[6] - Search & Display Specific Policy" +
                    "\n[7] - Search & Display Specific Claim \n[8] - Exit");
            System.out.print("\nChoose: ");
            choice = sc.next(); // prompts user for choices

            switch (choice) {
                case "1":
                    createAcc.createAccount(); //calls the customeraccount class
                    break;
                case "2":
                    policy.createPolicy(); //calls the policy class
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    System.out.println("Thank you and have a nice day!");
                    go.pauseClear();
                    sc.close();
                    break;
                default:
                    System.out.println("INVALID choice! Please Try again.");
                    go.pauseClear();

            }
            go.pauseClear();

        }

    }
}
