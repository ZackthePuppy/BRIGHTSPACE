import java.util.InputMismatchException;
import java.util.Scanner;

/**
* Java Course 3 Module 3
*
* @author Mike Lemuel Chan
* @Description: Mysql connectivity, searching values from mysql table
* @CreatedDate: 5/23/2022
* @ModifiedDate: 6/08/2022 fixed inputmismatchexception
*
*/

public class UserPolicyProgram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MySQLProcess mysql = new MySQLProcess(); // instantiating MysqlProcess class
        DisplayDesign go = new DisplayDesign(); // instantiating displaydesign class, this is for border box around
                                                // title text, purely for design, also for clearing the console
        int choice;
        String search;
        boolean mainLoop = true;

        while (mainLoop) {
            try {
            go.printBox("USER POLICY PROGRAM"); //prints a string with a border box

            System.out.print("[1] - View All Policy Details \n[2] - Show Policy Details " +
                    "where date_registered is less than '2012-01-01' \n[3] - Search Policy Details based by date_registered" +
                    "\n[4] - Exit \n\nChoose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    mysql.viewAll(); //calls the method viewAll in MysqlProcess class
                    break;
                case 2:
                    mysql.viewspecific("2012-01-01");
                    break;
                case 3:
                    System.out.print("Enter a value: "); 
                    search = sc.next();
                    mysql.viewspecific(search); //calls the viewspecific method and then pass a parameter's value that's originated from user input
                    break;
                case 4:
                    System.out.println("Thank you!");
                    mainLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;

            }
            go.PauseClear(); //pauses the console, then clears it after hitting the enter button
        } catch (InputMismatchException e){
            System.out.println("Invalid input! Try again.");
            sc.nextLine();
            go.PauseClear();
        }
    }
        sc.close();

    }
}
