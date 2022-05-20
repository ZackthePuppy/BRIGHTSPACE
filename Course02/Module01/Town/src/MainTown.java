import java.util.Scanner;

/**
 * Java Course 2 Module 1
* @author Mike Lemuel Chan
* @Description: Initializes child and adult population, then displays it. Also, it can add twin child or single child.
* @CreatedDate: 5/16/2022
* @ModifiedDate: 5/18/2022
*/

public class MainTown {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Town town = new Town(); //instantiates the Town class in order to access its method
        String choice = "";
        boolean mainLoop = true; //for looping

        town.initialize(); //calls the initialize method inside the Town class

        while (mainLoop) { //unlimited loop until user choose exit
            System.out.println("NEW HOME");
            System.out.print("[1] - Birthing a child (twins)" +
                    " \n[2] - Birthing a child \n[3] - Show Vital Statistics" +
                    " \n[4] - Exit \n\nChoose: ");
            choice = sc.nextLine(); //choose from selection

            switch (choice) {
                case "1":
                    town.simulateBirth(2); //calls the simulateBirth method in Town class and pass a parameter which contains integer
                    break;
                case "2":
                    town.simulateBirth(1);
                    break;
                case "3":
                    town.printStatistics(); //calls the printStatistics method in order to display the population of adults and child
                    break;
                case "4":
                    System.out.println("Thank you!");
                    mainLoop = false; //breaks the unlimited while loop
                    break;
                default:
                    System.out.print("Invalid selection! Try again. \nEnter to continue...");
                    break;
            }

            town.promptEnterKey();

        }
        sc.close();

    }
}
