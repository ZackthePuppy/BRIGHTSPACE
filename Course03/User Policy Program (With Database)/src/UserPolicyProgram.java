import java.util.Scanner;

/**
* Java Course 3 Unknown Module
*
* @author Mike Lemuel Chan
* @Description: Mysql connectivity, searching values from mysql table
* @CreatedDate: 5/23/2022
* @ModifiedDate: 5/23/2022
*
*/

public class UserPolicyProgram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MysqlProcess mysql = new MysqlProcess(); // instantiating MysqlProcess class
        DisplayDesign go = new DisplayDesign(); // instantiating displaydesign class, this is for border box around
                                                // title text, purely for design
        int choice;
        String search;
        boolean mainLoop = true;

        while (mainLoop) {
            go.printBox("USER POLICY PROGRAM");

            System.out.print("[1] - View All Policy Details \n[2] - Show Policy Details " +
                    "where date_registered is less than '2012-01-01' \n[3] - Search Policy Details based by date_registered" +
                    "\n[4] - Exit \n\nChoose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    mysql.viewAll();
                    break;
                case 2:
                    mysql.viewspecific("2012-01-01");
                    break;
                case 3:
                    System.out.print("Enter a value: ");
                    search = sc.next();
                    mysql.viewspecific(search);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    mainLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;

            }
            go.PauseClear();
        }
        sc.close();

    }
}
